Indicaciones para hacer más fácil que funcione el ejercicio:

1. A la hora de crear la base de datos, utiliza el fichero sql de creación que va en el proyecto 
2. Para que hibernate funcione correctamente, si modificas "hibernate.cfg.xml" para que funcione con tu servicio,
   no habrá problema, pero si borras el archivo y lo vuelves a crear para hacer el proceso más fácil, en la pestaña
   desplegable de "Mappings", deberás poner "hibernate", como "package" de cada fichero para que lo mapee correctamente.


En principio todo debería funcionar correctamente siguiendo esas 2 indicaciones. Es importante crear esa base de datos
específica para que los tipos de datos o nombre de variables funcionen correctamente.