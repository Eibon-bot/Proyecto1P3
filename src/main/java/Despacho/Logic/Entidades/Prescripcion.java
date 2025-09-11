package Despacho.Logic.Entidades;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Prescripcion {

    @XmlIDREF
    private Medicamento medicamento;
    private int cantidad;
    private String indicaciones;
    private int duracion;


    public Prescripcion() {
    }

    public Prescripcion(Medicamento medicamento, int cantidad, String indicaciones, int duracion) {
        this.medicamento = medicamento;
        this.cantidad= cantidad;
        this.indicaciones = indicaciones;
        this.duracion = duracion;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "[" +
                "Medicamento= " + medicamento.getNombre() +
                ", cantidad= " + cantidad +
                ", idicaciones= " + indicaciones +
                ", duracion= " + duracion +
                ']';
    }
}
