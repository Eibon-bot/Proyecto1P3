package Despacho.Presentation.AgregarFarma;

import Despacho.AbstractModel;
import Despacho.Data.Usuarios.Usuario;

import java.beans.PropertyChangeListener;

public class Model extends AbstractModel {
    Usuario current;


    public static final String CURRENT = "current";

    public Model() {
        current=null;

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario usuario) {
        this.current = usuario;
        firePropertyChange(CURRENT);
    }


}
