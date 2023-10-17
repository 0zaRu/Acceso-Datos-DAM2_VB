package Ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio_5 {

    static Scanner kb = new Scanner(System.in);
    public static void main(String[] args){
        
        kb.useDelimiter(System.getProperty("line.separator"));
        StringBuffer leido = new StringBuffer();
        
        File ruta = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"archivo");
        if(!ruta.exists())
            ruta.mkdir();

        
            FileReader fr = null;
            BufferedReader br = null;
            FileWriter fichero = null;
            PrintWriter escritor = null;
            
        try{
            fr = new FileReader("..\\archivos\\archivoOriginal.txt");
            br = new BufferedReader(fr);

            File archivo = new File(ruta, System.getProperty("file.separator")+"archivoMayus.txt");

            if(archivo.exists())
                escritor = new PrintWriter(fichero = new FileWriter(archivo, true), true);
            else
                escritor = new PrintWriter(fichero = new FileWriter(archivo, true));

        
            int c;
            while((c = (int)br.read()) != -1){
                escritor.write(Character.toUpperCase(c));
            }
        
        }catch(IOException e){
            e.getMessage();
        
        }finally{
            try {
                fr.close();
                br.close();
                escritor.close();
                fichero.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
