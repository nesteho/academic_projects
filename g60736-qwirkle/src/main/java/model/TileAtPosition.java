package model;

import java.io.Serializable;

/**
 * Draw a tile at a defined postion
 * @param row the row of grid
 * @param col the col of the grid
 * @param tile the tile to draw
 */
public record TileAtPosition(int row, int col, Tile tile) implements Serializable { }

