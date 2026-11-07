package app.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public final class AlurDAO {
    private static int id(String value) {
        if (value == null) return 0;
        String v = value.trim();
        if (v.contains(" - ")) v = v.split(" - ")[0].trim();
        return Integer.parseInt(v);
    }

    private static int positif(String value) {
        int n = id(value);
        if (n <= 0) throw new IllegalArgumentException("Jumlah harus lebih dari nol.");
        return n;
    }

    private static void rollback(Connection c) {
        if (c != null) try { c.rollback(); } catch (SQLException ignored) { }
    }

    private static List<Object[]> rows(String sql, int n) {
        List<Object[]> out = new ArrayList<>();
        try (Connection c = Koneksi.getConnection(); PreparedStatement p = c.prepareStatement(sql); ResultSet r = p.executeQuery()) {
            while (r.next()) {
                Object[] row = new Object[n];
                for (int i = 0; i < n; i++) row[i] = r.getObject(i + 1);
                out.add(row);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Gagal membaca data: " + ex.getMessage(), ex);
        }
        return out;
    }

    public List<Object[]> penggunaan() {
        return rows(
            "SELECT d.id_detail, " +
            "       CONCAT(b.id_bahan, ' - ', b.nama_bahan), " +
            "       d.jumlah_bahan, " +
            "       d.satuan_bahan, " +
            "       d.nomor_batch " +
            "FROM detail_penggunaan_bahan d " +
            "JOIN bahan_baku b ON b.id_bahan = d.id_bahan " +
            "ORDER BY d.id_detail DESC", 5);
    }

    public List<Object[]> bahanDalamBatch(int nomorBatch) {
        String sql = "SELECT b.nama_bahan, d.jumlah_bahan, d.satuan_bahan " +
            "FROM detail_penggunaan_bahan d JOIN bahan_baku b ON b.id_bahan=d.id_bahan " +
            "WHERE d.nomor_batch=" + nomorBatch + " ORDER BY b.nama_bahan";
        return rows(sql, 3);
    }

    /** Nilai produksi.stok adalah jumlah obat di dalam batch sesuai database final. */
    public static Object jumlahObatUntukTampilan(Object jumlahTercatat) {
        return jumlahTercatat;
    }

    public List<Object[]> detailPesanan() {
        return rows(
            "SELECT d.id_detail, d.id_pesanan, d.nomor_batch, pr.stok AS jumlah_obat " +
            "FROM detail_pesanan d JOIN produksi pr ON pr.nomor_batch=d.nomor_batch " +
            "ORDER BY d.id_detail DESC", 4);
    }

    public List<Object[]> batchTersedia() {
        return rows(
            "SELECT p.nomor_batch, o.nama_obat, p.stok, p.satuan_stok, p.tanggal_kedaluwarsa " +
            "FROM produksi p JOIN obat o ON p.id_obat=o.id_obat " +
            "WHERE p.stok > 0 AND p.tanggal_kedaluwarsa >= CURDATE() AND NOT EXISTS (SELECT 1 FROM detail_pesanan dp WHERE dp.nomor_batch=p.nomor_batch) " +
            "ORDER BY p.tanggal_kedaluwarsa ASC", 5);
    }

    /** Seluruh batch ditampilkan dengan lima kolom, tanpa filter alokasi, stok, atau kedaluwarsa. */
    public List<Object[]> semuaBatch() {
        return rows(
            "SELECT p.nomor_batch, COALESCE(o.nama_obat, CONCAT('Obat #', p.id_obat)), " +
            "p.stok, p.satuan_stok, p.tanggal_kedaluwarsa " +
            "FROM produksi p LEFT JOIN obat o ON o.id_obat=p.id_obat " +
            "ORDER BY p.nomor_batch DESC", 5);
    }

    public List<Object[]> produksi() {
        return rows(
            "SELECT p.nomor_batch, " +
            "       o.nama_obat, " +
            "       p.stok, " +
            "       p.satuan_stok, " +
            "       p.tanggal_produksi, " +
            "       p.tanggal_kedaluwarsa, " +
            "       COALESCE((SELECT GROUP_CONCAT(CONCAT(bb.nama_bahan, ' (', dp.jumlah_bahan, ' ', dp.satuan_bahan, ')') SEPARATOR ', ') " +
            "                 FROM detail_penggunaan_bahan dp " +
            "                 JOIN bahan_baku bb ON bb.id_bahan = dp.id_bahan " +
            "                 WHERE dp.nomor_batch = p.nomor_batch), '-') AS bahan_digunakan, " +
            "       COALESCE(k.nama, CONCAT('Petugas #', p.id_karyawan)) AS nama_petugas, " +
            "       CASE WHEN p.tanggal_kedaluwarsa < CURDATE() THEN 'Kedaluwarsa' ELSE 'Layak Edar' END AS status_kelayakan " +
            "FROM produksi p " +
            "JOIN obat o ON o.id_obat = p.id_obat " +
            "LEFT JOIN karyawan k ON k.id_karyawan = p.id_karyawan " +
            "ORDER BY p.nomor_batch DESC", 9);
    }

    public int produksiSederhana(int idObat, int stok, int idKaryawan) {
        if (stok != 25 && stok != 50 && stok != 100) throw new IllegalArgumentException("Jumlah produksi harus 25, 50, atau 100.");
        Connection c = null;
        try {
            c = Koneksi.getConnection(); c.setAutoCommit(false);
            OrmMasterDAO.getValidKaryawanId(c, idKaryawan);
            String satuan = "Strip";
            try (PreparedStatement p = c.prepareStatement("SELECT nama_obat FROM obat WHERE id_obat=?")) {
                p.setInt(1,idObat);
                try (ResultSet r = p.executeQuery()) {
                    if (!r.next()) throw new IllegalArgumentException("Obat tidak ditemukan.");
                    String nama = r.getString(1).toLowerCase(Locale.ROOT);
                    if (nama.contains("sirup") || nama.contains("suspensi") || nama.contains("larutan") || nama.contains("drop")) satuan = "Botol";
                }
            }
            int batch;
            try (PreparedStatement p = c.prepareStatement("INSERT INTO produksi(tanggal_kedaluwarsa,stok,satuan_stok,id_obat,id_karyawan,id_detail) VALUES(?,?,?,?,?,NULL)",Statement.RETURN_GENERATED_KEYS)) {
                p.setDate(1,java.sql.Date.valueOf(LocalDate.now().plusYears(3))); p.setInt(2,stok); p.setString(3,satuan); p.setInt(4,idObat); p.setInt(5,idKaryawan);
                p.executeUpdate(); batch = generated(p);
            }
            c.commit(); return batch;
        } catch (Exception ex) {
            rollback(c); throw new IllegalStateException("Gagal memproduksi obat: " + ex.getMessage(),ex);
        } finally { if (c != null) try { c.close(); } catch (SQLException ignored) {} }
    }

    public void catatPenggunaanBahan(int idBahan, int jumlah, String satuan, int nomorBatch) {
        ubahPenggunaan(0,idBahan,jumlah,satuan,nomorBatch);
    }

    public void ubahPenggunaanBahan(int detail, int idBahan, int jumlah, String satuan, int batch) {
        if (detail <= 0) throw new IllegalArgumentException("Pilih penggunaan bahan terlebih dahulu.");
        ubahPenggunaan(detail,idBahan,jumlah,satuan,batch);
    }

    private void ubahPenggunaan(int detail, int idBahan, int jumlah, String satuan, int batch) {
        if (jumlah <= 0) throw new IllegalArgumentException("Jumlah bahan harus lebih dari nol.");
        Connection c = null;
        try {
            c=Koneksi.getConnection(); c.setAutoCommit(false);
            try (PreparedStatement p=c.prepareStatement("SELECT nomor_batch FROM produksi WHERE nomor_batch=? FOR UPDATE")) {
                p.setInt(1,batch); try (ResultSet r=p.executeQuery()) { if (!r.next()) throw new IllegalArgumentException("Batch tidak ditemukan."); }
            }
            if (detail == 0) {
                try (PreparedStatement cek=c.prepareStatement("SELECT COUNT(*) FROM detail_penggunaan_bahan WHERE id_bahan=? AND nomor_batch=?")) {
                    cek.setInt(1,idBahan); cek.setInt(2,batch);
                    try (ResultSet rs=cek.executeQuery()) { rs.next(); if(rs.getInt(1)>0) throw new IllegalArgumentException("Bahan yang sama sudah tercatat untuk batch ini. Ubah catatan yang ada agar stok tidak terpotong dua kali."); }
                }
            }
            if (detail > 0) {
                try (PreparedStatement p=c.prepareStatement("SELECT id_bahan,nomor_batch,jumlah_bahan,satuan_bahan FROM detail_penggunaan_bahan WHERE id_detail=? FOR UPDATE")) {
                    p.setInt(1,detail);
                    try (ResultSet r=p.executeQuery()) {
                        if (!r.next()) throw new IllegalArgumentException("Penggunaan bahan tidak ditemukan.");
                        if (r.getInt(1)!=idBahan || r.getInt(2)!=batch) throw new IllegalArgumentException("Bahan dan batch pada catatan lama tidak dapat diganti. Hapus dan tambahkan catatan baru.");
                        tambahStokBahan(c,idBahan,r.getInt(3),r.getString(4));
                    }
                }
            }
            String master;
            int potong;
            try (PreparedStatement p=c.prepareStatement("SELECT stok,satuan_bahan FROM bahan_baku WHERE id_bahan=? FOR UPDATE")) {
                p.setInt(1,idBahan);
                try (ResultSet r=p.executeQuery()) {
                    if (!r.next()) throw new IllegalArgumentException("Bahan tidak ditemukan.");
                    master=r.getString(2); potong=konversi(jumlah,satuan,master);
                    if (r.getInt(1)<potong) throw new IllegalArgumentException("Stok bahan tidak cukup.");
                }
            }
            try (PreparedStatement p=c.prepareStatement("UPDATE bahan_baku SET stok=stok-? WHERE id_bahan=?")) { p.setInt(1,potong); p.setInt(2,idBahan); p.executeUpdate(); }
            String sql=detail>0 ? "UPDATE detail_penggunaan_bahan SET jumlah_bahan=?,satuan_bahan=? WHERE id_detail=?" : "INSERT INTO detail_penggunaan_bahan(jumlah_bahan,satuan_bahan,id_bahan,nomor_batch) VALUES(?,?,?,?)";
            try (PreparedStatement p=c.prepareStatement(sql)) {
                p.setInt(1,jumlah); p.setString(2,satuan);
                if(detail>0) p.setInt(3,detail); else { p.setInt(3,idBahan); p.setInt(4,batch); }
                if(p.executeUpdate()!=1) throw new SQLException("Catatan bahan gagal disimpan.");
            }
            c.commit();
        } catch(Exception ex) { rollback(c); throw new IllegalStateException("Gagal menyimpan penggunaan bahan: "+ex.getMessage(),ex); }
        finally { if(c!=null) try { c.close(); } catch(SQLException ignored) {} }
    }

    private static int konversi(int jumlah, String asal, String tujuan) {
        String a=asal==null?"":asal.trim().toLowerCase(Locale.ROOT);
        String b=tujuan==null?"":tujuan.trim().toLowerCase(Locale.ROOT);
        if(a.equals(b)) return jumlah;
        Map<String,Integer> faktor=Map.of("gram",1,"g",1,"kg",1000,"ml",1,"liter",1000,"l",1000);
        boolean massa=List.of("gram","g","kg").contains(a)&&List.of("gram","g","kg").contains(b);
        boolean cair=List.of("ml","liter","l").contains(a)&&List.of("ml","liter","l").contains(b);
        if(!massa&&!cair) throw new IllegalArgumentException("Satuan bahan tidak sesuai dengan stok master ("+tujuan+").");
        long nilai=(long)jumlah*faktor.get(a);
        if(nilai%faktor.get(b)!=0) throw new IllegalArgumentException("Jumlah tidak dapat dinyatakan sebagai bilangan bulat dalam satuan "+tujuan+".");
        return Math.toIntExact(nilai/faktor.get(b));
    }

    private static void tambahStokBahan(Connection c,int bahan,int jumlah,String satuan) throws SQLException {
        int kembali;
        try(PreparedStatement p=c.prepareStatement("SELECT satuan_bahan FROM bahan_baku WHERE id_bahan=? FOR UPDATE")) {
            p.setInt(1,bahan); try(ResultSet r=p.executeQuery()) {
                if(!r.next()) throw new IllegalArgumentException("Bahan tidak ditemukan.");
                kembali=konversi(jumlah,satuan,r.getString(1));
            }
        }
        try(PreparedStatement p=c.prepareStatement("UPDATE bahan_baku SET stok=stok+? WHERE id_bahan=?")) { p.setInt(1,kembali); p.setInt(2,bahan); p.executeUpdate(); }
    }

    private static int generated(PreparedStatement p) throws SQLException {
        try(ResultSet r=p.getGeneratedKeys()) {
            if(!r.next() || r.getInt(1)<=0) throw new SQLException("ID otomatis tidak tersedia.");
            return r.getInt(1);
        }
    }

    public void hapusPenggunaanBahan(int detail) {
        Connection c=null;
        try {
            c=Koneksi.getConnection(); c.setAutoCommit(false);
            try(PreparedStatement p=c.prepareStatement("SELECT id_bahan,jumlah_bahan,satuan_bahan FROM detail_penggunaan_bahan WHERE id_detail=? FOR UPDATE")) {
                p.setInt(1,detail);
                try(ResultSet r=p.executeQuery()) {
                    if(!r.next()) throw new IllegalArgumentException("Penggunaan bahan tidak ditemukan.");
                    tambahStokBahan(c,r.getInt(1),r.getInt(2),r.getString(3));
                }
            }
            try(PreparedStatement p=c.prepareStatement("UPDATE produksi SET id_detail=NULL WHERE id_detail=?")) { p.setInt(1,detail); p.executeUpdate(); }
            try(PreparedStatement p=c.prepareStatement("DELETE FROM detail_penggunaan_bahan WHERE id_detail=?")) { p.setInt(1,detail); p.executeUpdate(); }
            c.commit();
        } catch(Exception ex) { rollback(c); throw new IllegalStateException("Gagal menghapus penggunaan bahan: "+ex.getMessage(),ex); }
        finally { if(c!=null) try { c.close(); } catch(SQLException ignored) {} }
    }

    public void ubahProduksi(String batch, String jumlah, String satuan) {
        int stok;
        try { stok = Integer.parseInt(jumlah.trim()); }
        catch (Exception ex) { throw new IllegalArgumentException("Stok harus berupa angka bulat."); }
        if (stok != 25 && stok != 50 && stok != 100) throw new IllegalArgumentException("Stok produksi hanya boleh 25, 50, atau 100.");
        if (!List.of("Strip", "Botol").contains(satuan)) throw new IllegalArgumentException("Satuan stok harus Strip atau Botol.");
        try (Connection c = Koneksi.getConnection();
             PreparedStatement p = c.prepareStatement("UPDATE produksi SET stok=?, satuan_stok=? WHERE nomor_batch=? AND NOT EXISTS (SELECT 1 FROM detail_pesanan d WHERE d.nomor_batch=produksi.nomor_batch)")) {
            p.setInt(1, stok);
            p.setString(2, satuan);
            p.setInt(3, id(batch));
            if (p.executeUpdate() != 1) throw new IllegalArgumentException("Batch tidak ditemukan atau sudah dialokasikan ke pesanan.");
        } catch (SQLException ex) {
            throw new IllegalStateException("Gagal mengubah stok produksi: " + ex.getMessage(), ex);
        }
    }

    public void hapusProduksi(String value) {
        int batch=id(value); Connection c=null;
        try {
            c=Koneksi.getConnection(); c.setAutoCommit(false);
            try(PreparedStatement p=c.prepareStatement("SELECT nomor_batch FROM produksi WHERE nomor_batch=? FOR UPDATE")) {
                p.setInt(1,batch); try(ResultSet r=p.executeQuery()) { if(!r.next()) throw new IllegalArgumentException("Batch tidak ditemukan."); }
            }
            try(PreparedStatement p=c.prepareStatement("SELECT COUNT(*) FROM detail_pesanan WHERE nomor_batch=?")) {
                p.setInt(1,batch); try(ResultSet r=p.executeQuery()) { r.next(); if(r.getInt(1)>0) throw new IllegalArgumentException("Batch masih digunakan pada detail pesanan."); }
            }
            try(PreparedStatement p=c.prepareStatement("SELECT id_bahan,jumlah_bahan,satuan_bahan FROM detail_penggunaan_bahan WHERE nomor_batch=? ORDER BY id_bahan FOR UPDATE")) {
                p.setInt(1,batch); try(ResultSet r=p.executeQuery()) { while(r.next()) tambahStokBahan(c,r.getInt(1),r.getInt(2),r.getString(3)); }
            }
            try(PreparedStatement p=c.prepareStatement("UPDATE produksi SET id_detail=NULL WHERE id_detail IN (SELECT id_detail FROM detail_penggunaan_bahan WHERE nomor_batch=?)")) { p.setInt(1,batch); p.executeUpdate(); }
            try(PreparedStatement p=c.prepareStatement("DELETE FROM detail_penggunaan_bahan WHERE nomor_batch=?")) { p.setInt(1,batch); p.executeUpdate(); }
            try(PreparedStatement p=c.prepareStatement("DELETE FROM produksi WHERE nomor_batch=?")) { p.setInt(1,batch); p.executeUpdate(); }
            c.commit();
        } catch(Exception ex) { rollback(c); throw new IllegalStateException("Gagal menghapus batch: "+ex.getMessage(),ex); }
        finally { if(c!=null) try { c.close(); } catch(SQLException ignored) {} }
    }

    public int pesanBaru(int instansi,int karyawan,String catatan) {
        try(Connection c=Koneksi.getConnection(); PreparedStatement p=c.prepareStatement("INSERT INTO pesanan(catatan,id_instansi,id_karyawan,nomor_resi) VALUES(?,?,?,NULL)",Statement.RETURN_GENERATED_KEYS)) {
            p.setString(1,catatan==null?"":catatan.trim()); p.setInt(2,instansi); p.setInt(3,karyawan);
            p.executeUpdate(); return generated(p);
        } catch(SQLException ex) { throw new IllegalStateException("Gagal membuat pesanan: "+ex.getMessage(),ex); }
    }

    // Setiap batch dialokasikan utuh dan sekali saja. Stok di tabel produksi
    // adalah isi batch, bukan sisa alokasi: jangan nolkan stok pada pemesanan.
    public int tambahDetailPesanan(int pesanan, int batch) {
        Connection c = null;
        try {
            c = Koneksi.getConnection();
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(
                    "SELECT status_pesanan FROM pesanan WHERE id_pesanan=? FOR UPDATE")) {
                p.setInt(1, pesanan);
                try (ResultSet r = p.executeQuery()) {
                    if (!r.next()) throw new IllegalArgumentException("Pesanan tidak ditemukan.");
                    if (!"Menunggu".equals(r.getString(1)))
                        throw new IllegalArgumentException("Hanya pesanan Menunggu yang bisa memilih batch.");
                }
            }
            pastikanBelumDikirim(c, pesanan);
            int jumlah;
            // Kunci batch dulu: dua pesanan concurrent tidak bisa memakai batch yang sama.
            try (PreparedStatement p = c.prepareStatement(
                    "SELECT stok, tanggal_kedaluwarsa FROM produksi WHERE nomor_batch=? FOR UPDATE")) {
                p.setInt(1, batch);
                try (ResultSet r = p.executeQuery()) {
                    if (!r.next()) throw new IllegalArgumentException("Nomor batch tidak ditemukan.");
                    jumlah = r.getInt(1);
                    if (jumlah <= 0) throw new IllegalArgumentException("Batch ini tidak memiliki stok yang dapat dipesan.");
                    if (r.getDate(2).toLocalDate().isBefore(LocalDate.now()))
                        throw new IllegalArgumentException("Batch sudah kedaluwarsa.");
                }
            }
            // Aturan 1 pesanan = 1 batch, 1 batch = 1 pesanan.
            try (PreparedStatement p = c.prepareStatement(
                    "SELECT COUNT(*) FROM detail_pesanan WHERE id_pesanan=? OR nomor_batch=?")) {
                p.setInt(1, pesanan); p.setInt(2, batch);
                try (ResultSet r = p.executeQuery()) {
                    r.next();
                    if (r.getInt(1) > 0)
                        throw new IllegalArgumentException("Pesanan atau batch sudah memiliki alokasi. Satu pesanan hanya untuk satu batch.");
                }
            }
            int detail;
            try (PreparedStatement p = c.prepareStatement(
                    "INSERT INTO detail_pesanan(id_detail,id_pesanan,nomor_batch) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                detail = Koneksi.getNextId(c, "detail_pesanan", "id_detail");
                p.setInt(1, detail); p.setInt(2, pesanan); p.setInt(3, batch);
                p.executeUpdate();
            }
            c.commit();
            return detail;
        } catch (Exception ex) {
            rollback(c);
            if (ex instanceof IllegalArgumentException) throw (IllegalArgumentException) ex;
            throw new IllegalStateException("Gagal menyimpan detail pesanan: " + ex.getMessage(), ex);
        } finally {
            if (c != null) try { c.close(); } catch (SQLException ignored) {}
        }
    }

    // Kompatibilitas pemanggil lama: jumlah tidak boleh membagi batch.
    public int tambahDetailPesanan(int pesanan, int batch, int jumlah) {
        int stok;
        try (Connection c = Koneksi.getConnection();
             PreparedStatement p = c.prepareStatement("SELECT stok FROM produksi WHERE nomor_batch=?")) {
            p.setInt(1, batch);
            try (ResultSet r = p.executeQuery()) {
                if (!r.next()) throw new IllegalArgumentException("Batch tidak ditemukan.");
                stok = r.getInt(1);
            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Gagal membaca stok batch: " + ex.getMessage(), ex);
        }
        if (jumlah != stok) throw new IllegalArgumentException("Batch wajib dipesan utuh sebanyak " + stok + " unit.");
        return tambahDetailPesanan(pesanan, batch);
    }

    public void ubahDetailPesanan(int detail, int jumlahBaru) {
        throw new IllegalArgumentException("Jumlah tidak dapat diubah. Satu pesanan menggunakan satu batch utuh.");
    }

    public void hapusDetailPesanan(int detail) {
        Connection c = null;
        try {
            c = Koneksi.getConnection();
            c.setAutoCommit(false);
            int batch, jumlah = 1;
            String status;
            try (PreparedStatement p = c.prepareStatement("SELECT d.nomor_batch, pr.stok, p.status_pesanan FROM detail_pesanan d JOIN pesanan p ON p.id_pesanan=d.id_pesanan JOIN produksi pr ON pr.nomor_batch=d.nomor_batch WHERE d.id_detail=? FOR UPDATE")) {
                p.setInt(1, detail);
                try (ResultSet r = p.executeQuery()) {
                    if (!r.next()) throw new IllegalArgumentException("Detail pesanan tidak ditemukan.");
                    batch = r.getInt(1);
                    jumlah = r.getInt(2);
                    status = r.getString(3);
                }
            }
            try (PreparedStatement q=c.prepareStatement("SELECT id_pesanan FROM detail_pesanan WHERE id_detail=?")) {
                q.setInt(1,detail); try(ResultSet r=q.executeQuery()) { if(r.next()) pastikanBelumDikirim(c,r.getInt(1)); }
            }
            if (!"Menunggu".equals(status)) throw new IllegalArgumentException("Hanya detail pesanan berstatus 'Menunggu' yang dapat dihapus.");
            try (PreparedStatement p = c.prepareStatement("DELETE FROM detail_pesanan WHERE id_detail=?")) {
                p.setInt(1, detail);
                p.executeUpdate();
            }
            c.commit();
        } catch (Exception ex) {
            rollback(c);
            if (ex instanceof IllegalArgumentException) throw (IllegalArgumentException) ex;
            throw new IllegalStateException("Gagal menghapus detail: " + ex.getMessage(), ex);
        } finally {
            if (c != null) try { c.close(); } catch (SQLException ignored) {}
        }
    }

    private void statusDalamTransaksi(Connection c, int pesanan, String statusBaru) throws SQLException {
        if (!List.of("Menunggu", "Selesai", "Batal").contains(statusBaru)) {
            throw new IllegalArgumentException("Status pesanan tidak valid.");
        }
        String lama;
        Integer resiPesanan;
        try (PreparedStatement p = c.prepareStatement(
                "SELECT status_pesanan, nomor_resi FROM pesanan WHERE id_pesanan=? FOR UPDATE")) {
            p.setInt(1, pesanan);
            try (ResultSet r = p.executeQuery()) {
                if (!r.next()) throw new IllegalArgumentException("Pesanan tidak ditemukan.");
                lama = r.getString(1);
                resiPesanan = (Integer) r.getObject(2);
            }
        }
        if (!lama.equals(statusBaru) && !"Menunggu".equals(lama)) {
            throw new IllegalArgumentException("Pesanan yang sudah Selesai atau Batal tidak dapat diaktifkan kembali.");
        }
        // Seluruh perubahan status dicek pada database dalam transaksi yang sama.
        // Lihat resi yang benar-benar terhubung ke pesanan, bukan sekadar adanya baris pengiriman.
        if (!lama.equals(statusBaru) && ("Selesai".equals(statusBaru) || "Batal".equals(statusBaru))) {
            boolean pengirimanSelesai = false;
            try (PreparedStatement p = c.prepareStatement(
                    "SELECT nomor_resi, status_pengiriman FROM pengiriman WHERE id_pesanan=? FOR UPDATE")) {
                p.setInt(1, pesanan);
                try (ResultSet r = p.executeQuery()) {
                    while (r.next()) {
                        int resi = r.getInt(1);
                        String statusKirim = r.getString(2);
                        if ("Selesai".equals(statusBaru)
                                && resiPesanan != null && resiPesanan == resi
                                && "Selesai".equals(statusKirim)) {
                            pengirimanSelesai = true;
                        }
                        if ("Batal".equals(statusBaru)
                                && ("Diproses".equals(statusKirim) || "Selesai".equals(statusKirim))) {
                            throw new IllegalArgumentException(
                                    "Pesanan tidak dapat dibatalkan karena pengiriman sudah diproses atau selesai.");
                        }
                    }
                }
            }
            if ("Selesai".equals(statusBaru) && !pengirimanSelesai) {
                throw new IllegalArgumentException(
                        "Selesaikan pengiriman terlebih dahulu sebelum menyelesaikan pesanan.");
            }
        }
        if ("Selesai".equals(statusBaru)) {
            try (PreparedStatement p = c.prepareStatement(
                    "SELECT COUNT(*) FROM detail_pesanan WHERE id_pesanan=?")) {
                p.setInt(1, pesanan);
                try (ResultSet r = p.executeQuery()) {
                    r.next();
                    if (r.getInt(1) == 0) {
                        throw new IllegalArgumentException("Tambahkan rincian barang sebelum menyelesaikan pesanan.");
                    }
                }
            }
        }
        // Status Batal tidak menambah produksi.stok; stok adalah ukuran batch utuh.
        try (PreparedStatement p = c.prepareStatement(
                "UPDATE pesanan SET status_pesanan=? WHERE id_pesanan=?")) {
            p.setString(1, statusBaru);
            p.setInt(2, pesanan);
            p.executeUpdate();
        }
        if (!"Menunggu".equals(statusBaru)) {
            try (PreparedStatement p = c.prepareStatement(
                    "UPDATE pengiriman SET status_pengiriman=? WHERE id_pesanan=?")) {
                p.setString(1, statusBaru);
                p.setInt(2, pesanan);
                p.executeUpdate();
            }
        }
    }

    public void statusPesanan(int pesanan, String statusBaru) {
        ubahPesanan(pesanan,statusBaru,null,null);
    }

    public void ubahPesanan(int pesanan, String statusBaru, String catatan, Integer instansi) {
        Connection c=null;
        try {
            c=Koneksi.getConnection(); c.setAutoCommit(false);
            statusDalamTransaksi(c,pesanan,statusBaru);
            if(catatan!=null && instansi!=null) {
                try(PreparedStatement p=c.prepareStatement("UPDATE pesanan SET catatan=?,id_instansi=? WHERE id_pesanan=?")) {
                    p.setString(1,catatan); p.setInt(2,instansi); p.setInt(3,pesanan); p.executeUpdate();
                }
            }
            c.commit();
        } catch(Exception ex) { rollback(c); throw new IllegalStateException("Gagal mengubah pesanan: "+ex.getMessage(),ex); }
        finally { if(c!=null) try { c.close(); } catch(SQLException ignored) {} }
    }

    public void hapusPesanan(int pesanan) {
        Connection c = null;
        try {
            c = Koneksi.getConnection();
            c.setAutoCommit(false);
            String status;
            try (PreparedStatement p = c.prepareStatement("SELECT status_pesanan, nomor_resi FROM pesanan WHERE id_pesanan=? FOR UPDATE")) {
                p.setInt(1, pesanan);
                try (ResultSet r = p.executeQuery()) {
                    if (!r.next()) throw new IllegalArgumentException("Pesanan tidak ditemukan.");
                    status = r.getString(1);
                    if (r.getObject(2) != null) throw new IllegalArgumentException("Pesanan sudah mempunyai nomor resi pengiriman.");
                }
            }
            if ("Selesai".equals(status)) throw new IllegalArgumentException("Pesanan yang sudah selesai tidak boleh dihapus.");
            // Stok produksi adalah isi batch, sehingga penghapusan pesanan tidak merubahnya.
            try (PreparedStatement p = c.prepareStatement("DELETE FROM detail_pesanan WHERE id_pesanan=?")) {
                p.setInt(1, pesanan);
                p.executeUpdate();
            }
            try (PreparedStatement p = c.prepareStatement("DELETE FROM pesanan WHERE id_pesanan=?")) {
                p.setInt(1, pesanan);
                p.executeUpdate();
            }
            c.commit();
        } catch (Exception ex) {
            rollback(c);
            if (ex instanceof IllegalArgumentException) throw (IllegalArgumentException) ex;
            throw new IllegalStateException("Pesanan tidak dapat dihapus: " + ex.getMessage(), ex);
        } finally {
            if (c != null) try { c.close(); } catch (SQLException ignored) {}
        }
    }

    public int buatPengiriman(int pesanan, int karyawan, String status, String keterangan) {
        if (!List.of("Menunggu", "Diproses", "Selesai", "Batal").contains(status)) {
            throw new IllegalArgumentException("Status pengiriman tidak valid.");
        }
        Connection c = null;
        try {
            c = Koneksi.getConnection();
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement("SELECT nomor_resi, status_pesanan FROM pesanan WHERE id_pesanan=? FOR UPDATE")) {
                p.setInt(1, pesanan);
                try (ResultSet r = p.executeQuery()) {
                    if (!r.next()) throw new IllegalArgumentException("Pesanan ID " + pesanan + " tidak ditemukan.");
                    if (r.getObject(1) != null) throw new IllegalArgumentException("Pesanan ini sudah memiliki nomor resi (" + r.getInt(1) + ").");
                    if (!"Menunggu".equals(r.getString(2))) throw new IllegalArgumentException("Hanya pesanan berstatus Menunggu yang dapat dibuatkan pengiriman.");
                }
            }
            try (PreparedStatement p = c.prepareStatement("SELECT COUNT(*) FROM detail_pesanan WHERE id_pesanan=?")) {
                p.setInt(1, pesanan);
                try (ResultSet r = p.executeQuery()) {
                    r.next();
                    if (r.getInt(1) == 0) throw new IllegalArgumentException("Pesanan belum memiliki rincian barang.");
                }
            }
            int nomorResi;
            try (PreparedStatement p = c.prepareStatement("INSERT INTO pengiriman(status_pengiriman, keterangan, id_pesanan, id_karyawan) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                p.setString(1, status);
                p.setString(2, keterangan != null && !keterangan.isBlank() ? keterangan : "-");
                p.setInt(3, pesanan);
                p.setInt(4, karyawan);
                if (p.executeUpdate() != 1) throw new SQLException("Pengiriman gagal disimpan.");
                try (ResultSet keys = p.getGeneratedKeys()) {
                    if (!keys.next()) throw new SQLException("Nomor resi otomatis belum tersedia. Periksa AUTO_INCREMENT pengiriman.nomor_resi.");
                    nomorResi = keys.getInt(1);
                }
            }
            try (PreparedStatement p = c.prepareStatement("UPDATE pesanan SET nomor_resi=? WHERE id_pesanan=?")) {
                p.setInt(1, nomorResi);
                p.setInt(2, pesanan);
                if (p.executeUpdate() != 1) throw new SQLException("Pesanan gagal ditautkan ke pengiriman.");
            }
            if ("Selesai".equals(status) || "Batal".equals(status)) statusDalamTransaksi(c,pesanan,status);
            c.commit();
            return nomorResi;
        } catch (Exception ex) {
            rollback(c);
            if (ex instanceof IllegalArgumentException) throw (IllegalArgumentException) ex;
            throw new IllegalStateException("Gagal membuat pengiriman: " + ex.getMessage(), ex);
        } finally {
            if (c != null) try { c.close(); } catch (SQLException ignored) {}
        }
    }

    private void pastikanBelumDikirim(Connection c,int pesanan) throws SQLException {
        try(PreparedStatement p=c.prepareStatement("SELECT nomor_resi FROM pengiriman WHERE id_pesanan=? AND status_pengiriman<>'Batal'")) {
            p.setInt(1,pesanan); try(ResultSet r=p.executeQuery()) {
                if(r.next()) throw new IllegalArgumentException("Pesanan sudah memiliki pengiriman. Hapus pengiriman berstatus Menunggu sebelum mengubah rincian.");
            }
        }
    }

    public void ubahPengiriman(int resi,String status,String keterangan) {
        if(!List.of("Menunggu","Diproses","Selesai","Batal").contains(status)) throw new IllegalArgumentException("Status pengiriman tidak valid.");
        Connection c=null;
        try {
            c=Koneksi.getConnection(); c.setAutoCommit(false);
            int pesanan; String lama;
            try(PreparedStatement p=c.prepareStatement("SELECT id_pesanan,status_pengiriman FROM pengiriman WHERE nomor_resi=? FOR UPDATE")) {
                p.setInt(1,resi); try(ResultSet r=p.executeQuery()) {
                    if(!r.next()) throw new IllegalArgumentException("Pengiriman tidak ditemukan.");
                    pesanan=r.getInt(1); lama=r.getString(2);
                }
            }
            if (!lama.equals(status) && List.of("Selesai", "Batal").contains(lama)) {
                throw new IllegalArgumentException("Pengiriman yang sudah selesai atau batal tidak dapat diaktifkan kembali.");
            }
            if ("Selesai".equals(status) && !"Selesai".equals(lama)) {
                // Perubahan pesanan hanya boleh terjadi jika pengiriman sudah Selesai.
                // Update ini akan rollback bersama status pesanan jika salah satu langkah gagal.
                try (PreparedStatement p = c.prepareStatement(
                        "UPDATE pengiriman SET status_pengiriman=?, keterangan=? WHERE nomor_resi=?")) {
                    p.setString(1, status);
                    p.setString(2, keterangan);
                    p.setInt(3, resi);
                    p.executeUpdate();
                }
                statusDalamTransaksi(c, pesanan, status);
            } else if ("Batal".equals(status) && !"Batal".equals(lama)) {
                // Cek status lama dulu: Diproses / Selesai tidak boleh dibatalkan.
                statusDalamTransaksi(c, pesanan, status);
                try (PreparedStatement p = c.prepareStatement(
                        "UPDATE pengiriman SET status_pengiriman=?, keterangan=? WHERE nomor_resi=?")) {
                    p.setString(1, status);
                    p.setString(2, keterangan);
                    p.setInt(3, resi);
                    p.executeUpdate();
                }
            } else {
                // Perubahan keterangan pada pengiriman Selesai/Batal tetap diizinkan,
                // tetapi perpindahan status Menunggu/Diproses mensyaratkan pesanan aktif.
                if (!"Batal".equals(status) && !"Selesai".equals(status)) {
                    try (PreparedStatement p = c.prepareStatement(
                            "SELECT status_pesanan FROM pesanan WHERE id_pesanan=? FOR UPDATE")) {
                        p.setInt(1, pesanan);
                        try (ResultSet r = p.executeQuery()) {
                            if (!r.next() || !"Menunggu".equals(r.getString(1))) {
                                throw new IllegalArgumentException("Pesanan sudah selesai atau batal.");
                            }
                        }
                    }
                }
                try (PreparedStatement p = c.prepareStatement(
                        "UPDATE pengiriman SET status_pengiriman=?, keterangan=? WHERE nomor_resi=?")) {
                    p.setString(1, status);
                    p.setString(2, keterangan);
                    p.setInt(3, resi);
                    p.executeUpdate();
                }
            }
            c.commit();
        } catch(Exception ex) { rollback(c); throw new IllegalStateException("Gagal mengubah pengiriman: "+ex.getMessage(),ex); }
        finally { if(c!=null) try { c.close(); } catch(SQLException ignored) {} }
    }

    public void hapusPengiriman(int resi) {
        Connection c = null;
        try {
            c = Koneksi.getConnection();
            c.setAutoCommit(false);
            try(PreparedStatement p=c.prepareStatement("SELECT status_pengiriman FROM pengiriman WHERE nomor_resi=? FOR UPDATE")) {
                p.setInt(1,resi); try(ResultSet r=p.executeQuery()) {
                    if(!r.next()) throw new IllegalArgumentException("Pengiriman tidak ditemukan.");
                    if("Diproses".equals(r.getString(1)) || "Selesai".equals(r.getString(1))) throw new IllegalArgumentException("Pengiriman yang sedang diproses atau selesai tidak boleh dihapus.");
                }
            }
            try (PreparedStatement p = c.prepareStatement("UPDATE pesanan SET nomor_resi=NULL WHERE nomor_resi=?")) {
                p.setInt(1, resi);
                p.executeUpdate();
            }
            try (PreparedStatement p = c.prepareStatement("DELETE FROM pengiriman WHERE nomor_resi=?")) {
                p.setInt(1, resi);
                if (p.executeUpdate() != 1) throw new IllegalArgumentException("Nomor resi tidak ditemukan.");
            }
            c.commit();
        } catch (Exception ex) {
            rollback(c);
            if (ex instanceof IllegalArgumentException) throw (IllegalArgumentException) ex;
            throw new IllegalStateException("Gagal menghapus pengiriman: " + ex.getMessage(), ex);
        } finally {
            if (c != null) try { c.close(); } catch (SQLException ignored) {}
        }
    }
}
