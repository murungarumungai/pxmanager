/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import at.apa.pdfwlserver.monitoring.data.MonitoringProfile;
import at.apa.pdfwlserver.monitoring.data.MonitoringProfileCache;
import at.apa.pdfwlserver.monitoring.data.ReportResult;
import at.apa.pdfwlserver.monitoring.data.SubDirResult;
import at.apa.pdfwlserver.monitoring.utils.ClassPathInstrumentation;

/**
 *
 * @author razvan
 */
public class MonitoringProfileCheckJobTest {
	static String testDir = "CheckJobTest";
	static String customerBaseDir = "derstandard";
	static String xmlPath = "CustomerFolderStructureConfigurationCheckJob.xml";
	static String csvPath = "IssuesCheckJob.csv";
	
	static MonitoringProfileReader monitoringProfileReader = null;
	
    static MonitoringProfile monitoringProfile = null; 
	
    public MonitoringProfileCheckJobTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {    	
    	
    	
    	monitoringProfileReader = new MonitoringProfileReaderImpl(
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
    }



    /**
     * Razvan: Idee de test, (folosind TDD) cand implementezi logica de check() si status result
     * 
     * iti creezi o structura de test pentru derstandard si pui acolo in subDir fisire mock de .zip
     * 
     * si daca exista fisierul .zip in subDir "incoming" si isWithinTimePoint, ma astept la status "not processed yet"
     * 
     * Razvan: o alta idee puneti <code>interface</code> peste metodele gen isWithinTimePoint(), 
     * ca sa pot testa mai usor si automat. Daca am interfete pot sa fac mock-uri, 
     * si nu trebe sa astept sa se indeplineasca conditia de timp astfel ca isWithinTimePoint() sa intoarca ce am eu nevoie in test-case
     * @throws IOException 
     * 
     */
    @Ignore
    public void testCheck() throws IOException {
        System.out.println("check");
        MonitoringProfileCheckJob instance = new MonitoringProfileCheckJob();
        ReportResult expResult = null;
        ReportResult result = instance.check();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testCheckDataDelivery() throws IOException {
    	MonitoringProfileCheckJob instance = new MonitoringProfileCheckJob();
    	
    	SubDirResult result = instance.checkDataDelivery();
    }
}
