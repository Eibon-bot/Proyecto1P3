package Despacho.Data.Listas;

import Despacho.Data.entidades.Medicamento;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@XmlRootElement(name="ListaMedicamentos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaMedicamentos {
    @XmlElement(name="medicamento")
    private final List<Medicamento> medicamentos = new ArrayList<>();

    public ListaMedicamentos(List<Medicamento> lista) {
        if (lista != null) {
            medicamentos.addAll(lista);
        }
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }


    public boolean agregar(Medicamento m) {
        if (m == null) return false;
        return medicamentos.add(m);
    }

    public boolean eliminar(Medicamento m) {
        if (m == null) return false;
        return medicamentos.remove(m);
    }

    public List<Medicamento> obtenerTodos() {
        return Collections.unmodifiableList(medicamentos);
    }

    public int size() {
        return medicamentos.size();
    }


    public Optional<Medicamento> buscarPorNombre(String nombre) {
        if (nombre == null) return Optional.empty();
        return medicamentos.stream()
                .filter(m -> nombre.equalsIgnoreCase(m.getNombre()))
                .findFirst();
    }

    public List<Medicamento> buscarQueContengan(String texto) {
        if (texto == null || texto.isBlank()) return List.of();
        String t = texto.toLowerCase();
        return medicamentos.stream()
                .filter(m -> (m.getNombre() != null && m.getNombre().toLowerCase().contains(t))
                        || (m.getDescripcion() != null && m.getDescripcion().toLowerCase().contains(t)))
                .toList();
    }
}
