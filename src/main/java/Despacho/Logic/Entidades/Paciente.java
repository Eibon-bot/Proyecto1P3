package Despacho.Logic.Entidades;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="Paciente")
@XmlAccessorType(XmlAccessType.FIELD)
public class Paciente {
    @XmlID
    private String id;
    private String nombre;
    private String fechaNacimiento;
    private String telefono;

    public Paciente() {}

    public Paciente(String id, String nombre, String fechaNacimiento, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Paciente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
