-- Questo file contiene le istruzioni SQL per creare e gestire una tabella di forme geometriche.
create table formageometrica(
                      id identity primary key,
                      tipo varchar(50) not null,
                      lato1 double not null,
                      lato2 double
);


-- Inseriamo alcune forme geometriche
insert into formageometrica (id, tipo, lato1, lato2) values (1, 'quadrato', 4, null);
insert into formageometrica (id, tipo, lato1, lato2) values (2, 'rettangolo', 4, 6);
insert into formageometrica (id, tipo, lato1, lato2) values (3, 'cerchio', 4, null);
insert into formageometrica (id, tipo, lato1, lato2) values (4, 'rettangolo', 5, 10);

-- Modifichiamo i lati di un quadrato e di un rettangolo
update formageometrica set lato1 = 6 where id = 1;
update formageometrica set lato1 = 8 where id = 2;

-- Cancelliamo un rettangolo
delete from formageometrica where id = 4;

-- Esempio di SQL Injection
select id, tipo, lato1, lato2 from formageometrica where id = 4 or 1 = 1;