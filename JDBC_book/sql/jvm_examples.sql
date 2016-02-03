-- The SQL*Plus script jvm_examples.sql creates the various
-- call specs, packages, tables and triggers
-- required for Chapter 10 of the JDBC book

-- connect as store_user
CONNECT store_user/store_password;

-- create a call spec for the JvmExample1.displayMessageInTraceFile() method
CREATE OR REPLACE PROCEDURE display_message_in_trace_file
AS
LANGUAGE JAVA
NAME 'JvmExample1.displayMessageInTraceFile()';
/

-- create a call spec for the JvmExample1.displayMessageOnScreen() method
CREATE OR REPLACE PROCEDURE display_message_on_screen
AS
LANGUAGE JAVA
NAME 'JvmExample1.displayMessageOnScreen()';
/

-- create a call spec for the JvmExample1.displayProduct() method
CREATE OR REPLACE PROCEDURE display_product(
  id NUMBER
) AS
LANGUAGE JAVA
NAME 'JvmExample1.displayProduct(int)';
/

-- create a call spec for the JvmExample1.addProduct() method
CREATE OR REPLACE PROCEDURE add_product(
  type_id     NUMBER,
  name        VARCHAR2,
  description VARCHAR2,
  price       NUMBER
) AS
LANGUAGE JAVA
NAME 'JvmExample1.addProduct(int, java.lang.String, java.lang.String, double)';
/

-- create a call spec for the JvmExample1.countProducts() method
CREATE OR REPLACE FUNCTION count_products
RETURN NUMBER AS
LANGUAGE JAVA
NAME 'JvmExample1.countProducts() return int';
/


-- create a package named jvm_example1 containing
-- the call specs for the methods in the JvmExample1 class
CREATE OR REPLACE PACKAGE jvm_example1 AS

  PROCEDURE display_message_in_trace_file
  AS
  LANGUAGE JAVA
  NAME 'JvmExample1.displayMessageInTraceFile()';

  PROCEDURE display_message_on_screen
  AS
  LANGUAGE JAVA
  NAME 'JvmExample1.displayMessageOnScreen()';

  PROCEDURE display_product(
    id NUMBER
  ) AS
  LANGUAGE JAVA
  NAME 'JvmExample1.displayProduct(int)';

  PROCEDURE add_product(
    type_id     NUMBER,
    name        VARCHAR2,
    description VARCHAR2,
    price       NUMBER
  ) AS
  LANGUAGE JAVA
  NAME 'JvmExample1.addProduct(int, java.lang.String, java.lang.String, double)';
  
  FUNCTION count_products
  RETURN NUMBER AS
  LANGUAGE JAVA
  NAME 'JvmExample1.countProducts() return int';

END jvm_example1;
/


-- create a procedure named add_display_and_count_products that
-- calls the Java stored program's call specs in the package jvm_example1
-- to add a new product, then display that product, and finally
-- count the number of products
CREATE OR REPLACE PROCEDURE add_display_and_count_products AS

  number_of_products INTEGER;

BEGIN

  jvm_example1.add_product(1, 'Java', 'Learning Java', 19.95);
  jvm_example1.display_product(15);
  number_of_products := jvm_example1.count_products;
  dbms_output.put_line('Total number of products = ' || number_of_products);

END add_display_and_count_products;
/


-- drop and then create the product_price_audit table
DROP TABLE product_price_audit;
CREATE TABLE product_price_audit (
  product_id INTEGER
    CONSTRAINT price_audit_fk_products
    REFERENCES products(id),
  old_price  NUMBER(5, 2),
  new_price  NUMBER(5, 2)
);

-- create a database trigger named before_product_price_update
-- that runs its code when the new price for a row in the
-- products table is more than 25% lower than the old price.
-- This trigger "audits" the change by adding a row to the
-- product_price_audit table, storing the product id,
-- the new price and the old price.
CREATE OR REPLACE TRIGGER
  before_product_price_update
BEFORE UPDATE OF
  price
ON
  products
FOR EACH ROW
WHEN
  (new.price < old.price * 0.75)
BEGIN

  dbms_output.put_line('For product id ' || :old.id);
  dbms_output.put_line('Old price = ' || :old.price);
  dbms_output.put_line('New price = ' || :new.price);
  dbms_output.put_line('The price reduction is more than 25%');

  -- insert row into the product_price_audit table
  INSERT INTO
    product_price_audit
  VALUES (
    :old.id, :old.price, :new.price
  );

END before_product_price_update;
/


-- create a call spec for the JvmExample3.triggerCode() method
CREATE OR REPLACE PROCEDURE trigger_code(
  product_id NUMBER,
  old_price  NUMBER,
  new_price  NUMBER
) AS
LANGUAGE JAVA
NAME 'JvmExample3.triggerCode(int, double, double)';
/


-- recreate the database trigger before_product_price_update;
-- the PL/SQL code of this trigger has been rewritten in Java and
-- moved to the JvmExample3.triggerCode() method
CREATE OR REPLACE TRIGGER
  before_product_price_update
BEFORE UPDATE OF
  price
ON
  products
FOR EACH ROW
WHEN
  (new.price < old.price * 0.75)
CALL trigger_code(:old.id, :old.price, :new.price)
/