/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.apa.pdfwlserver.monitoring.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author rgaston
 */
public class CustomerDirStructureMarshalerTest {
    
    public CustomerDirStructureMarshalerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
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
     * Test of unMarshal method, of class CustomerDirStructureMarshaler.
     */
    @Test
    public void testUnMarshal() throws Exception {
        
        //File xmlFilePath = new File("CustomerFolderStructureConfiguration3.xml");
    	URL xmlFilePathURL = CustomerDirStructureMarshalerTest.class.getResource("CustomerFolderStructureConfiguration3.xml");
    	File xmlFilePath = new File(xmlFilePathURL.getFile());
    	
        CustomerBaseDir customerDirStructureJAXB = CustomerDirStructureMarshaler.unMarshal(xmlFilePath);
         
        assertEquals("Comparing customer name", "derstandard", customerDirStructureJAXB.getName());
        
        List<SubDir> subDirs = customerDirStructureJAXB.getSubDir();
        assertEquals("Comparing subDirs number", 4, subDirs.size());
        
        SubDir incomingDir = subDirs.get(0);
        checkSubDir(incomingDir, "incoming");
    }
    
    /** Check the 1st folder:incoming
     * - name 
     * - conditions
     * - status
     * */
    private static void checkSubDir(SubDir subDir, String expectedName){
    	assertEquals("Check subDir name", expectedName ,subDir.getName());
    	List<FileCondition> fileConditions = subDir.getFileCondition();
    	//incoming has 3 file conditions
    	assertEquals("Checking subDir fileConditions number", 3, fileConditions.size());
    	
    	FileCondition c1 = fileConditions.get(0);
    	//first condition:<fileCondition exists="true">
    	//isWithinTimePoint must be null, because is not set in xml
    	assertEquals("Checking condition isWithinTimePoint", null, c1.isWithinTimePoint() );
    	assertEquals("Checking condition fileExists", true, c1.isExists() );
    	
    	FileCondition c2 = fileConditions.get(1);
    	//<fileCondition exists="false"  IsWithinTimePoint="true">
    	assertEquals("Checking condition isWithinTimePoint", true, c2.isWithinTimePoint() );
    	assertEquals("Checking condition fileExists", false, c2.isExists() );
    	
    	FileCondition c3 = fileConditions.get(2);
    	//<fileCondition exists="false" IsWithinTimePoint="false">
    	assertEquals("Checking condition isWithinTimePoint", false, c3.isWithinTimePoint() );
    	assertEquals("Checking condition fileExists", false, c3.isExists() );
    }
}
