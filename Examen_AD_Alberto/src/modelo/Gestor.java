/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import org.xml.sax.InputSource;

/**
 *
 * @author 6002754
 */
public class Gestor extends InputSource implements Serializable {

    private ArrayList<Ciclo> ciclosCargados;

    public Gestor(ArrayList<Ciclo> ciclosCargados) {
        this.ciclosCargados = ciclosCargados;
    }

    public Gestor() {
        this.ciclosCargados = new ArrayList<>();
    }

    public ArrayList<Ciclo> getCiclosCargados() {
        return ciclosCargados;
    }

    public void setCiclosCargados(ArrayList<Ciclo> ciclosCargados) {
        this.ciclosCargados = ciclosCargados;
    }

    public void imprime(ArrayList<Ciclo> c){
        for(int i = 0; i < c.size(); i++){
            System.out.println("\n\n===========================================================================================");
            System.out.printf("%5s%50s%20s%8d\n\n", c.get(i).getNombre_corto(), c.get(i).getNombre_largo(), c.get(i).getGrado(), c.get(i).getHoras());
            for(int j = 0; j < c.get(i).getModulos().size(); j++){
                System.out.printf("\tModulo: \t%60s%15s\n", c.get(i).getModulos().get(j).getNombre(), c.get(i).getModulos().get(j).getCurso());
                
            }
        }
    }

    public void generaTextoPlano() throws IOException{
        
        File ruta = new File("ciclos.txt");
        if(!ruta.exists())ruta.createNewFile();
        
        PrintWriter flujoDeSalida = null;
        
        try {
            flujoDeSalida = new PrintWriter(new FileWriter(ruta));
            
            String linea = "";
            
            for (int i = 0; i < ciclosCargados.size(); i++) {
                linea = ciclosCargados.get(i).getNombre_corto();
                linea += "#"+ciclosCargados.get(i).getNombre_largo();
                linea += "#"+ciclosCargados.get(i).getGrado();
                linea += "#"+ciclosCargados.get(i).getHoras();
            
                System.out.println(linea);
                flujoDeSalida.write(linea);
            }
            System.out.println("Se ha guardado correctamente");
        } finally {
            if (flujoDeSalida != null) {
                flujoDeSalida.close();
            }
        }
    }
    
    public void escribeBinario(ArrayList<Ciclo> ciclos) throws FileNotFoundException, IOException{
        File ruta = new File("datosMem.bin");
        if(!ruta.exists()) ruta.createNewFile();
        
        ObjectOutputStream oos = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(ruta)));
        
        oos.writeObject(ciclos);
    }
    
    private byte [] convierteByte(String aConvertir, String add){
        aConvertir+= add;
        char[] array = aConvertir.toCharArray();
            
            byte[] byteArray = new byte[array.length];
            
            for(int i = 0; i < byteArray.length; i++){
                byteArray[i] = (byte)array[i];
            }
        return byteArray;
    } 

    /**
     * ESTE CODIGO LO DEJO COMENTADO PORQUE ESTÁ A MEDIAS, ME HE DADO CUENTA TARDE DE QUE DEBERÍA HABER USADO OTROS
     * VALORES PARA HACER LOS SPLIT A LA HORA DE RECIBIRLO
    */
    public ArrayList<Ciclo> leeBinario() throws FileNotFoundException, IOException, ClassNotFoundException{
        File ruta = new File("datosMem.bin");
        if(!ruta.exists()) ruta.createNewFile();
        
        ObjectInputStream oos = new ObjectInputStream( new BufferedInputStream(new FileInputStream(ruta)));
        ArrayList<Ciclo> recogido = new ArrayList<>();
        
        return (ArrayList<Ciclo>) oos.readObject();
    }
}
