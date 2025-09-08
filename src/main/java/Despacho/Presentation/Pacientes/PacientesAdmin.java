package Despacho.Presentation.Pacientes;

import Despacho.App;
import Despacho.Logic.Entidades.Paciente;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PacientesAdmin implements PropertyChangeListener {


    private JTextField textFieldIdPac;
    private JTextField textFieldNomPac;
    private JButton guardarButtonPac;
    private JButton limpiarButtonPac;
    private JButton borrarButtonPac;
    private JTextField textFieldBusqPac;
    private JButton buscarButtonPac;
    private JButton reporteButtonPac;
    private JPanel IngresarPaciente;
    private JTable tablePacientes;
    private JTextField textFieldFN;
    private JTextField textFieldNum;
    private JPanel panelFecha;
    private JDateChooser fecha;


    private Controller controller;
    private Model model;

    public PacientesAdmin() {
        fecha = new JDateChooser();
        fecha.setDateFormatString("dd/MM/yyyy");
        panelFecha.setLayout(new java.awt.BorderLayout());
        panelFecha.add(fecha, BorderLayout.CENTER);


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


//        comboBoxPac.setRenderer(new DefaultListCellRenderer() {
//            @Override public Component getListCellRendererComponent(JList<?> list, Object value, int index,
//                                                                    boolean isSelected, boolean cellHasFocus) {
//                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                if (value instanceof Paciente) {
//                    Paciente p = (Paciente) value;
//                    setText((p.getId() != null ? p.getId() : "") + " - " + (p.getNombre() != null ? p.getNombre() : ""));
//                } else if (value == null && index == -1) {
//                    setText("");
//                }
//                return this;
//            }
//        });
//        comboBoxPac.addActionListener(new ActionListener() {
//            @Override public void actionPerformed(ActionEvent e) {
//                Object sel = comboBoxPac.getSelectedItem();
//                if (sel instanceof Paciente) controller.selectFromList((Paciente) sel);
//            }
//        });
    }


    public JPanel getPanel() { return IngresarPaciente; }


    public void setController(Controller controller) {
        controller.clear();
        this.controller = controller; }

    public void setModel(Model model) {
        this.model = model;
        this.model.addPropertyChangeListener(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Despacho.Presentation.Pacientes.Model.LIST:
                int[] cols = {TableModelPacientes.ID, TableModelPacientes.NOMBRE, TableModelPacientes.FECHANACIMIENTO, TableModelPacientes.TELEFONO};
                tablePacientes.setModel(new TableModelPacientes(cols,model.getList()));
                break;
            case Despacho.Presentation.Pacientes.Model.CURRENT:
                textFieldIdPac.setText(model.getCurrent().getId());
                textFieldNomPac.setText(model.getCurrent().getNombre());
                try {
                    Date d = new SimpleDateFormat("dd/MM/yyyy").parse(model.getCurrent().getFechaNacimiento());
                    fecha.setDate(d);  // Le paso la fecha al JDateChooser
                } catch (Exception ex) {
                    fecha.setDate(null);  // Si hay error (por ejemplo fecha vacía o mal formada), limpio el campo
                }
                textFieldNum.setText(model.getCurrent().getTelefono());
                textFieldIdPac.setBackground(null);
                textFieldIdPac.setToolTipText(null);
                textFieldNomPac.setBackground(null);
                textFieldNomPac.setToolTipText(null);
                panelFecha.setBackground(null);
                panelFecha.setToolTipText(null);
                textFieldNum.setBackground(null);
                textFieldNum.setToolTipText(null);
                break;
        }
        this.IngresarPaciente.revalidate();
    }


    private Paciente take() {
        Paciente p = new Paciente();
        p.setId(textFieldIdPac.getText().trim());
        p.setNombre(textFieldNomPac.getText().trim());
        if (fecha.getDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            p.setFechaNacimiento(sdf.format(fecha.getDate()));
        }
        p.setTelefono(textFieldNum.getText().trim());

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

        if (fecha.getDate() == null) {
            ok = false;
            panelFecha.setBackground(App.BACKGROUND_ERROR);
            panelFecha.setToolTipText("Fecha de Nacimiento requerida");
        } else {
            panelFecha.setBackground(null);
            panelFecha.setToolTipText(null);
        }

        if(textFieldNum.getText().trim().isEmpty()){
            ok = false;
            textFieldNum.setBackground(App.BACKGROUND_ERROR);
            textFieldNum.setToolTipText("Número de Teléfono requerido");
        } else {
            textFieldNum.setBackground(null);
            textFieldNum.setToolTipText(null);
        }

        return ok;
    }
}
