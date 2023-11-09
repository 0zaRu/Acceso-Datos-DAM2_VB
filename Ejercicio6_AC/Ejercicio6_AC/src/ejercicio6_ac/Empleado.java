package ejercicio6_ac;

import java.time.LocalDate;

/**
 *
 * @author 6002754
 */
public class Empleado {
    private int nEmpleado;
    private String apellido;
    private String oficio;
    private int dir;
    private LocalDate fecha_alta;
    private double salario;
    private float comision;
    private int nDepartamento;

    public Empleado() {
    }

    
    public Empleado(int nEmpleado, String apellido, String oficio, int dir, LocalDate fecha_alta, double salario, float comision, int nDepartamento) {
        this.nEmpleado = nEmpleado;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alta = fecha_alta;
        this.salario = salario;
        this.comision = comision;
        this.nDepartamento = nDepartamento;
    }

    public int getnEmpleado() {
        return nEmpleado;
    }

    public void setnEmpleado(int nEmpleado) {
        this.nEmpleado = nEmpleado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public LocalDate getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public int getnDepartamento() {
        return nDepartamento;
    }

    public void setnDepartamento(int nDepartamento) {
        this.nDepartamento = nDepartamento;
    }

    @Override
    public String toString() {
        return String.format("| %-5s| %-15s| %-15s| %-5s| %-12s| %-10s| %-5s| %-5s|", nEmpleado, apellido, oficio, dir, fecha_alta, salario, comision, nDepartamento);
    }
}
