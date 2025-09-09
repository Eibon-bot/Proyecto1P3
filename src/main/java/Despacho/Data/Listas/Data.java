package Despacho.Data.Listas;

import javax.xml.bind.annotation.*;
import java.util.*;

import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Medicamento;
import Despacho.Logic.Entidades.Receta;
import Despacho.Logic.Entidades.Paciente;

import java.util.ArrayList;
import java.util.List;



@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {



    @XmlElementWrapper(name = "ListaFarmaceuticos")
    @XmlElement(name = "farmaceutico")
    private List<Farmaceutico> farmaceuticos = new ArrayList<>();

    @XmlElementWrapper(name = "ListaMedicamentos")
    @XmlElement(name = "medicamento")
    private List<Medicamento> medicamentos = new ArrayList<>();

    @XmlElementWrapper(name = "ListaMedicos")
    @XmlElement(name = "medico")
    private List<Medico> medicos = new ArrayList<>();

    @XmlElementWrapper(name = "ListaRecetas")
    @XmlElement(name = "receta")
    private List<Receta> recetas = new ArrayList<>();


    @XmlElementWrapper(name = "ListaPacientes")
    @XmlElement(name = "paciente")
    private List<Despacho.Logic.Entidades.Paciente> pacientes = new ArrayList<>();



    public Data() {
        medicos = new ArrayList<>();
        farmaceuticos = new ArrayList<>();
        medicamentos = new ArrayList<>();
        recetas = new ArrayList<>();
        pacientes = new ArrayList<>();

    }

    public List<Farmaceutico> getFarmaceuticos() {
        return farmaceuticos;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public List<Paciente> getPacientes() { return pacientes; }






}




