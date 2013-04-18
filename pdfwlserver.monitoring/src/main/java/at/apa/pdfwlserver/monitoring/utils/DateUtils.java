package at.apa.pdfwlserver.monitoring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public static final String DATE_TIME_FORMAT 			= "dd.MM.yy HH:mm";
	public static final String DATE_TIME_FORMAT_WITH_SECOND = "dd.MM.yy HH:mm:ss";
	public static final String DATE_FORMAT = "dd.MM.yyyy";
	//this is equivalent(and it also works for 2 digit year, i.e:yy) with "dd.mm.yyyy hh:mm";
	//see unit test
	
	/**
	 * <p>
	 * 
	 * Trying to be fault Tolerant to date format.
	 * Accepted/tested date formats in this order of precedence:
	 * (local Time-zone is used)
	 *  <p>01.01.2013 04:30</p>
	 *  <p>1.1.2013 04:30</p>
	 *  <p>01.01.2013 04:30:00</p>
	 *  <p>01.01.13 04:30</p>
	 *  
	 *  Format:dd.mm.yy hh:mm
	 * </p>
	 * 
	 * 
	 *  @return may return null if the stringDate could not be parsed using any of the formats
	 *  
	 * */
	public static Date parseDateTime(String dateTimeString){
		Date parsedDate = null;
		
		try {
			parsedDate = parseDate(DATE_TIME_FORMAT_WITH_SECOND, dateTimeString);
		} catch (ParseException e) {
			try{
				parsedDate = parseDate(DATE_TIME_FORMAT, dateTimeString);
			}catch(ParseException e1){
				logger.warn("Error Parsing dateString:"+dateTimeString,e);
			}
		}
		
		return parsedDate;
	}
	
	/**
	 * Trying to parse a DateString which doesn't contain also Time
	 * 
	 * Format:dd.mm.yy
	 * */
	public static Date parseDate(String dateString) {
		Date parsedDate = null;
		try {
			parsedDate = parseDate(DATE_FORMAT, dateString);
		} catch (ParseException e) {
			logger.warn("Error Parsing dateString:"+dateString,e);
		}
		return parsedDate;
	}
	
	private static Date parseDate(String dateFormat, String date) throws ParseException{
		
		SimpleDateFormat simpleDateFormat = null;
		simpleDateFormat = new SimpleDateFormat(dateFormat);
		Date parsedDate =  simpleDateFormat.parse(date);
		
		return parsedDate;
	}
	
	/**
	 * returns the same date but with hour set at 23:59:00:00 
	 * */
	public static Date getEndOfDayTime(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		return cal.getTime();
	}
	
	/**
	 * return equal is the two dates are equal up to a millisecond precision
	 * */
	public static boolean compareDatesUpToMillis(Date a, Date b){
		long difference = Math.abs(a.getTime() - b.getTime());
		boolean difLesThanAMillis = difference<=1000 ? true : false;
		return difLesThanAMillis;
	}
	
	public static String formatDate(final Date date){
		String result = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_WITH_SECOND);
		result = simpleDateFormat.format(date);
		return result;
	}

}
