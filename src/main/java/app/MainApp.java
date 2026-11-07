package app;

import app.controller.LoginController;
import app.model.AuthDAO;
import app.view.FormLogin;
import javax.swing.UIManager;

public class MainApp {
    public static void main(String[] args) {
        
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        System.setProperty("sun.java2d.uiScale.enabled", "true");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try { app.model.JpaManager.close(); } catch (Throwable ignored) {}
            try { app.model.Koneksi.close(); } catch (Throwable ignored) {}
        }, "formula-shutdown"));

        try {
            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ignored) {}
        }

        java.awt.EventQueue.invokeLater(() -> {
            FormLogin l = new FormLogin();
            new LoginController(l, new AuthDAO());
            l.setVisible(true);
        });
    }
}
