package raz.memo.reader;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateUtilTest {
	
	DateFormat dFormatter = new SimpleDateFormat("yyyy-MM-dd.HH.mm.ss");
	
	DateFormat normalFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSimpleDateFormat() {

		
		String sDate = "2013-03-19.19.42.17";

		Date input = null;
		try {
			input = dFormatter.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String output = dFormatter.format(input);

		System.out.println(">>" + output);

		assertEquals("I make sure that dates are the same", sDate, output);
	}

	@Test
	public void testParseMemoDate() {
		// LAST-MODIFIED:20130319T194217
		String input = "20130319T194217";
		
		String sameInputInAnotherFormat =  "2013-03-19.19.42.17";

		Date output = null;
	
		try {
			output = DateUtil.parseMemoDate(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String outputInAnotherFormat = dFormatter.format(output);
		
		//Here I make sure that the same date represented in 2 different formats, we get the same date
		assertEquals("I make sure that dates are the same", sameInputInAnotherFormat, outputInAnotherFormat);
		
		System.out.println("Date in UserFriendly Format:"+normalFormatter.format(output));
	}

}
