/*
 * TradeManagement.java
 *
 * Created on October 13, 2006, 8:57 AM
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
public class TradeManagement extends NotificationBroadcasterSupport implements TradeManagementMBean  {
    private long seqNo = 0;
    private String name;
    private ObjectName objName;
    
    /** Creates a new instance of TradeManagement 
     *
     * @param name a unique name for this TradeManagement
     */
    public TradeManagement(String name) throws MalformedObjectNameException, 
            InstanceAlreadyExistsException, MBeanRegistrationException, 
            NotCompliantMBeanException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        this.name = "com.sun.oss.trader.mq:type=TradeManagement,name=" + name;
        objName = new ObjectName(this.name);
        server.registerMBean(this, objName);
    }
    
    
    /**
     * Send a notification of a trade 
     *
     * @param xml the trade xml
     */
    public synchronized void notifyTrade(String xml){
        this.seqNo++;
        Notification notification = new Notification(name, objName, 
                this.seqNo, System.currentTimeMillis(), xml);
        this.sendNotification(notification);
    }    
    
}
