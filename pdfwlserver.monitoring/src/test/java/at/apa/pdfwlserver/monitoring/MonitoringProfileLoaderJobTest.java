/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring;

import java.io.File;
import java.net.URL;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.quartz.JobExecutionContext;

/**
 *
 * @author razvan
 */
public class MonitoringProfileLoaderJobTest {
	
	MonitoringProfileReader reader;
    
    public MonitoringProfileLoaderJobTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    	URL xmlFilePathURL = MonitoringProfileCheckerTest.class.getResource("xml/CustomerFolderStructureConfiguration3.xml");
    	File xmlFilePath = new File(xmlFilePathURL.getFile());
    	
    	
    	
    	 reader = new MonitoringProfileReaderImpl(null,xmlFilePath, null);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of execute method, of class MonitoringLoaderJob.
     * Razvan: este complicat sa testez asta?
     * 
     * Nu pot sa fac UnitTest, pentru ca for now reason I could figure out,
     * the Schedulers launched in JUnitTests are dying...?!
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        JobExecutionContext context = null;
        MonitoringProfileLoaderJob instance = new MonitoringProfileLoaderJob();
        instance.setMonitoringProfileReader(reader);
        instance.execute(context);
        
    }
    
    @Ignore
    public void testExecuteMonitoringProfileExists() throws Exception {
        System.out.println("execute");
        JobExecutionContext context = null;
        MonitoringProfileLoaderJob instance = new MonitoringProfileLoaderJob();
        instance.setMonitoringProfileReader(reader);
        
        //Before we execute set a MonitoringProfile
        MonitoringProfile monitoringProfile =reader.readMonitoringProfile();
    	
    	MonitoringProfileCache.setMonitoringProfile(monitoringProfile);
        
        instance.execute(context);
        
    }

    
}
