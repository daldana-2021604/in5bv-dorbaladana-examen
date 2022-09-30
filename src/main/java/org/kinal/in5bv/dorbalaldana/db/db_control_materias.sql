DROP DATABASE IF EXISTS db_control_materia_in5bv;
CREATE DATABASE IF NOT EXISTS db_control_materia_in5bv;

USE db_control_materia_in5bv;

DROP TABLE IF EXISTS materia;
CREATE TABLE IF NOT EXISTS materia(
	id_materia INT NOT NULL AUTO_INCREMENT,
    nombre_materia VARCHAR(45) NOT NULL,
    ciclo_escolar YEAR NULL,
	horario_inicio TIME NULL,
    horario_final TIME NULL,
    catedratico VARCHAR(45) NOT NULL,
    salon VARCHAR(45) NOT NULL,
    cupo_maximo INT NULL,
    cupo_minimo INT NULL,
    
    PRIMARY KEY (id_materia)
); 

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Matemáticas","2022","00:00:00","00:00:00","Fracisco Noj","C-38",10,5);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Sociales","1980","00:00:00","00:00:00","Rafael Gutierrez","C-37",20,15);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Estadistica","2002","00:00:00","00:00:00","Juan Rivas","C-37",20,15);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Etica","2019","00:00:00","00:00:00","Isidoro Esquite","C-34",50,30);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Quimica","1999","00:00:00","00:00:00","Hetor arreaza","C-34",50,30);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Taller","2012","00:00:00","00:00:00","Jorge Perez","C-29",35,10);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Psicologia","2015","00:00:00","00:00:00","Hedgar Cabrera","C-39",34,11);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Fisica Fundamental","2021","00:00:00","00:00:00","Orcar Bernard","C-39",34,11);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Biologia","2016","00:00:00","00:00:00","Fracisco Noj","C-31",36,18);

INSERT INTO materia(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo) 
VALUES ("Educacion fisica","2019","00:00:00","00:00:00","Hector Arreaza","C-01",18,8);


