/*
  ServletExample.java illustrates how to include
  JDBC statements in a servlet
*/

// import the servlet, io, util, and naming classes

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServletExample extends HttpServlet {

    // handle get request
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // begin an HTML page
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>Products</title></head>");
        out.println("<body>");

        // declare Connection and Statement objects
        Connection myConnection = null;
        Statement myStatement = null;

        try {

            // locate the data source jdbc/OracleCoreDS defined in the
            // data-sources.xml file in the config directory of OC4J
            InitialContext myInitialContext = new InitialContext();
            DataSource myDataSource = (DataSource) (myInitialContext.lookup("jdbc/OracleCoreDS"));

            // connect to the database using the data source
            myConnection = myDataSource.getConnection();

            // initialize the Statement object
            myStatement = myConnection.createStatement();

            // create a ResultSet object, and populate it with the
            // results of a query that retrieves the
            // id, name, description, and price columns
            // for all the rows from the products table
            ResultSet productResultSet = myStatement.executeQuery("SELECT id, name, description, price " +
                    "FROM products " +
                    "ORDER BY id"
            );

            // display the column values in an HTML table
            out.println("<table width=100% border=1>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Name</th>");
            out.println("<th>Description</th>");
            out.println("<th>Price</th>");
            out.println("</tr>");

            int id;
            String name;
            String description;
            float price;

            while (productResultSet.next()) {
                id = productResultSet.getInt("id");
                name = productResultSet.getString("name");
                description = productResultSet.getString("description");
                price = productResultSet.getFloat("price");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + description + "</td>");
                out.println("<td>" + price + "</td>");
                out.println("</tr>");
            } // end of while

            // close the JDBC objects
            productResultSet.close();
            myStatement.close();
            myConnection.close();

            // finish the HTML page
            out.println("</table>");
            out.println("</body></html>");

        } catch (SQLException e) {

            out.println("SQLException " + e);

        } catch (NamingException e) {

            out.println("NamingException " + e);

        }
    }
}