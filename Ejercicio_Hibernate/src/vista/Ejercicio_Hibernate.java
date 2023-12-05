package vista;

import controlador.DepartamentosMethods;
import java.util.Scanner;
import modelo.Departamentos;

/**
 *
 * @author 6002754
 */
public class Ejercicio_Hibernate {

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); 
            
            limpiarPantalla();

            switch (opcion) {
                case 1:
                    // Alta Departamento
                    
                    Departamentos nuevoDept = DepartamentosMethods.insertaDepartamento(null);
                    
                    if( nuevoDept != null && DepartamentosMethods.altaDepartamento(nuevoDept))
                        System.out.println("Departamento introducido correctamente");
                    else
                        System.out.println("Hubo un error haciendo la insercción del departamento");
                    
                    break;
                    
                case 2:
                    // Baja Departamento
                    
                    int nDept = DepartamentosMethods.insertaNumDept();
                    
                    if(nDept != -1 && DepartamentosMethods.bajaDepartamento(nDept))
                        System.out.println("Departamento dado de baja correctamente");
                    else
                        System.out.println("Hubo un error haciendo la baja del departamento");
                    
                    break;
                    
                case 3:
                    // Modificación Departamento
                    
                    int nDepart = DepartamentosMethods.insertaNumDept();
                    
                    if(nDepart != -1 && DepartamentosMethods.modificaDepartamento(nDepart))
                        System.out.println("Departamento dado de baja correctamente");
                    else
                        System.out.println("Hubo un error haciendo la baja del departamento");
                    
                    break;
                    
                case 4:
                    // Consulta Departamento
                    int nDeparta = DepartamentosMethods.insertaNumDept();
                    
                    if(nDeparta != -1 && DepartamentosMethods.consultaDepartamento(nDeparta))
                        System.out.println("Departamento dado de baja correctamente");
                    else
                        System.out.println("Hubo un error haciendo la baja del departamento");
                    
                    break;
                    
                case 11:
                    // Alta Empleado
                    //altaEmpleado();
                    break;
                case 12:
                    // Baja Empleado
                    //bajaEmpleado();
                    break;
                case 13:
                    // Modificación Empleado
                    //modificarEmpleado();
                    break;
                case 14:
                    // Consulta Empleado
                    //consultarEmpleado();
                    break;
                case 21:
                    // Empleados de un Departamento
                    //empleadosDeDepartamento();
                    break;
                case 22:
                    // Listar Departamentos
                    //listarDepartamentos();
                    break;
                case 23:
                    // Listar Empleados
                    //listarEmpleados();
                    break;
                case 31:
                    // Consultar Salarios Departamento
                    //consultarSalariosDepartamento();
                    break;
                case 32:
                    // Consultar Salarios Empresa
                    //consultarSalariosEmpresa();
                    break;
                case 41:
                    // Cargar Departamentos
                    //cargarDepartamentos();
                    break;
                case 42:
                    // Cargar Empleados
                    //cargarEmpleados();
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }

            System.out.println("Pulsa enter para continuar ...");
            
            scanner.nextLine();
            limpiarPantalla();
        }
    }

    // Aquí irían las implementaciones de los métodos correspondientes a cada opción del menú.

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Alta Departamento");
        System.out.println("2. Baja Departamento");
        System.out.println("3. Modificación Departamento");
        System.out.println("4. Consulta Departamento");
        System.out.println("11. Alta Empleado");
        System.out.println("12. Baja Empleado");
        System.out.println("13. Modificación Empleado");
        System.out.println("14. Consulta Empleado");
        System.out.println("21. Empleados de un Departamento");
        System.out.println("22. Listar Departamentos");
        System.out.println("23. Listar Empleados");
        System.out.println("31. Consultar Salarios Departamento");
        System.out.println("32. Consultar Salarios Empresa");
        System.out.println("41. Cargar Departamentos");
        System.out.println("42. Cargar Empleados");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.print("Seleccione una opción: ");
    }

    private static void limpiarPantalla() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}
