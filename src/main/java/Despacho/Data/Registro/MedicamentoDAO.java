package Despacho.Data.Registro;

import java.io.File;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import Despacho.Logic.Medicamento;
import org.w3c.dom.*;

public class MedicamentoDAO {
    private final File xmlFile;

    public MedicamentoDAO(String rutaXml) {
        this.xmlFile = new File(rutaXml);
    }

    public List<Medicamento> cargarMedicamentos() throws Exception {
        List<Medicamento> lista = new ArrayList<>();

        if (!xmlFile.exists()) {
            guardarMedicamentos(new ArrayList<>());
            return lista;
        }

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(xmlFile);
        NodeList nodes = doc.getElementsByTagName("medicamento");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);

            String codigo = e.getAttribute("codigo");
            String nombre = getTextContent(e, "nombre");
            int cantidad  = Integer.parseInt(getTextContent(e, "cantidad"));
            double precio = Double.parseDouble(getTextContent(e, "precio"));
            String descripcion = getTextContent(e, "descripcion");

            lista.add(new Medicamento(codigo, nombre, cantidad, precio, descripcion));
        }

        return lista;
    }

    public void guardarMedicamentos(List<Medicamento> medicamentos) throws Exception {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("medicamentos");
        doc.appendChild(root);

        for (Medicamento m : medicamentos) {
            Element em = doc.createElement("medicamento");
            em.setAttribute("codigo", m.getCodigo());

            Element enombre = doc.createElement("nombre");
            enombre.setTextContent(m.getNombre());
            em.appendChild(enombre);

            Element estock = doc.createElement("stock");
            estock.setTextContent(String.valueOf(m.getStock()));
            em.appendChild(estock);

            Element eprecio = doc.createElement("precio");
            eprecio.setTextContent(String.valueOf(m.getPrecio()));
            em.appendChild(eprecio);

            Element edesc = doc.createElement("descripcion");
            edesc.setTextContent(m.getDescripcion());
            em.appendChild(edesc);

            root.appendChild(em);
        }

        Transformer tr = TransformerFactory.newInstance().newTransformer();
        tr.setOutputProperty(OutputKeys.INDENT, "yes");
        tr.transform(new DOMSource(doc), new StreamResult(xmlFile));
    }

    private String getTextContent(Element e, String tag) {
        Node node = e.getElementsByTagName(tag).item(0);
        return (node != null) ? node.getTextContent() : "";
    }
}
