-- Questo file contiene le istruzioni SQL per creare e gestire una tabella di forme geometriche.
create table formageometrica(
                      id identity primary key,
                      tipo varchar(50) not null,
                      lato1 double not null,
                      lato2 double
);


create table utenti(
    id  varchar(50),
    pw varchar(255) not null
);

insert into utenti (id, pw) values ( 'Gaetano', '$2a$10$32P1DoAT20XTmkJXgvObW.jLoDGwKolgJX.BqRN3FFsiyPPDUejiq' );

-- Inseriamo alcune forme geometriche
insert into formageometrica (tipo, lato1, lato2) values ('quadrato', 4, null);
insert into formageometrica (tipo, lato1, lato2) values ('rettangolo', 4, 6);
insert into formageometrica (tipo, lato1, lato2) values ('cerchio', 4, null);
insert into formageometrica (tipo, lato1, lato2) values ('rettangolo', 33, 11);

-- Modifichiamo i lati di un quadrato e di un rettangolo
update formageometrica set lato1 = 6 where id = 1;
update formageometrica set lato1 = 8 where id = 2;

-- Cancelliamo un rettangolo
delete from formageometrica where id = 4;

-- Esempio di SQL Injection
select id, tipo, lato1, lato2 from formageometrica where id = 4 or 1 = 1;

-- Drop delle tabelle esistenti e creazione di nuovi valori modificati (Senza ID)
DROP TABLE IF EXISTS formageometrica;

