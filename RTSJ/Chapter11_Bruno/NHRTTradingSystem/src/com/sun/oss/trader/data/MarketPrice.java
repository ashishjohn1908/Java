package com.sun.oss.trader.data;

public class MarketPrice 
{
    // Private data
    //
    private double price;
    private long quantity;
    private StringBuffer timestamp = null;
    private StringBuffer symbol = null;

    // Constructors
    //
    private MarketPrice() { }
    public MarketPrice(StringBuffer s, double p, long q, StringBuffer t)
    {
        price = p;
        quantity = q;

        if ( t != null )
            timestamp = new StringBuffer(t);
        
        if ( s != null )
            symbol = new StringBuffer(s);
    }

    // Public methods
    //
    public double getPrice() {
        return price;
    }
    public void setPrice( double price ) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }
    public void setQuantity( long quantity ) {
        this.quantity = quantity;
    }

    public StringBuffer getTimestamp() {
        return timestamp;
    }

    public StringBuffer getSymbol() {
        return symbol;
    }
}
