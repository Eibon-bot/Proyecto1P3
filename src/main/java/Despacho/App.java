package Despacho;

import Despacho.Data.Listas.Data;
import Despacho.Data.Listas.XmlPersister;
import Despacho.Logic.Service;
import Despacho.Presentation.Farmaceutico.FarmaAdmin;
import Despacho.Presentation.Medico.MediAdmin;
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

            MediAdmin MedView = new MediAdmin();
            Despacho.Presentation.Medico.Model modelm=new Despacho.Presentation.Medico.Model();
            Despacho.Presentation.Medico.Controller controllerm=new Despacho.Presentation.Medico.Controller(MedView,modelm);
            MedView.setModel(modelm);
            MedView.setController(controllerm);
            modelm.setList(Service.instance().findAllMedicos());

            JFrame mediWindow = new JFrame("Administración de Medicos");
            mediWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            mediWindow.setContentPane(MedView.getPanel());
            mediWindow.pack();
            mediWindow.setLocationRelativeTo(null);
            mediWindow.setVisible(true);


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
        }
    }
}


