/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.utils;

import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * 
 * @author rgaston
 */
public class DateFormatUtilsTest {

	public DateFormatUtilsTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of parseDate method, of class DateFormatUtils.
	 */
	@Test
	public void testParseDate() {

		String[] dates = { 
				"01.01.2013 04:30", 
				"1.1.2013 04:30",
				"01.01.2013 04:30:00",
				"01.01.13 04:30"};

		for (String oneDate : dates) {
			Date result = DateUtils.parseDateTime(oneDate);
			System.out.println(result);
		}

	}

}
