package Despacho.Presentation.Prescribir;

import Despacho.Logic.Entidades.Paciente;
import Despacho.Logic.Entidades.Prescripcion;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Service;
import Despacho.Presentation.Login.Controller;
import Despacho.Presentation.Prescribir.DialogsPrescribir.AgregarMedicamento;
import Despacho.Presentation.Prescribir.DialogsPrescribir.BuscarPacienteView;
import Despacho.Presentation.TableModelPres;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Prescribir implements PropertyChangeListener {
    private JButton buscarPacienteButton;
    private JButton agregarMedicamentoButton;
    private JTable table1;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton descartarMedicamentoButton;
    private JButton detallesButton;
    private JPanel Prescribir;
    private JLabel fechaRetirolabel;
    private JLabel pacienteLabel;
    private JPanel panelfecha;
    private JPanel panellistado;
    private JDateChooser fechaRetiroChooser;

    public Prescribir() {
        buscarPacienteButton.setIcon(new ImageIcon(getClass().getResource("/pacienteBuscar.png")));
        agregarMedicamentoButton.setIcon(new ImageIcon(getClass().getResource("/medicamentosP.png")));
        fechaRetirolabel.setIcon(new ImageIcon(getClass().getResource("/calendario.png")));
        pacienteLabel.setIcon(new ImageIcon(getClass().getResource("/pacienteBuscar.png")));
        guardarButton.setIcon(new ImageIcon(getClass().getResource("/guardar.png")));
        limpiarButton.setIcon(new ImageIcon(getClass().getResource("/limpiar.png")));
        descartarMedicamentoButton.setIcon(new ImageIcon(getClass().getResource("/descartar.png")));
        detallesButton.setIcon(new ImageIcon(getClass().getResource("/lista.png")));

        fechaRetiroChooser = new JDateChooser();
        fechaRetiroChooser.setDateFormatString("dd/MM/yyyy");
        panelfecha.setLayout(new java.awt.BorderLayout());
        panelfecha.add(fechaRetiroChooser, BorderLayout.CENTER);

        table1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table1.getSelectedRow() >= 0) {
                int row = table1.getSelectedRow();
                controller.setPaciente(row);
            }
        });

        panellistado.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize(new Dimension(500, 150));
        panellistado.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(guardarButton);
        panelBotones.add(limpiarButton);
        panelBotones.add(descartarMedicamentoButton);
        panelBotones.add(detallesButton);
        panellistado.add(panelBotones, BorderLayout.SOUTH);






        guardarButton.addActionListener(e -> {
            if (fechaRetiroChooser.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una fecha de retiro.");
                return;
            }

            Date date = fechaRetiroChooser.getDate();
            LocalDate fechaRetiro = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


            controller.guardarReceta(fechaRetiro);

            JOptionPane.showMessageDialog(null, "Receta guardada correctamente");
        });




        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPacienteView dialogBuscarPaciente = new BuscarPacienteView();
                dialogBuscarPaciente.setController(controller);
                dialogBuscarPaciente.setModel(model);

                controller.loadPacientes();

                dialogBuscarPaciente.setModal(true);
                dialogBuscarPaciente.setVisible(true);


//                if (dialogBuscarPaciente.getPacienteSeleccionado() != null) {
//                    controller.setPaciente(dialogBuscarPaciente.getPacienteSeleccionado());
//                }

            }
        });
        agregarMedicamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadMedicamentos();
                AgregarMedicamento dialogAgregarMedicamento = new AgregarMedicamento();
                dialogAgregarMedicamento.setController(controller);
                dialogAgregarMedicamento.setModel(model);

                controller.loadPacientes();

                dialogAgregarMedicamento.setModal(true);
                dialogAgregarMedicamento.setVisible(true);

            }
        });
    }

    public JPanel getPrescribir() {
        return Prescribir;
    }

    //-------- MVC ---------
    Despacho.Presentation.Prescribir.Controller controller;
    Despacho.Presentation.Prescribir.Model model;

    public void setController(Despacho.Presentation.Prescribir.Controller controller) {
        this.controller = controller;
    }

    public void setModel(Despacho.Presentation.Prescribir.Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.PACIENTE:
                Paciente p = model.getCurrentPaciente();
                if (p != null) {
                    pacienteLabel.setText("Paciente: " + p.getNombre());
                } else {
                    pacienteLabel.setText("Paciente no seleccionado");
                }
                break;
            case "PRESCRIPCION_TEMP":
                int[] cols = {
                        TableModelPres.MEDICAMENTO,
                        TableModelPres.PRESENTACION,
                        TableModelPres.CANTIDAD,
                        TableModelPres.DURACION,
                        TableModelPres.INDICACIONES
                };
                table1.setModel(new TableModelPres(cols, model.getPrescripcionesTemp()));
                break;


//            case Despacho.Presentation.Prescribir.Model.:
//                // refrescar la tabla con medicamentos
//                int[] cols = { /* columnas necesarias */ };
//                table1.setModel(new MedicamentoTableModel(cols, model.getMedicamentos()));
//                break;
        }

    }
}

