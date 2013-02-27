/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.csv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.junit.*;

import at.apa.pdfwlserver.monitoring.data.Issue;
import at.apa.pdfwlserver.monitoring.xml.CustomerDirStructureMarshalerTest;
import static org.junit.Assert.*;

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
    	 
    	 System.out.println(issues.size());
    	
    }
}
