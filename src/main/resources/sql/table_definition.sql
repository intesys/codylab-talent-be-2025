-- Table: user
CREATE TABLE users (
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR NOT NULL,
                        cognome VARCHAR NOT NULL,
                        mail VARCHAR UNIQUE NOT NULL,
                        profilo VARCHAR NOT NULL,
                        orario_giornaliero DECIMAL NOT NULL
);

-- Table: project
CREATE TABLE projects (
                         id SERIAL PRIMARY KEY,
                         codice VARCHAR UNIQUE NOT NULL,
                         nome VARCHAR NOT NULL,
                         descrizione VARCHAR,
                         data_inizio DATE NOT NULL,
                         durata INTEGER NOT NULL
);

-- Table: task
CREATE TABLE tasks (
                      id SERIAL PRIMARY KEY,
                      progetto_id INTEGER NOT NULL REFERENCES projects(id),
                      codice VARCHAR UNIQUE NOT NULL,
                      nome VARCHAR NOT NULL,
                      descrizione VARCHAR,
                      data_inizio DATE NOT NULL,
                      durata INTEGER NOT NULL
);

-- Table: user_task
CREATE TABLE users_tasks (
                           id SERIAL PRIMARY KEY,
                           user_id INTEGER NOT NULL REFERENCES users(id),
                           task_id INTEGER NOT NULL REFERENCES tasks(id)
);
