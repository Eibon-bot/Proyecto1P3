package Despacho.Data.Registro;

import Despacho.Data.Listas.GestorDatosFarmaceuticos;
import Despacho.Data.Listas.GestorDatosMedicos;
import Despacho.Logic.Entidades.Farmaceutico;
import Despacho.Logic.Entidades.Medico;
import Despacho.Logic.Entidades.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AuthService {
    private final GestorDatosMedicos GestorMed;
    private final GestorDatosFarmaceuticos GestorFarma;

    private final Map<String, Usuario> usuarios = new HashMap<>();

    public AuthService(GestorDatosMedicos gestorMed, GestorDatosFarmaceuticos gestorFarma) throws Exception {
        this.GestorMed = gestorMed;
        this.GestorFarma = gestorFarma;
        cargarEnMemoria();
    }

    private void cargarEnMemoria() throws Exception {


        for (Usuario u : GestorMed.cargar()) {
            if (u != null && u.getId() != null) {
                usuarios.put(u.getId(), u);
            }
        }
        for (Usuario u : GestorFarma.cargar()) {
            if (u != null && u.getId() != null) {
                usuarios.put(u.getId(), u);
            }
        }

        // ---- SEED EN MEMORIA  ----

        usuarios.putIfAbsent("m1", new Medico("m1", "Dr. Demo", "m1", "General"));
        usuarios.putIfAbsent("f1", new Farmaceutico("f1", "Farma Demo", "f1"));
        // ------------------------------------------------
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


        if (u instanceof Medico) {
            GestorMed.cambiarClave(id, claveActual, claveNueva);

            List<Medico> medicos = GestorMed.cargar();
            for (Medico m : medicos) {
                if (Objects.equals(m.getId(), id)) {
                    usuarios.put(id, m);
                    return;
                }
            }
        } else if (u instanceof Farmaceutico) {
            GestorFarma.cambiarClave(id, claveActual, claveNueva);


            List<Farmaceutico> farmas = GestorFarma.cargar();
            for (Farmaceutico f : farmas) {
                if (Objects.equals(f.getId(), id)) {
                    usuarios.put(id, f);
                    return;
                }
            }
        } else {

            if (!u.validarClave(claveActual)) throw new Exception("Clave actual incorrecta");

            u.setClave(claveNueva);
            usuarios.put(id, u);
        }
    }
}
