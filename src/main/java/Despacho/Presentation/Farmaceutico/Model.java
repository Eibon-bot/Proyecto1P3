package Despacho.Presentation.Farmaceutico;

import Despacho.AbstractModel;
import Despacho.Logic.Farmaceutico;
import Despacho.Logic.Usuario;

import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Farmaceutico current;


    public static final String CURRENT = "current";

    public Model() {
        current=null;

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
    }

    public Farmaceutico getCurrent() {
        return current;
    }

    public void setCurrent(Farmaceutico farmaceutico) {
        this.current = Farmaceutico;
        firePropertyChange(CURRENT);
    }


}
