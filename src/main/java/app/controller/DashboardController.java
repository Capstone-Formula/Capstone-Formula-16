package app.controller;

import app.view.*;
import app.model.*;

public class DashboardController {
    public DashboardController(FormDashboard v, int id, String role) {
        if (role.equals("Administrasi")) {
            v.getBtn(0).addActionListener(e -> bukaMaster(v, "Menu Obat", "Obat", new String[]{"ID Obat", "Nama Obat", "Jenis Obat", "Status Ketersediaan"}, id));
            v.getBtn(1).addActionListener(e -> bukaMaster(v, "Menu Bahan Baku", "Bahan", new String[]{"ID Bahan", "Nama Bahan", "Stok", "Satuan"}, id));
            v.getBtn(2).addActionListener(e -> {
                FormKaryawan form = new FormKaryawan();
                new KaryawanController(form, v);
                aturTutup(form, v);
                form.setVisible(true);
                v.setVisible(false);
            });
            v.getBtn(3).addActionListener(e -> bukaMasterReadOnly(v, "Data Pesanan", "Pesanan", new String[]{"ID Pesanan", "Tanggal", "Status", "Catatan", "Instansi", "Petugas"}));
            v.getBtn(4).addActionListener(e -> bukaMasterReadOnly(v, "Data Pengiriman", "Pengiriman", new String[]{"No Resi", "Tanggal", "Status", "Keterangan", "ID Pesanan", "Petugas"}));
            v.getBtn(5).addActionListener(e -> bukaMasterReadOnly(v, "Instansi Kesehatan", "Instansi", new String[]{"ID Instansi", "Nama Instansi", "Jenis", "Alamat", "Kontak"}));
            v.getBtn(6).addActionListener(e -> bukaAlur(v, "Stok per Batch", "Batch", new String[]{"No Batch", "Nama Obat", "Stok", "Satuan", "Kedaluwarsa"}));
            v.getBtn(7).addActionListener(e -> bukaAlur(v, "Detail Pesanan", "DetailPesanan", new String[]{"ID Detail", "ID Pesanan", "No Batch", "Jumlah Obat"}, true));
        } else if (role.equals("Produksi")) {
            v.getBtn(0).addActionListener(e -> {
                MasterDAO dao = new MasterDAO();
                FormProduksi fp = new FormProduksi(java.util.List.of(), java.util.List.of());
                new ProduksiController(fp, dao, id, v);
                aturTutup(fp, v);
                fp.setVisible(true);
                v.setVisible(false);
                fp.setLoadStatus("Memuat pilihan obat...", false);
                new javax.swing.SwingWorker<java.util.List<String>, Void>() {
                    @Override protected java.util.List<String> doInBackground() {
                        return dao.getCbData("SELECT id_obat, nama_obat FROM obat ORDER BY nama_obat");
                    }
                    @Override protected void done() {
                        try {
                            java.util.List<String> data = get();
                            fp.setObatOptions(data);
                            fp.setLoadStatus(data.isEmpty() ? "Belum ada data obat." : "Pilihan obat siap dipilih.", data.isEmpty());
                        } catch (Exception ex) {
                            fp.setLoadStatus("Gagal memuat obat: " + ex.getMessage(), true);
                        }
                    }
                }.execute();
            });
            v.getBtn(1).addActionListener(e -> bukaAlur(v, "Data Produksi", "Produksi", new String[]{"No Batch", "Nama Obat", "Stok", "Satuan", "Tgl Produksi", "Kedaluwarsa", "Bahan Baku Digunakan", "Petugas", "Kelayakan"}));
            v.getBtn(2).addActionListener(e -> bukaAlur(v, "Penggunaan Bahan", "Penggunaan", new String[]{"ID Detail", "Nama Bahan", "Jumlah Bahan", "Satuan", "Nomor Batch"}));
        } else {
            v.getBtn(0).addActionListener(e -> bukaPesanan(v, id));
            v.getBtn(1).addActionListener(e -> bukaAlur(v, "Detail Pesanan", "DetailPesanan", new String[]{"ID Detail", "ID Pesanan", "No Batch", "Jumlah Obat"}));
            v.getBtn(2).addActionListener(e -> bukaMaster(v, "Menu Pengiriman", "Pengiriman", new String[]{"No Resi", "Tanggal", "Status", "Keterangan", "Pesanan", "Petugas"}, id));
            v.getBtn(3).addActionListener(e -> bukaMaster(v, "Instansi Kesehatan", "Instansi", new String[]{"ID Instansi", "Nama Instansi", "Jenis", "Alamat", "Kontak"}, id));
            v.getBtn(4).addActionListener(e -> bukaAlur(v, "Stok per Batch", "Batch", new String[]{"No Batch", "Nama Obat", "Stok", "Satuan", "Kedaluwarsa"}));
            v.getBtn(5).addActionListener(e -> {
                FormUpdateStatus fus = new FormUpdateStatus();
                new UpdateStatusController(fus, new MasterDAO(), v);
                aturTutup(fus, v);
                fus.setVisible(true);
                v.setVisible(false);
            });
        }

        for (int i=0; i<9; i++) {
            javax.swing.JButton button=v.getBtn(i);
            for (java.awt.event.ActionListener listener:button.getActionListeners()) {
                button.removeActionListener(listener);
                button.addActionListener(event -> {
                    try { listener.actionPerformed(event); }
                    catch (RuntimeException ex) {
                        v.setVisible(true);
                        Throwable cause=ex; while(cause.getCause()!=null) cause=cause.getCause();
                        javax.swing.JOptionPane.showMessageDialog(v,"Menu gagal dibuka: "+cause.getMessage(),"Kesalahan",javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        }

        v.getBtnLogout().addActionListener(e -> {
            int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(
                v,
                "Apakah anda mau logout?",
                "Konfirmasi Logout",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE
            );
            if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
                FormLogin fl = new FormLogin();
                new LoginController(fl, new AuthDAO());
                fl.setVisible(true);
                v.dispose();
            }
        });
    }
    
    private void aturTutup(javax.swing.JFrame form, FormDashboard dashboard) {
        form.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) {
                form.dispose();
                dashboard.setVisible(true);
            }
        });
    }

    private void bukaPesanan(FormDashboard v, int id) {
        FormMaster form = new FormMaster("Kelola Pesanan", new String[]{"ID Pesanan", "Tanggal", "Status", "Catatan", "Instansi", "Petugas"});
        new MasterController(form, new MasterDAO(), "Pesanan", v, id);
        aturTutup(form, v);
        form.setVisible(true);
        v.setVisible(false);
    }

    private void bukaAlur(FormDashboard v, String title, String jenis, String[] columns) {
        bukaAlur(v, title, jenis, columns, false);
    }

    private void bukaAlur(FormDashboard v, String title, String jenis, String[] columns, boolean lihat) {
        FormMaster form = new FormMaster(title, columns);
        if (lihat) form.setReadOnlyMode();
        new AlurController(form, jenis, v);
        aturTutup(form, v);
        form.setVisible(true);
        v.setVisible(false);
    }

    private void bukaMaster(FormDashboard v, String title, String tipe, String[] cols) {
        bukaMaster(v, title, tipe, cols, -1);
    }

    private void bukaMaster(FormDashboard v, String title, String tipe, String[] cols, int id) {
        FormMaster f = new FormMaster(title, cols);
        new MasterController(f, new MasterDAO(), tipe, v, id); 
        aturTutup(f, v);
        f.setVisible(true);
        v.setVisible(false);
    }
    
    private void bukaMasterReadOnly(FormDashboard v, String title, String tipe, String[] cols) {
        FormMaster f = new FormMaster(title, cols);
        f.setReadOnlyMode();
        new MasterController(f, new MasterDAO(), tipe, v); 
        aturTutup(f, v);
        f.setVisible(true);
        v.setVisible(false);
    }
}
