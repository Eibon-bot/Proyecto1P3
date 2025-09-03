package Despacho.Presentation.Farmaceutico;

import Despacho.App;
import Despacho.Logic.Entidades.Farmaceutico;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FarmaAdmin implements PropertyChangeListener {
    private JLabel idlabelFarma;
    private JTextField textFieldIdFarma;
    private JLabel nombreFarma;
    private JTextField textFieldNomFarma;
    private JButton guardarButtonFarma;
    private JButton limpiarButtonFarma;
    private JButton borrarButtonFarma;
    private JTextField textFieldBusqFarma;
    private JButton buscarButtonFarma;
    private JButton reporteButtonFarma;
    private JComboBox tableFarma;
    private JPanel IngresarFarmaceutas;
    private JTable farmatable;

    public FarmaAdmin() {
        guardarButtonFarma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Farmaceutico n = take();
                    try {
                        controller.create(n);
                        JOptionPane.showMessageDialog(IngresarFarmaceutas, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(IngresarFarmaceutas, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        borrarButtonFarma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Farmaceutico n = take();
                    try {
                        controller.delete(n);
                        JOptionPane.showMessageDialog(IngresarFarmaceutas, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(IngresarFarmaceutas, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }


            }
        });

        buscarButtonFarma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.read(textFieldBusqFarma.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(IngresarFarmaceutas, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        limpiarButtonFarma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });

    }

    public JPanel getPanel() {
        return IngresarFarmaceutas;
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
            case Model.LIST:
                int[] cols = {TableModel.ID,TableModel.NOMBRE};
                farmatable.setModel(new TableModel(cols,model.getList()));
                break;
            case Model.CURRENT:
                textFieldIdFarma.setText(model.getCurrent().getId());
                textFieldNomFarma.setText(model.getCurrent().getNombre());
                textFieldIdFarma.setBackground(null);
                textFieldIdFarma.setToolTipText(null);
                textFieldNomFarma.setBackground(null);
                textFieldNomFarma.setToolTipText(null);
                break;
        }
        this.IngresarFarmaceutas.revalidate();
    }

    public Farmaceutico take() {
        Farmaceutico e = new Farmaceutico();
        e.setId(textFieldIdFarma.getText());
        e.setNombre(textFieldNomFarma.getText());

        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (textFieldIdFarma.getText().isEmpty()) {
            valid = false;
            textFieldIdFarma.setBackground(App.BACKGROUND_ERROR);
            textFieldIdFarma.setToolTipText("id requerido");
        } else {
            textFieldIdFarma.setBackground(null);
            textFieldIdFarma.setToolTipText(null);
        }

        if (textFieldNomFarma.getText().isEmpty()) {
            valid = false;
            textFieldNomFarma.setBackground(App.BACKGROUND_ERROR);
            textFieldNomFarma.setToolTipText("Nombre requerido");
        } else {
            textFieldNomFarma.setBackground(null);
            textFieldNomFarma.setToolTipText(null);
        }

        return valid;
    }
}