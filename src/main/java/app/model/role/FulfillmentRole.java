package app.model.role;
public final class FulfillmentRole extends MenuRole {
    public FulfillmentRole() { super("Fulfillment"); }
    @Override public String[] getMenu() { return new String[]{"Kelola Pesanan", "Detail Pesanan", "Kelola Pengiriman", "Instansi Kesehatan", "Stok per Batch", "Ubah Status Obat"}; }
}
