/*
 * OnMessage.java
 *
 * Created on September 14, 2006, 2:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.oss.trader.mq;

import java.lang.management.ManagementFactory;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;

/**
 *
 * @author jclarke
 */
public class OnMessage extends NotificationBroadcasterSupport implements OnMessageMBean {
    
    private long totalCalls;
    private long totalNano;
    private long lastCallNano;
    private long lastCallTime;
    private String name;
    private ObjectName objName;
    
    public OnMessage(String name) throws MalformedObjectNameException, 
            InstanceAlreadyExistsException, MBeanRegistrationException, 
            NotCompliantMBeanException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        this.name = "com.sun.oss.trader.mq:type=OnMessage,name=" + name;
        objName = new ObjectName(this.name);
        server.registerMBean(this, objName);
    }


    public long getTotalCalls() {
        return totalCalls;
    }

    public long getTotalNano() {
        return totalNano;
    }

    public synchronized void reset() {
        this.totalCalls = 0;
        this.totalNano =0;
        lastCallNano = 0;
        lastCallTime = 0;
    }


    public long getLastCallNano() {
        return this.lastCallNano;
    }

    public long getLastCallTime() {
        return this.lastCallTime;
    }
    
    public double getAverage() {
        if(this.totalCalls != 0)
            return this.totalNano / this.totalCalls;
        else
            return Double.NaN;
    }    
    
    public long startCall() {
        return  System.nanoTime();
    }
    
    public synchronized long endCall(long startNano){
        this.lastCallNano = System.nanoTime() - startNano;
        this.lastCallTime = System.currentTimeMillis();
        this.totalCalls++;
        this.totalNano += this.lastCallTime;
        Notification notification = new Notification(name, objName, 
                this.totalCalls, this.lastCallTime, String.valueOf(this.lastCallNano));
        this.sendNotification(notification);
        return this.lastCallNano;
    }

}
