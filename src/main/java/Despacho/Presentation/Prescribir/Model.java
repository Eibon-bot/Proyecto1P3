package Despacho.Presentation.Prescribir;

import Despacho.AbstractModel;
import Despacho.Logic.Entidades.Paciente;

import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Paciente current;


    public static final String CURRENT = "current";

    public Model() {
        current=null;

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
    }

    public Paciente getCurrentPaciente() {
        return current;
    }

    public void setCurrentPaciente(Paciente paciente) {
        this.current = paciente;
        firePropertyChange(CURRENT);
    }


}
