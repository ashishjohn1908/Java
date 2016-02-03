package com.sun.oss.trader.tradingengine;

import java.util.*;
import javax.jms.*;
import javax.naming.*;

public class MarketManager implements Runnable, MessageListener
{
    static final String SYMBOL_TAG = "<symbol>";
    static final String SYMBOL_END_TAG = "</symbol>";
    static final String PRICE_TAG = "<price>";
    static final String PRICE_END_TAG = "</price>";

    private boolean fDebug = false;
    
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;
    
    // The Market Book is a hashmap of prices (stored as StringBuffer objects)
    // that are looked up by stock symbol (StringBuffer) as the key
    public static final int INITIAL_CAPACITY = 111;
    HashMap<String,StringBuffer> marketBook = 
        new HashMap<String,StringBuffer>(INITIAL_CAPACITY); 

    public MarketManager()
    {
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

        Set<String> keys = marketBook.keySet();
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
            return Double.parseDouble( marketBook.get(symbol).toString() );
        }
        catch ( Exception e ) { }
        
        return 0;
    }

    public void run() 
    {
        try {
            setupJMS("jms/QuoteUpdates");
/*
            while ( true )
            {
                Thread.sleep(1000);
                displayMarketBook();
            }
*/
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    // Market updates arrive as JMS Topic messages
    public void onMessage(Message msg) 
    {
        try {
            TextMessage update = (TextMessage)msg;

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
                int sBegin = sUpdate.indexOf(SYMBOL_TAG, start);
                if ( sBegin < 0 )
                    break;
                
                int sEnd = sUpdate.indexOf(SYMBOL_END_TAG, sBegin);
                String symbol = sUpdate.substring(sBegin+(SYMBOL_TAG.length()), sEnd);

                int pBegin = sUpdate.indexOf(PRICE_TAG, start);
                int pEnd = sUpdate.indexOf(PRICE_END_TAG, pBegin);
                String price = sUpdate.substring(pBegin+(PRICE_TAG.length()), pEnd);
                start = pEnd;
                
                onUpdate(symbol, price );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
	}
    
    private void onUpdate(String symbol, String price)
    {
        log("Symbol: " + symbol + ", Quote: " + price);

        // Look for the symbol in the existing market book. If it's not there
        // then create a new StringBuffer with an initial capacity, so that
        // as updates occur memory will not be constantly allocated
        //
        StringBuffer sbPrice = marketBook.get(symbol);
        if ( sbPrice == null )
        {
            sbPrice = new StringBuffer(15);
            marketBook.put(symbol, sbPrice);
        }

        // Replace the existing contents of the price StringBuffer
        // to avoid allocating more memory
        //
        sbPrice.replace(0, price.length(), price);
    }
    
    private void log(String m)
    {
        if ( fDebug )
            System.out.println(m);
    }
}
