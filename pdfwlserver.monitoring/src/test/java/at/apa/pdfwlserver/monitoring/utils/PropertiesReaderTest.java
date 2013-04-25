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
	public void testGetErrorFileExtension() {
		String expectedResult = ".txt";
		String result = instance.getErrorFileExtension();
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
	public void testGetWebServiceName() {
		String expectedResult = "pdfWhitelabelServer-pdfWhitelabelServer";
		String result = instance.getWebServiceName();
		assertEquals(expectedResult,result);
	}
	
	@Test
	public void testGetWebServiceNamespace() {
		String expectedResult = "http://pdfwlserver.apa.at";
		String result = instance.getWebServiceNamespace();
		assertEquals(expectedResult,result);
	}
	
	@Test 
	public void testGetStatusPageFilePath(){
		String expectedResult = "statusPage.html";
		String result = instance.getStatusPageFilePath();
		assertEquals(expectedResult,result);
	}
	
	@Test 
	public void testGetParamAppKey(){
		String expectedResult = "RHSQROHSAKNADFLKEADFAWE";
		String result = instance.getParamAppKey();
		assertEquals(expectedResult,result);
	}
	
	@Test 
	public void testGetParamUdid(){
		String expectedResult = "f6099142092673b3fa2645748923980b2d0a9152";
		String result = instance.getParamUdid();
		assertEquals(expectedResult,result);
	}
	
	@Test 
	public void testGetParamDeviceType(){
		String expectedResult = "iPad";
		String result = instance.getParamDeviceType();
		assertEquals(expectedResult,result);
	}
	
	@Test 
	public void testGetParamDeviceLocale(){
		String expectedResult = "de";
		String result = instance.getParamDeviceLocale();
		assertEquals(expectedResult,result);
	}
	
	@Test 
	public void testGetParamClientVersion(){
		String expectedResult = "WiBlattTest/3110/3110";
		String result = instance.getParamClientVersion();
		assertEquals(expectedResult,result);
	}
	
	@Test 
	public void testGetParamNetworkType(){
		String expectedResult = "";
		String result = instance.getParamNetworkType();
		assertEquals(expectedResult,result);
	}
	

}
