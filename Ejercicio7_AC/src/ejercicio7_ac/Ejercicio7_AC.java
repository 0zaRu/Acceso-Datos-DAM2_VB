package ejercicio7_ac;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
/**
 *
 * @author 6002754
 */
public class Ejercicio7_AC {

    static Scanner kb = new Scanner(System.in);
    static Statement st = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "123456");
            st = con.createStatement();
        boolean salir = false;
        int opcion;
        
        do{
           opcion = menu();
           kb.nextLine();
           limpiarPantalla(25);
           
           switch(opcion){
                case 1 -> {
                    muestraTablaEmpleado();
                }
                   
                case 2 -> {
                    muestraTablaDepartamento();
                }
                   
                case 3 -> {
                    muestraDeptVentas();
                    System.out.println("=======================");
                    muestraEntre1980y1990();
                    System.out.println("=======================");
                    muestraSumaPorDept();
                }
                   
                case 4 -> {
                    System.out.println("Se va a salir del programa");
                    salir = true;
                }
                   
                default -> System.out.println("Valor introducido incorrecto");
           }
            System.out.println("\nPulse enter para continuar ...");
            kb.nextLine();
            
            limpiarPantalla(25);
          
        }while(!salir);
  
        }catch(Exception e){
            System.err.println("Hubo un error");
        }
        
        kb.close();
    }

    public static int menu(){
        System.out.println("Elige una opcion: ");
        System.out.println("================================");
        System.out.println("");
        System.out.println("1. Mostrar la tabla empleado");
        System.out.println("2. Mostrar la tabla departamento");
        System.out.println("3. Ver selects filtrados");
        System.out.println("4. Salir");
        System.out.print("\n================================\nElige una opcion: ");
        
        return kb.nextInt();
    }
    
    public static void muestraTablaEmpleado() throws Exception{

        ResultSet rs = st.executeQuery("select * from departamentos");
        while(rs.next()){
            System.out.print(rs.getObject(1)+" "+rs.getObject(2)+"\n");
        }
    }

    public static void muestraTablaDepartamento() throws Exception{
        
        ResultSet rs = st.executeQuery("select * from empleados");
        while(rs.next()){
            System.out.print(rs.getObject(1)+" "+rs.getObject(2)+" "+rs.getObject(3)+" "+rs.getObject(4)+" "+rs.getObject(5)+" "+rs.getObject(6)+" "+rs.getObject(7)+" "+rs.getObject(8)+"\n");
        }
    }

    public static void limpiarPantalla(int lineas){
        for(int i=0; i<lineas; i++)
            System.out.println();
    }

    private static void muestraDeptVentas() throws  Exception{
        ResultSet rs = st.executeQuery("select apellido from empleados WHERE dept_no LIKE 30");
        while(rs.next()){
            System.out.println(rs.getObject(1));
        }
    }

    private static void muestraEntre1980y1990() throws Exception{
        ResultSet rs = st.executeQuery("select apellido from empleados WHERE fecha_alta > '1980-01-01' AND fecha_alta < '1990-12-31'");
        while(rs.next()){
            System.out.println(rs.getObject(1));
        }
    }

    private static void muestraSumaPorDept() throws Exception{
        ResultSet rs = st.executeQuery("select dnombre, sum(salario) from empleados, departamentos where empleados.dept_no = departamentos.dept_no GROUP BY dnombre");
        while(rs.next()){
            System.out.println(rs.getObject(1)+" - "+rs.getObject(2));
        }
    }
}
