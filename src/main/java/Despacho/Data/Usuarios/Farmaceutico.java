package Despacho.Data.Usuarios;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Farmaceutico")
@XmlAccessorType(XmlAccessType.FIELD)
public class Farmaceutico extends Usuario {
    public Farmaceutico(String id, String nombre, String clave) {
        super(id, nombre, clave, "farmaceutico");
    }
    public Farmaceutico(Usuario u) {
        super(u.getId(), u.getNombre(), u.getClave(), "farmaceutico");
    }
}
