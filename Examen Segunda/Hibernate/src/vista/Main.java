package vista;

import controlador.MetodosHibernate;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class Main {
    
    static Scanner kb = new Scanner(System.in);
    
    public static void main(String[] args){
        
        switch(verMenu()){
            case "1":
                
                System.out.print("Introduce el id de pedido que buscas: ");
                MetodosHibernate.mostrarPedido(kb.nextInt());
                
                break;
            case "2":
                
                MetodosHibernate.altaPedido(kb);
                
                break;
            default:
                break;
        }
        
        
        kb.close();
    }
    
    static String verMenu(){
        System.out.println("======MENU=========");
        System.out.println("1. Mostrar Pedido");
        System.out.println("2. Dar de alta un pedido");
        
        System.out.println("\n\n\t OPCION: ");
        return kb.nextLine();
    }
}
