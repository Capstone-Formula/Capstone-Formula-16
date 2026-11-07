package app.model.role;
public final class ProduksiRole extends MenuRole {
    public ProduksiRole() { super("Produksi"); }
    @Override public String[] getMenu() { return new String[]{"Proses Produksi", "Data Produksi", "Penggunaan Bahan"}; }
}
