/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DOM;

import modelo.Ciclo;
import modelo.Modulo;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 6002754
 */
public class DOMFuctions {

    public static ArrayList<Ciclo> leerDOM() throws ParseException, SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("Informatica.xml");
        document.getDocumentElement().normalize();

        System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());

        NodeList listaCiclos = document.getElementsByTagName("ciclo");
        
        ArrayList<Ciclo> recogidos = new ArrayList<>();
        
        for (int i = 0; i < listaCiclos.getLength(); i++) {
            Node ciclo = listaCiclos.item(i); //obtiene un nodo
            if (ciclo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) ciclo;
                
                String nombre_corto = getNodo("nombre_corto", elemento);
                String nombre_largo = getNodo("nombre_largo", elemento);
                String grado = getNodo("grado", elemento);
                String horas = getNodo("horas", elemento);

                NodeList listaModulos = elemento.getElementsByTagName("modulo");
                
                ArrayList<Modulo> modulosRecogidos = new ArrayList<>();
                
                for (int j = 0; j < listaModulos.getLength(); j++) {
                    Node modulo = listaModulos.item(j); //obtiene un nodo
                    if (modulo.getNodeType() == Node.ELEMENT_NODE) {
                        Element elementoM = (Element) modulo;
                
                        String nombre = getNodo("nombre", elementoM);
                        String curso = getNodo("curso", elementoM);
                        
                        modulosRecogidos.add(new Modulo(nombre, curso));
                    }
                }
                
                recogidos.add(new Ciclo(nombre_corto, nombre_largo, grado, Integer.parseInt(horas), modulosRecogidos));
            }
            
        }
        
        return recogidos;
        
    }
        
    private static String getNodo(String tag, Element elem) {
        NodeList nodo = elem.getElementsByTagName(tag).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue();
    }
}
