package ejercicio11_ac;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author arube
 */
public class DBConector {
    
    static Connection con;
    static Statement st;

    public DBConector() throws SQLException{
        con = DriverManager.getConnection("jdbc:mysql://localhost/ejemplo", "root", "123456");
        st = con.createStatement();
    }
    
    public void getConnectionData() throws SQLException{
        DatabaseMetaData meta = con.getMetaData();
        System.out.println("Nombre de la BBDD: "+meta.getDatabaseProductName());
        System.out.println("Driver en uso: "+meta.getDriverName());
        System.out.println("URL donde se aloja: "+meta.getURL());
        System.out.println("Usuario conectado: "+meta.getUserName()+"\n");
    }
    
    public void getTableInfo() throws  SQLException{
        ResultSet rs = st.executeQuery("SELECT "
                                     + "TABLE_CATALOG as Catálogo, "
                                     + "TABLE_SCHEMA as Esquema, "
                                     + "TABLE_NAME as Tabla, "
                                     + "TABLE_TYPE as Tipo "
                                     + "FROM information_schema.tables "
                                     + "WHERE TABLE_SCHEMA = 'ejemplo'");
        
        System.out.printf("%-15s%-15s%-15s%-15s%n", "Catalogo", "Equema", "Tabla", "Tipo");
        System.out.println("============================================================");
        while(rs.next())
            System.out.printf("%-15s%-15s%-15s%-15s%n", rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4));
    }
    
    public void getColumnsInfo() throws SQLException{
        ResultSet rs = st.executeQuery("SELECT "
                                     + "TABLE_NAME AS Tabla, "
                                     + "COLUMN_NAME AS Columna, "
                                     + "COLUMN_TYPE AS Tipo, "
                                     + "IS_NULLABLE AS 'Admt. Nulos', "
                                     + "CHARACTER_MAXIMUM_LENGTH AS Tamaño "
                                     + "FROM information_schema.columns "
                                     + "WHERE TABLE_SCHEMA = 'ejemplo'");
        
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "Tabla", "Columna", "Tipo", "Admt.Nulos", "Lenght");
        System.out.println("===========================================================================");
        while(rs.next())
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4), rs.getObject(5));
    }
    
    public void deleteTables() throws SQLException{
        int n = st.executeUpdate("DROP TABLE IF EXISTS departamentos, empleados;");
        if(n != 0)
            System.out.println("Se han eliminado: " + n + "tablas");
        else
            System.out.println("No se han encontrado las tablas en la base de datos");
    }
    
    public void createTables() throws SQLException{
        int n = st.executeUpdate("CREATE TABLE IF NOT EXISTS departamentos("
                               + "dept_no int NOT NULL,"
                               + "dnombre varchar(15) CHARACTER SET utf8 NOT NULL,"
                               + "loc varchar(15) CHARACTER SET utf8 NOT NULL )");
        
        n+= st.executeUpdate("CREATE TABLE IF NOT EXISTS empleados("
                + "emp_no int unsigned NOT NULL,"
                + "apellido varchar(10) CHARACTER SET utf8 NOT NULL,"
                + "oficio varchar(10) CHARACTER SET utf8 NOT NULL,"
                + "dir int NOT NULL,"
                + "fecha_alta date NOT NULL,"
                + "salario float(9,2) NOT NULL,"
                + "comision float(6,2) NOT NULL,"
                + "dept_no int NOT NULL)");
        
        System.out.println("\nSe han introducido " + n + " tablas en la base de datos.");
        
        n = 0;
        n += st.executeUpdate("INSERT INTO departamentos VALUES (10, 'CONTABILIDAD', 'SEVILLA')");
        n += st.executeUpdate("INSERT INTO departamentos VALUES (20, 'INVESTIGACION', 'MADRID')");
        n += st.executeUpdate("INSERT INTO departamentos VALUES (30, 'VENTAS', 'BARCELONA')");
        n += st.executeUpdate("INSERT INTO departamentos VALUES (40, 'PRODUCCION', 'BILBAO')");
        n += st.executeUpdate("INSERT INTO empleados VALUES (1, 'López', 'contable', 345, '1987-10-23', 23400.32, 5.34, 10)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (2, 'Alonso', 'contable', 346, '1994-12-23', 20400.32, 3.34, 10)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (3, 'Santana', 'contable', 345, '1980-09-23', 33670.20, 8.84, 10)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (4, 'Gil', 'investigad', 245, '1987-01-23', 23400.32, 5.34, 20)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (5, 'Lorenzo', 'investigad', 246, '1992-12-23', 20400.32, 3.34, 20)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (6, 'Manteca', 'investigad', 245, '2000-10-23', 33670.20, 8.84, 20)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (7, 'Tocino', 'vendedor', 445, '2003-05-23', 23400.32, 5.34, 30)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (8, 'Malo', 'vendedor', 446, '1994-06-23', 20400.32, 3.34, 30)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (9, 'Salamanca', 'vendedor', 445, '2004-08-23', 33670.20, 8.84, 30)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (10, 'Iglesias', 'productor', 145, '1987-01-23', 33400.32, 15.34, 40)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (11, 'Martín', 'productor', 146, '2004-12-23', 28400.32, 13.34, 40)");
        n += st.executeUpdate("INSERT INTO empleados VALUES (12, 'Soroya', 'productor', 145, '1980-10-23', 43670.20, 18.84, 40)");
        
        System.out.println("Se han introducido " + n + " datos en las tablas.");
    }
    
    public void insertNewEmployee(Scanner kb) throws  SQLException{
        //INSERT INTO empleados VALUES (1, 'López', 'contable', 345, '1987-10-23', 23400.32, 5.34, 10);
        System.out.print("Introduce el nº de empleado: ");
        int num = kb.nextInt();
        kb.nextLine();
        
        System.out.print("Introduce el apellido: ");
        String apellido = kb.nextLine();
        
        System.out.print("Introduce el oficio que ejerce: ");
        String trabajo = kb.nextLine();
        
        System.out.print("Introduce el dir(entero): ");
        int dir = kb.nextInt();
        
        System.out.print("Introduce el salario del trabajador: ");
        double salario = kb.nextDouble();
        
        System.out.print("Introducee su comision: ");
        float comision = kb.nextFloat();
        
        System.out.println("Introduce el numero de departamento: ");
        int nDept = kb.nextInt();
        kb.nextLine();
        
        ResultSet rs = st.executeQuery("select emp_no from empleados");
        while(rs.next())
            if (num == rs.getInt(1)){
                System.err.println("Empleado o numero de empleado ya introduce en la bbdd");
                return;
            }
        
        if(salario < 1080){
            System.err.println("Salario introducido menor al SMI ...");
            return;
        }
        
        rs = st.executeQuery("select dept_no from departamentos");
        boolean valido = false;
        while(rs.next())
            if (nDept == rs.getInt(1))
                valido = true;
        
        if(!valido){
            System.err.println("Numero de departamento no encontrado en la bbdd");
            return;    
        }
        
        String fecha_alta = LocalDate.now().toString();
        
        int operacion = st.executeUpdate("INSERT INTO empleados VALUES ('"+num+"', '"+apellido+"', '"+trabajo+"', '"+dir+"', '"+fecha_alta+"', '"+salario+"', '"+comision+"', '"+nDept+"')");
        
        if(operacion != 0)
            System.out.println("\nSe ha introducido el empleado");
        else
            System.out.println("\nNo se puedo introducir el empleado");
    }
    
    public void deleteEmployee(Scanner kb) throws SQLException{
        ej7muestraTablaEmpleado();
        
        System.out.print("\n\nIntroduce el numero de empleado que desea borrar: ");
        int num = kb.nextInt();
        kb.nextLine();
        
        int nModifi = st.executeUpdate("delete from empleados WHERE emp_no = "+num);
        if(nModifi !=0)
            System.out.println("\nSe ha eliminado al empleado correctamente");
        else
            System.err.println("\nHubo un error borrando al empleado, probablemente no se haya encontrado.");
    }
    
    public void insertNewDepartment(Scanner kb) throws  SQLException{
        //INSERT INTO empleados VALUES (1, 'López', 'contable', 345, '1987-10-23', 23400.32, 5.34, 10);
        System.out.print("Introduce el nº de departamento: ");
        int num = kb.nextInt();
        kb.nextLine();
        
        System.out.print("Introduce el nombre: ");
        String nombre = kb.nextLine();
        
        System.out.print("Introduce su localizacion: ");
        String loc = kb.nextLine();


        ResultSet rs = st.executeQuery("select dept_no from departamentos");
        while(rs.next())
            if (num == rs.getInt(1)){
                System.err.println("departamento o numero de departamento ya introduce en la bbdd");
                return;
            }
        
        int operacion = st.executeUpdate("INSERT INTO departamentos VALUES ('"+num+"', '"+nombre+"', '"+loc+"')");
        
        if(operacion != 0)
            System.out.println("\nSe ha introducido el departamento");
        else
            System.out.println("\nNo se puedo introducir el departamento");
    }
    
    public void deleteDepartment(Scanner kb) throws SQLException{
        ej7muestraTablaDepartamento();
        
        System.out.print("\n\nIntroduce el numero de departamento que desea borrar: ");
        int num = kb.nextInt();
        kb.nextLine();
        
        int nModifi = st.executeUpdate("delete from departamentos WHERE dept_no = "+num);
        if(nModifi !=0)
            System.out.println("\nSe ha eliminado el departamento correctamente");
        else
            System.err.println("\nHubo un error borrando el departamento, probablemente no se haya encontrado.");
    }
    
    public void modifyEmployee(Scanner kb) throws SQLException{
        ej7muestraTablaEmpleado();
        
        System.out.print("\n\nIntroduce el numero de empleado que deseas modificar: ");
        int num = kb.nextInt();
        kb.nextLine();
        
        System.out.print("Introduce el apellido: ");
        String apellido = kb.nextLine();
        
        System.out.print("Introduce el oficio que ejerce: ");
        String trabajo = kb.nextLine();
        
        System.out.print("Introduce el dir(entero): ");
        int dir = kb.nextInt();
        
        System.out.print("Introduce el salario del trabajador: ");
        double salario = kb.nextDouble();
        
        System.out.print("Introducee su comision: ");
        float comision = kb.nextFloat();
        
        System.out.println("Introduce el numero de departamento: ");
        int nDept = kb.nextInt();
        kb.nextLine();
        
        if(salario < 1080){
            System.err.println("Salario introducido menor al SMI ...");
            return;
        }
        
        ResultSet rs = st.executeQuery("select dept_no from departamentos");
        boolean valido = false;
        while(rs.next())
            if (nDept == rs.getInt(1))
                valido = true;
        
        if(!valido){
            System.err.println("Numero de departamento no encontrado en la bbdd");
            return;    
        }
        
        
        int operacion = st.executeUpdate("UPDATE empleados SET "
                + "apellido = '"+apellido+"', "
                + "oficio = '"+trabajo+"', "
                + "dir = '"+dir+"', "
                + "salario = '"+salario+"', "
                + "comision = '"+comision+"', "
                + "dept_no = '"+nDept+"' "
                + "WHERE emp_no = "+num);
        
        if(operacion != 0)
            System.out.println("\nSe ha modificado el empleado");
        else
            System.out.println("\nNo se pudo modificar el empleado");
    }
    
    public void modifyDepartment(Scanner kb) throws SQLException{
        ej7muestraTablaDepartamento();
        
        System.out.print("\n\nIntroduce el numero de departamento que deseas modificar: ");
        int num = kb.nextInt();
        kb.nextLine();
        
        System.out.print("Introduce el nuevo nombre: ");
        String nombre = kb.nextLine();
        
        System.out.print("Introduce la localizacion: ");
        String loc = kb.nextLine();
        
        int operacion = st.executeUpdate("UPDATE departamentos SET "
                + "dnombre = '"+nombre+"', "
                + "loc = '"+loc+"' "
                + "WHERE dept_no = "+num);
        
        if(operacion != 0)
            System.out.println("\nSe ha modificado el departamento");
        else
            System.out.println("\nNo se pudo modificar el departamento");
    }

    
    public void ej7muestraTablaEmpleado() throws SQLException{
        ResultSet rs = st.executeQuery("select * from empleados");
        while(rs.next()){
            System.out.print(rs.getObject(1)+" "+rs.getObject(2)+"\n");
        }
    }

    public void ej7muestraTablaDepartamento() throws SQLException{
        
        ResultSet rs = st.executeQuery("select * from departamentos");
        while(rs.next()){
            System.out.print(rs.getObject(1)+" "+rs.getObject(2)+" "+rs.getObject(3)+"\n");
        }
    }

    public void ej7muestraDeptVentas() throws  SQLException{
        ResultSet rs = st.executeQuery("select apellido from empleados WHERE dept_no LIKE 30");
        while(rs.next()){
            System.out.println(rs.getObject(1));
        }
    }

    public void ej7muestraEntre1980y1990() throws SQLException{
        ResultSet rs = st.executeQuery("select apellido from empleados WHERE fecha_alta > '1980-01-01' AND fecha_alta < '1990-12-31'");
        while(rs.next()){
            System.out.println(rs.getObject(1));
        }
    }

    public void ej7muestraSumaPorDept() throws SQLException{
        ResultSet rs = st.executeQuery("select dnombre, sum(salario) from empleados, departamentos where empleados.dept_no = departamentos.dept_no GROUP BY dnombre");
        while(rs.next()){
            System.out.println(rs.getObject(1)+" - "+rs.getObject(2));
        }
    }
    
    
}
