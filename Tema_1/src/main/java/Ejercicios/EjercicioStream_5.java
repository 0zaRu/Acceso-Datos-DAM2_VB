package ejercicios;

import java.util.Scanner;
import java.io.*;

public class EjercicioStream_5 {
	
	static Scanner kb = new Scanner(System.in);
	
	public static void main(String[] args) {
		
        System.out.print("Introduce el nombre del fichero que tienes en: " + System.getProperty("user.home")+ ": ");
        File archivo = new File(System.getProperty("user.home")+System.getProperty("file.separator")+kb.nextLine());
        
        if(!archivo.exists()){
            System.out.println("Ruta no valida ...");
            System.exit(0);
        }

        int nLineas = cuenta_cadenas(archivo);

        escribir_cadena(new File(System.getProperty("user.home")+"\\Desktop"), "Se han leido "+nLineas+" lineas.");
    }

	static void escribir_cadena(File ruta, String salida) {

		PrintWriter fSalir = null;
        File arcDestino = new File(ruta, "lContadas.txt");
		
		try {
            if(!arcDestino.exists())
                arcDestino.createNewFile();

			fSalir= new PrintWriter(new FileWriter(arcDestino));
			
			fSalir.print(salida);
			
		}catch(IOException e) {
			System.err.println("Ha ocurrido un error durante la escritura: "+e.getMessage());
		
		}finally {
		fSalir.close();
		}
	}
	
	static int cuenta_cadenas(File ruta) {
				
		int nLin = 0;
		BufferedReader leer = null;
		
		try {
			leer = new BufferedReader(new FileReader(ruta));
			
			while(leer.readLine() != null) {
				nLin++;
			}
			
		}catch(IOException e) {
			return nLin;
		
        }finally{
            try {
                leer.close();
            } catch (IOException e) {
            }
        }
        return nLin;
	}
}