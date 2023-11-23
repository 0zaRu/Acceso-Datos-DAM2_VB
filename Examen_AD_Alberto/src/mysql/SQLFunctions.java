package mysql;
import modelo.Ciclo;
import modelo.Modulo;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author 6002754
 */
public class SQLFunctions {
    Connection conexion;
    
    public SQLFunctions()throws SQLException{
        
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/informaticaMySQL", "root", "123456");
    
    }
    
    public void creaTablas() throws  SQLException{
        Statement st = conexion.createStatement();
        
        st.executeUpdate("CREATE TABLE IF NOT EXISTS ciclo (" +
                         " cicloID int primary key auto_increment," +
                         " nombre_corto varchar(30) unique NOT NULL," +
                         " nombre_largo varchar(60) NOT NULL," +
                         " grado varchar(30) NOT NULL," +
                         " horas int NOT NULL)");
        
        st.executeUpdate("CREATE TABLE IF NOT EXISTS modulo (" +
                         " moduloID int primary key auto_increment," +
                         " nombre varchar(60) NOT NULL," +
                         " curso varchar(30) NOT NULL," +
                         " cicloID int not null," +
                         " CONSTRAINT `cicloID_ibfk_1` FOREIGN KEY (cicloID) REFERENCES Ciclo (cicloID) ON UPDATE CASCADE)");
    }
    
    
    public void rellenaTablas(ArrayList<Ciclo> ciclos) throws SQLException{
        Statement st = conexion.createStatement();
        
        for(int i=0; i < ciclos.size(); i++){
            st.executeUpdate("INSERT INTO ciclo " +
                            "(nombre_corto, nombre_largo, grado, horas)" +
                            " VALUES ('" +
                            ciclos.get(i).getNombre_corto()+"', '" +
                            ciclos.get(i).getNombre_largo()+"', '" +
                            ciclos.get(i).getGrado()+"', '" +
                            ciclos.get(i).getHoras()+"')");
            
            ResultSet rs = st.executeQuery("Select cicloID from Ciclo WHERE nombre_corto = '"+ciclos.get(i).getNombre_corto()+"'");
            int idAsociada = -1;
            
            while(rs.next()){
                idAsociada = rs.getInt(1);
            }
            
            for(Modulo modulo : ciclos.get(i).getModulos()){
                st.executeUpdate("INSERT INTO modulo " +
                                    "(nombre, curso, cicloID)" +
                                    " VALUES ('" +
                                    modulo.getNombre()+"', '" +
                                    modulo.getCurso()+"', '" +
                                    idAsociada+"')");
            }
        }
    }
    
    public void busquedaCicloCurso(String ciclo, String curso) throws SQLException{
        Statement st = conexion.createStatement();
        
        ResultSet rs = st.executeQuery("select modulo.nombre, modulo.curso from modulo join ciclo " +
                        "where modulo.curso = '"+curso+"' " +
                        "AND ciclo.nombre_corto = '"+ciclo+"' " +
                        "AND modulo.cicloID = ciclo.cicloID");
        
        System.out.println("\n\nMODULOS COINCIDENTES:\n=========================================================================\n\n");
        while(rs.next()){
                System.out.printf("%50s%30s\n", rs.getString(1), rs.getString(2));
            }
    }
}
