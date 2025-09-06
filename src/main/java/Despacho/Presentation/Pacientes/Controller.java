package Despacho.Presentation.Pacientes;

import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Paciente;
import Despacho.Logic.Service;

import java.util.List;

public class Controller {
    PacientesAdmin view;
    Model model;

    public Controller(PacientesAdmin view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void create(Paciente e) throws  Exception{
        Service.instance().createPaciente(e);
        Service.instance().store();
        clear();
        model.setList(Service.instance().findAllPaciente());
    }

    public void delete(Paciente e) throws Exception {
        Service.instance().deletePaciente(e);
        Service.instance().store();
        model.setCurrent(new Paciente());
        model.setList(Service.instance().findAllPaciente());
    }
    public void read(String nombre) throws Exception {
        try {
            model.setCurrent(Service.instance().readPaciente(nombre));
        } catch (Exception ex) {
            Paciente b = new Paciente();
            b.setNombre(nombre);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void selectFromList(Paciente p) {
        if (p != null) {
            model.setCurrent(p);
        }
    }

    public void clear() {
        Paciente p = new Paciente();
        p.setId(Service.instance().generarNuevoIdPaciente());
        model.setCurrent(p);
    }
}
