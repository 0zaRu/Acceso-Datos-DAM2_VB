package ejercicios;

import java.io.File;

/*
 * @author 6002754
 */
public class Ejercicio_2 {
    public static void main(String[] args){
        if(args.length != 1){
            System.err.println("Numero de argumentos incorrecto, debes pasar 1.");
            return;
        }
        
        File ruta = new File(args[0]);
        
        if(!ruta.exists()){
            System.err.println("Ruta no válida, vuelva a ejecutar el programa");
            return;
        }
        
        File contenido[] = ruta.listFiles();
        for(File archivo : contenido){
            if(archivo.isDirectory())
                System.out.println("Directorio - "+archivo);
            else
                System.out.println("   Archivo - "+archivo+" --\t--\t\t"+archivo.length());
        }
    }
}
