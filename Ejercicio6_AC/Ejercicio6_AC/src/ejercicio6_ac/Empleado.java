package ejercicio6_ac;

/**
 *
 * @author 6002754
 */
public class Empleado {
    private int nEmpleado;
    private String apellido;
    private String oficio;
    private String dir;
    private String fecha_alta;
    private double salario;
    private float comision;
    private int nDepartamento;

    public Empleado() {
    }

    
    public Empleado(int nEmpleado, String apellido, String oficio, String dir, String fecha_alta, double salario, float comision, int nDepartamento) {
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

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
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
        return"| " + nEmpleado + "\t| " + apellido + "\t| " + oficio + "\t| " + dir + "\t| " + fecha_alta + "\t| " + salario + "\t| " + comision + "\t| " + nDepartamento + '|';
    }

    void setOfi(int nextInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
