/*
 * TradeDataPool.java
 *
 * Created on March 2, 2007, 12:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.oss.trader.data;

/**
 *
 * @author root
 */
public class StatsDataPool 
{
    StatsData pool = null;
    
    public StatsDataPool() 
    {
    }
    
    public StatsDataPool(int size) 
    {
        createDataPool(size);
    }
    
    private void createDataPool(int size)
    {
        for ( int i = 0; i < size; i++ )
            put( new StatsData() );
    }
    
    public synchronized StatsData get()
    {
        StatsData data = pool;
        if ( data != null )
        {
            pool = data.next;
            data.next = null;
        }

        return data;
    }
    
    public synchronized void put(StatsData data)
    {
        if ( data == null )
            return;
        
        StatsData head = pool;
        data.next = head;
        pool = data;
        //System.out.println("put succeeded");
    }
    
    public int getFreeQueueSize()
    {
        int count = 0;
        StatsData node = pool;
        while ( node != null )
        {
            count++;
            node = node.next;
        }
        return count;
    }
    
}
