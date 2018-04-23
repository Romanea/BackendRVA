INSERT INTO "smer"("id", "naziv", "oznaka") VALUES
(nextval('smer_seq'), 'Inzenjerstvo informacionih sistema', 'IT'),
(nextval('smer_seq'), 'Industrijski menadzment', 'IM'),
(nextval('smer_seq'), 'Industrijsko inzenjerstvo', 'II'),
(nextval('smer_seq'), 'Mehatronika', 'MH');

INSERT INTO "grupa"("id", "oznaka", "smer") VALUES
(nextval('grupa_seq'), 'I', 1),
(nextval('grupa_seq'), 'II', 1),
(nextval('grupa_seq'), 'III', 1),
(nextval('grupa_seq'), 'I', 2),
(nextval('grupa_seq'), 'II', 2),
(nextval('grupa_seq'), 'III', 2),
(nextval('grupa_seq'), 'I', 3),
(nextval('grupa_seq'), 'II', 3),
(nextval('grupa_seq'), 'III', 3),
(nextval('grupa_seq'), 'I', 4),
(nextval('grupa_seq'), 'II', 4),
(nextval('grupa_seq'), 'III', 4);

INSERT INTO "projekat"("id", "naziv", "oznaka", "opis") VALUES
(nextval('projekat_seq'), 'Zadatak_1', 'rva', 'neki opis'),
(nextval('projekat_seq'), 'Zadatak_2', 'rva', 'neki opis'),
(nextval('projekat_seq'), 'Zadatak_3', 'rva', 'neki opis'),
(nextval('projekat_seq'), 'Zadatak_4', 'rva', 'neki opis'),
(nextval('projekat_seq'), 'Zadatak_5', 'rva', 'neki opis'),
(nextval('projekat_seq'), 'Zadatak_6', 'rva', 'neki opis');

INSERT INTO "student"("id", "ime", "prezime", "broj_indeksa", "grupa", "projekat") VALUES
(nextval('student_seq'), 'Luka', 'Ilic', 'IT28-2015', 1, 1),
(nextval('student_seq'), 'Andrea', 'Romanic', 'IT20-2015', 1, 2),
(nextval('student_seq'), 'Jovana', 'Savic', 'IT18-2015', 2, 3),
(nextval('student_seq'), 'Ana', 'Gligoric', 'IT14-2015', 2, 4),
(nextval('student_seq'), 'Milos', 'Romanic', 'IT36-2015', 3, 5),
(nextval('student_seq'), 'Filip', 'Veselinovic', 'IT16-2016', 3, 6),
(nextval('student_seq'), 'Djordje', 'Djeric', 'IM22-2017', 4, 1),
(nextval('student_seq'), 'Danilo', 'Pavkovic', 'IM66-2018', 4, 2),
(nextval('student_seq'), 'Stefan', 'Ilic', 'IM28-2015', 5, 3),
(nextval('student_seq'), 'Luka', 'Zivanovic', 'IM28-2015', 5, 4),
(nextval('student_seq'), 'Zejn', 'Shaman', 'IM28-2015', 6, 5),
(nextval('student_seq'), 'Undrija', 'Mocni', 'IM20-2015', 6, 6),
(nextval('student_seq'), 'Sara', 'Petrovic', 'II18-2015', 7, 1),
(nextval('student_seq'), 'Nikola', 'Stosic', 'II14-2015', 7, 2),
(nextval('student_seq'), 'Jovana', 'Tomic', 'II36-2015', 8, 3),
(nextval('student_seq'), 'Natasa', 'Martinovic', 'II16-2016', 8, 4),
(nextval('student_seq'), 'Rada', 'Sandic', 'II22-2017', 9, 5),
(nextval('student_seq'), 'Mladen', 'Kalinic', 'II66-2018', 9, 6),
(nextval('student_seq'), 'Aleksandra', 'Petrovic', 'MH28-2015', 10, 1),
(nextval('student_seq'), 'Laura', 'Nikolic', 'MH28-2015', 10, 2),
(nextval('student_seq'), 'Marko', 'Petrovic', 'MH22-2017', 11, 3),
(nextval('student_seq'), 'Nikola', 'Nikolic', 'MH66-2018', 11, 4),
(nextval('student_seq'), 'Stefan', 'Markovic', 'MH28-2015', 12, 5),
(nextval('student_seq'), 'Luka', 'Savic', 'MH28-2015', 12, 6);
