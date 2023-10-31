/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Empleado implements Serializable{
    
    private int id;
    private String nombre;
    private String apell1;
    private String apell2;
    private float salario;
    private static int identif = 0;
    private static final String[] nombres = {"Juan", "Ricardo", "Rosa", "Ana", "Sandra", "Laura", "Mario", "José", "Gael", "Clara", "Carlos", "Paz"};
    private static final String[] apellidos = {"Wayne", "García", "Castro", "Lane", "Cano", "Pérez", "Martín", "Gil", "Iglesias", "Sánchez"};
    private static final float[] salarios = {900, 1200, 1600, 1800, 2000, 2200, 3000};

    public Empleado() {
        this.id = identif++;
    }

    public Empleado(int id, String nombre, String apell1, String apell2, float salario) {
        this.id = id;
        this.nombre = nombre;
        this.apell1 = apell1;
        this.apell2 = apell2;
        this.salario = salario;
    }

    public void emp_aleatorio() {
        Random r = new Random();
        setNombre(nombres[r.nextInt(nombres.length)]);
        setApell1(apellidos[r.nextInt(apellidos.length)]);
        setApell2(apellidos[r.nextInt(apellidos.length)]);
        setSalario(salarios[r.nextInt(salarios.length)]);
    }

          
        
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apell1
     */
    public String getApell1() {
        return apell1;
    }

    /**
     * @param apell1 the apell1 to set
     */
    public void setApell1(String apell1) {
        this.apell1 = apell1;
    }

    /**
     * @return the apell2
     */
    public String getApell2() {
        return apell2;
    }

    /**
     * @param apell2 the apell2 to set
     */
    public void setApell2(String apell2) {
        this.apell2 = apell2;
    }

    /**
     * @return the salario
     */
    public float getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getColumna() {
        return String.format("%4d %-15s %-20s %-20s %10.2f", id, nombre, apell1, apell2, salario);
    }

   
}
