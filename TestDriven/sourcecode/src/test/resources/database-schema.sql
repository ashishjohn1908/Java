DROP TABLE ARTICLES CASCADE CONSTRAINTS; 

create table ARTICLES 
(
id number(19,0) not null, 
body varchar2(80) not null, 
titel varchar2(80) not null,
primary key (id)
);

DROP TABLE ATTACHMENTS CASCADE CONSTRAINTS; 

create table ATTACHMENTS 
(
id number(19,0) not null, 
name varchar2(80) not null, 
filepath varchar2(80) not null, 
orders number(10,0),
article_id number(19,0),  
primary key (id)
);

alter table ATTACHMENTS add constraint FK54475F90DE5F9BC6 foreign key (article_id) references ARTICLES(id);

DROP TABLE EMPLOYEE CASCADE CONSTRAINTS; 

CREATE TABLE EMPLOYEE (
  employee_uid INTEGER NOT NULL, 
  start_date DATE NOT NULL,
  first_name VARCHAR2(50) NOT NULL, 
  last_name VARCHAR2(50) NOT NULL, 
  ssn VARCHAR2(20) NOT NULL,
  PRIMARY KEY(employee_uid)
);

DROP TABLE SALARY CASCADE CONSTRAINTS;

CREATE TABLE SALARY (
  employee_uid INTEGER NOT NULL,
  salary INTEGER NOT NULL,
  FOREIGN KEY (employee_uid) REFERENCES EMPLOYEE(employee_uid)
);

DROP TABLE PEOPLE CASCADE CONSTRAINTS;

CREATE TABLE PEOPLE (
  id INTEGER NOT NULL,
  first_name VARCHAR2(100) NOT NULL,
  last_name VARCHAR2(100) NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE HIBERNATE_UNIQUE_KEY  CASCADE CONSTRAINTS;

CREATE TABLE HIBERNATE_UNIQUE_KEY (
  next_hi INTEGER NOT NULL
);

INSERT INTO HIBERNATE_UNIQUE_KEY VALUES (0);

COMMIT;