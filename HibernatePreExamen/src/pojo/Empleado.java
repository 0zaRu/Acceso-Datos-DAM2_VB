package pojo;
// Generated 25 feb. 2024 18:48:57 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * Empleado generated by hbm2java
 */
public class Empleado  implements java.io.Serializable {


     private BigDecimal empNo;
     private Departamento departamento;
     private String apellido;

    public Empleado() {
    }

	
    public Empleado(BigDecimal empNo) {
        this.empNo = empNo;
    }
    public Empleado(BigDecimal empNo, Departamento departamento, String apellido) {
       this.empNo = empNo;
       this.departamento = departamento;
       this.apellido = apellido;
    }
   
    public BigDecimal getEmpNo() {
        return this.empNo;
    }
    
    public void setEmpNo(BigDecimal empNo) {
        this.empNo = empNo;
    }
    public Departamento getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }




}


