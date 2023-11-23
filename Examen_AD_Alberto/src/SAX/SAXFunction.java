package SAX;

import modelo.Gestor;
import java.io.File;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 *
 * @author 6002754
 */
public class SAXFunction {
    public static void escribeSAX(Gestor g) throws TransformerConfigurationException, TransformerException{
        XMLReader datosReader = new DatosReader();
        InputSource datosSource = g;
        
        Source source = new SAXSource(datosReader, datosSource);
        
        File f = new File("informaticaSAX.xml");
        
        Result resultado = new StreamResult(f);
        
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(source, resultado);
        
        System.out.println("Se ha escrito con SAX correctamente");
    }
}
