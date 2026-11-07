package app.view;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

public class FormProduksi extends javax.swing.JFrame {

    public FormProduksi(List<String> listObat, List<String> listBahan) {
        initComponents();

        setIconImage(AppTheme.appIcon());
        pnlRoot.setBackground(AppTheme.BG_MAIN);

        AppTheme.styleHeader(pnlHeader, pnlLogo, lblTitle, "Proses Produksi Obat");
        AppTheme.styleButton(btnKembali, "Kembali", AppTheme.Variant.GHOST, AppTheme.Glyph.ARROW_LEFT);

        AppTheme.styleSectionTitle(lblHeaderDesc);

        AppTheme.styleLabel(lblObat);
        AppTheme.styleCombo(cbObat);

        AppTheme.styleLabel(lblHasil);
        AppTheme.styleCombo(cbJumlahHasil);
        AppTheme.styleButton(btnLihatObat, "Lihat Obat", AppTheme.Variant.GHOST, AppTheme.Glyph.SEARCH);

        lblBahan.setVisible(false);
        cbBahan.setVisible(false);
        lblJmlBahan.setVisible(false);
        txtJmlBahan.setVisible(false);

        AppTheme.styleButton(btnProses, "Produksi Obat", AppTheme.Variant.PRIMARY, AppTheme.Glyph.GEAR);

        AppTheme.cardPanel(pnlCard1);

        setObatOptions(listObat);
        pack();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRoot = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        pnlLogo = new javax.swing.JPanel();
        lblHeaderDesc = new javax.swing.JLabel();
        lblObat = new javax.swing.JLabel();
        cbObat = new javax.swing.JComboBox<>();
        lblHasil = new javax.swing.JLabel();
        cbJumlahHasil = new javax.swing.JComboBox<>();
        btnLihatObat = new javax.swing.JButton();
        lblBahan = new javax.swing.JLabel();
        cbBahan = new javax.swing.JComboBox<>();
        lblJmlBahan = new javax.swing.JLabel();
        txtJmlBahan = new javax.swing.JTextField();
        btnProses = new javax.swing.JButton();
        pnlCard1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formula - Produksi");
        setResizable(false);

        pnlRoot.setBackground(new java.awt.Color(242, 246, 245));
        pnlRoot.setPreferredSize(new java.awt.Dimension(1040, 740));
        pnlRoot.setLayout(null);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); 
        lblTitle.setText("Formula  /  Proses Produksi Obat");
        lblTitle.setForeground(new java.awt.Color(16, 37, 43));
        pnlHeader.add(lblTitle);
        lblTitle.setBounds(74, 20, 640, 36);

        btnKembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnKembali.setText("Kembali");
        btnKembali.setBackground(new java.awt.Color(255, 255, 255));
        btnKembali.setForeground(new java.awt.Color(16, 37, 43));
        btnKembali.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlHeader.add(btnKembali);
        btnKembali.setBounds(888, 17, 128, 42);

        pnlLogo.setBackground(new java.awt.Color(15, 118, 110));
        pnlLogo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 118, 110), 1, true));
        pnlLogo.setLayout(null);

        pnlHeader.add(pnlLogo);
        pnlLogo.setBounds(28, 21, 34, 34);

        pnlRoot.add(pnlHeader);
        pnlHeader.setBounds(0, 0, 1040, 76);

        pnlCard1.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 230, 228), 1, true));
        pnlCard1.setLayout(null);

        pnlRoot.add(pnlCard1);
        pnlCard1.setBounds(26, 94, 988, 340);

        lblHeaderDesc.setFont(new java.awt.Font("Segoe UI", 1, 17)); 
        lblHeaderDesc.setText("Produksi Obat");
        lblHeaderDesc.setForeground(new java.awt.Color(16, 37, 43));
        pnlCard1.add(lblHeaderDesc);
        lblHeaderDesc.setBounds(40, 24, 500, 26);

        lblObat.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblObat.setText("Obat jadi");
        lblObat.setForeground(new java.awt.Color(84, 106, 112));
        pnlCard1.add(lblObat);
        lblObat.setBounds(40, 78, 896, 20);

        cbObat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));
        cbObat.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cbObat.setBackground(new java.awt.Color(255, 255, 255));
        cbObat.setForeground(new java.awt.Color(16, 37, 43));
        cbObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlCard1.add(cbObat);
        cbObat.setBounds(40, 99, 896, 38);

        lblHasil.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblHasil.setText("Jumlah stok dihasilkan");
        lblHasil.setForeground(new java.awt.Color(84, 106, 112));
        pnlCard1.add(lblHasil);
        lblHasil.setBounds(40, 158, 896, 20);

        cbJumlahHasil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "25", "50", "100" }));
        cbJumlahHasil.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cbJumlahHasil.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard1.add(cbJumlahHasil);
        cbJumlahHasil.setBounds(40, 179, 896, 38);

        btnLihatObat.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnLihatObat.setText("Lihat Obat");
        btnLihatObat.setBackground(new java.awt.Color(238, 248, 246));
        btnLihatObat.setForeground(new java.awt.Color(15, 118, 110));
        pnlCard1.add(btnLihatObat);
        btnLihatObat.setBounds(768, 62, 168, 32);

        lblBahan.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblBahan.setText("Satuan Sediaan Hasil");
        lblBahan.setVisible(false);
        pnlCard1.add(lblBahan);
        lblBahan.setBounds(8, 190, 220, 32);

        cbBahan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Strip", "Botol" }));
        cbBahan.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cbBahan.setVisible(false);
        pnlCard1.add(cbBahan);
        cbBahan.setBounds(238, 190, 580, 36);

        lblJmlBahan.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        lblJmlBahan.setText("Tanggal Kedaluwarsa");
        lblJmlBahan.setVisible(false);
        pnlCard1.add(lblJmlBahan);
        lblJmlBahan.setBounds(8, 245, 220, 32);

        txtJmlBahan.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtJmlBahan.setBackground(new java.awt.Color(255, 255, 255));
        txtJmlBahan.setForeground(new java.awt.Color(37, 57, 70));
        txtJmlBahan.setVisible(false);
        pnlCard1.add(txtJmlBahan);
        txtJmlBahan.setBounds(238, 245, 580, 36);

        btnProses.setFont(new java.awt.Font("Segoe UI", 1, 17)); 
        btnProses.setText("Produksi Obat");
        btnProses.setBackground(new java.awt.Color(15, 118, 110));
        btnProses.setForeground(new java.awt.Color(255, 255, 255));
        btnProses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 118, 110), 1, true));
        pnlCard1.add(btnProses);
        btnProses.setBounds(40, 246, 896, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents

    public String getCbObat() { return String.valueOf(cbObat.getSelectedItem()); }
    public String getCbBahan() { return String.valueOf(cbBahan.getSelectedItem()); }
    public String getJmlHasil() { return String.valueOf(cbJumlahHasil.getSelectedItem()); }
    public String getJmlBahan() { return txtJmlBahan.getText().trim(); }
    public void setObatOptions(java.util.List<String> names) {
        cbObat.setModel(new javax.swing.DefaultComboBoxModel<>(names.toArray(new String[0])));
        btnProses.setEnabled(!names.isEmpty());
    }
    public void setLoadStatus(String message, boolean error) {
        setTitle(error ? "Formula - Produksi | DATA BELUM DIMUAT" : "Formula - Produksi");
    }
    public javax.swing.JButton getBtnLihatObat() { return btnLihatObat; }
    public javax.swing.JButton getBtnProses() { return btnProses; }
    public javax.swing.JButton getBtnKembali() { return btnKembali; }
    public void msg(String m) { JOptionPane.showMessageDialog(this, m); }
    public void reset() {
        cbJumlahHasil.setSelectedIndex(0);
        if (cbObat.getItemCount() > 0) cbObat.setSelectedIndex(0);
    }

    //GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnProses;
    private javax.swing.JComboBox<String> cbBahan;
    private javax.swing.JComboBox<String> cbObat;
    private javax.swing.JComboBox<String> cbJumlahHasil;
    private javax.swing.JLabel lblBahan;
    private javax.swing.JLabel lblHasil;
    private javax.swing.JLabel lblHeaderDesc;
    private javax.swing.JLabel lblJmlBahan;
    private javax.swing.JLabel lblObat;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCard1;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JTextField txtJmlBahan;
    private javax.swing.JButton btnLihatObat;
    //GEN-END:variables
}
