/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;
import at.apa.pdfwlserver.monitoring.xml.CustomerDirStructureMarshalerTest;

/**
 *
 * @author razvan
 */
public class MonitoringProfileCheckerTest {
	
	static MonitoringProfile monitoringProfile;
	
    
    public MonitoringProfileCheckerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    	
    	URL xmlFilePathURL = MonitoringProfileCheckerTest.class.getResource("xml/CustomerFolderStructureConfiguration3.xml");
    	File xmlFilePath = new File(xmlFilePathURL.getFile());
    	
    	
    	
    	MonitoringProfileReader reader = new MonitoringProfileReaderImpl(null,xmlFilePath, null);
    	try {
			monitoringProfile =reader.readMonitoringProfile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
    }

  

    /**
     * Test of launchCheckJob method, of class MonitoringChecker.
     */
    @Test
    public void testLaunchCheckJob() throws Exception {
    	//Date firstTime =
        MonitoringProfileChecker.getInstance().launchCheckJob();
        //assertNotNull(firstTime);
        
        //Thread.sleep(10*1000L);
    }

    /**
     * Test of cleanUp method, of class MonitoringChecker.
     */
    @Ignore
    public void testCleanUp() throws Exception {
    	MonitoringProfileChecker.getInstance().launchCheckJob();
    	Thread.sleep(10*1000L);
    	//sleep 10 sec. so that scheduler executes some jobes
    	MonitoringProfileChecker.getInstance().cleanUp2();
    }
}
