/* Formatted on 2010/07/13 11:26 (Formatter Plus v4.8.8) */
/*<TOAD_FILE_CHUNK>*/
-- The SQL*Plus script collection_user.sql performs the following:
--   1. Creates collection_user
--   2. Creates the database object and collection type
--   3. Populates the database tables with example data

-- This script should be run by the system user (or the DBA)
CONNECT system/London92;

-- drop collection_user
DROP USER collection_user CASCADE;

-- create collection_user
CREATE USER collection_user IDENTIFIED BY collection_password;

-- allow collection_user to connect and create database objects
GRANT CONNECT, RESOURCE TO collection_user;
GRANT UNLIMITED TABLESPACE TO collection_user;

-- connect as collection_user
CONNECT collection_user/collection_password;

-- create the object and collection types

CREATE TYPE address_typ AS OBJECT (
   street   VARCHAR2 (15),
   city     VARCHAR2 (15),
   state    CHAR (2),
   zip      VARCHAR2 (5)
);
/
/*<TOAD_FILE_CHUNK>*/

CREATE TYPE varray_address_typ AS VARRAY(2) OF VARCHAR2(50);
/
/*<TOAD_FILE_CHUNK>*/

CREATE TYPE nested_table_address_typ AS TABLE OF address_typ;
/
/*<TOAD_FILE_CHUNK>*/

-- create the tables
CREATE TABLE customers_with_varray (
  id         INTEGER PRIMARY KEY,
  first_name VARCHAR2(10),
  last_name  VARCHAR2(10),
  addresses  varray_address_typ
);

CREATE TABLE customers_with_nested_table (
  id         INTEGER PRIMARY KEY,
  first_name VARCHAR2(10),
  last_name  VARCHAR2(10),
  addresses  nested_table_address_typ
)
NESTED TABLE
  addresses
STORE AS
  nested_addresses;

-- insert sample data into customers_with_varray table

INSERT INTO customers_with_varray VALUES (
  1, 'Steve', 'Brown',
  varray_address_typ(
    '2 State Street, Beantown, MA, 12345',
    '4 Hill Street, Lost Town, CA, 54321'
  )
);

-- insert sample data into customers_with_nested_table table

INSERT INTO customers_with_nested_table VALUES (
  1, 'Steve', 'Brown',
  nested_table_address_typ(
    address_typ('2 State Street', 'Beantown', 'MA', '12345'),
    address_typ('4 Hill Street', 'Lost Town', 'CA', '54321')
  )
);

-- commit the transaction
COMMIT;
