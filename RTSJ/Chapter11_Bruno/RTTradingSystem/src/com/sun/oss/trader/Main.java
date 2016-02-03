package com.sun.oss.trader;

import com.sun.oss.trader.tradingengine.*;
import com.sun.oss.trader.data.*;
import javax.realtime.*;

public class Main
{
    static int MAX_PRI = PriorityScheduler.instance().getMaxPriority();
    static RelativeTime TWO_MSEC = new RelativeTime(2,0);
    
    public Main()
    {
        try {
            java.lang.Class.forName("com.sun.oss.trader.tradingengine.MarketManager", true, this.getClass().getClassLoader() );
            java.lang.Class.forName("com.sun.oss.trader.tradingengine.OrderManager", true, this.getClass().getClassLoader() );
            
            // Create the Runnable worker classes
            //
            MarketManager marketMgr = new MarketManager();
            OrderManager orderMgr = new OrderManager(marketMgr);
            
            orderMgr.displayOrderBook();

            // Create and start Threads
            //
            RealtimeThread orderThread = 
                    new RealtimeThread( new PriorityParameters( MAX_PRI ),
                                        new PeriodicParameters( TWO_MSEC ),
                                        null,null,null,orderMgr);
            RealtimeThread marketThread = 
                    new RealtimeThread( new PriorityParameters( MAX_PRI - 1 ),
                                        null,null,null,null,marketMgr);
            marketThread.start();
            orderThread.start();

            while ( true ) {
                System.gc();
                Thread.sleep(1000);
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    public static void main( String[] args )
    {
        Main main = new Main();
    }
}
