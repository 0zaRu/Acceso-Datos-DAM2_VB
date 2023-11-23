package practica.completa;

import java.io.FileNotFoundException;
import java.util.Scanner;

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
                    case 'S': case 's':
                        System.out.println("Se va a salir del programa ...");
                        salir = true;
                    
                    case '1':
                        InOutFIies.escribeBinarios();
                        break;
                    
                    
                    default:
                        //nada
                }
            }catch(FileNotFoundException fnte){
                fnte.printStackTrace();
            }
            
            System.out.println("Pulsa enter para continuar ...");
            kb.nextLine();
            limpiarPantalla();
            
        }while(!salir);
        
    }
    
    
    public static char menu(){
        System.out.println("MENU CON TODAS LAS COSAS ESPERO:");
        System.out.println("================================");
        System.out.println("S. Salir");
        System.out.println("1. Escribe datos binarios.");
        System.out.println("2. Lee datos binarios.");
        return kb.nextLine().charAt(0);
        
    }
    
    public static void limpiarPantalla(){
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }
}
