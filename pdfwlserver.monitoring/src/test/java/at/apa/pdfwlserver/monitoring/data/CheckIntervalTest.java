/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.data;

import static at.apa.pdfwlserver.monitoring.csv.CsvUnitTestData.getMutation;
import static at.apa.pdfwlserver.monitoring.csv.CsvUnitTestData.getMutationList;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import at.apa.pdfwlserver.monitoring.utils.DateUtils;
import at.apa.pdfwlserver.monitoring.utils.PropertiesReader;

/**
 *
 * @author rgaston
 */
public class CheckIntervalTest {
	
	static PropertiesReader propsReader = null;
    
    public CheckIntervalTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    	propsReader = PropertiesReader.getInstance(new File("src/test/resources/monitoring.properties"));
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
     * testCase: when this is the last issue date in the List<Issues> 
     * TESTED - OK!
     */
    @Test
    public void testGetMutationBeingCheckedRightNow1() {
          	
    	//lets assume that now is 28.02.2013
    	Date now = DateUtils.parseDate("28.02.2013");
    	List<Issue> issues = getTestData1();
    	MonitoringProfile monitoringProfile = new MonitoringProfile(null, issues,  propsReader);
    	MonitoringProfileCache.setMonitoringProfile(monitoringProfile);
    	 
    	 
    	//Date now = new Date();
    	 
    	CheckSession response = CheckSession.getMutationBeingCheckedRightNow(now);
    	 
    	assertEquals("Comparing next Earliest Data Delivery", DateUtils.getEndOfDayTime(now) ,response.getNextEarliestDataDelivery());
    	
    }
    
    public static List<Issue> getTestData1(){
    	List<Mutation> mutations1 =  getMutationList(
				getMutation("Morning", "28.02.2013 04:30", "28.02.2013 04:00", "28.02.2013 00:00")
				);
    	Issue issue1 = new Issue(DateUtils.parseDate("28.02.2013"), mutations1);
    	
    	List<Issue> issues = new ArrayList<Issue>();
    	issues.add(issue1);
    	
    	return issues;
    }
    
    
    /**
     * Test case: when now() is right at the start of checkInterval,
     * that is now() == Earliest Data Delivery  
	 * 
     * */
    @Test 
    public void testGetMutationBeingCheckedRightNow2(){
    	
    	Date now = DateUtils.parseDateTime("28.02.2013 17:00");
    	//Date now = new Date();
    	List<Issue> issues = getTestData2();
    	MonitoringProfile monitoringProfile = new MonitoringProfile(null, issues, propsReader);
    	MonitoringProfileCache.setMonitoringProfile(monitoringProfile);
    	
    	CheckSession response = CheckSession.getMutationBeingCheckedRightNow(now);
    	assertEquals("Comparing check mutation", "Evening", response.getCurrentCheckedMutation().getName());
    	assertEquals("Comparing next Earliest Data Delivery", DateUtils.parseDateTime("01.03.2013 00:00"), response.getNextEarliestDataDelivery());
    }
    
    public static List<Issue> getTestData2(){
    	List<Issue> issues = new ArrayList<Issue>();
    	List<Mutation> mutations1 =  getMutationList(
				getMutation("Morning", "28.02.2013 04:30", "28.02.2013 04:00", "28.02.2013 00:00")				
				);
    	Issue issue1 = new Issue(DateUtils.parseDate("28.02.2013"), mutations1);
    	issues.add(issue1);
    	
    	List<Mutation> mutations2 = getMutationList(
				getMutation("Evening", "28.02.2013 23:00", "28.02.2013 21:30", "28.02.2013 17:00"),
				getMutation("Morning", "01.03.2013 04:30", "01.03.2013 04:00", "01.03.2013 00:00")
				);
    	
    	Issue issue2 = new Issue(DateUtils.parseDate("01.03.2013"), mutations2);
    	issues.add(issue2);
    	
    	List<Mutation> mutations3 = getMutationList(
				getMutation("Morning"		, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00"),
				getMutation("Wiena"			, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00"),
				getMutation("Upper_Austria"	, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 20:00")
				);
    	
    	Issue issue3 = new Issue(DateUtils.parseDate("31.12.2012"), mutations3);
    	issues.add(issue3);
    	
    	return issues;
    	
    }
    
    /**
     * Test When we have an Issue with multiple mutations
     * */
    @Test
    public void testGetMutationBeingCheckedRightNow3(){
    	Date now = DateUtils.parseDateTime("31.12.2012 17:00");
    	List<Issue> issues = getTestData3();
    	MonitoringProfile monitoringProfile = new MonitoringProfile(null, issues,  propsReader);
    	MonitoringProfileCache.setMonitoringProfile(monitoringProfile);
    	
    	CheckSession response = CheckSession.getMutationBeingCheckedRightNow(now);
    	
    	assertEquals("Checking expected issue date", DateUtils.parseDate("31.12.2012 23:00"), response.getCurrentCheckedIssue().getIssuseDate());
    	
    	assertEquals("Checking expected Current checked mutations number", 3, response.getCurrentCheckedIssue().getMutations().size());
    }
    
    public static List<Issue> getTestData3(){
    	List<Issue> issues = new ArrayList<Issue>();
    	List<Mutation> mutations1 =  getMutationList(
				getMutation("Morning", "28.02.2013 04:30", "28.02.2013 04:00", "28.02.2013 00:00")				
				);
    	Issue issue1 = new Issue(DateUtils.parseDate("28.02.2013"), mutations1);
    	//issues.add(issue1);
    	
    	List<Mutation> mutations2 = getMutationList(
				getMutation("Morning"		, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00"),
				getMutation("Wiena"			, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 17:00"),
				getMutation("Upper_Austria"	, "31.12.2012 23:00", "31.12.2012 21:30", "31.12.2012 20:00")
				);
    	
    	Issue issue2 = new Issue(DateUtils.parseDate("31.12.2012"), mutations2);
    	issues.add(issue2);
    	
    	List<Mutation> mutations3 = getMutationList(
				getMutation("Evening", "28.02.2013 23:00", "28.02.2013 21:30", "28.02.2013 17:00"),
				getMutation("Morning", "01.03.2013 04:30", "01.03.2013 04:00", "01.03.2013 00:00")
				);
    	
    	Issue issue3 = new Issue(DateUtils.parseDate("01.03.2013"), mutations3);
    	//issues.add(issue3);
    	return issues;
    	
    }
    
    
    
}
