package Despacho.Presentation.Farmaceutico;

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
        model.setCurrent(new Farmaceutico());
        model.setList(Service.instance().findAllFarmaceutico());
    }
    public void delete(Farmaceutico e) throws Exception {
        Service.instance().deleteFarmaceutico(e);
        model.setCurrent(new Farmaceutico());
        model.setList(Service.instance().findAllFarmaceutico());
    }

    public void read(String nombre) throws Exception {
        Farmaceutico e = new Farmaceutico();
        e.setNombre(nombre);
        try {
            model.setCurrent(Service.instance().readFarmaceutico(e));
        } catch (Exception ex) {
            Farmaceutico b = new Farmaceutico();
            b.setNombre(nombre);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void clear() {
        model.setCurrent(new Farmaceutico());
    }

}








