package practica.completa;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author arube
 */
class SAXClass extends DefaultHandler{ 
    
    @Override
    public void startDocument() throws SAXException { 
        System.out.println("\nPrincipio del documento XML"); 
    } 
    
    @Override
    public void endDocument() throws SAXException { 
        System.out.println("\nFin del documento XML"); 
    } 
    
    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException { 
        System.out.print("\nEtiqueta -> "+ name); 
        
    } 
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException { 
        System.out.println("\tTexto: "+String.valueOf(ch, start, length)); 
    } 
    
    @Override
    public void endElement(String uri, String localName, String name) throws SAXException { 
        System.out.println("fin "+name); 
    }
}