CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

CREATE TYPE stalker_status AS ENUM
    ('NEWCOMER', 'HUNTER', 'ACOLYTE', 'AGENT', 'WANTED', 'ENEMY', 'NOT_ACTIVE');

CREATE TABLE IF NOT EXISTS stalker
(
    id              uuid           not null
        constraint stalker_pkey
            primary key,
    steamId         varchar        not null,
    name            varchar        not null,
    status          stalker_status not null,
    description     varchar        not null,
    print_link      varchar        not null,
    nickname        varchar,
    personalAccount bigint,
    created         timestamp      not null,
    updated         timestamp      not null
);