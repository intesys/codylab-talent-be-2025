-- Insert examples for users table
INSERT INTO users (nome, cognome, mail, profilo, orario_giornaliero)
VALUES ('John', 'Doe', 'john.doe@example.com', 'pm', 8.0);

INSERT INTO users (nome, cognome, mail, profilo, orario_giornaliero)
VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'sd', 6.5);

-- Insert examples for "project" table
INSERT INTO projects (codice, nome, descrizione, data_inizio, durata)
VALUES ('PRJ001', 'Project Alpha', 'Description of Project Alpha', '2023-01-01', 30);

INSERT INTO projects (codice, nome, descrizione, data_inizio, durata)
VALUES ('PRJ002', 'Project Beta', 'Description of Project Beta', '2023-02-01', 45);

-- Insert examples for "task" table
INSERT INTO tasks (progetto_id, codice, nome, descrizione, data_inizio, durata)
VALUES (1, 'TASK001', 'Task One', 'Description of Task One', '2023-01-02', 5);

INSERT INTO tasks (progetto_id, codice, nome, descrizione, data_inizio, durata)
VALUES (1, 'TASK002', 'Task Two', 'Description of Task Two', '2023-01-03', 10);

-- Insert examples for "user_task" table
INSERT INTO users_tasks (user_id, task_id)
VALUES (1, 1);

INSERT INTO users_tasks (user_id, task_id)
VALUES (2, 2);

-- Insert examples for "slots" table
INSERT INTO slots (id, task_id, data_inizio, data_fine, durata)
VALUES (1, 1, '2023-10-01', '2023-10-05', 4);

INSERT INTO slots (id, task_id, data_inizio, data_fine, durata)
VALUES (2, 1, '2023-10-06', '2023-10-10', 4);