package Despacho.Data.Listas;
import Despacho.Data.Usuarios.Farmaceutico;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorDatosFarmaceuticos {
    private File archivo=new File("farmaceutico.xml");

    public void guardar(List<Farmaceutico> lista){
        try{
            JAXBContext ctx = JAXBContext.newInstance(Farmaceutico.class);
            Marshaller m = ctx.createMarshaller();
            m.marshal(new ListaFarmaceuticos(lista), archivo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Farmaceutico> cargar() {
        if (!archivo.exists()) return new ArrayList<Farmaceutico>();
        try {
            JAXBContext ctx = JAXBContext.newInstance(ListaFarmaceuticos.class);
            Unmarshaller um = ctx.createUnmarshaller();
            return ((ListaFarmaceuticos) um.unmarshal(archivo)).getFarmaceuticos();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}