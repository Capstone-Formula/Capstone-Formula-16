package app.controller;

import app.model.MasterDAO;
import app.view.*;
import java.util.List;

public class MasterController {
    private FormMaster v;
    private MasterDAO m;
    private String tipe;
    private final app.model.AlurDAO alur = new app.model.AlurDAO();
    private int idLogin = -1;

    public MasterController(FormMaster v, MasterDAO m, String tipe, FormDashboard fd) {
        this(v, m, tipe, fd, -1);
    }

    public MasterController(FormMaster v, MasterDAO m, String tipe, FormDashboard fd, int idLogin) {
        this.idLogin = idLogin;
        this.v = v;
        this.m = m;
        this.tipe = tipe;

        if ("Obat".equals(tipe)) {
            v.setDropdown(2, new String[]{"Obat Bebas", "Obat Bebas Terbatas", "Obat Keras", "Obat Narkotika", "Fitofarmaka", "Obat Herbal Terstandar", "Jamu"});
            v.setDropdown(3, new String[]{"Tersedia", "Tidak Tersedia"});
        } else if ("Bahan".equals(tipe)) {
            v.setDropdown(3, new String[]{"gram", "ml", "Kg", "Liter"});
        } else if ("Instansi".equals(tipe)) {
            v.setDropdown(2, new String[]{"Klinik", "Apotek", "Rumah Sakit"});
        } else if ("Pesanan".equals(tipe)) {
            v.setDropdown(2, new String[]{"Menunggu", "Selesai", "Batal"});
            v.setDropdown(4, m.getInstansiOptions());
            v.setDropdown(5, m.getKaryawanOptions());
            if (idLogin > 0) {
                v.selectComboById(5, idLogin);
                v.setDropdownEnabled(5, false);
            }
        } else if ("Pengiriman".equals(tipe)) {
            v.setDropdown(2, new String[]{"Menunggu", "Diproses", "Selesai", "Batal"});
            v.setDropdown(4, m.getPesananOptions());
            v.setDropdown(5, m.getKaryawanOptions());
            if (idLogin > 0) {
                v.selectComboById(5, idLogin);
                v.setDropdownEnabled(5, false);
            }
        } else if ("Karyawan".equals(tipe)) {
            v.setDropdown(3, new String[]{"Aktif", "Nonaktif"});
            v.setDropdown(4, new String[]{"Siang", "Malam"});
        }

        if ("Pesanan".equals(tipe)) {
            for (int i : new int[]{1,5}) v.setFieldEditable(i, false);
        } else if ("Pengiriman".equals(tipe)) {
            for (int i : new int[]{1,5}) v.setFieldEditable(i, false);
        }
        loadTable();
        
        v.getBtnSimpan().addActionListener(e -> {
            String[] d = v.getInputData();
            boolean res = false;
            if (!validasi(d, true)) return;
            try {
                if (tipe.equals("Obat")) {
                    res = m.simpanObat(d[1].trim(), d[2].trim(), d[3].trim(), idLogin);
                } else if (tipe.equals("Bahan")) {
                    res = m.simpanBahan(d[1].trim(), d[2].trim(), d[3].trim(), idLogin);
                } else if (tipe.equals("Karyawan")) {
                    res = m.simpanKaryawan(d[1].trim(), d[2].trim(), d[3].trim(), d[4].trim());
                } else if (tipe.equals("Instansi")) {
                    res = m.simpanInstansi(d[1].trim(), d[2].trim(), d[3].trim(), d[4].trim());
                } else if (tipe.equals("Pesanan")) {
                    int idInstansi = parseId(d[4], -1);
                    int idPetugas = idLogin > 0 ? idLogin : parseId(d.length > 5 ? d[5] : "", 1);
                    int idBaru = alur.pesanBaru(idInstansi, idPetugas, d[3].trim());
                    res = true;
                    v.msg("Pesanan baru berhasil disimpan dengan ID #" + idBaru + ".");
                } else if (tipe.equals("Pengiriman")) {
                    int pesanan = parseId(d[4], -1);
                    int idPetugas = idLogin > 0 ? idLogin : parseId(d.length > 5 ? d[5] : "", 1);
                    int resi = alur.buatPengiriman(pesanan, idPetugas, d[2].trim(), d[3].trim());
                    res = true;
                    v.msg("Pengiriman berhasil disimpan dengan Nomor Resi #" + resi + ".");
                }

                if (res) {
                    if (!tipe.equals("Pesanan") && !tipe.equals("Pengiriman")) {
                        v.msg("Data berhasil ditambahkan.");
                    }
                    resetInput();
                    loadTable();
                } else {
                    v.msg("Data tidak dapat ditambahkan. Periksa kembali isian Anda.");
                }
            } catch (Exception ex) {
                v.msg("Gagal menambahkan data: " + akar(ex));
            }
        });
        
        v.getBtnUpdate().addActionListener(e -> {
            String[] d = v.getInputData();
            boolean res = false;
            try {
                if (d[0].isEmpty()) {
                    v.msg("Pilih data dari tabel terlebih dahulu.");
                    return;
                }
                if (!validasi(d, false)) return;
                int idItem = parseId(d[0], -1);
                if (tipe.equals("Obat")) res = m.updateObat(d[0], d[1].trim(), d[2].trim(), d[3].trim());
                else if (tipe.equals("Bahan")) res = m.updateBahan(d[0], d[1].trim(), d[2].trim(), d[3].trim());
                else if (tipe.equals("Karyawan")) res = m.updateKaryawan(d[0], d[1].trim(), d[2].trim(), d[3].trim(), d[4].trim());
                else if (tipe.equals("Instansi")) res = m.updateInstansi(d[0], d[1].trim(), d[2].trim(), d[3].trim(), d[4].trim());
                else if (tipe.equals("Pesanan")) {
                    alur.ubahPesanan(idItem, d[2].trim(), d[3].trim(), parseId(d[4], -1));
                    res = true;
                } else if (tipe.equals("Pengiriman")) {
                    res = m.updatePengiriman(d[0], d[1], d[2].trim(), d[3].trim(), d[4], d[5]);
                }

                if (res) {
                    v.msg("Data berhasil diperbarui.");
                    resetInput();
                    loadTable();
                } else {
                    v.msg("Gagal memperbarui data.");
                }
            } catch (Exception ex) {
                v.msg("Gagal memperbarui data: " + akar(ex));
            }
        });
        
        v.getBtnHapus().addActionListener(e -> {
            String[] d = v.getInputData();
            boolean res = false;
            if (d[0].isEmpty()) {
                v.msg("Pilih data dari tabel terlebih dahulu.");
                return;
            }
            int idItem = parseId(d[0], -1);
            int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(v,
                "Apakah Anda yakin ingin menghapus data ini?",
                "Konfirmasi Hapus", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
            if (konfirmasi != javax.swing.JOptionPane.YES_OPTION) return;
            try {
                if (tipe.equals("Obat")) res = m.hapusObat(d[0]);
                else if (tipe.equals("Bahan")) res = m.hapusBahan(d[0]);
                else if (tipe.equals("Karyawan")) res = m.hapusKaryawan(d[0]);
                else if (tipe.equals("Instansi")) res = m.hapusInstansi(d[0]);
                else if (tipe.equals("Pesanan")) { alur.hapusPesanan(idItem); res = true; }
                else if (tipe.equals("Pengiriman")) { alur.hapusPengiriman(idItem); res = true; }

                if (res) {
                    v.msg("Data berhasil dihapus.");
                    resetInput();
                    loadTable();
                } else {
                    v.msg("Data tidak dapat dihapus karena masih digunakan pada data lain.");
                }
            } catch (Exception ex) {
                v.msg("Gagal menghapus data: " + akar(ex));
            }
        });
        

        v.getBtnKembali().addActionListener(e -> {
            fd.setVisible(true);
            v.dispose();
        });
    }

    private void resetInput() {
        v.resetFields();
        if (("Pesanan".equals(tipe) || "Pengiriman".equals(tipe)) && idLogin > 0) {
            v.selectComboById(5, idLogin);
            v.setDropdownEnabled(5, false);
        }
    }

    private int parseId(String val, int fallback) {
        if (val == null || val.isBlank()) return fallback;
        String s = val.trim();
        if (s.contains(" - ")) s = s.substring(0, s.indexOf(" - ")).trim();
        java.util.regex.Matcher mat = java.util.regex.Pattern.compile("^\\d+").matcher(s);
        if (mat.find()) {
            try { return Integer.parseInt(s); } catch (Exception ignored) {}
        }
        return fallback;
    }
    
    private boolean validasi(String[] d, boolean isNew) {
        if ("Pesanan".equals(tipe)) {
            if (isNew && parseId(d[4], -1) <= 0) {
                v.msg("Pilih instansi kesehatan.");
                return false;
            }
            if (!isNew && !java.util.List.of("Menunggu", "Selesai", "Batal").contains(d[2])) {
                v.msg("Pilih status pesanan: Menunggu, Selesai, atau Batal.");
                return false;
            }
            return true;
        }
        if ("Pengiriman".equals(tipe)) {
            if (isNew && parseId(d[4], -1) <= 0) {
                v.msg("Pilih pesanan. Nomor resi dibuat otomatis.");
                return false;
            }
            if (!java.util.List.of("Menunggu", "Diproses", "Selesai", "Batal").contains(d[2])) {
                v.msg("Pilih status pengiriman: Menunggu, Diproses, Selesai, atau Batal.");
                return false;
            }
            return true;
        }
        if ("Bahan".equals(tipe)) {
            if (d[1].isBlank() || !d[2].trim().matches("\\d+")) {
                v.msg("Nama bahan dan jumlah stok wajib diisi.");
                return false;
            }
            if (d[3].isBlank()) {
                v.msg("Satuan bahan wajib diisi.");
                return false;
            }
            return true;
        }
        if ("Obat".equals(tipe)) {
            if (d[1].isBlank() || d[2].isBlank() || d[3].isBlank()) {
                v.msg("Nama obat, jenis obat, dan status ketersediaan wajib diisi.");
                return false;
            }
            return true;
        }
        if ("Instansi".equals(tipe)) {
            if (d[1].isBlank()) {
                v.msg("Nama instansi kesehatan wajib diisi.");
                return false;
            }
            if (d[2].isBlank()) {
                v.msg("Pilih jenis instansi.");
                return false;
            }
            if (d[3].isBlank()) {
                v.msg("Alamat instansi wajib diisi.");
                return false;
            }
            if (d[4].isBlank()) {
                v.msg("Nomor kontak instansi wajib diisi.");
                return false;
            }
            return true;
        }
        return true;
    }

    private void loadTable() {
        v.setLoadStatus("Memuat data " + tipe.toLowerCase() + "...", false);
        new javax.swing.SwingWorker<java.util.List<Object[]>, Void>() {
            @Override protected java.util.List<Object[]> doInBackground() {
                switch (tipe) {
                    case "Obat": return m.getAllObat();
                    case "Bahan": return m.getAllBahan();
                    case "Karyawan": return m.getAllKaryawan();
                    case "Instansi": return m.getAllInstansi();
                    case "Pesanan": return m.getAllPesanan();
                    case "Pengiriman": return m.getAllPengiriman();
                    case "DetailProduksi": return m.getAllDetailProduksi();
                    case "Produksi": return m.getAllProduksi();
                    default: throw new IllegalStateException("Tipe data tidak ditemukan: " + tipe);
                }
            }
            @Override protected void done() {
                try {
                    java.util.List<Object[]> rows = get();
                    v.getModel().setRowCount(0);
                    for (Object[] row : rows) v.getModel().addRow(row);
                    v.setLoadStatus("Data " + tipe.toLowerCase() + " berhasil dimuat.", false);
                } catch (Exception ex) {
                    v.setLoadStatus("Gagal memuat " + tipe.toLowerCase() + ": " + akar(ex), true);
                }
            }
        }.execute();
    }

    private static String akar(Throwable t) {
        while (t.getCause() != null) t = t.getCause();
        return t.getMessage() == null ? t.getClass().getSimpleName() : t.getMessage();
    }
}
