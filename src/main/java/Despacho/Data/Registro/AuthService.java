package Despacho.Data.Registro;

import Despacho.Data.Listas.Data;
import Despacho.Data.Listas.XmlPersister;
import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AuthService {
    private final Map<String, Usuario> usuarios = new HashMap<>();

    public AuthService() throws Exception {
        cargarEnMemoria();
    }

    private void cargarEnMemoria() throws Exception {
        Data data = XmlPersister.instance().load();

        // Cargar médicos
        for (Medico m : data.getMedicos()) {
            if (m != null && m.getId() != null) {
                usuarios.put(m.getId(), m);
            }
        }

        // Cargar farmacéuticos
        for (Farmaceutico f : data.getFarmaceuticos()) {
            if (f != null && f.getId() != null) {
                usuarios.put(f.getId(), f);
            }
        }

        // ---- SEED EN MEMORIA ----
        usuarios.putIfAbsent("m1", new Medico("m1", "Dr. Demo", "m1", "General"));
        usuarios.putIfAbsent("f1", new Farmaceutico("f1", "Farma Demo", "f1"));
    }

    public Usuario login(String id, String clave) throws Exception {
        if (id == null || clave == null) throw new Exception("Debe digitar id y clave");
        String key = id.trim();
        Usuario u = usuarios.get(key);
        if (u == null || !u.validarClave(clave)) {
            throw new Exception("Credenciales inválidas");
        }
        return u;
    }

    public void cambiarClave(String id, String claveActual, String claveNueva) throws Exception {
        if (id == null) throw new Exception("Id requerido");
        Usuario u = usuarios.get(id);
        if (u == null) throw new Exception("Usuario no encontrado");
        if (claveNueva == null || claveNueva.isEmpty()) throw new Exception("La nueva clave no puede estar vacía");

        // Validar clave actual
        if (!u.validarClave(claveActual)) throw new Exception("Clave actual incorrecta");

        // Cambiar en memoria
        u.setClave(claveNueva);
        usuarios.put(id, u);

        // Persistir en archivo XML
        Data data = XmlPersister.instance().load();

        if (u instanceof Medico) {
            for (Medico m : data.getMedicos()) {
                if (Objects.equals(m.getId(), id)) {
                    m.setClave(claveNueva);
                    break;
                }
            }
        } else if (u instanceof Farmaceutico) {
            for (Farmaceutico f : data.getFarmaceuticos()) {
                if (Objects.equals(f.getId(), id)) {
                    f.setClave(claveNueva);
                    break;
                }
            }
        }

        XmlPersister.instance().store(data);
    }
}

