/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author 6002754
 */
public class Ciclo implements Serializable{
    private String nombre_corto;
    private String nombre_largo;
    private String grado;
    private int horas;
    private ArrayList<Modulo> modulos;

    public Ciclo(String nombre_corto, String nombre_largo, String grado, int hotas, ArrayList<Modulo> modulos) {
        this.nombre_corto = nombre_corto;
        this.nombre_largo = nombre_largo;
        this.grado = grado;
        this.horas = hotas;
        this.modulos = modulos;
    }

    public String getNombre_corto() {
        return nombre_corto;
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = nombre_corto;
    }

    public String getNombre_largo() {
        return nombre_largo;
    }

    public void setNombre_largo(String nombre_largo) {
        this.nombre_largo = nombre_largo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public ArrayList<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(ArrayList<Modulo> modulos) {
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        return "Ciclo{" + "nombre_corto=" + nombre_corto + ", nombre_largo=" + nombre_largo + ", grado=" + grado + ", hotas=" + horas + ", modulos=" + modulos + '}';
    }
    
}
