package com.alpha.message.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/** DATE_PATTERN **/
	public interface DATE_PATTERN {
	public 	static final	String HHSS = "HH:mm";
	public 	static final	String DD_MMM = "dd MMM";
	public 	static final    String HHMMSS = "HHmmss";
	public 	static final 	String HH_MM_SS = "HH:mm:ss";
	public 	static final	String YYYYMMDD = "yyyyMMdd";
	public 	static final	String YYYY_MM_DD = "yyyy-MM-dd";
	public 	static final	String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public 	static final	String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public 	static final	String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public 	static final	String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	}

	/**
	 * Format  Date  
	 * @param date
	 * @param pattern
	 * @return  String
	 */
	public static final String format(Date date, String pattern) {
		if (date == null || pattern == null) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}
    /**
     * Compare Date
     * @param dt1
     * @param dt2
     */
    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) { //dt1 在dt2前
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) { //dt1在dt2后
                return -1;
            } else {
                return 0;
            }
         } catch (Exception exception) {
            exception.printStackTrace();
        }
         return 0;
    }
}
