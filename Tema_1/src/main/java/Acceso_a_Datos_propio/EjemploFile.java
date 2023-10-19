package Acceso_a_Datos_propio;

import java.io.File;

public class EjemploFile{
    public static void main(String[] args){
        File f = new File(".");
        String[] lista = f.list();

        System.out.print("Directorio actual: "+ f.getAbsolutePath()+"\n\n");
        
        for(String nombre : lista)
            System.out.println(nombre);
        
    }
}