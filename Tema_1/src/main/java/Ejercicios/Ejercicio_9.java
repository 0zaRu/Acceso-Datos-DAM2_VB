package ejercicios;

import java.util.Scanner;

public class Ejercicio_9 {
    
    static Scanner kb = new Scanner(System.in);
    public static void main(String[] args){

        FlujosDeObjetos miFlujo = new FlujosDeObjetos();
        boolean salir = false;
        int resultado;

        do{
            miFlujo.menu();

            resultado = kb.nextInt();
            kb.nextLine();
            limpiaPantalla(25);

            switch(resultado){
                case 1:
                    System.out.println(miFlujo);
                    break;
                
                case 2:
                    if(miFlujo.escribirDisco())
                        System.out.println("Objetos escritos correctamente.");
                    else
                        System.out.println("Hubo un problema escribiendo los archivos.");
                    break;

                case 3:
                    FlujosDeObjetos temp = null;

                    if((temp = miFlujo.leerDisco()) != null){
                        miFlujo = temp;
                        System.out.println("Objetos leidos y almacenados correctamente.");
                    }else
                        System.out.println("Hubo un problema leyendo los archivos.");
                    break;
                
                case 4:
                    System.out.print("Introduce el precio del producto: ");
                    String precio = kb.nextLine();
            
                    System.out.print("Introduce la cantidad de unidades del producto: ");
                    int unidades = kb.nextInt();
                    kb.nextLine();

                    System.out.print("Introduce la descripcion del producto: ");
                    String descripcion = kb.nextLine();

                    if(miFlujo.apendElemento(precio, unidades, descripcion))
                        System.out.println("Producto añadido correctamente");
                    
                    else
                        System.out.println("Hubo un problema almacenando el producto");

                    break;

                case 5:
                    salir = true;
                    System.out.println("Se va a salir del programa ...");
                    break;

                default:

            }

            System.out.println("Pulsa enter para seguir ...");
            kb.nextLine();
            limpiaPantalla(25);

        }while(!salir);
    }



    static void limpiaPantalla(int nLineas){
        for(int i = 0; i<nLineas; i++){
            System.out.println();
        }
    }
}
