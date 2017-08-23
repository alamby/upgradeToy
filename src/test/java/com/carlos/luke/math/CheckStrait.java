package com.carlos.luke.math;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

/**
* @desc    
* @since   2017年8月15日
*
*/
public class CheckStrait {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        double distance = GeoUtils.distance(117.498666, 31.922766, 117.496161, 31.9318)*100;//cm
        Date first = sdf.parse("2017-08-11 00:00:06");
        Date now = sdf.parse("2017-08-11 00:00:53");
        double elapse = (now.getTime()/1000 - first.getTime()/1000);//s
        System.out.println(distance/elapse);
        
        DateTime dt6 = new DateTime(now).minusMinutes(2);
        System.out.println(dt6);
    }
}
