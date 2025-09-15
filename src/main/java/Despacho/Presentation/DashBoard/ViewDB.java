package Despacho.Presentation.DashBoard;

import Despacho.Logic.Entidades.Medicamento;

import javax.swing.*;
import java.awt.*;

public class ViewDB {
    public JPanel panel1;
    public JComboBox<String> DesdeMes;
    public JComboBox<String> HastaMes;
    public JComboBox<Integer> DesdeAño;
    public JComboBox<Integer> HastaAño;
    public JButton aplicarRangoButton;
    public JList<Medicamento> list1;
    public JComboBox<Medicamento> comboBox3;
    public JButton aplicarSeleccionButton;
    public JPanel lineChart;
    public JPanel pieChart;

    public DefaultListModel<Medicamento> listModel = new DefaultListModel<>();

    public ViewDB() {
        list1.setModel(listModel);
    }

    public JPanel getPanel() {
        return panel1;
    }

    private void createUIComponents() {
        lineChart = new JPanel(new BorderLayout());
        pieChart = new JPanel(new BorderLayout());
    }
}
