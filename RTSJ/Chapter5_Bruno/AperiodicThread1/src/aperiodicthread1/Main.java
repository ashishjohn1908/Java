package aperiodicthread1;

import java.util.*;
import java.io.*;
import java.net.*;
import javax.realtime.*;

public class Main {
    public boolean run = true;
    public boolean debug = false;
    
    private Vector<Thread> listeners 
            = new Vector<Thread>();
    
    private ServerSocket server = null;
    private int port = 8080;

    public static void main(String[] args) 
            throws Exception
    {
        Main m = new Main();
    }

    public Main() throws IOException
    {
        log("Message Proc starting");

        // Create the server listening socket
        server = new ServerSocket(port);
        
        // Create a pool of request processor threads
        if ( debug ) log("Creating request processor threads");
        for ( int i = 0; i < 25; i++ )
        {
            RealtimeThread rtt = null;
            
            rtt = new RealtimeThread( 
                        null, null, null, null, null, new RequestProcessor(i) );
            //Thread rtt = new Thread( new RequestProcessor(i) );
            listeners.add(rtt);
            rtt.start();
        }
        
        // Create a real-time thread to listen on a server socket
        if ( debug ) log("Creating socket listener");
        PriorityParameters HiPri = 
                new PriorityParameters(PriorityScheduler.instance().getNormPriority()+10);
        RealtimeThread rt = new RealtimeThread(HiPri) {
        //Thread rt = new Thread() {
            public void run() {
                // Listen on server socket here, and assign
                // requests to pooled RTTs
                if ( debug ) log("Entering loop to listen on port " + port);
                while ( run )
                {
                    try {
                        Socket request = server.accept();
                        if ( debug ) log("New Server Connection, handing off...");
                        RequestProcessor.processRequest(request);
                    }
                    catch ( Exception e ) {
                        e.printStackTrace();;
                    }
                }
            }
        };
        
        rt.start();
        
        // Wait for the server thread to terminate
        try {
            if ( debug ) log("Main thread going dormant");
            rt.join();
        }
        catch ( InterruptedException e ) {
            log("MessageProc applicatin terminating");
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        
        // Loop through all of the RTTs and kill them
        for ( int i = 0; i < listeners.size(); i++ )
        {
            Thread rtt = listeners.elementAt(i);
            rtt.interrupt();
        }
        listeners.clear();
        
        System.exit(0);
    }
    
    public void log(String s)
    {
        System.out.println(s);
    }
}
