package com.lawal;

import java.math.BigDecimal;

import com.google.gwt.i18n.client.CustomDateTimeFormat;

public class SliderUtil {
	public static <T extends Number> Number initValue(Class<T> claz, int defVal) {
		if (claz == Long.class) {
			return new Long(defVal);
		} else if (claz == Double.class) {
			return new Double(defVal);
		} else if (claz == Integer.class) {
			return new Integer(defVal);
		} else if (claz == Float.class) {
			return new Float(defVal);
		} else if (claz == Short.class) {
			return new Short((short) defVal);
		} else if (claz == Byte.class) {
			return new Byte((byte) defVal);
		} else if (claz == BigDecimal.class) {
			return new BigDecimal(defVal);
		}
		return null;
	}

	public static <T extends Number> Number getValue(Class<T> claz, Number defVal) {
		if (claz == Long.class) {
			return defVal.longValue();
		} else if (claz == Double.class) {
			return defVal.doubleValue();
		} else if (claz == Integer.class) {
			return defVal.intValue();
		} else if (claz == Float.class) {
			return defVal.floatValue();
		} else if (claz == Short.class) {
			return defVal.shortValue();
		} else if (claz == Byte.class) {
			return defVal.byteValue();
		} else if (claz == BigDecimal.class) {
			return new BigDecimal(defVal.doubleValue());
		}
		return null;
	}

	
	/**
	 * 
	 * PredefinedFormat 
	 *
	 */
	public enum DateLabelFormat {
		
		
		// TODO(jat): Javadoc to explain these formats
		/**
		 * ISO 8601 date format, fixed across all locales.
		 * <p>
		 * Example: {@code 2008-10-03T10:29:40.046-04:00}
		 * <p>
		 * http://code.google.com/p/google-web-toolkit/issues/detail?id=3068
		 * <p>
		 * http://www.iso.org/iso/support/faqs/faqs_widely_used_standards/
		 * widely_used_standards_other/date_and_time_format.htm
		 */
		ISO_8601,
		/**
		 * RFC 2822 date format, fixed across all locales.
		 * <p>
		 * Example: {@code Thu, 20 May 2010 17:54:50 -0700}
		 * <p>
		 * http://tools.ietf.org/html/rfc2822#section-3.3
		 */
		RFC_2822, DATE_FULL, DATE_LONG, DATE_MEDIUM, DATE_SHORT, TIME_FULL, TIME_LONG, TIME_MEDIUM, TIME_SHORT, DATE_TIME_FULL, DATE_TIME_LONG, DATE_TIME_MEDIUM, DATE_TIME_SHORT, DAY, HOUR_MINUTE, HOUR_MINUTE_SECOND, HOUR24_MINUTE, HOUR24_MINUTE_SECOND, MINUTE_SECOND, MONTH, MONTH_ABBR, MONTH_ABBR_DAY, MONTH_DAY, MONTH_NUM_DAY, MONTH_WEEKDAY_DAY, YEAR, YEAR_MONTH, YEAR_MONTH_ABBR, YEAR_MONTH_ABBR_DAY, YEAR_MONTH_DAY, YEAR_MONTH_NUM, YEAR_MONTH_NUM_DAY, YEAR_MONTH_WEEKDAY_DAY, YEAR_QUARTER, YEAR_QUARTER_ABBR,
	}

	public static final long SECOND = 1000;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;
	public static final long DAY = 24 * HOUR;
}
