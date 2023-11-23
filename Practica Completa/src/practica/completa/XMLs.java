/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica.completa;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 *
 * @author arube
 */
public class XMLs extends InputSource{
    
    static ArrayList<Cosmere> ejemplo = new ArrayList<>();
    
    public XMLs(){
        ejemplo.add(new Cosmere("Brandom Sanderson", new Date(2023, 11, 23), 10000, 
                    new SLA(4, "Vida antes que muerte", new Date(2024, 12, 1))));
    }
    
    public static void imprimeDOM(Cosmere c) throws  ParserConfigurationException, TransformerException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "Cosmere", null);
        document.setXmlVersion("1.0");
        
        Element raiz = document.createElement("cosmere");
        document.getDocumentElement().appendChild(raiz);
        
        //se crea cada atributo del nodo
        Element creador = document.createElement("creador");
        Text textCreador = document.createTextNode(c.getCreador());
        raiz.appendChild(creador); //se une el elemento con su padre
        creador.appendChild(textCreador); // se añade el valor del elemento
        
        Element pSalida = document.createElement("proximaSalida");
        Text textPSalida = document.createTextNode(c.getProximaSalida().toString());
        raiz.appendChild(pSalida);
        pSalida.appendChild(textPSalida);
        
        Element hojas = document.createElement("hojasTotales");
        Text textHojas = document.createTextNode(""+c.getHojasTotales());
        raiz.appendChild(hojas);
        hojas.appendChild(textHojas);

        
        Element sla = document.createElement("sla");
        raiz.appendChild(sla);
        
        Element nLibros = document.createElement("nLibros");
        Text textNLibros = document.createTextNode(""+c.getSagaAsociada().getnLibros());
        sla.appendChild(nLibros);
        nLibros.appendChild(textNLibros);
        
        Element ideal = document.createElement("ideal");
        Text textIdeal = document.createTextNode(c.getSagaAsociada().getIdeal());
        sla.appendChild(ideal);
        ideal.appendChild(textIdeal);

        Element pSalidaSLA = document.createElement("proximaSalidaSLA");
        Text textPSalidaSLA = document.createTextNode(c.getSagaAsociada().getProximaSalida().toString());
        sla.appendChild(pSalidaSLA);
        pSalidaSLA.appendChild(textPSalidaSLA);
        
        //se crea la fuente XML partir del documento
        Source source = new DOMSource(document);
        //se crea el fichero de texto Empleados.xml
        Result result = new StreamResult(new java.io.File("Cosmere.xml"));
        // se crea un TransformerFactory
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //se transforma el árbol al fichero
        transformer.transform(source, result);
        
        System.out.println("Se ha guardado en el XML con DOM.");
    }
    
    public static void leeDOM() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //Pasamos un fichero XML a un árbol DOM usando el DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Se crea un Java DOM XML parser(analizador sintáctico)
        Document document = builder.parse("Cosmere.xml");
        document.getDocumentElement().normalize();
        //Se visualiza el nombre del elemento raíz
        System.out.println("Elemento raiz:" + 
        document.getDocumentElement().getNodeName());
        
        NodeList listaCosmere = document.getElementsByTagName("cosmere");
        
        for (int i = 0; i < listaCosmere.getLength(); i++) {
            Node empleado = listaCosmere.item(i); //obtiene un nodo
            
            if (empleado.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) empleado; 
                System.out.println("Autor: "+getNodo("creador", elemento));
                System.out.println("Fecha: "+ getNodo("proximaSalida", elemento));
                System.out.println("N.Hojas: "+ getNodo("hojasTotales", elemento));
                System.out.println("SLA: "+ getNodo("sla", elemento));
                System.out.println("N.Libros: "+ getNodo("nLibros", elemento));
                System.out.println("Ideal: "+ getNodo("ideal", elemento));
                System.out.println("Fecha: "+ getNodo("proximaSalidaSLA", elemento));
            }
        }
    }
    
    private static String getNodo(String tag, Element elem) {
        NodeList nodo = elem.getElementsByTagName(tag).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue();
    }
    
    public static void leeSAX() throws Exception{
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser = parserFactory.newSAXParser();
        XMLReader reader = parser.getXMLReader();
        reader.setContentHandler(new SAXClass());
        
        reader.parse(new InputSource(new FileInputStream("Cosmere.xml")));
        
    }
    
    public static void serializazXStream() throws Exception{
        ejemplo.clear();
        ejemplo.add(new Cosmere("Brandom Sanderson", new Date(2023, 11, 23), 10000, 
                    new SLA(4, "Vida antes que muerte", new Date(2024, 12, 1))));
        
        XStream xstream = new XStream();
        
        File ruta = new File("CosmereXS.xml");
        if(!ruta.exists())ruta.createNewFile();
        
        xstream.allowTypes(new String[]{"list", "practica.completa.Cosmere", "practica.completa.SLA"});
        xstream.toXML(ejemplo, new FileOutputStream(ruta));
    }
    
    public static void desSerializazXStream() throws Exception{
        XStream xstream = new XStream();

        xstream.allowTypes(new String[]{"practica.completa.Cosmere"});
        ArrayList<Cosmere> recibido = (ArrayList<Cosmere>) xstream.fromXML("CosmereXSX.xml");

        for (int i = 0; i < recibido.size(); i++) {
            System.out.println(recibido.get(i).toString()+"\n"+recibido.get(i).getSagaAsociada().toString());
        }
    }
}
