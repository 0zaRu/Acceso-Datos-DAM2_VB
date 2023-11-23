/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica.completa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author arube
 */
public class InOutFIies {
    public static void escribeBinarios() throws FileNotFoundException{
        FileInputStream fis;
        fis = new FileInputStream("gojo.jpg");
        
        BufferedInputStream bis= new BufferedInputStream(fis);
        
        FileOutputStream fos;
        fos = new FileOutputStream("copia_gojo.jpg");
        
        BufferedOutputStream bos = new BufferedOutputStream(fos);

    }
}
