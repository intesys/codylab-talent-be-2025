
-- In database conventions, it is generally recommended to use lowercase for table names. This is because many database
-- systems (like PostgreSQL) treat table names as case-insensitive unless explicitly quoted. Using a lowercase avoids potential
-- issues with case sensitivity and ensures consistency.
-- Best Practice:
-- Use lowercase for table names (e.g., user, project, task).
-- Use snake_case for multi-word table names (e.g., user_task).

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       nome VARCHAR NOT NULL,
                       cognome VARCHAR NOT NULL,
                       mail VARCHAR UNIQUE NOT NULL,
                       profilo VARCHAR NOT NULL,
                       orario_giornaliero DECIMAL NOT NULL
);

CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          codice VARCHAR UNIQUE NOT NULL,
                          nome VARCHAR NOT NULL,
                          descrizione VARCHAR,
                          data_inizio DATE NOT NULL,
                          durata INTEGER NOT NULL
);
CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       progetto_id INTEGER NOT NULL REFERENCES projects(id),
                       codice VARCHAR UNIQUE NOT NULL,
                       nome VARCHAR NOT NULL,
                       descrizione VARCHAR,
                       data_inizio DATE NOT NULL,
                       durata INTEGER NOT NULL
);

CREATE TABLE users_tasks (
                             id SERIAL PRIMARY KEY,
                             user_id INTEGER NOT NULL REFERENCES users(id),
                             task_id INTEGER NOT NULL REFERENCES tasks(id)
);