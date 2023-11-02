/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import entidades.Alm_datos;
import entidades.Empleado;
import java.io.IOException;
import java.util.List;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author arube
 */
public class SAXDatosReader implements XMLReader {

    private ContentHandler handler;
    private final AttributesImpl atts = new AttributesImpl();
//métodos que serán usados por el transformer

    @Override
    public void parse(InputSource input) throws IOException, SAXException {
        if (!(input instanceof Alm_datos)) {
            String m = "El único parámetro para el parser es un Alm_datos";
            throw new SAXException(m);
        }
        if (handler == null) {
            throw new SAXException("Sin ContentHandler");
        }
        Alm_datos source = (Alm_datos) input;
        List<Empleado> empleados = source.getDatos();
        handler.startDocument();
        handler.startElement("", "Empleados", "Empleados", atts);
        for (Empleado empl : empleados) {
            handler.startElement("", "empleado", "empleado", atts);
            
            handler.startElement("", "id", "id", atts);
            char[] id = Integer.toString(empl.getId()).toCharArray();
            handler.characters(id, 0, id.length);
            handler.endElement("", "id", "id");
            
            handler.startElement("", "nombre", "nombre", atts);
            char[] nombre = empl.getNombre().toCharArray();
            handler.characters(nombre, 0, nombre.length);
            handler.endElement("", "nombre", "nombre");
            
            handler.startElement("", "apell1", "apell1", atts);
            char[] apell1 = empl.getApell1().toCharArray();
            handler.characters(apell1, 0, apell1.length);
            handler.endElement("", "apell1", "apell1");
            
            handler.startElement("", "apell2", "apell2", atts);
            char[] apell2 = empl.getApell2().toCharArray();
            handler.characters(apell2, 0, apell2.length);
            handler.endElement("", "apell2", "apell2");
            
            handler.startElement("", "salario", "salario", atts);
            char[] salario = Float.toString(empl.getSalario()).toCharArray();
            handler.characters(salario, 0, salario.length);
            handler.endElement("", "salario", "salario");
            
            handler.endElement("", "empleado", "empleado");
        }
        
        handler.endElement("", "Empleados", "Empleados");
        handler.endDocument();
    }

    @Override
    public ContentHandler getContentHandler() {
        return handler;
    }

    @Override
    public void setContentHandler(ContentHandler handler) {
        this.handler = handler;
    }

    @Override
    public void parse(String systemId) {
    }

    @Override
    public boolean getFeature(String name) {
        return false;
    }

    @Override
    public void setFeature(String name, boolean value) {
    }

    @Override
    public Object getProperty(String name) {
        return null;
    }

    @Override
    public void setProperty(String name, Object value) {
    }

    @Override
    public void setEntityResolver(EntityResolver resolver) {
    }

    @Override
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override
    public void setDTDHandler(DTDHandler handler) {
    }

    @Override
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override
    public void setErrorHandler(ErrorHandler handler) {
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return null;
    }
}
