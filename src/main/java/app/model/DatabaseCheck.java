package app.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public final class DatabaseCheck {
    private DatabaseCheck() {}

    public static void main(String[] args) {
        System.out.println("=== FORMULA - DIAGNOSIS MYSQL & SKEMA DATABASE ===");
        try (Connection c = Koneksi.getConnection()) {
            System.out.println("[OK] Koneksi Berhasil. Database aktif: " + c.getCatalog());
            System.out.println("[OK] Driver: " + c.getMetaData().getDriverName());

            String[] tabels = new String[]{
                "karyawan", "petugas_operasional", "petugas_fulfillment",
                "obat", "bahan_baku", "instansi_kesehatan",
                "produksi", "detail_penggunaan_bahan",
                "pesanan", "detail_pesanan", "pengiriman"
            };

            boolean lengkap = true;
            for (String tabel : tabels) {
                try (PreparedStatement ps = c.prepareStatement("SELECT count(*) FROM " + tabel);
                     ResultSet r = ps.executeQuery()) {
                    r.next();
                    System.out.printf("  [OK] %-25s : %d baris%n", tabel, r.getInt(1));
                } catch (Exception e) {
                    lengkap = false;
                    System.out.printf("  [INFO] %-23s : %s%n", tabel, e.getMessage());
                }
            }

            DatabaseMetaData md = c.getMetaData();
            for (String[] required : new String[][]{{"detail_pesanan", "id_detail"}, {"detail_pesanan", "nomor_batch"}, {"produksi", "stok"}}) {
                try (ResultSet rs = md.getColumns(c.getCatalog(), null, required[0], required[1])) {
                    if (rs.next()) {
                        System.out.println("  [OK] Kolom " + required[0] + "." + required[1] + " tersedia.");
                    } else {
                        lengkap = false;
                        System.out.println("  [PERINGATAN] Kolom " + required[0] + "." + required[1] + " belum ada.");
                    }
                }
            }
            System.out.println(lengkap ? "\nKoneksi dan tabel tersedia. Uji CRUD dijalankan terpisah." : "\nSkema belum lengkap. Periksa SQL setup atau migrasi.");
        } catch (Exception e) {
            System.err.println("\n[KONEKSI GAGAL] " + e.getMessage());
            System.err.println("Pastikan MySQL / XAMPP sedang berjalan pada port 3306.");
        } finally {
            Koneksi.close();
        }
    }
}
