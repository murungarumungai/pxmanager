package at.apa.pdfwlserver.monitoring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public static final String FORMAT1 = "dd.mm.yy hh:mm";
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
	 * </p>
	 * 
	 * 
	 *  @return may return null if the stringDate could not be parsed using any of the formats
	 *  
	 * */
	public static Date parseDate(String dateString){
		Date parsedDate = null;
		
		try {
			parsedDate = parseDate(FORMAT1, dateString);
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

}
