package Despacho.Presentation.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LineChart extends JPanel {
    public static class Series {
        public final String name;
        public final List<Integer> values;

        public Series(String name, List<Integer> values) {
            this.name = name;
            this.values = values;
        }
    }

    private final List<Series> series = new ArrayList<>();

    public LineChart() {
        setBackground(Color.WHITE);
    }

    public void setSeries(List<Series> data) {
        series.clear();
        if (data != null) series.addAll(data);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (series.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        int step = w / (series.get(0).values.size() + 1);

        int colorIdx = 0;
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};

        for (Series s : series) {
            g2.setColor(colors[colorIdx % colors.length]);
            int prevX = step;
            int prevY = h - s.values.get(0) * (h - 40) / 50;
            for (int i = 1; i < s.values.size(); i++) {
                int x = (i + 1) * step;
                int y = h - s.values.get(i) * (h - 40) / 50;
                g2.drawLine(prevX, prevY, x, y);
                prevX = x;
                prevY = y;
            }
            colorIdx++;
        }
    }
}
