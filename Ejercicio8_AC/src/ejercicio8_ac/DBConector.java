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
        DatabaseMetaData meta = con.getMetaData();
        
        System.out.println("catalogo de la tabla: "+meta.getCatalogs());
        System.out.println("Esquema de la base: "+meta.getSchemas());
        System.out.println("Tipo de tabla: "+meta.getTableTypes());
    }
    
    public void getColumnsInfo() throws SQLException{
        DatabaseMetaData meta = con.getMetaData();
        
        ResultSet rs = meta.getColumns(null, "prueba", "ejemplo", null);

        System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "Tabla", "Columna", "Tipo", "Admt.Nulos", "Lenght");
        System.out.println("===========================================================================");

        while (rs.next()) {
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%n",
                    rs.getString("TABLE_NAME"),
                    rs.getString("COLUMN_NAME"),
                    rs.getString("TYPE_NAME"),
                    rs.getString("IS_NULLABLE"),
                    rs.getString("COLUMN_SIZE"));
        }
    }
    
    public void deleteTables() throws SQLException{
        int n = st.executeUpdate("DROP TABLE IF EXISTS departamentos, empleados;");
        if(n != 0)
            System.out.println("Se han eliminado: " + n + "tablas");
        else
            System.out.println("No se han encontrado las tablas en la base de datos");
    }
}
