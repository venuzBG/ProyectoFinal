-- database: ../DataBase/ProyectGuitarra.sqlite
/*
Autor : Sebastian Oña
Fecha : 15.julio.2k24
Script: Insertando MER
*/

DROP TABLE IF EXISTS CatalogoTipo;
DROP TABLE IF EXISTS Catalogo;
DROP TABLE IF EXISTS Localidad;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Cancion;

CREATE TABLE catalogoTipo (
   IdCatalogoTipo   INTEGER NOT NULL PRIMARY KEY autoincrement 
  ,Nombre           VARCHAR(30) NOT NULL UNIQUE

  ,Estado           VARCHAR(1) NOT NULL DEFAULT('A')
  ,FechaCreacion    DATETIME DEFAULT(datetime('now','localtime'))
  ,FechaModifica    DATETIME
);

CREATE TABLE Catalogo(
  IdCatalogo         INTEGER NOT NULL PRIMARY KEY autoincrement 
  ,IdCatalogoTipo    INTEGER NOT NULL REFERENCES CatalogoTipo(IdCatalogoTipo)
  ,Nombre            VARCHAR(18) NOT NULL UNIQUE

  ,Estado           VARCHAR(1) NOT NULL DEFAULT('A')
  ,FechaCreacion    DATETIME DEFAULT(datetime('now','localtime'))
  ,FechaModifica    DATETIME
);

CREATE TABLE Localidad(
    IdLocalidad             INTEGER NOT NULL PRIMARY KEY autoincrement
    ,IdLocalidadPadre       INTEGER REFERENCES Localidad(IdLocalidad)
    ,IdLocalidadEstructura  INTEGER REFERENCES Catalogo(IdCatalogo)
    ,Nombre                 VARCHAR(18) NOT NULL 

    ,Estado                 VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCreacion          DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica          DATETIME
);

CREATE TABLE Persona(
    IdPersona                   INTEGER NOT NULL PRIMARY KEY autoincrement
    ,Nombre                     VARCHAR(18) NOT NULL
    ,Apellido                   VARCHAR(18) NOT NULL
    ,Correo                     VARCHAR(30) UNIQUE NOT NULL
    ,IdCatalogoSexo             INTEGER REFERENCES Catalogo(IdCatalogo)
    ,IdLocalidad                INTEGER REFERENCES Localidad(IdLocalidad)

    ,Estado                     VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCreacion              DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica              DATETIME
);

CREATE TABLE Usuario(
    IdUsuario               INTEGER 
    ,Usuario                VARCHAR(18) NOT NULL UNIQUE
    ,Clave                  VARCHAR(18) NOT NULL UNIQUE

    ,Estado                 VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCreacion          DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica          DATETIME
    ,PRIMARY KEY (IdUsuario)
    ,FOREIGN KEY (IdUsuario) REFERENCES Persona(IdPersona)
);

CREATE TABLE Cancion(
  IdCancion               INTEGER NOT NULL PRIMARY KEY autoincrement
  ,IdPersona              INTEGER REFERENCES Persona(IdPersona)
  ,Nombre                 VARCHAR(30) NOT NULL
  ,Cancion                TEXT NOT NULL
  
  ,Estado                 VARCHAR(1) NOT NULL DEFAULT('A')
  ,FechaCreacion          DATETIME DEFAULT(datetime('now','localtime'))
  ,FechaModifica          DATETIME
);
