package Despacho.Presentation.DashBoard;
import Despacho.Logic.Entidades.Medicamento;
import javax.swing.*;
public class ViewDB {


    private JPanel panel1;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton aplicarRangoButton;
    private JList<Medicamento> medList;
    private JButton aplicarSeleccionButton;
    private JPanel lineChart;
    private JPanel pieChart;
    private JComboBox<String> comboBox3;
    public JPanel getPanel() { return panel1; }




    public JComboBox<Integer> getDesdeYear() { return comboBox1; }
    public JComboBox<Integer> getDesdeMonth() { return comboBox2; }
    public JComboBox<Integer> getHastaYear() { return comboBox4; }
    public JComboBox<Integer> getHastaMonth() { return comboBox5; }




    public JButton getAplicarRangoButton() { return aplicarRangoButton; }
    public JList<Medicamento> getMedList() { return medList; }
    public JButton getAplicarSeleccionButton() { return aplicarSeleccionButton; }
    public JComboBox<String> getMedsCombo() { return comboBox3; }
    public LineChart getLineChart() { return (LineChart) lineChart; }
    public PieChart getPieChart() { return (PieChart) pieChart; }
    private void createUIComponents() { lineChart = new LineChart(); pieChart = new PieChart(); }

}


