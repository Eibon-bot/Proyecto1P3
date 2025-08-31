package Despacho.Data.entidades;

public class MedicamentoRecetado {

    private String codigoMedicamento;
    private String dosis;
    private String frecuencia;
    private int diasTratamiento;

    public MedicamentoRecetado(String codigoMedicamento, String dosis, String frecuencia, int diasTratamiento) {
        this.codigoMedicamento = codigoMedicamento;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.diasTratamiento = diasTratamiento;
    }

    public String getCodigoMedicamento() { return codigoMedicamento; }
    public void setCodigoMedicamento(String codigoMedicamento) { this.codigoMedicamento = codigoMedicamento; }

    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }

    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }

    public int getDiasTratamiento() { return diasTratamiento; }
    public void setDiasTratamiento(int diasTratamiento) { this.diasTratamiento = diasTratamiento; }

    @Override
    public String toString() {
        return "MedicamentoRecetado{" +
                "codigoMedicamento='" + codigoMedicamento + '\'' +
                ", dosis='" + dosis + '\'' +
                ", frecuencia='" + frecuencia + '\'' +
                ", diasTratamiento=" + diasTratamiento +
                '}';
    }
}
