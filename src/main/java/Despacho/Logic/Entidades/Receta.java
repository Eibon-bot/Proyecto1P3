package Despacho.Logic.Entidades;


import Despacho.Logic.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Receta {


    private Paciente paciente;
    private Medico medico;

    private LocalDate fechaEmision;
    private LocalDate fechaRetiro;
    private List<Prescripcion> prescripciones;
    private String estado;

    public Receta() {
        this.prescripciones = new ArrayList<>();
    }

    public Receta(Paciente paciente, Medico medico,
                  LocalDate fechaEmision, LocalDate fechaRetiro, String estado) {
        this.paciente=paciente;
        this.medico = medico;
        this.fechaEmision = fechaEmision;
        this.fechaRetiro = fechaRetiro;
        this.prescripciones = new ArrayList<>();
        this.estado = estado;
    }


    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getFechaRetiro() { return fechaRetiro; }
    public void setFechaRetiro(LocalDate fechaRetiro) { this.fechaRetiro = fechaRetiro; }

    public List<Prescripcion> getPrescripciones() { return prescripciones; }
    public void setPrescripciones(List<Prescripcion> prescripciones) { this.prescripciones = prescripciones; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public void agregarMedicamento(Prescripcion med) {
        prescripciones.add(med);
    }

    @Override
    public String toString() {
        return "Receta{" +
                ", idPaciente='" + paciente.getId() + '\'' +
                ", idMedico='" + medico.getId() + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", medicamentos=" + prescripciones +
                ", estado='" + estado + '\'' +
                '}';
    }
}
