package at.apa.pdfwlserver.monitoring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public static final String FORMAT1 = "dd.MM.yy hh:mm";
	public static final String FORMAT2 = "dd.MM.yyyy";
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
			parsedDate = parseDate(FORMAT1, dateTimeString);
		} catch (ParseException e) {
			logger.warn("Error Parsing dateString:"+dateTimeString,e);
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
			parsedDate = parseDate(FORMAT2, dateString);
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

}
