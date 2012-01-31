/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author razvan
 */
public class Snake {

    private byte[][] matrix = null;

    /**
     * size - matricea jocului de snake va fi patratica
     */
    public Snake(int size) {
        matrix = new byte[size][size];
    }

    /**
     * intoarce o referinta la matrix
     */
    public byte[][] getMatrix() {
        return matrix;
    }
}
