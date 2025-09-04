package Despacho.Data.Listas;

import Despacho.Logic.Entidades.Paciente;

import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorDatosPacientes {
    private final File archivo = new File("listapacientes.xml");

    public void guardar(List<Paciente> lista) {
        try {
            Data data = new Data();
            data.setPacientes(new ArrayList<>(lista)); // SOLO pacientes
            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, archivo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> cargar() {
        if (!archivo.exists()) return new ArrayList<>();
        try {
            JAXBContext ctx = JAXBContext.newInstance(Data.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Data data = (Data) um.unmarshal(archivo);
            return Optional.ofNullable(data.getPacientes()).orElseGet(ArrayList::new);
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
