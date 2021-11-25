CREATE DATABASE cine;
USE cine;

create table categoria(
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    CONSTRAINT pk_categoria PRIMARY KEY (id)
);

create table pelicula(
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(45) NOT NULL,
    descripcion TEXT NOT NULL,
    sinopsis TEXT NOT NULL,
    rating INT NOT NULL,
    fecha_registro DATETIME NOT NULL,
    fecha_actualizacion DATETIME NOT NULL,
    estado TINYINT NOT NULL,
    categoria INT NOT NULL,
    CONSTRAINT pk_pelicula PRIMARY KEY (id),
    CONSTRAINT categoria_fk_pelicula FOREIGN KEY (categoria) REFERENCES categoria (id)
);

--insert 
INSERT INTO `pelicula` (`titulo`, `descripcion`, `sinopsis`, `rating`, `fecha_registro`, `fecha_actualizacion`, `estado`, `categoria`) 
VALUES ('Silent hill', 'Pelicula de terror xd', 'La serie está fuertemente influenciada por el terror psicológico y presenta a 
protagonistas sin cualidades o destrezas físicas fuera de lo normal', '9', '2021-11-24 16:59:05.000000', '2021-11-24 16:59:05.000000', '1', '1'), 
('Up', 'Es una película de animación y aventuras producida por Walt Disney Pictures y Pixar Animation Studios', 'La trama relata las 
aventuras de un viudo de edad avanzada llamado Carl Fredricksen y de un niño escultista cuyo nombre es Russell, quienes viajan a 
Cataratas del Paraíso, en Venezuela, en el interior de una casa flotante suspendida con globos rellenos de helio', '10', '2021-11-24 16:59:05.000000', '2021-11-24 16:59:05.000000', '1', '2');

INSERT INTO `pelicula` (`titulo`, `descripcion`, `sinopsis`, `rating`, `fecha_registro`, `fecha_actualizacion`, `estado`, `categoria`) 
VALUES ('Aterrados', 'Es una película argentina de terror de 2018 escrita, musicalizada y dirigida por Demián Rugna', 'Fuerzas paranormales 
invadieron su barrio en Buenos Aires, y para llegar al fondo del asunto, se enfrentarán a sus peores miedos.', '9', '2021-11-24 17:04:32.000000', 
'2021-11-24 17:04:32.000000', '1', '1'), 
('Fractura', 'Es una película estadounidense de suspenso de 2019, dirigida por Brad Anderson', 'Ray y su mujer viajan con su hija por el país, pero 
en una parada en una gasolinera, la pequeña se tropieza y fractura el brazo. Tras varias horas de espera en el hospital, la niña es atendida y Ray, 
exhausto, se queda dormido. Al despertar, no hay nadie con él, ni noticias de su mujer e hija.', '10', '2021-11-24 17:04:32.000000', 
'2021-11-24 17:04:32.000000', '1', '3');

INSERT INTO `categoria`(`nombre`) VALUES ('terror');
INSERT INTO `categoria`(`nombre`) VALUES ('aventura');
INSERT INTO `categoria`(`nombre`) VALUES ('suspenso');
