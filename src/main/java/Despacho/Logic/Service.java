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
            e.setClave(e.getId());
            data.getMedicos().add(e);
            store();
        } else {
            throw new Exception("Farmaceutico ya existe");
        }
    }

    public void deleteMedico(Medico e) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(f -> f.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getMedicos().remove(result);
            store();
        } else {
            throw new Exception("Medico no existe");
        }
    }
    public Medico readMedico(String nombre) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(f -> f.getNombre().equalsIgnoreCase(nombre))
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

    public String generarNuevoIdMedico() {
        List<Medico> lista = Service.instance().findAllMedico();
        int max = 0;
        for (Medico m : lista) {
            String id = m.getId();
            if (id != null && id.startsWith("MED-")) {
                try {
                    int num = Integer.parseInt(id.substring(6));
                    if (num > max) max = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        int nuevo = max + 1;
        return String.format("MED-%04d", nuevo);
    }

    // =============== Farmaceuticos ===============
    public void createFarmaceutico(Farmaceutico e) throws Exception {
        Farmaceutico result = data.getFarmaceuticos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            e.setClave(e.getId());
            data.getFarmaceuticos().add(e);
            store();
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


    public String generarNuevoIdFarma() {
        List<Farmaceutico> lista = Service.instance().findAllFarmaceutico();
        int max = 0;
        for (Farmaceutico f : lista) {
            String id = f.getId();
            if (id != null && id.startsWith("FARMA-")) {
                try {
                    int num = Integer.parseInt(id.substring(6));
                    if (num > max) max = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        int nuevo = max + 1;
        return String.format("FARMA-%04d", nuevo);
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
                .filter(f -> f.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getPacientes().remove(result);
            store();
        } else {
            throw new Exception("Paciente no existe");
        }
    }

    public Paciente readPaciente(String nombre) throws Exception {
        Paciente result = data.getPacientes().stream()
                .filter(f -> f.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Paciente no existe");
        }
    }


    public String generarNuevoIdPaciente() {
        List<Paciente> lista = Service.instance().findAllPaciente();
        int max = 0;
        for (Paciente p : lista) {
            String id = p.getId();
            if (id != null && id.startsWith("PAC-")) {
                try {
                    int num = Integer.parseInt(id.substring(6));
                    if (num > max) max = num;
                } catch (NumberFormatException ignored) {}
            }
        }
        int nuevo = max + 1;
        return String.format("PAC-%04d", nuevo);
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



