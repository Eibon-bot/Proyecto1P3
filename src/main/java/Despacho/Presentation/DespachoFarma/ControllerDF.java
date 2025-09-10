package Despacho.Presentation.DespachoFarma;


import Despacho.Logic.Entidades.Paciente;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Service;

import javax.swing.*;
import java.util.List;

public class ControllerDF {
    private ModelDF model;
    private DespachoFarma view;
    private String usuarioRol;

    public ControllerDF(ModelDF model, DespachoFarma view, String usuarioRol) {
        this.model = model;
        this.view = view;
        this.usuarioRol = usuarioRol;

        this.view.setController(this);
        this.view.setModel(model);
    }

    public void buscarPaciente(String id) throws Exception {
        try {
            Paciente criterio = new Paciente();
            criterio.setId(id);

            List<Paciente> resultados = Service.instance().searchPacienteId(criterio);

            if (!resultados.isEmpty()) {
                model.setListaPacientes(resultados);

                model.setCurrentPaciente(resultados.get(0));
//                model.setRecetasPaciente(Service.instance().recetasDePaciente(resultados.get(0)));

            } else {
                Paciente b = new Paciente();
                b.setId(id);

                model.setCurrentPaciente(b);
                model.setRecetasPaciente(null);

                throw new Exception("Paciente no encontrado.");
            }

        } catch (Exception ex) {
            throw ex;
        }
    }


    public void avanzarEstado(Receta receta) {
        if (!"farmaceuta".equals(usuarioRol)) {
            JOptionPane.showMessageDialog(view.getPanel1(), "Solo farmaceutas pueden despachar recetas.");
            return;
        }

        switch (receta.getEstado()) {
            case "confeccionada":
                receta.setEstado("proceso");
                break;
            case "proceso":
                receta.setEstado("lista");
                break;
            case "lista":
                receta.setEstado("entregada");
                break;
            default:
                JOptionPane.showMessageDialog(view.getPanel1(), "La receta ya está entregada.");
        }

        try {
//            Service.instance().actualizarReceta(receta);

            model.updateReceta(receta);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getPanel1(), "Error al actualizar receta: " + e.getMessage());
        }
    }
}
