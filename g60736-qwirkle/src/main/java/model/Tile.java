package model;

import model.exception.QwirkleException;

import java.io.Serializable;

/**
 *   Tiles of the game
 * @param color color of the tile
 * @param shape shape of the tile
 */
public record Tile (Color color, Shape shape)   implements Serializable {
    /**
     * Constructor of a tile of the game
     * @param color the color of a tile
     * @param shape the shape of a tile
     */
    public Tile {
        if (shape == null) {
            throw new QwirkleException("La tuile n' a pas de forme");
        }
        if (color == null) {
            throw new QwirkleException("La tuile n' a pas de couleur");
        }
    }
}

