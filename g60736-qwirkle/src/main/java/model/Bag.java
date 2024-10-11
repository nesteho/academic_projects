package model;

import model.exception.QwirkleException;

import java.io.Serializable;
import java.util.*;

/**
*   Bag of tiles of the game
*
*/
public class Bag implements Serializable {
    //L8 : instance est le sac qui contient tiles
    private static final Bag bag = new Bag();

    //private: attribut auquel on a accès uniquement qd on est ds classe Bag
    //static: attribut lié a la classe mais pas spécifiquement lié à un objet de la classe
    private final List<Tile> tiles = new ArrayList<>();

    /**
     * Constructor of the bag
     */
    private Bag() {

        for (Color color : Color.values()) {

            for (Shape shape : Shape.values()) {

                for (int i = 0; i < 3; i++) {

                    tiles.add(new Tile(color, shape));

                }
            }
        }
        Collections.shuffle(tiles);
    }

    /**
     * Getter of the bag
     * @return the bag
     */
    public static Bag getInstance() {
        return bag;
    }

    /**
     * Getter of the size of the bag
     * @return the size of the bag
     */
    public int size() {

        return tiles.size();
    }

    /**
     * Draw  some random tiles
     * @param n number of tiles a gamer wants
     * @return random tiles
     */
    public Tile[] getRandomTiles(int n) {

        if (size() <= 0) {
            return null;
        }

        if (n>6) {
            throw new QwirkleException("Le maximum de tuile à donner dans un même tour est de 6");
        }

        Collections.shuffle(tiles);

        int lg = Math.min(n,size());
        Tile[] tilesToGive = new Tile[lg];  // si min = size on retourne tuiles restantes

        // (new Tile[0])-> param qui indique le type du tab qu'on veut creer
        for (int i = 0; i < lg; i++) {

            tilesToGive[i] = tiles.get(i);
            tiles.remove(tiles.get(i));

        }

        return tilesToGive;
    }
}
