package Despacho;

import Despacho.Presentation.Prescribir.Prescribir;
import Despacho.Presentation.View.*;
import Despacho.Presentation.Login.Controller;
import Despacho.Presentation.Login.Model;
import Despacho.Data.Registro.AuthService;
import Despacho.Data.Listas.GestorDatosFarmaceuticos;
import Despacho.Data.Listas.GestorDatosMedicos;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el Look and Feel: " + ex.getMessage());
        }

        try {
            GestorDatosFarmaceuticos gf = new GestorDatosFarmaceuticos();
            GestorDatosMedicos gm = new GestorDatosMedicos();
            Model model = new Model();
            AuthService auth = new AuthService(gm,gf);
            login login = new login();
            Controller controller = new Controller(login, model, auth);

            JFrame window = new JFrame();
            window.setSize(350, 300);
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setTitle("Login");
            window.setContentPane(login.getLogin());
            window.setVisible(true);
//para probar
            JFrame windowPrescribir = new JFrame();
            windowPrescribir.setTitle("Preescribir");
            windowPrescribir.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            windowPrescribir.setContentPane(new Prescribir().getPrescribir());
            windowPrescribir.pack();
            windowPrescribir.setLocationRelativeTo(null); // Centrar la ventana
            windowPrescribir.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
        }
    }
    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);
}
