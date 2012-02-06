/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author razvan
 */
public class Snake {

    private List<SnakeCell> cellsList = null;

    /**
     * Default Constructor
     * it creates an ArrayList and adds a single SnakeCell, the snake head
     * @param x
     * @param y
     */
    public Snake(byte x, byte y) {
        cellsList = new ArrayList<SnakeCell>();
        SnakeCell head = new SnakeCell();
        head.setX(x);
        head.setY(y);
        cellsList.add(head);
    }

    public List<SnakeCell> getList() {
        return this.cellsList;
    }

    public void add(SnakeCell cell) {
        this.cellsList.add(cell);
    }
}
