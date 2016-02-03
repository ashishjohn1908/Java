package com.sun.oss.trader;

import java.util.*;
import java.io.*;

import javax.realtime.*;

import com.sun.oss.trader.data.*;
import com.sun.oss.trader.util.*;
import com.sun.oss.trader.mq.*;
import com.sun.oss.trader.tradingengine.*;
        
public class Main
{
    static int MAX_PRI = PriorityScheduler.instance().getMaxPriority();
    static RelativeTime TWO_MSEC = new RelativeTime(2,0);

    static RealtimeThread rt;

    static {
        rt = new RealtimeThread(new PriorityParameters( MAX_PRI - 1 ),
                                null, //new PeriodicParameters(new RelativeTime(20,0)),
                                null, ImmortalMemory.instance(), null, null ) 
        {
            public void run() 
            {
                try {
                    Main main = new Main();

                    // Create the performance monitor and trade notifier
                    IMArea.onMessage = new OnMessage("OrderManager");
                    IMArea.tradeNotifier = new TradeManagement("LimitStopTrades");

                    // Create the Order Manager
                    PriorityParameters sched = new PriorityParameters(MAX_PRI);
                    PeriodicParameters period  = new PeriodicParameters(TWO_MSEC);
                    ImmortalMemory im = ImmortalMemory.instance();
                    IMArea.orderMgr = new OrderManager( sched, period, im );
                    
                    // Create the Market Manager
                    sched = new PriorityParameters(MAX_PRI - 1);
                    HeapMemory hm = HeapMemory.instance();
                    MarketManager marketMgr = new MarketManager( sched, hm );

                    // Read order data and enter the orders
                    main.readFileAndEnterOrders();
                    IMArea.orderMgr.displayOrderBook();

                    // Start threads
                    IMArea.orderMgr.start();
                    marketMgr.start();
                }
                catch ( Throwable e ) {
                    e.printStackTrace();
                }
            };
        };
    }
    
    public static String generateTradeXML(StatsData trade)
    {
        /*
         * SAMPLE:
            <trades>
                <trade>
                   <type>Buy</type>
                   <symbol>SUNW</Symbol>
                   <datetime>2006-09-20T13:59:25.993-04:00</datetime>
                   <tradeprice>4.9500</tradeprice>
                   <limitprice>4.9000</limitprice>
                   <volume>1000</volume>
                </trade>
            </trades>
        */
        StringBuffer tradeXML = new StringBuffer("");
        
        // Root tags
        tradeXML.append(IMArea.TRADES_TAG);
        tradeXML.append(IMArea.TRADE_TAG);

        // Trade type: Buy or Sell
        tradeXML.append(IMArea.TYPE_TAG);
        if ( trade.tradeType == StatsData.STATS_TYPE.BUY )
            tradeXML.append(IMArea.BUY);
        else
            tradeXML.append(IMArea.SELL);
        tradeXML.append(IMArea.TYPE_CLOSE);

        // Stock Symbol
        tradeXML.append(IMArea.SYMBOL_TAG);
        tradeXML.append(trade.symbol);
        tradeXML.append(IMArea.SYMBOL_CLOSE);

        // Date/time stamp of trade
        tradeXML.append(IMArea.DATETIME_TAG);
        tradeXML.append(ISO8601DateFormat.formatISO(new Date())); 
        tradeXML.append(IMArea.DATETIME_CLOSE);

        // Trade execution price
        tradeXML.append(IMArea.TRADEPRICE_TAG);
        tradeXML.append(trade.tradePrice);
        tradeXML.append(IMArea.TRADEPRICE_CLOSE);

        // Indicate limit or stop order, and the original order price
        //if ( entry.isLimitOrder() )
        {
            tradeXML.append(IMArea.LIMITPRICE_TAG);
            tradeXML.append(trade.entryPrice);
            tradeXML.append(IMArea.LIMITPRICE_CLOSE);
        }

        // Trade volume
        tradeXML.append(IMArea.VOLUME_TAG);
        tradeXML.append(trade.tradeQty);
        tradeXML.append(IMArea.VOLUME_CLOSE);

        // Close the root tags
        tradeXML.append(IMArea.TRADE_CLOSE);
        tradeXML.append(IMArea.TRADES_CLOSE);

        return tradeXML.toString();
    }

    public void setRt(RealtimeThread rt) {
        this.rt = rt;
    }

    private void readFileAndEnterOrders()
    {
        try {
            // Read update data from file
            File file = new File("orders.csv");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            
            String line = reader.readLine();
            while ( line != null )
            {
                int seperator = line.indexOf(',');
                String symbol = line.substring(0, seperator);
                int begin = seperator;
                seperator = line.indexOf(',', begin+1);
                String price = line.substring(begin+1, seperator);
                begin = seperator;
                seperator = line.indexOf(',', begin+1);
                String qty = line.substring(begin+1, seperator);
                begin = seperator;
                seperator = line.indexOf(',', begin+1);
                String type = line.substring(begin+1);
                int orderType = 0;
                if ( type.equalsIgnoreCase("limitbuy"))
                    orderType = OrderType.LIMIT_BUY;
                else if ( type.equalsIgnoreCase("limitsell"))
                    orderType = OrderType.LIMIT_SELL;
                else if ( type.equalsIgnoreCase("stopbuy"))
                    orderType = OrderType.STOP_BUY;
                else if ( type.equalsIgnoreCase("stopsell"))
                    orderType = OrderType.STOP_SELL;

                // Read the next line of fake update data
                line = reader.readLine();

                IMArea.orderMgr.enterOrder( Double.parseDouble(price), 
                                            Integer.parseInt(qty), 
                                            symbol,//new StringBuffer(symbol), 
                                            orderType);
                
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        System.out.println("NoHeap trading engine starting");

        Main.rt.start();
        
        StatsData stats = null;
        
        try {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            while ( true ) 
            {
                try {
                    // Pull a stats messages off the queue
                    if ( IMArea.queuedTradeStatsPool == null || 
                         IMArea.freeTradeStatsPool == null || 
                         IMArea.queuedPerfStatsPool == null || 
                         IMArea.freePerfStatsPool == null )
                        continue;
                    
                    stats = (StatsData)IMArea.queuedTradeStatsPool.read();
                    if ( stats != null )
                    {
                        // Send the trade data via JMX
                        String td = generateTradeXML(stats);
                        IMArea.tradeNotifier.notifyTrade( td );

                        // Place trade obj back on the free queue
                        IMArea.freeTradeStatsPool.write(stats);
                    }

                    stats = (StatsData)IMArea.queuedPerfStatsPool.read();
                    if ( stats != null )
                    {
                        IMArea.onMessage.recordCall(stats.startTime, stats.endTime);

                        // Place trade obj back on the free queue
                        IMArea.freePerfStatsPool.write(stats);
                    }

                }
                catch ( Exception e ) { 
                    e.printStackTrace();
                }

                Thread.sleep(1);
            }
	} 
        catch ( Exception e ) {
	    e.printStackTrace();
	}
    }
    
    private void log(String msg)
    {
        System.out.println(msg);
    }
}
