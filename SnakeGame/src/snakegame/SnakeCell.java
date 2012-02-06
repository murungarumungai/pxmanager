/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author razvan
 *
 * Aceasta clasa reprezinta cea mai mica unitate din snake, care este indivizibila, adica un atom!
 */
public class SnakeCell {

    private byte x;
    private byte y;

    public SnakeCell() {
        x = 0;
        y = 0;
    }

    /**
     * Returneaza coordonata X
     */
    public byte getX() {
        return x;
    }

    /**
     * Returneaza coordonata Y
     */
    public byte getY() {
        return y;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public void setY(byte y) {
        this.y = y;
    }
}
