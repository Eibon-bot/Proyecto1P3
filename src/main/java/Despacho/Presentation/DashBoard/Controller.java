package Despacho.Presentation.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    private final ViewDB view;
    private final Model model;

    public Controller(ViewDB view, Model model) {
        this.view = view;
        this.model = model;
        bind();
    }

    public void init() {
        ensureCombosPopulated();
        ensureDefaultsSelected();

        DefaultListModel<String> lm = new DefaultListModel<>();
        for (String n : model.getMedicamentosNombres()) lm.addElement(n);
        view.getMedList().setModel(lm);

        applySelection();
    }

    private void bind() {
        view.getAplicarSeleccionButton().addActionListener(e -> applySelection());
        view.getAplicarRangoButton().addActionListener(e -> applySelection());
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
        List<String> meds = view.getMedList().getSelectedValuesList();
        if (meds == null || meds.isEmpty()) meds = Arrays.asList(model.getMedicamentosNombres());

        int y1 = getInt(view.getDesdeYear(), 2023);
        int m1 = getInt(view.getDesdeMonth(), 1);
        int y2 = getInt(view.getHastaYear(), 2025);
        int m2 = getInt(view.getHastaMonth(), 12);

        List<LineChart.Series> series = new ArrayList<>();
        for (String med : meds) {
            series.add(new LineChart.Series(med, model.seriesFor(med, y1, m1, y2, m2)));
        }
        view.getLineChart().setSeries(series);

        List<PieChart.Slice> slices = new ArrayList<>();
        int idx = 0;
        Color[] colors = {new Color(220,20,60), new Color(30,144,255), new Color(34,139,34),
                new Color(255,140,0), new Color(128,0,128), new Color(70,130,180)};
        for (String med : meds) {
            int total = model.totalFor(med, y1, m1, y2, m2);
            if (total > 0) {
                slices.add(new PieChart.Slice(med, total, colors[idx % colors.length]));
                idx++;
            }
        }
        if (slices.isEmpty()) slices.add(new PieChart.Slice("Sin datos", 1, Color.LIGHT_GRAY));
        view.getPieChart().setData(slices);
    }
}

