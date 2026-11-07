package app.model.role;

public abstract class MenuRole {
    private final String nama; 
    protected MenuRole(String nama) { this.nama = nama; }
    public final String getNama() { return nama; }
    public abstract String[] getMenu();
    public static MenuRole untuk(String role) {
        if ("Administrasi".equals(role)) return new AdministrasiRole();
        if ("Produksi".equals(role)) return new ProduksiRole();
        if ("Fulfillment".equals(role)) return new FulfillmentRole();
        throw new IllegalArgumentException("Role tidak dikenal: " + role);
    }
}
