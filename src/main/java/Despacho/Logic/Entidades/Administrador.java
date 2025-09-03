package Despacho.Logic.Entidades;

public class Administrador extends Usuario {
    public Administrador(String id, String nombre, String clave) {
        super(id, nombre, clave, "administrador");
    }
    public Administrador(Usuario u) {
        super(u.getId(), u.getNombre(), u.getClave(), "administrador");
    }
}

