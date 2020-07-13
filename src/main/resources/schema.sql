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
    EMAIL      binary,
    CREATED_AT datetime     not null
);

create table if not exists REPO
(
    ID          varchar(255) not null primary key,
    NAME        varchar(128) not null,
    DESCRIPTION varchar(255),
    CREATED_BY  bigint       not null,
    CREATED_AT  datetime     not null
);

create table if not exists ISSUE
(
    ID         bigint      not null primary key auto_increment,
    VERSION    integer,
    REPO_ID    bigint      not null,
    ISSUE_NO   bigint      not null,
    STATUS     varchar(64) not null,
    CREATED_BY varchar(36) not null,
    CREATED_AT datetime    not null
);

create table if not exists ISSUE_CONTENT
(
    ID          bigint auto_increment primary key not null,
    LABEL_ID    varchar(36)                       not null,
    ATTACHED_AT datetime                          not null,
    ISSUE_ID    varchar(36)                       not null
);

create table if not exists ISSUE_ATTACHED_LABEL
(
    ID          bigint auto_increment primary key not null,
    LABEL_ID    bigint                            not null,
    ATTACHED_AT datetime                          not null,
    ISSUE_ID    bigint                            not null
);

create table if not exists LABEL
(
    ID      bigint auto_increment primary key not null,
    REPO_ID bigint                            not null,
    NAME    varchar(128)                      not null,
    COLOR   varchar(128)                      not null
);

create table if not exists COMMENT
(
    ID bigint auto_increment primary key not null,
    VERSION bigint,
    ISSUE_ID bigint not null ,
    CREATED_BY bigint not null ,
    CREATED_AT datetime not null
);

create table if not exists COMMENT_CONTENT
(
    ID bigint primary key ,
    BODY varchar(255) not null ,
    MIME_TYPE varchar(32) not null
);





