
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arube
 */
public class vista {
    public static void main(String[] args){
        System.out.println("ei");
        recuperaTipoEmpleado();
    }
    
    public static void recuperaTipoEmpleado(){
        SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
        Session session = sesion.openSession();

        Transaction tx = session.beginTransaction();
        
        Query q = session.createQuery("SELECT EMP_NO, APELLIDO, DEREF(DEPT).DNOMBRE AS DEPT_NOMBRE, DEREF(DEPT).LOC AS DEPT_LOC from TIPO_EMPLEADO");
        Iterator it = q.iterate();
        
        while(it.hasNext()){
            Object[] recibido = (Object[]) it.next();
            System.out.println("ID: "+recibido[0]+"\tApellido: "+recibido[1]+"\tDeptName: "+recibido[2]+"\tDeptLoc: "+recibido[3]);
        }
        
        tx.commit();
        session.close();
    }
}
