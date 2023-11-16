--
-- Base de datos: 'ejemplo'
--
-- --------------------------------------------------------
CREATE DATABASE IF NOT EXISTS ejemplo;
USE ejemplo;

--
-- Estructura de tabla para la tabla 'departamentos'
--

CREATE TABLE IF NOT EXISTS 'departamentos' (
  'dept_no' tinyint(2) NOT NULL,
  'dnombre' varchar(15) CHARACTER SET utf8 NOT NULL,
  'loc' varchar(15) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla 'empleados'
--

CREATE TABLE IF NOT EXISTS 'empleados' (
  'emp_no' smallint(4) unsigned NOT NULL,
  'apellido' varchar(10) CHARACTER SET utf8 NOT NULL,
  'oficio' varchar(10) CHARACTER SET utf8 NOT NULL,
  'dir' smallint(6) NOT NULL,
  'fecha_alt' date NOT NULL,
  'salario' float(9,2) NOT NULL,
  'comision' float(6,2) NOT NULL,
  'dept_no' tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indices de la tabla 'departamentos'
--
ALTER TABLE 'departamentos'
 ADD PRIMARY KEY ('dept_no');

--
-- Indices de la tabla 'empleados'
--
ALTER TABLE 'empleados'
 ADD PRIMARY KEY ('emp_no'), ADD KEY 'dept_no' ('dept_no');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla 'empleados'
--
ALTER TABLE 'empleados'
ADD CONSTRAINT 'empleados_ibfk_1' FOREIGN KEY ('dept_no') REFERENCES 'departamentos' ('dept_no') ON UPDATE CASCADE;

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

create user ejemplo@localhost identified	by	'ejemplo';
grant all on ejemplo.* TO ejemplo@localhost;