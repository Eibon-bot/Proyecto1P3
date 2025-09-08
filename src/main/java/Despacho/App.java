package Despacho;

import Despacho.Data.Listas.Data;
import Despacho.Data.Listas.XmlPersister;
import Despacho.Logic.Service;
import Despacho.Presentation.Farmaceutico.FarmaAdmin;
import Despacho.Presentation.Medicamentos.MedicaAdmin;
import Despacho.Presentation.Medico.MediAdmin;
import Despacho.Presentation.Pacientes.PacientesAdmin;
import Despacho.Presentation.Prescribir.DialogsPrescribir.BuscarPacienteView;
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

            MediAdmin pView = new MediAdmin();
            Despacho.Presentation.Medico.Model modelm=new Despacho.Presentation.Medico.Model();
            Despacho.Presentation.Medico.Controller controllerm=new Despacho.Presentation.Medico.Controller(pView,modelm);
            pView.setModel(modelm);
            pView.setController(controllerm);
            modelm.setList(Service.instance().findAllMedicos());

            JFrame pacWindow = new JFrame("Administración de Medicos");
            pacWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pacWindow.setContentPane(pView.getPanel());
            pacWindow.pack();
            pacWindow.setLocationRelativeTo(null);
            pacWindow.setVisible(true);

            MedicaAdmin mView = new MedicaAdmin();
            Despacho.Presentation.Medicamentos.ModelMedicamentos modelme=new Despacho.Presentation.Medicamentos.ModelMedicamentos();
            Despacho.Presentation.Medicamentos.ControllerMedicamentos controllerme=new Despacho.Presentation.Medicamentos.ControllerMedicamentos(mView,modelme);
            mView.setModel(modelme);
            mView.setController(controllerme);
            modelme.setList(Service.instance().findAllMedicamento());

            JFrame meWindow = new JFrame("Administración de Medicamentos");
            meWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            meWindow.setContentPane(mView.getPanel());
            meWindow.pack();
            meWindow.setLocationRelativeTo(null);
            meWindow.setVisible(true);



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
        }
    }
}


