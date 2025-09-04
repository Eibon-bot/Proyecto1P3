package Despacho;

import Despacho.Presentation.View.login;
import Despacho.Presentation.Login.Controller;
import Despacho.Presentation.Login.Model;
import Despacho.Data.Registro.AuthService;
import Despacho.Data.Listas.GestorDatosFarmaceuticos;
import Despacho.Data.Listas.GestorDatosMedicos;

import javax.swing.*;
import java.awt.*;

public class App {
    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {}

        try {
            GestorDatosFarmaceuticos gf = new GestorDatosFarmaceuticos();
            GestorDatosMedicos gm = new GestorDatosMedicos();
            Model model = new Model();
            AuthService auth = new AuthService(gm, gf);
            login loginView = new login();
            Controller controller = new Controller(loginView, model, auth);

            JFrame window = new JFrame("Login");
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setContentPane(loginView.getLogin());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
        }
    }
}

