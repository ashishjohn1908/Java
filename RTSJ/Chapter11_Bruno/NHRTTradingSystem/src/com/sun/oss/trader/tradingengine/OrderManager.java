package com.sun.oss.trader.tradingengine;

import com.sun.oss.trader.data.IMArea;
import com.sun.oss.trader.data.OrderEntry;
import com.sun.oss.trader.data.OrderType;
import com.sun.oss.trader.data.SubBook;
import com.sun.oss.trader.util.*;
import com.sun.oss.trader.data.*;

import java.io.*;
import java.util.*;
import javax.realtime.*;

public class OrderManager extends NoHeapRealtimeThread
{
    static int checks = 0;
    long loopStartTime = 0;
    Date d = new Date();

    
    // Constructors
    //
    public OrderManager(PriorityParameters priority,
                        PeriodicParameters period,
                        MemoryArea area) 
            throws Exception
    {
        super(priority, period, null, area, null, null);
        System.out.println("OrderManager created");
    }

    // Public methods
    //
    public void enterOrder( double price, int quantity, 
                            String symbol, int type)
        throws Exception
    {
        try {
            OrderEntry newEntry = new OrderEntry();
            newEntry.price = price;
            newEntry.quantity = quantity;
            newEntry.type = type;
            newEntry.symbol = symbol;
            newEntry.setActive(true);

            // Get the order book for this symbol if it exists
            SubBook sb = IMArea.orderBook.get(newEntry.symbol);
            if ( sb == null ) {
                sb = new SubBook();
                IMArea.orderBook.put(newEntry.symbol, sb);
            }

            // Get the right sub book (buy or sell orders)
            LinkedList<OrderEntry> orders = null;
            if ( newEntry.isBuyOrder() )
                orders = sb.buy;
            else
                orders = sb.sell;

            // Walk the sub book and find the correct location for this order
            for ( int i = 0; i < orders.size(); i++ )
            {
                OrderEntry entry = orders.get(i);
                if ( newEntry.comesBefore(entry.getPrice()) )
                {
                    orders.add( i, newEntry );
                    return;
                }
            }

            // Just add the order to the end of the list
            orders.addLast(newEntry);
        }
        catch ( Throwable e ) {
            e.printStackTrace();
        }
    }
    
    public void run() 
    {
        log("OrderManager starting");

        try {
            log("creating stats data transfer queues");
            IMArea.freeTradeStatsPool = new WaitFreeReadQueue(100, true);
            IMArea.queuedTradeStatsPool = new WaitFreeWriteQueue(100);
            IMArea.freePerfStatsPool = new WaitFreeReadQueue(100, true);
            IMArea.queuedPerfStatsPool = new WaitFreeWriteQueue(100);

            // This loop continually compares the order book to the
            // market book to see if any trades should be executed
            //
            int counter = 0;
            loopStartTime = System.nanoTime();
            while ( true ) 
            {
                // Check for trades and send trade notification if any
                doTrades();

                long loopEndTime = System.nanoTime();

                // Create performance monitor object and only
                // record the timing every 10th time through the
                // loop unless the timing is bad
                //
                if ( ++counter > 10 || (loopEndTime - loopStartTime) > 2000000 )
                {
                    counter = 0;
                    StatsData perf = (StatsData)IMArea.freePerfStatsPool.read();
                    if ( perf != null )
                    {
                        perf.messageType = StatsData.MESSAGE_TYPE.PERF;
                        perf.startTime = loopStartTime;
                        perf.endTime = System.nanoTime();
                        IMArea.queuedPerfStatsPool.write(perf);
                    }
                }

                // start timing again
                loopStartTime = System.nanoTime();

                while ( ! waitForNextPeriod() )
                    ;//System.out.println("Missed deadline");
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        catch ( Error r ) {
            r.printStackTrace();
            System.exit(1);
        }
        
        System.out.println("OrderManager exiting");
    }

    private void doTrades()
    {
        // Make sure the global order book index is up-to-date
        // The call is optimized to ensure the work is only done
        // once unless the order book is updated
        //
        getOrderBookKeys();

        //
        // This loop continually compares the order book to the
        // market book to see if any trades should be executed
        //

        // Loop to compare market to order prices
        //
        for ( int i = 0; i < IMArea.orderBookKeys.length; i++ )
        {
            MarketPrice mktPrice = IMArea.marketBook.get(IMArea.orderBookKeys[i]);
            if ( mktPrice == null )
                continue;

            double marketPrice = mktPrice.getPrice();

            // Get the sub book for this symbol
            //
            SubBook sb = IMArea.orderBook.get(IMArea.orderBookKeys[i]);

            // Walk the list of sell orders to see if the market price
            // has hit or passed a limit or stop order threshold
            //
            for ( int x = 0; x < sb.sell.size(); x++ )
            {
                OrderEntry entry = sb.sell.get(x);
                if ( checkForTrade( entry, marketPrice ) == true )
                    break;
            }
            
            // Walk the list of buy orders to see if the market price
            // has hit or passed a limit or stop order threshold
            //
            for ( int x = 0; x < sb.buy.size(); x++ )
            {
                OrderEntry entry = sb.buy.get(x);
                if ( checkForTrade( entry, marketPrice ) == true )
                    break;
            }
        } 
    }

    private boolean checkForTrade(OrderEntry entry, double marketPrice)
    {
        if ( ! entry.isActive() )
            return false;

        switch ( entry.getType() )
        {
        case OrderType.STOP_SELL:
            if ( marketPrice <= entry.getPrice() )
            {
                createTradeObj(entry, marketPrice);
                entry.setActive(false);
                return true;
            }
            break;

        case OrderType.LIMIT_SELL:
            if ( marketPrice == entry.getPrice()  )
            {
                createTradeObj(entry, marketPrice);
                entry.setActive(false);
                return true;
            }
            else if ( marketPrice < entry.getPrice() )
            {
                System.out.println(
                        "*** MISSED LIMIT SELL: Order price: " +
                        entry.getPrice() +
                        ", Market price=" + marketPrice +
                        ", Stock: " + entry.getSymbol() );
            }

            break;
            
        case OrderType.STOP_BUY:
            if ( marketPrice >= entry.getPrice() )
            {
                createTradeObj(entry, marketPrice);
                entry.setActive(false);
                return true;
            }
            break;

        case OrderType.LIMIT_BUY:
            if ( marketPrice == entry.getPrice()  )
            {
                createTradeObj(entry, marketPrice);
                entry.setActive(false);
                return true;
            }
            else if ( marketPrice > entry.getPrice() )
            {
                System.out.println(
                        "*** MISSED LIMIT BUY: Order price: " +
                        entry.getPrice() +
                        ", Market price=" + marketPrice +
                        ", Stock: " + entry.getSymbol() );
            }
            break;
        }

        return false;
    }
    
    // Private methods
    //
    private synchronized void createTradeObj(OrderEntry entry, double tradePrice)
    {
        StatsData trade = (StatsData)IMArea.freeTradeStatsPool.read();
        
        trade.startTime = loopStartTime;
        trade.messageType = StatsData.MESSAGE_TYPE.TRADE;
        trade.symbol = entry.getSymbol();
        if ( entry.isBuyOrder() )
            trade.tradeType = StatsData.STATS_TYPE.BUY;
        else
            trade.tradeType = StatsData.STATS_TYPE.SELL;
        trade.tradePrice = tradePrice;
        trade.tradeQty = entry.getQuantity();
        trade.entryPrice = entry.getPrice();
        trade.endTime = System.nanoTime();
        
        IMArea.queuedTradeStatsPool.write(trade);
    }
    
    private String[] getOrderBookKeys()
    {
        if ( IMArea.orderBookKeys == null )
        {
            Set<String> keys = IMArea.orderBook.keySet();
            IMArea.orderBookKeys = new String[IMArea.orderBook.size()];  
            keys.toArray(IMArea.orderBookKeys);
        }

        return IMArea.orderBookKeys;
    }

    // Display the entire order book for all symbols 
    public void displayOrderBook()
    {
        if ( IMArea.orderBookKeys == null )
        {
            Set<String> keys = IMArea.orderBook.keySet();
            IMArea.orderBookKeys = new String[IMArea.orderBook.size()];  
            keys.toArray(IMArea.orderBookKeys);
        }
        
        for ( int i = 0; i < IMArea.orderBook.size(); i++ )
            displayOrderBook(IMArea.orderBookKeys[i]);
    }

    // Display the order book for a symbol
    public void displayOrderBook(String symbol)
    {
        // Get the order book for this symbol if it exists
        SubBook sb = IMArea.orderBook.get(symbol);
        if ( sb != null )
        {
            System.out.println("******************************************");
            System.out.println("Buy orders for " + symbol);
            displaySubBook(sb.buy);
            System.out.println("Sell orders for " + symbol);
            displaySubBook(sb.sell);
            System.out.println(" ");
        }
    }

    // Display the orders within a sub book
    private void displaySubBook(LinkedList<OrderEntry> orders)
    {
        for ( int i = 0; i < orders.size(); i++)
        {
            OrderEntry entry = orders.get(i);
            System.out.println( "Price: " + entry.getPrice() + 
                                ", Qty: " + entry.getQuantity() +
                                ", Type: " + 
                                OrderType.getAsString( entry.getType() ) );
        }
    }

    private void log(String m)
    {
        boolean fDebug = false;
        if ( fDebug )
            System.out.println(m);
    }
}
