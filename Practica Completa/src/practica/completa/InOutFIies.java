/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica.completa;

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
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arube
 */
public class InOutFIies {
    public static void escribeBinarios() throws FileNotFoundException, IOException{
        FileInputStream fis;
        fis = new FileInputStream("gojo.jpg");
        
        BufferedInputStream bis= new BufferedInputStream(fis);
        
        FileOutputStream fos;
        fos = new FileOutputStream("copia_gojo.jpg");
        
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        
        int c;
        while((c = bis.read()) != -1)
            bos.write(c);
        
        System.out.println("Se ha escrito correctamente");
    }
    
    public static void escribeFrase(String frase)throws FileNotFoundException, IOException{
        File libro = new File("libro.txt");
        if(!libro.exists()) libro.createNewFile();
        
        BufferedReader flujoDeEntrada = null; 
        PrintWriter flujoDeSalida = null; 
        
        try { 
       
            flujoDeEntrada = new BufferedReader(new FileReader(libro)); 
            flujoDeSalida = new PrintWriter(new FileWriter(libro, true));
    
            if (frase != null) {
                flujoDeSalida.print(frase); 
            }
            
            String linea = "";
            System.out.println("Se ha leido del fichero: ");
            while((linea = flujoDeEntrada.readLine()) != null){
                System.out.println(linea);
            }
 
        } finally {
            if (flujoDeEntrada != null) {
                flujoDeEntrada.close();
            }
            if (flujoDeSalida != null) {
                flujoDeSalida.close();
            }
        }
    }
}
