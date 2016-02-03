/*
 * OrderType.java
 * 
 * Author: Eric Bruno
 * Project: RTTradingSystem
 * Created on Oct 16, 2006
 *
 */
package com.sun.oss.trader.data;

public class OrderType
{
    public static final int LIMIT_BUY = 1;
    public static final int LIMIT_SELL = 2;
    public static final int STOP_BUY = 3;
    public static final int STOP_SELL = 4;
    public static final int MARKET_BUY = 5;
    public static final int MARKET_SELL = 6;
    public static boolean isValid(int t) {
        if ( t >= LIMIT_BUY && t <= MARKET_SELL )
            return true;
        return false;
    }
    public static String getAsString(int t) {
        switch ( t ) {
        case LIMIT_BUY:
            return "Limit Buy Order";
        case LIMIT_SELL:
            return "Limit Sell Order";
        case STOP_BUY:
            return "Stop Buy Order";
        case STOP_SELL:
            return "Stop Sell Order";
        case MARKET_BUY:
            return "Market Buy Order";
        case MARKET_SELL:
            return "Market Sell Order";
        }
        return "Invalid order type";
    }
}
