
package Despacho.Presentation.DashBoard;

import javax.swing.*;

public class ViewDB {
    private JPanel panel1;

    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox4;
    private JComboBox comboBox5;

    private JButton aplicarRangoButton;
    private JList<String> medList;
    private JButton aplicarSeleccionButton;

    private JPanel lineChart;
    private JPanel pieChart;

    public JPanel getPanel() { return panel1; }

    public JComboBox getDesdeYear() { return comboBox1; }
    public JComboBox getDesdeMonth() { return comboBox2; }
    public JComboBox getHastaYear() { return comboBox4; }
    public JComboBox getHastaMonth() { return comboBox5; }

    public JButton getAplicarRangoButton() { return aplicarRangoButton; }
    public JList<String> getMedList() { return medList; }
    public JButton getAplicarSeleccionButton() { return aplicarSeleccionButton; }

    public LineChart getLineChart() { return (LineChart) lineChart; }
    public PieChart getPieChart() { return (PieChart) pieChart; }

    private void createUIComponents() {
        lineChart = new LineChart();
        pieChart = new PieChart();
    }
}
