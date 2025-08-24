package Despacho.Modelo.Listas;
import Despacho.Modelo.entidades.Medicamento;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GestorDatosMedicamentos {
    private File archivo=new File("medicamentos.xml");

    public void guardar(List<Medicamento> lista){
        try{
            JAXBContext ctx = JAXBContext.newInstance(Medicamento.class);
            Marshaller m = ctx.createMarshaller();
            m.marshal(new ListaMedicamentos(lista), archivo);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public List<Medicamento> cargar() {
        if (!archivo.exists()) return new ArrayList<Medicamento>();
        try {
            JAXBContext ctx = JAXBContext.newInstance(ListaMedicamentos.class);
            Unmarshaller um = ctx.createUnmarshaller();
            return ((ListaMedicamentos) um.unmarshal(archivo)).getMedicamentos();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
