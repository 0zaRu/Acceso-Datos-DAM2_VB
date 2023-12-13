package hibernate;
// Generated 12 dic. 2023 21:03:17 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * AuxEmpleados generated by hbm2java
 */
public class AuxEmpleados  implements java.io.Serializable {


     private short empNo;
     private AuxDepartamentos auxDepartamentos;
     private String apellido;
     private String oficio;
     private short dir;
     private Date fechaAlta;
     private float salario;
     private float comision;

    public AuxEmpleados() {
    }

    public AuxEmpleados(short empNo, AuxDepartamentos auxDepartamentos, String apellido, String oficio, short dir, Date fechaAlta, float salario, float comision) {
       this.empNo = empNo;
       this.auxDepartamentos = auxDepartamentos;
       this.apellido = apellido;
       this.oficio = oficio;
       this.dir = dir;
       this.fechaAlta = fechaAlta;
       this.salario = salario;
       this.comision = comision;
    }
   
    public short getEmpNo() {
        return this.empNo;
    }
    
    public void setEmpNo(short empNo) {
        this.empNo = empNo;
    }
    public AuxDepartamentos getAuxDepartamentos() {
        return this.auxDepartamentos;
    }
    
    public void setAuxDepartamentos(AuxDepartamentos auxDepartamentos) {
        this.auxDepartamentos = auxDepartamentos;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getOficio() {
        return this.oficio;
    }
    
    public void setOficio(String oficio) {
        this.oficio = oficio;
    }
    public short getDir() {
        return this.dir;
    }
    
    public void setDir(short dir) {
        this.dir = dir;
    }
    public Date getFechaAlta() {
        return this.fechaAlta;
    }
    
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    public float getSalario() {
        return this.salario;
    }
    
    public void setSalario(float salario) {
        this.salario = salario;
    }
    public float getComision() {
        return this.comision;
    }
    
    public void setComision(float comision) {
        this.comision = comision;
    }




}


