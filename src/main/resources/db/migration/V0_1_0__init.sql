CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

CREATE TYPE person_status AS ENUM('NEWCOMER', 'HUNTER', 'ACOLYTE', 'AGENT', 'WANTED', 'ENEMY', 'NOT_ACTIVE', 'DEAD');

CREATE TYPE party_status AS ENUM('STALKER', 'MILITARY', 'DUTY', 'FREEDOM', 'BANDIT', 'MERCURY', 'CLEAR_SKY', 'MONOLITH', 'RENEGADE', 'SMUGGLER', 'SCIENTIST');

CREATE TYPE task_status AS ENUM('IN_PROGRESS', 'PROLONG', 'FAIL', 'CANCEL');

CREATE TABLE IF NOT EXISTS person
(
    id            uuid          not null
        constraint person_pkey
            primary key,
    steam_id       bigint        not null,
    name          varchar       not null,
    nickname      varchar,
    description   varchar       not null,
    person_status person_status not null,
    party_status  party_status  not null,
    print_link    varchar       not null,
    cash_account  bigint,
    created       timestamp     not null,
    updated       timestamp     not null
);

CREATE TABLE IF NOT EXISTS task
(
    id          uuid        not null
        constraint task_pkey
            primary key,
    name        varchar     not null,
    description varchar     not null,
    start_date  timestamp   not null,
    end_date    timestamp   not null,
    task_status task_status not null,
    person_id   uuid        not null
        constraint task_person_fk
            references person,
    created     timestamp   not null,
    updated     timestamp   not null
);