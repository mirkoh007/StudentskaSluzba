
DROP SCHEMA IF EXISTS studentskasluzba;
CREATE SCHEMA studentskasluzba DEFAULT CHARACTER SET utf8;

use studentskasluzba;

	
	drop table if exists studenti;
	drop table if exists nastavnici;
	

-- KREIRANJE TABELA

	create table studenti (
	student_id  integer AUTO_INCREMENT,
	indeks varchar(20) NOT NULL,
	ime  varchar(20) NOT NULL,
	prezime  varchar(20) NOT NULL,
	grad varchar(20) NOT NULL,
	primary key(student_id) 
	);
	
	create table nastavnici (
	nastavnik_id  integer AUTO_INCREMENT,
	ime  varchar(20) NOT NULL,
	prezime  varchar(20) NOT NULL,
	zvanje varchar(20) NOT NULL,
	primary key(nastavnik_id)
	);
	
	
	
	
-- UBACIVANJE PODATAKA

-- STUDENTI

  insert into studenti (indeks, ime, prezime, grad) values ('E1', 'Petar', 'Mihajlovic', 'Novi Sad');     -- 1
  insert into studenti (indeks, ime, prezime, grad) values ('E2', 'Dejan', 'Ivanovic', 'Loznica');        -- 2 
  insert into studenti (indeks, ime, prezime, grad) values ('E3', 'Zorana', 'Kovacevic', 'Indjija');       -- 3
  insert into studenti (indeks, ime, prezime, grad) values ('E4', 'Marko', 'Popovic', 'Beograd');        -- 4
  insert into studenti (indeks, ime, prezime, grad) values ('E5', 'Miljenko', 'Cutuk', 'Ruma');        -- 5
  insert into studenti (indeks, ime, prezime, grad) values ('E6', 'Nedeljko', 'Ristanovic', 'Sabac');        -- 6
  insert into studenti (indeks, ime, prezime, grad) values ('E7', 'Milica', 'Zivkovic', 'Zlatibor');        -- 7  
  insert into studenti (indeks, ime, prezime, grad) values ('E8', 'Jelena', 'Lazic', 'Kragujevac');        -- 8
  insert into studenti (indeks, ime, prezime, grad) values ('E9', 'Lidija', 'Skrbac', 'Kovin');        -- 9

-- NASTAVNICI

  insert into nastavnici (ime, prezime, zvanje) values ('Mara', 'Popovic', 'Docent');            -- 1
  insert into nastavnici (ime, prezime, zvanje) values ('Milan', 'Janjic', 'Docent');             -- 2
  insert into nastavnici (ime, prezime, zvanje) values ('Zeljka', 'Djuric', 'Asistent');          -- 3
  insert into nastavnici (ime, prezime, zvanje) values ('Toplica', 'Milan', 'Doktor');            -- 4
  insert into nastavnici (ime, prezime, zvanje) values ('Milena', 'Nedeljkovic', 'Doktor');        -- 5 
  insert into nastavnici (ime, prezime, zvanje) values ('Vidak', 'Vuckovic', 'Asistent');         -- 6
  insert into nastavnici (ime, prezime, zvanje) values ('Sara', 'Nedeljkovic', 'Asistent');      -- 7
  insert into nastavnici (ime, prezime, zvanje) values ('Sava', 'Kovacevic', 'Docent');           -- 8
	
	
	
  
	
	