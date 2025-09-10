package Despacho.Presentation.DashBoard;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.AttributedString;
import java.util.List;

public class PieChart extends JPanel {
    private DefaultPieDataset dataset;
    private JFreeChart chart;

    public PieChart() {
        setLayout(new BorderLayout());
        dataset = new DefaultPieDataset();

        chart = ChartFactory.createPieChart(
                "Recetas",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setCircular(true);
        plot.setBackgroundPaint(Color.WHITE);


        plot.setLabelGenerator(new PieSectionLabelGenerator() {
            @Override
            public String generateSectionLabel(PieDataset ds, Comparable key) {
                Number value = ds.getValue(key);
                if (value == null) return key.toString();

                double total = 0.0;
                for (int i = 0; i < ds.getItemCount(); i++) {
                    Number v = ds.getValue(i);
                    if (v != null) total += v.doubleValue();
                }

                double percent = total > 0 ? value.doubleValue() / total : 0.0;
                return key.toString() + " = " + value.intValue() + " (" +
                        new DecimalFormat("0%").format(percent) + ")";
            }

            @Override
            public AttributedString generateAttributedSectionLabel(PieDataset dataset, Comparable key) {
                return null;
            }
        });

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        add(chartPanel, BorderLayout.CENTER);
    }

    public void setData(List<Slice> slices) {
        dataset.clear();
        for (Slice s : slices) {
            dataset.setValue(s.label, s.value);
        }
    }

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
}
