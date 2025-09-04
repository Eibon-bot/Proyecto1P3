package Despacho.Logic;

import Despacho.Data.Listas.Data;
import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Medico;

import Despacho.Data.Listas.GestorDatosPacientes;
import Despacho.Logic.Entidades.Paciente;

import java.util.List;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Data data;

    private Service() {
        data = new Data();
    }

    // =============== Medicos ===============
    public void createMedico(Medico e) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.agregarMedico(e);
        } else {
            throw new Exception("Medico ya existe");
        }
    }

    public void deleteMedico(Medico e) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(m -> m.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.eliminarMedico(e);
        } else {
            throw new Exception("Medico no existe");
        }
    }
    public Medico readMedico(Medico e) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Medico no existe");
        }
    }

    public List<Medico> findAllMedico() {
        return data.getMedicos();
    }

    // =============== Farmaceuticos ===============
    public void createFarmaceutico(Farmaceutico e) throws Exception {
        Farmaceutico result = data.getFarmaceuticos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.agregarFarmaceutico(e);
        } else {
            throw new Exception("Farmaceutico ya existe");
        }
    }

    public void deleteFarmaceutico(Farmaceutico e) throws Exception {
        Farmaceutico result = data.getFarmaceuticos().stream()
                .filter(m -> m.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.eliminarFarmaceutico(e);
        } else {
            throw new Exception("Farmaceutico no existe");
        }
    }
    public Farmaceutico readFarmaceutico(Farmaceutico e) throws Exception {
        Farmaceutico result = data.getFarmaceuticos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Farmaceutico no existe");
        }
    }

    public List<Farmaceutico> findAllFarmaceutico() {
        return data.getFarmaceuticos();
    }

    // =============== Medicamento ===============
    public void createMedicamento(Medicamento e) throws Exception {
        Medicamento result = data.getMedicamentos().stream()
                .filter(i -> i.getCodigo().equals(e.getCodigo()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.agregarMedicamento(e);
        } else {
            throw new Exception("El medicamento ya existe");
        }
    }

    public void deleteMedicamento(Medicamento e) throws Exception {
        Medicamento result = data.getMedicamentos().stream()
                .filter(m -> m.getCodigo().equals(e.getCodigo()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.eliminarMedicamento(e);
        } else {
            throw new Exception("El medicamento no existe");
        }
    }
    public Medicamento readMedicamento(Medicamento e) throws Exception {
        Medicamento result = data.getMedicamentos().stream()
                .filter(i -> i.getCodigo().equals(e.getCodigo()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Medicamento no existe");
        }
    }

    public List<Medicamento> findAllMedicamento() {
        return data.getMedicamentos();
    }


    // =============== Pacientes===============
    private final GestorDatosPacientes gPac = new GestorDatosPacientes();

    public void createPaciente(Paciente p) throws Exception {
        List<Paciente> all = gPac.cargar();
        boolean exists = all.stream().anyMatch(x -> x.getId() != null && x.getId().equalsIgnoreCase(p.getId()));
        if (exists) throw new Exception("Paciente ya existe");
        all.add(p);
        gPac.guardar(all);
    }

    public void deletePaciente(Paciente p) throws Exception {
        List<Paciente> all = gPac.cargar();
        boolean removed = all.removeIf(x -> x.getId() != null && x.getId().equalsIgnoreCase(p.getId()));
        if (!removed) throw new Exception("Paciente no existe");
        gPac.guardar(all);
    }

    public Paciente readPacienteById(String id) throws Exception {
        return gPac.cargar().stream()
                .filter(x -> x.getId() != null && x.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Paciente no existe"));
    }

    public List<Paciente> findAllPaciente() {
        return gPac.cargar();
    }


    public List<Paciente> buscarPacientes(String texto) {
        if (texto == null) texto = "";
        String t = texto.trim().toLowerCase();
        if (t.isEmpty()) return findAllPaciente();
        List<Paciente> all = gPac.cargar();
        List<Paciente> out = new java.util.ArrayList<>();
        for (Paciente p : all) {
            String id = p.getId() == null ? "" : p.getId().toLowerCase();
            String nom = p.getNombre() == null ? "" : p.getNombre().toLowerCase();
            if (id.contains(t) || nom.contains(t)) out.add(p);
        }
        return out;
    }

}



