DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS (
    NUMERO-MATRICULA VARCHAR(20) PRIMARY KEY,
    NOMBRE VARCHAR(50) NOT NULL ,
    APELLIDO VARCHAR(50) NOT NULL ;

    INSERT INTO ODONTOLOGOS
        VALUES(DEFAULT, 645, ANDREA, MONSALVE),
              (DEFAULT, 748, lEINY, PUELLO)
);