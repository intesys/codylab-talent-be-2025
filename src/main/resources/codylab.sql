create table formageometrica(
                      id identity primary key,
                      tipo varchar(50) not null,
                      lato1 double not null,
                      lato2 double
);

insert into formageometrica (id, tipo, lato1, lato2) values (1, 'quadrato', 4, null);
insert into formageometrica (id, tipo, lato1, lato2) values (2, 'rettangolo', 4, 6);
insert into formageometrica (id, tipo, lato1, lato2) values (1, 'cerchio', 4, null);
insert into formageometrica (id, tipo, lato1, lato2) values (2, 'rettangolo', 5, 10);
