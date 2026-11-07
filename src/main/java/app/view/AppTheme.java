package app.view;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;
import javax.swing.table.TableModel;

public final class AppTheme {
    private AppTheme() {}

    public static final Color INK          = new Color(16, 37, 43);      
    public static final Color TEXT_DARK    = INK;
    public static final Color TEXT_MUTED   = new Color(84, 106, 112);    
    public static final Color TEXT_FAINT   = new Color(133, 152, 156);   
    public static final Color BG_MAIN      = new Color(242, 246, 245);   
    public static final Color CARD_BG      = Color.WHITE;
    public static final Color BORDER_LIGHT = new Color(221, 230, 228);
    public static final Color BORDER_FIELD = new Color(207, 219, 217);
    public static final Color TEAL_PRIMARY = new Color(15, 118, 110);    
    public static final Color TEAL_DARK    = new Color(11, 90, 84);
    public static final Color TEAL_TINT    = new Color(222, 243, 240);
    public static final Color TEAL_MINT    = new Color(94, 234, 212);
    public static final Color DEEP         = new Color(6, 44, 43);       
    public static final Color ACCENT_INK   = new Color(22, 54, 62);      
    public static final Color RED_DANGER   = new Color(197, 48, 48);
    public static final Color RED_TINT     = new Color(253, 235, 235);
    public static final Color AMBER        = new Color(180, 105, 8);
    public static final Color AMBER_TINT   = new Color(254, 243, 214);
    public static final Color SKY          = new Color(29, 100, 160);
    public static final Color SKY_TINT     = new Color(224, 239, 252);
    public static final Color GREEN        = new Color(21, 128, 61);
    public static final Color GREEN_TINT   = new Color(220, 244, 228);
    public static final Color ROW_HOVER    = new Color(246, 250, 249);
    public static final Color ROW_SELECT   = new Color(222, 243, 240);

    public static final Color BLUE_PRIMARY = ACCENT_INK;
    public static final Color SLATE_NEUTRAL = TEXT_MUTED;
    public static final Color HEADER_NAVY  = DEEP;
    public static final Color TABLE_ZEBRA  = Color.WHITE;
    public static final Color TABLE_SEL    = ROW_SELECT;

    private static final String FAMILY = pickFamily();
    public static final Font FONT_HEADER = font(Font.BOLD, 20);
    public static final Font FONT_SUB = font(Font.PLAIN, 14);
    public static final Font FONT_SECTION = font(Font.BOLD, 17);
    public static final Font FONT_LABEL = font(Font.BOLD, 13);
    public static final Font FONT_INPUT = font(Font.PLAIN, 14);
    public static final Font FONT_BUTTON = font(Font.BOLD, 14);
    public static final Font FONT_TABLE = font(Font.PLAIN, 14);
    public static final Font FONT_TABLE_HEADER = font(Font.BOLD, 13);

    private static String pickFamily() {
        java.util.Set<String> have = new java.util.HashSet<>(java.util.Arrays.asList(
            GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        for (String f : new String[]{"Segoe UI", "Inter", "SF Pro Text", "Helvetica Neue", "Noto Sans", "Roboto"}) {
            if (have.contains(f)) return f;
        }
        return Font.SANS_SERIF;
    }

    public static Font font(int style, float size) { return new Font(FAMILY, style, 1).deriveFont(style, size); }

    public static Font tracked(Font f, float tracking) {
        Map<TextAttribute, Object> m = new HashMap<>();
        m.put(TextAttribute.TRACKING, tracking);
        return f.deriveFont(m);
    }

    static {
        install();
    }

    public static void install() {
        ToolTipManager.sharedInstance().setEnabled(false); 
        UIManager.put("ToolTip.font", font(Font.PLAIN, 13));
        UIManager.put("ToolTip.background", INK);
        UIManager.put("ToolTip.foreground", Color.WHITE);
        UIManager.put("ToolTip.border", new EmptyBorder(6, 10, 6, 10));
        UIManager.put("OptionPane.messageFont", font(Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", font(Font.BOLD, 13));
        UIManager.put("OptionPane.messageForeground", INK);
        UIManager.put("Label.font", font(Font.PLAIN, 14));
        UIManager.put("Button.font", font(Font.BOLD, 14));
        UIManager.put("TextField.font", FONT_INPUT);
        UIManager.put("ComboBox.font", FONT_INPUT);
        UIManager.put("Table.font", FONT_TABLE);
        UIManager.put("List.font", FONT_INPUT);
        UIManager.put("TextField.selectionBackground", TEAL_TINT);
        UIManager.put("TextField.selectionForeground", INK);
        UIManager.put("PasswordField.selectionBackground", TEAL_TINT);
        UIManager.put("PasswordField.selectionForeground", INK);
    }

    static Graphics2D aa(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        return g2;
    }

    static Color alpha(Color c, int a) { return new Color(c.getRed(), c.getGreen(), c.getBlue(), a); }

    static Color mix(Color a, Color b, float t) {
        return new Color(
            Math.round(a.getRed() + (b.getRed() - a.getRed()) * t),
            Math.round(a.getGreen() + (b.getGreen() - a.getGreen()) * t),
            Math.round(a.getBlue() + (b.getBlue() - a.getBlue()) * t));
    }

    public enum Glyph {
        PILL, FLASK, USERS, CLIPBOARD, TRUCK, BUILDING, LAYERS, GEAR, BOOK, LIST, TOGGLE,
        ARROW_LEFT, ARROW_RIGHT, SEARCH, REFRESH, LOGOUT, PLUS, EDIT, TRASH, PRINT, ERASE, CHEVRON_DOWN, DOT, INBOX, USER
    }

    public static final class GlyphIcon implements Icon {
        private final Glyph glyph;
        private final int size;
        private final Color color;
        public GlyphIcon(Glyph g, int size, Color color) { this.glyph = g; this.size = size; this.color = color; }

        @Override public int getIconWidth() { return size; }
        @Override public int getIconHeight() { return size; }

        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Color col = color;
            if (col == null) {
                col = c instanceof AbstractButton ? ModernButtonUI.currentFg((AbstractButton) c) : c.getForeground();
            }
            Graphics2D g2 = aa(g);
            g2.translate(x, y);
            double s = size / 24.0;
            g2.scale(s, s);
            g2.setColor(col);
            g2.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            draw(g2, glyph);
            g2.dispose();
        }
    }

    public static Icon icon(Glyph g, int size, Color c) { return new GlyphIcon(g, size, c); }
    public static Icon icon(Glyph g, int size) { return new GlyphIcon(g, size, null); }

    private static void line(Graphics2D g, double x1, double y1, double x2, double y2) { g.draw(new Line2D.Double(x1, y1, x2, y2)); }
    private static void rr(Graphics2D g, double x, double y, double w, double h, double r) { g.draw(new RoundRectangle2D.Double(x, y, w, h, r, r)); }
    private static void circle(Graphics2D g, double cx, double cy, double r, boolean fill) {
        Ellipse2D e = new Ellipse2D.Double(cx - r, cy - r, 2 * r, 2 * r);
        if (fill) g.fill(e); else g.draw(e);
    }

    private static void draw(Graphics2D g, Glyph glyph) {
        switch (glyph) {
            case PILL: {
                Graphics2D r = (Graphics2D) g.create();
                r.rotate(Math.toRadians(-45), 12, 12);
                r.draw(new RoundRectangle2D.Double(3, 7.5, 18, 9, 9, 9));
                line(r, 12, 7.5, 12, 16.5);
                r.dispose();
                break;
            }
            case FLASK: {
                Path2D p = new Path2D.Double();
                p.moveTo(10, 3); p.lineTo(10, 9.5); p.lineTo(4.8, 18.5);
                p.quadTo(3.8, 21, 6.4, 21); p.lineTo(17.6, 21);
                p.quadTo(20.2, 21, 19.2, 18.5); p.lineTo(14, 9.5); p.lineTo(14, 3);
                g.draw(p);
                line(g, 8.5, 3, 15.5, 3);
                line(g, 7.6, 15, 16.4, 15);
                break;
            }
            case USERS:
                circle(g, 9, 8, 3.4, false);
                { Path2D p = new Path2D.Double(); p.moveTo(2.8, 20); p.quadTo(2.8, 13.8, 9, 13.8); p.quadTo(15.2, 13.8, 15.2, 20); g.draw(p); }
                circle(g, 17, 8.6, 2.6, false);
                { Path2D p = new Path2D.Double(); p.moveTo(17.5, 13.8); p.quadTo(21.2, 14.2, 21.2, 19); g.draw(p); }
                break;
            case USER:
                circle(g, 12, 8, 3.8, false);
                { Path2D p = new Path2D.Double(); p.moveTo(4.5, 20.5); p.quadTo(4.5, 14, 12, 14); p.quadTo(19.5, 14, 19.5, 20.5); g.draw(p); }
                break;
            case CLIPBOARD:
                rr(g, 5, 4.5, 14, 17, 3.5);
                rr(g, 9, 2.5, 6, 4, 2);
                line(g, 8.5, 11.5, 15.5, 11.5);
                line(g, 8.5, 15.5, 13, 15.5);
                break;
            case TRUCK: {
                rr(g, 2, 6, 12, 10, 2.5);
                Path2D p = new Path2D.Double();
                p.moveTo(14, 9.5); p.lineTo(17.8, 9.5); p.lineTo(21.5, 13); p.lineTo(21.5, 16); p.lineTo(14, 16);
                g.draw(p);
                Color c = g.getColor();
                g.setColor(Color.WHITE); circle(g, 7, 17.5, 2.2, true); circle(g, 17, 17.5, 2.2, true);
                g.setColor(c); circle(g, 7, 17.5, 2.2, false); circle(g, 17, 17.5, 2.2, false);
                break;
            }
            case BUILDING:
                rr(g, 5, 3, 14, 18, 2.5);
                g.fill(new Rectangle2D.Double(8.5, 7, 2, 2)); g.fill(new Rectangle2D.Double(13.5, 7, 2, 2));
                g.fill(new Rectangle2D.Double(8.5, 11.2, 2, 2)); g.fill(new Rectangle2D.Double(13.5, 11.2, 2, 2));
                { Path2D p = new Path2D.Double(); p.moveTo(10, 21); p.lineTo(10, 16.5); p.lineTo(14, 16.5); p.lineTo(14, 21); g.draw(p); }
                break;
            case LAYERS: {
                Path2D a = new Path2D.Double(); a.moveTo(12, 3.2); a.lineTo(21, 8); a.lineTo(12, 12.8); a.lineTo(3, 8); a.closePath(); g.draw(a);
                Path2D b = new Path2D.Double(); b.moveTo(3, 12); b.lineTo(12, 16.8); b.lineTo(21, 12); g.draw(b);
                Path2D c = new Path2D.Double(); c.moveTo(3, 16); c.lineTo(12, 20.8); c.lineTo(21, 16); g.draw(c);
                break;
            }
            case GEAR:
                circle(g, 12, 12, 3, false);
                circle(g, 12, 12, 6.6, false);
                {
                    Stroke old = g.getStroke();
                    g.setStroke(new BasicStroke(3.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
                    for (int i = 0; i < 8; i++) {
                        double t = Math.toRadians(i * 45);
                        line(g, 12 + Math.cos(t) * 7.6, 12 + Math.sin(t) * 7.6, 12 + Math.cos(t) * 9.6, 12 + Math.sin(t) * 9.6);
                    }
                    g.setStroke(old);
                }
                break;
            case BOOK: {
                Path2D l = new Path2D.Double(); l.moveTo(3.5, 5.5); l.quadTo(8, 3.5, 12, 6.2); l.lineTo(12, 20.5); l.quadTo(8, 17.8, 3.5, 19.5); l.closePath(); g.draw(l);
                Path2D r = new Path2D.Double(); r.moveTo(20.5, 5.5); r.quadTo(16, 3.5, 12, 6.2); r.lineTo(12, 20.5); r.quadTo(16, 17.8, 20.5, 19.5); r.closePath(); g.draw(r);
                break;
            }
            case LIST:
                circle(g, 4.8, 7, 1.2, true); circle(g, 4.8, 12, 1.2, true); circle(g, 4.8, 17, 1.2, true);
                line(g, 9, 7, 20, 7); line(g, 9, 12, 20, 12); line(g, 9, 17, 16.5, 17);
                break;
            case TOGGLE:
                rr(g, 2.8, 7, 18.4, 10, 10);
                circle(g, 15.8, 12, 3, true);
                break;
            case ARROW_LEFT:
                line(g, 19, 12, 5.5, 12); line(g, 11.5, 6, 5.5, 12); line(g, 11.5, 18, 5.5, 12);
                break;
            case ARROW_RIGHT:
                line(g, 5, 12, 18.5, 12); line(g, 12.5, 6, 18.5, 12); line(g, 12.5, 18, 18.5, 12);
                break;
            case CHEVRON_DOWN:
                line(g, 6, 9.5, 12, 15.5); line(g, 18, 9.5, 12, 15.5);
                break;
            case SEARCH:
                circle(g, 10.5, 10.5, 6, false); line(g, 15, 15, 20.5, 20.5);
                break;
            case REFRESH:
                g.draw(new Arc2D.Double(4.5, 4.5, 15, 15, 40, 285, Arc2D.OPEN));
                line(g, 19.2, 4, 19.2, 9.4); line(g, 19.2, 9.4, 13.8, 9.4);
                break;
            case LOGOUT: {
                Path2D p = new Path2D.Double();
                p.moveTo(10, 4); p.lineTo(6.5, 4); p.quadTo(4, 4, 4, 6.5); p.lineTo(4, 17.5); p.quadTo(4, 20, 6.5, 20); p.lineTo(10, 20);
                g.draw(p);
                line(g, 9.5, 12, 20, 12); line(g, 15.5, 7.5, 20, 12); line(g, 15.5, 16.5, 20, 12);
                break;
            }
            case PLUS:
                line(g, 12, 5, 12, 19); line(g, 5, 12, 19, 12);
                break;
            case EDIT: {
                Path2D p = new Path2D.Double();
                p.moveTo(4, 20); p.lineTo(4.8, 15.4); p.lineTo(16, 4.2); p.quadTo(17.2, 3.2, 18.4, 4.4); p.lineTo(19.6, 5.6);
                p.quadTo(20.8, 6.8, 19.8, 8); p.lineTo(8.6, 19.2); p.closePath();
                g.draw(p);
                line(g, 14, 6.4, 17.6, 10);
                break;
            }
            case TRASH:
                line(g, 4.5, 7, 19.5, 7);
                { Path2D p = new Path2D.Double(); p.moveTo(9, 7); p.lineTo(9, 4.2); p.lineTo(15, 4.2); p.lineTo(15, 7); g.draw(p); }
                { Path2D p = new Path2D.Double(); p.moveTo(6.5, 7); p.lineTo(7.4, 19.6); p.quadTo(7.5, 20.6, 8.5, 20.6); p.lineTo(15.5, 20.6); p.quadTo(16.5, 20.6, 16.6, 19.6); p.lineTo(17.5, 7); g.draw(p); }
                line(g, 10.2, 11, 10.2, 16.5); line(g, 13.8, 11, 13.8, 16.5);
                break;
            case PRINT:
                { Path2D p = new Path2D.Double(); p.moveTo(7, 9); p.lineTo(7, 3.8); p.lineTo(17, 3.8); p.lineTo(17, 9); g.draw(p); }
                rr(g, 3.5, 9, 17, 8, 3);
                rr(g, 7, 14, 10, 6.5, 1.5);
                break;
            case ERASE:
                circle(g, 12, 12, 8.5, false); line(g, 8.6, 8.6, 15.4, 15.4); line(g, 15.4, 8.6, 8.6, 15.4);
                break;
            case DOT:
                circle(g, 12, 12, 5, true);
                break;
            case INBOX: {
                Path2D p = new Path2D.Double();
                p.moveTo(3.5, 13); p.lineTo(6.6, 5.4); p.quadTo(7, 4.5, 8, 4.5); p.lineTo(16, 4.5); p.quadTo(17, 4.5, 17.4, 5.4); p.lineTo(20.5, 13);
                p.lineTo(20.5, 18); p.quadTo(20.5, 19.5, 19, 19.5); p.lineTo(5, 19.5); p.quadTo(3.5, 19.5, 3.5, 18); p.closePath();
                g.draw(p);
                Path2D q = new Path2D.Double(); q.moveTo(3.5, 13); q.lineTo(8.5, 13); q.quadTo(9.5, 13, 10, 14); q.lineTo(10.4, 14.8); q.quadTo(10.8, 15.6, 11.8, 15.6); q.lineTo(12.2, 15.6); q.quadTo(13.2, 15.6, 13.6, 14.8); q.lineTo(14, 14); q.quadTo(14.5, 13, 15.5, 13); q.lineTo(20.5, 13);
                g.draw(q);
                break;
            }
            default:
        }
    }

    public static Glyph glyphForMenu(String label) {
        String s = label == null ? "" : label.toLowerCase(Locale.ROOT);
        if (s.contains("status")) return Glyph.TOGGLE;
        if (s.contains("detail")) return Glyph.LIST;
        if (s.contains("bahan")) return Glyph.FLASK;
        if (s.contains("karyawan")) return Glyph.USERS;
        if (s.contains("pesanan")) return Glyph.CLIPBOARD;
        if (s.contains("pengiriman")) return Glyph.TRUCK;
        if (s.contains("instansi")) return Glyph.BUILDING;
        if (s.contains("batch") || s.contains("stok")) return Glyph.LAYERS;
        if (s.contains("produksi")) return Glyph.GEAR;
        if (s.contains("obat")) return Glyph.PILL;
        return Glyph.DOT;
    }

    public static final class BrandIcon implements Icon {
        private final int size;
        private final boolean onDark;
        public BrandIcon(int size, boolean onDark) { this.size = size; this.onDark = onDark; }
        @Override public int getIconWidth() { return size; }
        @Override public int getIconHeight() { return size; }
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = aa(g);
            g2.translate(x, y);
            double r = size * 0.30;
            Shape box = new RoundRectangle2D.Double(0, 0, size, size, r * 2, r * 2);
            g2.setColor(onDark ? TEAL_MINT : TEAL_PRIMARY);
            g2.fill(box);
            Graphics2D k = (Graphics2D) g2.create();
            k.rotate(Math.toRadians(-45), size / 2.0, size / 2.0);
            double w = size * 0.62, h = size * 0.31;
            double cx = (size - w) / 2, cy = (size - h) / 2;
            Shape pill = new RoundRectangle2D.Double(cx, cy, w, h, h, h);
            k.setColor(onDark ? DEEP : Color.WHITE);
            k.fill(pill);
            k.setClip(new Rectangle2D.Double(cx + w / 2, cy - 1, w, h + 2));
            k.setColor(onDark ? alpha(DEEP, 120) : alpha(TEAL_DARK, 110));
            k.fill(pill);
            k.dispose();
            g2.dispose();
        }
    }

    public static Icon brandIcon(int size) { return new BrandIcon(size, false); }

    public static Image appIcon() {
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        new BrandIcon(64, false).paintIcon(null, g, 0, 0);
        g.dispose();
        return img;
    }

    public enum Variant { PRIMARY, INK, DANGER, TONAL, GHOST }

    static final class ModernButtonUI extends BasicButtonUI {
        private final Variant variant;
        private final int radius;
        ModernButtonUI(Variant v, int radius) { this.variant = v; this.radius = radius; }

        static Variant variantOf(AbstractButton b) {
            Object v = b.getClientProperty("formula.variant");
            return v instanceof Variant ? (Variant) v : Variant.GHOST;
        }

        static Color currentFg(AbstractButton b) {
            Variant v = variantOf(b);
            ButtonModel m = b.getModel();
            boolean hot = b.isEnabled() && (m.isRollover() || m.isPressed());
            Color fg;
            switch (v) {
                case PRIMARY: case INK: fg = Color.WHITE; break;
                case DANGER: fg = hot ? Color.WHITE : RED_DANGER; break;
                case TONAL: fg = TEAL_DARK; break;
                default: fg = hot ? TEAL_DARK : INK;
            }
            return b.isEnabled() ? fg : alpha(fg, 120);
        }

        static Color currentBg(AbstractButton b) {
            Variant v = variantOf(b);
            ButtonModel m = b.getModel();
            boolean press = m.isPressed() && m.isArmed();
            boolean hover = m.isRollover();
            Color bg;
            switch (v) {
                case PRIMARY: bg = press ? mix(TEAL_PRIMARY, Color.BLACK, .22f) : hover ? mix(TEAL_PRIMARY, Color.BLACK, .12f) : TEAL_PRIMARY; break;
                case INK: bg = press ? mix(ACCENT_INK, Color.BLACK, .25f) : hover ? mix(ACCENT_INK, Color.BLACK, .14f) : ACCENT_INK; break;
                case DANGER: bg = press ? mix(RED_DANGER, Color.BLACK, .2f) : hover ? RED_DANGER : RED_TINT; break;
                case TONAL: bg = press ? mix(TEAL_TINT, TEAL_PRIMARY, .25f) : hover ? mix(TEAL_TINT, TEAL_PRIMARY, .12f) : TEAL_TINT; break;
                default: bg = press ? new Color(214, 228, 225) : hover ? new Color(224, 236, 233) : new Color(236, 243, 241);
            }
            return b.isEnabled() ? bg : mix(bg, Color.WHITE, .55f);
        }

        @Override protected void installDefaults(AbstractButton b) {
            super.installDefaults(b);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            b.setRolloverEnabled(true);
            b.setFont(FONT_BUTTON);
            b.setIconTextGap(8);
            b.setBorder(new EmptyBorder(8, 16, 8, 16));
        }

        @Override public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            Graphics2D g2 = aa(g);
            int w = c.getWidth(), h = c.getHeight();
            RoundRectangle2D shape = new RoundRectangle2D.Double(0.5, 0.5, w - 1, h - 1, radius * 2, radius * 2);
            g2.setColor(currentBg(b));
            g2.fill(shape);
            if (b.hasFocus() && b.isFocusPainted()) {
                g2.setColor(alpha(TEAL_PRIMARY, 140));
                g2.setStroke(new BasicStroke(2f));
                g2.draw(new RoundRectangle2D.Double(2, 2, w - 4, h - 4, radius * 2 - 3, radius * 2 - 3));
            }
            g2.dispose();
            super.paint(g, c);
        }

        @Override protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
            Graphics2D g2 = aa(g);
            g2.setFont(b.getFont());
            g2.setColor(currentFg(b));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(text, textRect.x + getTextShiftOffset(), textRect.y + fm.getAscent() + getTextShiftOffset());
            g2.dispose();
        }

        @Override protected void paintFocus(Graphics g, AbstractButton b, Rectangle v, Rectangle t, Rectangle i) {}
    }

    private static void setupButton(JButton btn, String text, Variant v, int radius, Border pad) {
        btn.putClientProperty("formula.variant", v);
        btn.setUI(new ModernButtonUI(v, radius));
        if (text != null) btn.setText(text);
        if (pad != null) btn.setBorder(pad);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(true);
        btn.setForeground(ModernButtonUI.currentFg(btn));
        btn.repaint();
    }

    public static void stylePrimaryButton(JButton btn, String text, Color bgColor) {
        Variant v = bgColor == RED_DANGER ? Variant.DANGER : bgColor == TEAL_PRIMARY ? Variant.PRIMARY : Variant.INK;
        setupButton(btn, text, v, 10, new EmptyBorder(8, 18, 8, 18));
    }

    public static void styleButton(JButton btn, String text, Variant v, Glyph glyph) {
        setupButton(btn, text, v, 10, new EmptyBorder(8, 16, 8, 16));
        if (glyph != null) btn.setIcon(icon(glyph, 16));
    }

    public static void styleSecondaryButton(JButton btn, String text) {
        setupButton(btn, text, Variant.GHOST, 10, new EmptyBorder(8, 16, 8, 16));
    }

    public static void styleMenuButton(JButton btn) {
        Glyph glyph = glyphForMenu(btn.getText());
        btn.putClientProperty("formula.glyph", glyph);
        btn.putClientProperty("formula.variant", Variant.GHOST);
        btn.setUI(new MenuCardUI());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    static final class MenuCardUI extends BasicButtonUI {
        @Override protected void installDefaults(AbstractButton b) {
            super.installDefaults(b);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            b.setRolloverEnabled(true);
            b.setBorder(new EmptyBorder(0, 0, 0, 0));
        }

        @Override public void paint(Graphics g, JComponent c) {
            AbstractButton b = (AbstractButton) c;
            ButtonModel m = b.getModel();
            boolean hot = m.isRollover() || b.hasFocus();
            boolean press = m.isPressed() && m.isArmed();
            Graphics2D g2 = aa(g);
            int w = c.getWidth(), h = c.getHeight();
            int inset = 3;
            RoundRectangle2D card = new RoundRectangle2D.Double(inset, inset + (press ? 1 : 0), w - inset * 2, h - inset * 2, 22, 22);
            g2.setColor(hot ? TEAL_PRIMARY : Color.WHITE);
            g2.fill(card);

            int is = 26;
            int tx = inset + 24;
            Object gl = b.getClientProperty("formula.glyph");
            if (gl instanceof Glyph) {
                new GlyphIcon((Glyph) gl, is, hot ? Color.WHITE : TEAL_PRIMARY).paintIcon(c, g2, tx, (h - is) / 2);
            }

            g2.setFont(font(Font.BOLD, 16));
            g2.setColor(hot ? Color.WHITE : INK);
            FontMetrics fm = g2.getFontMetrics();
            int textX = tx + is + 16;
            int maxW = w - textX - 52;
            String text = b.getText() == null ? "" : b.getText();
            while (fm.stringWidth(text) > maxW && text.length() > 3) text = text.substring(0, text.length() - 2) + "…";
            g2.drawString(text, textX, (h - fm.getHeight()) / 2 + fm.getAscent());

            g2.dispose();
        }

        @Override protected void paintFocus(Graphics g, AbstractButton b, Rectangle v, Rectangle t, Rectangle i) {}
    }

    static final class FieldBorder extends AbstractBorder {
        private final Insets pad;
        FieldBorder(int top, int left, int bottom, int right) { pad = new Insets(top, left, bottom, right); }
        @Override public Insets getBorderInsets(Component c) { return (Insets) pad.clone(); }
        @Override public Insets getBorderInsets(Component c, Insets i) { i.set(pad.top, pad.left, pad.bottom, pad.right); return i; }

        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = aa(g);
            boolean focus = c.hasFocus() || (c instanceof JComboBox && ((JComboBox<?>) c).isPopupVisible());
            Shape round = new RoundRectangle2D.Double(x + 0.5, y + 0.5, w - 1, h - 1, 18, 18);
            if (focus) {
                g2.setColor(alpha(TEAL_PRIMARY, 45));
                g2.setStroke(new BasicStroke(3f));
                g2.draw(new RoundRectangle2D.Double(x + 1, y + 1, w - 2, h - 2, 17, 17));
                g2.setColor(TEAL_PRIMARY);
            } else {
                g2.setColor(c.isEnabled() ? BORDER_FIELD : BORDER_LIGHT);
            }
            g2.setStroke(new BasicStroke(1f));
            g2.draw(round);
            g2.dispose();
        }
    }

    private static void repaintOnFocus(JComponent c) {
        if (c.getClientProperty("formula.focusHook") != null) return;
        c.putClientProperty("formula.focusHook", Boolean.TRUE);
        c.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { c.repaint(); }
            @Override public void focusLost(FocusEvent e) { c.repaint(); }
        });
    }

    static void roundBg(Graphics g, JComponent c) {
        Graphics2D g2 = aa(g);
        g2.setColor(c.getBackground());
        g2.fill(new RoundRectangle2D.Double(0.5, 0.5, c.getWidth() - 1, c.getHeight() - 1, 18, 18));
        g2.dispose();
    }

    public static void styleInput(JTextField txt) {
        if (txt instanceof JPasswordField) {
            txt.setUI(new BasicPasswordFieldUI() {
                @Override protected void paintBackground(Graphics g) { roundBg(g, getComponent()); }
            });
        } else {
            txt.setUI(new BasicTextFieldUI() {
                @Override protected void paintBackground(Graphics g) { roundBg(g, getComponent()); }
            });
        }
        txt.setOpaque(false);
        txt.setFont(FONT_INPUT);
        txt.setForeground(INK);
        txt.setDisabledTextColor(TEXT_FAINT);
        txt.setBackground(Color.WHITE);
        txt.setCaretColor(TEAL_PRIMARY);
        txt.setSelectionColor(TEAL_TINT);
        txt.setSelectedTextColor(INK);
        txt.setBorder(new FieldBorder(6, 12, 6, 12));
        repaintOnFocus(txt);
    }

    public static JComponent idCard(String caption, Glyph glyph, JTextComponent source,
                                    String autoNote, String pickedNote) {
        final JPanel card = new JPanel(null) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = aa(g);
                int w = getWidth(), h = getHeight();
                boolean big = h >= 64;
                String val = source == null ? "" : source.getText().trim();
                boolean auto = val.isEmpty();

                g2.setPaint(new GradientPaint(0, 0, new Color(226, 248, 242), w, h, new Color(246, 253, 251)));
                g2.fill(new RoundRectangle2D.Double(0.5, 0.5, w - 1, h - 1, 18, 18));
                g2.setColor(new Color(164, 224, 212));
                g2.setStroke(new BasicStroke(1f));
                g2.draw(new RoundRectangle2D.Double(0.5, 0.5, w - 1, h - 1, 18, 18));

                int d = big ? 40 : 30;
                int cx = 14, cy = (h - d) / 2;
                g2.setPaint(new GradientPaint(cx, cy, TEAL_PRIMARY, cx + d, cy + d, new Color(20, 150, 136)));
                g2.fill(new Ellipse2D.Double(cx, cy, d, d));
                int is = big ? 22 : 17;
                new GlyphIcon(glyph, is, Color.WHITE).paintIcon(this, g2, cx + (d - is) / 2, cy + (d - is) / 2);

                int tx = cx + d + 12;
                g2.setFont(font(Font.BOLD, 11));
                g2.setColor(TEXT_MUTED);
                FontMetrics fm = g2.getFontMetrics();
                Font vf = font(Font.BOLD, big ? 18 : 15);
                Font nf = font(Font.PLAIN, 11);
                FontMetrics vm = g2.getFontMetrics(vf);
                FontMetrics nm = g2.getFontMetrics(nf);
                int total = fm.getHeight() + vm.getHeight() + (big ? nm.getHeight() : 0);
                int y = (h - total) / 2;
                g2.drawString(caption, tx, y + fm.getAscent());
                y += fm.getHeight();
                g2.setFont(vf);
                g2.setColor(auto ? TEAL_PRIMARY : INK);
                String shown = auto ? "Otomatis" : (val.matches("\\d+") ? "#" + val : val);
                g2.drawString(shown, tx, y + vm.getAscent());
                if (big) {
                    y += vm.getHeight();
                    g2.setFont(nf);
                    g2.setColor(TEXT_MUTED);
                    g2.drawString(auto ? autoNote : pickedNote, tx, y + nm.getAscent());
                }
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setToolTipText(autoNote);
        if (source != null) {
            source.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                public void insertUpdate(javax.swing.event.DocumentEvent e) { card.repaint(); }
                public void removeUpdate(javax.swing.event.DocumentEvent e) { card.repaint(); }
                public void changedUpdate(javax.swing.event.DocumentEvent e) { card.repaint(); }
            });
        }
        return card;
    }

    public static void styleAutomaticId(JTextField field) {
        field.setEditable(false);
        field.setToolTipText("Dibuat otomatis saat disimpan.");
        field.setUI(new BasicTextFieldUI() {
            @Override protected void paintBackground(Graphics g) { roundBg(g, getComponent()); }
            @Override protected void paintSafely(Graphics g) {
                super.paintSafely(g);
                if (field.getText().isEmpty()) {
                    Graphics2D g2 = aa(g);
                    g2.setFont(field.getFont());
                    g2.setColor(TEXT_MUTED);
                    FontMetrics fm = g2.getFontMetrics();
                    g2.drawString("Otomatis", field.getInsets().left,
                        (field.getHeight() - fm.getHeight()) / 2 + fm.getAscent());
                    g2.dispose();
                }
            }
        });
        field.setBackground(BG_MAIN);
    }

    public static JTextField searchField(String placeholder) {
        JTextField f = new JTextField() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = aa(g);
                new GlyphIcon(Glyph.SEARCH, 16, hasFocus() ? TEAL_PRIMARY : TEXT_FAINT).paintIcon(this, g2, 11, (getHeight() - 16) / 2);
                if (getText().isEmpty()) {
                    g2.setFont(getFont());
                    g2.setColor(TEXT_FAINT);
                    FontMetrics fm = g2.getFontMetrics();
                    g2.drawString(placeholder, getInsets().left, (getHeight() - fm.getHeight()) / 2 + fm.getAscent());
                }
                g2.dispose();
            }
        };
        styleInput(f);
        f.setBorder(new FieldBorder(6, 34, 6, 12));
        return f;
    }

    public static void styleCombo(JComboBox<?> cb) {
        cb.setUI(new ModernComboUI());
        cb.setFont(FONT_INPUT);
        cb.setForeground(INK);
        cb.setBackground(Color.WHITE);
        cb.setBorder(new FieldBorder(3, 12, 3, 6));
        cb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cb.setOpaque(false);
        cb.setFocusable(true);
        @SuppressWarnings("unchecked") JComboBox<Object> c = (JComboBox<Object>) cb;
        c.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean sel, boolean focus) {
                JLabel l = (JLabel) super.getListCellRendererComponent(list, value, index, sel, false);
                l.setFont(FONT_INPUT);
                l.setBorder(new EmptyBorder(index < 0 ? 0 : 8, index < 0 ? 0 : 12, index < 0 ? 0 : 8, 12));
                if (index < 0) {
                    l.setOpaque(false);
                    l.setForeground(cb.isEnabled() ? INK : TEXT_FAINT);
                } else {
                    l.setOpaque(true);
                    l.setBackground(sel ? TEAL_TINT : Color.WHITE);
                    l.setForeground(sel ? TEAL_DARK : INK);
                }
                return l;
            }
        });
        repaintOnFocus(cb);
    }

    static final class ModernComboUI extends BasicComboBoxUI {
        @Override protected JButton createArrowButton() {
            JButton b = new JButton() {
                @Override public void paint(Graphics g) {
                    Graphics2D g2 = aa(g);
                    boolean on = comboBox != null && comboBox.isEnabled();
                    new GlyphIcon(Glyph.CHEVRON_DOWN, 16, on ? TEXT_MUTED : TEXT_FAINT).paintIcon(this, g2, (getWidth() - 16) / 2, (getHeight() - 16) / 2);
                    g2.dispose();
                }
                @Override public Dimension getPreferredSize() { return new Dimension(26, 20); }
            };
            b.setBorder(BorderFactory.createEmptyBorder());
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setFocusable(false);
            return b;
        }
        @Override public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {}
        @Override public void paint(Graphics g, JComponent c) {
            roundBg(g, c);
            super.paint(g, c);
        }
        @Override protected ComboPopup createPopup() {
            return new BasicComboPopup(comboBox) {
                @Override protected void configurePopup() {
                    super.configurePopup();
                    setBorder(new LineBorder(BORDER_FIELD, 1));
                    getList().setBackground(Color.WHITE);
                }
            };
        }
    }

    public static void styleLabel(JLabel lbl) {
        lbl.setFont(font(Font.BOLD, 13));
        lbl.setForeground(TEXT_MUTED);
    }

    public static void chipLabel(JLabel lbl, Color fill, Color text, Insets pad) {
        lbl.setOpaque(false);
        lbl.setForeground(text);
        lbl.setBorder(new EmptyBorder(pad));
        lbl.setUI(new BasicLabelUI() {
            @Override public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = aa(g);
                g2.setColor(fill);
                g2.fill(new RoundRectangle2D.Double(0, 0, c.getWidth(), c.getHeight(), 20, 20));
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    private static void paintFlatCard(Graphics g, int pad, int w, int h) {
        Graphics2D g2 = aa(g);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Double(pad, pad, w - pad * 2, h - pad * 2, 24, 24));
        g2.dispose();
    }

    public static void cardPanel(JPanel card) {
        final int pad = 6;
        // Styling only: geometry remains owned by the NetBeans .form layout.
        card.setOpaque(false);
        card.setBorder(null);
        card.setUI(new BasicPanelUI() {
            @Override public void paint(Graphics g, JComponent c) { paintFlatCard(g, pad, c.getWidth(), c.getHeight()); }
        });
    }

    public static void logoPanel(JPanel p, boolean onDark) {
        p.setOpaque(false);
        p.setBorder(null);
        p.setUI(new BasicPanelUI() {
            @Override public void paint(Graphics g, JComponent c) {
                int s = Math.min(c.getWidth(), c.getHeight());
                new BrandIcon(s, onDark).paintIcon(c, g, (c.getWidth() - s) / 2, (c.getHeight() - s) / 2);
            }
        });
    }

    public static void iconTile(JPanel p, Glyph glyph, Color bg, Color fg) {
        p.setOpaque(false);
        p.setBorder(null);
        p.setUI(new BasicPanelUI() {
            @Override public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = aa(g);
                g2.setColor(bg);
                g2.fill(new RoundRectangle2D.Double(0, 0, c.getWidth(), c.getHeight(), c.getWidth() / 2.0, c.getHeight() / 2.0));
                int is = Math.round(Math.min(c.getWidth(), c.getHeight()) * 0.54f);
                new GlyphIcon(glyph, is, fg).paintIcon(c, g2, (c.getWidth() - is) / 2, (c.getHeight() - is) / 2);
                g2.dispose();
            }
        });
    }

    static final class SearchBorder extends AbstractBorder {
        private final FieldBorder base = new FieldBorder(6, 34, 6, 12);
        private final String placeholder;
        SearchBorder(String placeholder) { this.placeholder = placeholder; }
        @Override public Insets getBorderInsets(Component c) { return base.getBorderInsets(c); }
        @Override public Insets getBorderInsets(Component c, Insets i) { return base.getBorderInsets(c, i); }
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            base.paintBorder(c, g, x, y, w, h);
            Graphics2D g2 = aa(g);
            new GlyphIcon(Glyph.SEARCH, 16, c.hasFocus() ? TEAL_PRIMARY : TEXT_FAINT).paintIcon(c, g2, x + 11, y + (h - 16) / 2);
            if (c instanceof JTextComponent && ((JTextComponent) c).getText().isEmpty()) {
                g2.setFont(c.getFont());
                g2.setColor(TEXT_FAINT);
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(placeholder, x + 34, y + (h - fm.getHeight()) / 2 + fm.getAscent());
            }
            g2.dispose();
        }
    }

    public static void styleSearch(JTextField f, String placeholder) {
        styleInput(f);
        f.setBorder(new SearchBorder(placeholder));
    }

    public static void styleHeader(JPanel header, JPanel logo, JLabel title, String page) {
        logoPanel(logo, false);
        styleHeader(header, title, page);
        title.setIcon(null);
    }

    public static void styleHeader(JPanel header, JLabel title, String page) {
        header.setBackground(Color.WHITE);
        header.setOpaque(true);
        header.setUI(new BasicPanelUI() {
            @Override public void paint(Graphics g, JComponent c) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });
        title.setFont(font(Font.BOLD, 20));
        title.setForeground(INK);
        title.setIcon(brandIcon(34));
        title.setIconTextGap(14);
        title.setText("<html><span style='color:#6F8A8F;font-weight:normal'>Formula&nbsp;&nbsp;/&nbsp;&nbsp;</span>" + escape(page) + "</html>");
    }

    static String escape(String s) {
        return s == null ? "" : s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    public static void styleSectionTitle(JLabel lbl) {
        lbl.setFont(font(Font.BOLD, 17));
        lbl.setForeground(INK);
    }

    public static void styleCaption(JLabel lbl) {
        lbl.setFont(font(Font.PLAIN, 14));
        lbl.setForeground(TEXT_MUTED);
    }

    private static Color[] chipColors(String v) {
        String s = v.trim().toLowerCase(Locale.ROOT);
        if (s.isEmpty() || s.equals("-")) return null;
        if (s.equals("tersedia") || s.equals("aktif") || s.equals("selesai") || s.equals("diterima") || s.equals("lunas"))
            return new Color[]{GREEN_TINT, GREEN};
        if (s.equals("tidak tersedia") || s.equals("habis") || s.equals("nonaktif") || s.equals("batal") || s.equals("dibatalkan") || s.equals("kedaluwarsa") || s.equals("ditolak"))
            return new Color[]{RED_TINT, RED_DANGER};
        if (s.equals("menunggu") || s.equals("diproses") || s.equals("menipis") || s.equals("pending") || s.equals("proses"))
            return new Color[]{AMBER_TINT, AMBER};
        if (s.equals("dikirim") || s.equals("dalam perjalanan") || s.equals("terkirim"))
            return new Color[]{SKY_TINT, SKY};
        return null;
    }

    static class CellRenderer extends DefaultTableCellRenderer {
        private Color[] chip;
        private boolean selectedRow;
        private boolean firstCol;

        @Override public Component getTableCellRendererComponent(JTable t, Object value, boolean sel, boolean focus, int row, int col) {
            String text = value == null ? "" : value.toString();
            super.getTableCellRendererComponent(t, text, false, false, row, col);
            int hover = t.getClientProperty("formula.hoverRow") instanceof Integer ? (Integer) t.getClientProperty("formula.hoverRow") : -1;
            selectedRow = sel;
            firstCol = col == 0;
            setFont(FONT_TABLE);
            setForeground(INK);
            setOpaque(true);
            setBackground(sel ? ROW_SELECT : row == hover ? new Color(236, 244, 242) : row % 2 == 1 ? new Color(248, 251, 250) : Color.WHITE);
            setBorder(new EmptyBorder(0, 14, 0, 10));
            chip = t.getColumnName(col).toLowerCase(Locale.ROOT).contains("status") ? chipColors(text) : null;
            if (chip != null) setText("");
            return this;
        }

        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = aa(g);
            if (selectedRow && firstCol) {
                g2.setColor(TEAL_PRIMARY);
                g2.fillRect(0, 0, 3, getHeight());
            }
            if (chip != null) {
                Object tt = getClientProperty("formula.text");
                String text = tt == null ? "" : tt.toString();
                g2.setFont(font(Font.BOLD, 13));
                FontMetrics fm = g2.getFontMetrics();
                int w = fm.stringWidth(text) + 26, h = 26;
                int x = 14, y = (getHeight() - h) / 2;
                g2.setColor(chip[0]);
                g2.fill(new RoundRectangle2D.Double(x, y, w, h, h, h));
                g2.setColor(chip[1]);
                g2.fill(new Ellipse2D.Double(x + 10, y + h / 2.0 - 2.5, 5, 5));
                g2.drawString(text, x + 20, y + (h - fm.getHeight()) / 2 + fm.getAscent());
            }
            g2.dispose();
        }

        @Override public void setText(String t) {
            super.setText(t);
        }

        void chipText(String t) { putClientProperty("formula.text", t); }
    }

    public static void styleTable(JTable table) {
        table.setFont(FONT_TABLE);
        table.setRowHeight(44);
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setGridColor(new Color(233, 239, 237));
        table.setSelectionBackground(ROW_SELECT);
        table.setSelectionForeground(INK);
        table.setForeground(INK);
        table.setBackground(Color.WHITE);
        table.setOpaque(false);
        table.setFillsViewportHeight(true);
        table.setFocusable(true);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setOpaque(true);
        header.setBorder(BorderFactory.createEmptyBorder());
        header.setPreferredSize(new Dimension(header.getWidth(), 42));
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int row, int col) {
                String text = v == null ? "" : v.toString();
                RowSorter<?> rs = t.getRowSorter();
                if (rs != null && !rs.getSortKeys().isEmpty() && rs.getSortKeys().get(0).getColumn() == t.convertColumnIndexToModel(col)) {
                    text += rs.getSortKeys().get(0).getSortOrder() == SortOrder.DESCENDING ? "  ▾" : "  ▴";
                }
                super.getTableCellRendererComponent(t, text, false, false, row, col);
                setFont(FONT_TABLE_HEADER);
                setForeground(TEXT_MUTED);
                setBackground(Color.WHITE);
                setOpaque(true);
                setHorizontalAlignment(SwingConstants.LEFT);
                setBorder(new EmptyBorder(0, 14, 0, 10));
                return this;
            }
        });

        CellRenderer renderer = new CellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable t, Object value, boolean sel, boolean focus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, value, sel, focus, row, col);
                chipText(value == null ? "" : value.toString().trim());
                return c;
            }
        };
        table.setDefaultRenderer(Object.class, renderer);
        table.setDefaultRenderer(String.class, renderer);
        table.setDefaultRenderer(Integer.class, renderer);
        table.setDefaultRenderer(Long.class, renderer);
        table.setDefaultRenderer(Number.class, renderer);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(null);
        }

        if (table.getClientProperty("formula.hoverHook") == null) {
            table.putClientProperty("formula.hoverHook", Boolean.TRUE);
            MouseAdapter ma = new MouseAdapter() {
                @Override public void mouseMoved(MouseEvent e) {
                    int r = table.rowAtPoint(e.getPoint());
                    Object old = table.getClientProperty("formula.hoverRow");
                    if (old instanceof Integer && (Integer) old == r) return;
                    table.putClientProperty("formula.hoverRow", r);
                    table.repaint();
                }
                @Override public void mouseExited(MouseEvent e) {
                    table.putClientProperty("formula.hoverRow", -1);
                    table.repaint();
                }
            };
            table.addMouseMotionListener(ma);
            table.addMouseListener(ma);
        }

        Container p = SwingUtilities.getAncestorOfClass(JScrollPane.class, table);
        if (p instanceof JScrollPane) styleScroll((JScrollPane) p, table);
    }

    public static void styleScroll(JScrollPane sp, JTable table) {
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.setBackground(Color.WHITE);
        sp.getViewport().setBackground(Color.WHITE);
        sp.getVerticalScrollBar().setUI(new SlimScrollBarUI());
        sp.getHorizontalScrollBar().setUI(new SlimScrollBarUI());
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(10, 10));
        sp.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 10));
        sp.getVerticalScrollBar().setUnitIncrement(24);
        sp.setCorner(JScrollPane.LOWER_RIGHT_CORNER, new JPanel() {{ setBackground(Color.WHITE); }});
        if (table != null) {
            sp.setViewportBorder(new EmptyStateBorder(table));
            TableModel model = table.getModel();
            model.addTableModelListener(e -> sp.repaint());
            RowSorter<?> rs = table.getRowSorter();
            if (rs != null) rs.addRowSorterListener(e -> sp.repaint());
        }
    }

    static final class EmptyStateBorder extends AbstractBorder {
        private final JTable table;
        EmptyStateBorder(JTable t) { this.table = t; }
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            if (table.getRowCount() > 0) return;
            boolean filtered = table.getModel().getRowCount() > 0;
            Graphics2D g2 = aa(g);
            int cx = x + w / 2, cy = y + Math.max(h / 2 - 8, 40);
            new GlyphIcon(Glyph.INBOX, 30, new Color(176, 195, 192)).paintIcon(c, g2, cx - 15, cy - 38);
            g2.setFont(font(Font.BOLD, 15));
            g2.setColor(TEXT_MUTED);
            String t1 = filtered ? "Tidak ada hasil" : "Belum ada data";
            g2.drawString(t1, cx - g2.getFontMetrics().stringWidth(t1) / 2, cy + 8);
            g2.dispose();
        }
    }

    static final class SlimScrollBarUI extends BasicScrollBarUI {
        @Override protected void configureScrollBarColors() {
            thumbColor = new Color(196, 210, 207);
            trackColor = Color.WHITE;
        }
        @Override protected JButton createDecreaseButton(int o) { return zero(); }
        @Override protected JButton createIncreaseButton(int o) { return zero(); }
        private JButton zero() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            b.setMinimumSize(new Dimension(0, 0));
            b.setMaximumSize(new Dimension(0, 0));
            return b;
        }
        @Override protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
            g.setColor(Color.WHITE);
            g.fillRect(r.x, r.y, r.width, r.height);
        }
        @Override protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            if (r.isEmpty() || !scrollbar.isEnabled()) return;
            Graphics2D g2 = aa(g);
            g2.setColor(isThumbRollover() || isDragging ? new Color(150, 172, 168) : thumbColor);
            g2.fill(new RoundRectangle2D.Double(r.x + 2, r.y + 2, r.width - 4, r.height - 4, 8, 8));
            g2.dispose();
        }
    }

    public static void statusLine(JLabel lbl, String text, boolean error) {
        if (!error) {
            lbl.setText("");
            lbl.setIcon(null);
            return;
        }
        lbl.setText(text);
        lbl.setFont(font(Font.PLAIN, 13));
        lbl.setForeground(RED_DANGER);
        lbl.setIcon(icon(Glyph.DOT, 10, RED_DANGER));
        lbl.setIconTextGap(8);
    }
}
