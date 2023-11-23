package practica.completa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author arube
 */
public class PracticaCompleta {
    static Scanner kb = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean salir = false;
        
        do{
            try{
                char opcion = menu();
                limpiarPantalla();

                switch (opcion) {
                    case 'S', 's' -> {
                        System.out.println("Se va a salir del programa ...");
                        salir = true;
                    }
                    case '1' ->        InOutFIies.escribeBinarios();
                        
                    case '2' -> {
                        System.out.print("Introduce una frase: ");
                        String frase = kb.nextLine();
                        InOutFIies.escribeFrase(frase);
                    }
                    case '3' -> {
                        XMLs.imprimeDOM(new Cosmere("Brandom Sanderson", new Date(2023, 11, 23), 10000, 
                        new SLA(4, "Vida antes que muerte", new Date(2024, 12, 1))));
                    }
                    
                    case '4' -> {
                        XMLs.leeDOM();
                    }
                    
                    case '5' -> {
                        XMLs.leeSAX();
                    }
                    
                    case '6' -> {
                        XMLs.serializazXStream();
                    }
                    
                    case '7' -> {
                        XMLs.desSerializazXStream();
                    }
                    
                    case '8' -> {
                        BBDD.creataTabla();
                    }
                    
                    case '9' -> {
                        BBDD.insertaTabla(new Cosmere("Brandom Sanderson", new Date(2023, 11, 23), 10000, 
                                          new SLA(4, "Vida antes que muerte", new Date(2024, 12, 1))));
                    }
                    
                    default -> {
                        //NADA
                    }
                }
            }catch(FileNotFoundException fnte){
                fnte.printStackTrace();
            
            }catch(IOException ioe){
                ioe.printStackTrace();
            
            }catch(Exception e){
                e.printStackTrace();
            }
            
            
            System.out.println("Pulsa enter para continuar ...");
            kb.nextLine();
            limpiarPantalla();
            
        }while(!salir);
        kb.close();   
    }
    
    
    public static char menu(){
        System.out.println("MENU CON TODAS LAS COSAS ESPERO:");
        System.out.println("================================");
        System.out.println("S. Salir");
        System.out.println("1. Lee y escribe datos binarios.");
        System.out.println("2. Escribe y lee cadenas.");
        System.out.println("3. Escribe con DOM");
        System.out.println("4. Leer con DOM");
        System.out.println("5. Leer con SAX");
        System.out.println("6. Serializa con XStream");
        System.out.println("7. DesSerializa con XStream");
        System.out.println("8. Crea tabla en MySQL");
        System.out.println("9. Insertar un valor en las tablas");
        return kb.nextLine().charAt(0);
        
    }
    
    public static void limpiarPantalla(){
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }
}
