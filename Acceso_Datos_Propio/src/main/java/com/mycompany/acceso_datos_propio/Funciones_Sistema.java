/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.acceso_datos_propio;

/**
 *
 * @author arube
 */
public class Funciones_Sistema {
    public static void main(String[] args){
        
        //Diferentes system properties en:
        //https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html

        System.out.println("\n\nEl usuario que está en ejecución es: " + System.getProperty("user.home"));
        System.out.println("  \nEl separador del sistema es: " + System.getProperty("file.separator"));
        System.out.println("  \nActualmente la ruta donde está ejecutando el programa es: "+ System.getProperty("user.dir"));
        
        //Este no se muestra porque escribe separador de línea, vamos como pner \n
        System.out.println("  \nLas líneas se separan con el caracter: " + System.getProperty("line.separator"));

        
    }
}
