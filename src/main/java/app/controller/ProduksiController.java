package app.controller;

import app.model.AlurDAO;
import app.view.*;
import javax.swing.JOptionPane;

public class ProduksiController {
    public ProduksiController(FormProduksi v, app.model.MasterDAO unused, int idKar, FormDashboard fd) {
        AlurDAO dao = new AlurDAO();
        v.getBtnProses().addActionListener(e -> {
            try {
                String selectedObat = v.getCbObat();
                if (selectedObat == null || selectedObat.isBlank() || !selectedObat.contains(" - ")) {
                    v.msg("Pilih obat jadi yang akan diproduksi.");
                    return;
                }
                int obat = Integer.parseInt(selectedObat.split(" - ")[0].trim());
                String strHasil = v.getJmlHasil().trim();
                if (strHasil.isEmpty() || !strHasil.matches("\\d+")) {
                    v.msg("Jumlah stok obat harus berupa bilangan positif.");
                    return;
                }
                int hasil = Integer.parseInt(strHasil);
                if (hasil <= 0) {
                    v.msg("Jumlah stok obat harus lebih dari nol.");
                    return;
                }

                int konfirmasi = JOptionPane.showConfirmDialog(
                    v,
                    "Apakah ingin memproduksi obat?",
                    "Konfirmasi Produksi",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                if (konfirmasi != JOptionPane.YES_OPTION) {
                    return;
                }

                int nomor = dao.produksiSederhana(obat, hasil, idKar);
                v.msg("Obat berhasil diproduksi!\nNomor Batch: #" + nomor + "\nJumlah Stok: " + hasil + "\n\nCatatan penggunaan bahan baku dapat diinput di menu 'Penggunaan Bahan'.");
                v.reset();
            } catch (Exception ex) {
                v.msg("Gagal memproduksi obat: " + ex.getMessage());
            }
        });
        v.getBtnLihatObat().addActionListener(e -> {
            try {
                java.util.List<Object[]> obat = new app.model.MasterDAO().getAllObat();
                Object[][] rows = obat.toArray(new Object[0][]);
                javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                    rows, new String[]{"ID", "Nama Obat", "Jenis", "Status"}) {
                    @Override public boolean isCellEditable(int r, int c) { return false; }
                };
                javax.swing.JTable table = new javax.swing.JTable(model);
                table.setRowHeight(30);
                table.setAutoCreateRowSorter(true);
                table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(table);
                scroll.setPreferredSize(new java.awt.Dimension(660, 330));
                javax.swing.JOptionPane.showMessageDialog(v, scroll,
                    "Daftar Obat", javax.swing.JOptionPane.PLAIN_MESSAGE);
            } catch (Exception ex) {
                v.msg("Daftar obat belum dapat dimuat: " + ex.getMessage());
            }
        });
        v.getBtnKembali().addActionListener(e -> { fd.setVisible(true); v.dispose(); });
    }
}
