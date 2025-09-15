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

public class Controller {
    private final ViewDB view;
    private final Model model;

    public Controller(ViewDB view, Model model) {
        this.view = view;
        this.model = model;

        this.view.setModel(model);
        this.view.setController(this);
        this.view.init();
        this.registerEvents();
    }

    private void registerEvents() {
        view.aplicarSeleccionButton.addActionListener(e -> {
            Medicamento seleccionado = (Medicamento) view.comboBox3.getSelectedItem();
            if (seleccionado != null && !view.listModel.contains(seleccionado)) {
                view.listModel.addElement(seleccionado);
                view.list1.setSelectedValue(seleccionado, true);
            }
        });

        view.aplicarRangoButton.addActionListener(e -> actualizarGraficos());
    }

    public void actualizarGraficos() {
        if (view.DesdeAño.getSelectedItem() == null || view.HastaAño.getSelectedItem() == null ||
                view.DesdeMes.getSelectedItem() == null || view.HastaMes.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(view.panel1, "Seleccione correctamente el rango de fechas.");
            return;
        }

        int añoDesde = (int) view.DesdeAño.getSelectedItem();
        int añoHasta = (int) view.HastaAño.getSelectedItem();
        int mesDesde = parseMes((String) view.DesdeMes.getSelectedItem());
        int mesHasta = parseMes((String) view.HastaMes.getSelectedItem());

        LocalDate desde = LocalDate.of(añoDesde, mesDesde, 1);
        LocalDate hasta = LocalDate.of(añoHasta, mesHasta, 1).withDayOfMonth(28).plusDays(4).withDayOfMonth(1).minusDays(1);

        List<Medicamento> seleccionados = view.list1.getSelectedValuesList();

        if (seleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(view.panel1, "Seleccione al menos un medicamento.");
            return;
        }


        Map<String, Map<String, Integer>> datosLineChart = model.getCantidadMedicamentosPorMes(seleccionados, desde, hasta);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String nombre : datosLineChart.keySet()) {
            for (Map.Entry<String, Integer> entry : datosLineChart.get(nombre).entrySet()) {
                dataset.addValue(entry.getValue(), nombre, entry.getKey());
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Medicamentos Recetados", "Mes", "Cantidad",
                dataset, PlotOrientation.VERTICAL,
                true, true, false
        );

        view.lineChart.removeAll();
        view.lineChart.add(new ChartPanel(chart), BorderLayout.CENTER);
        view.lineChart.revalidate();
        view.lineChart.repaint();


        Map<String, Long> estados = model.getEstadosRecetas(desde, hasta);
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        for (Map.Entry<String, Long> entry : estados.entrySet()) {
            pieDataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart pie = ChartFactory.createPieChart("Recetas", pieDataset, true, true, false);

        view.pieChart.removeAll();
        view.pieChart.add(new ChartPanel(pie), BorderLayout.CENTER);
        view.pieChart.revalidate();
        view.pieChart.repaint();
    }

    private int parseMes(String str) {
        try {
            return Integer.parseInt(str.split("-")[0].trim());
        } catch (Exception e) {
            return 1;
        }
    }
}
