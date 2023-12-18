CREATE DATABASE `ejemplo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `aux_departamentos` (
  `dept_no` int NOT NULL,
  `dnombre` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `loc` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `aux_empleados` (
  `emp_no` smallint unsigned NOT NULL,
  `apellido` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `oficio` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `dir` smallint NOT NULL,
  `fecha_alta` date NOT NULL,
  `salario` float(9,2) NOT NULL,
  `comision` float(6,2) NOT NULL,
  `dept_no` int NOT NULL,
  PRIMARY KEY (`emp_no`),
  KEY `dept_no` (`dept_no`),
  CONSTRAINT `aux_empleados_ibfk_1` FOREIGN KEY (`dept_no`) REFERENCES `aux_departamentos` (`dept_no`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `departamentos` (
  `dept_no` int NOT NULL,
  `dnombre` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `loc` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `empleados` (
  `emp_no` smallint unsigned NOT NULL,
  `apellido` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `oficio` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `dir` smallint NOT NULL,
  `fecha_alta` date NOT NULL,
  `salario` float(9,2) NOT NULL,
  `comision` float(6,2) NOT NULL,
  `dept_no` int NOT NULL,
  PRIMARY KEY (`emp_no`),
  KEY `dept_no` (`dept_no`),
  CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`dept_no`) REFERENCES `departamentos` (`dept_no`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO departamentos VALUES (10, 'CONTABILIDAD', 'SEVILLA');
INSERT INTO departamentos VALUES (20, 'INVESTIGACION', 'MADRID');
INSERT INTO departamentos VALUES (30, 'VENTAS', 'BARCELONA');
INSERT INTO departamentos VALUES (40, 'PRODUCCION', 'BILBAO');

INSERT INTO empleados VALUES (1, 'López', 'contable', 345, '1987-10-23', 23400.32, 5.34, 10);
INSERT INTO empleados VALUES (2, 'Alonso', 'contable', 346, '1994-12-23', 20400.32, 3.34, 10);
INSERT INTO empleados VALUES (3, 'Santana', 'contable', 345, '1980-09-23', 33670.20, 8.84, 10);
INSERT INTO empleados VALUES (4, 'Gil', 'investigad', 245, '1987-01-23', 23400.32, 5.34, 20);
INSERT INTO empleados VALUES (5, 'Lorenzo', 'investigad', 246, '1992-12-23', 20400.32, 3.34, 20);
INSERT INTO empleados VALUES (6, 'Manteca', 'investigad', 245, '2000-10-23', 33670.20, 8.84, 20);
INSERT INTO empleados VALUES (7, 'Tocino', 'vendedor', 445, '2003-05-23', 23400.32, 5.34, 30);
INSERT INTO empleados VALUES (8, 'Malo', 'vendedor', 446, '1994-06-23', 20400.32, 3.34, 30);
INSERT INTO empleados VALUES (9, 'Salamanca', 'vendedor', 445, '2004-08-23', 33670.20, 8.84, 30);
INSERT INTO empleados VALUES (10, 'Iglesias', 'productor', 145, '1987-01-23', 33400.32, 15.34, 40);
INSERT INTO empleados VALUES (11, 'Martín', 'productor', 146, '2004-12-23', 28400.32, 13.34, 40);
INSERT INTO empleados VALUES (12, 'Soroya', 'productor', 145, '1980-10-23', 43670.20, 18.84, 40);

INSERT INTO aux_departamentos VALUES (10, 'CONTABILIDAD', 'SEVILLA');
INSERT INTO aux_departamentos VALUES (20, 'INVESTIGACION', 'MADRID');
INSERT INTO aux_departamentos VALUES (30, 'VENTAS', 'BARCELONA');
INSERT INTO aux_departamentos VALUES (40, 'PRODUCCION', 'BILBAO');

INSERT INTO aux_empleados VALUES (1, 'López', 'contable', 345, '1987-10-23', 23400.32, 5.34, 10);
INSERT INTO aux_empleados VALUES (2, 'Alonso', 'contable', 346, '1994-12-23', 20400.32, 3.34, 10);
INSERT INTO aux_empleados VALUES (3, 'Santana', 'contable', 345, '1980-09-23', 33670.20, 8.84, 10);
INSERT INTO aux_empleados VALUES (4, 'Gil', 'investigad', 245, '1987-01-23', 23400.32, 5.34, 20);
INSERT INTO aux_empleados VALUES (5, 'Lorenzo', 'investigad', 246, '1992-12-23', 20400.32, 3.34, 20);
INSERT INTO aux_empleados VALUES (6, 'Manteca', 'investigad', 245, '2000-10-23', 33670.20, 8.84, 20);
INSERT INTO aux_empleados VALUES (7, 'Tocino', 'vendedor', 445, '2003-05-23', 23400.32, 5.34, 30);
INSERT INTO aux_empleados VALUES (8, 'Malo', 'vendedor', 446, '1994-06-23', 20400.32, 3.34, 30);
INSERT INTO aux_empleados VALUES (9, 'Salamanca', 'vendedor', 445, '2004-08-23', 33670.20, 8.84, 30);
INSERT INTO aux_empleados VALUES (10, 'Iglesias', 'productor', 145, '1987-01-23', 33400.32, 15.34, 40);
INSERT INTO aux_empleados VALUES (11, 'Martín', 'productor', 146, '2004-12-23', 28400.32, 13.34, 40);
INSERT INTO aux_empleados VALUES (12, 'Soroya', 'productor', 145, '1980-10-23', 43670.20, 18.84, 40);