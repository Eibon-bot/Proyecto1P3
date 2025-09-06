package Despacho.Presentation.Prescribir;

import Despacho.AbstractModel;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Paciente;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Entidades.Medicamento;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Paciente paciente;
    Receta receta;
    Medicamento medicamento;
    private List<Receta> listReceta;
    private List<Paciente> listPaciente;

    public static final String PACIENTE = "paciente";
    public static final String RECETA = "receta";
    public static final String MEDICAMENTO = "medicamento";
    public static final String LISTPACIENTE = "listPaciente";
    public static final String LISTRECETA = "listReceta";
    public Model() {
        paciente=null;
        receta=null;
        medicamento=null;
        listReceta=new ArrayList<>();
        listPaciente = new ArrayList<>();

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(PACIENTE);
        firePropertyChange(RECETA);
        firePropertyChange(MEDICAMENTO);
    }

    public Paciente getCurrentPaciente() {
        return paciente;
    }
    public Medicamento getCurrentMedicamento() {
        return medicamento;
    }
    public Receta getCurrentReceta() {
        return receta;
    }

    public void setCurrentPaciente(Paciente paciente) {
        this.paciente = paciente;
        firePropertyChange(PACIENTE);
    }
    public void setCurrentMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        firePropertyChange(MEDICAMENTO);
    }
    public void setCurrentReceta(Receta receta) {
        this.receta = receta;
        firePropertyChange(RECETA);
    }

    //Listas
    public List<Paciente> getListPaciente() {
        return listPaciente;
    }

    public void setListPaciente(List<Paciente> listPaciente) {
        this.listPaciente = listPaciente;
        firePropertyChange(LISTPACIENTE);
    }
    public List<Receta> getListReceta() {
        return listReceta;
    }

    public void setListReceta(List<Receta> listReceta) {
        this.listReceta = listReceta;
        firePropertyChange(LISTRECETA);
    }
}
