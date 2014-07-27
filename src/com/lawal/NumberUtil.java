package com.lawal;

import java.math.BigDecimal;

public class NumberUtil {

	public static <T extends Number> Number initValue(Class<T> value, int defVal) {

		if (value == Long.class) {
			return new Long(defVal);
		} else if (value == Double.class) {
			return new Double(defVal);
		} else if (value == Integer.class) {
			return new Integer(defVal);
		} else if (value == Float.class) {
			return new Float(defVal);
		} else if (value == Short.class) {
			return new Short((short) defVal);
		} else if (value == Byte.class) {
			return new Byte((byte) defVal);
		} else if (value == BigDecimal.class) {
			return new BigDecimal(defVal);
		}
		return null;

	}
	
	public static <T extends Number> Number getValue(Class<T> value, Number defVal) {

		if (value == Long.class) {
			return defVal.longValue();
		} else if (value == Double.class) {
			return defVal.doubleValue();
		} else if (value == Integer.class) {
			return  defVal.intValue();
		} else if (value == Float.class) {
			return defVal.floatValue();
		} else if (value == Short.class) {
			return defVal.shortValue();
		} else if (value == Byte.class) {
			return defVal.byteValue();
		} else if (value == BigDecimal.class) {
			return new BigDecimal(defVal.doubleValue());
		}
		return null;

	}
	
    public static final long SECOND = 1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;
	
	
	

}
