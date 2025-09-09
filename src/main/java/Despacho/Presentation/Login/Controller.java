package Despacho.Presentation.Login;

import Despacho.Data.Registro.AuthService;
import Despacho.Logic.Entidades.Administrador;
import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Usuario;
import Despacho.Presentation.View.login;
import Despacho.Presentation.MainWindow;

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

    public void ingresar(String id, String clave) {
        try {
            Usuario u = auth.login(id, clave);
            String rol = (u.getRol() == null) ? "" : u.getRol().toLowerCase();
            Usuario usuarioActual;
            switch (rol) {
                case "medico":
                    usuarioActual = new Medico(u);
                    break;
                case "farmaceutico":
                    usuarioActual = new Farmaceutico(u);
                    break;
                case "administrador":
                    usuarioActual = new Administrador(u);
                    break;
                default:
                    usuarioActual = u;
            }
            model.setCurrent(usuarioActual);
            final Usuario usuarioFinal = usuarioActual;

            SwingUtilities.invokeLater(() -> {
                JFrame main = new MainWindow(usuarioFinal);
                main.setLocationRelativeTo(null);
                main.setVisible(true);
                java.awt.Window w = SwingUtilities.getWindowAncestor(view.getLogin());
                if (w != null) w.dispose();
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            model.setCurrent(null);
        }
    }
}
