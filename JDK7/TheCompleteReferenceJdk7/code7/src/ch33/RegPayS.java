package ch33;
// A simple loan calculator servlet.
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.*;

public class RegPayS extends HttpServlet {
  double principal; // original principal
  double intRate;   // interest rate
  double numYears;  // length of loan in years

  /* Number of payments per year.  You could
     allow this value to be set by the user. */
  final int payPerYear = 12;

  NumberFormat nf;

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
    throws ServletException, IOException {
    String payStr = "";

    // Create a number format.
    nf = NumberFormat.getInstance();
    nf.setMinimumFractionDigits(2);
    nf.setMaximumFractionDigits(2);

    // Get the parameters.
    String amountStr = request.getParameter("amount");
    String periodStr = request.getParameter("period");
    String rateStr = request.getParameter("rate");

    try {
      if(amountStr != null && periodStr != null &&
         rateStr != null) {
        principal = Double.parseDouble(amountStr);
        numYears = Double.parseDouble(periodStr);
        intRate = Double.parseDouble(rateStr) / 100;

        payStr = nf.format(compute());
      }
      else { // one or more parameters missing
        amountStr = "";
        periodStr = "";
        rateStr = "";
      }
    } catch (NumberFormatException exc) {
      // Take appropriate action here.
    }

    // Set the content type.
    response.setContentType("text/html");

    // Get the output stream.
    PrintWriter pw = response.getWriter();

    // Display the necessary HTML.
    pw.print("<html><body> <left>" +
             "<form name=\"Form1\"" +
             " action=\"http://localhost:8080/" +
             "examples/servlets/servlet/RegPayS\">" +
             "<B>Enter amount to finance:</B>" +
             " <input type=textbox name=\"amount\"" +
             " size=12 value=\"");
    pw.print(amountStr + "\">");
    pw.print("<BR><B>Enter term in years:</B>" +
             " <input type=textbox name=\"period\""+
             " size=12 value=\"");
    pw.println(periodStr + "\">");
    pw.print("<BR><B>Enter interest rate:</B>" +
             " <input type=textbox name=\"rate\"" +
             " size=12 value=\"");
    pw.print(rateStr + "\">");
    pw.print("<BR><B>Monthly Payment:</B>" +
             " <input READONLY type=textbox" +
             " name=\"payment\" size=12 value=\"");
    pw.print(payStr + "\">");
    pw.print("<BR><P><input type=submit value=\"Submit\">");
    pw.println("</form> </body> </html>");
  }

  // Compute the loan payment.
  double compute() {
    double numer;
    double denom;
    double b, e;

    numer = intRate * principal / payPerYear;

    e = -(payPerYear * numYears);
    b = (intRate / payPerYear) + 1.0;

    denom = 1.0 - Math.pow(b, e);

    return numer / denom;
  }
}
