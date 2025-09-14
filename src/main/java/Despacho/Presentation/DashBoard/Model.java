package Despacho.Presentation.DashBoard;

import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private final Service service;

    public Model(Service service) {
        this.service = service;
    }

    public List<Medicamento> getTodosLosMedicamentos() {
        return service.findAllMedicamentos();
    }

    public List<Receta> getRecetasEntreFechas(LocalDate desde, LocalDate hasta) {
        return service.findAllRecetas().stream()
                .filter(r -> {
                    LocalDate fecha = r.getFechaEmision();
                    return fecha != null && (fecha.isEqual(desde) || fecha.isEqual(hasta) || (fecha.isAfter(desde) && fecha.isBefore(hasta)));
                })
                .collect(Collectors.toList());
    }

    public Map<String, Long> getEstadosRecetas(LocalDate desde, LocalDate hasta) {
        return getRecetasEntreFechas(desde, hasta).stream()
                .collect(Collectors.groupingBy(Receta::getEstado, Collectors.counting()));
    }

    public Map<String, Map<String, Integer>> getCantidadMedicamentosPorMes(List<Medicamento> seleccionados, LocalDate desde, LocalDate hasta) {
        Map<String, Map<String, Integer>> resultado = new HashMap<>();

        for (Medicamento m : seleccionados) {
            Map<String, Integer> porMes = new TreeMap<>();

            for (Receta r : getRecetasEntreFechas(desde, hasta)) {
                for (var p : r.getPrescripciones()) {
                    if (p.getMedicamento() != null && p.getMedicamento().equals(m)) {
                        String mes = r.getFechaEmision().getMonthValue() + "-" + r.getFechaEmision().getMonth().name().substring(0, 1) + r.getFechaEmision().getMonth().name().substring(1).toLowerCase();
                        porMes.put(mes, porMes.getOrDefault(mes, 0) + p.getCantidad());
                    }
                }
            }
            resultado.put(m.getNombre(), porMes);
        }

        return resultado;
    }

    public List<String> getMesesDisponibles() {
        return Arrays.stream(Month.values())
                .map(m -> m.getValue() + "-" + m.name().substring(0, 1) + m.name().substring(1).toLowerCase())
                .collect(Collectors.toList());
    }

    public List<Integer> getAniosDisponibles() {
        return service.findAllRecetas().stream()
                .map(r -> r.getFechaEmision().getYear())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
