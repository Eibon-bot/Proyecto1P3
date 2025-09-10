package Despacho.Presentation.DashBoard;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LineChart extends JPanel {
    private DefaultCategoryDataset dataset;

    public LineChart() {
        setLayout(new BorderLayout());
        dataset = new DefaultCategoryDataset();

        JFreeChart chart = ChartFactory.createLineChart(
                "Medicamentos",
                "Mes",
                "Cantidad",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 300));
        add(chartPanel, BorderLayout.CENTER);
    }

    public void setSeries(List<Series> seriesList) {
        dataset.clear();
        int mes = 1;
        for (Series s : seriesList) {
            int i = 1;
            for (Integer val : s.values) {
                dataset.addValue(val, s.name, "" + i);
                i++;
            }
            mes++;
        }
    }

    public static class Series {
        public final String name;
        public final List<Integer> values;

        public Series(String name, List<Integer> values) {
            this.name = name;
            this.values = values;
        }
    }
}
