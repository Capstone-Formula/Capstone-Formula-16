package app.controller;

import app.model.MasterDAO;
import app.view.*;
import java.util.List;

public class UpdateStatusController {
    private FormUpdateStatus v;
    private MasterDAO m;

    public UpdateStatusController(FormUpdateStatus v, MasterDAO m, FormDashboard fd) {
        this.v = v;
        this.m = m;
        loadTable();

        v.getBtnUpdateStatus().addActionListener(e -> {
            int row = v.getTblObat().getSelectedRow();
            if (row == -1) {
                v.msg("Pilih salah satu baris obat pada tabel terlebih dahulu.");
                return;
            }
            int modelRow = v.getTblObat().convertRowIndexToModel(row);
            int idObat = Integer.parseInt(v.getModel().getValueAt(modelRow, 0).toString());
            String namaObat = v.getModel().getValueAt(modelRow, 1).toString();
            String currentStatus = v.getModel().getValueAt(modelRow, 3).toString();
            String statusBaru = "Tersedia".equalsIgnoreCase(currentStatus) ? "Tidak Tersedia" : "Tersedia";
            try {
                if (m.updateStatusObat(idObat, statusBaru)) {
                    v.msg("Status obat \"" + namaObat + "\" berhasil diubah menjadi '" + statusBaru + "'.");
                    loadTable();
                } else {
                    v.msg("ID obat tidak ditemukan di database.");
                }
            } catch (RuntimeException ex) {
                v.msg("Gagal mengubah status: " + akar(ex));
            }
        });

        v.getBtnKembali().addActionListener(e -> {
            fd.setVisible(true);
            v.dispose();
        });
    }

    private void loadTable() {
        v.setLoadStatus("Memuat data obat...", false);
        new javax.swing.SwingWorker<java.util.List<Object[]>, Void>() {
            @Override protected java.util.List<Object[]> doInBackground() {
                return m.getAllObat();
            }
            @Override protected void done() {
                try {
                    java.util.List<Object[]> data = get();
                    v.getModel().setRowCount(0);
                    for (Object[] row : data) v.getModel().addRow(row);
                    v.setLoadStatus("Menampilkan " + data.size() + " obat dari katalog.", false);
                } catch (Exception ex) {
                    v.setLoadStatus("Gagal memuat obat: " + akar(ex), true);
                }
            }
        }.execute();
    }

    private static String akar(Throwable t) {
        while (t.getCause() != null) t = t.getCause();
        return t.getMessage() == null ? t.getClass().getSimpleName() : t.getMessage();
    }
}
