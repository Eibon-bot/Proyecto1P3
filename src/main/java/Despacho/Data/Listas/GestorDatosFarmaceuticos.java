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
            Data data = new Data();
            lista.forEach(data::agregarFarmaceutico);

            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, archivo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public List<Farmaceutico> cargar() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try {
            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Data data = (Data) um.unmarshal(archivo);
            return data.getFarmaceuticos();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public void cambiarClave(String id, String claveActual, String claveNueva) throws Exception {
        List<Farmaceutico> farmaceuticos = cargar();
        for (Farmaceutico m : farmaceuticos) {
            if (m.getId().equals(id)) {
                if (!m.validarClave(claveActual)) {
                    throw new Exception("Clave actual incorrecta");
                }
                m.setClave(claveNueva);
                guardar(farmaceuticos);
                return;
            }
        }
        throw new Exception("Médico no encontrado");
    }
}