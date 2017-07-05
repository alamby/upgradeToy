package com.carlos.luke.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

public class TimeUtils {

	public static Calendar parseToCalendar(String dateStr, String formatStr)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = format.parse(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}

	// /**
	// * 获取下一秒
	// *
	// * @param date
	// * @return
	// */
	// public static Date getNextSeconds(Date date) {
	// Calendar cal1 = Calendar.getInstance();
	// cal1.setTime(date);
	// Calendar cal = Calendar.getInstance();
	// cal.setTime(date);
	// cal.set(Calendar.SECOND, cal1.get(Calendar.SECOND) + 1);
	// return cal.getTime();
	// }

	public static Date parseToDate(Date date, String formatStr)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String dateStr = format.format(date);
		return format.parse(dateStr);

	}

	public static Date parseToDate(String dateStr, String formatStr)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = format.parse(dateStr);
		return date;

	}

	public static String format(Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);

	}

	public static boolean isInSameWeek(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		cal1.setTime(date1);
		// 1.比较当前日期在年份中的周数是否相同
		if (cal2.get(Calendar.WEEK_OF_YEAR) == cal1.get(Calendar.WEEK_OF_YEAR)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 获取某月第一天
	 * 
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar theDate = Calendar.getInstance();
		theDate.setTime(date);
		theDate.set(Calendar.DAY_OF_MONTH, 1);
		return theDate.getTime();

	}

	/**
	 * 获取某月最后一天的日期
	 * 
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar theDate = Calendar.getInstance();
		theDate.setTime(date);
		theDate.set(Calendar.DAY_OF_MONTH,
				theDate.getActualMaximum(Calendar.DATE));
		return theDate.getTime();

	}
	
	/**
	 * 获取下个月的第一天
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfNextMonth(Date date) {
		DateTime nextMonthDate = new DateTime(
				TimeUtils.getFirstDateOfMonth(date)).plusMonths(1);
		return nextMonthDate.toDate();

	}
	/**
	 * 获取上个月的第一天
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfLastMonth(Date date) {
		DateTime nextMonthDate = new DateTime(
				TimeUtils.getFirstDateOfMonth(date)).minusMonths(1);
		return nextMonthDate.toDate();

	}

	/**
	 * 获取某个月最后一天的号数
	 * 
	 * @param date
	 * @return
	 */
	public static int getLastDayOfMonth(Date date) {
		Calendar theDate = Calendar.getInstance();
		theDate.setTime(date);
		return theDate.getActualMaximum(Calendar.DATE);

	}

	/**
	 * 获取某年的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfYear(Date date) {
		Calendar date1 = Calendar.getInstance();
		date1.setTime(date);
		return getYearLast(date1.get(Calendar.YEAR));

	}

	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 获取下一年的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfNextYear(Date date) {
		Calendar date1 = Calendar.getInstance();
		date1.setTime(date);
		return getYearFirst(date1.get(Calendar.YEAR) + 1);

	}

	/**
	 * 获取上一年的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfFowardYear(Date date) {
		Calendar date1 = Calendar.getInstance();
		date1.setTime(date);
		return getYearLast(date1.get(Calendar.YEAR) - 1);

	}

	/**
	 * 获取某年的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfYear(Date date) {
		Calendar date1 = Calendar.getInstance();
		date1.setTime(date);
		return getYearFirst(date1.get(Calendar.YEAR));

	}

	/**
	 * 获取第二天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, cal1.get(Calendar.DAY_OF_YEAR) + 1);
		return cal.getTime();
	}

	/**
	 * 获取某一天的最后时刻，即23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();

	}

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
		return firstDate.toDate();
	}

	/**
	 * 获取某一天所在周的周日
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		int dayOfWeek = dateTime.getDayOfWeek();

		DateTime theDateTime = new DateTime(date.getTime());
		theDateTime = theDateTime.plusDays(7 - dayOfWeek);
		return theDateTime.toDate();
	}

	/**
	 * 获取第二天凌晨，即00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstTimeOfNextDay(Date date) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, cal1.get(Calendar.DAY_OF_YEAR) + 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取某天凌晨，即00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstTime(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		return dateTime.withTime(0, 0, 0, 0).toDate();
	}

	// 获取分钟1的日期
	public static Date getFirstSeconds(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		return dateTime.withTime(dateTime.getHourOfDay(), 0, 1, 0).toDate();
	}

	// 获取某个时间分和秒为00
	public static Date getZeroSeconds(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		return dateTime.withTime(dateTime.getHourOfDay(), 0, 0, 0).toDate();
	}

	// 获取某个日期的59分59秒
	public static Date getLastSeconds(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		return dateTime.withTime(dateTime.getHourOfDay(), 59, 59, 0).toDate();
	}

	// 获取下一秒
	public static Date getNextSecond(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		return dateTime.plusSeconds(1).toDate();
	}

	/**
	 * 获取上一秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeSecond(Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		return dateTime.minusSeconds(1).toDate();
	}

	/**
	 * 获取指定时间段的月份总数
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getAmountOfMonth(Date from, Date to) {
		DateTime start = new DateTime(from);
		DateTime end = new DateTime(to);
		return Months.monthsBetween(start.withDayOfMonth(1),
				end.withDayOfMonth(1)).getMonths()+1;
	}

	/**
	 * 获取指定时间段的天总数
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int getAmountOfDays(Date from, Date to) {
		DateTime start = new DateTime(from);
		DateTime end = new DateTime(to);
		return Days.daysBetween(start.withHourOfDay(1), end.withHourOfDay(1))
				.getDays()+1;
	}
	
	public static double  formatDoubleNumber(double f){
		BigDecimal   b   =   new   BigDecimal(f);  
		return   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
	}


	public static void main(String[] args) {
		
		long d=12344l;
		int dd=3;
		System.out.println(12344l*1.00d/dd);
		
		System.out.println(formatDoubleNumber(12344l*1.00d/dd));

		// try {
		// Date date = parseToDate("2016", "yyyy");
		// System.out.print(date);
		//
		// date = parseToDate("201603", "yyyyMM");
		// System.out.print(date);
		// date = parseToDate("20160306", "yyyyMMdd");
		// System.out.print(date);
		//
		// Date lastDate = getLastDateOfMonth(date);
		// System.out.println(lastDate);
		//
		// Date startDateTime = TimeUtils.parseToDate("20160102", "yyyyMM");
		// // Date endDateTime = TimeUtils.parseToDate(endDate, "yyyyMMdd");
		// System.out.println(">>>>>" + startDateTime);
		// String nn = "20160102";
		//
		// String startDate1 = nn.substring(0, nn.length() - 2);
		// System.out.println(startDate1);
		//
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		try {
			Date date = parseToDate("2016-06-05", "yyyy-MM-dd");
			System.out.println(getFirstDayOfWeek(date));
//			System.out.println(getNextMonth(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.print(date);

	}

}
