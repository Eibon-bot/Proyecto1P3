package Despacho.Presentation.Prescribir.DialogsPrescribir;

import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Prescripcion;

import javax.swing.*;
import java.awt.event.*;

public class ModificarDetalle extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private Prescripcion prescripcion;
    private Medicamento med;

    public ModificarDetalle(Medicamento med) {
        this.med = med;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Modificar Detalle");
        pack();
        setLocationRelativeTo(null);




        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        int cantidad = (Integer) spinner1.getValue();
        int duracion = (Integer) spinner2.getValue();
        String indicaciones = textField1.getText();

        prescripcion = new Prescripcion(med, cantidad, indicaciones, duracion);
        dispose();
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }




}
