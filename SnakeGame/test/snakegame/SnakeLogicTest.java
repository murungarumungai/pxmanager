/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author razvan
 */
public class SnakeLogicTest {

    public SnakeLogicTest() {
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

    @Test
    public void testSnakeArea() {
        SnakeLogic snakeLogic = new SnakeLogic(10);

        assertNotNull(snakeLogic.getMatrix());
        showMatrix(snakeLogic.getMatrix());
    }

    public static void showMatrix(byte[][] matrix) {
        if (matrix != null) {
            int xSize = matrix.length;
            for (int i = 0; i < xSize; i++) {
                byte[] temp = matrix[i];
                int ySize = temp.length;
                for (int j = 0; j < ySize; j++) {
                    System.out.print(/*"[" + i + "][" + j + "]=" +*/matrix[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        } else {
            System.out.println("Matrix is null");
        }
    }
}
