package vista;

import controlador.DepartamentosMethods;
import controlador.EmpleadosMethods;
import hibernate.Departamentos;
import hibernate.Empleados;
import java.util.Scanner;

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
                        System.out.println("Departamento modificado correctamente");
                    else
                        System.out.println("Hubo un error haciendo la modificación del departamento");
                    
                    break;
                    
                case 4:
                    // Consulta Departamento
                    int nDeparta = DepartamentosMethods.insertaNumDept();
                    
                    if(nDeparta != -1 && DepartamentosMethods.consultaDepartamento(nDeparta))
                        System.out.println("Departamento mostrado correctamente");
                    else
                        System.out.println("Hubo un error mostrando el departamento");
                    
                    break;
                    
                case 11:
                    // Alta Empleado
                    
                    Empleados nuevoEmp = EmpleadosMethods.insertaEmpleado(null);
                    
                    if(nuevoEmp != null && EmpleadosMethods.altaEmpleado(nuevoEmp))
                        System.out.println("Empleado introducido correctamente");
                    else
                        System.out.println("Hubo un error haciendo la insercción del Empleado");
                    
                    break;
                    
                case 12:
                    // Baja Empleado
                    
                    int nEmp = EmpleadosMethods.insertaNumEmp();
                    
                    if(nEmp != -1 && EmpleadosMethods.bajaEmpleado(nEmp))
                        System.out.println("Empleado dado de baja correctamente");
                    else
                        System.out.println("Hubo un error haciendo la baja del empleado");
                    
                    break;
                    
                case 13:
                    // Modificación Empleado
                    int nEmplea = EmpleadosMethods.insertaNumEmp();
                    
                    if(nEmplea != -1 && EmpleadosMethods.modificaEmpleado(nEmplea))
                        System.out.println("Empleado modificado correctamente");
                    else
                        System.out.println("Hubo un error haciendo la modificación del empleado");
                    
                    break;
                    
                case 14:
                    // Consulta Empleado
                    int nEmpleado = EmpleadosMethods.insertaNumEmp();
                    
                    if(nEmpleado != -1 && EmpleadosMethods.consultaEmpleado(nEmpleado))
                        System.out.println("Empleado mostrado correctamente");
                    else
                        System.out.println("Hubo un error mostrando el empleado");
                    
                    break;
                    
                case 21:
                    // Empleados de un Departamento
                    int nDpt = DepartamentosMethods.insertaNumDept();
                    
                    if(nDpt != -1 && EmpleadosMethods.empleadosDeDepartamento(nDpt))
                        System.out.println("Empleados mostrados correctamente");
                    else
                        System.out.println("Hubo un error mostrando los empleados");
                    
                    break;
                    
                case 22:
                    // Listar Departamentos
                    if(DepartamentosMethods.departamentos())
                        System.out.println("Departamentos mostrados correctamente");
                    else
                        System.out.println("Hubo un error mostrando los departamentos");
                    
                    break;
                    
                case 23:
                    // Listar Empleados
                    if(EmpleadosMethods.empleados())
                        System.out.println("Empleados mostrados correctamente");
                    else
                        System.out.println("Hubo un error mostrando los empleados");
                    
                    break;
                    
                case 31:
                    // Consultar Salarios Departamento
                    int nDpto = DepartamentosMethods.insertaNumDept();
                    
                    if(nDpto != -1 && DepartamentosMethods.salarioDepartamento(nDpto))
                        System.out.println("Salario de departamento mostrado correctamente");
                    else
                        System.out.println("Hubo un error mostrando el salarios del departamento");
                    
                    break;
                    
                case 32:
                    // Consultar Salarios Empresa
                    if(EmpleadosMethods.salarioEmpresa())
                        System.out.println("Salarios de la empresa mostrados correctamente");
                    else
                        System.out.println("Hubo un error mostrando los salarios de la empresa");
                    
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
