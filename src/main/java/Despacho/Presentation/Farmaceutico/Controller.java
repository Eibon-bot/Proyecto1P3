package Despacho.Presentation.Farmaceutico;

import Despacho.Data.Registro.AuthService;
import Despacho.Presentation.View.ViewPrincipal;

public class Controller {
    ViewPrincipal view;
    Despacho.Presentation.Farmaceutico.Model model;
    AuthService auth;

    public Controller(ViewPrincipal view, Model model, AuthService auth) {
        this.view = view;
        this.model = model;
        this.auth = auth;
        view.setController(this);
        view.setModel(model);
    }



}



