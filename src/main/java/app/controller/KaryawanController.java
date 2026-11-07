package app.controller;

import app.model.KaryawanDAO;
import app.view.FormDashboard;
import app.view.FormKaryawan;
import javax.swing.JOptionPane;

public final class KaryawanController {
    private final KaryawanDAO dao = new KaryawanDAO();
    private final FormKaryawan form;

    public KaryawanController(FormKaryawan form, FormDashboard dashboard) {
        this.form = form;
        refresh();

        form.getBtnTambah().addActionListener(e -> {
            try {
                String[] d = form.data();
                int id = dao.tambah(d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8]);
                form.msg("Karyawan berhasil ditambahkan dengan ID #" + id + ".");
                refresh();
            } catch (Exception ex) {
                form.msg("Gagal menambah karyawan: " + ex.getMessage());
            }
        });

        form.getBtnStatus().addActionListener(e -> {
            if (form.idTerpilih() < 0) {
                form.msg("Pilih karyawan dari tabel terlebih dahulu.");
                return;
            }
            try {
                dao.updateStatus(form.idTerpilih(), form.data()[9]);
                form.msg("Status karyawan berhasil diubah.");
                refresh();
            } catch (Exception ex) {
                form.msg("Gagal mengubah status: " + ex.getMessage());
            }
        });

        form.getBtnHapus().addActionListener(e -> {
            int id = form.idTerpilih();
            if (id < 0) {
                form.msg("Pilih karyawan dari tabel terlebih dahulu.");
                return;
            }
            if (JOptionPane.showConfirmDialog(form, "Hapus data karyawan ID #" + id + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION) {
                return;
            }
            try {
                dao.hapus(id);
                form.msg("Karyawan berhasil dihapus.");
                refresh();
            } catch (Exception ex) {
                form.msg("Karyawan tidak dapat dihapus: " + ex.getMessage());
            }
        });

        form.getBtnKembali().addActionListener(e -> {
            dashboard.setVisible(true);
            form.dispose();
        });
    }

    private void refresh() {
        form.setLoadStatus("Memuat data karyawan...", false);
        new javax.swing.SwingWorker<java.util.List<Object[]>, Void>() {
            @Override protected java.util.List<Object[]> doInBackground() {
                return dao.semua();
            }
            @Override protected void done() {
                try {
                    java.util.List<Object[]> data = get();
                    form.getModel().setRowCount(0);
                    for (Object[] r : data) form.getModel().addRow(r);
                    form.setLoadStatus(data.isEmpty() ? "Data karyawan kosong." : "Data karyawan berhasil dimuat.", data.isEmpty());
                } catch (Exception ex) {
                    form.setLoadStatus("Gagal memuat karyawan: " + akar(ex), true);
                    javax.swing.JOptionPane.showMessageDialog(form, "Gagal mengambil data karyawan: " + akar(ex), "Kesalahan", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private static String akar(Throwable t) {
        while (t.getCause() != null) t = t.getCause();
        return t.getMessage() == null ? t.getClass().getSimpleName() : t.getMessage();
    }
}
