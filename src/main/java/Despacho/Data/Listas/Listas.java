package Despacho.Data.Listas;

import javax.xml.bind.annotation.*;
import java.util.*;

import Despacho.Logic.Farmaceutico;
import Despacho.Logic.Medico;
import Despacho.Logic.Medicamento;
import Despacho.Logic.Receta;

@XmlRootElement(name = "Listas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Listas {

    @XmlElementWrapper(name = "ListaFarmaceuticos")
    @XmlElement(name = "farmaceutico")
    private List<Farmaceutico> farmaceuticos = new ArrayList<>();

    @XmlElementWrapper(name = "ListaMedicamentos")
    @XmlElement(name = "medicamento")
    private List<Medicamento> medicamentos = new ArrayList<>();

    @XmlElementWrapper(name = "ListaMedicos")
    @XmlElement(name = "medico")
    private List<Medico> medicos = new ArrayList<>();

    @XmlElementWrapper(name = "ListaRecetas")
    @XmlElement(name = "receta")
    private List<Receta> recetas = new ArrayList<>();

    // ====== Métodos Farmacéuticos ======
    public List<Farmaceutico> getFarmaceuticos() {
        return Collections.unmodifiableList(farmaceuticos);
    }

    public void agregarFarmaceutico(Farmaceutico f) {
        if (f == null) return;
        farmaceuticos.add(f);
    }

    public boolean eliminarFarmaceutico(Farmaceutico f) {
        if (f == null) return false;
        return farmaceuticos.remove(f);
    }

    public Optional<Farmaceutico> buscarFarmaceuticoPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return Optional.empty();
        String target = nombre.trim().toLowerCase();
        return farmaceuticos.stream()
                .filter(f -> f.getNombre() != null && f.getNombre().toLowerCase().equals(target))
                .findFirst();
    }

    // ====== Métodos Medicamentos ======
    public List<Medicamento> getMedicamentos() {
        return Collections.unmodifiableList(medicamentos);
    }

    public void agregarMedicamento(Medicamento m) {
        if (m == null) return;
        medicamentos.add(m);
    }

    public boolean eliminarMedicamento(Medicamento m) {
        if (m == null) return false;
        return medicamentos.remove(m);
    }

    public Optional<Medicamento> buscarMedicamentoPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return Optional.empty();
        String target = nombre.trim().toLowerCase();
        return medicamentos.stream()
                .filter(m -> m.getNombre() != null && m.getNombre().toLowerCase().equals(target))
                .findFirst();
    }

    public List<Medicamento> buscarQueContengan(String texto) {
        if (texto == null || texto.isBlank()) {
            return List.of();
        }
        String t = texto.toLowerCase();
        return medicamentos.stream()
                .filter(m -> (m.getNombre() != null && m.getNombre().toLowerCase().contains(t))
                        || (m.getDescripcion() != null && m.getDescripcion().toLowerCase().contains(t)))
                .toList();
    }

    // ====== Métodos Médicos ======
    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    public void agregarMedico(Medico m) {
        if (m == null) return;
        medicos.add(m);
    }

    public boolean eliminarMedico(Medico m) {
        if (m == null) return false;
        return medicos.remove(m);
    }

    public Optional<Medico> buscarMedicoPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return Optional.empty();
        String target = nombre.trim().toLowerCase();
        return medicos.stream()
                .filter(m -> m.getNombre() != null && m.getNombre().toLowerCase().equals(target))
                .findFirst();
    }

    // ====== Métodos Recetas ======
    public List<Receta> getRecetas() {
        return Collections.unmodifiableList(recetas);
    }

    public void agregarReceta(Receta r) {
        if (r == null) return;
        recetas.add(r);
    }

    public boolean eliminarReceta(Receta r) {
        if (r == null) return false;
        return recetas.remove(r);
    }

    public Optional<Receta> buscarRecetaPorCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) return Optional.empty();
        String target = codigo.trim().toLowerCase();
        return recetas.stream()
                .filter(r -> r.getCodigo() != null && r.getCodigo().toLowerCase().equals(target))
                .findFirst();
    }

    public List<Receta> buscarRecetasPorIdPaciente(String idPaciente) {
        if (idPaciente == null || idPaciente.isBlank()) return List.of();
        String target = idPaciente.trim().toLowerCase();
        return recetas.stream()
                .filter(r -> r.getIdPaciente() != null && r.getIdPaciente().toLowerCase().equals(target))
                .toList();
    }

    public List<Receta> buscarRecetasPorIdMedico(String idMedico) {
        if (idMedico == null || idMedico.isBlank()) return List.of();
        String target = idMedico.trim().toLowerCase();
        return recetas.stream()
                .filter(r -> r.getIdMedico() != null && r.getIdMedico().toLowerCase().equals(target))
                .toList();
    }
}

