-- Questo file contiene le istruzioni SQL per creare e gestire una tabella di forme geometriche.
create table formageometrica(
                      id identity primary key,
                      tipo varchar(50) not null,
                      lato1 double not null,
                      lato2 double
);

create table users(
                      id  serial primary key,
                      firstName varchar(50) not null,
                      lastName varchar(50) not null,
                      email varchar(50) not null,
                      profile varchar(50) not null,
--                       workingHours varchar(50) not null
);

-- Inseriamo alcune forme geometriche
insert into formageometrica (tipo, lato1, lato2) values ('quadrato', 4, null);
insert into formageometrica (tipo, lato1, lato2) values ('rettangolo', 4, 6);
insert into formageometrica (tipo, lato1, lato2) values ('cerchio', 4, null);
insert into formageometrica (tipo, lato1, lato2) values ('rettangolo', 5, 10);
insert into formageometrica (tipo, lato1, lato2) values ('quadrato', 5, null);

-- Modifichiamo i lati di un quadrato e di un rettangolo
update formageometrica set lato1 = 6 where id = 1;
update formageometrica set lato1 = 8 where id = 2;

-- Cancelliamo un rettangolo
delete from formageometrica where id = 4;

-- Esempio di SQL Injection
select id, tipo, lato1, lato2 from formageometrica where id = 4 or 1 = 1;


-- Drop tabella
drop table formageometrica;