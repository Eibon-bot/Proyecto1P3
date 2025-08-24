package Despacho.Modelo.Listas;

import javax.lang.model.element.Name;
import javax.xml.bind.annotation.*;
import java.util.List;

import Despacho.Modelo.Usuarios.Farmaceutico;

@XmlRootElement(name="ListaFarmaceuticos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaFarmaceuticos {
    @XmlElement(name="farmaceutico")
    private List<Farmaceutico> farmaceuticos;

    public ListaFarmaceuticos() {
    }

    public ListaFarmaceuticos(List<Farmaceutico> farmaceuticos) {
        this.farmaceuticos = farmaceuticos;
    }

    public List<Farmaceutico> getFarmaceuticos() {
        return this.farmaceuticos;
    }
}
