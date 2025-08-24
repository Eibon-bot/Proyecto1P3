package Despacho.Modelo.Listas;
import Despacho.Modelo.Usuarios.Medico;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosMedicos {
    private File archivo=new File("medicos.xml");

    public void guardar(List<Medico> lista){
        try{
            JAXBContext ctx = JAXBContext.newInstance(Medico.class);
            Marshaller m = ctx.createMarshaller();
            m.marshal(new ListaMedicos(lista), archivo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Medico> cargar() {
        if (!archivo.exists()) return new ArrayList<Medico>();
        try {
            JAXBContext ctx = JAXBContext.newInstance(ListaMedicos.class);
            Unmarshaller um = ctx.createUnmarshaller();
            return ((ListaMedicos) um.unmarshal(archivo)).getMedicos();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}