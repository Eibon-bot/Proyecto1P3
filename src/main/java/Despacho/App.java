package Despacho;

import Despacho.View.*;

import javax.swing.*;
public class App {
    public static void main(String[] args) {
        try {UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        login login = new login();

        JFrame window = new JFrame();
        window.setSize(350,300);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Login");
        window.setContentPane(login.getLogin());
        window.setVisible(true);
    }
}
