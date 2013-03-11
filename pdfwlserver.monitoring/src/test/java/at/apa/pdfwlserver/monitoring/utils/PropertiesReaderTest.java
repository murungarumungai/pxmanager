package at.apa.pdfwlserver.monitoring.utils;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

public class PropertiesReaderTest {
	
	static PropertiesReader instance;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		instance = PropertiesReader.getInstance(new File("src/test/resources/monitoring.properties"));
	}
	

	@Test
	public void testGetFreemarkerTemplateFilePath() {
		String expectedResult = "statusPage.ftl";
		String result = instance.getFreemarkerTemplateFilePath();
		assertEquals(expectedResult,result);
	}

	@Test
	public void testGetCheckedFileExtension() {
		String expectedResult = ".zip";
		String result = instance.getCheckedFileExtension();
		assertEquals(expectedResult,result);
	}

	@Test
	public void testGetRegularCheckRepeatPeriod() {
		int expectedResult = 1;
		int result = instance.getRegularCheckRepeatPeriod();
		assertEquals(expectedResult, result);
	}

	@Test
	public void testGetWebServiceUrl() {
		String expectedResult = "http://localhost:9001/pdfWhitelabelServer/pdfWhitelabelServer-pdfWhitelabelServer/Services2Impl?WSDL";
		String result = instance.getWebServiceUrl();
		assertEquals(expectedResult,result);
	}
	
	@Test 
	public void testGet(){
		String expectedResult = "statusPage.html";
		String result = instance.getStatusPageFilePath();
		assertEquals(expectedResult,result);
	}

}
