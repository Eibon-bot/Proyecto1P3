package Despacho.Data.Listas;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XmlPersister {
    private String path;
    private static XmlPersister theInstance;
    public static XmlPersister instance(){
        if (theInstance==null) theInstance=new XmlPersister("data.xml");
        return theInstance;
    }
    public XmlPersister(String p) {
        path=p;
    }
    public Data load() throws Exception {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            Data empty = new Data();
            store(empty);
            return empty;
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        try (FileInputStream is = new FileInputStream(file)) {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Data) unmarshaller.unmarshal(is);
        }
    }
    public void store(Data d) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        try (FileOutputStream os = new FileOutputStream(path)) {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(d, os);
        }
    }

}
