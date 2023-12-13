package controlador;

import hibernate.Departamentos;
import hibernate.Empleados;
import hibernate.SessionFactoryUtil;
import java.time.Instant;
import java.util.Date;
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
public class EmpleadosMethods {

    public static Empleados insertaEmpleado(Empleados nuevoEmp) {
        boolean nuevo = false;

        if (nuevoEmp == null) {
            nuevo = true;
            nuevoEmp = new Empleados();
        }

        try {
            Scanner scanner = new Scanner(System.in);

            if (nuevo) {
                System.out.print("Introduce el numero de empleado: ");
                nuevoEmp.setEmpNo(scanner.nextShort());
            }

            System.out.print("Introduce el numero de departamento al que pertenece: ");
            nuevoEmp.setnDepartamento(scanner.nextByte());
            scanner.nextLine();

            System.out.print("Introduce el apellido del nuevo empleado: ");
            nuevoEmp.setApellido(scanner.nextLine());

            System.out.print("Introduce el oficio del nuevo empleado: ");
            nuevoEmp.setOficio(scanner.nextLine());

            System.out.print("Introduce la dir (numerico) del nuevo empleado: ");
            nuevoEmp.setDir(scanner.nextShort());
            scanner.nextLine();

            if (nuevo) {
                System.out.print("Fecha de alta establecida como la actual.");
                scanner.nextLine();
                nuevoEmp.setFechaAlta(Date.from(Instant.now()));
            }

            System.out.print("Introduce el salario del nuevo empleado: ");
            nuevoEmp.setSalario(scanner.nextFloat());

            System.out.print("Introduce la comisión del nuevo empleado: ");
            nuevoEmp.setComision(scanner.nextFloat());

            return nuevoEmp;

        } catch (Exception e) {
            return null;
        }
    }

    public static int insertaNumEmp() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Introduce el numero de empleado con el que deseas trabajar: ");
            int nEmp = scanner.nextInt();
            scanner.nextLine();

            return nEmp;

        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean altaEmpleado(Empleados emp) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Transaction tx = session.beginTransaction();
            Departamentos deptPertenencia = (Departamentos) session.load(Departamentos.class, emp.getnDepartamento());
            emp.setDepartamentos(deptPertenencia);

            session.save(emp);

            tx.commit();
            session.close();

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean bajaEmpleado(int nEmp) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Transaction tx = session.beginTransaction();
            session.delete(session.load(Empleados.class, (short) nEmp));

            tx.commit();
            session.close();

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean modificaEmpleado(int nEmp) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Empleados recogido = (Empleados) session.load(Empleados.class, (short) nEmp);
            recogido = insertaEmpleado(recogido);

            Departamentos deptPertenencia = (Departamentos) session.load(Departamentos.class, recogido.getnDepartamento());
            recogido.setDepartamentos(deptPertenencia);

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

    public static boolean consultaEmpleado(int nEmp) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Empleados recogido = (Empleados) session.load(Empleados.class, (short) nEmp);

            recogido.imprimeColumnado(true);
            session.close();

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean empleadosDeDepartamento(int nDept) {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Departamentos deptComun = (Departamentos) session.load(Departamentos.class, (byte) nDept);

            /*
            Query query = session.createQuery("FROM Empleados e WHERE e.departamentos.deptNo = ?");
            query.setByte(0, deptComun.getDeptNo());
            
            Iterator<Empleados> empleados = query.iterate();
             */
            Iterator<Empleados> empleados = deptComun.getEmpleadoses().iterator();

            boolean primero = true;
            while (empleados.hasNext()) {
                Empleados empLeido = (Empleados) empleados.next();
                empLeido.imprimeColumnado(primero);
                primero = false;
            }

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean empleados() {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Query query = session.createQuery("FROM Empleados");

            Iterator<Empleados> empleados = query.iterate();

            boolean primero = true;
            while (empleados.hasNext()) {
                Empleados empLeido = (Empleados) empleados.next();
                empLeido.imprimeColumnado(primero);
                primero = false;
            }

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean salarioEmpresa() {
        try {
            SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
            Session session = sesion.openSession();

            Query query = session.createQuery("select avg(salario), max(salario), sum(salario) FROM Empleados e");

            Object[] resultado = (Object[]) query.uniqueResult();

            System.out.printf("SALARIOS:\n==========\n%-15s %-15s %-15s\n", "Promedio", "Máximo", "Total");
            System.out.printf("%-15.2f %-15.2f %-15.2f\n\n", resultado[0], resultado[1], resultado[2]);

            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
