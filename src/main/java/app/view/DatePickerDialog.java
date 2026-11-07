package app.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class DatePickerDialog {
    private DatePickerDialog() {}

    public static LocalDate pilih(Component parent, LocalDate initial) {
        LocalDate awal = initial == null ? LocalDate.of(2000, 1, 1) : initial;
        JDialog dialog = new JDialog(javax.swing.SwingUtilities.getWindowAncestor(parent), "Pilih Tanggal Lahir", JDialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getRootPane().setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        String[] months = new String[12];
        for (int m = 1; m <= 12; m++) months[m - 1] = java.time.Month.of(m).getDisplayName(TextStyle.FULL, new Locale("id", "ID"));
        JComboBox<String> monthBox = new JComboBox<>(months);
        monthBox.setSelectedIndex(awal.getMonthValue() - 1);
        Integer[] years = new Integer[LocalDate.now().getYear() - 1899];
        for (int i = 0; i < years.length; i++) years[i] = LocalDate.now().getYear() - i;
        JComboBox<Integer> yearBox = new JComboBox<>(years);
        yearBox.setEditable(true);
        yearBox.setSelectedItem(awal.getYear());

        JPanel top = new JPanel(new GridLayout(1, 2, 8, 0));
        top.add(monthBox);
        top.add(yearBox);
        dialog.add(top, BorderLayout.NORTH);

        JPanel calendar = new JPanel(new GridLayout(7, 7, 5, 5));
        String[] weekdays = {"Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab"};
        for (String day : weekdays) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(Font.BOLD, 12f));
            calendar.add(label);
        }
        JButton[] dayButtons = new JButton[42];
        final LocalDate[] selected = {null};
        for (int i = 0; i < dayButtons.length; i++) {
            JButton button = new JButton();
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(56, 42));
            button.setMinimumSize(new Dimension(48, 38));
            button.setMargin(new java.awt.Insets(0, 0, 0, 0));
            button.setFont(new Font("Dialog", Font.BOLD, 13));
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.putClientProperty("JButton.buttonType", "square");
            dayButtons[i] = button;
            calendar.add(button);
        }
        dialog.add(calendar, BorderLayout.CENTER);

        Runnable refresh = () -> {
            int year;
            try { year = Integer.parseInt(String.valueOf(yearBox.getEditor().getItem()).trim()); }
            catch (Exception ex) { year = awal.getYear(); }
            if (year < 1900 || year > LocalDate.now().getYear()) year = awal.getYear();
            YearMonth ym = YearMonth.of(year, monthBox.getSelectedIndex() + 1);
            int offset = ym.atDay(1).getDayOfWeek().getValue() % 7;
            for (int i = 0; i < dayButtons.length; i++) {
                int day = i - offset + 1;
                JButton b = dayButtons[i];
                for (java.awt.event.ActionListener al : b.getActionListeners()) b.removeActionListener(al);
                if (day < 1 || day > ym.lengthOfMonth()) {
                    b.setText(""); b.setEnabled(false); b.setBackground(null);
                } else {
                    final LocalDate date = ym.atDay(day);
                    b.setText(Integer.toString(day)); b.setEnabled(true);
                    b.setBackground(date.equals(awal) ? new java.awt.Color(220, 245, 229) : javax.swing.UIManager.getColor("Button.background"));
                    b.addActionListener(e -> { selected[0] = date; dialog.dispose(); });
                }
            }
            calendar.revalidate(); calendar.repaint();
        };
        monthBox.addActionListener(e -> refresh.run());
        yearBox.addActionListener(e -> refresh.run());
        refresh.run();

        JPanel bottom = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        JButton cancel = new JButton("Batal");
        cancel.addActionListener(e -> dialog.dispose());
        bottom.add(cancel);
        dialog.add(bottom, BorderLayout.SOUTH);
        dialog.setMinimumSize(new Dimension(470, 440));
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        return selected[0];
    }
}
