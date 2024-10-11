package model;

import java.io.Serializable;

/**
 * The GridView of the game
 */
public class GridView implements Serializable {
    private final Grid grid;

    /**
     * Constructor of the gridview
     * @param grid the game grid
     */
    public GridView (Grid grid){
        this.grid = grid;

    }

    /**
     * Getter of a tile
     * @param row the row where the tile is
     * @param col the col where the tile is
     * @return the tile at given position
     */
    public Tile get(int row, int col){

        return grid.get(row,col);
    }

    /**
     * Getter of the state of the gridView
     * @return whether the gridView is empty
     */
    public boolean isEmpty(){
        return true;
    }
}
