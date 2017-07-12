package com.carlos.luke.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class DateUtils {

	/**
	 * 获取某一天所在周的周一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		int dayOfWeek = dateTime.getDayOfWeek();
		DateTime firstDate = dateTime.minusDays(dayOfWeek - 1);
		firstDate = firstDate.withTime(0, 0, 0, 0);
		return firstDate.toDate();

	}

	/**
	 * 获取某一天所在月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		int dayOfMonth = dateTime.getDayOfMonth();
		DateTime firstDate = dateTime.minusDays(dayOfMonth - 1);
		firstDate = firstDate.withTime(0, 0, 0, 0);
		return firstDate.toDate();

	}

	/**
	 * 获取某一天所在年的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfYear(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		int dayOfYear = dateTime.getDayOfYear();
		DateTime firstDate = dateTime.minusDays(dayOfYear - 1);
		firstDate = firstDate.withTime(0, 0, 0, 0);
		return firstDate.toDate();

	}
	
	/**
	 * 获取某一天最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastOfDay(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		dateTime = dateTime.withTime(23, 59, 59, 0);
		return dateTime.toDate();

	}

	public static Date formatStr2Date(String dateStr, String patter) {
		SimpleDateFormat format = new SimpleDateFormat(patter);
		try {
			Date start = format.parse(dateStr);
			return start;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
        DateTime startTime = new DateTime(new Date());
        Long startTimeLong = startTime.minusDays(1).withTime(0, 0, 0, 0).toDate().getTime();
        Long endTimeLong = new DateTime(startTime).withTime(0, 0, 0, 0).toDate().getTime();
        
		System.out.println("startTime:"+startTime);
		
		
        DateTime startTime2 = new DateTime(new Date());
        startTime2 = startTime2.minusDays(0).withTime(0, 0, 0, 0);
        DateTime endTime2 = new DateTime(startTime).minusDays(0).withTime(23, 59, 59, 999);
        
        Date start = startTime2.toDate();
        Date end = endTime2.toDate();
        System.out.println("start:"+start);
        if (start.getTime() >= startTimeLong && end.getTime() < endTimeLong) {
            System.out.println("ok");
        }
        if (start.getTime() >= endTimeLong) {
            System.out.println("not ok");
        }
        Date old = new Date();
        Date plus1 = new DateTime(old).plusSeconds(1).toDate();
        System.out.println(1000.0 / (plus1.getTime() - old.getTime()));
	}

	/**
	 * 获取当前日期的前一天Date
	 */
	public static Date getLastDay() {
		Date current = new Date();
		DateTime dateTime = new DateTime(current);
		dateTime = dateTime.minusDays(1);
		dateTime = dateTime.withTime(0, 0, 0, 0);
		return dateTime.toDate();
	}
}