package Despacho.Modelo.entidades;

public class Medicamento {

    private String codigo;
    private String nombre;
    private String descripcion;
    private String laboratorio;
    private int stock;
    private double precio;

    public Medicamento() {
    }

    public Medicamento(String codigo, String nombre, String descripcion,
                       String laboratorio, int stock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.laboratorio = laboratorio;
        this.stock = stock;
        this.precio = precio;
    }

    public Medicamento(String codigo, String nombre, int cantidad, double precio, String descripcion) {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", laboratorio='" + laboratorio + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }




}
