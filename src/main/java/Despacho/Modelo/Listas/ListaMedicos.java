package Despacho.Modelo.Listas;

import java.util.List;
import Despacho.Modelo.Usuarios.Medico;

public class ListaMedicos {
    private List<Medico> medicos;

    public ListaMedicos() {
    }

    public ListaMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Medico> getMedicos() {
        return this.medicos;
    }
}
