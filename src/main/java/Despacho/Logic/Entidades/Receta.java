package Despacho.Logic.Entidades;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receta {

    private String codigo;
    private String idPaciente;
    private Medico medico;
    private LocalDate fechaEmision;
    private List<Prescripcion> medicamentos;
    private String observaciones;

    public Receta() {
        this.medicamentos = new ArrayList<>();
    }

    public Receta(String codigo, String idPaciente, Medico medico,
                  LocalDate fechaEmision, String observaciones) {
        this.codigo = codigo;
        this.idPaciente = idPaciente;
        this.medico = medico;
        this.fechaEmision = fechaEmision;
        this.medicamentos = new ArrayList<>();
        this.observaciones = observaciones;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public List<Prescripcion> getMedicamentos() { return medicamentos; }
    public void setMedicamentos(List<Prescripcion> medicamentos) { this.medicamentos = medicamentos; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public void agregarMedicamento(Prescripcion med) {
        medicamentos.add(med);
    }

    @Override
    public String toString() {
        return "Receta{" +
                "codigo='" + codigo + '\'' +
                ", idPaciente='" + idPaciente + '\'' +
                ", idMedico='" + medico.getId() + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", medicamentos=" + medicamentos +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
