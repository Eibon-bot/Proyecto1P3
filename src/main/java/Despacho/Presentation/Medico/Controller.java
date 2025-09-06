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
        Service.instance().store();
        clear();
        model.setList(Service.instance().findAllMedicos());
    }
    public void delete(Medico e) throws Exception {
        Service.instance().deleteMedico(e);
        Service.instance().store();
        model.setCurrent(new Medico());
        model.setList(Service.instance().findAllMedico());
    }

    public void read(String nombre) throws Exception {
        try {
            model.setCurrent(Service.instance().readMedico(nombre));
        } catch (Exception ex) {
            Medico b = new Medico();
            b.setNombre(nombre);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void clear() {
        Medico m= new Medico();
        m.setId(Service.instance().generarNuevoIdMedico());
        model.setCurrent(m);
    }

}








