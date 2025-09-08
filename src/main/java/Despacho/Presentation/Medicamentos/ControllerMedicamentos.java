package Despacho.Presentation.Medicamentos;

import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Paciente;
import Despacho.Logic.Service;

public class ControllerMedicamentos {
    MedicaAdmin view;
    ModelMedicamentos model;

    public ControllerMedicamentos(MedicaAdmin view, ModelMedicamentos model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void create(Medicamento e) throws  Exception{
        Service.instance().createMedicamento(e);
        Service.instance().store();
        clear();
        model.setList(Service.instance().findAllMedicamentos());
    }
    public void delete(Medicamento e) throws Exception {
        Service.instance().deleteMedicamento(e);
        Service.instance().store();
        model.setCurrent(new Medicamento());
        model.setList(Service.instance().findAllMedicamento());
    }

    public void setMedicamento(int row) {
        Medicamento p = model.getList().get(row);
        model.setCurrent(p);
    }


    public void update(Medicamento p) throws Exception {
        Service.instance().updateMedicamento(p);
        model.setList(Service.instance().findAllMedicamento());
    }

    public void read(String nombre) throws Exception {
        try {
            model.setCurrent(Service.instance().readMedicamento(nombre));
        } catch (Exception ex) {
            Medicamento b = new Medicamento();
            b.setNombre(nombre);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void clear() {
        Medicamento m= new Medicamento();
//        m.setCodigo(Service.instance().generarNuevoCodMedicamento(m.getNombre()));
        model.setCurrent(m);
    }
}
