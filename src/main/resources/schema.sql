create table if not exists MEMBER
(
    ID   bigint      not null primary key auto_increment,
    NAME varchar(32) not null,
    NICKNAME varchar(32)
);