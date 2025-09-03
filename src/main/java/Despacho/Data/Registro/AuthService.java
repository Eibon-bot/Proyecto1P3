package Despacho.Data.Registro;
import Despacho.Data.Listas.GestorDatosFarmaceuticos;
import Despacho.Data.Listas.GestorDatosMedicos;
import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Usuario;
import java.util.*;

public class AuthService {
    private final GestorDatosMedicos GestorMed;
    private final GestorDatosFarmaceuticos GestorFarma;

    private final Map<String, Usuario> usuarios = new HashMap<>();

    public AuthService( GestorDatosMedicos gestorMed, GestorDatosFarmaceuticos gestorFarma) throws Exception {
        GestorMed = gestorMed;
        GestorFarma = gestorFarma;
        cargarEnMemoria();
    }
    private void cargarEnMemoria() throws Exception {
        for (Usuario u : GestorMed.cargar()) {
            usuarios.put(u.getId(), u);
        }
        for (Usuario u : GestorFarma.cargar()) {
            usuarios.put(u.getId(), u);
        }
    }


    public Usuario login(String id, String clave) throws Exception {
        Usuario u = usuarios.get(id);
        if (u == null || !u.validarClave(clave)) {
            throw new Exception("Credenciales inválidas");
        }
        return u;
    }

    public void cambiarClave(String id, String claveActual, String claveNueva) throws Exception {
        Usuario u = usuarios.get(id);
        if (u == null) {
            throw new Exception("Usuario no encontrado");
        }
        if (u instanceof Medico) {
           GestorMed.cambiarClave(id, claveActual, claveNueva);
        }
        if (u instanceof Farmaceutico) {
            GestorFarma.cambiarClave(id, claveActual, claveNueva);
        }
    }
}
