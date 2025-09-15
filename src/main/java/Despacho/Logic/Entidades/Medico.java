package Despacho.Logic.Entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlID;
@XmlRootElement(name="Medico")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medico extends Usuario {
    private String especialidad;

    public Medico(String id, String nombre, String clave, String especialidad) {
        super(id, nombre, clave, "medico");
        this.especialidad = especialidad;
    }
    public Medico() {
        this("", "",  "", "");
    }

    public Medico(Usuario u) {
        super(u.getId(), u.getNombre(), u.getClave(), "medico");
        if (u instanceof Medico) {
            this.especialidad = ((Medico) u).getEspecialidad();
        } else {
            this.especialidad = "";
        }
    }


    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String esp) { this.especialidad = esp; }


}

