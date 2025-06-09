
-- In database conventions, it is generally recommended to use lowercase for table names. This is because many database
-- systems (like PostgreSQL) treat table names as case-insensitive unless explicitly quoted. Using lowercase avoids potential
-- issues with case sensitivity and ensures consistency.
-- Best Practice:
-- Use lowercase for table names (e.g., user, project, task).
-- Use snake_case for multi-word table names (e.g., user_task).

-- It is generally recommended to use singular names for table names. This is because a table represents a single
-- entity or object type (e.g., user, project, task), and singular names align better with object-oriented
-- programming principles.
-- Best Practice:
-- Use singular names for tables (e.g., user, project, task).

CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR NOT NULL,
                        cognome VARCHAR NOT NULL,
                        mail VARCHAR UNIQUE NOT NULL,
                        profilo VARCHAR NOT NULL,
                        orario_giornaliero DECIMAL NOT NULL
);

CREATE TABLE project (
                         id SERIAL PRIMARY KEY,
                         codice VARCHAR UNIQUE NOT NULL,
                         nome VARCHAR NOT NULL,
                         descrizione VARCHAR,
                         data_inizio DATE NOT NULL,
                         durata INTEGER NOT NULL
);
CREATE TABLE task (
                      id SERIAL PRIMARY KEY,
                      progetto_id INTEGER NOT NULL REFERENCES project(id),
                      codice VARCHAR UNIQUE NOT NULL,
                      nome VARCHAR NOT NULL,
                      descrizione VARCHAR,
                      data_inizio DATE NOT NULL,
                      durata INTEGER NOT NULL
);

CREATE TABLE user_task (
                           id SERIAL PRIMARY KEY,
                           user_id INTEGER NOT NULL REFERENCES "user"(id),
                           task_id INTEGER NOT NULL REFERENCES task(id)
);
