package Despacho.Data.Listas;

import Despacho.Data.Usuarios.Medico;
import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosMedicos {
    private final File archivo = new File("listas.xml");

    public void guardar(List<Medico> lista) {
        try {
            // Envolvemos solo los médicos en el wrapper Listas
            Listas listas = new Listas();
            lista.forEach(listas::agregarMedico);

            JAXBContext ctx = JAXBContext.newInstance(Listas.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(listas, archivo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Medico> cargar() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(Listas.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Listas listas = (Listas) um.unmarshal(archivo);
            return listas.getMedicos();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

