/* Formatted on 2010/07/13 12:01 (Formatter Plus v4.8.8) */
-- product_cursor.sql displays the id, name, and price columns
-- from the products table using a cursor


-- connect as store_user
--CONNECT store_user/store_password;

DECLARE
   -- step 1: declare the variables
   ID      products.ID%TYPE;
   NAME    products.NAME%TYPE;
   price   products.price%TYPE;

   -- step 2: declare the cursor
   CURSOR product_cursor
   IS
      SELECT   ID, NAME, price
          FROM products
      ORDER BY ID;
BEGIN
   -- step 3: open the cursor
   OPEN product_cursor;

   LOOP
      -- step 4: fetch the rows from the cursor
      FETCH product_cursor
       INTO ID, NAME, price;

      -- exit the loop when there are no more rows, as indicated by
      -- the Boolean variable product_cursor%NOTFOUND (= true when
      -- there are no more rows)
      EXIT WHEN product_cursor%NOTFOUND;
      -- use DBMS_OUTPUT.PUT_LINE() to display the variables
      DBMS_OUTPUT.put_line (   'id = '
                            || ID
                            || ', name = '
                            || NAME
                            || ', price = '
                            || price
                           );
   END LOOP;

   -- step 5: close the cursor
   CLOSE product_cursor;
END;
/