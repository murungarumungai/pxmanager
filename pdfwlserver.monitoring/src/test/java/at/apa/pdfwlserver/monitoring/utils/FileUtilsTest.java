package at.apa.pdfwlserver.monitoring.utils;

import static org.junit.Assert.*;


import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

public class FileUtilsTest {

	@Test
	public void testGetLastFolderInPath() {
		
		String result = FileUtils.getLastFolderInPath(new File("c:\\Users\\rgaston\\Downloads\\"));
		assertEquals("Downloads", result);
		
		String resultUnix = FileUtils.getLastFolderInPath(new File("/home/user/docs/"));
		assertEquals("docs",resultUnix);
	}

	/**
	 * TODO: try with at least 2 files
	 * */
	@Test
	public void testGetReceivedDate() throws Exception{
		File dir = FileUtils.checkDirExists("src/test/resources/FileUtilsInputTest");
		File latestFile = FileUtils.getLatestFileFromDir(dir, ".zip");
		Date expectedReceivedDate = DateUtils.parseDateTime("12.02.2013 12:24:01");
		Date result = FileUtils.getReceivedDate(latestFile);
		System.out.println(expectedReceivedDate.getTime());
		System.out.println(result.getTime());
		assertTrue(DateUtils.compareDatesUpToMillis(expectedReceivedDate, result));
	}

	/**
	 * Negative test with file extension <code>.zip.txt</code>
	 * */
	@Test
	public void testGetLatestFileFromDirFile() throws Exception{
		File dir = FileUtils.checkDirExists("src/test/resources/FileUtilsInputTest");
		File latestFile = FileUtils.getLatestFileFromDir(dir, ".zip.txt");
		assertNull(latestFile);
	}

	/**
	 * TestCase when file is withinCheckInterval
	 * lets assume that 
	 * EarliestDataDelivery is 		12.02.2013 12:24
	 * NextEarliestDataDelivery is	13.02.2013 
	 * I expect the file to be returned
	 * @throws IOException 
	 * */
	@Test
	public void testGetLatestFileFromDirFileWithinCheckInterval() throws IOException{
		File dir = FileUtils.checkDirExists("src/test/resources/FileUtilsInputTest");
		Date earliestDataDelivery = DateUtils.parseDateTime("12.02.2013 12:24:01");
		Date nextEarliestDataDelivery = DateUtils.parseDate("13.02.2013");
		
		File latestFileWithinCheckInterval = FileUtils.getLatestFileFromDir(dir, earliestDataDelivery, nextEarliestDataDelivery, ".zip");
		assertNotNull(latestFileWithinCheckInterval);
	}
	/**
	 * Test Case when file is not withinCheckInterval
	 * we have 2 older files
	 * */
	@Test
	public void testGetLatestFileFromDirFileNotWithinCheckInterval() throws Exception{
		File dir = FileUtils.checkDirExists("src/test/resources/FileUtilsInputTest");
		Date earliestDataDelivery = DateUtils.parseDateTime("12.03.2013 12:24:01");
		Date nextEarliestDataDelivery = DateUtils.parseDate("13.03.2013");
		
		File latestFileWithinCheckInterval = FileUtils.getLatestFileFromDir(dir, earliestDataDelivery, nextEarliestDataDelivery, ".zip");
		//files are not within the check interval so I expect null
		assertNull(latestFileWithinCheckInterval);
	}

}
