package Despacho.Data.Listas;

import Despacho.Logic.Medicamento;
import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosMedicamentos {
    private final File archivo = new File("listamedicamentos.xml");

    public void guardar(List<Medicamento> lista) {
        try {
            Data data = new Data();
            lista.forEach(data::agregarMedicamento);

            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, archivo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Medicamento> cargar() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Data data = (Data) um.unmarshal(archivo);
            return data.getMedicamentos();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}