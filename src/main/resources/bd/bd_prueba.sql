create database db_prueba;
use db_prueba;


CREATE TABLE persona (
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    edad INT,
    dni CHAR(8) UNIQUE,
    correo VARCHAR(100) UNIQUE,
    direccion VARCHAR(150),
    estado INT
);

CREATE TABLE tipo_usuario (
    id_tipo_usuario INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL -- Estudiante, Asesor, Jefe Académico, Asesor de experiencia
);


CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    id_persona INT NOT NULL,
    id_tipo_usuario INT NOT NULL,
    estado INT,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuario(id_tipo_usuario)
);



CREATE TABLE comentario (
    id_comentario INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    id_avance INT, -- si lo relacionas con un avance de tesis
    estado INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);


CREATE TABLE avance_tesis (
    id_avance_tesis INT AUTO_INCREMENT PRIMARY KEY,
    id_estudiante INT NOT NULL,
    titulo VARCHAR(100),
    descripcion TEXT,
    archivo_url VARCHAR(255),
    fecha_subida DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado_revision ENUM('pendiente', 'revisado', 'observado') DEFAULT 'pendiente',
    FOREIGN KEY (id_estudiante) REFERENCES usuario(id_usuario)
);

CREATE TABLE alerta (
    id_alerta INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    suscripcion INT, -- notificación automática
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE notificacion (
    id_notificacion INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    leido INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE asignacion (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    nombre_asignacion VARCHAR(100),
    id_estudiante INT,
    id_asesor INT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado INT,
    FOREIGN KEY (id_estudiante) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_asesor) REFERENCES usuario(id_usuario)
);

CREATE TABLE auditoria (
    id_auditoria INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo_operacion VARCHAR(50),
    tabla_afectada VARCHAR(50),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
