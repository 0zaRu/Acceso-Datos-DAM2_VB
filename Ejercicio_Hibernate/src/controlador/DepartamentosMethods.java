/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hibernate.Departamentos;
import hibernate.SessionFactoryUtil;
import java.util.Iterator;
import java.util.Scanner;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author 6002754
 */
public class DepartamentosMethods {

    public static Departamentos insertaDepartamento(Departamentos nuevoDept) {
        boolean nuevo = false;
        if (nuevoDept == null) {
            nuevo = true;
            nuevoDept = new Departamentos();
        }
        try {
            Scanner scanner = new Scanner(System.in);

            if (nuevo) {
                System.out.print("Introduce el numero de departamento: ");
                nuevoDept.setDeptNo(scanner.nextByte());
                scanner.nextLine();
            }

            System.out.print("Introduce el nombre del departamento: ");
            nuevoDept.setDnombre(scanner.nextLine());

            System.out.print("Introduce el loc del departamento: ");
            nuevoDept.setLoc(scanner.nextLine());

            return nuevoDept;

        } catch (Exception e) {
            return null;
        }
    }

    public static int insertaNumDept() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Introduce el numero de departamento con el que deseas trabajar: ");
            int nDept = scanner.nextInt();
            scanner.nextLine();

            return nDept;

        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean altaDepartamento(Departamentos dept) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Transaction tx = session.beginTransaction();
            session.save(dept);

            tx.commit();
            session.close();

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean bajaDepartamento(int nDept) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Transaction tx = session.beginTransaction();
            session.delete(session.load(Departamentos.class, (byte) nDept));

            tx.commit();
            session.close();

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean modificaDepartamento(int nDept) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Departamentos recogido = (Departamentos) session.load(Departamentos.class, (byte) nDept);
            recogido = insertaDepartamento(recogido);

            Transaction tx = session.beginTransaction();

            if (recogido != null) {
                session.update(recogido);
            } else {
                return false;
            }

            tx.commit();
            session.close();

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean consultaDepartamento(int nDept) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Departamentos recogido = (Departamentos) session.load(Departamentos.class, (byte) nDept);

            recogido.imprimeColumnado(true);
            session.close();

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean departamentos() {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Query query = session.createQuery("FROM Departamentos");

            Iterator<Departamentos> departamentos = query.iterate();

            boolean primero = true;
            while (departamentos.hasNext()) {
                Departamentos deptLeido = (Departamentos) departamentos.next();
                deptLeido.imprimeColumnado(primero);
                primero = false;
            }

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean salarioDepartamento(int nDept) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Query query = session.createQuery("select avg(salario), max(salario), sum(salario) FROM Empleados e "
                    + "WHERE departamentos.deptNo = ?");

            query.setByte(0, (byte) nDept);

            Object[] resultado = (Object[]) query.uniqueResult();

            System.out.printf("SALARIOS:\n==========\n%-15s %-15s %-15s\n", "Promedio", "Máximo", "Total");
            System.out.printf("%-15.2f %-15.2f %-15.2f\n\n", resultado[0], resultado[1], resultado[2]);

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static int cargaDepartamentosyEmpleados() {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();

            // Borro los datos previos
            Query q = session.createQuery("delete from Empleados");
            q.executeUpdate();

            q = session.createQuery("delete from Departamentos");
            q.executeUpdate();

            // Inserto desde la tabla auxiliar para Departamentos
            String hqlInsertDepartamentos = "insert into Departamentos(deptNo, dnombre, loc) "
                    + "select n.deptNo, n.dnombre, n.loc from AuxDepartamentos n";

            int filasInsDepartamentos = session.createQuery(hqlInsertDepartamentos).executeUpdate();

            // Inserto desde la tabla auxiliar para Empleados
            String hqlInsertEmpleados = "insert into Empleados(empNo, departamentos, apellido, oficio, dir, fechaAlta, salario, comision) "
                    + "select n.empNo, d, n.apellido, n.oficio, n.dir, n.fechaAlta, n.salario, n.comision from AuxEmpleados n, Departamentos d "
                    + "where n.auxDepartamentos.deptNo = d.deptNo";

            int filasInsEmpleados = session.createQuery(hqlInsertEmpleados).executeUpdate();

            tx.commit();
            session.close();

            // Devuelvo el número total de filas insertadas
            return filasInsDepartamentos + filasInsEmpleados;
        } catch (HibernateException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
