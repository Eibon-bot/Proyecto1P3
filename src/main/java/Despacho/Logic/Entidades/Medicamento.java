package Despacho.Logic.Entidades;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="Medicamento")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicamento {
    @XmlID
    private String codigo;
    private String nombre=" ";
    private String presentacion;

    public Medicamento() {}

    public Medicamento(String codigo, String nombre, String presentacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.presentacion = presentacion;
    }



    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPresentacion() { return presentacion; }
    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }




    @Override
    public String toString() {
        return "Medicamento{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", presentacion='" + presentacion + '\'' +
                '}';
    }
}

