/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import org.xml.sax.InputSource;

/**
 *
 * @author carlos
 */
public class Alm_datos extends InputSource {

    private ArrayList<Empleado> datos;

    public Alm_datos(ArrayList<Empleado> datos) {
        this.datos = new ArrayList();
        this.datos = datos;
    }

    public ArrayList<Empleado> getDatos() {
        return datos;
    }

    public void mostrar() {
        for (Empleado emp : datos) {
            System.out.println(emp);
        }
    }
}