package Modelo.Usuarios;

public class Administrador extends Usuario {
    public Administrador(String id, String nombre, String clave) {
        super(id, nombre, clave, "administrador");
    }
}

