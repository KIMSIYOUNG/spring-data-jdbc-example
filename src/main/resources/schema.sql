create table if not exists MEMBER
(
    ID       bigint      not null primary key auto_increment,
    NAME     varchar(32) not null,
    NICKNAME varchar(32)
);

create table if not exists ACCOUNT
(
    ID         bigint       not null primary key auto_increment,
    LOGIN_ID   varchar(64)  not null,
    NAME       varchar(128) not null,
    STATE      varchar(16)  not null,
    EMAIL      varchar(64),
    CREATED_AT datetime     not null
)