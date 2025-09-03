package Despacho.Logic;

import Despacho.Data.Listas.Data;
import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Medico;

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
}
