package ejercicio8_ac;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author 6002754
 */
public class Ejercicio8_AC {

    static Scanner kb = new Scanner(System.in);
    static Statement st = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConector con = null;
        boolean salir = false;
        int opcionDB;
        
        try{
            opcionDB = menuDB();
            kb.nextLine();
            limpiarPantalla(25);
            switch(opcionDB){
                case 1 -> {
                    con = new DBConector("jdbc:sqlite:./ejemplo.db");
                }
                case 2 -> {
                    con = new DBConector("jdbc:derby:./ejemplo");
                }
                case 3 -> {
                    con = new DBConector("jdbc:hsqldb:file:./ejemplo");
                }
                case 4 -> {
                    con = new DBConector("jdbc:h2:./ejemplo");
                }
                case 5 -> {
                    con = new DBConector("jdbc:mysql://localhost/ejemplo", "root", "123456");
                }
                case 6 -> {
                    System.out.println("Se va a salir del programa");
                }
                default -> {
                    System.out.println("Valor introducido incorrecto, saldremos del programa");
                }
            }

            if(con == null)
                return;

            limpiarPantalla(25);          
            int opcion;
            do{
                try{
                    opcion = menu();
                    kb.nextLine();
                    limpiarPantalla(25);

                    switch(opcion){
                        case 1 -> {
                            con.getConnectionData();
                        }

                        case 2 -> {
                            con.getTableInfo();
                        }

                        case 3 -> {
                            con.getColumnsInfo();
                        }

                        case 4 -> {
                            System.out.println("Se va a salir del programa");
                            salir = true;
                        }

                        default -> System.out.println("Valor introducido incorrecto");
                    }

                }catch(SQLException e){
                    System.err.println("Hubo un error en relación a la base de datos");
                    System.out.println(e.getMessage());
                }catch(Exception e){
                    System.err.println("Hubo un error desconocido");
                }

                System.out.println("\nPulse enter para continuar ...");
                kb.nextLine();

                limpiarPantalla(25);

            }while(!salir);
            DBConector.con.close();
            
        }catch(SQLException e){
            System.err.println("Error instanciando la conexion");
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.err.println("Hubo un error desconocido");
        }
        
        kb.close();
    }

    public static int menu(){
        System.out.println("Elige una opcion: ");
        System.out.println("================================");
        System.out.println("");
        System.out.println("1. Mostrar informacion de la base de datos");
        System.out.println("2. Mostrar informacion de las tablas");
        System.out.println("3. Mostrar informacion de las columnas");
        System.out.println("4. Salir");
        System.out.print("\n================================\nElige una opcion: ");
        
        return kb.nextInt();
    }
    
    public static int menuDB(){
        System.out.println("Elige una opcion de BBDD para utilizar: ");
        System.out.println("================================");
        System.out.println("");
        System.out.println("1. SQLite");
        System.out.println("2. Derby");
        System.out.println("3. HSQLDB");
        System.out.println("4. H2");
        System.out.println("5. MySQL");
        System.out.println("6. Salir");
        System.out.print("\n================================\nElige una opcion: ");
        
        return kb.nextInt();
    }
    
    public static void limpiarPantalla(int lineas){
        for(int i=0; i<lineas; i++)
            System.out.println();
    }
}
