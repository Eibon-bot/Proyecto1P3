package Despacho.Data.Listas;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.stream.*;

import Despacho.Data.Usuarios.Farmaceutico;
import Despacho.Data.Usuarios.Medico;
import Despacho.Data.entidades.Medicamento;

@XmlRootElement(name="Listas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Listas {

    @XmlElementWrapper(name="ListaFarmaceuticos")
    @XmlElement(name="farmaceutico")
    private List<Farmaceutico> farmaceuticos = new ArrayList<>();

    @XmlElementWrapper(name="ListaMedicamentos")
    @XmlElement(name="medicamento")
    private List<Medicamento> medicamentos = new ArrayList<>();

    @XmlElementWrapper(name="ListaMedicos")
    @XmlElement(name="medico")
    private List<Medico> medicos = new ArrayList<>();

    // el unmodifiable es para que no se pueda modificar la lista desde fuera
    public List<Farmaceutico> getFarmaceuticos() {
        return Collections.unmodifiableList(farmaceuticos);
    }

    public List<Medicamento> getMedicamentos() {
        return Collections.unmodifiableList(medicamentos);
    }

    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    // Metodos para los farmaceuticos
    public boolean agregarFarmaceutico(Farmaceutico f) {
        if (f == null) return false;
        return farmaceuticos.add(f);
    }

    public boolean eliminarFarmaceutico(Farmaceutico f) {
        if (f == null) return false;
        return farmaceuticos.remove(f);
    }

    public Optional<Farmaceutico> buscarFarmaceuticoPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return Optional.empty();
        String target = nombre.trim().toLowerCase();
        return farmaceuticos.stream()
                .filter(f -> f.getNombre() != null
                        && f.getNombre().toLowerCase().equals(target))
                .findFirst();
    }

    // Metodos de medicamentos
    public boolean agregarMedicamento(Medicamento m) {
        if (m == null) return false;
        return medicamentos.add(m);
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
                .filter(m -> (m.getNombre() != null
                        && m.getNombre().toLowerCase().contains(t))
                        || (m.getDescripcion() != null
                        && m.getDescripcion().toLowerCase().contains(t)))
                .toList();
    }




    // Métodos de medicos
    public boolean agregarMedico(Medico m) {
        if (m == null) return false;
        return medicos.add(m);
    }

    public boolean eliminarMedico(Medico m) {
        if (m == null) return false;
        return medicos.remove(m);
    }

    public Optional<Medico> buscarMedicoPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return Optional.empty();
        String target = nombre.trim().toLowerCase();
        return medicos.stream()
                .filter(m -> m.getNombre() != null
                        && m.getNombre().toLowerCase().equals(target))
                .findFirst();
    }
}