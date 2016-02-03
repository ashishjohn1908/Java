/*
 * ISO8601DateFormat.java
 *
 * Created on September 20, 2006, 9:50 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.oss.trader.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * ISO 8601 Formats
 * YYYY-MM-DDThh:mm:ss.sss+hh:mm
 * YYYY-MM-DDThh:mm:ss.sss-hh:mm
 * YYYY-MM-DDThh:mm:ss.sssZ
 * @author jclarke
 */
public class ISO8601DateFormat extends DateFormat {
     private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    /**
     * Creates a new instance of ISO8601DateFormat
     */
    public ISO8601DateFormat() {
        super();
    }
    
    public static String formatISO(Date date) {
        StringBuffer tmp = new StringBuffer(sdf.format(date));
        tmp.insert(tmp.length() - 2,":");
        
        return tmp.toString();
    }

    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        
        StringBuffer tmp = sdf.format(date,toAppendTo, fieldPosition);
        tmp.insert(tmp.length() - 2,":");
        
        return toAppendTo;
    }

    public Date parse(String source, ParsePosition pos) {
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int millis = 0;
        TimeZone tz = TimeZone.getTimeZone("GMT");
        
        int length = source.length();
        int i = pos.getIndex();
        try {
            year = Integer.parseInt(source.substring(i, i+4));
            i+= 4;
            if(i < length) {
                i = check(source, i, '-');
                month = Integer.parseInt(source.substring(i, i+2));
                month--;
                i += 2;
                if(i < length) {
                    i = check(source, i, '-');
                    day = Integer.parseInt(source.substring(i, i+2));
                    i += 2;
                    if(i < length) {
                        i = check(source,i, 'T');
                        
                        hour = Integer.parseInt(source.substring(i, i+2));
                        i += 2;
                        i = check(source,i, ':');
                        minute = Integer.parseInt(source.substring(i, i+2));
                        i+= 2;
                        if(source.charAt(i) == ':') {
                            i++;
                            second = Integer.parseInt(source.substring(i, i+2));
                            i+= 2;
                            if(source.charAt(i) == '.') {
                                i++;
                                int j = i;
                                for(; j < length && Character.isDigit(source.charAt(j)); j++);
                                millis = Integer.parseInt(source.substring(i, j));
                                i = j;
                            }
                        }
                        if(source.charAt(i) == 'Z' || source.charAt(i) == '-' || source.charAt(i) == '+') {
                            tz = parseTZ(source.substring(i));
                        }else {
                            throw new NumberFormatException();
                        }
                    }
                }
            }
        }catch(NumberFormatException ex) {
            pos.setErrorIndex(pos.getIndex() + i);
        }finally {
            pos.setIndex(pos.getIndex() + i);
        }
        Calendar cal = Calendar.getInstance(tz);
        cal.set(year, month, day, hour, minute, second);
        cal.set(Calendar.MILLISECOND, millis);
        
        return cal.getTime();
        
        
    }
    
    private TimeZone parseTZ(String tzStr) {
        if(tzStr.charAt(0) == 'Z')
            return TimeZone.getTimeZone("GMT");
        else if(tzStr.charAt(0) == '+'|| tzStr.charAt(0) == '-') {
            return TimeZone.getTimeZone("GMT" + tzStr);
        }else {
            throw new NumberFormatException();
        }
    }
    
    private int check(String source, int ndx, int c) {
        if(source.charAt(ndx) != c) {
            throw new NumberFormatException();
        }
        return ndx+1;
    }
    
    public static void main(String[] args) {
        ISO8601DateFormat d = new ISO8601DateFormat();
        Date now = new Date();
        String format = d.format(now);
        System.out.println(format);

        try {
            System.out.println(d.parse(format));
            System.out.println(d.parse("2006"));
            System.out.println(d.parse("2006-05"));
            System.out.println(d.parse("2006-05-11"));
            System.out.println(d.parse("2006-05-11T10:31-04:00"));
            System.out.println(d.parse("2006-05-11T10:31:02-04:00"));
            System.out.println(d.parse("2006-05-11T10:31:02.123-04:00"));
            System.out.println(d.parse("2006-05-11T10:31:02.123Z"));
            System.out.println(d.parse("2006-05-11T10:31:02.123+01:00"));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
