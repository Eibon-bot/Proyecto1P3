package Despacho.Presentation.Pacientes;

import Despacho.App;
import Despacho.Logic.Entidades.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PacientesAdmin implements PropertyChangeListener {


    private JTextField textFieldIdPac;
    private JTextField textFieldNomPac;
    private JButton guardarButtonPac;
    private JButton limpiarButtonPac;
    private JButton borrarButtonPac;
    private JTextField textFieldBusqPac;
    private JButton buscarButtonPac;
    private JButton reporteButtonPac;  // (opcional; por ahora queda sin handler real)
    private JComboBox<Paciente> comboBoxPac;
    private JPanel IngresarPaciente;


    private Controller controller;
    private Model model;

    public PacientesAdmin() {


        guardarButtonPac.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    try {
                        controller.create(take());
                        JOptionPane.showMessageDialog(IngresarPaciente, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(IngresarPaciente, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });



        borrarButtonPac.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                String id = textFieldIdPac.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(IngresarPaciente, "Id requerido para borrar", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    Paciente p = new Paciente();
                    p.setId(id);
                    controller.delete(p);
                    JOptionPane.showMessageDialog(IngresarPaciente, "REGISTRO ELIMINADO", "", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(IngresarPaciente, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        limpiarButtonPac.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });


        buscarButtonPac.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                try {
                    controller.read(textFieldBusqPac.getText());
                } catch (Exception ex) {
                     JOptionPane.showMessageDialog(IngresarPaciente, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        reporteButtonPac.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(IngresarPaciente, "Reporte no implementado aún.");
            }
        });


        comboBoxPac.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Paciente) {
                    Paciente p = (Paciente) value;
                    setText((p.getId() != null ? p.getId() : "") + " - " + (p.getNombre() != null ? p.getNombre() : ""));
                } else if (value == null && index == -1) {
                    setText("");
                }
                return this;
            }
        });
        comboBoxPac.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Object sel = comboBoxPac.getSelectedItem();
                if (sel instanceof Paciente) controller.selectFromList((Paciente) sel);
            }
        });
    }


    public JPanel getPanel() { return IngresarPaciente; }


    public void setController(Controller controller) { this.controller = controller; }
    public void setModel(Model model) {
        this.model = model;
        this.model.addPropertyChangeListener(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                DefaultComboBoxModel<Paciente> cbm = new DefaultComboBoxModel<>();
                for (Paciente p : model.getList()) cbm.addElement(p);
                comboBoxPac.setModel(cbm);
                break;

            case Model.CURRENT:
                Paciente c = model.getCurrent();
                textFieldIdPac.setText(c.getId() == null ? "" : c.getId());
                textFieldNomPac.setText(c.getNombre() == null ? "" : c.getNombre());


                textFieldIdPac.setBackground(null); textFieldIdPac.setToolTipText(null);
                textFieldNomPac.setBackground(null); textFieldNomPac.setToolTipText(null);


                ComboBoxModel<Paciente> modelCB = comboBoxPac.getModel();
                for (int i = 0; i < modelCB.getSize(); i++) {
                    Paciente pi = modelCB.getElementAt(i);
                    if (pi != null && c.getId() != null && c.getId().equalsIgnoreCase(pi.getId())) {
                        comboBoxPac.setSelectedIndex(i);
                        break;
                    }
                }
                break;
        }
        IngresarPaciente.revalidate();
        IngresarPaciente.repaint();
    }


    private Paciente take() {
        Paciente p = new Paciente();
        p.setId(textFieldIdPac.getText().trim());
        p.setNombre(textFieldNomPac.getText().trim());

        return p;
    }

    private boolean validateForm() {
        boolean ok = true;

        if (textFieldIdPac.getText().trim().isEmpty()) {
            ok = false;
            textFieldIdPac.setBackground(App.BACKGROUND_ERROR);
            textFieldIdPac.setToolTipText("Id requerido");
        } else {
            textFieldIdPac.setBackground(null);
            textFieldIdPac.setToolTipText(null);
        }

        if (textFieldNomPac.getText().trim().isEmpty()) {
            ok = false;
            textFieldNomPac.setBackground(App.BACKGROUND_ERROR);
            textFieldNomPac.setToolTipText("Nombre requerido");
        } else {
            textFieldNomPac.setBackground(null);
            textFieldNomPac.setToolTipText(null);
        }

        return ok;
    }
}
