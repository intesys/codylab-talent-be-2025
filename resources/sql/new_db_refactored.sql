

create table projects
(
    id          bigint default nextval('projects_id_seq') not null
        primary key,
    code        varchar(10),
    name        varchar(255)                                        not null,
    description text,
    start_date  date,
    duration    integer,
    manager_id  bigint
);


create table users
(
    id          bigint default nextval('users_id_seq') not null
        primary key,
    first_name  varchar(255)                                     not null,
    last_name   varchar(255)                                     not null,
    username    varchar(50)                                      not null
        unique,
    email       varchar(255)                                     not null
        constraint users_mail_key
            unique,
    profile     varchar(50),
    daily_hours integer
);

create table tasks
(
    id          bigint default nextval('tasks_id_seq') not null
        primary key,
    project_id  bigint
        references projects,
    code        varchar(10),
    name        varchar(255)                                     not null,
    description text,
    start_date  date,
    duration    integer
);

create table slots
(
    id         bigint default nextval('slots_id_seq') not null
        primary key,
    task_id    bigint
        references tasks,
    start_date date,
    end_date   date,
    duration   integer
);


create table users_tasks
(
    user_id bigint not null
        constraint fk_users_tasks_user
            references users
            on delete cascade,
    task_id bigint not null
        constraint fk_users_tasks_task
            references tasks
            on delete cascade,
    primary key (user_id, task_id)
);

