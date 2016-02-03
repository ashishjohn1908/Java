package com.sun.oss.trader.data;

public class StatsData 
{
    public static enum MESSAGE_TYPE { PERF, TRADE };
    public static enum STATS_TYPE { BUY, SELL };

    public MESSAGE_TYPE messageType;
    public STATS_TYPE tradeType;
    public String symbol;
    public double tradePrice;
    public double entryPrice;
    public long tradeQty;
    public long startTime = 0;
    public long endTime = 0;

    public StatsData next = null;
}
