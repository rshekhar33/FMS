package com.url.app.utility;

import java.util.Collection;
import java.util.Date;

/**
 * Common functionalities of application.
 * 
 * @author Shekhar Shinde
 */
public class AppCommon {

	private AppCommon() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Get current date and time.
	 * 
	 * @return date object of current date and time.
	 */
	public static final Date currentDateTime() {
		return new Date();
	}

	/**
	 * Check whether string is null or empty.
	 * 
	 * @param str the string which is to be checked.
	 * @return true/false.
	 */
	public static final boolean isEmpty(final String str) {
		return str == null || str.trim().isEmpty();
	}

	/**
	 * Check whether collection is null or empty.
	 * 
	 * @param collection the collection which is to be checked.
	 * @return true/false.
	 */
	public static final boolean isEmpty(final Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * Check whether collection is not null and not empty.
	 * 
	 * @param collection the collection which is to be checked.
	 * @return true/false.
	 */
	public static final boolean isNotEmpty(final Collection<?> collection) {
		return collection != null && !collection.isEmpty();
	}

	/**
	 * Check whether integer is a positive number.
	 * 
	 * @param str the string which is to be checked.
	 * @return true/false.
	 */
	public static final boolean isPositiveInteger(final Integer val) {
		return val != null && val > 0;
	}

	/**
	 * Check whether string is not a number.
	 * 
	 * @param str the string which is to be checked.
	 * @return true/false.
	 */
	public static final boolean isNotNumber(final String str) {
		boolean isNotNumber = true;
		try {
			Double.parseDouble(str);
			isNotNumber = false;
		} catch (NumberFormatException nfe) {
			// NumberFormatException
		}

		return isNotNumber;
	}

	/**
	 * Convert String to Integer. If String is invalid then returns 0.
	 * 
	 * @param str the string which is to be converted.
	 * @return integer from string.
	 */
	public static final Integer toInteger(final String str) {
		Integer val = 0;
		try {
			val = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			// NumberFormatException
		}

		return val;
	}

	/**
	 * Convert String to Integer. If String is invalid then returns null.
	 * 
	 * @param str the string which is to be converted.
	 * @return integer from string.
	 */
	public static final Integer toIntegerOrNull(final String str) {
		Integer val = null;
		try {
			val = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			// NumberFormatException
		}

		return val;
	}
}