package at.apa.pdfwlserver.monitoring.utils;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class FileScannerTest {
	
	private static List<String> errorStrings;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		errorStrings = new ArrayList<String>();
		errorStrings.add("ApaError");
		errorStrings.add("ApaOrCustomerError");
		
	}

	@Test
	public void test() throws FileNotFoundException {
		File f = new File("src/test/resources/FileScannerTest","testInput");
		boolean rez = FileScanner.parseErrorDescriptionFile(f, errorStrings);
		assertEquals("expecting flase ", false, rez);
	}
	
	@Test
	public void test2() throws FileNotFoundException {
		File f = new File("src/test/resources/FileScannerTest","20130207040731_stdIPAD_20130207_morgen.zip.txt");
		boolean rez = FileScanner.parseErrorDescriptionFile(f, errorStrings);
		assertEquals("expecting flase ", true, rez);
	}

}
