package Despacho.Modelo.Listas;

import java.util.List;
import javax.lang.model.element.Name;
import javax.xml.bind.annotation.*;

import Despacho.Modelo.Usuarios.Medico;

@XmlRootElement(name="ListaMedicos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaMedicos {
    @XmlElement(name="medico")
    private List<Medico> medicos;

    public ListaMedicos() {}
    public ListaMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Medico> getMedicos() {
        return this.medicos;
    }
}
