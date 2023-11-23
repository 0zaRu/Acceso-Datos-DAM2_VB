package practica.completa;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author arube
 */
public class SLA implements Serializable{
    private int nLibros;
    private String ideal;
    private Date proximaSalida;

    public SLA(int nLibros, String ideal, Date proximaSalida) {
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

    public Date getProximaSalida() {
        return proximaSalida;
    }

    public void setProximaSalida(Date proximaSalida) {
        this.proximaSalida = proximaSalida;
    }

    @Override
    public String toString() {
        return "SLA{" + "nLibros=" + nLibros + ", ideal=" + ideal + ", proximaSalida=" + proximaSalida + '}';
    }
    
    
    
}
