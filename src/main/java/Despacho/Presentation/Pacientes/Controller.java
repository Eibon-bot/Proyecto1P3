package Despacho.Presentation.Pacientes;

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
        model.setList(Service.instance().findAllPaciente());
    }

    public void create(Paciente p) throws Exception {
        Service.instance().createPaciente(p);
        model.setList(Service.instance().findAllPaciente());
        model.setCurrent(new Paciente());
    }

    public void delete(Paciente p) throws Exception {
        Service.instance().deletePaciente(p);
        model.setList(Service.instance().findAllPaciente());
        model.setCurrent(new Paciente());
    }
    public void read(String id) throws Exception {
        Paciente e = new Paciente();
        e.setId(id);
        try {
            model.setCurrent(Service.instance().readPaciente(e));
        } catch (Exception ex) {
            Paciente b = new Paciente();
            b.setId(id);
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
        model.setCurrent(new Paciente());
    }
}
