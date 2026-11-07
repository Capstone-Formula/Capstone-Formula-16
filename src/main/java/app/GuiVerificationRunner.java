package app;

import app.model.role.MenuRole;
import app.view.*;
import java.awt.GraphicsEnvironment;
import java.util.List;

public class GuiVerificationRunner {
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeAndWait(GuiVerificationRunner::verify);
    }
    private static void verify() {
        System.out.println("=== MEMULAI VERIFIKASI GUI BUILDER FORMULA ===");

        System.out.print("[1/7] Menguji Menu Roles... ");
        String[] adminMenu = MenuRole.untuk("Administrasi").getMenu();
        String[] prodMenu = MenuRole.untuk("Produksi").getMenu();
        String[] fulMenu = MenuRole.untuk("Fulfillment").getMenu();
        if (adminMenu.length == 0 || prodMenu.length == 0 || fulMenu.length == 0) {
            throw new AssertionError("Menu role tidak boleh kosong");
        }
        System.out.println("OK (Admin: " + adminMenu.length + ", Prod: " + prodMenu.length + ", Ful: " + fulMenu.length + " menu)");

        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Lingkungan headless terdeteksi, uji inisialisasi grafis dilewati.");
            return;
        }

        System.out.print("[2/7] Menguji FormLogin... ");
        FormLogin formLogin = new FormLogin();
        if (formLogin.getBtnLogin() == null) throw new AssertionError("btnLogin null");
        formLogin.dispose();
        System.out.println("OK");

        System.out.print("[3/7] Menguji FormDashboard... ");
        for (String role : new String[]{"Administrasi", "Produksi", "Fulfillment"}) {
            FormDashboard fd = new FormDashboard(11, role);
            if (fd.getBtnLogout() == null) throw new AssertionError("btnLogout null");
            fd.dispose();
        }
        System.out.println("OK (3 peran berhasil)");

        System.out.print("[4/7] Menguji FormMaster... ");
        String[][] modules = new String[][]{
            {"Menu Obat", "ID Obat", "Nama Obat", "Jenis Obat", "Status Ketersediaan"},
            {"Menu Bahan Baku", "ID Bahan", "Nama Bahan", "Stok", "Satuan"},
            {"Kelola Pesanan", "ID Pesanan", "Tanggal", "Status", "Catatan", "ID Instansi", "ID Karyawan"},
            {"Menu Pengiriman", "No Resi", "Tanggal", "Status", "Keterangan", "ID Pesanan", "ID Karyawan"},
            {"Instansi Kesehatan", "ID Instansi", "Nama Instansi", "Jenis", "Alamat", "Kontak"}
        };
        for (String[] mod : modules) {
            String title = mod[0];
            String[] cols = new String[mod.length - 1];
            System.arraycopy(mod, 1, cols, 0, cols.length);
            FormMaster fm = new FormMaster(title, cols);
            if (fm.getModel() == null || fm.getBtnSimpan() == null) throw new AssertionError("FormMaster null");
            fm.dispose();
        }
        System.out.println("OK (" + modules.length + " variasi master)");

        System.out.print("[5/7] Menguji FormKaryawan... ");
        FormKaryawan fk = new FormKaryawan();
        if (fk.getModel() == null || fk.getBtnTambah() == null) throw new AssertionError("FormKaryawan null");
        fk.dispose();
        System.out.println("OK");

        System.out.print("[6/7] Menguji FormProduksi... ");
        FormProduksi fp = new FormProduksi(List.of("1 - Paracetamol 500 mg", "2 - Amoxicillin 500 mg"), List.of());
        if (fp.getBtnProses() == null) throw new AssertionError("FormProduksi null");
        fp.dispose();
        System.out.println("OK");

        System.out.print("[7/7] Menguji FormUpdateStatus... ");
        FormUpdateStatus fus = new FormUpdateStatus();
        if (fus.getBtnUpdateStatus() == null || fus.getTblObat() == null) throw new AssertionError("FormUpdateStatus null");
        fus.dispose();
        System.out.println("OK");

        System.out.println("\n=== PEMBUKAAN FORM BERHASIL. CRUD diuji terpisah dengan database. ===");
    }
}
