package aperiodicthread1;

import java.io.*;
import java.net.*;
import java.util.*;

public class RequestProcessor implements Runnable
{
    private static List<Socket> pool = new LinkedList<Socket>();
    private int id = 0;
    
    public RequestProcessor(int id)
    {
        this.id = id;
    }
    
    public void run() 
    {
        System.out.println("Thread " + id + " starting");
        boolean run = true;

        // Wait for a request to available
        while ( run )
        {
            try {
                Socket conn;

                synchronized ( pool ) {
                    while ( pool.isEmpty() )
                    {
                        try {
                            pool.wait();
                        }
                        catch ( InterruptedException e ) {
                            //e.printStackTrace();
                        }
                    }
                    conn = pool.remove(0);
                }

                // Read and respond to the request
                OutputStream os = new BufferedOutputStream( conn.getOutputStream() );
                Writer out = new OutputStreamWriter( os );
                
                InputStream is = new BufferedInputStream( conn.getInputStream() );
                Reader in = new InputStreamReader( is, "ASCII" );
                
                StringBuffer reqLine = new StringBuffer();
                Integer c;
                while ( true ) {
                    c = in.read();
                    if ( c == '\r' || c == '\n' )
                        break;
                    reqLine.append((char)c.byteValue());
                }

                Long tid = Thread.currentThread().getId();
                Integer pri = Thread.currentThread().getPriority();
                
                // Display the request message
                System.out.println( 
                        "Thread=" + id + 
                        ", priority=" + pri + 
                        ", processing " + "meesage: '" + reqLine.toString() + "'");
                
                // Send a message acknowledgement to the sender
                String resp = "MESSAGE ACK";
                out.write("HTTP 1.1 200 OK\r\n");
                out.write("Date: " + new Date() + "\r\n");
                out.write("Server: MSGPROC 1.0\r\n");
                out.write("Content-length: " + resp.length() + "\r\n");
                out.write("Content-type: ASCII\r\n\r\n");
                out.write(resp);
                out.flush();

                try {
                    in.close();
                    out.close();
                    os.close();
                    is.close();
                    conn.close();
                }
                catch ( Exception e ) { 
                    e.printStackTrace();
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + id + " exiting");
    }

    public static void processRequest(Socket request)
    {
        synchronized ( pool ) {
            // Make sure the request gets queued
            pool.add( request );
            
            // Notify one of the blocked threads in the pool. The 
            // first to wake up will process the request
            pool.notifyAll();
        }
    }
}
