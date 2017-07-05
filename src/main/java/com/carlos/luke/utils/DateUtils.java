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
		Date date = formatStr2Date("20170705","yyyyMMdd");
		
		Date kk = getLastOfDay(new Date());
		System.out.println(date);
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