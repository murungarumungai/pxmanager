/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author razvan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                SnakeGUI snakeGUI = new SnakeGUI(new SnakeLogic(10));
                try {
                    snakeGUI.play();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
