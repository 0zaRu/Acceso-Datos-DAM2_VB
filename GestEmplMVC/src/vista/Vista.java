/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Controlador;
import entidades.Empleado;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Modelo;


/**
 *
 * @author Carlos
 */
public class Vista {
    Controlador c= new Controlador();

    public void cargar_aleatorios() {
        System.out.println("¿Cuántos empleados vas a generar?: ");
        Scanner sc = new Scanner(System.in);
        String ent;
        ent = sc.nextLine();
        c.generarAleatorios(Integer.parseInt(ent));
    }

    public void mostrarEmpleados() {
        ArrayList<String> emp_mostrar = c.mostrarEmpleados();
        for (String st: emp_mostrar){
            System.out.println(st);
        }           
    }

    public void escribeDom(){
        if(c.generaDom())
            System.out.println("\nEscrito correctamente en el fichero por defecto\n");
        else
            System.err.println("Hubo un problema a la hora de la escritura");
    }
    
    public void leeDom(){
        if(c.leeDom())
            System.out.println("\nLeido correctamente del fichero por defecto\n");
        else
            System.err.println("Hubo un problema a la hora de la lectura");
    }

   
} // fin class Vista

