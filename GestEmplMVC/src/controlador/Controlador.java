/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Modelo;

/**
 *
 * @author Carlos
 */
public class Controlador {
    Modelo m= new Modelo();

    public void generarAleatorios(int parseInt) {
        m.generarAleatorios(parseInt);
    }

    public ArrayList<String> mostrarEmpleados() {
       return m.mostrarEmpleados();
    }   

    public boolean generaDom(){
        return m.generaDom();
    }
    
    public boolean leeDom(){
        return m.leeDom();
    }
    
    public boolean leeSax(){
        return m.leeSax();
    }
    
    public boolean escribeSax(){
        return m.escribeSax();
    }
}