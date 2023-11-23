package practica.completa;

/**
 *
 * @author arube
 */
import java.sql.*;

public class BBDD {

    public static void creataTabla() throws Exception{
        
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/cosmere", "root", "123456");
        
        Statement sentencia = conexion.createStatement();
        
        int i = sentencia.executeUpdate("CREATE TABLE if not exists cosmere ("
                                        +"keyC int primary key auto_increment, "
                                        +"creador varchar(30) NOT NULL, "
                                        +"proximaSalida date NOT NULL, "
                                        +"hojasTotales int not null, "
                                        +"SLA_key int not null)");
        
        i += sentencia.executeUpdate("CREATE TABLE if not exists SLA (" +
                                    "SLA_key int primary key auto_increment, " +
                                    "ideal varchar(50) NOT NULL, " +
                                    "proximaSalida date NOT NULL)");
        
        i+= sentencia.executeUpdate("Alter table cosmere add FOREIGN KEY (SLA_key) REFERENCES SLA (SLA_key) ON UPDATE CASCADE");
        
        System.out.println("Se han ejecutado "+i+" updates con éxito.");
    }
    
    public static void insertaTabla(Cosmere c) throws Exception{
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/cosmere", "root", "123456");
        
        Statement sentencia = conexion.createStatement();
        
        String fecha = ""+(c.getSagaAsociada().getProximaSalida().getYear()+1);
        fecha += "-"+(c.getSagaAsociada().getProximaSalida().getMonth()+1);
        fecha += "-"+(c.getSagaAsociada().getProximaSalida().getDay()+1);
        
        int i = sentencia.executeUpdate("INSERT INTO sla" +
                                        "(nLibros, ideal, proximaSalida)" +
                                        "VALUES ('"+c.getSagaAsociada().getnLibros()+
                                        "', '"+c.getSagaAsociada().getIdeal()+
                                        "', '"+fecha+"')");
        String sql = "Select SLA_key FROM sla where ideal = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, c.getSagaAsociada().getIdeal());
        
        int keyRecibida = -1;
        
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            keyRecibida = rs.getInt(1);
        }
        
        fecha = ""+(c.getProximaSalida().getYear()+1);
        fecha += "-"+(c.getProximaSalida().getMonth()+1);
        fecha += "-"+(c.getProximaSalida().getDay()+1);
        
        i += sentencia.executeUpdate("INSERT INTO cosmere" +
                                     "(creador, proximaSalida, hojasTotales, SLA_key)" +
                                     "VALUES ('"+c.getCreador()+
                                     "', '"+fecha+
                                     "', '"+c.getHojasTotales()+
                                     "', '"+keyRecibida+"')");
    }
}
