insert into role (id,name) values (1,'KORISNIK');
insert into role (id,name) values (2,'AUTOR');
insert into role (id,name) values (3,'UREDNIK');
insert into role (id,name) values (4,'ADMIN');

insert into scientific_area(id,name,year)values(1,'Veb nauka','2018');
insert into scientific_area(id,name,year)values(2,'Fizika','2018');
insert into scientific_area(id,name,year)values(3,'Hemija','2018');
insert into scientific_area(id,name,year)values(4,'Matematika, racunarske nauke i mehanika','2018');
insert into scientific_area(id,name,year)values(5,'Biologija','2018');
insert into scientific_area(id,name,year)values(6,'Geonauka i astronomija','2018');
insert into scientific_area(id,name,year)values(7,'Medicinske nauke','2018');
insert into scientific_area(id,name,year)values(8,'Elektronika, telekomunikacija i informacione tehnologije','2018');
insert into scientific_area(id,name,year)values(9,'Masinstvo','2018');
insert into scientific_area(id,name,year)values(10,'Saobracaj, urbanizam i gradjevinarstvo','2018');
insert into scientific_area(id,name,year)values(11,'Veb nauka','2017');
insert into scientific_area(id,name,year)values(12,'Fizika','2017');
insert into scientific_area(id,name,year)values(13,'Hemija','2017');
insert into scientific_area(id,name,year)values(14,'Matematika, racunarske nauke i mehanika','2017');
insert into scientific_area(id,name,year)values(15,'Biologija','2017');
insert into scientific_area(id,name,year)values(16,'Geonauka i astronomija','2017');
insert into scientific_area(id,name,year)values(17,'Medicinske nauke','2017');
insert into scientific_area(id,name,year)values(18,'Elektronika, telekomunikacija i informacione tehnologije','2017');
insert into scientific_area(id,name,year)values(19,'Masinstvo','2017');
insert into scientific_area(id,name,year)values(20,'Saobracaj, urbanizam i gradjevinarstvo','2017');
insert into scientific_area(id,name,year)values(21,'Veb nauka','2016');
insert into scientific_area(id,name,year)values(22,'Fizika','2016');
insert into scientific_area(id,name,year)values(23,'Hemija','2016');
insert into scientific_area(id,name,year)values(24,'Matematika, racunarske nauke i mehanika','2016');
insert into scientific_area(id,name,year)values(25,'Biologija','2016');
insert into scientific_area(id,name,year)values(26,'Geonauka i astronomija','2016');
insert into scientific_area(id,name,year)values(27,'Medicinske nauke','2016');
insert into scientific_area(id,name,year)values(28,'Elektronika, telekomunikacija i informacione tehnologije','2016');
insert into scientific_area(id,name,year)values(29,'Masinstvo','2016');
insert into scientific_area(id,name,year)values(30,'Saobracaj, urbanizam i gradjevinarstvo','2016');

-- username == password
insert into user (id,username,role_id,password,email,name,surname) values (1,'vladimir',1,'$2a$10$oP2Vbqb0JvjYB9soV9yL2e6409UfcEkC91KLY9kDhCTNrNxugXfCG','vlada.jova@yahoo.com','Vladimir','Jovicic');
insert into user (id,username,role_id,password,email,name,surname) values (2,'milan',2,'$2a$10$bVikXYQemT0hIWmBCZWueeixC4zrWFjsEljCWhMFr1pjZofbPDnmS','milan2-SEP@milan.com','Milan','Milanovic');
insert into user (id,username,role_id,password,email,name,surname) values (3,'ivan',1,'$2a$10$5Ti6Xe7AdH0Poe5h9z3S3.QjKEAScAoEIXKisSUavEzM9XIP8D1B2','ivan-SEP@ivan.com','Ivan','Ivanovic');
insert into user (id,username,role_id,password,email,name,surname) values (4,'pera',2,'$2a$10$6LSbrt6YghsXu.AvvKEihuHfB/IlwL66QLz38VM5lpCQfb9oHQwie','pera-SEP@pera.com','Petar','Petrovic');
insert into user (id,username,role_id,password,email,name,surname) values (5,'stefan',2,'$2a$10$7wNWpb9ZVaFwjQ/fWfAX8e.Pw9fSJHPGi3V2nmuhT1kMUtUYNmPsm','stefan-SEP@stefan.com','Stefan','Stefanovic');
insert into user (id,username,role_id,password,email,name,surname) values (6,'sima',3,'$2a$10$vqpfUrrnjfS1yZtYMr2sEOCjfKRrpSHk.luTvO5gcsfRwGTju5p1e','sima2-SEP@sima.com','Sima','Simic');
insert into user (id,username,role_id,password,email,name,surname) values (7,'admin',4,'$2a$10$aUUi0OVjyXxCTzTl3jnCiuNKrpR4DW9vYBvaMpHuj76Q4JJwpySBC','vladimirjovicic95@yahoo.com','Admin','Admin');


--insert into magazine(title,issn,key_words,author, scientific_area_id, file_path) values ('Filomat',93545180,'web nauka informatika internet racunar kompjuter','pera',1,'C:\Users\misur');
--insert into magazine(title,issn,key_words,author, scientific_area_id,file_path) values ('Zbornik Medicinski pregled',99258105,'medicina nauka covek zdravlje','stefan',7,'C:\Users');
--insert into magazine(title,issn,key_words,author, scientific_area_id,file_path) values ('Zbornik Matice srpske za prirodne nauke',14509636,'biologija nauka priroda matica svet biljke zivotinje','sima',5,'C:\Users\misur\Desktop');

--insert into article(title,price, user_id, author,magazine_file_path) values ('Detekcija mikotoksina putem razlicitih analitickih metoda',0.01,4,'milan','C:\Users\misur\Desktop');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Vrednovanje ciljeva upravljanja spomenicima prirode primenom smart i smarter metoda',1.00,4,'milan','C:\Users\misur\Desktop');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Negativni joni i njihova uloga u razvoju nauke i tehnologije',3.20,2,'stefan','C:\Users');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Neurorehabilitacija aleksije bez agrafije - prikaz slucaja.',2.50,2,'stefan','C:\Users');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Lecenje plucne embolije',5.00,2,'stefan','C:\Users');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Savremeni principi dijagnostike i lecenja reaktivnog artritisa',1.75,2,'stefan','C:\Users');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Osteoid osteoma kod mladog sportiste',4.20,2,'stefan','C:\Users');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Ucestalost pojave aflatoksina b1 u namirnicama biljnog porijeka',1.20,2,'milan','C:\Users\misur\Desktop');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Primena atr-ftir analize za odredjiivanje fumonizina u kukuruzu',2.00,2,'milan','C:\Users\misur\Desktop');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Akvizicioni parametri trostrukog kvadropola lc/ms odredjiivanja citrinina',0.02,2,'milan','C:\Users\misur\Desktop');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Antioksidativna svojstva sadnica soje inokulisanih sa Trichoderma asperellum',1.09,2,'milan','C:\Users\misur\Desktop');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Hipernearni prstenovi sa defektom distributivnosti',4.20,2,'pera','C:\Users\misur');
--insert into article(title,price, user_id, author,magazine_file_path) values ('Napomena o uzastopnim koeficijentima spiralnih funkcije',0.01,2,'pera','C:\Users\misur');
