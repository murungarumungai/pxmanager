/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import at.apa.pdfwlserver.monitoring.data.DirectoryFileConditionNowMocker;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;
import at.apa.pdfwlserver.monitoring.data.ReportResult;
import at.apa.pdfwlserver.monitoring.data.SubDirResult;
import at.apa.pdfwlserver.monitoring.utils.DateUtils;
import at.apa.pdfwlserver.monitoring.utils.FileUtils;

/**
 *
 * @author razvan
 */
public class MonitoringProfileCheckJobTest {
	static String testDir = "CheckJobTest";
	static String customerBaseDir = "derstandard";
	static String xmlPath = "CustomerFolderStructureConfigurationCheckJob.xml";
	static String csvPath = "IssuesCheckJob.csv";
	static String propertiesFilePath = "monitoring.properties";
	
	static MonitoringProfileReader monitoringProfileReader = null;
	
    static MonitoringProfile monitoringProfile = null; 
	
    public MonitoringProfileCheckJobTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {    	
    	
    	
    	monitoringProfileReader = new MonitoringProfileReaderImpl(
    			new File(testDir,propertiesFilePath),
    			new File(testDir,customerBaseDir), 
    			new File(testDir,xmlPath), 
    			new File(testDir,csvPath));
    	
    	try {
			monitoringProfile = monitoringProfileReader.readMonitoringProfile();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	MonitoringProfileCache.setMonitoringProfile(monitoringProfile);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    	//clean up the NowMocker
    	DirectoryFileConditionNowMocker.setNow(null);
    }

    
    /**
     * Test Case Scenario/SetUp
     * <p>
     * a. CheckJobTest\derstandard\incoming we have 2 .zip files with date: 11.03.2013 14:53:15
     * </p>
     * <p>
     * b. We have the following Mutation:
     * 11.03.2013;"Morning";11.03.2013 04:30;11.03.2013 04:00;11.03.2013 00:00
     * <br>
     * c. Next Mutation:
     * 12.03.2013;"Morning ,Wiena,Upper_Austria";12.03.2013 23:00;12.03.2013 21:30;12.03.2013 17:00
     * </p>
     * <p>
     * Earliest-Data-Delivery:		11.03.2013 00:00
     * Netx-Earliest-Data-Delivery:	12.03.2013 17:00
     * </p>
     * <p>
     * let's say the now(){the moment when we run the check} is just when we receive the file:11.03.2013 14:53
     * </p> 
     * <p>
     * Expected Result:
     * - we have a file (it doesn't matter that we have missed the timePoint DueDateDelivery:11.03.2013 04:00)
     * - expected status is: "not processed yet" and receivedDate
     * <p>
     * */
    @Test
    public void testCheckDataDelivery() throws IOException {
    	//Set the now()
    	Date now = DateUtils.parseDateTime("11.03.2013 14:53");
    	DirectoryFileConditionNowMocker.setNow(now);
    	MonitoringProfileCheckJob instance = new MonitoringProfileCheckJob();
    	SubDirResult result = instance.checkDataDelivery();
    	assertEquals("Checking STATUS","not yet processed",result.getStatus().getText());
    	Date expectedDate = DateUtils.parseDateTime("11.03.2013 14:53:15");
    	assertNotNull("Checking received file WithInCheck interval", result.getLatestFileWithinTheCheckInterval());
    	assertTrue("Checking RECEIVED DATE", DateUtils.compareDatesUpToMillis(expectedDate, FileUtils.getReceivedDate(result.getLatestFileWithinTheCheckInterval())));
    }
    
    /**
     * Test Case Scenario:
     * <p>Mutation:
     * 02.01.2013;"Evening";01.01.2013 23:00;01.01.2013 21:30;01.01.2013 17:00
     * </p>
     * <p>
     * file not exist. Folder is empty or we have old files.
     * </p>
     * <p>
     * now(){the moment when we run the check} 01.01.2013 21:29  
     * but we are still within the TimePoint. So expected status is "waiting"
     * </p>
     * */
    @Test 
    public void testCheckDataDelivery2() throws IOException{
    	//set the now
    	Date now = DateUtils.parseDateTime("01.01.2013 21:30"); 
    	DirectoryFileConditionNowMocker.setNow(now);
    	MonitoringProfileCheckJob instance = new MonitoringProfileCheckJob();
    	SubDirResult result = instance.checkDataDelivery();
    	assertEquals("Checking STATUS","waiting",result.getStatus().getText());
    	assertNull("Checking Received File Name. Should be null:n/A",result.getLatestFileWithinTheCheckInterval());
    	assertNull("Checking Received File Name. Should be null:n/A",result.getLatestFileOutOfCheckInterval());
    }
    
    
    /**
     * Test Case Scenario:
     * <p>
     * file not exist. Folder is empty or we have old files.
     * </p>
     * <p>
     * The timePoint has expired. now() the moment the check is done is after [Due-Date Delivery]
     * </p>
     * */
    @Test
    public void testCheckDataDelivery3() throws IOException{
    	//set the now
    	Date now = DateUtils.parseDateTime("01.01.2013 21:31"); 
    	DirectoryFileConditionNowMocker.setNow(now);
    	MonitoringProfileCheckJob instance = new MonitoringProfileCheckJob();
    	SubDirResult result = instance.checkDataDelivery();
    	assertEquals("Checking STATUS","No data received in time",result.getStatus().getText());
    	assertEquals("Checking TODO","Call customer",result.getStatus().getTodo());
    	assertNull("Checking Received File Name. Should be null:n/A",result.getLatestFileWithinTheCheckInterval());
    	assertNotNull("Checking Latest known File Name. Should be NOT be null",result.getLatestFileOutOfCheckInterval());
    }
    
}
