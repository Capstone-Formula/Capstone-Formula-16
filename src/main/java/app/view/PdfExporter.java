package app.view;

import java.awt.Component;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class PdfExporter {
    private PdfExporter() {}

    public static void exportTable(Component parent, JTable table, String title) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Simpan sebagai PDF");
        fc.setFileFilter(new FileNameExtensionFilter("Dokumen PDF (*.pdf)", "pdf"));
        fc.setAcceptAllFileFilterUsed(false);
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmm"));
        fc.setSelectedFile(new File(slug(title) + "-" + stamp + ".pdf"));
        if (fc.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION) return;

        File out = fc.getSelectedFile();
        if (!out.getName().toLowerCase(Locale.ROOT).endsWith(".pdf")) out = new File(out.getParentFile(), out.getName() + ".pdf");
        if (out.exists() && JOptionPane.showConfirmDialog(parent, "File " + out.getName() + " sudah ada. Timpa?",
                "Simpan PDF", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;

        try {
            List<String> head = new ArrayList<>();
            for (int c = 0; c < table.getColumnCount(); c++) head.add(table.getColumnName(c));
            List<List<String>> rows = new ArrayList<>();
            for (int r = 0; r < table.getRowCount(); r++) {
                List<String> row = new ArrayList<>();
                for (int c = 0; c < table.getColumnCount(); c++) {
                    Object v = table.getValueAt(r, c);
                    row.add(v == null ? "" : v.toString());
                }
                rows.add(row);
            }
            byte[] pdf = build(title, head, rows);
            Files.write(out.toPath(), pdf);
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                    Desktop.getDesktop().open(out);
                    return;
                }
            } catch (IOException | RuntimeException ignored) {  }
            JOptionPane.showMessageDialog(parent, "PDF tersimpan di:\n" + out.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent, "Gagal menyimpan PDF: " + ex.getMessage());
        }
    }

    private static String slug(String s) {
        String t = (s == null ? "tabel" : s).toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
        return t.isEmpty() ? "tabel" : "formula-" + t;
    }

    private static final float PAGE_W = 841.89f, PAGE_H = 595.28f;      
    private static final float MARGIN = 40f;
    private static final float FS = 9.5f, FS_HEAD = 9.5f, LEAD = 13f, PAD_X = 7f, PAD_Y = 6f;
    private static final float[] TEAL = {15 / 255f, 118 / 255f, 110 / 255f};
    private static final float[] INK = {16 / 255f, 37 / 255f, 43 / 255f};
    private static final float[] MUTED = {84 / 255f, 106 / 255f, 112 / 255f};
    private static final float[] ZEBRA = {244 / 255f, 248 / 255f, 247 / 255f};
    private static final Charset WIN = Charset.forName("windows-1252");

    private static final int[] W_REG = {278, 278, 355, 556, 556, 889, 667, 191, 333, 333, 389, 584, 278, 333, 278, 278,
        556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 278, 278, 584, 584, 584, 556,
        1015, 667, 667, 722, 722, 667, 611, 778, 722, 278, 500, 667, 556, 833, 722, 778,
        667, 778, 722, 667, 611, 722, 667, 944, 667, 667, 611, 278, 278, 278, 469, 556,
        333, 556, 556, 500, 556, 556, 278, 556, 556, 222, 222, 500, 222, 833, 556, 556,
        556, 556, 333, 500, 278, 556, 500, 722, 500, 500, 500, 334, 260, 334, 584};
    private static final int[] W_BOLD = {278, 333, 474, 556, 556, 889, 722, 238, 333, 333, 389, 584, 278, 333, 278, 278,
        556, 556, 556, 556, 556, 556, 556, 556, 556, 556, 333, 333, 584, 584, 584, 611,
        975, 722, 722, 722, 722, 667, 611, 778, 722, 278, 556, 722, 611, 833, 722, 778,
        667, 778, 722, 667, 611, 722, 667, 944, 667, 667, 611, 333, 278, 333, 584, 556,
        333, 556, 611, 556, 611, 556, 333, 611, 611, 278, 278, 556, 278, 889, 611, 611,
        611, 611, 389, 556, 333, 611, 556, 778, 556, 556, 500, 389, 280, 389, 584};

    private static float width(String s, boolean bold, float size) {
        int[] w = bold ? W_BOLD : W_REG;
        float sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            sum += (ch >= 32 && ch <= 126) ? w[ch - 32] : 556;
        }
        return sum * size / 1000f;
    }

    private static List<String> wrap(String text, boolean bold, float size, float maxW) {
        List<String> lines = new ArrayList<>();
        for (String para : text.replace("\r", "").split("\n", -1)) {
            StringBuilder cur = new StringBuilder();
            for (String word : para.split(" ")) {
                
                while (width(word, bold, size) > maxW && word.length() > 1) {
                    int cut = word.length() - 1;
                    while (cut > 1 && width(word.substring(0, cut), bold, size) > maxW) cut--;
                    if (cur.length() > 0) { lines.add(cur.toString()); cur.setLength(0); }
                    lines.add(word.substring(0, cut));
                    word = word.substring(cut);
                }
                String trial = cur.length() == 0 ? word : cur + " " + word;
                if (width(trial, bold, size) <= maxW) {
                    cur.setLength(0);
                    cur.append(trial);
                } else {
                    lines.add(cur.toString());
                    cur.setLength(0);
                    cur.append(word);
                }
            }
            lines.add(cur.toString());
        }
        return lines;
    }

    private static String esc(String s) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == ')' || c == '\\') b.append('\\').append(c);
            else if (c < 32) b.append(' ');
            else if (c < 127) b.append(c);
            else {
                byte[] enc = String.valueOf(c).getBytes(WIN);
                b.append(String.format("\\%03o", enc.length == 1 ? enc[0] & 0xFF : '?'));
            }
        }
        return b.toString();
    }

    private static String f(float v) { return String.format(Locale.ROOT, "%.2f", v); }

    private static final class Page {
        final StringBuilder sb = new StringBuilder();
        void rect(float x, float yTop, float w, float h, float[] c) {
            sb.append(f(c[0])).append(' ').append(f(c[1])).append(' ').append(f(c[2])).append(" rg ")
              .append(f(x)).append(' ').append(f(PAGE_H - yTop - h)).append(' ').append(f(w)).append(' ').append(f(h)).append(" re f\n");
        }
        void text(String s, float x, float baselineTop, boolean bold, float size, float[] c) {
            sb.append("BT /").append(bold ? "F2" : "F1").append(' ').append(f(size)).append(" Tf ")
              .append(f(c[0])).append(' ').append(f(c[1])).append(' ').append(f(c[2])).append(" rg ")
              .append(f(x)).append(' ').append(f(PAGE_H - baselineTop)).append(" Td (").append(esc(s)).append(") Tj ET\n");
        }
    }

    static byte[] build(String title, List<String> head, List<List<String>> rows) throws IOException {
        int n = Math.max(1, head.size());
        float tableW = PAGE_W - 2 * MARGIN;

        float[] want = new float[n];
        for (int c = 0; c < n; c++) {
            float w = width(head.get(c), true, FS_HEAD);
            for (List<String> r : rows) w = Math.max(w, width(r.get(c), false, FS));
            want[c] = Math.min(Math.max(w + 2 * PAD_X, 48f), 230f);
        }
        float sum = 0;
        for (float w : want) sum += w;
        float[] colW = new float[n];
        for (int c = 0; c < n; c++) colW[c] = want[c] * tableW / sum;

        List<Page> pages = new ArrayList<>();
        Page pg = null;
        float y = 0;
        final float top = MARGIN, bottom = PAGE_H - MARGIN - 14;
        String printed = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", new Locale("id", "ID")));

        int rowNo = 0;
        for (int i = -1; i < rows.size(); i++) {
            boolean isHead = i < 0;
            List<List<String>> cells = new ArrayList<>();
            int maxLines = 1;
            for (int c = 0; c < n; c++) {
                String t = isHead ? head.get(c) : rows.get(i).get(c);
                List<String> ls = wrap(t, isHead, FS, colW[c] - 2 * PAD_X);
                cells.add(ls);
                maxLines = Math.max(maxLines, ls.size());
            }
            float rh = maxLines * LEAD + 2 * PAD_Y;
            boolean newPage = pg == null || y + rh > bottom;
            if (newPage) {
                pg = new Page();
                pages.add(pg);
                y = top;
                if (pages.size() == 1) {
                    pg.text("Formula", MARGIN, y + 10, true, 10, TEAL);
                    pg.text(title, MARGIN, y + 34, true, 20, INK);
                    String meta = "Dicetak " + printed;
                    pg.text(meta, MARGIN, y + 52, false, 10, MUTED);
                    y += 72;
                } else {
                    // Halaman lanjutan tidak menampilkan judul "(lanjutan)".
                    // Header kolom tetap diulang agar tabel mudah dibaca.
                    y += 8;
                }
                if (!isHead) { 
                    float hh = LEAD + 2 * PAD_Y;
                    pg.rect(MARGIN, y, tableW, hh, TEAL);
                    float x = MARGIN;
                    for (int c = 0; c < n; c++) {
                        pg.text(head.get(c), x + PAD_X, y + PAD_Y + 9.5f, true, FS_HEAD, new float[]{1, 1, 1});
                        x += colW[c];
                    }
                    y += hh;
                }
            }
            if (isHead) {
                pg.rect(MARGIN, y, tableW, rh, TEAL);
            } else if (rowNo++ % 2 == 1) {
                pg.rect(MARGIN, y, tableW, rh, ZEBRA);
            }
            float x = MARGIN;
            for (int c = 0; c < n; c++) {
                List<String> ls = cells.get(c);
                for (int k = 0; k < ls.size(); k++) {
                    pg.text(ls.get(k), x + PAD_X, y + PAD_Y + 9.5f + k * LEAD, isHead, FS,
                            isHead ? new float[]{1, 1, 1} : INK);
                }
                x += colW[c];
            }
            y += rh;
        }
        if (rows.isEmpty() && pg != null) {
            pg.text("Tidak ada data untuk dicetak.", MARGIN + PAD_X, y + 22, false, 11, MUTED);
        }

        return assemble(pages);
    }

    private static byte[] assemble(List<Page> pages) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<Integer> offsets = new ArrayList<>();
        int total = 4 + pages.size() * 2;     
        write(out, "%PDF-1.4\n");
        offsets.add(out.size()); write(out, "1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n");
        StringBuilder kids = new StringBuilder();
        for (int p = 0; p < pages.size(); p++) kids.append(5 + p * 2).append(" 0 R ");
        offsets.add(out.size()); write(out, "2 0 obj\n<< /Type /Pages /Kids [" + kids + "] /Count " + pages.size() + " >>\nendobj\n");
        offsets.add(out.size()); write(out, "3 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica /Encoding /WinAnsiEncoding >>\nendobj\n");
        offsets.add(out.size()); write(out, "4 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica-Bold /Encoding /WinAnsiEncoding >>\nendobj\n");
        for (int p = 0; p < pages.size(); p++) {
            int pageObj = 5 + p * 2, contentObj = pageObj + 1;
            offsets.add(out.size());
            write(out, pageObj + " 0 obj\n<< /Type /Page /Parent 2 0 R /MediaBox [0 0 " + f(PAGE_W) + " " + f(PAGE_H) + "] "
                    + "/Resources << /Font << /F1 3 0 R /F2 4 0 R >> >> /Contents " + contentObj + " 0 R >>\nendobj\n");
            byte[] data = pages.get(p).sb.toString().getBytes(java.nio.charset.StandardCharsets.ISO_8859_1);
            offsets.add(out.size());
            write(out, contentObj + " 0 obj\n<< /Length " + data.length + " >>\nstream\n");
            out.write(data);
            write(out, "\nendstream\nendobj\n");
        }
        int xref = out.size();
        StringBuilder x = new StringBuilder("xref\n0 " + (total + 1) + "\n0000000000 65535 f \n");
        for (int off : offsets) x.append(String.format(Locale.ROOT, "%010d 00000 n \n", off));
        x.append("trailer\n<< /Size ").append(total + 1).append(" /Root 1 0 R >>\nstartxref\n").append(xref).append("\n%%EOF\n");
        write(out, x.toString());
        return out.toByteArray();
    }

    private static void write(OutputStream o, String s) throws IOException {
        o.write(s.getBytes(java.nio.charset.StandardCharsets.ISO_8859_1));
    }
}
