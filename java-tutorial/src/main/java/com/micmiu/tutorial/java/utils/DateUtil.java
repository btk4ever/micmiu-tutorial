package com.micmiu.tutorial.java.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class DateUtil {

	/**
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date parseDate(String dateStr, String formatStr) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.UK);
			if (null != dateStr && !"".equals(dateStr)) {
				date = sdf.parse(dateStr);
			}
		} catch (ParseException e) {
			e.getStackTrace();
			date = null;
		}
		return date;
	}

	/**
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String formatDate(Date date, String formatStr) {
		String dateStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.UK);
		if (null == date) {
			return dateStr;
		}
		dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
