create table if not EXISTS Account(
id integer not null,
username varchar(50) unique not null,
first_name varchar(50) not null,
last_name varchar(50) not null,
email varchar(50) not null,
password varchar(64),
marketing_ok char(1) not null check (marketing_ok in ( 'Y', 'N' )) ,
accept_terms char(1) not null check (accept_terms in ( 'Y', 'N' )) ,
enabled   char(1) not null check (enabled in ( 'Y', 'N' )),
date_created date default sysdate,
date_modified date default sysdate,
role varchar(64) not null,
optlock int,
primary key (id));

create sequence if not EXISTS ACC_SEQ start with 10 increment by 1
