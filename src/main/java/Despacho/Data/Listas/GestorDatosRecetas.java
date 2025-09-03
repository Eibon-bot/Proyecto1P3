package Despacho.Data.Listas;

import Despacho.Logic.Entidades.Receta;
import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosRecetas {

    private final File archivo = new File("listarecetas.xml");

    public void guardar(List<Receta> lista) {
        try {
            Data data = new Data();
            lista.forEach(data::agregarReceta);

            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, archivo);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Receta> cargar() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Data data = (Data) um.unmarshal(archivo);
            return data.getRecetas();

        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
