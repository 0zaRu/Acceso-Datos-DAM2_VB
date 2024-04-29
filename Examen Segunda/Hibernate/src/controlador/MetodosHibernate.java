package controlador;

import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import modelo.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Alumno
 */
public class MetodosHibernate {
    public static void mostrarPedido(int id){
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Pedidos pedido = (Pedidos) session.load(Pedidos.class, id);
            System.out.println(pedido);
            
            Query q = session.createQuery("From Detallepedidos WHERE CodigoPedido = ?");
            q.setInteger(0, id);
            Iterator it = q.iterate();
            
            while(it.hasNext()){
                System.out.println(((Detallepedidos)it.next()).toString());
            }
            
            session.close();

            
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Pedido no registrado");
        }
    }
    
    public static void altaPedido(Scanner kb){
        
        SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
        Session session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        
        System.out.print("Introduce una id para asignar al pedido que no esté registrada: ");
        int id = kb.nextInt();
        kb.nextLine();
        
        Pedidos pedidoPrueba = (Pedidos) session.get(Pedidos.class, id);
        if(pedidoPrueba != null){
            System.out.println("El codigo de pedido ya está en uso.");
            return;
        }
        
        System.out.print("Introduce el dia del pedido con formato DD-MM-AAAA: ");
        String[] fechaPartida = kb.nextLine().split("-");
        Date fechaP = new Date(Integer.parseInt(fechaPartida[0]), Integer.parseInt(fechaPartida[1]), Integer.parseInt(fechaPartida[2]));
        
        System.out.print("\nIntroduce la fecha en la que quieres recibirlo con DD-MM-AAAA: ");
        fechaPartida = kb.nextLine().split("-");
        Date fechaL = new Date(Integer.parseInt(fechaPartida[0]), Integer.parseInt(fechaPartida[1]), Integer.parseInt(fechaPartida[2]));
        
        System.out.print("\nIntroduce la fecha en la que se te ha entregado:");
        fechaPartida = kb.nextLine().split("-");
        Date fechaE = new Date(Integer.parseInt(fechaPartida[0]), Integer.parseInt(fechaPartida[1]), Integer.parseInt(fechaPartida[2]));
        
        System.out.print("\nIntroduce el estado del pedido: ");
        String estado = kb.nextLine();
        
        System.out.print("\nIntroduce un comentario: ");
        String comentario = kb.nextLine();
        
        System.out.print("\nIntroduce el código del cliente: ");
        Clientes cliente = (Clientes) session.get(Clientes.class, kb.nextInt());
        
        Pedidos pedido = new Pedidos(id, cliente, fechaP, fechaL, fechaE, estado, comentario);
        
        session.save(pedido);
        
        tx.commit();
        session.close();
    }
}
