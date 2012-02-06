/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.List;

/**
 *
 * @author razvan
 */
public class SnakeLogic {

    public static final byte RIGHT = 1;
    public static final byte LEFT = 2;
    public static final byte UP = 3;
    public static final byte DOWN = 4;
    public static final byte NEUTRAL = 0;
    public static final byte SNAKE_BODY = 1;
    private byte[][] matrix = null;
    private Snake snake = null;

    /**
     * constructor
     * @param  size - matricea jocului de snake va fi patratica
     * 
     */
    public SnakeLogic(int size) {
        matrix = new byte[size][size];
        initSnake();
    }

    /**
     * intoarce o referinta la matrix
     */
    public byte[][] getMatrix() {
        return matrix;
    }

    /**
     * Initializes the snake, and represent it
     */
    private void initSnake() {
        //Snakeul porneste din mijloc (5,5)
        this.snake = new Snake((byte) 5, (byte) 5);

        matrix = representSnake(matrix, snake);
    }

    /**
     * important
     * This method lays out the snake in the game matrix
     * @param matrix - the game matrix
     * @param snake - the snake
     * @return - the game matrix with the sanke represented
     *
     * In the matrix I lay out the snake from Head to Tale
     *
     */
    private static byte[][] representSnake(byte[][] matrix, Snake snake) {
        if (matrix == null || snake == null) {
            throw new IllegalArgumentException("The matrix or snake can not be null.");
        } else {
            int count = 0;
            List<SnakeCell> list = snake.getList();
            for (SnakeCell oneCell : list) {
                System.out.println(SnakeLogic.class.getName() + " <cell:" + count + "> [" + oneCell.getX() + "][" + oneCell.getY() + "]");
                matrix = markCell(matrix, oneCell.getX(), oneCell.getY(), SNAKE_BODY);
                count++;
            }
        }
        return matrix;
    }

    /**
     * This mathod marks a matrix cell[x,y] with a value
     */
    private static byte[][] markCell(byte[][] matrix, int x, int y, byte value) {
        int xSize = matrix.length;
        if (x >= xSize) {
            throw new IllegalArgumentException("Coordonata x, este mai mare decat " +
                    "lungimea maxima matricii ");
        }
        byte[] a = matrix[x];
        int ySize = a.length;
        if (y >= ySize) {
            throw new IllegalArgumentException("Coordonata y, este mai mare decat " +
                    "lungimea maxima matricii ");
        }
        matrix[x][y] = value;
        return matrix;
    }

    boolean gameOver() {
        SnakeCell head = snake.getList().get(0);
        boolean result = false;
        for (int i = 0; i < 10; i++) {
            if ((head.getY() == 0 && head.getX() == i) || (head.getX() == 0 && head.getY() == i)) {
                System.out.println("head[" + head.getX() + "][" + head.getY() + "]");
                return true;
            }
        }
        return result;
    }

    /**
     * Metoda asta muta sarpele si reactualizeaza matricea jocului
     */
    void moveSnake(byte keyPressed) {
        SnakeCell head = snake.getList().get(0);
        byte x = head.getX();
        byte y = head.getY();
        switch (keyPressed) {
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
        }
        head.setX(x);
        head.setY(y);
        matrix = representSnake(matrix, snake);
    }
}
