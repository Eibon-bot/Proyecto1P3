package Despacho.Presentation.DashBoard;

import Despacho.Logic.Entidades.Medicamento;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;




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

    private Controller controller;


    private Model model;

    public ViewDB() {
        list1.setModel(listModel);

        aplicarSeleccionButton.addActionListener(e -> {
            Medicamento seleccionado = (Medicamento) comboBox3.getSelectedItem();
            if (seleccionado != null && !listModel.contains(seleccionado)) {
                listModel.addElement(seleccionado);
                list1.setSelectedValue(seleccionado, true);
            }
        });

        aplicarRangoButton.addActionListener(e -> {
            actualizarGraficos();
        });
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void init() {
        comboBox3.removeAllItems();
        for (Medicamento m : model.getTodosLosMedicamentos()) {
            comboBox3.addItem(m);
        }

        DesdeMes.removeAllItems();
        HastaMes.removeAllItems();
        for (String mes : model.getMesesDisponibles()) {
            DesdeMes.addItem(mes);
            HastaMes.addItem(mes);
        }

        DesdeAño.removeAllItems();
        HastaAño.removeAllItems();
        for (Integer año : model.getAniosDisponibles()) {
            DesdeAño.addItem(año);
            HastaAño.addItem(año);
        }

        if (DesdeAño.getItemCount() > 0)
            DesdeAño.setSelectedIndex(0);
        if (HastaAño.getItemCount() > 0)
            HastaAño.setSelectedIndex(HastaAño.getItemCount() - 1);
    }

    private void actualizarGraficos() {
        if (model == null) return;

        int añoDesde = (int) DesdeAño.getSelectedItem();
        int añoHasta = (int) HastaAño.getSelectedItem();
        int mesDesde = parseMes((String) DesdeMes.getSelectedItem());
        int mesHasta = parseMes((String) HastaMes.getSelectedItem());

        LocalDate desde = LocalDate.of(añoDesde, mesDesde, 1);
        LocalDate hasta = LocalDate.of(añoHasta, mesHasta, 1).withDayOfMonth(28).plusDays(4).withDayOfMonth(1).minusDays(1);

        List<Medicamento> seleccionados = list1.getSelectedValuesList();

        // Line chart
        Map<String, Map<String, Integer>> datosLineChart = model.getCantidadMedicamentosPorMes(seleccionados, desde, hasta);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String nombre : datosLineChart.keySet()) {
            for (Map.Entry<String, Integer> entry : datosLineChart.get(nombre).entrySet()) {
                dataset.addValue(entry.getValue(), nombre, entry.getKey());
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Medicamentos", "Mes", "Cantidad",
                dataset, PlotOrientation.VERTICAL,
                true, true, false
        );

        lineChart.removeAll();
        lineChart.add(new ChartPanel(chart), BorderLayout.CENTER);
        lineChart.revalidate();
        lineChart.repaint();

        // Pie chart
        Map<String, Long> estados = model.getEstadosRecetas(desde, hasta);
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        for (Map.Entry<String, Long> entry : estados.entrySet()) {
            pieDataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart pie = ChartFactory.createPieChart(
                "Recetas", pieDataset, true, true, false
        );

        pieChart.removeAll();
        pieChart.add(new ChartPanel(pie), BorderLayout.CENTER);
        pieChart.revalidate();
        pieChart.repaint();
    }

    private int parseMes(String str) {
        if (str == null) return 1;
        try {
            return Integer.parseInt(str.split("-")[0]);
        } catch (Exception e) {
            return 1;
        }
    }

    public JPanel getPanel() {
        return panel1;
    }

    private void createUIComponents() {
        lineChart = new JPanel(new BorderLayout());
        pieChart = new JPanel(new BorderLayout());
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }


}
