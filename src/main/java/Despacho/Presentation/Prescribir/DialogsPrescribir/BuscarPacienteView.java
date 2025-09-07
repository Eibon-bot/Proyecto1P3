package Despacho.Presentation.Prescribir.DialogsPrescribir;

import Despacho.App;
import Despacho.Logic.Entidades.Paciente;
import Despacho.Presentation.Prescribir.Controller;
import Despacho.Presentation.Prescribir.Model;
import Despacho.Presentation.Prescribir.TableModelPacientes;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;



public class BuscarPacienteView extends JDialog implements PropertyChangeListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> comboBox1;
    private JTextField TextFieldBuscarP;
    private JTable table1;

    public BuscarPacienteView() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Buscar Paciente");
        pack();
        setLocationRelativeTo(null);


        TextFieldBuscarP.getDocument().addDocumentListener(new DocumentListener() {
            private void ejecutarBusqueda() {
                if (controller == null) return;
                if (comboBox1.getSelectedIndex() == -1) return;
                switch (comboBox1.getSelectedItem().toString()) {
                    case "Nombre":
                        controller.searchPacienteNombre(TextFieldBuscarP.getText());
                        break;
                    case "ID":
                        controller.searchPacienteId(TextFieldBuscarP.getText());
                        break;

                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                ejecutarBusqueda();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                ejecutarBusqueda();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                ejecutarBusqueda();
            }
        });


        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() >= 0) {
                    controller.setPaciente(table1.getSelectedRow());
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPacienteView.this.setVisible(false);
            }
        });
    }
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
            case Model.LISTPACIENTE:
                int[] cols = {TableModelPacientes.ID, TableModelPacientes.NOMBRE};
                table1.setModel(new TableModelPacientes(cols, model.getListPaciente()));
                break;
        }
        this.contentPane.revalidate();

    }

    }


