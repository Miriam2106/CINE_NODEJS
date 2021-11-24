CREATE DATABASE agencia;
USE agencia;

create table marca(
id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    CONSTRAINT pk_marca PRIMARY KEY (id)
);

create table autos(
id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    matricula CHAR(7) NOT NULL,
    verificacion CHAR(4) NOT NULL,
    fecha_registro DATETIME NOT NULL,
    fecha_actualizacion DATETIME NOT NULL,
    estado TINYINT NOT NULL,
    marca INT NOT NULL,
    CONSTRAINT pk_autos PRIMARY KEY (id),
    CONSTRAINT employee_fk_office FOREIGN KEY (marca) REFERENCES marca (id)
);
