/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import entidades.Empleado;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author arube
 */
public class GestionContenidoSAX extends DefaultHandler {
    ArrayList<Empleado> empLeidos = new ArrayList<>();
    Empleado empTemp;
    
    String etiquetaActual = "";
    
    @Override
    public void startDocument() throws SAXException {
        //System.out.println("\nPrincipio del documento XML");
    }

    @Override
    public void endDocument() throws SAXException {
        //System.out.println("\nFin del documento XML");
    }

    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        //System.out.print("\nEtiqueta -> " + name);
        
        if(name.equals("empleado"))
            empTemp = new Empleado();
        
        else if(!name.equals("Empleados")){
            etiquetaActual = name;
        }
        
        /*Recorremos los atributos 
        System.out.println("\t" + attributes.getLength() + " atributos: ");
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.print("\t" + attributes.getQName(i) + ": " + attributes.getValue(i));
        }
        */
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //System.out.println("\tTexto: " + String.valueOf(ch, start, length));
        if(etiquetaActual.equals("id")){
            empTemp.setId(Integer.parseInt(String.valueOf(ch, start, length)));
        
        }else if(etiquetaActual.equals("nombre")){
            empTemp.setNombre(String.valueOf(ch, start, length));
        
        }else if(etiquetaActual.equals("apell1")){
            empTemp.setApell1(String.valueOf(ch, start, length));
        
        }else if(etiquetaActual.equals("apell2")){
            empTemp.setApell2(String.valueOf(ch, start, length));
        
        }else if(etiquetaActual.equals("salario")){
            empTemp.setSalario(Float.parseFloat(String.valueOf(ch, start, length)));
        }
    }

    @Override
    public void endElement(String uri, String localName, String name) throws SAXException {
        //System.out.println("fin " + name);
        
        if(name.equals("empleado"))
            empLeidos.add(empTemp);
        
    }
    
    public ArrayList<Empleado> getEmpLeidos(){
        if(empLeidos.isEmpty())
            return null;
        else
            return empLeidos;
    }
}
