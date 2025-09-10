package Despacho.Presentation.DashBoard;

import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Prescripcion;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Service;

import java.time.LocalDate;
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

        List<Receta> recetas = Service.instance().findAllRecetas();


        LocalDate start = LocalDate.of(y1, m1, 1);
        LocalDate end = LocalDate.of(y2, m2, 28);

        LocalDate cursor = start;
        while (!cursor.isAfter(end)) {
            int count = 0;
            for (Receta r : recetas) {
                if (r.getFechaEmision() != null &&
                        !r.getFechaEmision().isBefore(cursor.withDayOfMonth(1)) &&
                        !r.getFechaEmision().isAfter(cursor.withDayOfMonth(cursor.lengthOfMonth()))) {

                    for (Prescripcion p : r.getPrescripciones()) {
                        if (p.getMedicamento() != null &&
                                p.getMedicamento().getNombre().equalsIgnoreCase(med)) {
                            count += p.getCantidad();
                        }
                    }
                }
            }
            values.add(count);
            cursor = cursor.plusMonths(1);
        }

        return values;
    }


    public int totalFor(String med, int y1, int m1, int y2, int m2) {
        int total = 0;

        List<Receta> recetas = Service.instance().findAllRecetas();

        LocalDate start = LocalDate.of(y1, m1, 1);
        LocalDate end = LocalDate.of(y2, m2, 28);

        for (Receta r : recetas) {
            if (r.getFechaEmision() != null &&
                    !r.getFechaEmision().isBefore(start) &&
                    !r.getFechaEmision().isAfter(end)) {

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

