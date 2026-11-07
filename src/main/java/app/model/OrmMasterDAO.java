package app.model;

import app.model.entity.InstansiKesehatan;
import app.model.entity.Obat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class OrmMasterDAO {
    private <T> T transaksi(Function<EntityManager, T> operation) {
        try (EntityManager em = JpaManager.open()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            try {
                T result = operation.apply(em);
                tx.commit();
                return result;
            } catch (RuntimeException ex) {
                if (tx.isActive()) tx.rollback();
                throw ex;
            }
        }
    }
    public static int getValidKaryawanId(Connection conn, int idLogin) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("SELECT id_karyawan FROM karyawan WHERE id_karyawan=? AND status='Aktif'")) {
            ps.setInt(1, idLogin);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new IllegalArgumentException("Karyawan tidak ditemukan atau nonaktif. Silakan login kembali.");
    }
    private static String wajib(String value, String label, int max) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(label + " wajib diisi.");
        value = value.trim();
        if (value.length() > max) throw new IllegalArgumentException(label + " maksimal " + max + " karakter.");
        return value;
    }
    public List<Object[]> semuaInstansi() {
        return transaksi(em -> em.createQuery("select i.id, i.nama, i.jenis, i.alamat, i.kontak from InstansiKesehatan i order by i.id", Object[].class).getResultList());
    }
    public boolean tambahInstansi(String n, String j, String a, String k) {
        InstansiKesehatan item = new InstansiKesehatan(wajib(n,"Nama instansi",50), wajib(j,"Jenis instansi",30), wajib(a,"Alamat",255), wajib(k,"Kontak",20));
        return transaksi(em -> {
            Long sama = em.createQuery("select count(i) from InstansiKesehatan i where lower(trim(i.nama))=lower(trim(:nama))", Long.class).setParameter("nama", item.getNama()).getSingleResult();
            if (sama > 0) throw new IllegalArgumentException("Nama instansi sudah terdaftar. Gunakan nama yang berbeda.");
            em.persist(item); return true;
        });
    }
    public boolean ubahInstansi(String id, String n, String j, String a, String k) {
        return transaksi(em -> {
            InstansiKesehatan item = em.find(InstansiKesehatan.class, Integer.valueOf(id));
            if (item == null) return false;
            String namaBaru = wajib(n,"Nama instansi",50);
            Long sama = em.createQuery("select count(i) from InstansiKesehatan i where lower(trim(i.nama))=lower(trim(:nama)) and i.id<>:id", Long.class).setParameter("nama", namaBaru).setParameter("id", item.getId()).getSingleResult();
            if (sama > 0) throw new IllegalArgumentException("Nama instansi sudah digunakan oleh data lain.");
            item.setNama(namaBaru);
            item.setJenis(wajib(j,"Jenis instansi",30));
            item.setAlamat(wajib(a,"Alamat",255));
            item.setKontak(wajib(k,"Kontak",20));
            return true;
        });
    }
    public boolean hapusInstansi(String id) {
        return transaksi(em -> {
            InstansiKesehatan item = em.find(InstansiKesehatan.class, Integer.valueOf(id));
            if (item == null) return false;
            em.remove(item); return true;
        });
    }
    public List<Object[]> semuaObat() {
        return transaksi(em -> em.createQuery("select o.id, o.nama, o.jenis, case when lower(o.status) in ('tersedia','available','aktif') then 'Tersedia' else 'Tidak Tersedia' end from Obat o order by o.id", Object[].class).getResultList());
    }
    public boolean tambahObat(String n, String j, String s, int idLogin) {
        try (Connection c = Koneksi.getConnection()) { getValidKaryawanId(c, idLogin); }
        catch (SQLException ex) { throw new IllegalStateException("Gagal memeriksa karyawan.", ex); }
        Obat item = new Obat(wajib(n,"Nama obat",50), wajib(j,"Jenis obat",50), wajib(s,"Status obat",30), idLogin);
        return transaksi(em -> {
            Long sama = em.createQuery("select count(o) from Obat o where lower(trim(o.nama))=lower(trim(:nama))", Long.class)
                .setParameter("nama", item.getNama()).getSingleResult();
            if (sama > 0) throw new IllegalArgumentException("Nama obat sudah terdaftar.");
            em.persist(item); return true;
        });
    }
    public boolean tambahObat(String n, String j, String s) { return tambahObat(n,j,s,-1); }
    public boolean ubahObat(String id, String n, String j, String s) {
        return transaksi(em -> {
            Obat item = em.find(Obat.class, Integer.valueOf(id));
            if (item == null) return false;
            String namaBaru = wajib(n,"Nama obat",50);
            Long sama = em.createQuery("select count(o) from Obat o where lower(trim(o.nama))=lower(trim(:nama)) and o.id<>:id", Long.class)
                .setParameter("nama", namaBaru).setParameter("id", item.getId()).getSingleResult();
            if (sama > 0) throw new IllegalArgumentException("Nama obat sudah digunakan.");
            item.setNama(namaBaru); item.setJenis(wajib(j,"Jenis obat",50)); item.setStatus(wajib(s,"Status obat",30));
            return true;
        });
    }
    public boolean ubahStatusObat(int id, String s) {
        if (!List.of("Tersedia", "Tidak Tersedia").contains(s)) throw new IllegalArgumentException("Status obat tidak valid.");
        return transaksi(em -> {
            Obat item = em.find(Obat.class, id);
            if (item == null) return false;
            item.setStatus(s); return true;
        });
    }
    public boolean hapusObat(String id) {
        return transaksi(em -> {
            Obat item = em.find(Obat.class, Integer.valueOf(id));
            if (item == null) return false;
            em.remove(item); return true;
        });
    }
}
