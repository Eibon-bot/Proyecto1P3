package Despacho.Presentation.Prescribir;
import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Service;
import Despacho.Logic.Entidades.Paciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
        Prescribir prescribirView;
        Model model;

        public Controller(Prescribir prescribirView, Model model) {
            this.prescribirView = prescribirView;
            this.model = model;
            prescribirView.setController(this);
            prescribirView.setModel(model);
        }

        //Buscar Paciente Metodos
        public void searchPacienteNombre(String nombre) {
            Paciente p = new Paciente();
            p.setNombre(nombre);
            model.setListPaciente(Service.instance().searchPacienteNombre(p));
        }
        public void searchPacienteId(String id) {
        Paciente p = new Paciente();
        p.setId(id);
        model.setListPaciente(Service.instance().searchPacienteId(p));
        }
        public void setPaciente(int row) {
        model.setCurrentPaciente(model.getListPaciente().get(row));
        }

        public void loadPacientes() {
        model.setListPaciente(Service.instance().findAllPaciente());
        }
    public void searchMedicamentoNombre(String nombre) {
        Medicamento p = new Medicamento();
        p.setNombre(nombre);
        model.setListmedicamento(Service.instance().searchMedicamentoNombre(p));
    }
    public void searchMedicamentoCod(String cod) {
        Medicamento p = new Medicamento();
        p.setCodigo(cod);
        model.setListmedicamento(Service.instance().searchMedicamentoCod(p));
    }

    public void setMedicamento(int row) {
        model.setCurrentMedicamento(model.getListaMedicamentos().get(row));
    }

    public void loadMedicamentos() {
        model.setListmedicamento(Service.instance().findAllMedicamento());
    }

    public Medico getCurrentMedico() {
        return model.getCurrentMedico();
    }

    public void setCurrentMedico(Medico medico) {
        model.setCurrentMedico(medico);
    }

    public void limpiarPrescripciones() {
        model.limpiarPrescripcionesTemp();
    }
    public void descartarPrescripcion(int indice) {
        model.removerPrescripcionTemp(indice);
    }



    public void guardarReceta(LocalDate fechaRetiro) {

        Receta receta = new Receta();
        receta.setPaciente(model.getCurrentPaciente());
        receta.setMedico(model.getCurrentMedico());
        receta.setFechaEmision(LocalDate.now());
        receta.setFechaRetiro(fechaRetiro);
        receta.setPrescripciones(new ArrayList<>(model.getPrescripcionesTemp()));
        receta.setEstado("Confeccionada");

        Service.instance().findAllRecetas().add(receta);
        Service.instance().store();
        List<Receta> recetas = model.getListReceta();
        recetas.add(receta);
        model.setListReceta(recetas);
        model.setCurrentReceta(receta);

        model.limpiarPrescripcionesTemp();
    }











}
