/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raz.test.command;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author razvang
 */
public class SubstrCommandTest {

    public SubstrCommandTest() {
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
     * Test of execute method, of class SubstrCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("Testing SubstrCommand");
        String expectedResult = "a";
        SubstrCommand instance = new SubstrCommand("abc", 1, 1);
        instance.execute();
        String result = instance.getResult();
        assertEquals("comparing",expectedResult, result);
        
    }

}