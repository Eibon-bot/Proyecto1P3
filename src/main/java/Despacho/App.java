package Despacho;

import Despacho.Presentation.View.*;
import Despacho.Presentation.Login.Controller;
import Despacho.Presentation.Login.Model;
import Despacho.Data.Registro.AuthService;
import Despacho.Data.Registro.UsuarioDAO;

import javax.swing.*;
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
}
