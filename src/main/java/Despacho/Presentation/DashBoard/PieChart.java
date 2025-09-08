package Despacho.Presentation.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PieChart extends JPanel {
    public static class Slice {
        public final String label;
        public final int value;
        public final Color color;

        public Slice(String label, int value, Color color) {
            this.label = label;
            this.value = value;
            this.color = color;
        }
    }

    private final List<Slice> slices = new ArrayList<>();

    public PieChart() {
        setBackground(Color.WHITE);
    }

    public void setData(List<Slice> data) {
        slices.clear();
        if (data != null) slices.addAll(data);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (slices.isEmpty()) return;

        int total = slices.stream().mapToInt(s -> s.value).sum();
        int start = 0;
        for (Slice s : slices) {
            int angle = (int) Math.round(360.0 * s.value / total);
            g.setColor(s.color);
            g.fillArc(10, 10, getWidth() - 20, getHeight() - 20, start, angle);
            start += angle;
        }
    }
}
