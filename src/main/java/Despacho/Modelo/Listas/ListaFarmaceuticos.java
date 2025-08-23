package Despacho.Modelo.Listas;

import java.util.List;
import Despacho.Modelo.Usuarios.Farmaceutico;

public class ListaFarmaceuticos {
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
