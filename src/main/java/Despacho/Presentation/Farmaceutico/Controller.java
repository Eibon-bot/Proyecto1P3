package Despacho.Presentation.Farmaceutico;

import Despacho.Logic.Farmaceutico;
import Despacho.Logic.Service;

public class Controller {
    FarmaAdmin view;
    Model model;

    public Controller(FarmaAdmin view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);

    }

    public void create(Farmaceutico e) throws  Exception{
        Service.instance().createFarmaceutico(e);
        model.setCurrent(new f());
        model.setList(Service.instance().findAll());
    }

    public void read(String id) throws Exception {
        Persona e = new Persona();
        e.setId(id);
        try {
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {
            Persona b = new Persona();
            b.setId(id);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void clear() {
        model.setCurrent(new Persona());
    }

    public void setDepartamento(int row) {
        model.setDepartamento(model.getDepartamentos().get(row));
    }

    public void searchDepartamentos(String nombre) {
        Departamento d = new Departamento();
        d.setNombre(nombre);
        model.setDepartamentos(Service.instance().search(d));
    }
    }




}



