package practica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author arube
 */
public class SLA implements Serializable{
    private int nLibros;
    private String ideal;
    private LocalDate proximaSalida;

    public SLA(int nLibros, String ideal, LocalDate proximaSalida) {
        this.nLibros = nLibros;
        this.ideal = ideal;
        this.proximaSalida = proximaSalida;
    }

    public int getnLibros() {
        return nLibros;
    }

    public void setnLibros(int nLibros) {
        this.nLibros = nLibros;
    }

    public String getIdeal() {
        return ideal;
    }

    public void setIdeal(String ideal) {
        this.ideal = ideal;
    }

    public LocalDate getProximaSalida() {
        return proximaSalida;
    }

    public void setProximaSalida(LocalDate proximaSalida) {
        this.proximaSalida = proximaSalida;
    }

    @Override
    public String toString() {
        return "SLA{" + "nLibros=" + nLibros + ", ideal=" + ideal + ", proximaSalida=" + proximaSalida + '}';
    }
    
    
    
}
