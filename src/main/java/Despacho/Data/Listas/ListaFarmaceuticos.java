package Despacho.Data.Listas;

import javax.xml.bind.annotation.*;
import java.util.List;

import Despacho.Data.Usuarios.Farmaceutico;

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
