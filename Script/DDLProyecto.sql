-- database: ../DataBase/ProyectGuitarra.sqlite
/*
Autor : Sebastian Oña
Fecha : 15.julio.2k24
Script: Insertando MER
*/

DROP TABLE IF EXISTS SOCatalogoTipo;
DROP TABLE IF EXISTS SOCatalogo;
DROP TABLE IF EXISTS SOLocalidad;
DROP TABLE IF EXISTS SOPersona;
DROP TABLE IF EXISTS SOUsuario;
DROP TABLE IF EXISTS SOCancion;

CREATE TABLE SOCatalogoTipo (
   IdCatalogoTipo   INTEGER NOT NULL PRIMARY KEY autoincrement 
  ,Nombre           VARCHAR(30) NOT NULL UNIQUE

  ,Estado           VARCHAR(1) NOT NULL DEFAULT('A')
  ,FechaCreacion    DATETIME DEFAULT(datetime('now','localtime'))
  ,FechaModifica    DATETIME
);

CREATE TABLE SOCatalogo(
  IdCatalogo         INTEGER NOT NULL PRIMARY KEY autoincrement 
  ,IdCatalogoTipo    INTEGER NOT NULL REFERENCES SOCatalogoTipo(IdCatalogoTipo)
  ,Nombre            VARCHAR(18) NOT NULL UNIQUE

  ,Estado           VARCHAR(1) NOT NULL DEFAULT('A')
  ,FechaCreacion    DATETIME DEFAULT(datetime('now','localtime'))
  ,FechaModifica    DATETIME
);

CREATE TABLE SOLocalidad(
    IdLocalidad             INTEGER NOT NULL PRIMARY KEY autoincrement
    ,IdLocalidadPadre       INTEGER REFERENCES SOLocalidad(IdLocalidad)
    ,IdLocalidadEstructura  INTEGER REFERENCES SOCatalogo(IdCatalogo)
    ,Nombre                 VARCHAR(18) NOT NULL 

    ,Estado                 VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCreacion          DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica          DATETIME
);

CREATE TABLE SOPersona(
    IdPersona                   INTEGER NOT NULL PRIMARY KEY autoincrement
    ,Nombre                     VARCHAR(18) NOT NULL
    ,Apellido                   VARCHAR(18) NOT NULL
    ,Correo                     VARCHAR(30) UNIQUE NOT NULL
    ,IdCatalogoSexo             INTEGER REFERENCES SOCatalogo(IdCatalogo)
    ,IdLocalidad                INTEGER REFERENCES SOLocalidad(IdLocalidad)

    ,Estado                     VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCreacion              DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica              DATETIME
);

CREATE TABLE SOUsuario(
    IdUsuario                   INTEGER 
    ,Usuario                  VARCHAR(18) NOT NULL UNIQUE
    ,Clave                      VARCHAR(18) NOT NULL UNIQUE

    ,Estado                     VARCHAR(1) NOT NULL DEFAULT('A')
    ,FechaCreacion              DATETIME DEFAULT(datetime('now','localtime'))
    ,FechaModifica              DATETIME
    ,PRIMARY KEY (IdUsuario)
    ,FOREIGN KEY (IdUsuario)    REFERENCES SOPersona(IdPersona)
);

CREATE TABLE SOCancion(
  IdCancion               INTEGER NOT NULL PRIMARY KEY autoincrement
  ,IdPersona              INTEGER REFERENCES SOPersona(IdPersona)
  ,Nombre                 VARCHAR(18) NOT NULL
  ,Cancion                TEXT NOT NULL
  
  ,Estado                 VARCHAR(1) NOT NULL DEFAULT('A')
  ,FechaCreacion          DATETIME DEFAULT(datetime('now','localtime'))
  ,FechaModifica          DATETIME
);
