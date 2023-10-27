package ejercicios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FlujosDeDatos {
    static final String archivoDatos = "archivoDatos.bin";
    static File ruta = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"archivos"+System.getProperty("file.separator"));

    double[] precios = {19.99, 9.99, 15.99, 3.99, 4.99};
    int[] numerosDeUnidades = {12, 8, 13, 29, 50};
    String[] descripciones = {"Acceso a datos", "Bases de datos", "Java", "Android Studio", "Programación móviles"};

    public FlujosDeDatos(){
        if(!ruta.exists())
            ruta.mkdirs();
        
            ruta = new File(ruta, archivoDatos);
    }

    boolean escribirDisco(){

        DataOutputStream escritor = null;
        BufferedOutputStream bos = null;

        try{
            if(!ruta.exists())
                ruta.createNewFile();

            escritor = new DataOutputStream(bos = new BufferedOutputStream(new FileOutputStream(archivoDatos)));

            for(int i = 0; i < this.precios.length; i++){
                escritor.writeDouble(this.precios[i]);
                escritor.writeInt(this.numerosDeUnidades[i]);
                escritor.writeUTF(this.descripciones[i]);
            }

        }catch(Exception e){
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

    boolean leerDisco(){
        DataInputStream lector = null;
        BufferedInputStream bis = null;

        try{
            if(!ruta.exists()){
                return false;
            }

            lector = new DataInputStream(bis = new BufferedInputStream(new FileInputStream(archivoDatos)));
            ArrayList<Double> precios = new ArrayList<>();
            ArrayList<Integer> numerosDeUnidades = new ArrayList<>();
            ArrayList<String> descripciones = new ArrayList<>();

            try{
                while(true){
                    precios.add(lector.readDouble());
                    numerosDeUnidades.add(lector.readInt());
                    descripciones.add(lector.readUTF()); 
                }
            }catch(Exception e){

            }

            this.precios = new double[precios.size()];
            this.numerosDeUnidades = new int[precios.size()];
            this.descripciones = new String[precios.size()];

            for(int i = 0; i < precios.size(); i++){
                this.precios[i] = precios.get(i);
                this.numerosDeUnidades[i] = numerosDeUnidades.get(i);
                this.descripciones[i] = descripciones.get(i);
            }


        }catch(Exception e){
            return false;
        
        }finally{
            try{
                lector.close();
                bis.close();
            }catch(Exception e){
                
            }
        }

        return true;
    }

    boolean apendElemento(double precio, int nUnidades, String descripcion){
        int i;

        double[] precios = new double[this.precios.length+1];
        int[] numerosDeUnidades = new int[this.numerosDeUnidades.length+1];
        String[] descripciones = new String[this.descripciones.length+1];

        for(i = 0; i < this.precios.length; i++){
            precios[i] = this.precios[i];
            numerosDeUnidades[i] = this.numerosDeUnidades[i];
            descripciones[i] = this.descripciones[i];
        }

        precios[i] = precio;
        numerosDeUnidades[i] = nUnidades;
        descripciones[i] = descripcion;

        this.precios = precios;
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

        for(int i = 0; i < precios.length; i++){
            imprime += "Posicion "+ i + "->\tPrecio: "+ precios[i] + ". Unid: " + numerosDeUnidades[i] + ". Desc: " + descripciones[i] + ".\n";
        }

        return imprime;
    }
}