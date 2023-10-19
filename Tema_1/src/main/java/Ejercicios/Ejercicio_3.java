package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author 6002754
 */
public class Ejercicio_3 {
    
    static Scanner kb = new Scanner(System.in);
    
    public static void main(String[] args){
        kb.useDelimiter(System.getProperty("line.separator"));
        boolean salir = false;
        String ruta, nombre;
        
        do{
            int opcion = menu();
            limpiar();
            
            switch(opcion){
                case 1:
                    System.out.print("\nIntroduce una ruta: ");
                    ruta = kb.next();
                    
                    if(!mostrarDirectorio(ruta))
                        System.out.println("\nEl directorio introducido o el nombre probablemente sea erroneo, pulse enter ...");
                        
                    break;
                case 2:
                    System.out.print("\nIntroduce una ruta donde quieres crearlo: ");
                    ruta = kb.next();
                    
                    System.out.print("\nIntroduce el nombre de la carpeta: ");
                    nombre = kb.next();
                    
                    if(!creaDirectorio(ruta, nombre))
                        System.out.println("\nEl directorio introducido o el nombre probablemente sea erroneo, pulse enter ...");
                    
                    break;
                case 3:
                    System.out.print("\nIntroduce una ruta donde quieres crearlo: ");
                    ruta = kb.next();
                    
                    System.out.print("\nIntroduce el nombre y extensión del archivo: ");
                    nombre = kb.next();
                    
                    if(!creaFichero(ruta, nombre))
                        System.out.println("\nEl directorio introducido o el nombre probablemente sea erroneo, pulse enter ...");
                    
                    break;
                case 4:
                    System.out.print("\nIntroduce una ruta de fichero que quieras renombrar: ");
                    ruta = kb.next();
                    
                    System.out.print("\nIntroduce el nuevo nombre: ");
                    nombre = kb.next();
                    
                    if(!renombraFichero(ruta, nombre))
                        System.out.println("\nEl directorio introducido o el nombre probablemente sea erroneo, pulse enter ...");
                    
                    break;
                case 5:
                    System.out.print("\nIntroduce una ruta de fichero que quieras borrar: ");
                    ruta = kb.next();
                    
                    if(!borraFichero(ruta))
                        System.out.println("\nLa ruta introducida probablemente sea erronea, pulse enter ...");
                    
                    break;
                case 6:
                    salir = true;
                    break;
                default:
            }
            
            System.out.println("Pulse enter para continuar ...");
            kb.next();
            limpiar();
            
        }while(!salir);
    }
    
    public static int menu(){
        System.out.println("            Menu de selección           ");
        System.out.println("========================================");
        System.out.println();
        System.out.println("1. Mostrar contenido de un directorio.  ");
        System.out.println("2. Crear un directorio.                 ");
        System.out.println("3. Crea un fichero.                     ");
        System.out.println("4. Cambia el nombre de un fichero       ");
        System.out.println("5. Borra un fichero.                    ");
        System.out.println("6. Salir.                               ");
        System.out.println();
        System.out.println("========================================");
        System.out.print("\nOpcion: ");
        int opcion = kb.nextInt();
        
        return opcion;
    }
    
    public static boolean mostrarDirectorio(String cadenaRuta){
        
        File ruta = new File(cadenaRuta);
        
        if(!ruta.exists())
            return false;
        
        File contenido[] = ruta.listFiles();
        for(File archivo : contenido){
            if(archivo.isDirectory())
                System.out.println("Directorio\t - \t"+archivo.getName());
            else
                System.out.println(archivo.length()+"\t - \t"+archivo.getName());
        }
        
        return true;
    }
    
    public static boolean creaDirectorio(String cadenaRuta, String nombreDir){
        File ruta = new File(cadenaRuta);
        File nuevoDir = new File(cadenaRuta, nombreDir);
        
        if(!ruta.exists())
            return false;
        
        if(!nuevoDir.exists())
            nuevoDir.mkdirs();
            
        return true;
    }
    
    public static boolean creaFichero(String cadenaRuta, String nombreFile){
        File ruta = new File(cadenaRuta);
        File nuevoFile = new File(cadenaRuta, nombreFile);
        
        if(!ruta.exists())
            return false;
        
        try{
            if(!nuevoFile.exists())
                nuevoFile.createNewFile();
            
        }catch(IOException e){
            return false;
        }    
        
        return true;
    }
    
    public static boolean renombraFichero(String cadenaRuta, String newNombre){
        File ruta = new File(cadenaRuta);
        
        if(!ruta.exists())
            return false;
        
        ruta.renameTo(new File(ruta.getParent(), newNombre));
            
        return true;
    }
    
    public static boolean borraFichero(String cadenaRuta){
        File ruta = new File(cadenaRuta);
        
        if(!ruta.exists())
            return false;
        
        if(!ruta.delete())
            return false;
        
        return true;
    }
    
    public static void limpiar(){
        for(int i = 0; i < 25; i++)
            System.out.println();
    }
}
