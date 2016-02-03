-- The SQL*Plus script store_user.sql performs the following:
--   1. Creates store_user
--   2. Creates the database objects
--   3. Populates the database tables with example data

-- This script should be run by the system user (or the DBA)
CONNECT system/London92;

-- drop store_user
DROP USER store_user CASCADE;

-- create store_user
CREATE USER store_user IDENTIFIED BY store_password;

-- allow store_user to connect and create database objects
GRANT connect, resource TO store_user;
GRANT UNLIMITED TABLESPACE TO store_user;

-- connect as store_user
CONNECT store_user/store_password;

-- create the tables
CREATE TABLE customers (
  id         INTEGER CONSTRAINT customers_pk PRIMARY KEY,
  first_name VARCHAR2(10) NOT NULL,
  last_name  VARCHAR2(10) NOT NULL,
  dob        DATE,
  phone      VARCHAR2(12)
);

CREATE TABLE product_types (
  id   INTEGER CONSTRAINT product_types_pk PRIMARY KEY,
  name VARCHAR2(10) NOT NULL
);

CREATE TABLE products (
  id          INTEGER
    CONSTRAINT products_pk PRIMARY KEY,
  type_id     INTEGER
    CONSTRAINT products_fk_product_types
    REFERENCES product_types(id),
  name        VARCHAR2(30) NOT NULL,
  description VARCHAR2(50),
  price       NUMBER(5, 2)
);

CREATE TABLE purchases (
  product_id   INTEGER
    CONSTRAINT purchases_fk_products
    REFERENCES products(id),
  purchased_by INTEGER
    CONSTRAINT purchases_fk_customers
    REFERENCES customers(id),
  quantity     INTEGER NOT NULL,
  CONSTRAINT   purchases_pk PRIMARY KEY (product_id, purchased_by)
);

CREATE TABLE perf_test (
  value INTEGER
);


-- create the PL/SQL functions, procedures and packages

CREATE PROCEDURE update_product_price(

  p_product_id IN products.id%TYPE,
  p_factor     IN NUMBER

) AS

  product_count INTEGER;

BEGIN

  -- count the number of products with the
  -- supplied id (should be 1 if the product exists)
  SELECT
    COUNT(*)
  INTO
    product_count
  FROM
    products
  WHERE
    id = p_product_id;

  -- if the product exists (product_count = 1) then
  -- update that product's price
  IF product_count = 1 THEN
    UPDATE
      products
    SET
      price = price * p_factor;
    COMMIT;
  END IF;

END update_product_price;
/


CREATE FUNCTION update_product_price_func(

  p_product_id IN products.id%TYPE,
  p_factor     IN NUMBER

) RETURN INTEGER AS

  product_count INTEGER;

BEGIN

  SELECT
    COUNT(*)
  INTO
    product_count
  FROM
    products
  WHERE
    id = p_product_id;

  -- if the product doesn't exist then return 0,
  -- otherwise perform the update and return 1
  IF product_count = 0 THEN
    RETURN 0;
  ELSE
    UPDATE
      products
    SET
      price = price * p_factor;
    COMMIT;
    RETURN 1;
  END IF;

END update_product_price_func;
/


-- package ref_cursor_package illustrates the use of the
-- REF CURSOR type
CREATE OR REPLACE PACKAGE ref_cursor_package AS

  TYPE t_ref_cursor IS REF CURSOR;
  FUNCTION get_products_ref_cursor RETURN t_ref_cursor;

END ref_cursor_package;
/

CREATE PACKAGE BODY ref_cursor_package AS

  -- function get_products_ref_cursor() returns a REF CURSOR
  FUNCTION get_products_ref_cursor
  RETURN t_ref_cursor IS

    products_ref_cursor t_ref_cursor;

  BEGIN

    -- get the REF CURSOR
    OPEN products_ref_cursor FOR
      SELECT
        id, name, price
      FROM
        products;

    -- return the REF CURSOR
    RETURN products_ref_cursor;

  END get_products_ref_cursor;

END ref_cursor_package;
/

-- insert sample data into customers table

INSERT INTO customers (id, first_name, last_name, dob, phone)
VALUES (1, 'John', 'Brown', '01-JAN-1965', '800-555-1211');

INSERT INTO customers (id, first_name, last_name, dob, phone)
VALUES (2, 'Cynthia', 'Green', '05-FEB-1968', '800-555-1212');

INSERT INTO customers (id, first_name, last_name, dob, phone)
VALUES (3, 'Steve', 'White', '16-MAR-1971', '800-555-1213');

INSERT INTO customers (id, first_name, last_name, dob, phone)
VALUES (4, 'Gail', 'Black', NULL, '800-555-1214');

INSERT INTO customers (id, first_name, last_name, dob, phone)
VALUES (5, 'Doreen', 'Blue', '20-MAY-1970', NULL);

-- insert sample data into product_types table

INSERT INTO product_types (id, name)
VALUES (1, 'Book');

INSERT INTO product_types (id, name)
VALUES (2, 'Video');

INSERT INTO product_types (id, name)
VALUES (3, 'DVD');

INSERT INTO product_types (id, name)
VALUES (4, 'CD');

-- insert sample data into products table

INSERT INTO products (id, type_id, name, description, price)
VALUES (1, 1, 'Modern Science', 'A description of modern science', 19.95);

INSERT INTO products (id, type_id, name, description, price)
VALUES (2, 1, 'Chemistry', 'Introduction to Chemistry', 30.00);

INSERT INTO products (id, type_id, name, description, price)
VALUES (3, 2, 'Supernova', 'A star explodes', 25.99);

INSERT INTO products (id, type_id, name, description, price)
VALUES (4, 2, 'Tank War', 'Action movie about a future war', 13.95);

INSERT INTO products (id, type_id, name, description, price)
VALUES (5, 2, 'Z Files', 'Series on mysterious activities', 49.99);

INSERT INTO products (id, type_id, name, description, price)
VALUES (6, 2, '2412: The Return', 'Aliens return', 14.95);

INSERT INTO products (id, type_id, name, description, price)
VALUES (7, 3, 'Space Force 9', 'Adventures of heroes', 13.49);

INSERT INTO products (id, type_id, name, description, price)
VALUES (8, 3, 'From Another Planet', 'Alien from another planet lands on Earth', 12.99);

INSERT INTO products (id, type_id, name, description, price)
VALUES (9, 4, 'Classical Music', 'The best classical music', 10.99);

INSERT INTO products (id, type_id, name, description, price)
VALUES (10, 4, 'Pop 3', 'The best popular music', 15.99);

INSERT INTO products (id, type_id, name, description, price)
VALUES (11, 4, 'Creative Yell', 'Debut album', 14.99);

INSERT INTO products (id, type_id, name, description, price)
VALUES (12, NULL, 'My Front Line', 'Their greatest hits', 13.49);

-- insert sample data into purchases table

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (1, 1, 1);

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (1, 2, 3);

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (4, 1, 1);

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (2, 2, 1);

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (3, 1, 1);

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (2, 1, 2);

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (3, 2, 1);

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (4, 2, 1);

INSERT INTO purchases (purchased_by, product_id, quantity)
VALUES (3, 3, 1);

-- commit the transaction
COMMIT;