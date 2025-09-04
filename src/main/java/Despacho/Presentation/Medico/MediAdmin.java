package Despacho.Presentation.Medico;

import Despacho.App;
import Despacho.Logic.Entidades.Medico;
import Despacho.Presentation.Medico.Controller;
import Despacho.Presentation.Medico.Model;
import Despacho.Presentation.Medico.TableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MediAdmin implements PropertyChangeListener {
    private JTextField textFieldIdMed;
    private JTextField textFieldNomMed;
    private JButton guardarButtonMed;
    private JButton limpiarButtonMed;
    private JTextField textFieldEspMed;
    private JButton borrarButtonMed;
    private JTextField textFieldBusqMed;
    private JButton buscarButtonMed;
    private JButton reporteButtonMed;
    private JTable meditable;
    private JPanel MenuMedicos;


    public MediAdmin() {
        guardarButtonMed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Medico n = take();
                    try {
                        controller.create(n);
                        JOptionPane.showMessageDialog(MenuMedicos, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MenuMedicos, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        borrarButtonMed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Medico n = take();
                    try {
                        controller.delete(n);
                        JOptionPane.showMessageDialog(MenuMedicos, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(MenuMedicos, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }


            }
        });

        buscarButtonMed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.read(textFieldBusqMed.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MenuMedicos, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        limpiarButtonMed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });

    }

    public JPanel getPanel() {
        return MenuMedicos;
    }

    Despacho.Presentation.Medico.Controller controller;
    Despacho.Presentation.Medico.Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Despacho.Presentation.Medico.Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Despacho.Presentation.Farmaceutico.Model.LIST:
                int[] cols = {Despacho.Presentation.Farmaceutico.TableModel.ID, Despacho.Presentation.Farmaceutico.TableModel.NOMBRE};
                meditable.setModel(new TableModel(cols,model.getList()));
                break;
            case Model.CURRENT:
                textFieldIdMed.setText(model.getCurrent().getId());
                textFieldNomMed.setText(model.getCurrent().getNombre());
                textFieldEspMed.setText(model.getCurrent().getEspecialidad());
                textFieldIdMed.setBackground(null);
                textFieldIdMed.setToolTipText(null);
                textFieldNomMed.setBackground(null);
                textFieldNomMed.setToolTipText(null);
                textFieldEspMed.setBackground(null);
                textFieldEspMed.setToolTipText(null);
                break;
        }
        this.MenuMedicos.revalidate();
    }

    public Medico take() {
        Medico e = new Medico();
        e.setId(textFieldIdMed.getText());
        e.setNombre(textFieldNomMed.getText());

        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (textFieldIdMed.getText().isEmpty()) {
            valid = false;
            textFieldIdMed.setBackground(App.BACKGROUND_ERROR);
            textFieldIdMed.setToolTipText("id requerido");
        } else {
            textFieldIdMed.setBackground(null);
            textFieldIdMed.setToolTipText(null);
        }

        if (textFieldNomMed.getText().isEmpty()) {
            valid = false;
            textFieldNomMed.setBackground(App.BACKGROUND_ERROR);
            textFieldNomMed.setToolTipText("Nombre requerido");
        } else {
            textFieldNomMed.setBackground(null);
            textFieldNomMed.setToolTipText(null);
        }

        return valid;
    }
}
