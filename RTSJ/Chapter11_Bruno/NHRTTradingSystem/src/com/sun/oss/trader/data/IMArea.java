/*
 * IMArea.java
 *
 * Created on October 23, 2006, 2:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.oss.trader.data;

import com.sun.oss.trader.tradingengine.*;
import com.sun.oss.trader.util.*;
import com.sun.oss.trader.mq.*;

import java.util.*;
import java.io.*;

import javax.realtime.*;

public class IMArea 
{
    public static WaitFreeReadQueue freePerfStatsPool = null;
    public static WaitFreeWriteQueue queuedPerfStatsPool = null;
    public static WaitFreeReadQueue freeTradeStatsPool = null;
    public static WaitFreeWriteQueue queuedTradeStatsPool = null;

    //
    // Real time components
    //

    public static OrderManager orderMgr = null;

    // 
    // Constants
    //
    public static final String SYMBOL_TAG = "<symbol>";
    public static final String SYMBOL_CLOSE = "</symbol>";
    public static final String PRICE_TAG = "<price>";
    public static final String PRICE_CLOSE = "</price>";
    public static final String TRADES_TAG = "<trades>";
    public static final String TRADES_CLOSE = "</trades>";
    public static final String TRADE_TAG = "<trade>";
    public static final String TRADE_CLOSE = "</trade>";
    public static final String TYPE_TAG = "<type>";
    public static final String TYPE_CLOSE = "</type>";
    public static final String DATETIME_TAG = "<datetime>";
    public static final String DATETIME_CLOSE = "</datetime>";
    public static final String TRADEPRICE_TAG = "<tradeprice>";
    public static final String TRADEPRICE_CLOSE = "</tradeprice>";
    public static final String LIMITPRICE_TAG = "<limitprice>";
    public static final String LIMITPRICE_CLOSE = "</limitprice>";
    public static final String STOPPRICE_TAG = "<stopprice>";
    public static final String STOPPRICE_CLOSE = "</stopprice>";
    public static final String VOLUME_TAG = "<volume>";
    public static final String VOLUME_CLOSE = "</volume>";
    public static final String BUY = "Buy";
    public static final String SELL = "Sell";
    public static final ISO8601DateFormat isoDf = new ISO8601DateFormat(); 

    public static final int INITIAL_CAPACITY = 111;

    //
    // Working data
    //

    // The Market Book is a hashmap of prices (stored as StringBuffer objects)
    // that are looked up by stock symbol (StringBuffer) as the key
    public static HashMap<String,MarketPrice> marketBook = 
            new HashMap<String,MarketPrice>(INITIAL_CAPACITY); 

    // The order book is a HashMap of linked lists. Each HashMap bucket
    // is keyed by a stock name (a StringBuffer). The linked list pointed to 
    // by each bucket represents the list of open orders for that
    // particular stock, in value, low to high for buy orders, and
    // high to low for sell orders
    //
    public static HashMap<String,SubBook> orderBook = 
            new HashMap<String,SubBook>(INITIAL_CAPACITY);

    public static String[] orderBookKeys = null;

    //
    // JMX related
    //
    
    public static TradeManagement tradeNotifier = null; 
    public static OnMessage onMessage = null; 
}
