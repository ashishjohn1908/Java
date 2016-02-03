/*
 * OnMessageMBean.java
 *
 * Created on September 14, 2006, 2:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.oss.trader.mq;

import javax.management.NotificationEmitter;

/**
 *
 * @author jclarke
 */
public interface OnMessageMBean extends NotificationEmitter {
    /**
     * Getter for property totalCalls.
     * @return Value of property totalCalls.
     */
    public long getTotalCalls();


    /**
     * Getter for property totalNano.
     * @return Value of property totalNano.
     */
    public long getTotalNano();
    
    


    /**
     * reset all values to 0
     */
    public void reset();


    /**
     * Getter for property lastCallNano.
     * @return Value of property lastCallNano.
     */
    public long getLastCallNano();

    /**
     * Getter for property lastCallTime.
     * @return Value of property lastCallTime.
     */
    public long getLastCallTime();

    /**
     * Getter for property average.
     * @return Value of property average.
     */
    public double getAverage();
    
    
}
