drop table if exists urls_roles;
drop table if exists users_roles;
drop table if exists users;
drop table if exists roles;
drop table if exists configs;
drop table if exists pccs;

create table pccs (
    pcc_id int not null auto_increment,
    pcc_pcc char(6) not null,
    pcc_desc varchar(200) not null,
    pcc_version int not null default 0,
    constraint primary key (pcc_id),
    constraint nk_pccs unique (pcc_pcc)
);

create table users (
    user_id int not null auto_increment,
    pcc_id int not null,
    user_name varchar(50) not null,
    user_hcm varchar(50) not null,
    user_password varchar(128) not null,
    user_fname varchar(100) not null,
    user_lname varchar(100) not null,
    user_company_name varchar(50),
    user_address1 varchar(50),
    user_address2 varchar(50),
    user_city varchar(30),
    user_state varchar(30),
    user_zip varchar(10),
    user_country char(2),
    user_phone varchar(30),
    user_fax varchar(30),
    user_mobile varchar(15),
    user_pager varchar(15),
    user_email varchar(200),
    user_last_login timestamp,
    user_version int not null default 0,
    constraint primary key (user_id),
    constraint nk_users unique (user_name),
    constraint fk_users1 foreign key (pcc_id) references pccs (pcc_id)
);

create table roles (
    role_id int not null auto_increment,
    role_name varchar(50) not null,
    role_desc varchar(100) not null,
    role_menu varchar(50) not null,
    role_version int not null default 0,
    constraint primary key (role_id),
    constraint nk_roles unique (role_name)
);

create table users_roles (
    user_id int not null,
    role_id int not null,
    users_roles_desc varchar(100),
    users_roles_version int not null default 0,
    primary key (user_id,role_id),
    constraint fk_users_roles1 foreign key (user_id) references users (user_id),
    constraint fk_users_roles2 foreign key (role_id) references roles (role_id),
    index idx_users_roles1 (user_id),
    index idx_users_roles2 (role_id)
);

create table urls_roles (
    url_role varchar(250) not null,
    role_id int not null,
    url_role_version int not null default 0,
    primary key (url_role,role_id),
    constraint fk_urls_roles1 foreign key (role_id) references roles (role_id),
    index idx_urls_roles1 (url_role),
    index idx_urls_roles2 (role_id)
);

create table configs (
    config_id int not null auto_increment,
    config_key char(30) not null,
    config_desc varchar(100) not null,
    config_type varchar(100) not null,
    config_value varchar(100) not null,
    config_version int not null default 0,
    constraint primary key (config_id), 
    constraint nk_configs unique (config_key)
);

