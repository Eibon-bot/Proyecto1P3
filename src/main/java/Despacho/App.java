package Despacho;

import Despacho.Data.Listas.Data;
import Despacho.Data.Listas.XmlPersister;
import Despacho.Logic.Service;
import Despacho.Presentation.Farmaceutico.FarmaAdmin;
import Despacho.Presentation.View.login;
import Despacho.Presentation.Login.Controller;
import Despacho.Presentation.Login.Model;
import Despacho.Data.Registro.AuthService;

import javax.swing.*;
import java.awt.*;

public class App {
    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {}

        try {
            Model model = new Model();
            AuthService auth = new AuthService();
            login loginView = new login();
            Controller controller = new Controller(loginView, model, auth);

            JFrame window = new JFrame("Login");
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setContentPane(loginView.getLogin());
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);


            // para probar

            FarmaAdmin farmaView = new FarmaAdmin();
            Despacho.Presentation.Farmaceutico.Model modelf=new Despacho.Presentation.Farmaceutico.Model();
            Despacho.Presentation.Farmaceutico.Controller controllerf=new Despacho.Presentation.Farmaceutico.Controller(farmaView,modelf);
            farmaView.setModel(modelf);
            farmaView.setController(controllerf);
            modelf.setList(Service.instance().findAllFarmaceutico());

            JFrame farmaWindow = new JFrame("Administración de Farmacéuticos");
            farmaWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            farmaWindow.setContentPane(farmaView.getPanel());
            farmaWindow.pack();
            farmaWindow.setLocationRelativeTo(null);
            farmaWindow.setVisible(true);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
        }
    }
}


