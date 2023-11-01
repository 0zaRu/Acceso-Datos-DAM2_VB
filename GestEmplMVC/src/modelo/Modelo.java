package modelo;

import entidades.Alm_datos;
import entidades.Empleado;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author Carlos
 */
public class Modelo implements Serializable {
    
    public static final File fich = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"archivos"+System.getProperty("file.separator"), "EmpleadosMVC.xml");    
    private ArrayList<Empleado> empleados;
    
    public Modelo() {
        empleados = new ArrayList<>();
        compruebaFicheroStatico();
    }

    public ArrayList<String> mostrarEmpleados() {   
       ArrayList<String> emp_mostrar = new ArrayList<>();
       for (Empleado emp: empleados){
           emp_mostrar.add(emp.getColumna());
       }
       return emp_mostrar;
    }

    public void generarAleatorios(int n) {
        for(int i=0; i< n; i++){
            Empleado e= new Empleado();
            e.emp_aleatorio();
            empleados.add(e);
        }
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    public boolean generaDom(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
         
            for(int i = 0; i<empleados.size(); i++){
                Element raiz = document.createElement("empleado");
                document.getDocumentElement().appendChild(raiz);
                
                Element id = document.createElement("id");
                Text textId = document.createTextNode(Integer.toString(empleados.get(i).getId()));
                raiz.appendChild(id);
                id.appendChild(textId);
                
                Element nombre = document.createElement("nombre");
                Text textNombre = document.createTextNode(empleados.get(i).getNombre());
                raiz.appendChild(nombre);
                nombre.appendChild(textNombre);
                
                Element apell1 = document.createElement("apell1");
                Text textApell1 = document.createTextNode(empleados.get(i).getApell1());
                raiz.appendChild(apell1);
                apell1.appendChild(textApell1);
                
                Element apell2 = document.createElement("apell2");
                Text textApell2 = document.createTextNode(empleados.get(i).getApell2());
                raiz.appendChild(apell2);
                apell2.appendChild(textApell2);
                
                Element salario = document.createElement("salario");
                Text textSalario = document.createTextNode(Float.toString(empleados.get(i).getSalario()));
                raiz.appendChild(salario);
                salario.appendChild(textSalario);
            }

            Source source = new DOMSource(document);
            Result result = new StreamResult(fich);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            
            return true;
        }catch(ParserConfigurationException | TransformerException | DOMException e){
            return false;
        }
    }

    public boolean leeDom(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<Empleado> empTemp = new ArrayList<>();
        try{

            DocumentBuilder builder = factory.newDocumentBuilder();
            
            Document document = builder.parse(fich);
            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz:\t"+ document.getDocumentElement().getNodeName());

            NodeList listaEmpleados = document.getElementsByTagName("empleado");

            for(int i = 0; i < listaEmpleados.getLength(); i++){
                Node empleado = listaEmpleados.item(i);

                if(empleado.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element)empleado;
                    Empleado leido = new Empleado(Integer.parseInt(getNodo("id", elemento)),
                                                getNodo("nombre", elemento),
                                                getNodo("apell1", elemento),
                                                getNodo("apell2", elemento),
                                                Float.parseFloat(getNodo("salario", elemento)));
                    
                    empTemp.add(leido);
                }
                empleados = empTemp;
            }
            return true;
        }catch(IOException | NumberFormatException | ParserConfigurationException | SAXException e){
            return false;
        }
    }
    
    private static String getNodo(String tag, Element element) {
        
        NodeList nodo = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node valorNodo = (Node) nodo.item(0);

        return valorNodo.getNodeValue();
    }
    
    public boolean leeSax(){
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        ArrayList<Empleado> empTemp = new ArrayList<>();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            
            GestionContenidoSAX gCSAX = new GestionContenidoSAX();
            
            reader.setContentHandler(gCSAX);
            reader.parse(new InputSource(new FileInputStream(fich)));
            
            if((empTemp = gCSAX.getEmpLeidos()) == null){
                System.err.println("Fallo leyendo el XML o ausencia de empleados");
                return false;
            }else{
                empleados = empTemp;
                return true;
            }
            
        } catch (ParserConfigurationException ex) {
            System.err.println("ParserCofngurationException");
            return false;
        } catch (SAXException ex) {
            System.err.println("SAXException");
            return false;
        } catch (IOException e){
            return false;
        }
    }
    
    public boolean escribeSax(){
        
        try{
            
            XMLReader datosReader = new SAXDatosReader();
            InputSource datosSource = new Alm_datos(50);
            Source source = new SAXSource(datosReader, datosSource);
            
            Result resultado = new StreamResult(fich);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            
            transformer.transform(source, resultado);
            
            return true;
        }catch(IllegalArgumentException | TransformerException e){
            return false;
        }
    }
    
    
    private static void compruebaFicheroStatico() {
        if(!fich.exists()){
            if(!fich.getParentFile().exists())
                fich.getParentFile().mkdirs();
            
            try {
                fich.createNewFile();
            } catch (IOException e) {
                System.err.println("Problema creando fichero de XML.");
            }
        }
    }
}
