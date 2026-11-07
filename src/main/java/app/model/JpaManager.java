package app.model;

import app.model.entity.InstansiKesehatan;
import app.model.entity.Obat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public final class JpaManager {
    private static EntityManagerFactory factory;
    private JpaManager() {}
    public static synchronized EntityManager open() {
        if (factory == null || !factory.isOpen()) {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
            cfg.addAnnotatedClass(InstansiKesehatan.class);
            cfg.addAnnotatedClass(Obat.class);
            cfg.setProperty("hibernate.hbm2ddl.auto", "none");
            cfg.setProperty("hibernate.show_sql", "false");
            cfg.setProperty("hibernate.jdbc.time_zone", "Asia/Makassar");
            cfg.getProperties().put("hibernate.connection.datasource", Koneksi.getDataSource());
            factory = cfg.buildSessionFactory();
        }
        return factory.createEntityManager();
    }
    public static synchronized void close() {
        if (factory != null && factory.isOpen()) factory.close();
        factory = null;
    }
}
