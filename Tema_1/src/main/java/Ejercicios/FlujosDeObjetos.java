package Ejercicios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FlujosDeObjetos implements Serializable {
    static final long serialVersionUID = 1;
    static final String archivoObjetos = "archivoObjetos.bin";
    static File ruta = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"archivos"+System.getProperty("file.separator"));

    BigDecimal[] precio_unitario = {
        new BigDecimal("19.99"),
        new BigDecimal("9.99"),
        new BigDecimal("15.99"),
        new BigDecimal("3.99"),
        new BigDecimal("4.99")};
         
    int[] numerosDeUnidades = {12, 8, 13, 29, 50};
    String[] descripciones = {"Acceso a datos", "Bases de datos", "Java", "Android Studio", "Programación móviles"};

    public FlujosDeObjetos(){
        if(!ruta.exists())
            ruta.mkdirs();
        
            ruta = new File(ruta, archivoObjetos);
    }

    boolean escribirDisco(){

        FlujosDeObjetos esteFlujo = this;
        ObjectOutputStream escritor = null;
        BufferedOutputStream bos = null;

        try{
            if(!ruta.exists())
                ruta.createNewFile();

            escritor = new ObjectOutputStream(bos = new BufferedOutputStream(new FileOutputStream(archivoObjetos)));

            escritor.writeObject(esteFlujo);

        }catch(Exception e){
            e.printStackTrace();
            return false;
        
        }finally{
            try{
                escritor.close();
                bos.close();
            }catch(Exception e){

            }
        }

        return true;
    }

    FlujosDeObjetos leerDisco(){

        ObjectInputStream lector = null;
        BufferedInputStream bis = null;

        try{
            if(!ruta.exists()){
                return null;
            }

            lector = new ObjectInputStream(bis = new BufferedInputStream(new FileInputStream(archivoObjetos)));
            return (FlujosDeObjetos)lector.readObject();

        }catch(Exception e){
            return null;
        
        }finally{
            try{
                lector.close();
                bis.close();
            }catch(Exception e){
                
            }
        }
    }

    boolean apendElemento(String precio, int nUnidades, String descripcion){
        int i;

        BigDecimal[] precio_unitario = new BigDecimal[this.precio_unitario.length+1];
        int[] numerosDeUnidades = new int[this.numerosDeUnidades.length+1];
        String[] descripciones = new String[this.descripciones.length+1];

        for(i = 0; i < this.precio_unitario.length; i++){
            precio_unitario[i] = this.precio_unitario[i];
            numerosDeUnidades[i] = this.numerosDeUnidades[i];
            descripciones[i] = this.descripciones[i];
        }
        precio = precio.replace(",", ".");
        
        precio_unitario[i] = new BigDecimal(precio);
        numerosDeUnidades[i] = nUnidades;
        descripciones[i] = descripcion;

        this.precio_unitario = precio_unitario;
        this.numerosDeUnidades = numerosDeUnidades;
        this.descripciones = descripciones;

        return true;
    }

    void menu(){
        System.out.println("=================  MENU DE ACCION ===================");
        System.out.println();
        System.out.println("1. Muestra los datos actuales.");
        System.out.println("2. Escribe los datos en el disco");
        System.out.println("3. Lee y sobre escribe los datos almacenados en disco");
        System.out.println("4. Añade un dato a los actuales.");
        System.out.println("5. Salir");
        System.out.println();
        System.out.println("=====================================================");
        System.out.print("elige una opcion: ");

    }

    @Override
    public String toString(){
        String imprime = "";

        for(int i = 0; i < precio_unitario.length; i++){
            imprime += "Posicion "+ i + "->\tPrecio: "+ precio_unitario[i] + ". Unid: " + numerosDeUnidades[i] + ". Desc: " + descripciones[i] + ".\n";
        }

        return imprime;
    }
}