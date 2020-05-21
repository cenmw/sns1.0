package com.cenmw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	/**
	 * 将日期格式化成指定格式
	 * 
	 * @param date
	 * @param format
	 *            yyyy-MM-dd
	 * @return
	 */
	public static String getFormattedDate(Date date) {
		String format = "yyyy-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(format); // format:yyyy-MM-dd
		// HH:mm:ss
		String date_str = "";
		if (date != null) {
			date_str = df.format(date);
		}
		return date_str;
	}

	/**
	 * 将日期格式化成指定格式
	 * 
	 * @param date
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getFormatDate(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format); // format:yyyy-MM-dd
															// HH:mm:ss
		String date_str = "";
		if (date != null) {
			date_str = df.format(date);
		}
		return date_str;
	}

	/**
	 * 将日期格式化成指定格式
	 * 
	 * @param date
	 * @param format
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getFormatedDate(Date date) {
		if (date == null) {
			return "";
		}
		return getFormatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 计算天数
	 * 
	 * @param date
	 * @param day
	 *            为加减天数 正数为加天，负数为减天
	 * @return
	 */
	public static Date getCompareDateToDay(Date date, int day) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, day);
		return c.getTime();
	}

	/**
	 * 计算月份
	 * 
	 * @param date
	 * @param day
	 *            为加减月数 正数为加月，负数为减月
	 * @return
	 */
	public static Date getCompareDateToMonth(Date date, int month) {//
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	/**
	 * 将字符串转换为日期类型： 例 ：2010-10-2
	 * 
	 * @param strTime
	 *            例 ：2010-10-2
	 * @return
	 */
	public static Date StringToDate(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			if (!time.trim().equals("")) {
				date = format.parse(time);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date StringToDatetime(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			if (!time.trim().equals("")) {
				date = format.parse(time);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date StringToDate(String time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算相差天数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int countDays(String begin, String end) {
		int days = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c_b = Calendar.getInstance();
		Calendar c_e = Calendar.getInstance();

		try {
			c_b.setTime(df.parse(begin));
			c_e.setTime(df.parse(end));

			while (c_b.before(c_e)) {
				days++;
				c_b.add(Calendar.DAY_OF_YEAR, 1);
			}
		} catch (ParseException pe) {
			System.out.println("日期格式必须为：yyyy-MM-dd；如：2010-4-4.");
		}

		return days;
	}

	/**
	 * 计算相差月数 --yyyy-MM-dd
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int countMonths(String begin, String end) {
		return countMonths(StringToDate(begin), StringToDate(end));
	}

	public static int countMonths(Date begin, Date end) {
		int months = 0;
		Calendar c_b = Calendar.getInstance();
		Calendar c_e = Calendar.getInstance();
		c_b.setTime(begin);
		c_e.setTime(end);
		while (c_b.before(c_e)) {
			months++;
			c_b.add(Calendar.MONTH, 1);
		}
		return months;
	}

	/**
	 * 计算相差年数 --yyyy-MM-dd
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int countYears(String begin, String end) {
		return countYears(StringToDate(begin), StringToDate(end));
	}

	public static int countYears(Date begin, Date end) {
		int years = 0;
		Calendar c_b = Calendar.getInstance();
		Calendar c_e = Calendar.getInstance();
		c_b.setTime(begin);
		c_e.setTime(end);
		while (c_b.before(c_e)) {
			years++;
			c_b.add(Calendar.YEAR, 1);
		}
		return years;
	}

	public static String getWeek(Date date) {
		String arr[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		return arr[weekDay - 1];
	}

	public static void main(String[] str) {
		// System.out.println(countDays("2011-11-16","2011-11-16"));
		// Calendar c = Calendar.getInstance();
		// // c.add(Calendar.DAY_OF_MONTH,-6);
		// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// // c.set(Calendar.DAY_OF_WEEK,0);
		// c.add(Calendar.DAY_OF_WEEK, (-1) * c.get(Calendar.DAY_OF_WEEK) + 1);
		// c.add(Calendar.DAY_OF_YEAR, 1);
		// String firstweek=df.format(c.getTime())+" 00:00:00";
		// System.out.println("本周第一天：" + firstweek);
		// c.add(Calendar.DAY_OF_WEEK, 7 - c.get(Calendar.DAY_OF_WEEK));
		// c.add(Calendar.DAY_OF_YEAR, 1);
		// String lastweek=df.format(c.getTime())+" 23:59:59";
		// System.out.println("本周最后一天：" + lastweek);
		// System.out.println(countMonths("2011-08-01", "2012-03-01"));
		// Date begin = StringToDate("2011-10-31 00:00:00",
		// "yyyy-MM-dd HH:mm:ss");
		// Date end = StringToDate("2011-12-31 00:00:00",
		// "yyyy-MM-dd HH:mm:ss");
		// Date cur = StringToDate("2011-11-01 00:00:00",
		// "yyyy-MM-dd HH:mm:ss");
		// System.out
		// .println(cur.compareTo(begin) >= 0 && cur.compareTo(end) <= 0);
		int years = DateUtil.countDays("2012-9-1", "2012-8-1");
		System.out.println(years);
	}
}
