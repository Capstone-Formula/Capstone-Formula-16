package app.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormUpdateStatus extends javax.swing.JFrame {

    private DefaultTableModel tableModel;

    public FormUpdateStatus() {
        initComponents();

        setIconImage(AppTheme.appIcon());
        pnlRoot.setBackground(AppTheme.BG_MAIN);

        AppTheme.styleHeader(pnlHeader, pnlLogo, lblTitle, "Ubah Status Obat");
        AppTheme.styleButton(btnKembali, "Kembali", AppTheme.Variant.GHOST, AppTheme.Glyph.ARROW_LEFT);
        AppTheme.styleSectionTitle(lblStatusTitle);
        lblStatusTitle.setText("Daftar Obat & Status");
        AppTheme.styleButton(btnUpdateStatus, "Update", AppTheme.Variant.PRIMARY, AppTheme.Glyph.TOGGLE);
        AppTheme.cardPanel(pnlCard1);

        tableModel = new DefaultTableModel(new String[]{"ID Obat", "Nama Obat", "Jenis Obat", "Status Saat Ini"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblObat.setModel(tableModel);
        AppTheme.styleTable(tblObat);
        tblObat.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
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
        lblStatusTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblObat = new javax.swing.JTable();
        lblStatusFoot = new javax.swing.JLabel();
        btnUpdateStatus = new javax.swing.JButton();
        pnlCard1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formula - Ubah Status Obat");
        setResizable(false);

        pnlRoot.setBackground(new java.awt.Color(242, 246, 245));
        pnlRoot.setPreferredSize(new java.awt.Dimension(1040, 740));
        pnlRoot.setLayout(null);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); 
        lblTitle.setText("Formula  /  Ubah Status Obat");
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

        lblStatusTitle.setFont(new java.awt.Font("Segoe UI", 1, 17)); 
        lblStatusTitle.setText("Daftar Obat & Status");
        lblStatusTitle.setForeground(new java.awt.Color(16, 37, 43));
        pnlRoot.add(lblStatusTitle);
        lblStatusTitle.setBounds(48, 108, 500, 24);

        tblObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
        
            },
            new String [] {
        
            }
        ));
        jScrollPane1.setViewportView(tblObat);

        pnlRoot.add(jScrollPane1);
        jScrollPane1.setBounds(36, 150, 968, 460);

        lblStatusFoot.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
        lblStatusFoot.setText("");
        lblStatusFoot.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblStatusFoot);
        lblStatusFoot.setBounds(36, 651, 740, 44);

        btnUpdateStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnUpdateStatus.setText("Update");
        btnUpdateStatus.setBackground(new java.awt.Color(15, 118, 110));
        btnUpdateStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 118, 110), 1, true));
        pnlRoot.add(btnUpdateStatus);
        btnUpdateStatus.setBounds(816, 651, 200, 44);

        pnlCard1.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 230, 228), 1, true));
        pnlCard1.setLayout(null);

        pnlRoot.add(pnlCard1);
        pnlCard1.setBounds(18, 86, 1004, 632);

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

    public void setLoadStatus(String status, boolean error) {
        AppTheme.statusLine(lblStatusFoot, status, error);
    }
    public javax.swing.JTable getTblObat() { return tblObat; }
    public DefaultTableModel getModel() { return tableModel; }
    public javax.swing.JButton getBtnUpdateStatus() { return btnUpdateStatus; }
    public javax.swing.JButton getBtnKembali() { return btnKembali; }
    public void msg(String m) { JOptionPane.showMessageDialog(this, m); }

    //GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnUpdateStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatusFoot;
    private javax.swing.JLabel lblStatusTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCard1;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JTable tblObat;
    //GEN-END:variables
}
