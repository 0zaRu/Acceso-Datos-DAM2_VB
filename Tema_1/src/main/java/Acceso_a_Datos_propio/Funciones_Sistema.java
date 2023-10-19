/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Acceso_a_Datos_propio;

/**
 *
 * @author arube
 */
public class Funciones_Sistema {
    public static void main(String[] args){
        
        //Diferentes system properties en:
        //https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
        /*
        System.out.println("\n\nEl usuario que está en ejecución es: " + System.getProperty("user.home"));
        System.out.println("  \nEl separador del sistema es: " + System.getProperty("file.separator"));
        System.out.println("  \nActualmente la ruta donde está ejecutando el programa es: "+ System.getProperty("user.dir"));
        
        //Este no se muestra porque escribe separador de línea, vamos como pner \n
        System.out.println("  \nLas líneas se separan con el caracter: " + System.getProperty("line.separator"));

        */

        System.out.println("file.separator: "+System.getProperty("file.separator"));
        System.out.println("java.class.path: "+System.getProperty("java.class.path"));
        System.out.println("java.home: "+System.getProperty("java.home"));
        System.out.println("java.vendor: "+System.getProperty("java.vendor"));
        System.out.println("java.vendor.url: "+System.getProperty("java.vendor.url"));
        System.out.println("java.version: "+System.getProperty("java.version"));
        System.out.println("line.separator: "+System.getProperty("line.separator"));
        System.out.println("os.arch: "+System.getProperty("os.arch"));
        System.out.println("os.name: "+System.getProperty("os.name"));
        System.out.println("os.version: "+System.getProperty("os.version"));
        System.out.println("path.separator: "+System.getProperty("path.separator"));
        System.out.println("user.dir: "+System.getProperty("user.dir"));
        System.out.println("user.home: "+System.getProperty("user.home"));
        System.out.println("user.name: "+System.getProperty("user.name"));
        
    }
}
