package app.controller;

import app.model.AuthDAO;
import app.model.AuthDAO.AuthResult;
import app.view.FormDashboard;
import app.view.FormLogin;
import javax.swing.SwingWorker;

public class LoginController {
    public LoginController(FormLogin view, AuthDAO dao) {
        view.getBtnLogin().addActionListener(e -> {
            final String idOrUser = view.getIdKaryawan();
            if (idOrUser.isEmpty()) {
                view.msg("Masukkan ID Karyawan.");
                return;
            }
            final String password = view.getPassword();
            if (password.isEmpty()) {
                view.msg("Masukkan password.");
                return;
            }
            view.getBtnLogin().setEnabled(false);
            view.getBtnLogin().setText("Memeriksa akun...");
            new SwingWorker<AuthResult, Void>() {
                @Override protected AuthResult doInBackground() {
                    return dao.authenticate(idOrUser, password);
                }
                @Override protected void done() {
                    view.getBtnLogin().setText("Masuk");
                    view.getBtnLogin().setEnabled(true);
                    try {
                        AuthResult auth = get();
                        if (auth == null) {
                            view.msg("ID atau password salah, atau akun nonaktif.");
                            return;
                        }
                        FormDashboard dashboard = new FormDashboard(auth.id, auth.role);
                        new DashboardController(dashboard, auth.id, auth.role);
                        dashboard.setVisible(true);
                        view.dispose();
                    } catch (Exception ex) {
                        Throwable root = ex;
                        while (root.getCause() != null) root = root.getCause();
                        view.msg("Login gagal: " + root.getMessage());
                    }
                }
            }.execute();
        });
    }
}
