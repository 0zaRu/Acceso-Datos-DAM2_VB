package ejercicio6_ac;

/**
 *
 * @author 6002754
 */
public class Departamento {
    private int nDepartamento;
    private String nombre;
    private String localizacion;

    public Departamento() {
    }
    
    public Departamento(int nDepartamento, String nombre, String localizacion) {
        this.nDepartamento = nDepartamento;
        this.nombre = nombre;
        this.localizacion = localizacion;
    }

    public int getnDepartamento() {
        return nDepartamento;
    }

    public void setnDepartamento(int nDepartamento) {
        this.nDepartamento = nDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    @Override
    public String toString() {
        return "\t| " + nDepartamento + "\t| " + nombre + "\t| " + localizacion + '|';
    }
}
