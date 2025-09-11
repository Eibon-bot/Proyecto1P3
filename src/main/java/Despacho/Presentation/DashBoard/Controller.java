package Despacho.Presentation.DashBoard;

import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Service;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Controller {
    private final ViewDB view;
    private final Model model;

    private final LinkedHashMap<String, Medicamento> medByName = new LinkedHashMap<>();
    private DefaultListModel<Medicamento> selectedModel = new DefaultListModel<>();

    public Controller(ViewDB view, Model model) {
        this.view = view;
        this.model = model;
        bind();
    }

    public void init() {
        ensureCombosPopulated();
        ensureDefaultsSelected();

        List<Medicamento> meds = Service.instance().findAllMedicamento();
        medByName.clear();
        for (Medicamento m : meds) {
            medByName.put(m.getCodigo(), m);
        }

        DefaultComboBoxModel<String> cbm =
                new DefaultComboBoxModel<>(medByName.keySet().toArray(new String[0]));
        view.getMedsCombo().setModel(cbm);

        selectedModel = new DefaultListModel<>();
        view.getMedList().setModel(selectedModel);
        view.getMedList().setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Medicamento m = (Medicamento) value;
                lbl.setText(m.getCodigo() + " — " + m.getNombre() + " (" + m.getPresentacion() + ")");
                return lbl;
            }
        });

        applySelection();
    }

    private void bind() {
        view.getAplicarSeleccionButton().addActionListener(e -> {
            String code = (String) view.getMedsCombo().getSelectedItem();
            Medicamento m = code == null ? null : medByName.get(code);
            if (m != null && !containsByCode(selectedModel, m.getCodigo())) {
                selectedModel.addElement(m);
            }
            applySelection();
        });
        view.getAplicarRangoButton().addActionListener(e -> applySelection());
    }

    private boolean containsByCode(DefaultListModel<Medicamento> model, String codigo) {
        for (int i = 0; i < model.size(); i++) {
            if (codigo.equalsIgnoreCase(model.get(i).getCodigo())) return true;
        }
        return false;
    }

    private void ensureCombosPopulated() {
        if (view.getDesdeYear().getItemCount() == 0 || view.getHastaYear().getItemCount() == 0) {
            DefaultComboBoxModel<Integer> years = new DefaultComboBoxModel<>();
            for (int y = 2020; y <= 2026; y++) years.addElement(y);
            view.getDesdeYear().setModel(years);
            DefaultComboBoxModel<Integer> years2 = new DefaultComboBoxModel<>();
            for (int y = 2020; y <= 2026; y++) years2.addElement(y);
            view.getHastaYear().setModel(years2);
        }
        if (view.getDesdeMonth().getItemCount() == 0 || view.getHastaMonth().getItemCount() == 0) {
            DefaultComboBoxModel<Integer> months = new DefaultComboBoxModel<>();
            for (int m = 1; m <= 12; m++) months.addElement(m);
            view.getDesdeMonth().setModel(months);
            DefaultComboBoxModel<Integer> months2 = new DefaultComboBoxModel<>();
            for (int m = 1; m <= 12; m++) months2.addElement(m);
            view.getHastaMonth().setModel(months2);
        }
    }

    private void ensureDefaultsSelected() {
        if (view.getDesdeYear().getSelectedItem() == null) view.getDesdeYear().setSelectedItem(2023);
        if (view.getDesdeMonth().getSelectedItem() == null) view.getDesdeMonth().setSelectedItem(1);
        if (view.getHastaYear().getSelectedItem() == null) view.getHastaYear().setSelectedItem(2025);
        if (view.getHastaMonth().getSelectedItem() == null) view.getHastaMonth().setSelectedItem(12);
    }

    private int getInt(JComboBox box, int def) {
        Object o = box.getSelectedItem();
        if (o instanceof Integer) return (Integer) o;
        if (o instanceof String) {
            try { return Integer.parseInt((String) o); } catch (Exception ignored) {}
        }
        return def;
    }

    private void applySelection() {
        int y1 = getInt(view.getDesdeYear(), 2023);
        int m1 = getInt(view.getDesdeMonth(), 1);
        int y2 = getInt(view.getHastaYear(), 2025);
        int m2 = getInt(view.getHastaMonth(), 12);


        List<String> medCodes = new ArrayList<>();
        if (selectedModel.size() == 0) {
            for (Medicamento m : medByName.values()) {
                medCodes.add(m.getCodigo());
            }
        } else {
            for (int i = 0; i < selectedModel.size(); i++) {
                medCodes.add(selectedModel.get(i).getCodigo());
            }
        }

        List<LineChart.Series> series = new ArrayList<>();
        for (String medCode : medCodes) {
            series.add(new LineChart.Series(medCode, model.seriesFor(medCode, y1, m1, y2, m2)));
        }
        view.getLineChart().setSeries(series);


        Map<String, Integer> estados = model.recetasPorEstado(y1, m1, y2, m2);

        List<PieChart.Slice> slices = new ArrayList<>();
        Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
        String[] labels = {"Proceso", "Confeccionada", "Entregada", "Lista"};

        for (int i = 0; i < labels.length; i++) {
            int count = estados.getOrDefault(labels[i], 0);
            if (count > 0) {
                slices.add(new PieChart.Slice(labels[i], count, colors[i]));
            }
        }

        if (slices.isEmpty()) slices.add(new PieChart.Slice("Sin datos", 1, Color.LIGHT_GRAY));
        view.getPieChart().setData(slices);
    }
}

