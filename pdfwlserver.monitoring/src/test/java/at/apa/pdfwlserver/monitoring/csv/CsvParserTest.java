/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.csv;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.apa.pdfwlserver.monitoring.data.Issue;
import at.apa.pdfwlserver.monitoring.data.Mutation;
import at.apa.pdfwlserver.monitoring.utils.DateUtils;

/**
 *
 * @author rgaston
 */
public class CsvParserTest {
	
	static File csvFilePath;
    
    public CsvParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    	
    	URL csvFilePathURL = CsvParserTest.class.getResource("mutationExample.csv");
        csvFilePath = new File(csvFilePathURL.getFile());
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
     * Test of parse method, of class CsvParser.
     * @throws IOException 
     */
    @Test
    public void testParse() throws IOException {
    	
    	
        //List<CsvRow> expResult = null;
        int expectedCsvRowsListSize = 7;
        List<CsvRow> result = CsvParser.parseCSVFile(csvFilePath);
        assertEquals("Comparing number of CvsEntires", expectedCsvRowsListSize, result.size());
        
    }
    
    @Test
    public void testLoadIssuesFromCsvRows() throws Exception {
    	
    	 List<CsvRow> csvRows = CsvParser.parseCSVFile(csvFilePath);
    	 List<Issue> issues = CsvParser.loadIssuesFromCsvRows(csvRows);
    	 
    	 
    	 //I expect 5 issues from test .csv
    	 assertEquals("expected Issues Number",5, issues.size());
    	 
    	 List<Issue> expectedIssues = CsvUnitTestData.getExpectedIssues();
    	 //Sort issues by date so we have the same order
    	 Collections.sort(expectedIssues);
    	 
    	 //Comparing issues with expected result
    	 int i =0;
    	 for(Issue expectedIssue : expectedIssues ){
    		 compareIssue(expectedIssue, issues.get(i) );
    		 i++;
    	 }
    	 
    	 System.out.println(issues.size());
    	
    }
    
    
    private static void compareIssue(Issue expectedIssue,  Issue actualIssue){
    	Date expectedIssueDate = expectedIssue.getIssuseDate();
    	List<Mutation> expectedMutations = expectedIssue.getMutations();
    	assertEquals("Comparing IssueDate", expectedIssueDate, actualIssue.getIssuseDate());
    	
    	compareMutations(expectedMutations, actualIssue);
    }
    
    private static void compareMutations(List<Mutation> expectedMutations ,Issue actualIssue){
    	assertEquals("Comparing mutation number", expectedMutations.size(), actualIssue.getMutations().size());
    	int i =0;
    	for(Mutation oneMutation : expectedMutations) {
    		assertEquals("Comparing mutation name", oneMutation.getName(), actualIssue.getMutations().get(i).getName());
    		assertEquals("Comparing mutation DueDate", oneMutation.getDataDueDate(), actualIssue.getMutations().get(i).getDataDueDate());
    		assertEquals("Comparing mutation Data Processed", oneMutation.getDataProcessed(), actualIssue.getMutations().get(i).getDataProcessed());
    		i++;
    	}
    }
}
