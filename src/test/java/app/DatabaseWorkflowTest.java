package app;

import app.model.*;
import java.sql.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfEnvironmentVariable(named="FORMULA_INTEGRATION_TESTS",matches="true")
public class DatabaseWorkflowTest {
    final MasterDAO master=new MasterDAO();
    final AlurDAO alur=new AlurDAO();
    final KaryawanDAO karyawan=new KaryawanDAO();
    int admin, produksi, fulfillment, obat, bahan, instansi;
    String tag;

    @BeforeEach void fixture() throws Exception {
        try(Connection c=Koneksi.getConnection()) { assertTrue(c.getCatalog().startsWith("formula_test_"),"Gunakan database pengujian terpisah."); }
        tag="Uji "+UUID.randomUUID().toString().substring(0,8);
        admin=karyawan.tambah(tag+" Admin","uji123","081234567890","Perempuan","Alamat uji","2000-01-01","Siang","Administrasi","");
        produksi=karyawan.tambah(tag+" Prod","uji123","081234567890","Laki-laki","Alamat uji","2000-01-01","Siang","Produksi","Pabrik");
        fulfillment=karyawan.tambah(tag+" Full","uji123","081234567890","Perempuan","Alamat uji","2000-01-01","Malam","Fulfillment","Gudang");
        assertTrue(master.simpanObat(tag,"Obat Bebas","Tersedia",admin));
        obat=idByName(master.getAllObat(),tag);
        assertTrue(master.simpanBahan(tag,"10000","gram",admin));
        bahan=idByName(master.getAllBahan(),tag);
        assertTrue(master.simpanInstansi(tag,"Apotek","Alamat uji","08123456"));
        instansi=idByName(master.getAllInstansi(),tag);
    }
    static int idByName(List<Object[]> data,String name) {
        return ((Number)data.stream().filter(r->name.equals(r[1])).findFirst().orElseThrow()[0]).intValue();
    }
    static int angka(String table,String col,String key,int id) throws Exception {
        try(Connection c=Koneksi.getConnection();PreparedStatement p=c.prepareStatement("SELECT "+col+" FROM "+table+" WHERE "+key+"=?")) {
            p.setInt(1,id); try(ResultSet r=p.executeQuery()) { assertTrue(r.next()); return r.getInt(1); }
        }
    }
    static String teks(String table,String col,String key,int id) throws Exception {
        try(Connection c=Koneksi.getConnection();PreparedStatement p=c.prepareStatement("SELECT "+col+" FROM "+table+" WHERE "+key+"=?")) {
            p.setInt(1,id);try(ResultSet r=p.executeQuery()){assertTrue(r.next());return r.getString(1);}
        }
    }
    int batch() { return alur.produksiSederhana(obat,100,produksi); }
    int pesanan() { return alur.pesanBaru(instansi,fulfillment,"Pesanan uji"); }

    @Test void loginRolesAndInactiveAccount() {
        AuthDAO auth=new AuthDAO();
        assertEquals("Administrasi",auth.authenticate(""+admin,"uji123").role);
        assertEquals("Produksi",auth.authenticate(""+produksi,"uji123").role);
        assertEquals("Fulfillment",auth.authenticate(""+fulfillment,"uji123").role);
        assertNull(auth.authenticate(""+admin,"salah"));
        assertNull(auth.authenticate("1 OR 1=1","uji123"));
        karyawan.updateStatus(admin,"Nonaktif");assertNull(auth.authenticate(""+admin,"uji123"));
        karyawan.updateStatus(admin,"Aktif");assertNotNull(auth.authenticate(""+admin,"uji123"));
        karyawan.hapus(fulfillment);
        assertNull(auth.authenticate(""+fulfillment,"uji123"));
    }
    @Test void ormCrudAndForeignKeys() {
        assertTrue(master.updateObat(""+obat,tag+" Baru","Obat Keras","Tidak Tersedia"));
        assertEquals(obat,idByName(master.getAllObat(),tag+" Baru"));
        assertTrue(master.updateStatusObat(obat,"Tersedia"));
        assertTrue(master.updateInstansi(""+instansi,tag+" Baru","Klinik","Jalan baru","08112233"));
        assertEquals(instansi,idByName(master.getAllInstansi(),tag+" Baru"));
        assertTrue(master.hapusObat(""+obat)); assertTrue(master.hapusInstansi(""+instansi));
        assertTrue(master.hapusBahan(""+bahan));
        assertThrows(RuntimeException.class,()->master.simpanObat(tag,"Obat Bebas","Tersedia",-1));
    }
    @Test void productionAndMaterialCrudRollback() throws Exception {
        int batch=batch();
        alur.ubahProduksi(""+batch,"50","Botol");
        assertEquals(50,angka("produksi","stok","nomor_batch",batch));
        alur.catatPenggunaanBahan(bahan,1,"Kg",batch);
        assertEquals(9000,angka("bahan_baku","stok","id_bahan",bahan));
        int detail=((Number)alur.penggunaan().get(0)[0]).intValue();
        alur.ubahPenggunaanBahan(detail,bahan,2000,"gram",batch);
        assertEquals(8000,angka("bahan_baku","stok","id_bahan",bahan));
        assertThrows(RuntimeException.class,()->alur.ubahPenggunaanBahan(detail,bahan,20000,"gram",batch));
        assertEquals(8000,angka("bahan_baku","stok","id_bahan",bahan));
        assertThrows(RuntimeException.class,()->alur.catatPenggunaanBahan(bahan,1,"ml",batch));
        assertEquals(8000,angka("bahan_baku","stok","id_bahan",bahan));
        alur.hapusPenggunaanBahan(detail);
        assertEquals(10000,angka("bahan_baku","stok","id_bahan",bahan));
        alur.catatPenggunaanBahan(bahan,500,"gram",batch);
        alur.hapusProduksi(""+batch);
        assertEquals(10000,angka("bahan_baku","stok","id_bahan",bahan));
    }
    @Test void orderDetailsAndCancellation() throws Exception {
        int batch=batch(), pesanan=pesanan();
        assertThrows(RuntimeException.class, () -> alur.tambahDetailPesanan(pesanan,batch,10));
        int detail=alur.tambahDetailPesanan(pesanan,batch);
        assertEquals(0,angka("produksi","stok","nomor_batch",batch));
        assertThrows(RuntimeException.class, () -> alur.ubahDetailPesanan(detail,20));
        assertThrows(RuntimeException.class, () -> alur.tambahDetailPesanan(pesanan,batch));
        int pesananLain=pesanan();
        assertThrows(RuntimeException.class, () -> alur.tambahDetailPesanan(pesananLain,batch));
        assertThrows(RuntimeException.class, () -> alur.hapusProduksi(""+batch));
        alur.hapusDetailPesanan(detail);
        assertEquals(100,angka("produksi","stok","nomor_batch",batch));
        alur.tambahDetailPesanan(pesanan,batch);
        alur.statusPesanan(pesanan,"Batal"); alur.statusPesanan(pesanan,"Batal");
        assertEquals(100,angka("produksi","stok","nomor_batch",batch));
        assertThrows(RuntimeException.class,()->alur.statusPesanan(pesanan,"Menunggu"));
        alur.hapusPesanan(pesanan);
        assertEquals(100,angka("produksi","stok","nomor_batch",batch));
    }
    @Test void oneOrderOnlyOneBatch() throws Exception {
        int batchA=batch(), batchB=batch(), id=pesanan();
        alur.tambahDetailPesanan(id,batchA);
        assertThrows(RuntimeException.class,()->alur.tambahDetailPesanan(id,batchB));
        assertEquals(100,angka("produksi","stok","nomor_batch",batchB));
        assertEquals(0,angka("produksi","stok","nomor_batch",batchA));
    }
    @Test void shippingCrudAndOrderSync() throws Exception {
        int batch=batch(),pesanan=pesanan();
        assertThrows(RuntimeException.class,()->alur.buatPengiriman(pesanan,fulfillment,"Menunggu",""));
        alur.tambahDetailPesanan(pesanan,batch);
        int resi=alur.buatPengiriman(pesanan,fulfillment,"Menunggu","uji");
        assertEquals(resi,angka("pesanan","nomor_resi","id_pesanan",pesanan));
        assertThrows(RuntimeException.class,()->alur.buatPengiriman(pesanan,fulfillment,"Menunggu","duplikat"));
        assertThrows(RuntimeException.class,()->alur.tambahDetailPesanan(pesanan,batch,1));
        alur.hapusPengiriman(resi);assertNull(teks("pesanan","nomor_resi","id_pesanan",pesanan));
        resi=alur.buatPengiriman(pesanan,fulfillment,"Diproses","uji");
        alur.ubahPengiriman(resi,"Selesai","Terkirim");
        assertEquals("Selesai",teks("pesanan","status_pesanan","id_pesanan",pesanan));
        assertEquals(0,angka("produksi","stok","nomor_batch",batch));
        final int idResi=resi;
        assertThrows(RuntimeException.class,()->alur.ubahPengiriman(idResi,"Menunggu",""));
        assertThrows(RuntimeException.class,()->alur.hapusPengiriman(idResi));
        assertThrows(RuntimeException.class,()->master.hapusInstansi(""+instansi));
    }
    @Test void cancelShippingRestoresStockOnce() throws Exception {
        int batch=batch(),pesanan=pesanan();
        alur.tambahDetailPesanan(pesanan,batch);
        int resi=alur.buatPengiriman(pesanan,fulfillment,"Menunggu","");
        alur.ubahPengiriman(resi,"Batal","");alur.ubahPengiriman(resi,"Batal","Dibatalkan");
        assertEquals("Batal",teks("pesanan","status_pesanan","id_pesanan",pesanan));
        assertEquals(100,angka("produksi","stok","nomor_batch",batch));
        alur.hapusPengiriman(resi);alur.hapusPesanan(pesanan);
        assertEquals(100,angka("produksi","stok","nomor_batch",batch));
    }
    @Test void tidakBisaSelesaiTanpaPengiriman() throws Exception {
        int batch = batch(), pesanan = pesanan();
        alur.tambahDetailPesanan(pesanan, batch);
        assertThrows(RuntimeException.class, () -> alur.statusPesanan(pesanan, "Selesai"));
        assertEquals("Menunggu", teks("pesanan", "status_pesanan", "id_pesanan", pesanan));
        int resi = alur.buatPengiriman(pesanan, fulfillment, "Menunggu", "uji");
        assertThrows(RuntimeException.class, () -> alur.statusPesanan(pesanan, "Selesai"));
        alur.ubahPengiriman(resi, "Selesai", "Terkirim");
        assertEquals("Selesai", teks("pesanan", "status_pesanan", "id_pesanan", pesanan));
    }

    @Test void pengirimanDiprosesTidakBisaBatalkanPesanan() throws Exception {
        int batch = batch(), pesanan = pesanan();
        alur.tambahDetailPesanan(pesanan, batch);
        int resi = alur.buatPengiriman(pesanan, fulfillment, "Diproses", "uji");
        assertThrows(RuntimeException.class, () -> alur.statusPesanan(pesanan, "Batal"));
        assertThrows(RuntimeException.class, () -> alur.ubahPengiriman(resi, "Batal", "uji"));
        assertEquals(0, angka("produksi", "stok", "nomor_batch", batch));
        assertEquals("Menunggu", teks("pesanan", "status_pesanan", "id_pesanan", pesanan));
        assertEquals("Diproses", teks("pengiriman", "status_pengiriman", "nomor_resi", resi));
    }

    @Test void jalurLamaKaryawanPakaiValidasiSama() throws Exception {
        assertThrows(RuntimeException.class,
                () -> master.simpanKaryawan(tag + " Lama", "08123", "Aktif", "Siang"));
        assertThrows(RuntimeException.class,
                () -> master.simpanKaryawan(tag + " Admin", "081234567890", "Aktif", "Siang"));
        assertTrue(master.simpanKaryawan(tag + " Baru", "081234567891", "Aktif", "Siang"));
        assertThrows(RuntimeException.class,
                () -> master.updateKaryawan("" + admin, tag + " Baru", "081234567892", "Aktif", "Siang"));
    }

    @Test void batchOptionsMengikutiStokTerbaru() throws Exception {
        int batch = batch(), pesanan = pesanan();
        String prefix = batch + " - ";
        assertTrue(master.getBatchOptionsPesanan().stream().anyMatch(s -> s.startsWith(prefix) && s.contains("Stok: 100")));
        alur.tambahDetailPesanan(pesanan, batch);
        assertTrue(master.getBatchOptionsPesanan().stream().noneMatch(s -> s.startsWith(prefix)));
        alur.statusPesanan(pesanan, "Batal");
        assertTrue(master.getBatchOptionsPesanan().stream().noneMatch(s -> s.startsWith(prefix)));
    }

    @AfterAll static void close() { JpaManager.close();Koneksi.close(); }
}
