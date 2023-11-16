package ejercicio8_ac;

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

    public DBConector(String url, String user, String password) throws SQLException{
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
    }
    
    public DBConector(String url) throws  SQLException{
        con = DriverManager.getConnection(url);
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
}
