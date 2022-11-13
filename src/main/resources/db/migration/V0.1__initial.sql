create table if not exists developer (
    id uuid not null,
    name varchar(255) not null,
    primary key (id)
);

create table if not exists repository (
    id uuid not null,
    name varchar(255) not null,
    url varchar(255) not null,
    language varchar(255),
    developer_id uuid not null,
    primary key (id),
    constraint developer_id
        foreign key (developer_id)
            references developer (id)
);
