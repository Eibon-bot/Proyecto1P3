package Despacho.Data.Registro;
import Despacho.Logic.Usuario;
import java.util.*;

public class AuthService {
    private final UsuarioDAO dao;
    private final Map<String, Usuario> usuarios = new HashMap<>();

    public AuthService(UsuarioDAO dao) throws Exception {
        this.dao = dao;
        cargarEnMemoria();
    }

    private void cargarEnMemoria() throws Exception {
        for (Usuario u : dao.cargarUsuarios()) {
            usuarios.put(u.getId(), u);
        }
    }

    public void registrarUsuario(Usuario nuevo) throws Exception {
        if (usuarios.containsKey(nuevo.getId())) {
            throw new Exception("El ID ya existe: " + nuevo.getId());
        }
        usuarios.put(nuevo.getId(), nuevo);
        dao.guardarUsuarios(new ArrayList<>(usuarios.values()));
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
        if (!u.validarClave(claveActual)) {
            throw new Exception("Clave actual incorrecta");
        }
        u.setClave(claveNueva);
        dao.guardarUsuarios(new ArrayList<>(usuarios.values()));
    }
}
