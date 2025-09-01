package Despacho.Data.Registro;

import java.io.File;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import Despacho.Logic.Administrador;
import Despacho.Logic.Farmaceutico;
import Despacho.Logic.Medico;
import Despacho.Logic.Usuario;
import org.w3c.dom.*;

public class UsuarioDAO {
    private final File xmlFile;

    public UsuarioDAO(String rutaXml) {
        this.xmlFile = new File(rutaXml);
    }

    public List<Usuario> cargarUsuarios() throws Exception {
        List<Usuario> lista = new ArrayList<>();

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        NodeList nodes = doc.getElementsByTagName("usuario");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            String id    = e.getAttribute("id");
            String rol   = e.getAttribute("rol");
            String nombre = e.getElementsByTagName("nombre").item(0).getTextContent();
            String clave  = e.getElementsByTagName("clave").item(0).getTextContent();

            switch (rol) {
                case "administrador":
                    lista.add(new Administrador(id, nombre, clave));
                    break;
                case "medico":
                    String esp = e.getElementsByTagName("especialidad").item(0).getTextContent();
                    lista.add(new Medico(id, nombre, clave, esp));
                    break;
                case "farmaceutico":
                    lista.add(new Farmaceutico(id, nombre, clave));
                    break;
                default:
                    throw new IllegalArgumentException("Rol desconocido en XML: " + rol);
            }
        }

        return lista;
    }

    public void guardarUsuarios(List<Usuario> usuarios) throws Exception {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("usuarios");
        doc.appendChild(root);

        for (Usuario u : usuarios) {
            Element eu = doc.createElement("usuario");
            eu.setAttribute("id", u.getId());
            eu.setAttribute("rol", u.getRol());

            Element enombre = doc.createElement("nombre");
            enombre.setTextContent(u.getNombre());
            eu.appendChild(enombre);

            Element eclave = doc.createElement("clave");
            eclave.setTextContent(u.getClave());
            eu.appendChild(eclave);

            if (u instanceof Medico) {
                Element eesp = doc.createElement("especialidad");
                eesp.setTextContent(((Medico) u).getEspecialidad());
                eu.appendChild(eesp);
            }

            root.appendChild(eu);
        }

        Transformer tr = TransformerFactory.newInstance().newTransformer();
        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        tr.transform(new DOMSource(doc), new StreamResult(xmlFile));
    }
}
