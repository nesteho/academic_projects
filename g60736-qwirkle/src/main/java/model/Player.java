package model;

import model.exception.QwirkleException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The players of the game
 */
public class Player  implements Serializable  {
    private String name;
    private List<Tile> tiles;
    private int score;


    /**
     * Constructor of a player
     * @param name
     */
    public Player(String name){
        this.name=name;
        tiles= new ArrayList<>();
       refill();
       this.score=0;
    }
    /**
     * Getter of the hand of player
     * @return the hand of player
     */
    public List<Tile> getHand() {

      final List<Tile> tilesInHand = new ArrayList<>();

        for ( Tile tile: tiles) {

            tilesInHand.add(tile);
        }
        return tilesInHand;

    }
    /**
     * Getter of the name of player
     * @return the name of player
     */
    public String getName(){
        return name;
    }

    /**
     * Refills the player's hand
     */
    public void refill(){
        Tile[] tabtiles = Bag.getInstance().getRandomTiles(6-getHand().size());

        for (int i = 0; i <tabtiles.length; i++) {
            tiles.add(i, tabtiles[i]);
        }
    }

    /**
     * Removes the played tiles of the player's hand
     * @param ts the played tiles
     */
    public  void remove( Tile... ts){
        for (Tile tile: ts) {
            tiles.remove(tile);
        }

    }

    /**
     * Getter of the score of player
     * @return the score of player
     */
    public int getScore(){
        return score;
    }

    /**
     * Increases the player's score
     */
    public void addScore(int value){
        score+=value;
    }

}
