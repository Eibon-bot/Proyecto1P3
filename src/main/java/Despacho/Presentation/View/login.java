package Despacho.Presentation.View;

import Despacho.Presentation.Login.Controller;
import Despacho.Presentation.Login.Model;
import Despacho.Logic.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class login implements PropertyChangeListener {
    private JPanel Login;
    private JTextField ID;
    private JPasswordField CLAVE;
    private JLabel id;
    private JLabel clave;
    private JButton ingresarButton;
    private JButton cancelarButton;
    private JButton cambiarClaveButton;

    public login() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = ID.getText().trim();
                String clave = new String(CLAVE.getPassword());
                try {
                    controller.ingresar(id, clave);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

            }
        });
    }

    public JPanel getLogin() {
        return Login;
    }

    //-------- MVC ---------
    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.CURRENT:
                Usuario nuevoUsuario = model.getCurrent();
                if (nuevoUsuario != null) { // <-- clave
                    ID.setText(nuevoUsuario.getId());
                    CLAVE.setText(nuevoUsuario.getClave());
                } else {
                    ID.setText("");
                    CLAVE.setText("");
                }
                break;
        }
    }
}
