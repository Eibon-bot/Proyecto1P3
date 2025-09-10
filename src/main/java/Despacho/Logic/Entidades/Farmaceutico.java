package Despacho.Logic.Entidades;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlID;
@XmlRootElement(name="Farmaceutico")
@XmlAccessorType(XmlAccessType.FIELD)
public class Farmaceutico extends Usuario {
    public Farmaceutico(String id, String nombre, String clave) {
        super(id, nombre, clave, "farmaceutico");
    }

    public Farmaceutico() {
        this("", "",  "");
    }

    public Farmaceutico(Usuario u) {
        super(u.getId(), u.getNombre(), u.getClave(), "farmaceutico");
    }

}
