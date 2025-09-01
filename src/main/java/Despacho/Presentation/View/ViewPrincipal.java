package Despacho.Presentation.View;

import Despacho.Presentation.Farmaceutico.Controller;
import Despacho.Presentation.Farmaceutico.Model;


import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewPrincipal implements PropertyChangeListener {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTabbedPane PanelIngresarFarmc;
    private JTabbedPane PanelIngresarPac;
    private JTabbedPane PanelIngresarMedicamentos;
    private JTabbedPane PanelDashboard;
    private JTabbedPane PanelHistorico;
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
    private JComboBox comboBoxFar;
    private JTabbedPane PanelIngresarMed;
    private JTextField textFieldIdPac;
    private JTextField textFieldNomPac;
    private JTextField textFieldBusqPac;
    private JComboBox comboBoxPac;
    private JButton guardarButtonPac;
    private JButton limpiarButtonPac;
    private JButton borrarButtonPac;
    private JButton buscarButtonPac;
    private JButton reporteButtonPac;
    private JTextField textFieldEspMed;
    private JTextField textFieldIdMed;
    private JTextField textFieldNomMed;
    private JTextField textFieldBusqMed;
    private JComboBox comboBoxListadoMed;
    private JButton guardarButtonMed;
    private JButton limpiarButtonMed;
    private JButton borrarButtonMed;
    private JButton buscarButtonMed;
    private JButton reporteButtonMed;
    private JPanel Prescribir;
    private JButton buscarPacienteButton;
    private JButton agregarMedicamentoButton;
    private JSpinner spinner1;
    private JTable table1;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton descartarMedicamentoButton;
    private JButton detallesButton;

    public JPanel getPanel1() {
        return panel1;
    }

    //-------- MVC ---------
    Controller controller;
    Model model;
//    Controller controllerAF;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        switch (evt.getPropertyName()) {
//            case Model.CURRENT:
//                Usuario nuevoUsuario = model.getCurrent();
//                if (nuevoUsuario != null) { // <-- clave
//                    ID.setText(nuevoUsuario.getId());
//                    CLAVE.setText(nuevoUsuario.getClave());
//                } else {
//                    ID.setText("");
//                    CLAVE.setText("");
//                }
//                break;
        }

}
