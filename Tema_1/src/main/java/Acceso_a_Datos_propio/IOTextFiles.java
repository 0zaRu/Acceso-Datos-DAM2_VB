package Acceso_a_Datos_propio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IOTextFiles {

    static Scanner kb = new Scanner(System.in);
    public static void main(String[] args){
        
        kb.useDelimiter(System.getProperty("line.separator"));

        System.out.println("Escribe una frase para almacenar en un fichero:");
        String frase = kb.nextLine();

        File ruta = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+"archivos");
        if(!ruta.exists())
            ruta.mkdir();


        escribeFrase(frase, ruta);

    }


    private static void escribeFrase(String frase, File ruta) {
        File archivo = new File(ruta, System.getProperty("file.separator")+"archivo.txt");

        FileWriter fichero = null;
        PrintWriter escritor = null;

        try{
            if(archivo.exists())
                escritor = new PrintWriter(fichero = new FileWriter(archivo, true), true);
            else
                escritor = new PrintWriter(fichero = new FileWriter(archivo, true));

            escritor.println(frase);
        
        }catch(IOException e){
            e.getMessage();
        
        }finally{
            try {
                escritor.close();
                fichero.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
