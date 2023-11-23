package practica.completa;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author arube
 */
public class Cosmere implements Serializable{
    private String creador;
    private Date proximaSalida;
    private int hojasTotales;
    private SLA sagaAsociada;

    public Cosmere(String creador, Date proximaSalida, int hojasTotales, SLA libro) {
        this.creador = creador;
        this.proximaSalida = proximaSalida;
        this.hojasTotales = hojasTotales;
        this.sagaAsociada = libro;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public Date getProximaSalida() {
        return proximaSalida;
    }

    public void setProximaSalida(Date proximaSalida) {
        this.proximaSalida = proximaSalida;
    }

    public int getHojasTotales() {
        return hojasTotales;
    }

    public void setHojasTotales(int hojasTotales) {
        this.hojasTotales = hojasTotales;
    }

    public SLA getSagaAsociada() {
        return sagaAsociada;
    }

    public void setSagaAsociada(SLA sagaAsociada) {
        this.sagaAsociada = sagaAsociada;
    }

    @Override
    public String toString() {
        return "Cosmere{" + "creador=" + creador + ", proximaSalida=" + proximaSalida + ", hojasTotales=" + hojasTotales + ", sagaAsociada=" + sagaAsociada + '}';
    }

    
    
}
