package org.coindesk.api.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateUtils {
	protected static final DateFormat FORMAT_ISO = new SimpleDateFormat("yyyy-MM-dd'T'");
	
	protected static DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
	
	public static void main(String[] args) {
		System.out.println(parseISOFullDate("2023-02-17T02:05:00+00:00"));
	}
	
	public static Date parseISOFullDate(String isoDate) {
		return parser.parseDateTime(isoDate).toDate();
	}

}
