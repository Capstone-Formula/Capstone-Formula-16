package app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDAO {

    public static class AuthResult {
        public final int id;
        public final String role;
        public final String nama;

        public AuthResult(int id, String role, String nama) {
            this.id = id;
            this.role = role;
            this.nama = nama;
        }
    }

    public String login(int idKaryawan, String password) {
        AuthResult res = authenticate(String.valueOf(idKaryawan), password);
        return res != null ? res.role : null;
    }

    public AuthResult authenticate(String idKaryawan, String password) {
        if (idKaryawan == null || idKaryawan.trim().isEmpty() || password == null) return null;
        idKaryawan = idKaryawan.trim();
        if (!idKaryawan.matches("\\d{1,9}")) return null;

        String query = "SELECT k.id_karyawan, k.nama, po.divisi, pf.area_logistik FROM karyawan k " +
                       "LEFT JOIN petugas_operasional po ON k.id_karyawan = po.id_karyawan " +
                       "LEFT JOIN petugas_fulfillment pf ON k.id_karyawan = pf.id_karyawan " +
                       "WHERE k.id_karyawan = ? " +
                       "  AND k.password = ? AND k.status = 'Aktif'";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, Integer.parseInt(idKaryawan));
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_karyawan");
                    String nama = rs.getString("nama");
                    String role = null;
                    if (rs.getString("divisi") != null) role = rs.getString("divisi");
                    else if (rs.getString("area_logistik") != null) role = "Fulfillment";

                    if (role != null) {
                        return new AuthResult(id, role, nama);
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Koneksi atau query login gagal: " + e.getMessage(), e);
        }
        return null;
    }
}
