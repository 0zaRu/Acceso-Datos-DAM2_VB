PARA LA BASE DE DATOS:

Modifica la linea del constructor del SQLFunctions.java en mysql package
para poner tu nombre de database (el mio fue hecho con create database informaticaMySQL)

Cambia el usuario si no eres root y la contraseña si no es 123456

La base de datos se creó con:

create database informaticaMySQL;
use informaticaMySQL;

CREATE TABLE IF NOT EXISTS Ciclo (
  cicloID int primary key auto_increment,
  nombre_corto varchar(30) unique NOT NULL,
  nombre_largo varchar(30) NOT NULL,
  grado varchar(30) NOT NULL,
  horas int NOT NULL);

CREATE TABLE IF NOT EXISTS Modulo (
  moduloID int primary key auto_increment,
  nombre varchar(30) NOT NULL,
  curso varchar(30) NOT NULL,
  cicloID int not null,
  CONSTRAINT `cicloID_ibfk_1` FOREIGN KEY (cicloID) REFERENCES Ciclo (cicloID) ON UPDATE CASCADE
  );