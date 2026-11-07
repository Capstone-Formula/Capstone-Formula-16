package app.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormKaryawan extends javax.swing.JFrame {
    private DefaultTableModel model;

    public FormKaryawan() {
        initComponents();

        setIconImage(AppTheme.appIcon());
        pnlRoot.setBackground(AppTheme.BG_MAIN);

        AppTheme.styleHeader(pnlHeader, pnlLogo, lblTitle, "Data Karyawan");
        AppTheme.styleButton(btnKembali, "Kembali", AppTheme.Variant.GHOST, AppTheme.Glyph.ARROW_LEFT);
        AppTheme.statusLine(lblLoadStatus, "", false);

        AppTheme.styleLabel(lblIdKaryawan);
        AppTheme.styleInput(idSource);
        idSource.setEditable(false);
        idSource.setBackground(new java.awt.Color(241, 245, 249));

        JLabel[] fl = {lblNama, lblPassword, lblTelepon, lblJk, lblLahir, lblAlamat, lblArea, lblShift, lblPeran, lblStatus};
        JComponent[] fc = {txtNama, txtPassword, txtTelepon, cbJk, txtLahir, txtAlamat, txtArea, cbShift, cbPeran, cbStatus};
        for (int i = 0; i < fl.length; i++) {
            AppTheme.styleLabel(fl[i]);
            if (fc[i] instanceof JComboBox) AppTheme.styleCombo((JComboBox<?>) fc[i]);
            else AppTheme.styleInput((JTextField) fc[i]);
        }

        txtLahir.setEditable(false);
        txtLahir.setText(java.time.LocalDate.of(2000, 1, 1).toString());
        AppTheme.styleButton(btnPilihTanggal, "Pilih", AppTheme.Variant.GHOST, null);
        btnPilihTanggal.setToolTipText("Pilih tanggal lahir");
        btnPilihTanggal.setMargin(new java.awt.Insets(0, 2, 0, 2));
        btnPilihTanggal.addActionListener(e -> {
            java.time.LocalDate awal;
            try { awal = java.time.LocalDate.parse(txtLahir.getText().trim()); }
            catch (Exception ex) { awal = java.time.LocalDate.of(2000, 1, 1); }
            java.time.LocalDate pilih = DatePickerDialog.pilih(this, awal);
            if (pilih != null) txtLahir.setText(pilih.toString());
        });
        lblAlamat.setText("Alamat Domisili");

        AppTheme.styleButton(btnTambah, "Tambah Karyawan", AppTheme.Variant.PRIMARY, AppTheme.Glyph.PLUS);
        AppTheme.styleButton(btnStatus, "Update", AppTheme.Variant.INK, AppTheme.Glyph.TOGGLE);
        AppTheme.styleButton(btnHapus, "Hapus", AppTheme.Variant.DANGER, AppTheme.Glyph.TRASH);
        lblFormTitle.setText("Formulir Karyawan");
        AppTheme.styleSectionTitle(lblFormTitle);
        lblTableTitle.setText("Daftar Karyawan & Hak Akses");
        AppTheme.styleSectionTitle(lblTableTitle);
        AppTheme.cardPanel(pnlCard1);
        AppTheme.cardPanel(pnlCard2);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Telepon", "Jenis Kelamin", "Alamat", "Lahir", "Shift", "Status", "Peran", "Area"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblData.setModel(model);
        AppTheme.styleTable(tblData);
        tblData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        int[] lebar = {50, 160, 110, 110, 150, 100, 70, 100, 115, 95};
        for (int i = 0; i < model.getColumnCount(); i++) {
            tblData.getColumnModel().getColumn(i).setPreferredWidth(lebar[i]);
        }
        tblData.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            if (tblData.getSelectedRow() >= 0) pilihBaris(); else idSource.setText("");
        });
        getRootPane().setDefaultButton(btnTambah);
        pack();
        setLocationRelativeTo(null);
    }

    private void pilihBaris() {
        int row = tblData.convertRowIndexToModel(tblData.getSelectedRow());
        idSource.setText(teks(row, 0));
        txtNama.setText(teks(row, 1));
        txtPassword.setText("");
        txtTelepon.setText(teks(row, 2));
        selectCombo(cbJk, teks(row, 3));
        txtAlamat.setText(teks(row, 4));
        try { txtLahir.setText(java.time.LocalDate.parse(teks(row, 5).substring(0, 10)).toString()); }
        catch (Exception ignored) { txtLahir.setText(""); }
        selectCombo(cbShift, teks(row, 6));
        selectCombo(cbStatus, teks(row, 7));
        selectCombo(cbPeran, teks(row, 8));
        txtArea.setText(teks(row, 9));
    }

    private void selectCombo(JComboBox<String> cb, String val) {
        if (val == null) return;
        for (int i = 0; i < cb.getItemCount(); i++) {
            if (cb.getItemAt(i).equalsIgnoreCase(val.trim())) {
                cb.setSelectedIndex(i);
                return;
            }
        }
    }

    private String teks(int row, int col) {
        Object v = model.getValueAt(row, col);
        return v == null ? "" : v.toString();
    }

    public int idTerpilih() {
        int i = tblData.getSelectedRow();
        return i < 0 ? -1 : Integer.parseInt(teks(tblData.convertRowIndexToModel(i), 0));
    }

    public String[] data() {
        return new String[]{
            txtNama.getText().trim(),
            txtPassword.getText().trim(),
            txtTelepon.getText().trim(),
            cbJk.getSelectedItem() == null ? "Laki-laki" : cbJk.getSelectedItem().toString(),
            txtAlamat.getText().trim(),
            txtLahir.getText().trim(),
            cbShift.getSelectedItem() == null ? "Siang" : cbShift.getSelectedItem().toString(),
            cbPeran.getSelectedItem() == null ? "Administrasi" : cbPeran.getSelectedItem().toString(),
            txtArea.getText().trim(),
            cbStatus.getSelectedItem() == null ? "Aktif" : cbStatus.getSelectedItem().toString()
        };
    }

    public void setLoadStatus(String status, boolean error) {
        AppTheme.statusLine(lblLoadStatus, status, error);
        setTitle(error ? "Formula - Karyawan | KONEKSI BERMASALAH" : "Formula - Karyawan");
    }

    public DefaultTableModel getModel() { return model; }
    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnStatus() { return btnStatus; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnKembali() { return btnKembali; }
    public void msg(String m) { JOptionPane.showMessageDialog(this, m); }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRoot = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        pnlLogo = new javax.swing.JPanel();
        lblIdKaryawan = new javax.swing.JLabel();
        idSource = new javax.swing.JTextField();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        lblTelepon = new javax.swing.JLabel();
        txtTelepon = new javax.swing.JTextField();
        lblJk = new javax.swing.JLabel();
        cbJk = new javax.swing.JComboBox<>();
        lblAlamat = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        lblLahir = new javax.swing.JLabel();
        txtLahir = new javax.swing.JTextField();
        btnPilihTanggal = new javax.swing.JButton();
        lblShift = new javax.swing.JLabel();
        cbShift = new javax.swing.JComboBox<>();
        lblPeran = new javax.swing.JLabel();
        cbPeran = new javax.swing.JComboBox<>();
        lblArea = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        btnTambah = new javax.swing.JButton();
        btnStatus = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        lblLoadStatus = new javax.swing.JLabel();
        lblFormTitle = new javax.swing.JLabel();
        lblTableTitle = new javax.swing.JLabel();
        pnlCard1 = new javax.swing.JPanel();
        pnlCard2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formula - Karyawan");
        setResizable(false);

        pnlRoot.setBackground(new java.awt.Color(242, 246, 245));
        pnlRoot.setPreferredSize(new java.awt.Dimension(1040, 740));
        pnlRoot.setLayout(null);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); 
        lblTitle.setText("Formula  /  Data Karyawan");
        lblTitle.setForeground(new java.awt.Color(16, 37, 43));
        pnlHeader.add(lblTitle);
        lblTitle.setBounds(70, 18, 605, 33);

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
        pnlLogo.setBounds(26, 19, 32, 31);

        pnlRoot.add(pnlHeader);
        pnlHeader.setBounds(0, 0, 1040, 76);

        lblIdKaryawan.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblIdKaryawan.setText("ID Karyawan");
        lblIdKaryawan.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblIdKaryawan);
        lblIdKaryawan.setBounds(742, 100, 250, 18);

        idSource.setEditable(false);
        idSource.setBackground(new java.awt.Color(241, 245, 249));
        idSource.setToolTipText("ID karyawan terisi otomatis saat data dipilih.");
        pnlRoot.add(idSource);
        idSource.setBounds(742, 120, 250, 35);

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblNama.setText("Nama Lengkap");
        lblNama.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblNama);
        lblNama.setBounds(48, 162, 224, 20);

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtNama.setBackground(new java.awt.Color(255, 255, 255));
        txtNama.setForeground(new java.awt.Color(16, 37, 43));
        txtNama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtNama);
        txtNama.setBounds(48, 183, 224, 38);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblPassword.setText("Password");
        lblPassword.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblPassword);
        lblPassword.setBounds(288, 162, 224, 20);

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setForeground(new java.awt.Color(16, 37, 43));
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtPassword);
        txtPassword.setBounds(288, 183, 224, 38);

        lblTelepon.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblTelepon.setText("No Telepon");
        lblTelepon.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblTelepon);
        lblTelepon.setBounds(528, 162, 224, 20);

        txtTelepon.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtTelepon.setBackground(new java.awt.Color(255, 255, 255));
        txtTelepon.setForeground(new java.awt.Color(16, 37, 43));
        txtTelepon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtTelepon);
        txtTelepon.setBounds(528, 183, 224, 38);

        lblJk.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblJk.setText("Jenis Kelamin");
        lblJk.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblJk);
        lblJk.setBounds(768, 162, 224, 20);

        cbJk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));
        cbJk.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cbJk.setBackground(new java.awt.Color(255, 255, 255));
        cbJk.setForeground(new java.awt.Color(16, 37, 43));
        cbJk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cbJk);
        cbJk.setBounds(768, 183, 224, 38);

        lblAlamat.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblAlamat.setText("Alamat Domisili");
        lblAlamat.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblAlamat);
        lblAlamat.setBounds(288, 230, 224, 20);

        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtAlamat.setBackground(new java.awt.Color(255, 255, 255));
        txtAlamat.setForeground(new java.awt.Color(16, 37, 43));
        txtAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtAlamat);
        txtAlamat.setBounds(288, 251, 224, 38);

        lblLahir.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblLahir.setText("Tanggal Lahir");
        lblLahir.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblLahir);
        lblLahir.setBounds(48, 230, 224, 20);

        txtLahir.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtLahir.setBackground(new java.awt.Color(255, 255, 255));
        txtLahir.setForeground(new java.awt.Color(16, 37, 43));
        txtLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtLahir);
        txtLahir.setBounds(48, 251, 148, 38);

        btnPilihTanggal.setText("Pilih");
        btnPilihTanggal.setToolTipText("Pilih tanggal lahir");
        pnlRoot.add(btnPilihTanggal);
        btnPilihTanggal.setBounds(200, 251, 72, 38);

        lblShift.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblShift.setText("Shift Kerja");
        lblShift.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblShift);
        lblShift.setBounds(768, 230, 224, 20);

        cbShift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Siang", "Malam" }));
        cbShift.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cbShift.setBackground(new java.awt.Color(255, 255, 255));
        cbShift.setForeground(new java.awt.Color(16, 37, 43));
        cbShift.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cbShift);
        cbShift.setBounds(768, 251, 224, 38);

        lblPeran.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblPeran.setText("Divisi / Peran");
        lblPeran.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblPeran);
        lblPeran.setBounds(48, 298, 224, 20);

        cbPeran.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrasi", "Produksi", "Fulfillment" }));
        cbPeran.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cbPeran.setBackground(new java.awt.Color(255, 255, 255));
        cbPeran.setForeground(new java.awt.Color(16, 37, 43));
        cbPeran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cbPeran);
        cbPeran.setBounds(48, 319, 224, 38);

        lblArea.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblArea.setText("Area Penugasan");
        lblArea.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblArea);
        lblArea.setBounds(528, 230, 224, 20);

        txtArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtArea.setBackground(new java.awt.Color(255, 255, 255));
        txtArea.setForeground(new java.awt.Color(16, 37, 43));
        txtArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtArea);
        txtArea.setBounds(528, 251, 224, 38);

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblStatus.setText("Status Akun");
        lblStatus.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblStatus);
        lblStatus.setBounds(288, 298, 224, 20);

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Nonaktif" }));
        cbStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cbStatus.setForeground(new java.awt.Color(16, 37, 43));
        cbStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cbStatus);
        cbStatus.setBounds(288, 319, 224, 38);

        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnTambah.setText("Tambah Karyawan");
        btnTambah.setBackground(new java.awt.Color(15, 118, 110));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 118, 110), 1, true));
        pnlRoot.add(btnTambah);
        btnTambah.setBounds(48, 375, 210, 42);

        btnStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnStatus.setText("Update");
        btnStatus.setBackground(new java.awt.Color(22, 54, 62));
        btnStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(22, 54, 62), 1, true));
        pnlRoot.add(btnStatus);
        btnStatus.setBounds(266, 375, 160, 42);

        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnHapus.setText("Hapus");
        btnHapus.setBackground(new java.awt.Color(253, 235, 235));
        btnHapus.setForeground(new java.awt.Color(197, 48, 48));
        btnHapus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(253, 235, 235), 1, true));
        pnlRoot.add(btnHapus);
        btnHapus.setBounds(434, 375, 112, 42);

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
        
            },
            new String [] {
        
            }
        ));
        scroll.setViewportView(tblData);

        pnlRoot.add(scroll);
        scroll.setBounds(36, 509, 968, 191);

        lblLoadStatus.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
        lblLoadStatus.setText("");
        lblLoadStatus.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblLoadStatus);
        lblLoadStatus.setBounds(388, 467, 604, 24);

        lblFormTitle.setFont(new java.awt.Font("Segoe UI", 1, 17)); 
        lblFormTitle.setText("Formulir Karyawan");
        lblFormTitle.setForeground(new java.awt.Color(16, 37, 43));
        pnlRoot.add(lblFormTitle);
        lblFormTitle.setBounds(48, 106, 500, 24);

        lblTableTitle.setFont(new java.awt.Font("Segoe UI", 1, 17)); 
        lblTableTitle.setText("Daftar Karyawan & Hak Akses");
        lblTableTitle.setForeground(new java.awt.Color(16, 37, 43));
        pnlRoot.add(lblTableTitle);
        lblTableTitle.setBounds(48, 467, 500, 24);

        pnlCard1.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 230, 228), 1, true));
        pnlCard1.setLayout(null);

        pnlRoot.add(pnlCard1);
        pnlCard1.setBounds(18, 86, 1004, 355);

        pnlCard2.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 230, 228), 1, true));
        pnlCard2.setLayout(null);

        pnlRoot.add(pnlCard2);
        pnlCard2.setBounds(18, 445, 1004, 281);

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

    //GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnPilihTanggal;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnStatus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbJk;
    private javax.swing.JComboBox<String> cbPeran;
    private javax.swing.JComboBox<String> cbShift;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblIdKaryawan;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblFormTitle;
    private javax.swing.JLabel lblJk;
    private javax.swing.JLabel lblLahir;
    private javax.swing.JLabel lblLoadStatus;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPeran;
    private javax.swing.JLabel lblShift;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTableTitle;
    private javax.swing.JLabel lblTelepon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCard1;
    private javax.swing.JPanel pnlCard2;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField idSource;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtLahir;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtTelepon;
    //GEN-END:variables
}
