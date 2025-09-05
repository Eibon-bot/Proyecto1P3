package Despacho.Logic;

import Despacho.Data.Listas.XmlPersister;
import Despacho.Data.Listas.Data;
import Despacho.Logic.Entidades.*;

import java.util.List;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Data data;

    private Service(){
        try{
            data= XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }
    }
    public void store(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // =============== Medicos ===============
    public void createMedico(Medico e) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.getMedicos().add(e);
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
            data.getMedicos().remove(e);
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
            data.getFarmaceuticos().add(e);
        } else {
            throw new Exception("Farmaceutico ya existe");
        }
    }

    public void deleteFarmaceutico(Farmaceutico e) throws Exception {
        Farmaceutico result = data.getFarmaceuticos().stream()
                .filter(f -> f.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getFarmaceuticos().remove(result);
            store();
        } else {
            throw new Exception("Farmaceutico no existe");
        }
    }

    public Farmaceutico readFarmaceutico(String nombre) throws Exception {
        Farmaceutico result = data.getFarmaceuticos().stream()
                .filter(f -> f.getNombre().equalsIgnoreCase(nombre))
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
            data.getMedicamentos().add(e);
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
            data.getMedicamentos().remove(e);
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

    public void createPaciente(Paciente e) throws Exception {
        Paciente result = data.getPacientes().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.getPacientes().add(e);
        } else {
            throw new Exception("El Paciente ya existe");
        }
    }

    public void deletePaciente(Paciente e) throws Exception {
        Paciente result = data.getPacientes().stream()
                .filter(m -> m.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getPacientes().remove(e);
        } else {
            throw new Exception("El Paciente no existe");
        }
    }

    public Paciente readPaciente(Paciente e) throws Exception {
        Paciente result = data.getPacientes().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Paciente no existe");
        }
    }

    public List<Medico> findAllMedicos() {return data.getMedicos();}
    public List<Farmaceutico> findAllFarmaceuticos() {
        return data.getFarmaceuticos();
    }
    public List<Medicamento> findAllMedicamentos() {
        return data.getMedicamentos();
    }
    public List<Paciente> findAllPaciente() { return data.getPacientes(); }
    public List<Receta> findAllRecetas() { return data.getRecetas(); }






}



