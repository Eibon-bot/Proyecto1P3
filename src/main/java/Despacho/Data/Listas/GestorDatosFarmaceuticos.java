package Despacho.Data.Listas;

import Despacho.Logic.Farmaceutico;
import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosFarmaceuticos {
    private final File archivo = new File("listafarmaceutico.xml");

    public void guardar(List<Farmaceutico> lista) {
        try {
            Listas listas = new Listas();
            lista.forEach(listas::agregarFarmaceutico);

            JAXBContext ctx = JAXBContext.newInstance(Listas.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(listas, archivo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public List<Farmaceutico> cargar() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(Listas.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Listas listas = (Listas) um.unmarshal(archivo);
            return listas.getFarmaceuticos();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}