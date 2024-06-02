create table users
(
    id       serial
        primary key,
    username varchar(255) not null
        unique,
    password varchar(255) not null
);

alter table users
    owner to postgres;

create table games
(
    id          serial
        primary key,
    name        varchar(255) not null
        unique,
    description text,
    genre       varchar(255),
    image       varchar(255),
    coverimage  varchar(255),
    exe         varchar(255),
    folder      varchar(255)
);

alter table games
    owner to postgres;

create table user_games
(
    user_id integer not null
        references users,
    game_id integer not null
        references games,
    primary key (user_id, game_id)
);

alter table user_games
    owner to postgres;