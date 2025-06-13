INSERT INTO users (nome, cognome, mail, profilo, orario_giornaliero)
VALUES
    ('Mario', 'Rossi', 'mario.rossi@example.com', 'developer', 8.0),
    ('Luca', 'Bianchi', 'luca.bianchi@example.com', 'designer', 7.5),
    ('Anna', 'Verdi', 'anna.verdi@example.com', 'project_manager', 8.0);

INSERT INTO projects (codice, nome, descrizione, data_inizio, durata)
VALUES
    ('PRJ001', 'Sistema di Gestione', 'Sviluppo di un sistema di gestione interno', '2025-06-01', 60),
    ('PRJ002', 'Applicazione Mobile', NULL, '2025-07-01', 45);


-- Assuming project IDs are 1 and 2
INSERT INTO tasks (progetto_id, codice, nome, descrizione, data_inizio, durata)
VALUES
    (1, 'TSK001', 'Analisi Requisiti', 'Analisi dettagliata dei requisiti', '2025-06-02', 10),
    (1, 'TSK002', 'Sviluppo Backend', NULL, '2025-06-12', 30),
    (2, 'TSK003', 'UI Design', 'Creazione interfaccia utente', '2025-07-02', 15);


INSERT INTO users_tasks (user_id, task_id)
VALUES
    (1, 1),  -- Mario assigned to Analisi Requisiti
    (1, 2),  -- Mario assigned to Sviluppo Backend
    (2, 3),  -- Luca assigned to UI Design
    (3, 1),  -- Anna also assigned to Analisi Requisiti
    (3, 2);  -- Anna also assigned to Sviluppo Backend
