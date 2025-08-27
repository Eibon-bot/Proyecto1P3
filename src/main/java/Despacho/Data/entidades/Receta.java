package Despacho.Data.entidades;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receta {

    private String codigo;
    private String idPaciente;
    private String idMedico;
    private LocalDate fechaEmision;
    private List<MedicamentoRecetado> medicamentos;
    private String observaciones;

    public Receta() {
        this.medicamentos = new ArrayList<>();
    }

    public Receta(String codigo, String idPaciente, String idMedico,
                  LocalDate fechaEmision, String observaciones) {
        this.codigo = codigo;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.fechaEmision = fechaEmision;
        this.medicamentos = new ArrayList<>();
        this.observaciones = observaciones;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }

    public String getIdMedico() { return idMedico; }
    public void setIdMedico(String idMedico) { this.idMedico = idMedico; }

    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }

    public List<MedicamentoRecetado> getMedicamentos() { return medicamentos; }
    public void setMedicamentos(List<MedicamentoRecetado> medicamentos) { this.medicamentos = medicamentos; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public void agregarMedicamento(MedicamentoRecetado med) {
        medicamentos.add(med);
    }

    @Override
    public String toString() {
        return "Receta{" +
                "codigo='" + codigo + '\'' +
                ", idPaciente='" + idPaciente + '\'' +
                ", idMedico='" + idMedico + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", medicamentos=" + medicamentos +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
