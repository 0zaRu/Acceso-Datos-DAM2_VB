/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import DOM.DOMFuctions;
import SAX.SAXFunction;
import modelo.Gestor;
import java.util.Scanner;
import mysql.SQLFunctions;

/**
 *
 * @author 6002754
 */
public class GestorVista {
    static Scanner kb = new Scanner(System.in);
    
    public static void main(String[] args) {
        limpiarPantalla();
        try{
            Gestor gestor = new Gestor();
            SQLFunctions mySQL = new SQLFunctions();
            boolean salir = false;

            do{
                try{
                    char opcion = menu();
                    limpiarPantalla();
                    switch(opcion){
                        case '1' -> {
                            gestor.imprime(gestor.getCiclosCargados());
                        }

                        case '2' -> {
                            gestor.setCiclosCargados(DOMFuctions.leerDOM());
                            System.out.println("Leido con DOM correctamente");
                        }

                        case '3' -> {
                            SAXFunction.escribeSAX(gestor);
                        }

                        case '4' -> {
                            mySQL.creaTablas();
                            System.out.println("Tablas creadas correctamente");
                        }
                        
                        case '5' -> {
                            mySQL.rellenaTablas(gestor.getCiclosCargados());
                            System.out.println("Tablas rellenadas correctamente");
                        }
                        
                        case '6' -> {
                            System.out.print("Introduce el nombre corto del ciclo: ");
                            String ciclo = kb.nextLine();
                            
                            System.out.print("Introduce primero o segundo, el que prfieras: ");
                            String curso = kb.nextLine();
                            
                            mySQL.busquedaCicloCurso(ciclo, curso);
                        }
                        
                        case '7' -> {
                            gestor.generaTextoPlano();
                        }
                        
                        case '8' -> {
                            gestor.escribeBinario(gestor.getCiclosCargados());
                        }
                        
                        case '9' -> {
                            gestor.setCiclosCargados(gestor.leeBinario());
                        }

                        case 's', 'S' -> {
                            System.out.println("Se va a salir");
                            salir = true;
                        }

                        default -> {
                        }
                    }



                }catch(Exception e){
                    System.err.println("Ha habido algun tipo de fallo ...");
                    e.printStackTrace();
                }

                System.out.println("\nPulsa enter para continuar");
                kb.nextLine();
                limpiarPantalla();
            }while(!salir);
            
        }catch(Exception e){
            System.err.println("Has tenido un problema conectándonte a la base de datos, comprueba tener todo bien con el fichero README de la entrega");
        }
        kb.close();
    }
    
    public static char menu(){
        System.out.println("MENU DEL EXAMEN:");
        System.out.println("==================================");
        System.out.println("S. Salir");
        System.out.println("1. Mostrar datos en memoria");
        System.out.println("2. Leer con DOM");
        System.out.println("3. Escribe con SAX");
        System.out.println("4. Crear tablas en MySQL (debes tener creada la database)");
        System.out.println("5. Rellenar tablas en MySQL (debes tener creada la database y las tablas)");
        System.out.println("6. Buscar por curso y ciclo.");
        System.out.println("7. Escribir ciclos en un fichero delimitado.");
        System.out.println("8. Escribe fichero binario.");
        //System.out.println("9. Lee fichero binario.");
        
        System.out.println("");
        
        return kb.nextLine().charAt(0);
    }
    
    public static void limpiarPantalla(){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
}
