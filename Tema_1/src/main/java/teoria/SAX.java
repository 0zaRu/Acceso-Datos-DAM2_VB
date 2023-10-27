package teoria;

import org.xml.sax.SAXException;

public class SAX {
    public static void main(String[] args) {
        GestionaSAX sax = new GestionaSAX();

        try {
            
            sax.startDocument();

            
            
            sax.endDocument();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        
    }
}
