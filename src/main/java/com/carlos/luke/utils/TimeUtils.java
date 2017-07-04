package com.carlos.luke.utils;

import java.util.Calendar;
import java.util.Date;

/**
* @desc    
* @since   2017年7月3日
*
*/
public class TimeUtils {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        Calendar calend = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        calend.add(Calendar.DATE, -1);
        
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),0,0,0);
        cal.set(Calendar.MILLISECOND, 0);
        calend.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),23,59,59);
        
        
        Date start = cal.getTime();
        Date end = calend.getTime();
        System.out.println(start);
        System.out.println(end);
    }
}
