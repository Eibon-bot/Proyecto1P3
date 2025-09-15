package Despacho.Presentation.DashBoard;

import Despacho.Logic.Entidades.Medicamento;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Controller {
    private final ViewDB view;
    private final Model model;

    public Controller(ViewDB view, Model model) {
        this.view = view;
        this.model = model;

        view.aplicarSeleccionButton.addActionListener(e -> {
            Medicamento seleccionado = (Medicamento) view.comboBox3.getSelectedItem();
            if (seleccionado != null && !view.listModel.contains(seleccionado)) {
                view.listModel.addElement(seleccionado);
                view.list1.setSelectedValue(seleccionado, true);
            }
        });

        view.aplicarRangoButton.addActionListener(e -> actualizarGraficos());
    }

    public void init() {
        view.comboBox3.removeAllItems();
        for (Medicamento m : model.getTodosLosMedicamentos()) {
            view.comboBox3.addItem(m);
        }

        view.DesdeMes.removeAllItems();
        view.HastaMes.removeAllItems();
        for (String mes : model.getMesesDisponibles()) {
            view.DesdeMes.addItem(mes);
            view.HastaMes.addItem(mes);
        }

        view.DesdeAño.removeAllItems();
        view.HastaAño.removeAllItems();
        for (Integer año : model.getAniosDisponibles()) {
            view.DesdeAño.addItem(año);
            view.HastaAño.addItem(año);
        }

        if (view.DesdeAño.getItemCount() > 0)
            view.DesdeAño.setSelectedIndex(0);
        if (view.HastaAño.getItemCount() > 0)
            view.HastaAño.setSelectedIndex(view.HastaAño.getItemCount() - 1);
    }

    private void actualizarGraficos() {
        int añoDesde = (int) view.DesdeAño.getSelectedItem();
        int añoHasta = (int) view.HastaAño.getSelectedItem();
        int mesDesde = parseMes((String) view.DesdeMes.getSelectedItem());
        int mesHasta = parseMes((String) view.HastaMes.getSelectedItem());

        LocalDate desde = LocalDate.of(añoDesde, mesDesde, 1);
        LocalDate hasta = LocalDate.of(añoHasta, mesHasta, 1).withDayOfMonth(28).plusDays(4).withDayOfMonth(1).minusDays(1);

        List<Medicamento> seleccionados = view.list1.getSelectedValuesList();

        actualizarGraficoLineal(model.contarMedicamentosPorMes(seleccionados, desde, hasta));
        actualizarGraficoPastel(model.contarEstadosRecetas(desde, hasta));
    }

    private void actualizarGraficoLineal(Map<String, Map<String, Integer>> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (String medicamento : data.keySet()) {
            for (Map.Entry<String, Integer> entry : data.get(medicamento).entrySet()) {
                dataset.addValue(entry.getValue(), medicamento, entry.getKey());
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Medicamentos",
                "Mes",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        view.lineChart.removeAll();
        view.lineChart.add(new ChartPanel(chart), BorderLayout.CENTER);
        view.lineChart.revalidate();
    }

    private void actualizarGraficoPastel(Map<String, Long> data) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (Map.Entry<String, Long> entry : data.entrySet()) {
            pieDataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Recetas",
                pieDataset,
                true, true, false
        );

        view.pieChart.removeAll();
        view.pieChart.add(new ChartPanel(pieChart), BorderLayout.CENTER);
        view.pieChart.revalidate();
    }

    private int parseMes(String str) {
        if (str == null) return 1;
        try {
            return Integer.parseInt(str.split("-")[0]);
        } catch (Exception e) {
            return 1;
        }
    }
}
