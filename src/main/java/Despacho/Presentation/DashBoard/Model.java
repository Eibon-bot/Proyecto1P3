package Despacho.Presentation.DashBoard;

import Despacho.Logic.Service;
import Despacho.Logic.Entidades.Medicamento;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<LineChart.Series> series = new ArrayList<>();
    private final List<PieChart.Slice> pie = new ArrayList<>();
    private String[] medicamentosNombres = new String[0];

    public Model() {
        List<Medicamento> meds = Service.instance().findAllMedicamento();
        medicamentosNombres = meds.stream().map(Medicamento::getNombre).toArray(String[]::new);
    }

    public List<LineChart.Series> getSeries() { return series; }
    public List<PieChart.Slice> getPie() { return pie; }
    public String[] getMedicamentosNombres() { return medicamentosNombres; }

    public void setSeries(List<LineChart.Series> data) {
        series.clear();
        if (data != null) series.addAll(data);
    }

    public void setPie(List<PieChart.Slice> data) {
        pie.clear();
        if (data != null) pie.addAll(data);
    }

    public void setMedicamentosNombres(String[] nombres) {
        medicamentosNombres = nombres != null ? nombres : new String[0];
    }

    public List<Integer> seriesFor(String med, int y1, int m1, int y2, int m2) {
        List<Integer> values = new ArrayList<>();
        values.add(10);
        values.add(20);
        values.add(15);
        return values;
    }

    public int totalFor(String med, int y1, int m1, int y2, int m2) {
        return 45;
    }
}
