package Despacho.Presentation.DashBoard;

import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Prescripcion;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Service;

import java.util.ArrayList;
import java.util.List;

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

        for (Receta r : Service.instance().findAllRecetas()) {
            if (r.getFechaEmision() == null) continue;

            int year = r.getFechaEmision().getYear();
            int month = r.getFechaEmision().getMonthValue();

            boolean dentroRango =
                    (year > y1 || (year == y1 && month >= m1)) &&
                            (year < y2 || (year == y2 && month <= m2));

            if (dentroRango) {
                for (Prescripcion p : r.getPrescripciones()) {
                    if (p.getMedicamento() != null &&
                            p.getMedicamento().getNombre().equalsIgnoreCase(med)) {
                        values.add(p.getCantidad());
                    }
                }
            }
        }

        if (values.isEmpty()) values.add(0);
        return values;
    }

    public int totalFor(String med, int y1, int m1, int y2, int m2) {
        int total = 0;

        for (Receta r : Service.instance().findAllRecetas()) {
            if (r.getFechaEmision() == null) continue;

            int year = r.getFechaEmision().getYear();
            int month = r.getFechaEmision().getMonthValue();

            boolean dentroRango =
                    (year > y1 || (year == y1 && month >= m1)) &&
                            (year < y2 || (year == y2 && month <= m2));

            if (dentroRango) {
                for (Prescripcion p : r.getPrescripciones()) {
                    if (p.getMedicamento() != null &&
                            p.getMedicamento().getNombre().equalsIgnoreCase(med)) {
                        total += p.getCantidad();
                    }
                }
            }
        }

        return total;
    }
}

