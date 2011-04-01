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
public class ConcatCommandTest {

    public ConcatCommandTest() {
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
     * Test of execute method, of class ConcatCommand.
     */
    @Test
    public void testExecute() {
        System.out.println("Testing Concat");
        String expectedResult = "abc";
        ConcatCommand instance = new ConcatCommand("a", "b", "c");
        instance.execute();
        String result = instance.getResult();
        assertEquals("comparing Result of Concat",expectedResult, result);
    }

    @Test
    public void testExecute1() {
        System.out.println("Testing Concat");
        String expectedResult = "abc";
        String param[] = {"a","b","c"};
        ConcatCommand instance = new ConcatCommand(param);
        instance.execute();
        String result = instance.getResult();
        assertEquals("comparing Result of Concat",expectedResult, result);
    }
}
