package Despacho.Presentation.Historico;

import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Entidades.Paciente;
import Despacho.Logic.Service;

public class Controller {
    Historico view;
    Model model;

    public Controller(Historico view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void setReceta(int row) {
        Receta r = model.getList().get(row);
        model.setCurrent(r);
    }

    public void aplicarFiltros(String textoBusqueda, String tipoBusqueda, String estado) {
        Receta filtro = new Receta();
        Paciente p = new Paciente();

        if ("ID Paciente".equalsIgnoreCase(tipoBusqueda)) {
            p.setId(textoBusqueda);
            filtro.setPaciente(p);
            filtro.setEstado(estado);
            model.setList(Service.instance().searchRecetaPorIdPaciente(filtro));

        } else if ("Nombre Paciente".equalsIgnoreCase(tipoBusqueda)) {
            p.setNombre(textoBusqueda);
            filtro.setPaciente(p);
            filtro.setEstado(estado);
            model.setList(Service.instance().searchRecetaPorNombrePaciente(filtro));

        } else {
            filtro.setEstado(estado);
            model.setList(Service.instance().filtrarRecetaPorEstado(filtro));
        }
    }



}

