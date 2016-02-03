/*
 * MissedTrade.java
 *
 * Created on April 26, 2007, 11:31 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.oss.trader.mq;

import com.sun.oss.trader.data.OrderType;
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
public class MissedTrade extends NotificationBroadcasterSupport implements MissedTradeMBean {
    private String name;
    private ObjectName objName;
    private long seqNo = 0;
    /** Creates a new instance of MissedTrade */
    public MissedTrade(String name) throws MalformedObjectNameException, 
            InstanceAlreadyExistsException, MBeanRegistrationException, 
            NotCompliantMBeanException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        this.name = "com.sun.oss.trader.mq:type=MissedTrade,name=" + name;
        objName = new ObjectName(this.name);
        server.registerMBean(this, objName);
    }
    
    public void sendMissedTrade(int orderType, double price, double marketPrice, StringBuffer symbol) {
        //OrderType
        long now = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer(symbol).append(": Missed ");
        sb.append(OrderType.getAsString(orderType)).append(" Trade Price = ").append(price);
        sb.append(", MarketPrice = ").append(marketPrice);
        Notification notification = new Notification(name, objName, 
                seqNo++, System.currentTimeMillis(), sb.toString());
        this.sendNotification(notification);
    }
    
}
