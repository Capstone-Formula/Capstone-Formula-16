package app.model.role;
public final class AdministrasiRole extends MenuRole {
    public AdministrasiRole() { super("Administrasi"); }
    @Override public String[] getMenu() { return new String[]{"Kelola Obat", "Kelola Bahan Baku", "Kelola Karyawan", "Data Pesanan", "Data Pengiriman", "Instansi Kesehatan", "Stok per Batch", "Detail Pesanan"}; }
}
