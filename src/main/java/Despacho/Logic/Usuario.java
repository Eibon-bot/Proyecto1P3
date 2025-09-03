package Despacho.Logic;

public abstract class Usuario {
    protected String id;
    protected String nombre;
    protected String clave;
    protected String rol;


    public Usuario(String id, String nombre, String clave, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.rol   = rol;
    }
    public String getId()      { return id; }
    public String getNombre()  { return nombre; }
    public String getClave()   { return clave; }
    public String getRol()     { return rol;   }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setClave(String clave)   { this.clave   = clave;   }
    public void setId(String id) { this.id = id; }

    public boolean validarClave(String intento) {
        return this.clave.equals(intento);
    }
}