
DROP SCHEMA IF EXISTS studentskasluzba;
CREATE SCHEMA studentskasluzba DEFAULT CHARACTER SET utf8;

USE studentskasluzba;

	
	DROP TABLE IF EXISTS studenti;
	DROP TABLE IF EXISTS nastavnici;
    DROP TABLE IF EXISTS predmeti;
    DROP TABLE IF EXISTS pohadja;
	
-- KREIRANJE TABELA

	CREATE TABLE studenti (
		student_id  integer AUTO_INCREMENT,
		indeks varchar(20) NOT NULL,
		ime  varchar(20) NOT NULL,
		prezime  varchar(20) NOT NULL,
		grad varchar(20) NOT NULL,
		primary key(student_id) 
	);
	
	CREATE TABLE nastavnici (
		nastavnik_id  integer AUTO_INCREMENT,
		ime  varchar(20) NOT NULL,
		prezime  varchar(20) NOT NULL,
		zvanje varchar(20) NOT NULL,
		primary key(nastavnik_id)
	);
    
    CREATE TABLE predmeti (
		predmet_id integer AUTO_INCREMENT PRIMARY KEY,
		naziv varchar(128)
    );
	
    CREATE TABLE pohadja (
		student_id integer NOT NULL,
        predmet_id integer NOT NULL,
        PRIMARY KEY(student_id, predmet_id),
	
	FOREIGN KEY (student_id)
	                        REFERENCES studenti(student_id)
	                        ON DELETE CASCADE,
	
	FOREIGN KEY (predmet_id)
	                        REFERENCES predmeti(predmet_id)
	                        ON DELETE CASCADE
    );
    
-- UBACIVANJE PODATAKA

-- STUDENTI

  insert into studenti (indeks, ime, prezime, grad) values ('E1', 'Petar', 'Mihajlovic', 'Novi Sad');     -- 1
  insert into studenti (indeks, ime, prezime, grad) values ('E2', 'Dejan', 'Ivanovic', 'Loznica');        -- 2 
  insert into studenti (indeks, ime, prezime, grad) values ('E3', 'Zorana', 'Kovacevic', 'Indjija');      -- 3
  insert into studenti (indeks, ime, prezime, grad) values ('E4', 'Marko', 'Popovic', 'Beograd');         -- 4
  insert into studenti (indeks, ime, prezime, grad) values ('E5', 'Miljenko', 'Cutuk', 'Ruma');           -- 5
  insert into studenti (indeks, ime, prezime, grad) values ('E6', 'Nedeljko', 'Ristanovic', 'Sabac');     -- 6
  insert into studenti (indeks, ime, prezime, grad) values ('E7', 'Milica', 'Zivkovic', 'Zlatibor');      -- 7  
  insert into studenti (indeks, ime, prezime, grad) values ('E8', 'Jelena', 'Lazic', 'Kragujevac');       -- 8
  insert into studenti (indeks, ime, prezime, grad) values ('E9', 'Lidija', 'Skrbac', 'Kovin');           -- 9
  insert into studenti (indeks, ime, prezime, grad) values ('E10', 'Marko', 'Novakovic', 'Kula');         -- 10
  insert into studenti (indeks, ime, prezime, grad) values ('E11', 'Milos', 'Popovic', 'Jagodina');       -- 11

-- NASTAVNICI

  insert into nastavnici (ime, prezime, zvanje) values ('Mara', 'Popovic', 'Docent');             -- 1
  insert into nastavnici (ime, prezime, zvanje) values ('Milan', 'Janjic', 'Docent');             -- 2
  insert into nastavnici (ime, prezime, zvanje) values ('Zeljka', 'Djuric', 'Asistent');          -- 3
  insert into nastavnici (ime, prezime, zvanje) values ('Toplica', 'Milan', 'Doktor');            -- 4
  insert into nastavnici (ime, prezime, zvanje) values ('Milena', 'Nedeljkovic', 'Doktor');       -- 5 
  insert into nastavnici (ime, prezime, zvanje) values ('Vidak', 'Vuckovic', 'Asistent');         -- 6
  insert into nastavnici (ime, prezime, zvanje) values ('Sara', 'Nedeljkovic', 'Asistent');       -- 7
  insert into nastavnici (ime, prezime, zvanje) values ('Sava', 'Kovacevic', 'Docent');           -- 8
  
  -- PREDMETI
  
  insert into predmeti (naziv) values ('Algebra');              -- 1
  insert into predmeti (naziv) values ('Analiza 1');            -- 2
  insert into predmeti (naziv) values ('Diskretna Matematika'); -- 3
  insert into predmeti (naziv) values ('MikroElektronika');     -- 4
  insert into predmeti (naziv) values ('IDEK');                 -- 5
  insert into predmeti (naziv) values ('Programiranje');        -- 6  
  insert into predmeti (naziv) values ('RPEK');                 -- 7
  insert into predmeti (naziv) values ('MIRMAS');               -- 8 
  insert into predmeti (naziv) values ('Fizika');               -- 9
  insert into predmeti (naziv) values ('TEK');                  -- 10
  
  -- POHADJA
  
  insert into pohadja values (1, 1);
  insert into pohadja values (1, 2);
  insert into pohadja values (2, 1),(2,3);
  insert into pohadja values (3, 1);
  insert into pohadja values (4, 1),(4, 3),(4, 5);
  insert into pohadja values (5, 1),(5, 3),(5, 5),(5, 7),(5, 10);
  insert into pohadja values (6, 1),(6, 2),(6, 3),(6, 6),(6, 9);
  insert into pohadja values (7, 4),(7, 5),(7, 6),(7, 7),(7, 8),(7, 10);
  insert into pohadja values (8, 4),(8, 5),(8, 6),(8, 10);
  insert into pohadja values (9, 7),(9, 8),(9, 9),(9, 10);	
	
	
  
	
	