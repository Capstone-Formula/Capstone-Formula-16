package app.view;

public class FormDashboard extends javax.swing.JFrame {

    private javax.swing.JButton[] btns;

    public FormDashboard(int id, String role) {
        initComponents();
        btns = new javax.swing.JButton[]{btnMenu1, btnMenu2, btnMenu3, btnMenu4, btnMenu5, btnMenu6, btnMenu7, btnMenu8, btnMenu9};

        String[] labels = app.model.role.MenuRole.untuk(role).getMenu();

        setIconImage(AppTheme.appIcon());
        pnlRoot.setBackground(AppTheme.BG_MAIN);

        AppTheme.styleHeader(pnlHeader, pnlLogo, lblTitle, "Beranda Operasional");
        lblUser.setForeground(AppTheme.TEXT_MUTED);
        lblUser.setText("ID Karyawan: " + id + "  ·  " + role);

        AppTheme.styleButton(btnLogout, "Keluar", AppTheme.Variant.DANGER, AppTheme.Glyph.LOGOUT);

        for (int i = 0; i < btns.length; i++) {
            if (i < labels.length) {
                btns[i].setText(labels[i]);
                btns[i].setVisible(true);
                AppTheme.styleMenuButton(btns[i]);
            } else {
                btns[i].setVisible(false);
            }
        }

        pack();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRoot = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        pnlLogo = new javax.swing.JPanel();
        btnMenu1 = new javax.swing.JButton();
        btnMenu2 = new javax.swing.JButton();
        btnMenu3 = new javax.swing.JButton();
        btnMenu4 = new javax.swing.JButton();
        btnMenu5 = new javax.swing.JButton();
        btnMenu6 = new javax.swing.JButton();
        btnMenu7 = new javax.swing.JButton();
        btnMenu8 = new javax.swing.JButton();
        btnMenu9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formula - Dashboard");
        setResizable(false);

        pnlRoot.setPreferredSize(new java.awt.Dimension(1040, 740));
        pnlRoot.setBackground(new java.awt.Color(242, 246, 245));
        pnlRoot.setLayout(null);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(16, 37, 43));
        lblTitle.setText("Formula  /  Beranda Operasional");
        pnlHeader.add(lblTitle);
        lblTitle.setBounds(74, 14, 700, 36);

        lblUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(84, 106, 112));
        lblUser.setText("ID Karyawan: -  ·  Divisi");
        pnlHeader.add(lblUser);
        lblUser.setBounds(74, 50, 650, 22);

        btnLogout.setBackground(new java.awt.Color(253, 235, 235));
        btnLogout.setForeground(new java.awt.Color(197, 48, 48));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(253, 235, 235), 1, true));
        btnLogout.setText("Keluar");
        pnlHeader.add(btnLogout);
        btnLogout.setBounds(888, 24, 124, 42);

        pnlLogo.setBackground(new java.awt.Color(15, 118, 110));
        pnlLogo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 118, 110), 1, true));
        pnlLogo.setLayout(null);
        pnlHeader.add(pnlLogo);
        pnlLogo.setBounds(28, 16, 34, 34);

        pnlRoot.add(pnlHeader);
        pnlHeader.setBounds(0, 0, 1040, 82);

        btnMenu1.setText("Menu 1");
        btnMenu1.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu1.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlRoot.add(btnMenu1);
        btnMenu1.setBounds(34, 116, 306, 92);

        btnMenu2.setText("Menu 2");
        btnMenu2.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu2.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlRoot.add(btnMenu2);
        btnMenu2.setBounds(364, 116, 306, 92);

        btnMenu3.setText("Menu 3");
        btnMenu3.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu3.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlRoot.add(btnMenu3);
        btnMenu3.setBounds(694, 116, 306, 92);

        btnMenu4.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu4.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu4.setText("Menu 4");
        btnMenu4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenu4ActionPerformed(evt);
            }
        });
        pnlRoot.add(btnMenu4);
        btnMenu4.setBounds(34, 224, 306, 92);

        btnMenu5.setText("Menu 5");
        btnMenu5.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu5.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlRoot.add(btnMenu5);
        btnMenu5.setBounds(364, 224, 306, 92);

        btnMenu6.setText("Menu 6");
        btnMenu6.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu6.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlRoot.add(btnMenu6);
        btnMenu6.setBounds(694, 224, 306, 92);

        btnMenu7.setText("Menu 7");
        btnMenu7.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu7.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlRoot.add(btnMenu7);
        btnMenu7.setBounds(34, 332, 306, 92);

        btnMenu8.setText("Menu 8");
        btnMenu8.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu8.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlRoot.add(btnMenu8);
        btnMenu8.setBounds(364, 332, 306, 92);

        btnMenu9.setText("Menu 9");
        btnMenu9.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu9.setForeground(new java.awt.Color(16, 37, 43));
        btnMenu9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnMenu9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(221, 230, 228), 1, true));
        btnMenu9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlRoot.add(btnMenu9);
        btnMenu9.setBounds(694, 332, 306, 92);

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
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenu4ActionPerformed

    public javax.swing.JButton getBtn(int i) { return btns[i]; }
    public javax.swing.JButton getBtnLogout() { return btnLogout; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenu1;
    private javax.swing.JButton btnMenu2;
    private javax.swing.JButton btnMenu3;
    private javax.swing.JButton btnMenu4;
    private javax.swing.JButton btnMenu5;
    private javax.swing.JButton btnMenu6;
    private javax.swing.JButton btnMenu7;
    private javax.swing.JButton btnMenu8;
    private javax.swing.JButton btnMenu9;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlRoot;
    // End of variables declaration//GEN-END:variables
}
