-- database: ../DataBase/ProyectGuitarra.sqlite
/*
Autor : Sebastian Oña
Fecha : 15.julio.2k24
Script: Insertando MER
*/

INSERT INTO CatalogoTipo
(Nombre) VALUES
('Sexo')
,('EstadoCivil')
,('LocalidadTipo');

INSERT INTO Catalogo
(IdCatalogoTipo  ,Nombre    ) VALUES
(1,'Masculino')
,(1,'Femenino')
,(1,'Otro')
,(3,'Pais')
,(3,'Ciudad');

INSERT INTO Localidad
(IdLocalidadPadre  ,IdLocalidadEstructura   ,Nombre    ) VALUES
(NULL, 4 ,'Ecuador')
,(NULL,4,'Colombia')
,(NULL,4,'Argentina')
,(1,5,'Quito')
,(1,5,'Guayaquil')
,(2,5,'Medellin')
,(2,5,'Cali')
,(3,5,'Buenos Aires')
,(3,5,'Cordova');


INSERT INTO Persona
(Nombre     ,Apellido      ,Correo     ,IdCatalogoSexo,IdLocalidad) VALUES
('Sebastian', 'Andrade', 'sebastian.a@gmail.com', 1, 4)
,('Martha', 'Rodriguez', 'MarthaRod@gmail.com', 2, 7);

INSERT INTO Usuario
(Usuario , Clave) VALUES
('sebastian', '1234')
,('Martha', '3456');

DELETE FROM Cancion WHERE IdPersona = 0;
