CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR NOT NULL,
                       last_name VARCHAR NOT NULL,
                       email VARCHAR UNIQUE NOT NULL,
                       profile VARCHAR NOT NULL,
                       username VARCHAR UNIQUE NOT NULL,
                       daily_hours DECIMAL NOT NULL
);

CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          code VARCHAR UNIQUE NOT NULL,
                          name VARCHAR NOT NULL,
                          description TEXT,
                          start_date DATE NOT NULL,
                          duration INTEGER NOT NULL,
                          manager_id INTEGER REFERENCES users(id) ON DELETE SET NULL,
                          state VARCHAR NOT NULL
);

CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       project_id INTEGER NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
                       code VARCHAR UNIQUE NOT NULL,
                       name VARCHAR NOT NULL,
                       description TEXT,
                       start_date DATE NOT NULL,
                       duration INTEGER NOT NULL,
                       state VARCHAR NOT NULL
);

CREATE TABLE slots (
                       id SERIAL PRIMARY KEY,
                       task_id INTEGER NOT NULL REFERENCES tasks(id) ON DELETE CASCADE,
                       start_date DATE NOT NULL,
                       end_date DATE NOT NULL,
                       duration INTEGER NOT NULL
);

CREATE TABLE users_tasks (
                             id SERIAL PRIMARY KEY,
                             user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                             task_id INTEGER NOT NULL REFERENCES tasks(id) ON DELETE CASCADE
);