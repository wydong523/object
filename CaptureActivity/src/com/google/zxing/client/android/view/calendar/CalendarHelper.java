package com.google.zxing.client.android.view.calendar;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CalendarHelper {

	public static ArrayList<DateTime> getFullWeeks(int month, int year,
			int startDayOfWeek) {
		ArrayList<DateTime> datetimeList = new ArrayList<DateTime>();

		DateTime firstDateOfMonth = new DateTime(year, month, 1, 0, 0);
		DateTime lastDateOfMonth = firstDateOfMonth.plusMonths(1).minusDays(1);

		int weekdayOfFirstDate = firstDateOfMonth.getDayOfWeek();

		if (weekdayOfFirstDate < startDayOfWeek) {
			weekdayOfFirstDate += 7;
		}

		while (weekdayOfFirstDate > 0) {
			DateTime dateTime = firstDateOfMonth.minusDays(weekdayOfFirstDate
					- startDayOfWeek);
			if (!dateTime.isBefore(firstDateOfMonth)) {
				break;
			}

			datetimeList.add(dateTime);
			weekdayOfFirstDate--;
		}

		for (int i = 0; i < lastDateOfMonth.getDayOfMonth(); i++) {
			datetimeList.add(firstDateOfMonth.plusDays(i));
		}

		int endDayOfWeek = startDayOfWeek - 1;

		if (endDayOfWeek == 0) {
			endDayOfWeek = 7;
		}

		if (lastDateOfMonth.getDayOfWeek() != endDayOfWeek) {
			int i = 1;
			while (true) {
				DateTime nextDay = lastDateOfMonth.plusDays(i);
				datetimeList.add(nextDay);
				i++;
				if (nextDay.getDayOfWeek() == endDayOfWeek) {
					break;
				}
			}
		}

		return datetimeList;
	}

	public static DateTime convertDateToDateTime(Date date) {
		DateTime dateTime = new DateTime(date);
		dateTime = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(),
				dateTime.getDayOfMonth(), 0, 0);
		return dateTime;
	}

	public static Date getDateFromString(String dateString, String dateFormat)
			throws ParseException {
		SimpleDateFormat formatter;
		if (dateFormat == null) {
			formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		} else {
			formatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
		}

		return formatter.parse(dateString);
	}

	public static DateTime getDateTimeFromString(String dateString,
			String dateFormat) {
		DateTimeFormatter formatter;
		if (dateFormat == null) {
			formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		} else {
			formatter = DateTimeFormat.forPattern(dateFormat);
		}
		return formatter.parseDateTime(dateString);
	}
	
	public static ArrayList<String> convertToStringList(ArrayList<DateTime> dateTimes) {
		ArrayList<String> list = new ArrayList<String>();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		for (DateTime dateTime : dateTimes) {
			list.add(formatter.print(dateTime));
		}
		return list;
	}

}
