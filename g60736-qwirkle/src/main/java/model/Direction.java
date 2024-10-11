package model;

import java.io.Serializable;

/**
 *    Allowed directions for a placement of tiles.
 */
public enum Direction implements Serializable {
    UP(-1, 0),
    DOWN(+1,0),
    LEFT(0,-1),
    RIGHT(0,+1);
    private int deltaRow;
    private int deltaCol;

    /**
     * Constructor of a direction
     * @param deltaRow
     * @param deltaCol
     */
    Direction(int deltaRow, int deltaCol) {

        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    /**
     * Getter of the getDeltaRow
     * @return the getDeltaRow
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Getter of the getDeltaCol
     * @return the getDeltaCol
     */
    public int getDeltaCol() {
        return deltaCol;
    }

    /**
     *  Returns the opposite direction
     * @return the opposite direction
     */
    public Direction opposite(){

        return switch (this){
            //switch (Direction) --> faux : signification ds main : Direction.opposite())
            // --> Direction n' a pas de valeur et donc n'a pas d'opposé sol: on prend l'instance courante
            case UP -> DOWN;
            // le switch est evalue (on lui donne une val, la val aps "->")
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
    // COTE MAIN :  System.out.println(Direction.UP.opposite());
    //  appel de meth avc UP --> this de switch a la valeur UP

}

