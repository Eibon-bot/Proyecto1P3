package Despacho.Logic.Entidades;

public class Medicamento {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int stock;

    public Medicamento() {}

    public Medicamento(String codigo, String nombre, String descripcion, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    public Medicamento(String codigo, String nombre, int stock, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }


    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }


    @Override
    public String toString() {
        return "Medicamento{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                '}';
    }
}

