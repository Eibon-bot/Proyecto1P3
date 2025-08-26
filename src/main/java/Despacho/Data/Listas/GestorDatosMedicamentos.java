package Despacho.Data.Listas;

import Despacho.Data.entidades.Medicamento;
import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosMedicamentos {
    private final File archivo = new File("listamedicamentos.xml");

    public void guardar(List<Medicamento> lista) {
        try {
            Listas listas = new Listas();
            lista.forEach(listas::agregarMedicamento);

            JAXBContext ctx = JAXBContext.newInstance(Listas.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(listas, archivo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Medicamento> cargar() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(Listas.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Listas listas = (Listas) um.unmarshal(archivo);
            return listas.getMedicamentos();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}