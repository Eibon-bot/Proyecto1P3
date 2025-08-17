package Usuarios;

public class Medico extends Usuario {
    private String especialidad;

    public Medico(String id, String nombre, String clave, String especialidad) {
        super(id, nombre, clave, "medico");
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String esp) { this.especialidad = esp; }
}

