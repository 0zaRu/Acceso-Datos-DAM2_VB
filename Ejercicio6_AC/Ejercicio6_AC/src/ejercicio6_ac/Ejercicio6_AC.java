package ejercicio6_ac;

import com.db4o.*;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 6002754
 */
public class Ejercicio6_AC {

    final static File rutaBD = new File("baseDates6.db");
    static ObjectContainer bd;
    
    static Scanner kb = new Scanner(System.in);

    public static void main(String[] args) {

        if (!rutaBD.exists())
            try {
            rutaBD.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio6_AC.class.getName()).log(Level.SEVERE, null, ex);
        }

        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaBD.toString());
        
        borraRegistrosPrevios();
        instanciaDatosDB();

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
                    muestraEntre1980y1990();
                    muestraSumaPorDept();
                }
                   
                case 4 -> {
                    System.out.println("Vamos a introducir un nuevo empleado: ");
                    Empleado emp = new Empleado();
                    introducirEmpleado(emp);
                    System.out.println("Empleado introducido");
                }
                   
                case 5 -> {
                    System.out.println("Que registro de empleado quieres editar: ");
                    editaEmpleado();
                    System.out.println("Empleado modificado");
                }
                   
                case 6 -> {
                    System.out.println("Se va a salir del programa");
                    salir = true;
                }
                   
                default -> System.out.println("Valor introducido incorrecto");
           }
            System.out.println("\nPulse enter para continuar ...");
            kb.nextLine();
            
            limpiarPantalla(25);
          
        }while(!salir);
  
        bd.close();
        kb.close();
    }

    public static int menu(){
        System.out.println("Elige una opcion: ");
        System.out.println("================================");
        System.out.println("");
        System.out.println("1. Mostrar la tabla empleado");
        System.out.println("2. Mostrar la tabla departamento");
        System.out.println("3. Ver selects filtrados");
        System.out.println("4. Introducir un empleado");
        System.out.println("5. Modificar un empleado");
        System.out.println("6. Salir");
        System.out.print("\n================================\nElige una opcion: ");
        
        return kb.nextInt();
    }

    public static void instanciaDatosDB() {
        bd.store(new Departamento(10, "CONTABILIDAD", "SEVILLA"));
        bd.store(new Departamento(20, "INVESTIGACION", "MADRID"));
        bd.store(new Departamento(30, "VENTAS", "BARCELONA"));
        bd.store(new Departamento(40, "PRODUCCION", "BILBAO"));

        bd.store(new Empleado(1, "López", "contable", 345, LocalDate.of(1987, 10, 23), 23400.32, 5.34f, 10));
        bd.store(new Empleado(2, "Alonso", "contable", 346, LocalDate.of(1994, 12, 23), 20400.32, 3.34f, 10));
        bd.store(new Empleado(3, "Santana", "contable", 345, LocalDate.of(1980, 9, 23), 33670.20, 8.84f, 10));
        bd.store(new Empleado(4, "Gil", "investigad", 245, LocalDate.of(1987, 1, 23), 23400.32, 5.34f, 20));
        bd.store(new Empleado(5, "Lorenzo", "investigad", 246, LocalDate.of(1992, 12, 23), 20400.32, 3.34f, 20));
        bd.store(new Empleado(6, "Manteca", "investigad", 245, LocalDate.of(2000, 10, 23), 33670.20, 8.84f, 20));
        bd.store(new Empleado(7, "Tocino", "vendedor", 445, LocalDate.of(2003, 5, 23), 23400.32, 5.34f, 30));
        bd.store(new Empleado(8, "Malo", "vendedor", 446, LocalDate.of(1994, 6, 23), 20400.32, 3.34f, 30));
        bd.store(new Empleado(9, "Salamanca", "vendedor", 445, LocalDate.of(2004, 8, 23), 33670.20, 8.84f, 30));
        bd.store(new Empleado(10, "Iglesias", "productor", 145, LocalDate.of(1987, 1, 23), 33400.32, 15.34f, 40));
        bd.store(new Empleado(11, "Martín", "productor", 146, LocalDate.of(2004, 12, 23), 28400.32, 13.34f, 40));
        bd.store(new Empleado(12, "Soroya", "productor", 145, LocalDate.of(1980, 10, 23), 43670.20, 18.84f, 40));
    }
    
    public static void muestraTablaEmpleado() {

        Empleado emp = new Empleado();
        ObjectSet<Empleado> result = bd.queryByExample(emp);

        if (result.size() == 0) {
            System.out.println("No existen Registros de Empleados..");

        } else {
            System.out.println("Tabla empleados:");
                    
            System.out.printf("Número de registros: %d %n",
                    result.size());
            
            System.out.println("-----------------------------------------------------------------------------------------");
            while (result.hasNext()) {
                Empleado p = result.next();
                System.out.println(p);
            }
            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }

    public static void muestraTablaDepartamento() {

        Departamento dep = new Departamento();
        ObjectSet<Departamento> result = bd.queryByExample(dep);

        if (result.size() == 0) {
            System.out.println("No existen Registros de Departamentos..");

        } else {
            System.out.println("Tabla departamentos:");
            System.out.printf("Número de registros: %d %n",
                    result.size());
            
            System.out.println("------------------------------------------");
            while (result.hasNext()) {
                Departamento d = result.next();
                System.out.println(d);
            }
            System.out.println("------------------------------------------");
        }
    }

    public static void introducirEmpleado(Empleado emp) {
        
        System.out.print("\nIntroduce el numero de empleado: ");
        int nEmp = kb.nextInt();
        kb.nextLine();
        emp.setnEmpleado(nEmp);

        System.out.print("Introduce el apellido de empleado: ");
        String apellido = kb.nextLine();
        emp.setApellido(apellido);

        System.out.print("Introduce el oficio de empleado: ");
        String oficio = kb.nextLine();
        emp.setOficio(oficio);

        System.out.print("Introduce la direccion de empleado: ");
        int dir = kb.nextInt();
        kb.nextLine();
        emp.setDir(dir);

        System.out.print("Introduce la fecha de alta de empleado (AAAA-MM-DD): ");
        String fecha_alta = kb.nextLine();
        String[] fechas = fecha_alta.split("-");
        
        emp.setFecha_alta(LocalDate.of(Integer.parseInt(fechas[0]), Integer.parseInt(fechas[1]), Integer.parseInt(fechas[2])));

        System.out.print("Introduce el salario de empleado: ");
        double salario = kb.nextDouble();
        kb.nextLine();
        emp.setSalario(salario);

        System.out.print("Introduce la comision de empleado: ");
        float comision = kb.nextFloat();
        kb.nextLine();
        emp.setComision(comision);

        System.out.print("Introduce el numero de departamento de empleado: ");
        int dept = kb.nextInt();
        kb.nextLine();
        emp.setnDepartamento(dept);
        
        bd.store(emp);
    }

    public static void editaEmpleado() {

        System.out.print("Introduce el numero de registro:");
        int reg = kb.nextInt();

        Empleado emp = new Empleado();
        ObjectSet<Empleado> result = bd.queryByExample(emp);

        emp = result.get(reg-1);

        introducirEmpleado(emp);
        bd.store(emp);

    }

    private static void borraRegistrosPrevios() {
        Empleado emp = new Empleado();
        ObjectSet<Empleado> result = bd.queryByExample(emp);

        if (result.isEmpty()) {

        } else {
            while (result.hasNext())
                    bd.delete((Empleado)result.next());
            
        }
        
        Departamento dep = new Departamento();
        ObjectSet<Departamento> result2 = bd.queryByExample(dep);

        if (result2.isEmpty()) {

        } else {
            while (result2.hasNext()) {
                    Departamento d = result2.next();
                    bd.delete(d);
            }
        }
    }

    public static void limpiarPantalla(int lineas){
        for(int i=0; i<lineas; i++)
            System.out.println();
    }

    private static void muestraDeptVentas() {
        ObjectSet<Empleado> result = bd.queryByExample(new Empleado(0, null, null, 0, null, 0, 0, 30));
                    
        System.out.println("\n\nAPELLIDOS DE EMPLEADOS DE VENTAS: \n");
        while (result.hasNext()) {
            System.out.println("Empleado: "+((Empleado)result.next()).getApellido());
        }
    }

    private static void muestraEntre1980y1990() {
        ObjectSet<Empleado> resultado = null;
        
            resultado = bd.query (new Predicate<Empleado> () {
                @Override
                public boolean match (Empleado e) {
                    try{
                    
                        return e.getFecha_alta().isAfter(java.time.LocalDate.of (1980, 1, 1)) &&
                               e.getFecha_alta().isBefore(java.time.LocalDate.of (1990, 12, 31));
                    
                    }catch(Exception ex){

                    }
                    return true;
                }
            });
        
        System.out.println("\n\nAPELLIDOS DE EMPLEADOS DADOS DE ALTA ENTRE 1980 Y 1990: \n");
        while (resultado.hasNext())
            System.out.println("Empleado: "+((Empleado)resultado.next()).getApellido());
    }

    private static void muestraSumaPorDept() {
        System.out.println("\n\nSUMA DE SALARIOS DE LOS DEPARTAMENTOS: \n");
        
        float sueldo;
        for(int i=10; i < 100; i+=10){
            sueldo = 0;
            
            Empleado emp = new Empleado();
            ObjectSet<Empleado> result = bd.queryByExample(new Empleado(0, null, null, 0, null, 0, 0, i));
            while (result.hasNext()) {
                Empleado p = result.next();
                sueldo += p.getSalario();
            }
            if(sueldo != 0)
                System.out.println("El departamento numero " + i + " tiene la suma de sueldos de: "+ sueldo);
        }
    }
}
