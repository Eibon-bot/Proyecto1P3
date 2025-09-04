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


        List<Paciente> all = Service.instance().findAllPaciente();
        model.setList(all);
        model.setCurrent(new Paciente());
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


    public void search(String texto) {
        model.setList(Service.instance().buscarPacientes(texto));
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
