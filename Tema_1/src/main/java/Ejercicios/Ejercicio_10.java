/*
A partir de la estructura dada en teoría para un fichero aleatorio, y partiendo de los 
datos almacenados en un fichero, realizar una aplicación que permita realizar las 
siguientes opciones:
        a. Visualizar el contenido del fichero. En forma de tabla con columnas alineadas, 
            con títulos en las cabeceras y mostrando cada empleado en una línea.
        b. Visualizar los datos de un empleado, pidiendo su identificador.
        c. Añadir un empleado al final del fichero. (Es necesario calcular su identificador 
            antes de añadirlo, en función del tamaño del fichero).
        d. Modificar los datos de un empleado. Se muestran los actuales y se permiten 
            cambios, excepto en el identificador
 */
package ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio_10 {
    static final int RECORD_SIZE = 36;
    static File fich = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"archivos"+System.getProperty("file.separator"), "Empl_aleat.dat");
    
    public static void main(String[] args) {
        //Si el fichero de datos no existe, lo creo
        if(!fich.exists())
            creaFichero();

        try {
            muestraDatos();

            System.out.println("\n\n");
            displayEmployee(2);

            System.out.println("\n\n");
            addEmployee();

            System.out.println("\n\n");
            modifyEmployee(2);
            

        } catch (IOException e) {

            e.printStackTrace();
        }
        
    }

    static void muestraDatos(){
        RandomAccessFile file = null;

        try{

            file = new RandomAccessFile(fich, "r");
            
            System.out.printf("%-5s %-15s %-15s %-10s\n", "ID", "Apellido", "Departamento", "Salario");
            
            while(file.getFilePointer() < file.length()){
                int id = file.readInt();
                char[] apellidoChars = new char[10];

                for(int i = 0; i < 10; i++)
                    apellidoChars[i] = file.readChar();
                
                String apellido = new String(apellidoChars).trim();

                int dept = file.readInt();
                double salario = file.readDouble();

                System.out.printf("%-5d %-15s %-15d %-10.2f\n", id, apellido, dept, salario);
            }
            file.close();

        }catch(Exception e){
            e.getMessage();
        }
    }

    static void creaFichero(){
        if(!fich.getParentFile().exists())
            fich.getParentFile().mkdirs();
        
        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile(fich, "rw");

            String[] apellidos = { "MARTÍN", "SORIA", "FERNÁNDEZ", "LUNA", "GARCÍA", "PÉREZ", "RODRÍGUEZ", "MARTÍNEZ" };
            int[] dept = { 10, 20, 30, 20, 10, 40, 30, 40 };
            Double[] salario = { 850.65, 12035.36, 2156.36, 1500.32, 989.23, 1566.32, 1866.88, 2356.78 };
            StringBuffer buffer = null;

            for (int i = 0; i < apellidos.length; i++) {
                
                file.writeInt(i + 1); // identificador de empleado
                
                buffer = new StringBuffer(apellidos[i]);
                buffer.setLength(10);

                    file.writeChars(buffer.toString());
                    file.writeInt(dept[i]);
                    file.writeDouble(salario[i]);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            
        } catch(Exception e){
            e.printStackTrace();
        
        }finally{
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void displayEmployee(int id) throws IOException{
        RandomAccessFile file = new RandomAccessFile(fich, "r");

        file.seek((id-1) * RECORD_SIZE);
        int ide = file.readInt();
        char[] apellidoChars = new char[10];

        for(int i = 0; i < 10; i++)
            apellidoChars[i] = file.readChar();
        
        String apellido = new String(apellidoChars).trim();

        int dept = file.readInt();
        double salario = file.readDouble();

        System.out.println("ID:"+ide);
        System.out.println("Apellido::"+apellido);
        System.out.println("Departamento::"+dept);
        System.out.println("Salario:"+salario);
        
        file.close();
    }

    public static void addEmployee() throws IOException{
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el nombre del empleado: ");
        String apellido = sc.nextLine();

        System.out.print("Introduce el numero del departamento: ");
        int dept = sc.nextInt();

        System.out.print("introduce el salario del empleado:: ");
        double salario = sc.nextDouble();

        RandomAccessFile file = new RandomAccessFile(fich, "rw");
        int id = (int) (file.length() / RECORD_SIZE) + 1;
        file.seek(file.length());

        file.writeInt(id);

        StringBuffer buffer = new StringBuffer(apellido);
        buffer.setLength(10);
        file.writeChars(buffer.toString());

        file.writeInt(dept);
        file.writeDouble(salario);

        file.close();
        sc.close();
    }

    public static void modifyEmployee(int id) throws IOException{
        Scanner sc = new Scanner(System.in);

        RandomAccessFile file = new RandomAccessFile(fich, "rw");
        file.seek((id-1) * RECORD_SIZE);

        int empID = file.readInt();

        char[] apellidoChars = new char[10];

        for(int i = 0; i < 10; i++)
            apellidoChars[i] = file.readChar();
        
        String apellido = new String(apellidoChars).trim();

        int dept = file.readInt();
        double salario = file.readDouble();

        System.out.println("ID:"+empID);
        System.out.println("Apellido::"+apellido);
        System.out.println("Departamento::"+dept);
        System.out.println("Salario:"+salario);

        System.out.print("Introduce el nuevo apellido (en blanco para no modificar): ");
        String nuevoApellido = sc.nextLine();

        System.out.print("introduce el nuevo departamento (-1 para no modificar): ");
        int nuevoDept = sc.nextInt();

        System.out.print("Introduce el nuevo salario (-1 para no modificar): ");
        double nuevoSalario = sc.nextDouble();

        if(!nuevoApellido.isEmpty()){
            StringBuffer buffer = new StringBuffer(nuevoApellido);
            buffer.setLength(10);
            apellido = buffer.toString();
        }

        if(nuevoDept != -1)
            dept = nuevoDept;
        
        if(nuevoSalario != -1)
            salario = nuevoSalario;

        file.seek((id-1) * RECORD_SIZE +4);
        file.writeChars(apellido);
        file.writeInt(dept);
        file.writeDouble(nuevoSalario);

        file.close();
        sc.close();
    }
}