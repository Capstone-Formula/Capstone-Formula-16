package app.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;
import javax.sql.DataSource;

public final class Koneksi {
    private static HikariDataSource pool;
    private Koneksi() {}
    public static synchronized DataSource getDataSource() {
        if (pool == null || pool.isClosed()) {
            Properties p = new Properties();
            Path file = Path.of(System.getProperty("formula.config", "formula.properties"));
            if (Files.exists(file)) {
                try (InputStream in = Files.newInputStream(file)) { p.load(in); }
                catch (Exception ex) { throw new IllegalStateException("Gagal membaca formula.properties.", ex); }
            }
            HikariConfig cfg = new HikariConfig();
            cfg.setPoolName("Formula-MySQL-Pool");
            cfg.setJdbcUrl(setting("FORMULA_DB_URL", p.getProperty("db.url", "jdbc:mysql://localhost:3306/formula?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Makassar&connectTimeout=5000&socketTimeout=15000")));
            cfg.setUsername(setting("FORMULA_DB_USER", p.getProperty("db.user", "root")));
            cfg.setPassword(setting("FORMULA_DB_PASSWORD", p.getProperty("db.password", "12")));
            cfg.setMaximumPoolSize(10);
            cfg.setMinimumIdle(1);
            cfg.setConnectionTimeout(10000);
            cfg.setValidationTimeout(3000);
            cfg.setInitializationFailTimeout(-1);
            HikariDataSource candidate = new HikariDataSource(cfg);
            try (Connection c = candidate.getConnection()) {
                pool = candidate;
            } catch (Exception ex) {
                candidate.close();
                throw new IllegalStateException("Koneksi atau skema database gagal. Periksa MySQL, formula.properties dan impor SQL setup: " + ex.getMessage(), ex);
            }
        }
        return pool;
    }
    public static int getNextId(Connection c, String table, String col) throws SQLException {
        if (!table.matches("[a-z_]+") || !col.matches("[a-z_]+")) throw new IllegalArgumentException("Nama kolom tidak valid.");
        try (Statement st = c.createStatement(); ResultSet r = st.executeQuery("SELECT COALESCE(MAX(" + col + "),0)+1 FROM " + table)) {
            if (r.next()) return r.getInt(1);
            throw new SQLException("ID tidak tersedia.");
        }
    }
    private static String setting(String name, String fallback) {
        String value = System.getenv(name);
        return value == null ? fallback : value;
    }
    public static Connection getConnection() throws SQLException { return getDataSource().getConnection(); }
    public static synchronized void close() {
        if (pool != null && !pool.isClosed()) pool.close();
        pool = null;
    }
}
