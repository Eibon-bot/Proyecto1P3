package Despacho;

import Despacho.Presentation.View.*;

import Despacho.Data.Registro.AuthService;
import Despacho.Presentation.Farmaceutico.Controller;
import Despacho.Presentation.Farmaceutico.Model;
import Despacho.Presentation.Farmaceutico.FarmaAdmin;
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
            Model model = new Model();
            UsuarioDAO uDAO = new UsuarioDAO("target/classes/usuarios.xml");
            AuthService auth = new AuthService(uDAO);
            login login = new login();
            Controller controller = new Controller(login, model, auth);

            JFrame window = new JFrame();
            window.setSize(350, 300);
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            window.setTitle("Login");
            window.setContentPane(login.getLogin());
            window.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage());
        }
    }
    public static final Color BACKGROUND_ERROR = new Color(255, 102, 102);
}
