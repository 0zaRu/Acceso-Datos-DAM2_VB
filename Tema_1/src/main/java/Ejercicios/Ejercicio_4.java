package ejercicios;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author 6002754
 */
public class Ejercicio_4 {
    public static void main(String[] args){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{

            fis = new FileInputStream("..\\archivos\\gato.jpg");
            bis = new BufferedInputStream(fis);
            
            fos = new FileOutputStream("..\\archivos\\copia_gato.jpg");
            bos = new BufferedOutputStream(fos);
        
            int c;
            while((c = bis.read()) != -1)
                bos.write(c);
            
            System.out.println("Imagen copiada, comprueba la carpeta!!!!");
            
        }catch(Exception e){
            System.err.println("Archivo no encontrado");
        
        }finally{
            try{
                fos.close();
                fis.close();

                bis.close();
                bos.close();
            }catch(Exception e){
                
            }
        }
    }
}
