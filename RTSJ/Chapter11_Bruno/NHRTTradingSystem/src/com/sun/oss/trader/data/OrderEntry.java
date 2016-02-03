package com.sun.oss.trader.data;

import com.sun.oss.trader.tradingengine.*;

// The OrderEntry class is basically immutable 
// Once you create it, you can't change it
//
public class OrderEntry 
{
    // Private data
    //
    private boolean active;
    public double price;
    public long quantity;
    public String symbol;
    public int type;

    // Constructors
    //
    public OrderEntry() { }
    public OrderEntry(String s, double p, long q, int t)
    {
        assert ( OrderType.isValid(t) );
        assert ( q > 0 );

        price = p;
        quantity = q;
        type = t;
        symbol = new String(s);
        active = true;
    }

    // Public methods
    //
    public double getPrice() {
            return price;
    }

    public long getQuantity() {
            return quantity;
    }

    public String getSymbol() {
            return symbol;
    }

    public int getType() {
            return type;
    }

    public boolean isLimitOrder() {
            return (type == OrderType.LIMIT_BUY || type == OrderType.LIMIT_SELL);
    }

    public boolean isStopOrder() {
            return (type == OrderType.STOP_BUY || type == OrderType.STOP_SELL);
    }

    public boolean isMarketOrder() {
            return (type == OrderType.MARKET_BUY || type == OrderType.MARKET_SELL);
    }

    public boolean isBuyOrder() {
            return (type == OrderType.LIMIT_BUY || type == OrderType.STOP_BUY || type == OrderType.MARKET_BUY);
    }

    public boolean isSellOrder() {
            return (type == OrderType.LIMIT_SELL || type == OrderType.STOP_SELL || type == OrderType.MARKET_SELL);
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive( boolean active )
    {
        this.active = active;
    }

    public boolean comesBefore(double price)
    {
        if ( this.isBuyOrder() )
            return (this.price < price);
        else
            return (this.price > price);
    }
}
