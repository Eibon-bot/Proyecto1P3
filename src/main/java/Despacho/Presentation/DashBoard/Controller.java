package Despacho.Presentation.DashBoard;

public class Controller {
    private final ViewDB view;
    private final Model model;

    public Controller(ViewDB view, Model model) {
        this.view = view;
        this.model = model;
        this.view.setModel(model);
        this.view.init();
    }
}
