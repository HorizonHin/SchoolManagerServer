create table department
(
    id              bigint auto_increment
        primary key,
    address         varchar(255)         null,
    department_name varchar(255)         not null,
    is_delete       int        default 0 not null,
    open            tinyint(1) default 1 not null,
    order_num       int                  null,
    parent_name     varchar(255)         null,
    phone           varchar(20)          null,
    pid             bigint               null,
    constraint department_ibfk_1
        foreign key (pid) references department (id)
            on delete set null
);

create index pid
    on department (pid);

create table permission
(
    id          bigint auto_increment
        primary key,
    code        varchar(255)         not null,
    create_time date                 not null,
    icon        varchar(255)         null,
    is_delete   int        default 0 not null,
    label       varchar(255)         null,
    open        tinyint(1) default 1 not null,
    order_num   int                  null,
    parent_id   bigint               null,
    parent_name varchar(255)         null,
    path        varchar(255)         null,
    remark      text                 null,
    type        int                  null,
    update_time date                 not null,
    url         varchar(255)         null,
    value       varchar(255)         not null,
    constraint value
        unique (value),
    constraint permission_ibfk_1
        foreign key (parent_id) references permission (id)
            on delete set null
);

create index parent_id
    on permission (parent_id);

create table role
(
    id   int auto_increment
        primary key,
    name varchar(255) not null,
    constraint roleNameUni
        unique (name),
    constraint role_name_uni
        unique (name),
    constraint role_name_unique
        unique (name)
);

create table functiontable
(
    ID       int unsigned auto_increment
        primary key,
    name     varchar(20) null,
    super_ID int         null,
    constraint functiontable_ibfk_1
        foreign key (super_ID) references role (id)
);

create index super_ID
    on functiontable (super_ID);

create table role_permissions
(
    role_name        varchar(255) not null,
    permission_value varchar(255) not null,
    primary key (role_name, permission_value),
    constraint permissions_value_fk
        foreign key (permission_value) references permission (value),
    constraint role_name_permission_fk
        foreign key (role_name) references role (name)
);

create table rolefunction
(
    role_ID     int         not null,
    function_ID int         not null,
    name        varchar(20) null,
    primary key (role_ID, function_ID)
);

create table user
(
    id                     bigint auto_increment
        primary key,
    avatar                 varchar(255)         null,
    email                  varchar(255)         null,
    gender                 int                  null,
    nick_name              varchar(255)         null,
    password               varchar(255)         not null,
    phone                  varchar(20)          null,
    real_name              varchar(255)         null,
    mobile                 varchar(20)          null,
    username               varchar(255)         not null,
    create_time            datetime             null,
    department_id          bigint               null,
    department_name        varchar(255)         null,
    is_account_expired     tinyint(1) default 0 null,
    is_account_locked      tinyint(1) default 0 null,
    is_admin               tinyint(1) default 0 null,
    is_credentials_expired tinyint(1) default 0 null,
    is_delete              tinyint(1) default 0 null,
    is_enabled             tinyint(1) default 1 null,
    update_time            datetime             null,
    constraint email
        unique (email),
    constraint username
        unique (username)
);

create table user_roles
(
    username  varchar(255) not null,
    role_name varchar(255) not null,
    primary key (username, role_name),
    constraint role_name_fk
        foreign key (role_name) references role (name),
    constraint username_fk
        foreign key (username) references user (username)
);

