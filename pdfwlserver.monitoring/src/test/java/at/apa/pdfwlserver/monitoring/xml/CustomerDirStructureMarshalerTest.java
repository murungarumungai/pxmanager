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
    	
        CustomerBaseDir result = CustomerDirStructureMarshaler.unMarshal(xmlFilePath);
        String customerBaseDirName = result.getName(); 
        System.out.println(customerBaseDirName);
        assertEquals("Comparing customer name","derstandard", customerBaseDirName);
        
        List<SubDir> subDirs = result.getSubDir();
        
        assertEquals("Comparing subDirs number",4, subDirs.size());
        
    }
}
