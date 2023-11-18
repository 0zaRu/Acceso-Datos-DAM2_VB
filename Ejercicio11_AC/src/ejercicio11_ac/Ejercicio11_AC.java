package ejercicio11_ac;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author 6002754
 */
public class Ejercicio11_AC {

    static Scanner kb = new Scanner(System.in);
    static Statement st = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            DBConector con = new DBConector();

            boolean salir = false;
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
                            con.deleteTables();
                        }
                         
                        case 5 -> {
                            con.createTables();
                        }
                         
                        case 6 -> {
                            con.insertNewEmployee(kb);
                        }
                         
                        case 7 -> {
                            con.deleteEmployee(kb);
                        }
                        
                        case 8 -> {
                            con.insertNewDepartment(kb);
                        }
                         
                        case 9 -> {
                            con.deleteDepartment(kb);
                        }
                        
                        case 10 -> {
                            con.modifyEmployee(kb);
                        }
                        
                        case 11 -> {
                            con.modifyDepartment(kb);
                        }

                        case 12 -> {
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
        System.out.println("4. Borrar tablas de dept. y empleados");
        System.out.println("5. Crear tabla de dept. y empleados");
        System.out.println("6. Dar de alta a un empleado");
        System.out.println("7. Dar de baja a un empleado");
        System.out.println("8. Dar de alta un departamento");
        System.out.println("9. Dar de baja un departamento");
        System.out.println("10. Modificar a un empleado");
        System.out.println("11. Modificar un departamento");
        System.out.println("12. Salir");
        System.out.print("\n================================\nElige una opcion: ");
        
        return kb.nextInt();
    }
    
    public static void limpiarPantalla(int lineas){
        for(int i=0; i<lineas; i++)
            System.out.println();
    }
}
