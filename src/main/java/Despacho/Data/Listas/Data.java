package Despacho.Data.Listas;

import javax.xml.bind.annotation.*;
import java.util.*;

import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Receta;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    private GestorDatosMedicamentos gestorMedicamentos;
    private GestorDatosFarmaceuticos gestorFarmaceuticos;
    private GestorDatosMedicos gestorMedicos;
    private GestorDatosRecetas gestorRecetas;

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

    public Data() {
        medicos = new ArrayList<>();
        farmaceuticos = new ArrayList<>();
        medicamentos = new ArrayList<>();
        recetas = new ArrayList<>();

            gestorMedicos = new GestorDatosMedicos();
            medicos = gestorMedicos.cargar();
            gestorFarmaceuticos = new GestorDatosFarmaceuticos();
            farmaceuticos = gestorFarmaceuticos.cargar();
            gestorMedicamentos = new GestorDatosMedicamentos();
            medicamentos= gestorMedicamentos.cargar();
            gestorRecetas = new GestorDatosRecetas();
            recetas = gestorRecetas.cargar();
    }
    // ====== Métodos Farmacéuticos ======
    public List<Farmaceutico> getFarmaceuticos() {
        return farmaceuticos;
    }

    public void agregarFarmaceutico(Farmaceutico f) {
        if (f == null) return;
        farmaceuticos.add(f);
        gestorFarmaceuticos.guardar(farmaceuticos);
    }

    public void eliminarFarmaceutico(Farmaceutico f) {
        if (f == null) return;
        farmaceuticos.remove(f);
        gestorFarmaceuticos.guardar(farmaceuticos);
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
        return medicamentos;
    }

    public void agregarMedicamento(Medicamento m) {
        if (m == null) return;
        medicamentos.add(m);
        gestorMedicamentos.guardar(medicamentos);
    }

    public void eliminarMedicamento(Medicamento m) {
        if (m == null) return ;
        medicamentos.remove(m);
        gestorMedicamentos.guardar(medicamentos);
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
        return medicos;
    }

    public void agregarMedico(Medico m) {
        if (m == null) return;
        medicos.add(m);
        gestorMedicos.guardar(medicos);
    }

    public void eliminarMedico(Medico m) {
        if (m == null) return;
        medicos.remove(m);
        gestorMedicos.guardar(medicos);
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
        return recetas;
    }

    public void agregarReceta(Receta r) {
        if (r == null) return;
        recetas.add(r);
        gestorRecetas.guardar(recetas);
    }

    public void eliminarReceta(Receta r) {
        if (r == null) return;
        recetas.remove(r);
        gestorRecetas.guardar(recetas);
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

