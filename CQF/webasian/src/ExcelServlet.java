import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 01-Oct-2005
 * Time: 12:23:01
 * To change this template use File | Settings | File Templates.
 */
public class ExcelServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        ServletOutputStream  out = res.getOutputStream ();
        res.setContentType( "application/vnd.ms-excel" );
        String uRL = req.getRequestURL().toString();
        String fileURL = uRL.substring(0,uRL.lastIndexOf('/')) + "/image/binomial_barrier_project.xls";

        BufferedInputStream  bis = null;
        BufferedOutputStream bos = null;

            try {
                URL url = new URL(fileURL);

                // Use Buffered Stream for reading/writing.
                bis = new BufferedInputStream(url.openStream());
                bos = new BufferedOutputStream(out);

                byte[] buff = new byte[2048];
                int bytesRead;

                // Simple read/write loop.
                while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

            } catch(final MalformedURLException e) {
                System.out.println ( "MalformedURLException." );
                throw e;
            } catch(final IOException e) {
                  try{
                      res.sendRedirect(fileURL);
                  }catch(final IOException ex){
                      throw e;
                  }
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }


    }

}
