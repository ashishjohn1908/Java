/*<TOAD_FILE_CHUNK>*/
-- The SQL*Plus script collection_user_9i.sql performs the following:
--   1. Creates collection_user_9i
--   2. Creates the database object and collection types
--   3. Populates the database tables with example data

-- This script should be run by the system user (or the DBA)
CONNECT system/London92;

-- drop collection_user_9i
DROP USER collection_user_9i CASCADE;

-- create collection_user_9i
CREATE USER collection_user_9i IDENTIFIED BY collection_password;

-- allow collection_user_9i to connect and create database objects
GRANT connect, resource TO collection_user_9i;
GRANT UNLIMITED TABLESPACE TO collection_user_9i;

-- connect as collection_user_9i
CONNECT collection_user_9i/collection_password;


-- create the object and collection types
CREATE TYPE varray_phone_typ AS VARRAY(3) OF VARCHAR2(14);
/
/*<TOAD_FILE_CHUNK>*/

CREATE TYPE address_typ AS OBJECT (
  street        VARCHAR2(15),
  city          VARCHAR2(15),
  state         CHAR(2),
  zip           VARCHAR2(5),
  phone_numbers varray_phone_typ
);
/
/*<TOAD_FILE_CHUNK>*/

CREATE TYPE nested_table_address_typ AS TABLE OF address_typ;
/
/*<TOAD_FILE_CHUNK>*/


-- create the tables

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


-- insert sample data into customers_with_nested_table table

INSERT INTO customers_with_nested_table VALUES (
  1, 'Steve', 'Brown',
  nested_table_address_typ(
    address_typ('2 State Street', 'Beantown', 'MA', '12345',
      varray_phone_typ(
        '(800)-555-1211',
        '(800)-555-1212',
        '(800)-555-1213'
      )
    ),
    address_typ('4 Hill Street', 'Lost Town', 'CA', '54321',
      varray_phone_typ(
        '(800)-555-1211',
        '(800)-555-1212'
      )
    )
  )
);

-- commit the transaction
COMMIT;
