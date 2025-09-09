package Despacho.Presentation.Farmaceutico;

import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Paciente;
import Despacho.Logic.Service;
import Despacho.Logic.Entidades.Farmaceutico;

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
        Service.instance().store();
        clear();
        model.setList(Service.instance().findAllFarmaceutico());
    }

    public void setFarmaceutico(int row) {
        Farmaceutico f = model.getList().get(row);
        model.setCurrent(f);
    }
    public void delete(Farmaceutico e) throws Exception {
        Service.instance().deleteFarmaceutico(e);
        Service.instance().store();
        model.setCurrent(new Farmaceutico());
        model.setList(Service.instance().findAllFarmaceutico());
    }

    public void update(Farmaceutico f) throws Exception {
        Service.instance().updateFarmaceutico(f);
        model.setList(Service.instance().findAllFarmaceutico());
    }

    public void read(String nombre) throws Exception {
        try {
            model.setCurrent(Service.instance().readFarmaceutico(nombre));
        } catch (Exception ex) {
            Farmaceutico b = new Farmaceutico();
            b.setNombre(nombre);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void clear() {
        Farmaceutico f = new Farmaceutico();
        f.setId(Service.instance().generarNuevoIdFarma());
        model.setCurrent(f);
    }

    public void searchFarmaceuticoNombre(String nombre) {
        Farmaceutico p = new Farmaceutico();
        p.setNombre(nombre);
        model.setList(Service.instance().searchFarmaceuticoNombre(p));
    }
    public void searchFarmaceuticoId(String id) {
        Farmaceutico p = new Farmaceutico();
        p.setId(id);
        model.setList(Service.instance().searchFarmaceuticoId(p));
    }
}








