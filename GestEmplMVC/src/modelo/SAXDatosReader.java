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
        handler.startElement("", "empleados", "empleados", atts);
        for (Empleado empl : empleados) {//El id, se añade como atributo para ver el uso de atributos, podría ser un elemento más
            atts.addAttribute("", "id", "id", "", String.valueOf(empl.getId()));
            handler.startElement("", "empleado", "empleado", atts);
            atts.clear();
            handler.startElement("", "nombre", "nombre", atts);
            char[] nombre = empl.getNombre().toCharArray();
            handler.characters(nombre, 0, nombre.length);
            handler.endElement("", "nombre", "nombre");
        }
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
