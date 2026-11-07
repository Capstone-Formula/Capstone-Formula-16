package app.controller;

import app.model.AlurDAO;
import app.model.MasterDAO;
import app.view.FormDashboard;
import app.view.FormMaster;
import java.util.List;
import javax.swing.JOptionPane;

public final class AlurController {
    private final AlurDAO dao = new AlurDAO();
    private final MasterDAO masterDao = new MasterDAO();
    private final FormMaster view;
    private final String jenis;

    public AlurController(FormMaster view, String jenis, FormDashboard dashboard) {
        this.view = view;
        this.jenis = jenis;
        view.getBtnKembali().addActionListener(e -> {
            dashboard.setVisible(true);
            view.dispose();
        });

        if ("Batch".equals(jenis)) {
            view.setReadOnlyMode();
        }
        if ("Produksi".equals(jenis)) {
            view.getBtnSimpan().setVisible(false); 
        }

        if ("Penggunaan".equals(jenis)) {
            view.setDropdown(1, masterDao.getBahanOptions());
            view.setDropdown(3, new String[]{"gram", "ml", "Kg", "Liter"});
            view.setDropdown(4, masterDao.getBatchOptions());
        } else if ("DetailPesanan".equals(jenis)) {
            view.setDropdown(1, masterDao.getPesananOptionsBatch());
            view.setDropdown(2, masterDao.getBatchOptionsPesanan());
            view.setFieldEditable(3, false); // Jumlah obat hanya informasi, bukan input.
            view.onDropdownChange(2, this::tampilkanJumlahObatBatchDipilih);
            tampilkanJumlahObatBatchDipilih();
            view.getBtnUpdate().setVisible(false); // Satu batch dialokasikan utuh.
        }

        if ("Produksi".equals(jenis)) {
            view.aktifkanPopupBahanProduksi(dao);
            view.setDropdown(3, new String[]{"Strip", "Botol"});
            for (int i : new int[]{1,4,5,6,7,8}) view.setFieldEditable(i, false);
        }
        refresh();
        view.getBtnSimpan().addActionListener(e -> aksi("tambah"));
        view.getBtnUpdate().addActionListener(e -> aksi("ubah"));
        view.getBtnHapus().addActionListener(e -> aksi("hapus"));
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

    private void tampilkanJumlahObatBatchDipilih() {
        if (!"DetailPesanan".equals(jenis)) return;
        String pilihan = view.getInputData()[2];
        // Pilihan batch dari MasterDAO memuat stok TERSEDIA saat ini, misalnya:
        // "17 - Batch #17 (Nama Obat - Stok: 50 Strip)".
        java.util.regex.Matcher stok = java.util.regex.Pattern
                .compile("Stok:\\s*(\\d+)", java.util.regex.Pattern.CASE_INSENSITIVE)
                .matcher(pilihan);
        view.setFieldValue(3, stok.find() ? stok.group(1) : "");
    }

    private void refresh() {
        view.setLoadStatus("Memuat data " + jenis.toLowerCase() + "...", false);
        new javax.swing.SwingWorker<java.util.List<Object[]>, Void>() {
            @Override protected java.util.List<Object[]> doInBackground() {
                switch (jenis) {
                    case "DetailPesanan": return dao.detailPesanan();
                    case "Produksi": return dao.produksi();
                    case "Batch": return dao.semuaBatch();
                    case "Penggunaan": return dao.penggunaan();
                    default: throw new IllegalStateException("Modul tidak dikenal: " + jenis);
                }
            }
            @Override protected void done() {
                try {
                    java.util.List<Object[]> data = get();
                    view.getModel().setRowCount(0);
                    for (Object[] r : data) view.getModel().addRow(r);
                    String nama = "Batch".equals(jenis) ? "stok per batch" : jenis.toLowerCase();
                    view.setLoadStatus(data.isEmpty() ? "Belum ada data " + nama + " di database."
                            : "Menampilkan " + data.size() + " baris " + nama + ".", false);
                } catch (Exception ex) {
                    String pesan = "Gagal memuat " + jenis.toLowerCase() + ": " + akar(ex);
                    view.setLoadStatus(pesan, true);
                    javax.swing.JOptionPane.showMessageDialog(view, pesan, "Gagal Mengambil Data", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    // Setelah transaksi stok, muat ulang pilihan dari database agar angka stok tidak tertinggal.
    private void perbaruiPilihanBatch() {
        try {
            if ("DetailPesanan".equals(jenis)) {
                view.setDropdown(1, masterDao.getPesananOptionsBatch());
                view.setDropdown(2, masterDao.getBatchOptionsPesanan());
            } else if ("Penggunaan".equals(jenis)) {
                view.setDropdown(4, masterDao.getBatchOptions());
            }
        } catch (RuntimeException ex) {
            // Transaksi sudah COMMIT: kegagalan memuat ulang daftar tidak boleh
            // dilaporkan sebagai transaksi gagal karena bisa memicu input ganda.
            view.msg("Transaksi tersimpan, tetapi daftar batch gagal diperbarui. "
                    + "Buka kembali menu untuk melihat stok terbaru.");
        }
    }

    private static String akar(Throwable t) {
        while (t.getCause() != null) t = t.getCause();
        return t.getMessage() == null ? t.getClass().getSimpleName() : t.getMessage();
    }

    private void aksi(String aksi) {
        String[] d = view.getInputData();
        try {
            switch (jenis) {
                case "Penggunaan":
                    if (aksi.equals("tambah")) {
                        int idBahan = parseId(d[1], -1);
                        int jumlah = parseId(d[2], 0);
                        String satuan = d[3].trim();
                        int batch = parseId(d[4], -1);
                        if (idBahan <= 0 || jumlah <= 0 || batch <= 0) {
                            view.msg("Pilih bahan baku, masukkan jumlah bahan terpotong, dan pilih nomor batch.");
                            return;
                        }
                        dao.catatPenggunaanBahan(idBahan, jumlah, satuan, batch);
                        view.msg("Penggunaan bahan berhasil disimpan dan stok otomatis dipotong.");
                    } else if (aksi.equals("ubah")) {
                        if (d[0].isBlank()) { view.msg("Pilih catatan penggunaan bahan."); return; }
                        dao.ubahPenggunaanBahan(parseId(d[0], -1), parseId(d[1], -1), parseId(d[2], 0), d[3].trim(), parseId(d[4], -1));
                        view.msg("Penggunaan bahan berhasil diperbarui.");
                    } else if (aksi.equals("hapus")) {
                        if (d[0].isBlank()) { view.msg("Pilih data penggunaan bahan yang akan dihapus."); return; }
                        if (!konfirmasi("Hapus data penggunaan ini? Stok bahan baku akan dikembalikan.")) return;
                        dao.hapusPenggunaanBahan(parseId(d[0], -1));
                        view.msg("Data penggunaan bahan berhasil dihapus dan stok dikembalikan.");
                    }
                    break;
                case "DetailPesanan":
                    if (aksi.equals("tambah")) {
                        if (d[1].isBlank() || d[2].isBlank()) {
                            view.msg("Pilih pesanan dan nomor batch.");
                            return;
                        }
                        dao.tambahDetailPesanan(parseId(d[1], -1), parseId(d[2], -1));
                        view.msg("Detail pesanan berhasil ditambahkan.");
                    } else if (aksi.equals("ubah")) {
                        if (d[0].isBlank()) { view.msg("Pilih baris detail pesanan terlebih dahulu."); return; }
                        view.msg("Jumlah tidak dapat diubah. Hapus detail sebelum pengiriman jika perlu mengganti batch.");
                        return;
                    } else if (aksi.equals("hapus")) {
                        if (d[0].isBlank()) { view.msg("Pilih detail pesanan yang akan dihapus."); return; }
                        if (!konfirmasi("Hapus alokasi batch terpilih dan kembalikan stok batch?")) return;
                        dao.hapusDetailPesanan(parseId(d[0], -1));
                        view.msg("Detail pesanan berhasil dihapus.");
                    }
                    break;
                case "Produksi":
                    if (aksi.equals("ubah")) {
                        if (d[0].isBlank()) { view.msg("Pilih data batch produksi terlebih dahulu."); return; }
                        dao.ubahProduksi(d[0], d[2], d[3]);
                        view.msg("Data produksi berhasil diperbarui.");
                    } else if (aksi.equals("hapus")) {
                        if (d[0].isBlank()) { view.msg("Pilih batch yang akan dihapus."); return; }
                        if (!konfirmasi("Hapus batch produksi ini?")) return;
                        dao.hapusProduksi(d[0]);
                        view.msg("Batch produksi berhasil dihapus.");
                    }
                    break;
                default:
                    return;
            }
            view.resetFields();
            perbaruiPilihanBatch();
            refresh();
        } catch (Exception ex) {
            view.msg("Proses gagal: " + akar(ex));
        }
    }

    private boolean konfirmasi(String pesan) {
        return JOptionPane.showConfirmDialog(view, pesan, "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }
}
