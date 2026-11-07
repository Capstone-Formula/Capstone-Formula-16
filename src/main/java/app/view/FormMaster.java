package app.view;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormMaster extends javax.swing.JFrame {

    private final javax.swing.JLabel[] labels;
    private final javax.swing.JTextField[] txtFields;
    private final javax.swing.JComboBox<String>[] cbFields;
    private final int jumlahKolom;
    private boolean readOnly;
    private DefaultTableModel tableModel;
    private javax.swing.table.TableRowSorter<DefaultTableModel> sorter;

    public FormMaster(String title, String[] columns) {

        if (title != null && title.toLowerCase(java.util.Locale.ROOT).contains("penggunaan bahan")
                && columns != null) {
            columns = java.util.Arrays.stream(columns)
                    .filter(c -> c == null || !c.trim().equalsIgnoreCase("Untuk Obat"))
                    .toArray(String[]::new);
        }
        initComponents();
        labels = new javax.swing.JLabel[]{lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8};
        txtFields = new javax.swing.JTextField[]{txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8};
        cbFields = new javax.swing.JComboBox[]{cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8};
        jumlahKolom = Math.min(columns.length, labels.length);

        setTitle("Formula - " + title);

        for (int i = 0; i < labels.length; i++) {
            boolean ada = i < jumlahKolom;
            labels[i].setVisible(ada);
            txtFields[i].setVisible(ada);
            cbFields[i].setVisible(false);
            if (ada) {
                labels[i].setText(columns[i]);
            }
        }

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblData.setModel(tableModel);
        tblData.setRowHeight(26);
        tblData.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        int totalWidth = 0;
        for (int i = 0; i < columns.length; i++) {
            javax.swing.table.TableColumn col = tblData.getColumnModel().getColumn(i);
            int textWidth = tblData.getTableHeader().getFontMetrics(tblData.getTableHeader().getFont()).stringWidth(columns[i]);
            int colWidth = Math.max(i == 0 ? 95 : 145, textWidth + 42);
            if (columns[i].toLowerCase().contains("alamat") || columns[i].toLowerCase().contains("catatan") || columns[i].toLowerCase().contains("keterangan")) {
                colWidth = Math.max(colWidth, 240);
            }
            col.setPreferredWidth(colWidth);
            col.setMinWidth(Math.min(colWidth, 90));
            totalWidth += colWidth;
        }
        tblData.getTableHeader().setReorderingAllowed(false);
        sorter = new javax.swing.table.TableRowSorter<>(tableModel);
        tblData.setRowSorter(sorter);

        txt1.setEditable(false);
        txt1.setBackground(txt1.isEditable() ? java.awt.Color.WHITE : new java.awt.Color(241, 245, 249));

        setIconImage(AppTheme.appIcon());
        pnlRoot.setBackground(AppTheme.BG_MAIN);

        AppTheme.styleHeader(pnlHeader, pnlLogo, lblTitle, title);
        AppTheme.styleButton(btnKembali, "Kembali", AppTheme.Variant.GHOST, AppTheme.Glyph.ARROW_LEFT);
        AppTheme.styleSectionTitle(lblFormTitle);
        lblFormTitle.setText("Data " + title.replaceFirst("^(Menu|Kelola|Data|Lihat)\\s+", ""));
        AppTheme.styleSectionTitle(lblTableTitle);
        lblTableTitle.setText("Daftar " + title.replaceFirst("^(Menu|Kelola|Data|Lihat)\\s+", ""));
        lblTip.setText("");

        for (int i = 0; i < labels.length; i++) {
            AppTheme.styleLabel(labels[i]);
            AppTheme.styleInput(txtFields[i]);
            AppTheme.styleCombo(cbFields[i]);
        }
        txt1.setEditable(false);
        txt1.setBackground(new java.awt.Color(241, 245, 249));

        AppTheme.styleButton(btnSimpan, "Tambah", AppTheme.Variant.PRIMARY, AppTheme.Glyph.PLUS);
        AppTheme.styleButton(btnUpdate, "Ubah Data", AppTheme.Variant.INK, AppTheme.Glyph.EDIT);
        AppTheme.styleButton(btnHapus, "Hapus", AppTheme.Variant.DANGER, AppTheme.Glyph.TRASH);
        AppTheme.styleButton(btnBersihkan, "Kosongkan", AppTheme.Variant.GHOST, AppTheme.Glyph.ERASE);
        AppTheme.styleButton(btnCetak, "Cetak PDF", AppTheme.Variant.GHOST, AppTheme.Glyph.PRINT);
        AppTheme.styleSearch(txtCari, "Cari");
        AppTheme.styleTable(tblData);
        if (totalWidth < 960 && columns.length > 0) {
            tblData.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        }

        btnLihatBahan.setVisible(false);
        AppTheme.cardPanel(pnlCard1);
        AppTheme.cardPanel(pnlCard2);

        txtCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        });

        tblData.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) isiFieldDariTabel();
        });

        btnBersihkan.addActionListener(e -> {
            resetFields();
            tblData.clearSelection();
        });
        btnCetak.setVisible(title.contains("Pesanan") || title.contains("Pengiriman") || title.contains("Produksi") || title.contains("Obat") || title.contains("Bahan") || title.contains("Batch"));
        btnCetak.addActionListener(e -> {
            if (tblData.isEditing()) tblData.getCellEditor().stopCellEditing();
            PdfExporter.exportTable(this, tblData, lblTableTitle.getText());
        });

        pack();
        setLocationRelativeTo(null);
    }

    public void aktifkanPopupBahanProduksi(app.model.AlurDAO dao) {
        // Data tetap dibaca dari detail_penggunaan_bahan; tidak disimpan dalam textfield panjang.
        txt7.setVisible(false);
        AppTheme.styleButton(btnLihatBahan, "Lihat Bahan Baku", AppTheme.Variant.GHOST, AppTheme.Glyph.SEARCH);
        btnLihatBahan.setVisible(true);
        btnLihatBahan.addActionListener(e -> {
            String nomor = txt1.getText().trim();
            if (nomor.isEmpty()) { msg("Pilih batch produksi dari tabel."); return; }
            try {
                int batch = Integer.parseInt(nomor);
                java.util.List<Object[]> baris = dao.bahanDalamBatch(batch);
                javax.swing.table.DefaultTableModel tm = new javax.swing.table.DefaultTableModel(
                    new Object[]{"Nama Bahan", "Jumlah", "Satuan"}, 0) {
                    @Override public boolean isCellEditable(int r, int c) { return false; }
                };
                for (Object[] item : baris) tm.addRow(item);
                javax.swing.JTable tabel = new javax.swing.JTable(tm);
                AppTheme.styleTable(tabel);
                javax.swing.JScrollPane pane = new javax.swing.JScrollPane(tabel);
                pane.setPreferredSize(new java.awt.Dimension(540, 260));
                if (baris.isEmpty()) JOptionPane.showMessageDialog(this, "Belum ada penggunaan bahan pada batch " + batch + ".", "Bahan Baku", JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(this, pane, "Penggunaan Bahan - Batch " + batch, JOptionPane.PLAIN_MESSAGE);
            } catch (NumberFormatException ex) { msg("Nomor batch tidak valid."); }
            catch (Exception ex) { msg("Gagal memuat penggunaan bahan: " + ex.getMessage()); }
        });
    }

    public void setDropdown(int fieldIndex0Based, java.util.List<String> items) {
        if (fieldIndex0Based < 0 || fieldIndex0Based >= cbFields.length) return;
        javax.swing.JComboBox<String> cb = cbFields[fieldIndex0Based];
        javax.swing.JTextField txt = txtFields[fieldIndex0Based];
        cb.removeAllItems();
        for (String item : items) {
            cb.addItem(item);
        }
        txt.setVisible(readOnly);
        cb.setVisible(!readOnly);
        cb.setEnabled(!readOnly);
        pnlRoot.revalidate();
        pnlRoot.repaint();
    }

    public void setDropdown(int fieldIndex0Based, String[] items) {
        setDropdown(fieldIndex0Based, java.util.Arrays.asList(items));
    }

    // Menghubungkan pilihan JComboBox yang sudah ada di GUI Builder ke
    // tampilan informasi otomatis tanpa membuat komponen visual baru.
    public void onDropdownChange(int fieldIndex0Based, Runnable callback) {
        if (fieldIndex0Based < 0 || fieldIndex0Based >= cbFields.length || callback == null) return;
        cbFields[fieldIndex0Based].addActionListener(e -> callback.run());
    }

    public void setDropdownEnabled(int fieldIndex0Based, boolean enabled) {
        if (fieldIndex0Based >= 0 && fieldIndex0Based < cbFields.length && cbFields[fieldIndex0Based] != null) {
            cbFields[fieldIndex0Based].setEnabled(enabled && !readOnly);
        }
    }

    public void selectComboById(int fieldIndex0Based, int id) {
        if (fieldIndex0Based < 0 || fieldIndex0Based >= cbFields.length) return;
        javax.swing.JComboBox<String> cb = cbFields[fieldIndex0Based];
        if (cb == null) return;
        String prefix = id + " - ";
        for (int i = 0; i < cb.getItemCount(); i++) {
            String item = cb.getItemAt(i);
            if (item.startsWith(prefix) || item.equals(String.valueOf(id))) {
                cb.setSelectedIndex(i);
                return;
            }
        }
    }

    public void setFieldEditable(int fieldIndex0Based, boolean editable) {
        if (fieldIndex0Based >= 0 && fieldIndex0Based < txtFields.length) {
            txtFields[fieldIndex0Based].setEditable(editable && !readOnly);
            cbFields[fieldIndex0Based].setEnabled(editable && !readOnly);
            txtFields[fieldIndex0Based].setBackground(editable ? java.awt.Color.WHITE : new java.awt.Color(241, 245, 249));
        }
    }

    public void setFieldValue(int fieldIndex0Based, String val) {
        if (fieldIndex0Based >= 0 && fieldIndex0Based < txtFields.length) {
            txtFields[fieldIndex0Based].setText(val == null ? "" : val);
            if (cbFields[fieldIndex0Based] != null && cbFields[fieldIndex0Based].isVisible()) {
                selectComboMatching(cbFields[fieldIndex0Based], val);
            }
        }
    }

    private void filter() {
        if (sorter != null) {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(txtCari.getText().trim())));
        }
    }

    private void isiFieldDariTabel() {
        int r = tblData.getSelectedRow();
        if (r == -1) return;
        r = tblData.convertRowIndexToModel(r);
        for (int i = 0; i < jumlahKolom; i++) {
            Object v = tableModel.getValueAt(r, i);
            String valStr = v == null ? "" : v.toString();
            txtFields[i].setText(valStr);
            if (cbFields[i] != null && cbFields[i].isVisible()) {
                if (cbFields[i].isEnabled()) {
                    selectComboMatching(cbFields[i], valStr);
                }
            }
        }
    }

    private void selectComboMatching(javax.swing.JComboBox<String> cb, String val) {
        if (val == null || val.trim().isEmpty()) { cb.setSelectedIndex(-1); return; }
        String valTrim = val.trim();
        String id = valTrim.contains(" - ") ? valTrim.substring(0, valTrim.indexOf(" - ")).trim() : valTrim;
        for (int k = 0; k < cb.getItemCount(); k++) {
            String item = cb.getItemAt(k);
            if (item.equalsIgnoreCase(valTrim) || item.startsWith(id + " - ")) {
                cb.setSelectedIndex(k);
                return;
            }
        }
        cb.setSelectedIndex(-1);
    }

    @SuppressWarnings("unchecked")
//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRoot = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        pnlLogo = new javax.swing.JPanel();
        lblFormTitle = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        cb1 = new javax.swing.JComboBox<>();
        cb1.setVisible(false);
        lbl2 = new javax.swing.JLabel();
        txt2 = new javax.swing.JTextField();
        cb2 = new javax.swing.JComboBox<>();
        cb2.setVisible(false);
        lbl3 = new javax.swing.JLabel();
        txt3 = new javax.swing.JTextField();
        cb3 = new javax.swing.JComboBox<>();
        cb3.setVisible(false);
        lbl4 = new javax.swing.JLabel();
        txt4 = new javax.swing.JTextField();
        cb4 = new javax.swing.JComboBox<>();
        cb4.setVisible(false);
        lbl5 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        cb5 = new javax.swing.JComboBox<>();
        cb5.setVisible(false);
        lbl6 = new javax.swing.JLabel();
        txt6 = new javax.swing.JTextField();
        cb6 = new javax.swing.JComboBox<>();
        cb6.setVisible(false);
        lbl7 = new javax.swing.JLabel();
        txt7 = new javax.swing.JTextField();
        btnLihatBahan = new javax.swing.JButton();
        cb7 = new javax.swing.JComboBox<>();
        cb7.setVisible(false);
        lbl8 = new javax.swing.JLabel();
        txt8 = new javax.swing.JTextField();
        cb8 = new javax.swing.JComboBox<>();
        cb8.setVisible(false);
        btnSimpan = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBersihkan = new javax.swing.JButton();
        btnCetak = new javax.swing.JButton();
        lblTableTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        lblTip = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        pnlCard1 = new javax.swing.JPanel();
        pnlCard2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Formula - Master Data");
        setResizable(false);

        pnlRoot.setBackground(new java.awt.Color(242, 246, 245));
        pnlRoot.setPreferredSize(new java.awt.Dimension(1040, 740));
        pnlRoot.setLayout(null);

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(null);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); 
        lblTitle.setText("Formula  /  Data Farmasi");
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

        lblFormTitle.setFont(new java.awt.Font("Segoe UI", 1, 17)); 
        lblFormTitle.setText("Formulir Entri Data");
        lblFormTitle.setForeground(new java.awt.Color(16, 37, 43));
        pnlRoot.add(lblFormTitle);
        lblFormTitle.setBounds(48, 106, 500, 24);

        lbl1.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lbl1.setText("Field 1");
        lbl1.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lbl1);
        lbl1.setBounds(48, 146, 224, 18);

        txt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txt1.setBackground(new java.awt.Color(255, 255, 255));
        txt1.setForeground(new java.awt.Color(16, 37, 43));
        txt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txt1);
        txt1.setBounds(48, 167, 224, 38);

        cb1.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cb1.setBackground(new java.awt.Color(255, 255, 255));
        cb1.setForeground(new java.awt.Color(16, 37, 43));
        cb1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cb1);
        cb1.setBounds(48, 167, 224, 38);

        lbl2.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lbl2.setText("Field 2");
        lbl2.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lbl2);
        lbl2.setBounds(288, 146, 224, 18);

        txt2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txt2.setBackground(new java.awt.Color(255, 255, 255));
        txt2.setForeground(new java.awt.Color(16, 37, 43));
        txt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txt2);
        txt2.setBounds(288, 167, 224, 38);

        cb2.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cb2.setBackground(new java.awt.Color(255, 255, 255));
        cb2.setForeground(new java.awt.Color(16, 37, 43));
        cb2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cb2);
        cb2.setBounds(288, 167, 224, 38);

        lbl3.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lbl3.setText("Field 3");
        lbl3.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lbl3);
        lbl3.setBounds(528, 146, 224, 18);

        txt3.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txt3.setBackground(new java.awt.Color(255, 255, 255));
        txt3.setForeground(new java.awt.Color(16, 37, 43));
        txt3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txt3);
        txt3.setBounds(528, 167, 224, 38);

        cb3.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cb3.setBackground(new java.awt.Color(255, 255, 255));
        cb3.setForeground(new java.awt.Color(16, 37, 43));
        cb3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cb3);
        cb3.setBounds(528, 167, 224, 38);

        lbl4.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lbl4.setText("Field 4");
        lbl4.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lbl4);
        lbl4.setBounds(768, 146, 224, 18);

        txt4.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txt4.setBackground(new java.awt.Color(255, 255, 255));
        txt4.setForeground(new java.awt.Color(16, 37, 43));
        txt4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txt4);
        txt4.setBounds(768, 167, 224, 38);

        cb4.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cb4.setBackground(new java.awt.Color(255, 255, 255));
        cb4.setForeground(new java.awt.Color(16, 37, 43));
        cb4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cb4);
        cb4.setBounds(768, 167, 224, 38);

        lbl5.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lbl5.setText("Field 5");
        lbl5.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lbl5);
        lbl5.setBounds(48, 230, 224, 18);

        txt5.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txt5.setBackground(new java.awt.Color(255, 255, 255));
        txt5.setForeground(new java.awt.Color(16, 37, 43));
        txt5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txt5);
        txt5.setBounds(48, 251, 224, 38);

        cb5.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cb5.setBackground(new java.awt.Color(255, 255, 255));
        cb5.setForeground(new java.awt.Color(16, 37, 43));
        cb5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cb5);
        cb5.setBounds(48, 251, 224, 38);

        lbl6.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lbl6.setText("Field 6");
        lbl6.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lbl6);
        lbl6.setBounds(288, 230, 224, 18);

        txt6.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txt6.setBackground(new java.awt.Color(255, 255, 255));
        txt6.setForeground(new java.awt.Color(16, 37, 43));
        txt6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txt6);
        txt6.setBounds(288, 251, 224, 38);

        cb6.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cb6.setBackground(new java.awt.Color(255, 255, 255));
        cb6.setForeground(new java.awt.Color(16, 37, 43));
        cb6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cb6);
        cb6.setBounds(288, 251, 224, 38);

        lbl7.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lbl7.setText("Field 7");
        lbl7.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lbl7);
        lbl7.setBounds(528, 230, 224, 18);

        txt7.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txt7.setBackground(new java.awt.Color(255, 255, 255));
        txt7.setForeground(new java.awt.Color(16, 37, 43));
        txt7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txt7);
        txt7.setBounds(528, 251, 224, 38);

        btnLihatBahan.setText("Lihat Bahan Baku");
        btnLihatBahan.setVisible(false);
        pnlRoot.add(btnLihatBahan);
        btnLihatBahan.setBounds(528, 251, 224, 38);

        cb7.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cb7.setBackground(new java.awt.Color(255, 255, 255));
        cb7.setForeground(new java.awt.Color(16, 37, 43));
        cb7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cb7);
        cb7.setBounds(528, 251, 224, 38);

        lbl8.setFont(new java.awt.Font("Segoe UI", 1, 13)); 
        lbl8.setText("Field 8");
        lbl8.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lbl8);
        lbl8.setBounds(768, 230, 224, 18);

        txt8.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txt8.setBackground(new java.awt.Color(255, 255, 255));
        txt8.setForeground(new java.awt.Color(16, 37, 43));
        txt8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txt8);
        txt8.setBounds(768, 251, 224, 38);

        cb8.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        cb8.setBackground(new java.awt.Color(255, 255, 255));
        cb8.setForeground(new java.awt.Color(16, 37, 43));
        cb8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(cb8);
        cb8.setBounds(768, 251, 224, 38);

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnSimpan.setText("Tambah");
        btnSimpan.setBackground(new java.awt.Color(15, 118, 110));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 118, 110), 1, true));
        pnlRoot.add(btnSimpan);
        btnSimpan.setBounds(48, 326, 124, 42);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnUpdate.setText("Ubah Data");
        btnUpdate.setBackground(new java.awt.Color(22, 54, 62));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(22, 54, 62), 1, true));
        pnlRoot.add(btnUpdate);
        btnUpdate.setBounds(180, 326, 140, 42);

        btnHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnHapus.setText("Hapus");
        btnHapus.setBackground(new java.awt.Color(253, 235, 235));
        btnHapus.setForeground(new java.awt.Color(197, 48, 48));
        btnHapus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(253, 235, 235), 1, true));
        pnlRoot.add(btnHapus);
        btnHapus.setBounds(328, 326, 112, 42);

        btnBersihkan.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnBersihkan.setText("Kosongkan");
        btnBersihkan.setBackground(new java.awt.Color(255, 255, 255));
        btnBersihkan.setForeground(new java.awt.Color(16, 37, 43));
        btnBersihkan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(btnBersihkan);
        btnBersihkan.setBounds(448, 326, 156, 42);

        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        btnCetak.setText("Cetak PDF");
        btnCetak.setBackground(new java.awt.Color(255, 255, 255));
        btnCetak.setForeground(new java.awt.Color(16, 37, 43));
        btnCetak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(btnCetak);
        btnCetak.setBounds(852, 326, 140, 42);

        lblTableTitle.setFont(new java.awt.Font("Segoe UI", 1, 17)); 
        lblTableTitle.setText("Daftar Data");
        lblTableTitle.setForeground(new java.awt.Color(16, 37, 43));
        pnlRoot.add(lblTableTitle);
        lblTableTitle.setBounds(48, 418, 420, 24);

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
        
            },
            new String [] {
        
            }
        ));
        jScrollPane1.setViewportView(tblData);

        pnlRoot.add(jScrollPane1);
        jScrollPane1.setBounds(36, 460, 968, 240);

        lblTip.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
        lblTip.setText("");
        lblTip.setForeground(new java.awt.Color(84, 106, 112));
        pnlRoot.add(lblTip);
        lblTip.setBounds(388, 418, 322, 24);

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        txtCari.setBackground(new java.awt.Color(255, 255, 255));
        txtCari.setForeground(new java.awt.Color(16, 37, 43));
        txtCari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(207, 219, 217), 1, true));
        pnlRoot.add(txtCari);
        txtCari.setBounds(722, 412, 270, 38);

        pnlCard1.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 230, 228), 1, true));
        pnlCard1.setLayout(null);

        pnlRoot.add(pnlCard1);
        pnlCard1.setBounds(18, 86, 1004, 306);

        pnlCard2.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(221, 230, 228), 1, true));
        pnlCard2.setLayout(null);

        pnlRoot.add(pnlCard2);
        pnlCard2.setBounds(18, 396, 1004, 330);

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

    public void setReadOnlyMode() {
        readOnly = true;
        btnSimpan.setVisible(false);
        btnUpdate.setVisible(false);
        btnHapus.setVisible(false);
        btnBersihkan.setVisible(false);
        for (javax.swing.JTextField field : txtFields) field.setEditable(false);
        for (int i = 0; i < cbFields.length; i++) {
            if (cbFields[i].isVisible()) {
                cbFields[i].setVisible(false);
                txtFields[i].setVisible(i < jumlahKolom);
            }
            cbFields[i].setEnabled(false);
        }
        pnlRoot.revalidate();
        pnlRoot.repaint();
    }

    public DefaultTableModel getModel() { return tableModel; }

    public String[] getInputData() {
        String[] data = new String[txtFields.length];
        for (int i = 0; i < txtFields.length; i++) {
            if (cbFields[i] != null && cbFields[i].isVisible()) {
                Object sel = cbFields[i].getSelectedItem();
                data[i] = sel == null ? "" : sel.toString().trim();
            } else {
                data[i] = txtFields[i].getText().trim();
            }
        }
        return data;
    }

    public void resetFields() {
        tblData.clearSelection();
        for (javax.swing.JTextField t : txtFields) t.setText("");
        for (javax.swing.JComboBox<String> cb : cbFields) {
            if (cb != null && cb.getItemCount() > 0) cb.setSelectedIndex(0);
        }
    }

    public void setLoadStatus(String message, boolean error) {
        AppTheme.statusLine(lblTip, message, error);
    }

    public javax.swing.JButton getBtnSimpan() { return btnSimpan; }
    public javax.swing.JButton getBtnUpdate() { return btnUpdate; }
    public javax.swing.JButton getBtnHapus() { return btnHapus; }
    public javax.swing.JButton getBtnKembali() { return btnKembali; }
    public void msg(String m) { JOptionPane.showMessageDialog(this, m); }

//GEN-BEGIN:variables
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnLihatBahan;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JComboBox<String> cb2;
    private javax.swing.JComboBox<String> cb3;
    private javax.swing.JComboBox<String> cb4;
    private javax.swing.JComboBox<String> cb5;
    private javax.swing.JComboBox<String> cb6;
    private javax.swing.JComboBox<String> cb7;
    private javax.swing.JComboBox<String> cb8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lblFormTitle;
    private javax.swing.JLabel lblTableTitle;
    private javax.swing.JLabel lblTip;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlCard1;
    private javax.swing.JPanel pnlCard2;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlRoot;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    private javax.swing.JTextField txt7;
    private javax.swing.JTextField txt8;
    private javax.swing.JTextField txtCari;
//GEN-END:variables
}
