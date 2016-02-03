/*
  ProductServlet.java illustrates how to use a bean in a servlet
*/

// import the required classes

import EJB.server.ProductHome;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Hashtable;

public class ProductServlet extends HttpServlet {
    // declare a server.ProductHome object
    ProductHome myProductHome;

    public void init() throws ServletException {
        try {

            // create a Hashtable object to store the details
            // of how to look up the bean
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.evermind.server.rmi.RMIInitialContextFactory");
            env.put(Context.PROVIDER_URL, "ormi://localhost/JDBCEJB");
            env.put(Context.SECURITY_PRINCIPAL, "store_user");
            env.put(Context.SECURITY_CREDENTIALS, "store_password");

            // create a JNDI Context object
            Context myInitialContext = new InitialContext(env);

            // locate the bean using the lookup() method
            Object homeObject = myInitialContext.lookup("ProductBean");

            // narrow the reference to a ProductHome object
            myProductHome = (ProductHome)
                    PortableRemoteObject.narrow(
                            homeObject,
                            ProductHome.class
                    );

        } catch (NamingException e) {
            throw new ServletException("Error looking up home", e);
        }
    }


    // handle get request
    public void doGet(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        // begin an HTML page
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        out.println("<html>");
        out.println("<head><title>Product</title></head>");
        out.println("<body>");

        // display an HTML table
        out.println("<table width=100% border=1>");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Name</th>");
        out.println("<th>Description</th>");
        out.println("<th>Price</th>");
        out.println("</tr>");

        try {

            // create a ProductRemote object by calling the create() method
            EJB.server.ProductRemote myProductRemote = myProductHome.create();

            // create a Product object and use the query() method of the
            // ProductRemote object
            EJB.server.Product myProduct = myProductRemote.query(1);

            // if the Product object is not null (i.e. the row was found in
            // the products table), then display the Product object's
            // attributes in the table
            if (myProduct != null) {

                out.println("<tr>");
                out.println("<td>" + myProduct.id + "</td>");
                out.println("<td>" + myProduct.name + "</td>");
                out.println("<td>" + myProduct.description + "</td>");
                out.println("<td>" + myProduct.price + "</td>");
                out.println("</tr>");

            }

            // finish the HTML page
            out.println("</table>");
            out.println("</body></html>");

        } catch (RemoteException e) {
            out.println("Error communicating with EJB-server: " +
                    e.getMessage());
        } catch (CreateException e) {
            out.println("Error creating EJB: " + e.getMessage());
        } catch (SQLException e) {
            out.println("SQL Exception: " + e.getMessage());
        }
    }

}