package Despacho.Presentation.Medico;

import Despacho.Logic.Service;
import Despacho.Logic.Entidades.Medico;

public class Controller {
    MediAdmin view;
    Model model;

    public Controller(MediAdmin view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void create(Medico e) throws  Exception{
        Service.instance().createMedico(e);
        model.setCurrent(new Medico());
        model.setList(Service.instance().findAllMedico());
    }
    public void delete(Medico e) throws Exception {
        Service.instance().deleteMedico(e);
        model.setCurrent(new Medico());
        model.setList(Service.instance().findAllMedico());
    }

    public void read(String nombre) throws Exception {
        Medico e = new Medico();
        e.setNombre(nombre);
        try {
            model.setCurrent(Service.instance().readMedico(e));
        } catch (Exception ex) {
            Medico b = new Medico();
            b.setNombre(nombre);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void clear() {
        model.setCurrent(new Medico());
    }

}








