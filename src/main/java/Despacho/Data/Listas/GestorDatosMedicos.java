package Despacho.Data.Listas;

import Despacho.Logic.Entidades.Medico;

import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosMedicos {
    private final File archivo = new File("listas.xml");

    public void guardar(List<Medico> lista) {
        try {
            // Envolvemos solo los médicos en el wrapper Listas
            Data data = new Data();
            lista.forEach(data::agregarMedico);

            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, archivo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Medico> cargar() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Data data = (Data) um.unmarshal(archivo);
            return data.getMedicos();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void cambiarClave(String id, String claveActual, String claveNueva) throws Exception {
        List<Medico> medicos = cargar();
        for (Medico m : medicos) {
            if (m.getId().equals(id)) {
                if (!m.validarClave(claveActual)) {
                    throw new Exception("Clave actual incorrecta");
                }
                m.setClave(claveNueva);
                guardar(medicos);
                return;
            }
        }
        throw new Exception("Médico no encontrado");
    }
}

