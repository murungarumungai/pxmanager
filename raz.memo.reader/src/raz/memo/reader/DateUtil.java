package raz.memo.reader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static final DateFormat memoNoteFormatter = new SimpleDateFormat(
			"yyyyMMdd'T'hhmmss");
	
	public static final DateFormat normalFormatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

	/**
	 * Parses a Date in Memo fromat, exmple:
	 * <p>
	 * DCREATED: 20130406T103338 LAST-MODIFIED: 20130406T103338
	 * </p>
	 */
	public static Date parseMemoDate(String param) throws ParseException {
		Date result = null;
		result = memoNoteFormatter.parse(param);
		return result;
	}

}
