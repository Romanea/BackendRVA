DROP TABLE IF EXISTS smer CASCADE;
DROP TABLE IF EXISTS grupa CASCADE;
DROP TABLE IF EXISTS projekat CASCADE;
DROP TABLE IF EXISTS student CASCADE;

DROP SEQUENCE IF EXISTS smer;
DROP SEQUENCE IF EXISTS grupa;
DROP SEQUENCE IF EXISTS projekat;
DROP SEQUENCE IF EXISTS student;

CREATE TABLE smer(
	id integer NOT NULL,
    naziv varchar(100) NOT NULL,
    oznaka varchar(50) NOT NULL
);

CREATE TABLE grupa(
	id integer NOT NULL,
    oznaka VARCHAR(10) NOT NULL,
    smer integer NOT NULL
);

CREATE TABLE projekat(
	id integer NOT NULL,
    naziv varchar(100) NOT NULL,
    oznaka varchar(10) NOT NULL,
    opis varchar(500) NOT NULL

);

CREATE TABLE student(
	id integer NOT NULL,
    ime VARCHAR(50) NOT NULL,
    prezime varchar(50) NOT NULL,
    broj_indeksa varchar(20).
    grupa integer NOT NULL
    projekat integer NOT NULL

);

ALTER TABLE smer ADD CONSTRAINT PK_smer
	PRIMARY KEY(id);
ALTER TABLE grupa ADD CONSTRAINT PK_grupa
	PRIMARY KEY(id);
ALTER TABLE projekat ADD CONSTRAINT PK_projekat
	PRIMARY KEY(id);
ALTER TABLE student ADD CONSTRAINT PK_student
	PRIMARY KEY(id);

ALTER TABLE grupa ADD CONSTRAINT FK_grupa_smer
	FOREIGN KEY (smer) REFERENCES smer (id);
ALTER TABLE student ADD CONSTRAINT FK_student_grupa
	FOREIGN KEY (grupa) REFERENCES grupa (id);
ALTER TABLE student ADD CONSTRAINT FK_student_projekat
	FOREIGN KEY (projekat) REFERENCES projekat (id);

CREATE INDEX IDXFK_grupa_smer
	ON grupa (smer);
CREATE INDEX IDXFK_student_grupa
	ON student (grupa);
CREATE INDEX IDXFK_student_projekat
	ON student (projekat);
	
CREATE SEQUENCE smer_seq
INCREMENT 1;
CREATE SEQUENCE grupa_seq
INCREMENT 1;
CREATE SEQUENCE projekat_seq
INCREMENT 1;
CREATE SEQUENCE student_seq
INCREMENT 1;

