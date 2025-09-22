-- ---------------------------------------------------
-- Creazione tabelle (solo se non esistono)
-- ---------------------------------------------------

CREATE TABLE IF NOT EXISTS users
(
    id          SERIAL PRIMARY KEY,
    first_name  VARCHAR        NOT NULL,
    last_name   VARCHAR        NOT NULL,
    email       VARCHAR UNIQUE NOT NULL,
    profile     VARCHAR        NOT NULL,
    username    VARCHAR UNIQUE NOT NULL,
    daily_hours DECIMAL        NOT NULL
);

CREATE TABLE IF NOT EXISTS projects
(
    id          SERIAL PRIMARY KEY,
    code        VARCHAR UNIQUE NOT NULL,
    name        VARCHAR        NOT NULL,
    description TEXT,
    start_date  DATE           NOT NULL,
    duration    INTEGER        NOT NULL,
    manager_id  INTEGER        REFERENCES users (id) ON DELETE SET NULL,
    state       VARCHAR        NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks
(
    id          SERIAL PRIMARY KEY,
    project_id  INTEGER        NOT NULL REFERENCES projects (id) ON DELETE CASCADE,
    code        VARCHAR UNIQUE NOT NULL,
    name        VARCHAR        NOT NULL,
    description TEXT,
    start_date  DATE           NOT NULL,
    duration    INTEGER        NOT NULL,
    state       VARCHAR        NOT NULL
);

CREATE TABLE IF NOT EXISTS slots
(
    id         SERIAL PRIMARY KEY,
    task_id    INTEGER NOT NULL REFERENCES tasks (id) ON DELETE CASCADE,
    start_date DATE    NOT NULL,
    end_date   DATE    NOT NULL,
    duration   INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS users_tasks
(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    task_id INTEGER NOT NULL REFERENCES tasks (id) ON DELETE CASCADE
);

-- ---------------------------------------------------
-- Popolamento dati iniziali
-- ---------------------------------------------------

INSERT INTO users (first_name, last_name, email, profile, username, daily_hours)
VALUES ('John', 'Smith', 'john.smith@email.com', 'DEVELOPER', 'jsmith', 8),
       ('Emma', 'Johnson', 'emma.johnson@email.com', 'PROJECT_MANAGER', 'ejohnson', 8),
       ('Michael', 'Brown', 'michael.brown@email.com', 'ANALYST', 'mbrown', 8),
       ('Sarah', 'Davis', 'sarah.davis@email.com', 'DEVELOPER', 'sdavis', 6)
ON CONFLICT (email) DO NOTHING;

INSERT INTO projects (code, name, description, start_date, duration, manager_id, state)
VALUES ('PROJ001', 'ERP System', 'Development of new ERP system', '2025-01-01', 180, 2, 'OPEN'),
       ('PROJ002', 'Mobile App', 'Corporate mobile application', '2025-03-01', 90, 2, 'OPEN'),
       ('PROJ003', 'DB Migration', 'Legacy database migration', '2024-12-01', 45, 2, 'CLOSED')
ON CONFLICT (code) DO NOTHING;

INSERT INTO tasks (project_id, code, name, description, start_date, duration, state)
VALUES (1, 'TASK001', 'Requirements Analysis', 'Detailed ERP requirements analysis', '2025-01-01', 30, 'IN_PROGRESS'),
       (1, 'TASK002', 'Backend Development', 'ERP backend implementation', '2025-02-01', 60, 'IN_PROGRESS'),
       (2, 'TASK003', 'UI Design', 'Mobile app interface design', '2025-03-01', 20, 'IN_PROGRESS'),
       (2, 'TASK004', 'App Development', 'Mobile application development', '2025-03-21', 45, 'IN_PROGRESS'),
       (3, 'TASK005', 'DB Analysis', 'Database structure analysis', '2024-12-01', 15, 'COMPLETED'),
       (3, 'TASK006', 'Data Migration', 'Data transfer to new system', '2024-12-16', 30, 'COMPLETED')
ON CONFLICT (code) DO NOTHING;

INSERT INTO users_tasks (user_id, task_id)
VALUES (1, 2),
       (3, 1),
       (4, 3),
       (1, 4),
       (3, 5),
       (1, 6)
ON CONFLICT DO NOTHING;

INSERT INTO slots (task_id, start_date, end_date, duration)
VALUES (1, '2025-01-01', '2025-01-31', 30),
       (2, '2025-02-01', '2025-04-01', 60),
       (3, '2025-03-01', '2025-03-20', 20),
       (4, '2025-03-21', '2025-05-05', 45),
       (5, '2024-12-01', '2024-12-15', 15),
       (6, '2024-12-16', '2025-01-15', 30)
ON CONFLICT DO NOTHING;
