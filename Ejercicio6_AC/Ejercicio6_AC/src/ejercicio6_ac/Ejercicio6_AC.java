package ejercicio6_ac;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.io.File;
import java.io.IOException;
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
        kb.useDelimiter(System.getProperty("line.separator"));
        
        if (!rutaBD.exists())
            try {
            rutaBD.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio6_AC.class.getName()).log(Level.SEVERE, null, ex);
        }

        bd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), rutaBD.toString());

        //bd.store(new Empleado(10, "Rodríguez", "Informático", "C/Plasencia", "08/01/2024", 1500, 100, 10));
        //bd.store(new Empleado(11, "Sánchez", "Instalador", "C/Nicaragua", "01/11/2023", 1800, 150, 20));

        //bd.store(new Departamento(10, "Informática", "Salamanca"));
        //bd.store(new Departamento(20, "Instalaciones", "Zamora"));

        System.out.println("Tabla empleados:");
        muestraTablaEmpleado();

        System.out.println("Tabla empleados:");
        muestraTablaDepartamento();
        
        
        //QUEDA HACER LOS SELECT
        
        System.out.println("Vamos a introducir un nuevo empleado: ");
        introducirEmpleado(new Empleado());
        
        System.out.println("Que registro de empleado quieres editar: ");
        editaEmpleado();
        muestraTablaEmpleado();
        
        bd.close();
    }

    public static void muestraTablaEmpleado() {

        Empleado emp = new Empleado();
        ObjectSet<Empleado> result = bd.queryByExample(emp);

        if (result.size() == 0) {
            System.out.println("No existen Registros de Empleados..");

        } else {
            System.out.printf("Número de registros: %d %n",
                    result.size());
            while (result.hasNext()) {
                Empleado p = result.next();
                System.out.println(p);
            }
        }
    }
    
    public static void muestraTablaDepartamento() {

        Departamento dep = new Departamento();
        ObjectSet<Departamento> result = bd.queryByExample(dep);

        if (result.size() == 0) {
            System.out.println("No existen Registros de Departamentos..");

        } else {
            System.out.printf("Número de registros: %d %n",
                    result.size());
            while (result.hasNext()) {
                Departamento d = result.next();
                System.out.println(d);
            }
        }
    }
    
    public static void introducirEmpleado(Empleado emp){
        
        System.out.print("\nIntroduce el numero de empleado: ");
        emp.setnEmpleado(kb.nextInt());
        
        System.out.print("\nIntroduce el apellido de empleado: ");
        emp.setApellido(kb.next());
        
        System.out.print("\nIntroduce el oficio de empleado: ");
        emp.setOficio(kb.next());
        
        System.out.print("\nIntroduce la direccion de empleado: ");
        emp.setDir(kb.next());
        
        System.out.print("\nIntroduce la fecha de alta de empleado: ");
        emp.setFecha_alta(kb.next());
        
        System.out.print("\nIntroduce el salario de empleado: ");
        emp.setSalario(kb.nextDouble());
        
        System.out.print("\nIntroduce la comision de empleado: ");
        emp.setComision(kb.nextFloat());
        
        System.out.print("\nIntroduce el numero de departamento de empleado: ");
        emp.setnDepartamento(kb.nextInt());
        
        bd.store(emp);
    }
    
    public static void editaEmpleado(){
        
        System.out.println("Introduce el numero de registro (considerando 0 el primero): ");
        int reg = kb.nextInt();
        
        Empleado emp = new Empleado();
        ObjectSet<Empleado> result = bd.queryByExample(emp);
        
        emp = result.get(reg);
        
        introducirEmpleado(emp);
        bd.store(emp);
        
    }
}
