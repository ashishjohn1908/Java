/*
 * SubBook.java
 *
 * Created on October 23, 2006, 1:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.oss.trader.data;

import java.util.*;

/**
 *
 * @author root
 */
public class SubBook 
{
    public LinkedList<OrderEntry> buy = new LinkedList<OrderEntry>();
    public LinkedList<OrderEntry> sell = new LinkedList<OrderEntry>();
}
