package teoria;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DOM {

    static File fich = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"archivos"+System.getProperty("file.separator")+"DOM"+System.getProperty("file.separator"), "Empleados.xml");
    
    public static void main(String[] args){
        
        compruebaFicheroStatico();

        creaDOM(1, "Alberto", 333, 2345);

        System.out.println("\n");

        leeDOM();

    }

    static void creaDOM(int eId, String eNombre, int eDepartamento, double eSalario){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, "Empleados", null);
         
            Element raiz = document.createElement("empleado");
            document.getDocumentElement().appendChild(raiz);

            Element id = document.createElement("id");
            Text textId = document.createTextNode(Integer.toString(eId));
            raiz.appendChild(id);
            id.appendChild(textId);

            Element nombre = document.createElement("nombre");
            Text textNombre = document.createTextNode(eNombre);
            raiz.appendChild(nombre);
            nombre.appendChild(textNombre);


            Element departamento = document.createElement("departamento");
            Text textDepartamento = document.createTextNode(Integer.toString(eDepartamento));
            raiz.appendChild(departamento);
            departamento.appendChild(textDepartamento);

            Element salario = document.createElement("salario");
            Text textSalario = document.createTextNode(Double.toString(eSalario));
            raiz.appendChild(salario);
            salario.appendChild(textSalario);

            Source source = new DOMSource(document);
            Result result = new StreamResult(fich);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            Result console = new StreamResult(System.out);
            transformer.transform(source, console);

        }catch(Exception e){

        }
    }

    static void leeDOM(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

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
                    System.out.println("Id:\t\t" + getNodo("id", elemento));
                    System.out.println("Nombre:\t\t" + getNodo("nombre", elemento));
                    System.out.println("Departamento:\t" + getNodo("departamento", elemento));
                    System.out.println("Salario:\t" + getNodo("salario", elemento));
                }
            }

        }catch(Exception e){
            System.err.println("Error.");
        }
        
    }

    private static String getNodo(String tag, Element element) {
        
        NodeList nodo = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node valorNodo = (Node) nodo.item(0);

        return valorNodo.getNodeValue();
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
