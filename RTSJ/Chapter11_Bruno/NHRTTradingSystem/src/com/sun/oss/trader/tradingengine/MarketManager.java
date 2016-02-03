package com.sun.oss.trader.tradingengine;

import com.sun.oss.trader.data.*;
import java.util.*;
import javax.jms.*;
import javax.naming.*;
import javax.realtime.*;

public class MarketManager extends RealtimeThread implements MessageListener
{
    static final String SYMBOL_TAG = "<symbol>";
    static final String SYMBOL_END_TAG = "</symbol>";
    static final String PRICE_TAG = "<price>";
    static final String PRICE_END_TAG = "</price>";

    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;

    public MarketManager(   PriorityParameters priorityParameters,
                            MemoryArea area)
    {
        super(priorityParameters, null, null, area, null, null);
    }

    public void close()
    {
        try {
            destination = null;
            consumer.close();
            session.close();
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void setupJMS(String destinationName) throws Exception
    {
        // Look up connection factory and destination.
        //
        Context jndiContext = new InitialContext();

        ConnectionFactory connectionFactory = 
                (ConnectionFactory)jndiContext.lookup("jms/ConnectionFactory");
        
        destination = (Destination)jndiContext.lookup(destinationName);

        jndiContext.close();

        // Create JMS connection, session, and message consumer
        //
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        consumer = session.createConsumer(destination);
        consumer.setMessageListener( this );
        connection.start();
    }

    public void displayMarketBook()
    {
        System.out.println("************************************************");
        System.out.println("Current Market Book:");

        Set<String> keys = IMArea.marketBook.keySet();
        Iterator<String> iter = keys.iterator();
        while ( iter.hasNext() )
        {
            String symbol = iter.next();
            double price = getLastTradePrice(symbol);
            System.out.println( "Last trade price for " + 
                                symbol + ":" + price);
        }

        System.out.println(" ");
        System.out.println(" ");
    }

    // Display the last trade price for a symbol
    public double getLastTradePrice(String symbol)
    {
        // Get the order book for this symbol if it exists
        try {
            return Double.parseDouble( IMArea.marketBook.get(symbol).toString() );
        }
        catch ( Exception e ) { }
        
        return 0;
    }

    public void run() 
    {
        try {
            setupJMS("jms/QuoteUpdates");
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    // Market updates arrive as JMS Topic messages
    public void onMessage(Message msg) 
    {
        try {
            javax.jms.TextMessage update = (javax.jms.TextMessage)msg;

            /*
             * SAMPLE UPDATE XML:
                <updates>
                    <update>
                        <symbol>SUNW</symbol>
                        <datetime>2006-09-20T13:59:25.993-04:00</datetime>
                        <price>4.9500</price>
                    </update>
                </updates>
            */

            // To preserve memory when running within a no-heap realtime thread
            // (NHRT) the XML String is walked manually, without the use of
            // a DOM or SAX parser that would otherwise create lots of objects
            //
            String sUpdate = update.getText();
            int start = 0;
            boolean fParse = true;
            while ( fParse )
            {
                int symbolBegin = sUpdate.indexOf(IMArea.SYMBOL_TAG, start);
                if ( symbolBegin < 0 )
                    break;
                
                int symbolEnd = sUpdate.indexOf(IMArea.SYMBOL_CLOSE, symbolBegin);
                String symbol = sUpdate.substring(symbolBegin+(IMArea.SYMBOL_TAG.length()), symbolEnd);

                int priceBegin = sUpdate.indexOf(IMArea.PRICE_TAG, start);
                int priceEnd = sUpdate.indexOf(IMArea.PRICE_CLOSE, priceBegin);
                String price = sUpdate.substring(priceBegin+(IMArea.PRICE_TAG.length()), priceEnd);
                start = priceEnd;
                
                onUpdate( symbol, price );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    //static StringBuffer sbSymbol = new StringBuffer(15);
    static MarketPrice mktPriceObj = null;
    static String symbol;
    static double price;
    
    RealtimeThread rt = null;
    class UpdateThread implements Runnable {
        public void run() {
            try {
                mktPriceObj = IMArea.marketBook.get(symbol);
                if ( mktPriceObj == null )
                {
                    // These objects are only allocated the first
                    // time an update is received for an equity
                    //StringBuffer sbS = new StringBuffer(symbol);
                    mktPriceObj = new MarketPrice(null, price, 0, null);
                    IMArea.marketBook.put(new String(symbol), mktPriceObj);
                }
                else
                {
                    // Replace the existing contents of the price StringBuffer
                    // to avoid allocating more memory
                    mktPriceObj.setPrice(price);
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
            catch ( Error err ) {
                err.printStackTrace();
            }
        }
    }

    static int count = 0;
    private void onUpdate(String s, String p)
    {
        if ( this.getMemoryArea() == ImmortalMemory.instance() )
            System.exit(-3);
        
        try {
            synchronized ( IMArea.marketBook ) {
                price = new Double(p).doubleValue();
                symbol = s;
                rt = new RealtimeThread(null,null,null,ImmortalMemory.instance(),null, new UpdateThread());
                rt.start();
            }
        }
        catch ( Throwable e ) {
            e.printStackTrace();
        }
    }
    
    private void log(String m)
    {
        boolean fDebug = false;
        if ( fDebug )
            System.out.println(m);
    }
}
