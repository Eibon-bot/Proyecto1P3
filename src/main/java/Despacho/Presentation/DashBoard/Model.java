package Despacho.Presentation.DashBoard;

import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private final List<LineChart.Series> series = new ArrayList<>();
    private final List<PieChart.Slice> pie = new ArrayList<>();
    private final List<Medicamento> allMeds;

    public Model() {
        allMeds = new ArrayList<>(Service.instance().findAllMedicamento());
    }

    public List<LineChart.Series> getSeries() { return series; }
    public List<PieChart.Slice> getPie() { return pie; }
    public List<Medicamento> getAllMeds() { return allMeds; }
    public String[] getMedicamentosNombres() {
        return allMeds.stream().map(Medicamento::getNombre).toArray(String[]::new);
    }

    public void setSeries(List<LineChart.Series> data) {
        series.clear();
        if (data != null) series.addAll(data);
    }

    public void setPie(List<PieChart.Slice> data) {
        pie.clear();
        if (data != null) pie.addAll(data);
    }

    public List<Integer> seriesFor(String med, int y1, int m1, int y2, int m2) {
        List<Integer> values = new ArrayList<>();
        values.add(22);
        values.add(30);
        values.add(25);
        return values;
    }

    public int totalFor(String med, int y1, int m1, int y2, int m2) {
        return 20 + Math.abs(med.hashCode() % 30);
    }
}

