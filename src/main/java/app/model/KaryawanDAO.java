package app.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public final class KaryawanDAO {

    public static String namaValid(String nama) {
        if (nama == null || nama.isBlank() || nama.trim().length() > 50) {
            throw new IllegalArgumentException("Nama karyawan wajib diisi, maksimal 50 karakter.");
        }
        return nama.trim();
    }

    public static String nomorValid(String nomor) {
        if (nomor == null) throw new IllegalArgumentException("Nomor telepon wajib diisi.");
        String bersih = nomor.trim().replace(" ", "").replace("-", "");
        if (!bersih.matches("(?:08[0-9]{8,12}|\\+628[0-9]{8,11})")) {
            throw new IllegalArgumentException("Nomor telepon harus berformat 08xxxxxxxxxx atau +628xxxxxxxxxx.");
        }
        return bersih;
    }

    public static void pastikanNamaUnik(Connection c, String nama, int kecualiId) throws SQLException {
        try (PreparedStatement cek = c.prepareStatement(
                "SELECT COUNT(*) FROM karyawan WHERE LOWER(TRIM(nama))=LOWER(TRIM(?)) AND id_karyawan<>?")) {
            cek.setString(1, nama);
            cek.setInt(2, kecualiId);
            try (ResultSet rs = cek.executeQuery()) {
                rs.next();
                if (rs.getInt(1) > 0) {
                    throw new IllegalArgumentException("Nama karyawan sudah terdaftar.");
                }
            }
        }
    }

    public List<Object[]> semua() {
        List<Object[]> rows = new ArrayList<>();
        try (Connection c = Koneksi.getConnection()) {
            String sql = "SELECT k.id_karyawan, k.nama, k.no_telepon, k.jenis_kelamin, k.alamat, " +
                         "       k.tanggal_lahir, k.shift, k.status, " +
                         "       CASE WHEN pf.id_karyawan IS NOT NULL THEN 'Fulfillment' ELSE po.divisi END AS peran, " +
                         "       COALESCE(pf.area_logistik, po.area_produksi, '-') AS area " +
                         "FROM karyawan k " +
                         "LEFT JOIN petugas_operasional po ON po.id_karyawan = k.id_karyawan " +
                         "LEFT JOIN petugas_fulfillment pf ON pf.id_karyawan = k.id_karyawan " +
                         "ORDER BY k.id_karyawan";

            try (PreparedStatement p = c.prepareStatement(sql);
                 ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    Object[] data = new Object[10];
                    for (int i = 0; i < 10; i++) data[i] = r.getObject(i + 1);
                    rows.add(data);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Gagal membaca data karyawan: " + ex.getMessage(), ex);
        }
        return rows;
    }

    public int tambah(String nama, String password, String telepon, String jk, String alamat, String lahir, String shift, String peran, String area) {
        nama = namaValid(nama);
        String nomor = nomorValid(telepon);
        if (alamat == null || alamat.isBlank()) {
            throw new IllegalArgumentException("Alamat karyawan wajib diisi.");
        }
        if (password == null || password.isBlank()) {
            password = "123";
        }
        if (password.length() > 10) {
            throw new IllegalArgumentException("Password maksimal 10 karakter pada database project ini.");
        }
        if (!List.of("Laki-laki", "Perempuan").contains(jk)) {
            jk = "Laki-laki";
        }
        if (!List.of("Siang", "Malam").contains(shift)) {
            shift = "Siang";
        }
        if (!List.of("Administrasi", "Produksi", "Fulfillment").contains(peran)) {
            throw new IllegalArgumentException("Peran harus salah satu dari: Administrasi, Produksi, atau Fulfillment.");
        }
        if (!peran.equals("Administrasi") && (area == null || area.isBlank())) {
            area = peran.equals("Produksi") ? "Pabrik Sediaan Farmasi" : "Gudang Logistik Samarinda";
        }
        if (lahir == null || lahir.isBlank()) {
            lahir = "1995-01-01";
        }

        LocalDate tanggalLahir;
        try { tanggalLahir=LocalDate.parse(lahir); }
        catch (Exception ex) { throw new IllegalArgumentException("Tanggal lahir harus berformat YYYY-MM-DD."); }
        if(tanggalLahir.isAfter(LocalDate.now())) throw new IllegalArgumentException("Tanggal lahir tidak boleh di masa depan.");
        if(nama.trim().length()>50 || telepon.trim().length()>20 || alamat.trim().length()>255 || (area!=null&&area.length()>100)) throw new IllegalArgumentException("Isian terlalu panjang untuk kolom database.");
        Connection c = null;
        try {
            c = Koneksi.getConnection();
            c.setAutoCommit(false);
            pastikanNamaUnik(c, nama, -1);

            int id;
            try (PreparedStatement p = c.prepareStatement("INSERT INTO karyawan(nama,password,no_telepon,jenis_kelamin,alamat,tanggal_lahir,tanggal_masuk,status,shift) VALUES(?,?,?,?,?,?,CURDATE(),'Aktif',?)", Statement.RETURN_GENERATED_KEYS)) {
                p.setString(1,nama); p.setString(2,password); p.setString(3,nomor);
                p.setString(4,jk); p.setString(5,alamat.trim()); p.setDate(6,java.sql.Date.valueOf(lahir)); p.setString(7,shift);
                p.executeUpdate();
                try (ResultSet keys=p.getGeneratedKeys()) {
                    if(!keys.next()) throw new SQLException("ID karyawan otomatis tidak tersedia.");
                    id=keys.getInt(1);
                }
            }

            if (peran.equals("Fulfillment")) {
                try (PreparedStatement p = c.prepareStatement("INSERT INTO petugas_fulfillment(id_karyawan, area_logistik) VALUES(?,?)")) {
                    p.setInt(1, id);
                    p.setString(2, area);
                    p.executeUpdate();
                }
            } else {
                try (PreparedStatement p = c.prepareStatement("INSERT INTO petugas_operasional(id_karyawan, divisi, area_produksi) VALUES(?,?,?)")) {
                    p.setInt(1, id);
                    p.setString(2, peran);
                    p.setString(3, peran.equals("Produksi") ? area : null);
                    p.executeUpdate();
                }
            }

            c.commit();
            return id;
        } catch (Exception ex) {
            if (c != null) try { c.rollback(); } catch (SQLException ignored) {}
            throw new IllegalStateException("Gagal menambah karyawan: " + ex.getMessage(), ex);
        } finally {
            if (c != null) try { c.close(); } catch (SQLException ignored) {}
        }
    }

    public void updateStatus(int id, String status) {
        if (!List.of("Aktif", "Nonaktif").contains(status)) {
            throw new IllegalArgumentException("Status harus Aktif atau Nonaktif.");
        }
        try (Connection c = Koneksi.getConnection();
             PreparedStatement p = c.prepareStatement("UPDATE karyawan SET status=? WHERE id_karyawan=?")) {
            p.setString(1, status);
            p.setInt(2, id);
            if (p.executeUpdate() != 1) throw new IllegalArgumentException("ID karyawan tidak ditemukan.");
        } catch (SQLException ex) {
            throw new IllegalStateException("Update status gagal: " + ex.getMessage(), ex);
        }
    }

    public void hapus(int id) {
        Connection c = null;
        try {
            c = Koneksi.getConnection();
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement("DELETE FROM petugas_operasional WHERE id_karyawan=?")) {
                p.setInt(1, id);
                p.executeUpdate();
            }
            try (PreparedStatement p = c.prepareStatement("DELETE FROM petugas_fulfillment WHERE id_karyawan=?")) {
                p.setInt(1, id);
                p.executeUpdate();
            }
            try (PreparedStatement p = c.prepareStatement("DELETE FROM karyawan WHERE id_karyawan=?")) {
                p.setInt(1, id);
                if (p.executeUpdate() != 1) throw new IllegalArgumentException("ID karyawan tidak ditemukan.");
            }
            c.commit();
        } catch (Exception ex) {
            if (c != null) try { c.rollback(); } catch (SQLException ignored) {}
            throw new IllegalStateException("Karyawan tidak dapat dihapus (masih tercatat pada data pesanan, pengiriman, atau produksi): " + ex.getMessage(), ex);
        } finally {
            if (c != null) try { c.close(); } catch (SQLException ignored) {}
        }
    }
}
