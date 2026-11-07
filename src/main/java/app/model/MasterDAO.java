package app.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterDAO {
    private final OrmMasterDAO orm = new OrmMasterDAO();
    private static final String DEFAULT_KARYAWAN = "COALESCE((SELECT MIN(id_karyawan) FROM karyawan WHERE status='Aktif'), 1)";

    private boolean exec(String sql, Object... params) {
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                Object p = params[i];
                if (p instanceof String && ((String) p).trim().isEmpty()) p = null;
                ps.setObject(i + 1, p);
            }
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw new IllegalStateException("Operasi database gagal: " + e.getMessage(), e);
        }
    }

    private List<Object[]> get(String sql, int cols) {
        List<Object[]> list = new ArrayList<>();
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[cols];
                for (int i = 0; i < cols; i++) row[i] = rs.getObject(i + 1);
                list.add(row);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Gagal membaca data dari database: " + e.getMessage(), e);
        }
        return list;
    }

    public List<Object[]> getAllKaryawan() {
        return get("SELECT id_karyawan, nama, no_telepon, status, shift FROM karyawan", 5);
    }
    public boolean simpanKaryawan(String n, String telp, String stat, String shift) {
        n = KaryawanDAO.namaValid(n);
        telp = KaryawanDAO.nomorValid(telp);
        if (!List.of("Aktif", "Nonaktif").contains(stat)
                || !List.of("Siang", "Malam").contains(shift)) {
            throw new IllegalArgumentException("Status atau shift karyawan tidak valid.");
        }
        Connection conn = null;
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false);
            KaryawanDAO.pastikanNamaUnik(conn, n, -1);
            int nextId = Koneksi.getNextId(conn, "karyawan", "id_karyawan");
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO karyawan (id_karyawan, nama, password, no_telepon, jenis_kelamin, alamat, tanggal_lahir, tanggal_masuk, status, shift) " +
                    "VALUES (?, ?, '123', ?, 'Laki-laki', '-', CURDATE(), CURDATE(), ?, ?)")) {
                ps.setInt(1, nextId);
                ps.setString(2, n);
                ps.setString(3, telp);
                ps.setString(4, stat);
                ps.setString(5, shift);
                if (ps.executeUpdate() != 1) throw new SQLException("Karyawan gagal ditambahkan.");
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ignored) { }
            if (e instanceof IllegalArgumentException) throw (IllegalArgumentException) e;
            throw new IllegalStateException("Gagal menyimpan karyawan: " + e.getMessage(), e);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignored) { }
        }
    }

    public boolean updateKaryawan(String id, String n, String telp, String stat, String shift) {
        n = KaryawanDAO.namaValid(n);
        telp = KaryawanDAO.nomorValid(telp);
        if (!List.of("Aktif", "Nonaktif").contains(stat)
                || !List.of("Siang", "Malam").contains(shift)) {
            throw new IllegalArgumentException("Status atau shift karyawan tidak valid.");
        }
        int idKaryawan;
        try { idKaryawan = Integer.parseInt(id.trim()); }
        catch (Exception e) { throw new IllegalArgumentException("ID karyawan tidak valid."); }
        Connection conn = null;
        try {
            conn = Koneksi.getConnection();
            conn.setAutoCommit(false);
            KaryawanDAO.pastikanNamaUnik(conn, n, idKaryawan);
            try (PreparedStatement ps = conn.prepareStatement(
                    "UPDATE karyawan SET nama=?, no_telepon=?, status=?, shift=? WHERE id_karyawan=?")) {
                ps.setString(1, n);
                ps.setString(2, telp);
                ps.setString(3, stat);
                ps.setString(4, shift);
                ps.setInt(5, idKaryawan);
                if (ps.executeUpdate() != 1) {
                    throw new IllegalArgumentException("Karyawan tidak ditemukan atau tidak ada perubahan.");
                }
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ignored) { }
            if (e instanceof IllegalArgumentException) throw (IllegalArgumentException) e;
            throw new IllegalStateException("Gagal mengubah karyawan: " + e.getMessage(), e);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException ignored) { }
        }
    }

    public boolean hapusKaryawan(String id) {
        return exec("DELETE FROM karyawan WHERE id_karyawan=?", id);
    }

    public List<Object[]> getAllObat() {
        return orm.semuaObat();
    }
    public boolean simpanObat(String n, String j, String s) {
        return orm.tambahObat(n, j, s, -1);
    }
    public boolean simpanObat(String n, String j, String s, int idLogin) {
        return orm.tambahObat(n, j, s, idLogin);
    }
    public boolean updateObat(String id, String n, String j, String s) {
        return orm.ubahObat(id, n, j, s);
    }
    public boolean updateStatusObat(int id, String s) {
        return orm.ubahStatusObat(id, s);
    }
    public boolean hapusObat(String id) {
        return orm.hapusObat(id);
    }

    public List<Object[]> getAllBahan() {
        return get("SELECT id_bahan, nama_bahan, stok, satuan_bahan FROM bahan_baku ORDER BY id_bahan", 4);
    }
    private void pastikanNamaUnik(String tabel, String kolomId, String kolomNama, String nama, int kecualiId) {
        // Nama tabel/kolom hanya berasal dari konstanta dalam source, bukan input pengguna.
        String sql = "SELECT COUNT(*) FROM " + tabel + " WHERE LOWER(TRIM(" + kolomNama + "))=LOWER(TRIM(?))" +
            (kecualiId > 0 ? " AND " + kolomId + "<>?" : "");
        try (Connection c=Koneksi.getConnection(); PreparedStatement ps=c.prepareStatement(sql)) {
            ps.setString(1, nama.trim());
            if (kecualiId > 0) ps.setInt(2, kecualiId);
            try (ResultSet rs=ps.executeQuery()) {
                rs.next();
                if (rs.getInt(1)>0) throw new IllegalArgumentException("Nama bahan sudah terdaftar. Gunakan nama lain.");
            }
        } catch(SQLException ex) { throw new IllegalStateException("Gagal memeriksa nama bahan: " + ex.getMessage(), ex); }
    }
    public boolean simpanBahan(String n, String stok, String sat) {
        return simpanBahan(n, stok, sat, -1);
    }
    public boolean simpanBahan(String n, String stok, String sat, int idLogin) {
        int jumlah=Integer.parseInt(stok.trim());
        if(jumlah<0) throw new IllegalArgumentException("Stok tidak boleh negatif.");
        if(n==null || n.isBlank() || n.trim().length()>50) throw new IllegalArgumentException("Nama bahan wajib diisi, maksimal 50 karakter.");
        if(sat==null || sat.isBlank() || sat.length()>5) throw new IllegalArgumentException("Satuan wajib diisi, maksimal 5 karakter.");
        pastikanNamaUnik("bahan_baku", "id_bahan", "nama_bahan", n, -1);
        try(Connection conn=Koneksi.getConnection()) {
            OrmMasterDAO.getValidKaryawanId(conn,idLogin);
            try(PreparedStatement ps=conn.prepareStatement("INSERT INTO bahan_baku(nama_bahan,stok,satuan_bahan,id_karyawan) VALUES(?,?,?,?)")) {
                ps.setString(1,n.trim()); ps.setInt(2,jumlah); ps.setString(3,sat); ps.setInt(4,idLogin);
                return ps.executeUpdate()==1;
            }
        } catch(SQLException ex) { throw new IllegalStateException("Gagal menyimpan bahan: "+ex.getMessage(),ex); }
    }

    public boolean updateBahan(String id, String n, String stok, String sat) {
        if(n==null || n.isBlank() || n.trim().length()>50 || Integer.parseInt(stok)<0) throw new IllegalArgumentException("Nama atau stok bahan tidak valid.");
        try(Connection c=Koneksi.getConnection(); PreparedStatement p=c.prepareStatement("SELECT satuan_bahan FROM bahan_baku WHERE id_bahan=?")) {
            p.setInt(1,Integer.parseInt(id));
            try(ResultSet rs=p.executeQuery()) {
                if(!rs.next()) return false;
                if(!sat.equalsIgnoreCase(rs.getString(1))) throw new IllegalArgumentException("Satuan bahan yang sudah tersimpan tidak dapat diganti. Buat bahan baru untuk satuan berbeda.");
            }
        } catch(SQLException ex) { throw new IllegalStateException("Gagal membaca bahan.",ex); }
        pastikanNamaUnik("bahan_baku", "id_bahan", "nama_bahan", n, Integer.parseInt(id));
        return exec("UPDATE bahan_baku SET nama_bahan=?, stok=? WHERE id_bahan=?", n.trim(), stok, id);
    }
    public boolean hapusBahan(String id) {
        return exec("DELETE FROM bahan_baku WHERE id_bahan=?", id);
    }

    public List<Object[]> getAllInstansi() {
        return orm.semuaInstansi();
    }
    public boolean simpanInstansi(String n, String j, String a, String k) {
        return orm.tambahInstansi(n, j, a, k);
    }
    public boolean updateInstansi(String id, String n, String j, String a, String k) {
        return orm.ubahInstansi(id, n, j, a, k);
    }
    public boolean hapusInstansi(String id) {
        return orm.hapusInstansi(id);
    }

    public List<Object[]> getAllPesanan() {
        return get("SELECT p.id_pesanan, p.tanggal_pesanan, p.status_pesanan, p.catatan, " +
                   "       CONCAT(i.id_instansi, ' - ', i.nama_instansi) AS instansi, " +
                   "       CONCAT(k.id_karyawan, ' - ', k.nama) AS petugas " +
                   "FROM pesanan p " +
                   "LEFT JOIN instansi_kesehatan i ON i.id_instansi = p.id_instansi " +
                   "LEFT JOIN karyawan k ON k.id_karyawan = p.id_karyawan " +
                   "ORDER BY p.id_pesanan DESC", 6);
    }

    public List<Object[]> getAllPengiriman() {
        return get("SELECT p.nomor_resi, p.tanggal_pengiriman, p.status_pengiriman, p.keterangan, " +
                   "       p.id_pesanan, " +
                   "       CONCAT(k.id_karyawan, ' - ', k.nama) AS petugas " +
                   "FROM pengiriman p " +
                   "LEFT JOIN karyawan k ON k.id_karyawan = p.id_karyawan " +
                   "ORDER BY p.nomor_resi DESC", 6);
    }
    public boolean updatePengiriman(String resi, String tgl, String stat, String ket, String idPes, String idKar) {
        if (!java.util.List.of("Menunggu", "Diproses", "Selesai", "Batal").contains(stat)) {
            throw new IllegalArgumentException("Status pengiriman tidak valid.");
        }
        new AlurDAO().ubahPengiriman(Integer.parseInt(resi), stat, ket);
        return true;
    }

    public List<Object[]> getAllProduksi() {
        return get("SELECT nomor_batch, tanggal_produksi, tanggal_kedaluwarsa, stok, satuan_stok, id_obat, id_karyawan, id_detail FROM produksi ORDER BY nomor_batch DESC", 8);
    }
    public List<Object[]> getAllDetailProduksi() {
        return get("SELECT id_detail, jumlah_bahan, satuan_bahan, id_bahan, nomor_batch FROM detail_penggunaan_bahan ORDER BY id_detail DESC", 5);
    }

    public List<String> getCbData(String sql) {
        List<String> list = new ArrayList<>();
        try (Connection conn = Koneksi.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(rs.getInt(1) + " - " + rs.getString(2));
        } catch (Exception e) {
            throw new IllegalStateException("Gagal membaca data dari database: " + e.getMessage(), e);
        }
        return list;
    }

    public List<String> getInstansiOptions() {
        return getCbData("SELECT id_instansi, nama_instansi FROM instansi_kesehatan ORDER BY nama_instansi");
    }

    public List<String> getKaryawanOptions() {
        return getCbData("SELECT id_karyawan, nama FROM karyawan WHERE status='Aktif' ORDER BY nama");
    }

    public List<String> getObatOptions() {
        return getCbData("SELECT id_obat, nama_obat FROM obat ORDER BY nama_obat");
    }

    public List<String> getBahanOptions() {
        return getCbData("SELECT id_bahan, nama_bahan FROM bahan_baku ORDER BY nama_bahan");
    }

    public List<String> getPesananOptions() {
        return getCbData("SELECT p.id_pesanan, CONCAT('Pesanan #', p.id_pesanan, ' (', i.nama_instansi, ' - ', p.status_pesanan, ')') FROM pesanan p JOIN instansi_kesehatan i ON i.id_instansi=p.id_instansi ORDER BY p.id_pesanan DESC");
    }

    public List<String> getPesananOptionsBatch() {
        return getCbData("SELECT p.id_pesanan, CONCAT('Pesanan #', p.id_pesanan, ' (', i.nama_instansi, ')') " +
                "FROM pesanan p JOIN instansi_kesehatan i ON i.id_instansi=p.id_instansi " +
                "WHERE p.status_pesanan='Menunggu' " +
                "AND NOT EXISTS (SELECT 1 FROM detail_pesanan d WHERE d.id_pesanan=p.id_pesanan) " +
                "AND NOT EXISTS (SELECT 1 FROM pengiriman g WHERE g.id_pesanan=p.id_pesanan) " +
                "ORDER BY p.id_pesanan DESC");
    }

    // Batch yang bisa ditambahkan ke detail pesanan, berdasarkan stok saat query dijalankan.
    public List<String> getBatchOptionsPesanan() {
        List<String> list = new ArrayList<>();
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT p.nomor_batch, CONCAT('Batch #', p.nomor_batch, ' (', o.nama_obat, ' - Stok: ', p.stok, ' ', p.satuan_stok, ')') " +
                 "FROM produksi p JOIN obat o ON o.id_obat=p.id_obat " +
                 "WHERE p.stok > 0 AND p.tanggal_kedaluwarsa >= CURDATE() " +
                 "AND NOT EXISTS (SELECT 1 FROM detail_pesanan d WHERE d.nomor_batch=p.nomor_batch) " +
                 "ORDER BY p.nomor_batch DESC");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(rs.getInt(1) + " - " + rs.getString(2));
        } catch (SQLException e) {
            throw new IllegalStateException("Gagal membaca batch tersedia: " + e.getMessage(), e);
        }
        return list;
    }

    public List<String> getBatchOptions() {
        List<String> list = new ArrayList<>();
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT p.nomor_batch, CONCAT('Batch #', p.nomor_batch, ' (', o.nama_obat, ' - Stok: ', p.stok, ' ', p.satuan_stok, ')') FROM produksi p JOIN obat o ON o.id_obat=p.id_obat ORDER BY p.nomor_batch DESC");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getInt(1) + " - " + rs.getString(2));
            }
        } catch (Exception e) {
            throw new IllegalStateException("Gagal membaca daftar batch: " + e.getMessage(), e);
        }
        return list;
    }
}
