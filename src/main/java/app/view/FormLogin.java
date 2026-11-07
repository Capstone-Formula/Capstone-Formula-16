package app.view;

import javax.swing.JOptionPane;

public class FormLogin extends javax.swing.JFrame {

    public FormLogin() {
        initComponents();

        setIconImage(AppTheme.appIcon());
        pnlRoot.setBackground(java.awt.Color.WHITE);

        pnlBrand.setUI(new javax.swing.plaf.basic.BasicPanelUI() {
            @Override public void paint(java.awt.Graphics g, javax.swing.JComponent c) {
                g.setColor(AppTheme.DEEP);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });
        lblBrandLogo.setForeground(java.awt.Color.WHITE);
        AppTheme.logoPanel(pnlLogoMark, true);

        lblWelcome.setForeground(AppTheme.INK);
        AppTheme.styleLabel(lblId);
        AppTheme.styleLabel(lblPassword);

        AppTheme.styleInput(txtIdKaryawan);
        AppTheme.styleInput(txtPassword);

        AppTheme.styleButton(btnLogin, "Masuk", AppTheme.Variant.PRIMARY, AppTheme.Glyph.ARROW_RIGHT);
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        pack();
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnLogin);
    }

    @SuppressWarnings("unchecked")
    //GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRoot = new javax.swing.JPanel();
        pnlBrand = new javax.swing.JPanel();
        lblBrandLogo = new javax.swing.JLabel();
        pnlLogoMark = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtIdKaryawan = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formula - Login");
        setResizable(false);

        pnlRoot.setBackground(new java.awt.Color(255, 255, 255));
        pnlRoot.setPreferredSize(new java.awt.Dimension(1040, 740));
        pnlRoot.setLayout(null);

        pnlBrand.setBackground(new java.awt.Color(6, 44, 43));
        pnlBrand.setLayout(null);

        lblBrandLogo.setFont(new java.awt.Font("Segoe UI", 1, 40)); 
        lblBrandLogo.setText("Formula");
        lblBrandLogo.setForeground(new java.awt.Color(255, 255, 255));
        pnlBrand.add(lblBrandLogo);
        lblBrandLogo.setBounds(110, 308, 240, 60);

        pnlLogoMark.setBackground(new java.awt.Color(60, 214, 192));
        pnlLogoMark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 214, 192), 1, true));
        pnlLogoMark.setLayout(null);

        pnlBrand.add(pnlLogoMark);
        pnlLogoMark.setBounds(40, 310, 56, 56);

        pnlRoot.add(pnlBrand);
        pnlBrand.setBounds(0, 0, 420, 740);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 32)); 
        lblWelcome.setText("Masuk");
        lblWelcome.setForeground(new java.awt.Color(16, 37, 43));
        pnlRoot.add(lblWelcome);
        lblWelcome.setBounds(550, 216, 370, 46);

        lblId.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblId.setText("ID Karyawan");
        lblId.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblId);
        lblId.setBounds(550, 292, 370, 20);

        txtIdKaryawan.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
        txtIdKaryawan.setBackground(new java.awt.Color(255, 255, 255));
        txtIdKaryawan.setForeground(new java.awt.Color(16, 37, 43));
        txtIdKaryawan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtIdKaryawan);
        txtIdKaryawan.setBounds(550, 316, 370, 46);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lblPassword.setText("Kata sandi");
        lblPassword.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblPassword);
        lblPassword.setBounds(550, 382, 370, 20);

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setForeground(new java.awt.Color(16, 37, 43));
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtPassword);
        txtPassword.setBounds(550, 406, 370, 46);

        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 15)); 
        btnLogin.setText("Masuk");
        btnLogin.setBackground(new java.awt.Color(15, 118, 110));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 118, 110), 1, true));
        pnlRoot.add(btnLogin);
        btnLogin.setBounds(550, 486, 370, 50);

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

    public String getIdKaryawan() { return txtIdKaryawan.getText().trim(); }
    public String getPassword() { return new String(txtPassword.getPassword()); }
    public javax.swing.JButton getBtnLogin() { return btnLogin; }
    public void msg(String msg) { JOptionPane.showMessageDialog(this, msg); }

    //GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblBrandLogo;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlBrand;
    private javax.swing.JPanel pnlLogoMark;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JTextField txtIdKaryawan;
    private javax.swing.JPasswordField txtPassword;
    //GEN-END:variables
}
