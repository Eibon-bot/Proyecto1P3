package Despacho.Presentation.Login;


import Despacho.Data.Registro.AuthService;
import Despacho.Logic.Entidades.Administrador;
import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medico;
import Despacho.Presentation.View.login;
import Despacho.Logic.Entidades.Usuario;

import javax.swing.*;

public class Controller {
    login view;
    Model model;
    AuthService auth;

    public Controller(login view, Model model, AuthService auth) {
        this.view = view;
        this.model = model;
        this.auth = auth;
        view.setController(this);
        view.setModel(model);
    }

    public void ingresar(String id,String clave)throws Exception{
        try {
            Usuario u = auth.login(id, clave);
            Usuario usuarioActual;

            switch (u.getRol().toLowerCase()) {
                case "medico": usuarioActual = new Medico(u); break;
                case "farmaceutico": usuarioActual = new Farmaceutico(u); break;
                case "administrador": usuarioActual = new Administrador(u); break;
                default: usuarioActual = u;
            }

            model.setCurrent(usuarioActual);
//Para probar el login
            JOptionPane.showMessageDialog(null,
                    "Bienvenido " + usuarioActual.getNombre() +
                            " (" + usuarioActual.getRol() + ")");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Error: " + ex.getMessage());
            model.setCurrent(null);
        }

    }


}
