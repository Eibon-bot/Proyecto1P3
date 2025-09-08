package Despacho.Logic.Entidades;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receta {

    private String codigo;
    private Paciente paciente;
    private Medico medico;
    private LocalDate fechaEmision;
    private List<Prescripcion> medicamentos;
    private String estado;

    public Receta() {
        this.medicamentos = new ArrayList<>();
    }

    public Receta(String codigo, Paciente paciente, Medico medico,
                  LocalDate fechaEmision, String estado) {
        this.codigo = codigo;
        this.paciente=paciente;
        this.medico = medico;
        this.fechaEmision = fechaEmision;
        this.medicamentos = new ArrayList<>();
        this.estado = estado;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public List<Prescripcion> getMedicamentos() { return medicamentos; }
    public void setMedicamentos(List<Prescripcion> medicamentos) { this.medicamentos = medicamentos; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public void agregarMedicamento(Prescripcion med) {
        medicamentos.add(med);
    }

    @Override
    public String toString() {
        return "Receta{" +
                "codigo='" + codigo + '\'' +
                ", idPaciente='" + paciente.getId() + '\'' +
                ", idMedico='" + medico.getId() + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", medicamentos=" + medicamentos +
                ", estado='" + estado + '\'' +
                '}';
    }
}
