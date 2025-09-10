package Despacho.Presentation.DespachoFarma;



import javax.swing.*;

public class DespachoFarma {
    private JPanel panel1;
    private JTextField textFieldBuscarPaciente;
    private JTable table1Pacientes;
    private JLabel labelPaciente;
    private JTable tableRecetasPaciente;
    private JButton siguienteButton;

    public DespachoFarma() {
    }

    public JPanel getPanel1() {
        return panel1;
    }







    Despacho.Presentation.DespachoFarma.ControllerDF controller;
    Despacho.Presentation.DespachoFarma.ModelDF model;

    public void setController(Despacho.Presentation.DespachoFarma.ControllerDF controller) {
        this.controller = controller;
    }

    public void setModel(Despacho.Presentation.DespachoFarma.ModelDF model) {
        this.model = model;
//        model.addPropertyChangeListener(this);
    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        switch (evt.getPropertyName()) {
//            case Model.PACIENTE:
//                Paciente p = model.getCurrentPaciente();
//                if (p != null) {
//                    labelPaciente.setText("Paciente: " + p.getNombre());
//                } else {
//                    labelPaciente.setText("Paciente no seleccionado");
//                }
//                break;
//
//
//        }
//
//    }
}
