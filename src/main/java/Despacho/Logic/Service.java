package Despacho.Logic;

import Despacho.Data.Listas.XmlPersister;
import Despacho.Data.Listas.Data;
import Despacho.Logic.Entidades.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new Exception("Medico ya existe");
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

    public void updateMedico(Medico p) {
        Medico old = searchMedicoId(p).stream().findFirst().orElse(null);
        if (old != null) {
            old.setNombre(p.getNombre());
            old.setEspecialidad(p.getEspecialidad());
            store();
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

    public void updateFarmaceutico(Farmaceutico p) {
        Farmaceutico old = searchFarmaceuticoId(p).stream().findFirst().orElse(null);
        if (old != null) {
            old.setNombre(p.getNombre());
            store();
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
                .filter(f -> f.getCodigo().equals(e.getCodigo()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getMedicamentos().remove(result);
            store();
        } else {
            throw new Exception("Medicamento no existe");
        }
    }

    public void updateMedicamento(Medicamento p) {
        Medicamento old = searchMedicamentoCod(p).stream().findFirst().orElse(null);
        if (old != null) {
            old.setNombre(p.getNombre());
            old.setPresentacion(p.getPresentacion());
            store();
        }
    }
    public Medicamento readMedicamento(String cod) throws Exception {
        Medicamento result = data.getMedicamentos().stream()
                .filter(f -> f.getCodigo().equalsIgnoreCase(cod))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Medicamento no existe");
        }
    }

    public String generarNuevoCodMedicamento(String nombre) {
        String prefijo = nombre.trim().toUpperCase();
        prefijo = prefijo.length() >= 3 ? prefijo.substring(0, 3) : prefijo;
        List<Medicamento> lista = Service.instance().findAllMedicamento();
        int max = 0;

        for (Medicamento f : lista) {
            String cod = f.getCodigo();
            if (cod != null && cod.startsWith(prefijo + "-")) {
                try {
                    int num = Integer.parseInt(cod.substring(prefijo.length() + 1));
                    if (num > max) max = num;
                } catch (NumberFormatException ignored) {}
            }
        }

        int nuevo = max + 1;
        return String.format("%s-%03d", prefijo, nuevo);
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

    public void updatePaciente(Paciente p) {
        Paciente old = searchPacienteId(p).stream().findFirst().orElse(null);
        if (old != null) {
            old.setNombre(p.getNombre());
            old.setFechaNacimiento(p.getFechaNacimiento());
            old.setTelefono(p.getTelefono());
            store();
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

    //Otras funciones
    public List<Paciente> searchPacienteNombre( Paciente e) {
        return data.getPacientes().stream()
                .filter(i -> i.getNombre().toLowerCase().contains(e.getNombre().toLowerCase()))
                .sorted(Comparator.comparing(Paciente::getNombre))
                .collect(Collectors.toList());
    }
    public List<Paciente> searchPacienteId(Paciente e) {
        return data.getPacientes().stream()
                .filter(i -> i.getId() != null && i.getId().toLowerCase().contains(e.getId().toLowerCase()))
                .sorted(Comparator.comparing(Paciente::getId))
                .collect(Collectors.toList());
    }

    public List<Farmaceutico> searchFarmaceuticoId(Farmaceutico e) {
        return data.getFarmaceuticos().stream()
                .filter(i -> i.getId() != null && i.getId().toLowerCase().contains(e.getId().toLowerCase()))
                .sorted(Comparator.comparing(Farmaceutico::getId))
                .collect(Collectors.toList());
    }

    public List<Medico> searchMedicoId(Medico e) {
        return data.getMedicos().stream()
                .filter(i -> i.getId() != null && i.getId().toLowerCase().contains(e.getId().toLowerCase()))
                .sorted(Comparator.comparing(Medico::getId))
                .collect(Collectors.toList());
    }

    public List<Farmaceutico> searchFarmaceuticoNombre(Farmaceutico e) {
        return data.getFarmaceuticos().stream()
                .filter(i -> i.getNombre() != null && i.getNombre().toLowerCase().contains(e.getNombre().toLowerCase()))
                .sorted(Comparator.comparing(Farmaceutico::getNombre))
                .collect(Collectors.toList());
    }

    public List<Medico> searchMedicoNombre(Medico e) {
        return data.getMedicos().stream()
                .filter(i -> i.getNombre() != null && i.getNombre().toLowerCase().contains(e.getNombre().toLowerCase()))
                .sorted(Comparator.comparing(Medico::getNombre))
                .collect(Collectors.toList());
    }
    public List<Medicamento> searchMedicamentoCod(Medicamento e) {
        return data.getMedicamentos().stream()
                .filter(i -> i.getCodigo() != null && i.getCodigo().toLowerCase().contains(e.getCodigo().toLowerCase()))
                .sorted(Comparator.comparing(Medicamento::getCodigo))
                .collect(Collectors.toList());
    }

    public List<Medicamento> searchMedicamentoNombre( Medicamento e) {
        return data.getMedicamentos().stream()
                .filter(i -> i.getNombre().toLowerCase().contains(e.getNombre().toLowerCase()))
                .sorted(Comparator.comparing(Medicamento::getNombre))
                .collect(Collectors.toList());
    }

    public List<Receta> searchRecetaPorIdPaciente(Receta rfiltro) {
        return data.getRecetas().stream()
                .filter(r -> r.getPaciente() != null
                        && r.getPaciente().getId() != null
                        && r.getPaciente().getId().toLowerCase()
                        .contains(rfiltro.getPaciente().getId().toLowerCase()))
                .filter(r -> rfiltro.getEstado() == null ||
                        rfiltro.getEstado().equalsIgnoreCase("Todos") ||
                        (r.getEstado() != null &&
                                r.getEstado().equalsIgnoreCase(rfiltro.getEstado())))
                .sorted(Comparator.comparing(r -> r.getPaciente().getId()))
                .collect(Collectors.toList());
    }

    public List<Receta> searchRecetaPorNombrePaciente(Receta rfiltro) {
        return data.getRecetas().stream()
                .filter(r -> r.getPaciente() != null
                        && r.getPaciente().getNombre() != null
                        && r.getPaciente().getNombre().toLowerCase()
                        .contains(rfiltro.getPaciente().getNombre().toLowerCase()))
                .filter(r -> rfiltro.getEstado() == null ||
                        rfiltro.getEstado().equalsIgnoreCase("Todos") ||
                        (r.getEstado() != null &&
                                r.getEstado().equalsIgnoreCase(rfiltro.getEstado())))
                .sorted(Comparator.comparing(r -> r.getPaciente().getNombre()))
                .collect(Collectors.toList());
    }
    public List<Receta> filtrarRecetaPorEstado(Receta rfiltro) {
        if (rfiltro.getEstado() == null || rfiltro.getEstado().equalsIgnoreCase("Todos")) {
            return data.getRecetas();
        }
        return data.getRecetas().stream()
                .filter(r -> r.getEstado() != null &&
                        r.getEstado().equalsIgnoreCase(rfiltro.getEstado()))
                .sorted(Comparator.comparing(Receta::getEstado))
                .collect(Collectors.toList());
    }


    public Medicamento findMedicamentoByNombre(String nombre) {
        return data.getMedicamentos().stream()
                .filter(m -> m.getNombre() != null && m.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }



}



