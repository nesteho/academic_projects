package model;

import model.exception.QwirkleException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grid   implements Serializable {
    private boolean empty;
    private final Tile[][] tilesArray ;
    public static final int SIDE = 91;

    /**
     * Constructor of the grid
     */
    public Grid(){
        this.empty = true;
        this.tilesArray = new Tile[SIDE][SIDE];
    }

    /**
     * Getter of the tile at the given position
     * @param row the row where the tile is
     * @param col the col where the tile is
     * @return the tile at the given position
     */
    public  Tile get(int row, int col){
        return tilesArray[row][col];
    }

    /**
     * Adds the first tiles to the game grid
     * @param d the direction of the add
     * @param line the tiles to add
     */
    public  int firstAdd(Direction d, Tile... line) {

        if (d == null){
            throw new QwirkleException("Aucune direction n' a été fournie");
        }

        onlyOneCommonCharacteristic(line);

        int currentRow = 45;
        int currentCol = 45;

        if (tilesArray[currentRow][currentCol]!= null){
            throw new QwirkleException("ce n'est pas le premier ajout de tuiles");
        }

        for (Tile t: line) {
            //pas de try-catch car pas besoin de verifier car : mm direction, 1er ajout et onlyOneCommonCharacteristic

            tilesArray[currentRow][currentCol] = t;

            currentRow+= d.getDeltaRow();
            currentCol+= d.getDeltaCol();
            //faire varier les lignes/col
            // ex  Direction =UP: la col ne bouge pas   currentCol=currentCol+ d.getDeltaCol(); 45 <= 45+0

        }
        empty=false;
        //  coté main :  Grid.firstAdd(Direction.UP, new Tile(..., ...), new Tile(..., ...), ....)

        int pts = line.length +checkIfQwirkle(line);

        return pts;
    }

    private void callIsIsolated(int row, int col){
        if (isIsolated(row, col) ){

            throw new QwirkleException("placement non autorisé : la tuile ne peut pas être isolée");
        }
    }

    /**
     * Adds one tile to the game grid at a given position
     * @param row the row where the tile is
     * @param col the col where the tile is
     * @param tile the tile at the given postition
     */
    public  int add(int row, int col, Tile tile){

        checkRowColTileOrTiles(row, col, tile);
        callIsIsolated(row, col);
        TileAtPosition  tap = new TileAtPosition(row, col, tile);

        int pts=add(tap);
        return  pts;

    }

    /** Adds tiles to the game grid
     * @param row the row where to add the first of the given tiles
     * @param col the col where to add the first of the given tiles
     *@param  d the direction of the add
     * @param line the tiles to add
     */
    public  int add(int row, int col, Direction d, Tile... line){

       callIsIsolated(row, col);

       TileAtPosition [] tiles = new TileAtPosition[line.length];

        if (d == null){
            throw new QwirkleException("aucune direction n' a été fournie");
        }

        int currentRow = row;
        int currentCol = col;

        int i=0;

        for (Tile t: line) {

            tiles[i] = new TileAtPosition(currentRow,currentCol,t);
            currentRow+= d.getDeltaRow();
            currentCol+= d.getDeltaCol();

            i++;

        }

        int pts =  add(tiles);
        return pts;
    }

    /**
     * Adds tiles at their different given positions
     * @param line the tiles to add
     */
    public  int add(TileAtPosition... line ){

        if (isEmpty()){
            throw new QwirkleException("placement non autorisé : il n' ya pas eu de premier ajout");
        }

        if (line == null){
            throw new QwirkleException("Aucune tuile à ajouter n'a été fournie");
        }

        for (TileAtPosition tap:line) {
            checkRowColTileOrTiles(tap.row(),tap.col(),tap.tile());
        }

        int minRow= 0;
        int minCol = 0;

        if (line.length != 1){
            int j = 1;

            for (int i = 0; i < line.length-1  ; i++) {

                if (line[i].row() != line[j].row() && line[i].col() != line[j].col()){
                    throw new QwirkleException("placement 'm' non autorisé: ligne et colone differentes");
                }
                j++;
            }

            if (line[0].row() == line[1].row()){

                minCol = line[0].col();
                for (TileAtPosition tap :line) {

                    if (tap.col()<minCol){
                        minCol=tap.col();
                    }
                }
            }
            else {
                minRow = line[0].row();

                for (TileAtPosition tap :line) {

                    if (tap.row()<minRow){
                        minRow=tap.row();
                    }
                }
            }

        }
        TileAtPosition[]  savedTilesAtPosition = new TileAtPosition[line.length];
        int i = 0;
        int pts;

        for (TileAtPosition tap: line) {

            tilesArray[tap.row()][tap.col()] = tap.tile();
            savedTilesAtPosition[i] = tap;
            i++;

        }
        int cptTiles = 0;
        if (line.length != 1){ //ce if permis à add(oneTile) d'appler add(TileAtPosition)

            if (minRow == 0 ){

                int cptCol = minCol;

                while (  tilesArray[line[0].row()][cptCol]!= null){

                    for (TileAtPosition tap:line) {

                        if (tilesArray[line[0].row()][cptCol]
                                == tilesArray[tap.row()][tap.col()]) {
                            cptTiles++;
                        }
                    }
                    cptCol++;
                }
            }
            else {
                int cptRow = minRow;

                while ( tilesArray[cptRow][line[0].col()]!= null){

                    for (TileAtPosition tap:line) {

                        if (tilesArray[cptRow][line[0].col()]
                            == tilesArray[tap.row()][tap.col()]) {

                            cptTiles++;
                        }
                    }
                    cptRow++;
                }
            }
        }

        try {

            if (line.length == 1){

                return checkWantedAdd(line[0].row(),line[0].col(),null, line.length);
            }

            //plusieurs tuiles

            if (cptTiles!= line.length){
                throw new QwirkleException("placement 'm' : un trou entre des tuiles n'est pas autorisé ");
            }

            if (minRow == 0){
                pts=  checkWantedAdd(line[0].row(),minCol,Direction.RIGHT, line.length);

            }
            else {

                pts=  checkWantedAdd(minRow,line[0].col(),Direction.DOWN, line.length);

            }

            return  pts;

        }
        catch (QwirkleException q){

            for (TileAtPosition toRemove: savedTilesAtPosition ) {
                removeATileFromACase(toRemove.row(),toRemove.col());
            }

            throw q;
        }

    }

 /*
    private void checkJocker(Tile[] line){

        int i=0;
        int j =1;}
*/

    /**
     * Checks if given tiles have only one characteristic in common
     * @param line the given tiles
     * @return whether the tiles have one characteristic in common
     */
    private boolean onlyOneCommonCharacteristic(Tile... line){

        if ( line.length < 1 ) {

            throw new QwirkleException("la série de tuile à placer est inférieur au minimum autorisé (1)");
        }

        if ( line.length > 6) {

            throw new QwirkleException("la série de tuile à placer dépasse le maximum autorisé (6)");

        }

        if (line.length==1){
            return true;
        }

        int i = 0;
        int j = 1;



        while (i < line.length - 1 && !line[i].equals(line[j])

                &&( (line[i].color() == line[j].color() ||  line[i].color().equals(Color.BLACK) || line[j].color().equals(Color.BLACK))
                || (line[i].shape() == line[j].shape() ||line[i].color().equals(Shape.INTERROGATION) || line[j].shape().equals(Shape.INTERROGATION)) )
        )
        {

            if( ( line[i].color().equals(Color.BLACK) && !line[j].color().equals(Color.BLACK) ||  !line[i].color().equals(Color.BLACK) && line[j].color().equals(Color.BLACK)
               )&& (line[i].shape() == line[j].shape() ) ){
                throw new QwirkleException("deux tuiles avec mm forme et mm couleur jocker mal-utilisé");
            }


            j++;

            if (j == line.length) {
                i++;
                j = i+1;
            }
        }

        if (i == line.length - 1){
            return true;
        }

        if (line[i].equals(line[j])){

            throw new QwirkleException("Il n'est pas permis de jouer deux tuiles identiques en même temps");

        }
        else {

            System.out.println();
            throw new QwirkleException("Vos tuiles n'ont aucune caractéristique commune");
        }


    }

    /**
     * Checks if the given index exists in the length
     * @param index the given index
     * @param length the given length
     * @return whether the given index exists in the length
     */
    private boolean  contains(int index, int length){

        return 0 <= index && index < length;
    }

    /**
     * Checks the index
     * @param index index the given index
     * @param length length the given lenght
     * @param errorMessage the error message to show
     */
    private void checkIndex(int index, int length, String errorMessage) {
        if ( !contains(index, length)) {
            throw new QwirkleException(errorMessage);
        }
    }

    private void checkRowColTileOrTiles(int row, int col,Tile... line){

        checkIndex(row,91, "Il n'y a pas y de ligne d'indice " + row + " dans le plateau de jeu");
        checkIndex(col,91, "Il n'y a pas y de colone d'indice " + col + " dans le plateau de jeu");

        if (tilesArray[row][col] != null){

            throw new QwirkleException("la case est déja occupée ");
        }
        for (Tile tile:line) {
            if (tile == null){
                throw new QwirkleException("Une tuile à ajouter n'a pas été fournie");
            }
        }

    }

    /**
     * Checks if the tile to add is isolated
     * @param row the row where the tile is wanted to be placed
     * @param col the col where the tile is wanted to be placed
     */
    private boolean isIsolated(int row, int col) {
        //pr eviter les ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 91
        // --> regarder les voisins au cas par cas

        if (row == Grid.SIDE-1){
            if (col == Grid.SIDE-1){

                  return tilesArray[row - 1][col] == null && tilesArray[row][col - 1] == null;
            }
            if (col == 0){

                return tilesArray[row-1][col] == null && tilesArray[row][col+1] == null;
            }
            if (col>0 && col <90){

                return  tilesArray[row][col + 1] == null
                        && tilesArray[row][col - 1] == null
                        &&tilesArray[row - 1][col] == null;
            }

        }
        if (row == 0){

            if (col == 0){

                return tilesArray[row + 1][col] == null && tilesArray[row][col + 1] == null;
            }
            if (col == Grid.SIDE -1){

                return tilesArray[row + 1][col] == null && tilesArray[row][col - 1] == null;
            }
            if (col>0 && col <90){
                return  tilesArray[row][col + 1] == null
                        && tilesArray[row][col - 1] == null
                        &&tilesArray[row + 1][col] == null;
            }
        }
        if (row == Grid.SIDE-1){
            if (col == Grid.SIDE-1){

                return tilesArray[row - 1][col] == null && tilesArray[row][col - 1] == null;
            }
            if (col == 0){

                return tilesArray[row-1][col] == null && tilesArray[row][col+1] == null;
            }
            if (col>0 && col <90){

                return  tilesArray[row][col + 1] == null
                        && tilesArray[row][col - 1] == null
                        &&tilesArray[row - 1][col] == null;
            }

        }
        if (col == 0){

            if (row>0 && row <90){
                return  tilesArray[row-1][col] == null
                        && tilesArray[row][col+1] == null
                        &&tilesArray[row + 1][col] == null;
            }
        }
        if (col == SIDE-1){

            if (row>0 && row <90){
                return  tilesArray[row-1][col] == null
                        && tilesArray[row][col-1] == null
                        &&tilesArray[row + 1][col] == null;
            }
        }

        return  tilesArray[row+1][col] == null
                && tilesArray[row][col + 1] == null
                && tilesArray[row - 1][col] == null
                && tilesArray[row][col - 1] == null;

    }

    /**
     * Checks if the wanted add is allowed
     * @param row the row where the tile is wanted to be placed
     * @param col the row where the tile is wanted to be placed
     * @param d the direction of the add
     * @param nbTiles the number of tiles wanted to be add
     */
    private int checkWantedAdd (int row, int col, Direction d, int nbTiles){

        int pts=0;
        int c;
        if (nbTiles==1){

            c = checkTilesInARow(row, col);
            if (c != 1){  //aucune nv ligne cree, pas de point mais on a teste la col car on ne savait pas av appeler check le nb tileInCol
                pts =c;
            }

            c= checkTilesInACol(row, col);

            if (c != 1){
                pts+=c;
            }
        }
        else if( d.equals(Direction.LEFT) || d.equals(Direction.RIGHT) ){

            pts = checkTilesInARow(row, col);

            for (int k = 0; k < nbTiles; k++) {
                c= checkTilesInACol(row, col+k );


                if (c != 1){  // si c =1 aucune nv ligne cree, pas de point mais on a teste la col car on ne savait pas av appeler check le nb tileInCol
                    pts +=c;
                }

            }

        } else{

            pts = checkTilesInACol(row, col);
            for (int k = 0; k <nbTiles ; k++) {
                c= checkTilesInARow(row+k, col);
                if (c!=1){
                    pts +=c;
                }

            }
        }

        return pts;
    }

    /**
     * Getter of the state of the grid
     * @return whether the grid is empty
     */

    public boolean isEmpty() {
        return empty;
    }

    private int checkTilesInARow(int row, int col) {
        int pts=0;
        List< Tile> tileInRow = new ArrayList<>();
        Tile [] tabTilesInRow;
        int j=0;

        while (tilesArray[row][col+j-1] != null){
            j--;
        }

        while (tilesArray[row][col+j] != null){

            tileInRow.add(tilesArray[row][col+j]);
            pts++;
            j++;
        }

        tabTilesInRow=tileInRow.toArray(new Tile[0]);
        pts += checkIfQwirkle(tabTilesInRow);

        onlyOneCommonCharacteristic(tabTilesInRow);
        return pts;
    }


    private int checkTilesInACol(int row, int col) {

        int pts=0;
        int i = 0;

        List< Tile> tileInCol = new ArrayList<>();
        Tile [] tabTilesInCol;

        while (tilesArray[row+i-1][col] != null){
            i--;
        }

        while (tilesArray[row+i][col] != null){

            tileInCol.add(tilesArray[row+i][col]);
            pts++;

            i++;
        }
        tabTilesInCol=tileInCol.toArray(new Tile[0]);
        onlyOneCommonCharacteristic(tabTilesInCol);
        pts += checkIfQwirkle(tabTilesInCol);
        return pts;
    }

    private int checkIfQwirkle(Tile[] line){

        if (line.length == 6){
            System.out.println("vous avez realisé un Qwirkle");
            return 6;
        }
        return 0;
    }
    public void removeATileFromACase(int row, int col){

        tilesArray[row][col] = null;
    }



}

