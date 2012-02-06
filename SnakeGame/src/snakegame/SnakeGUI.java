/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author razvan
 * This class displays the snake
 */
public class SnakeGUI {

    
    
    private int second = 1000;
    int frequency = 1 * second;
    //Create and set up the window.
    JFrame frame = new JFrame("Game x si o");
    //JPANEL
    JPanel panel = new JPanel();
    //Matrice de etichete
    //JButton[][] buttonMatrix = null;
    JLabel[][] labelMatrix = null;
    SnakeLogic snakeLogic = null;

    public SnakeGUI(SnakeLogic snake) {
        this.snakeLogic = snake;
        init();
    }

    private void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(20, 20, 500, 500);

        panel.setLayout(new GridLayout(10, 10));
        panel.setBackground(Color.GREEN);

        labelMatrix = new JLabel[snakeLogic.getMatrix().length][snakeLogic.getMatrix().length];

        drawInitMatrix();

        //Display the window.
        frame.getContentPane().add(panel);
        //frame.pack();
        frame.setVisible(true);
    }

    private void drawInitMatrix() {
        for (int i = 0; i < labelMatrix.length; i++) {
            //JLabel[] tempColumn = labelMatrix[i];
            for (int j = 0; j < labelMatrix[i].length; j++) {

                //JLabel oneLabel = new JLabel();

                //tempColumn[j] = oneLabel;
                labelMatrix[i][j] = new JLabel();
//                labelMatrix[i][j].setBackground(Color.red);
//                labelMatrix[i][j].setOpaque(true);
                Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED/*,Color.black,Color.black*/);
                labelMatrix[i][j].setBorder(b);
                panel.add(labelMatrix[i][j]);

            }
        }
    }

    /**
     * Redeseneaza game matrix
     * o parcurge si unde este 1 pune negru
     */
    private void draw() throws InterruptedException {
        for (int i = 0; i < labelMatrix.length; i++) {
            for (int j = 0; j < labelMatrix[i].length; j++) {

                if (snakeLogic.getMatrix()[i][j] == SnakeLogic.SNAKE_BODY) {
                    labelMatrix[i][j].setBackground(Color.red);
                    labelMatrix[i][j].setOpaque(true);
                }

            }
        }
        //panel.repaint();

        
    }

    public void play() throws InterruptedException {
        int contor =0;
        while (!snakeLogic.gameOver()) {
            System.out.println("contor:"+contor);
            byte keyPressed = SnakeLogic.RIGHT; //= readInputKey();
            draw();
            snakeLogic.moveSnake(keyPressed);
            //readInputKey();
            
            //readInputKey();
            contor++;
        }
    }
}
