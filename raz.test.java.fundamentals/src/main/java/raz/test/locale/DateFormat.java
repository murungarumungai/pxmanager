package raz.test.locale;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

public class DateFormat {

	// "Donnerstag, 15. Dezember 2011"
	// formatting day of week in EEEE format like Sunday, Monday etc.
	static String dateFormat = "EEEE, dd. MMMMM yyyy";

	public static class DateUtils {

		public static String now(String dateFormat, Locale locale) {
			String dateNOW = null;
			Calendar now = Calendar.getInstance();
			Date currentDate = now.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,
					locale);
			dateNOW = sdf.format(currentDate);
			return dateNOW;
		}

	}

	public static void main(String arg[]) {
		//System.out.println(DateUtils.now("dd MMMMM yyyy"));
		System.out.println(DateUtils.now(dateFormat,Locale.GERMAN));
		// System.out.println(DateUtils.now("yyyyMMdd"));
		// System.out.println(DateUtils.now("dd.MM.yy"));
		// System.out.println(DateUtils.now("MM/dd/yy"));
		// System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
		// System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
		// System.out.println(DateUtils.now("h:mm a"));
		// System.out.println(DateUtils.now("H:mm:ss:SSS"));
		// System.out.println(DateUtils.now("K:mm a,z"));
		// System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
	}

}
