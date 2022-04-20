create sequence hibernate_sequence start 1 increment 1;
create table "user"
(
    id              int8         not null,
    activation_code varchar(255),
    active          boolean      not null,
    email           varchar(255),
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);
create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table "application"
(
    id          int8 not null,
    dt_creation timestamp,
    name        varchar(255),
    user_id     int8,
    primary key (id)
);

create table "event"
(
    id             int8 not null,
    description    varchar(255),
    dt_creation    timestamp,
    name           varchar(255),
    application_id int8,
    primary key (id)
);

alter table if exists "application"
    add constraint application_user_id_fk
        foreign key (user_id)
            references "user";

alter table if exists "event"
    add constraint event_application_id_fk
        foreign key (application_id)
            references "application";

alter table if exists user_role
    add constraint user_role_user_fk foreign key (user_id) references "user";