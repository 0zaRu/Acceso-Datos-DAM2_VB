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

        instanciaDatosDB();

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

    public static void instanciaDatosDB() {
        bd.store(new Departamento(10, "CONTABILIDAD", "SEVILLA"));
        bd.store(new Departamento(20, "INVESTIGACION", "MADRID"));
        bd.store(new Departamento(30, "VENTAS", "BARCELONA"));
        bd.store(new Departamento(40, "PRODUCCION", "BILBAO"));

        bd.store(new Empleado(1, "López", "contable", 345, "1987-10-23", 23400.32, 5.34f, 10));
        bd.store(new Empleado(2, "Alonso", "contable", 346, "1994-12-23", 20400.32, 3.34f, 10));
        bd.store(new Empleado(3, "Santana", "contable", 345, "1980-09-23", 33670.20, 8.84f, 10));
        bd.store(new Empleado(4, "Gil", "investigad", 245, "1987-01-23", 23400.32, 5.34f, 20));
        bd.store(new Empleado(5, "Lorenzo", "investigad", 246, "1992-12-23", 20400.32, 3.34f, 20));
        bd.store(new Empleado(6, "Manteca", "investigad", 245, "2000-10-23", 33670.20, 8.84f, 20));
        bd.store(new Empleado(7, "Tocino", "vendedor", 445, "2003-05-23", 23400.32, 5.34f, 30));
        bd.store(new Empleado(8, "Malo", "vendedor", 446, "1994-06-23", 20400.32, 3.34f, 30));
        bd.store(new Empleado(9, "Salamanca", "vendedor", 445, "2004-08-23", 33670.20, 8.84f, 30));
        bd.store(new Empleado(10, "Iglesias", "productor", 145, "1987-01-23", 33400.32, 15.34f, 40));
        bd.store(new Empleado(11, "Martín", "productor", 146, "2004-12-23", 28400.32, 13.34f, 40));
        bd.store(new Empleado(12, "Soroya", "productor", 145, "1980-10-23", 43670.20, 18.84f, 40));
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

    public static void introducirEmpleado(Empleado emp) {

        System.out.print("\nIntroduce el numero de empleado: ");
        int nEmp = kb.nextInt();
        emp.setnEmpleado(nEmp);

        System.out.print("\nIntroduce el apellido de empleado: ");
        String apellido = kb.next();
        emp.setApellido(apellido);

        System.out.print("\nIntroduce el oficio de empleado: ");
        String oficio = kb.next();
        emp.setOficio(oficio);

        System.out.print("\nIntroduce la direccion de empleado: ");
        int dir = kb.nextInt();
        emp.setDir(dir);

        System.out.print("\nIntroduce la fecha de alta de empleado: ");
        String fecha_alta = kb.next();
        emp.setFecha_alta(fecha_alta);

        System.out.print("\nIntroduce el salario de empleado: ");
        double salario = kb.nextDouble();
        emp.setSalario(salario);

        System.out.print("\nIntroduce la comision de empleado: ");
        float comision = kb.nextFloat();
        emp.setComision(comision);

        System.out.print("\nIntroduce el numero de departamento de empleado: ");
        int dept = kb.nextInt();
        emp.setnDepartamento(dept);

        bd.store(emp);
    }

    public static void editaEmpleado() {

        System.out.println("Introduce el numero de registro (considerando 0 el primero): ");
        int reg = kb.nextInt();

        Empleado emp = new Empleado();
        ObjectSet<Empleado> result = bd.queryByExample(emp);

        emp = result.get(reg);

        introducirEmpleado(emp);
        bd.store(emp);

    }
}
