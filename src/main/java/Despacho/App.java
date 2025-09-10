package Despacho;

import Despacho.Logic.Service;
import Despacho.Presentation.Medicamentos.MedicaAdmin;
import Despacho.Presentation.Medico.MediAdmin;
import Despacho.Presentation.View.login;
import Despacho.Presentation.Login.Controller;
import Despacho.Presentation.Login.Model;
import Despacho.Data.Registro.AuthService;

import javax.swing.*;
import java.awt.*;




public class App {
    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);
    private static final boolean DEBUG_OPEN_ADMIN_SCREENS = false;

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            try {
                Model model = new Model();
                AuthService auth = new AuthService();
                login loginView = new login();
                new Controller(loginView, model, auth);

                JFrame window = new JFrame("Login");
                window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                window.setContentPane(loginView.getLogin());
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);

                if (DEBUG_OPEN_ADMIN_SCREENS) {
                    MediAdmin mView = new MediAdmin();
                    Despacho.Presentation.Medico.Model medModel = new Despacho.Presentation.Medico.Model();
                    new Despacho.Presentation.Medico.Controller(mView, medModel);
                    mView.setModel(medModel);
                    mView.setController(new Despacho.Presentation.Medico.Controller(mView, medModel));
                    medModel.setList(Service.instance().findAllMedicos());
                    JFrame medWin = new JFrame("Administración de Medicos");
                    medWin.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    medWin.setContentPane(mView.getPanel());
                    medWin.pack();
                    medWin.setLocationRelativeTo(null);
                    medWin.setVisible(true);

                    MedicaAdmin medsView = new MedicaAdmin();
                    Despacho.Presentation.Medicamentos.ModelMedicamentos medsModel = new Despacho.Presentation.Medicamentos.ModelMedicamentos();
                    new Despacho.Presentation.Medicamentos.ControllerMedicamentos(medsView, medsModel);
                    medsView.setModel(medsModel);
                    medsView.setController(new Despacho.Presentation.Medicamentos.ControllerMedicamentos(medsView, medsModel));
                    medsModel.setList(Service.instance().findAllMedicamento());
                    JFrame medsWin = new JFrame("Administración de Medicamentos");
                    medsWin.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    medsWin.setContentPane(medsView.getPanel());
                    medsWin.pack();
                    medsWin.setLocationRelativeTo(null);
                    medsWin.setVisible(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
            }
        });
    }




}

