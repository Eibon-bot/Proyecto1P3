package Despacho.Presentation.AgregarFarma;

import Despacho.Data.Registro.AuthService;
import Despacho.Data.Usuarios.Administrador;
import Despacho.Data.Usuarios.Farmaceutico;
import Despacho.Data.Usuarios.Medico;
import Despacho.Data.Usuarios.Usuario;
import Despacho.Presentation.View.ViewPrincipal;
import javax.swing.*;

public class Controller {
    ViewPrincipal view;
    Despacho.Presentation.AgregarFarma.Model model;
    AuthService auth;

    public Controller(ViewPrincipal view, Model model, AuthService auth) {
        this.view = view;
        this.model = model;
        this.auth = auth;
        view.setController(this);
        view.setModel(model);
    }



}



