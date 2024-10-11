package model;

import model.*;
import model.exception.QwirkleException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;


class GridTest {

    Grid grid = new Grid();

    @Test
    void add_nb_Tuiles_Superieur_A_6() {

        Tile t = new Tile(Color.RED, Shape.ROUND);
        Tile t0 = new Tile(Color.RED, Shape.DIAMOND);
        grid.firstAdd(Direction.DOWN,t,t0);

        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);
        Tile t4 = new Tile(Color.RED, Shape.CROSS);
        Tile t5 = new Tile(Color.RED, Shape.SQUARE);
        Tile t6 = new Tile(Color.RED, Shape.STAR);
        Tile t7 = new Tile(Color.RED, Shape.PLUS);
        assertThrows(QwirkleException.class, () ->grid.add(45,47,Direction.RIGHT,t1, t2, t3, t4, t5, t6, t7));
        assertNull(grid.get( 45, 47));
        assertNull(grid.get( 45, 48));
        assertNull(grid.get( 45, 49));
        assertNull(grid.get( 45, 50));
        assertNull(grid.get( 45, 51));
        assertNull(grid.get( 45, 52));
        assertNull(grid.get( 45, 53));

    }

    @Test
    void addUneSeuleCaracteristiqueCommune_Forme() {

        Tile t1 = new Tile(Color.RED, Shape.DIAMOND);
        grid.firstAdd(Direction.RIGHT, t1);

        Tile t2 = new Tile(Color.BLUE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.GREEN, Shape.DIAMOND);
        grid.add(45,46,Direction.RIGHT, t2, t3);

        assertSame(t1, grid.get( 45,45));
        assertSame(t2, grid.get(45, 46 ));
        assertSame(t3, grid.get(45, 47 ));


    }

    @Test
    void addUneSeuleCaracteristiqueCommune_Couleur() {

        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        grid.firstAdd(Direction.RIGHT, t1);

        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.CROSS);
        grid.add(45,46,Direction.RIGHT, t2, t3);

        assertSame(t2, grid.get(45, 46 ));
        assertSame(t3, grid.get(45, 47 ));

    }
    @Test
    void addDeuxCartesIdentitiquesSur4() {
        Tile t = new Tile(Color.RED, Shape.ROUND);
        Tile t0 = new Tile(Color.RED, Shape.DIAMOND);
        grid.firstAdd(Direction.DOWN,t,t0);

        Tile t1 = new Tile(Color.RED, Shape.PLUS);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);
        Tile t4 = new Tile(Color.RED, Shape.ROUND);

        assertThrows(QwirkleException.class, () ->grid.add(47,45,Direction.DOWN,t1,t2,t3,t4));

        assertSame(t,grid.get( 45, 45));
        assertSame(t0,grid.get( 46, 45));
        assertNull(grid.get( 47, 45));
        assertNull(grid.get( 48, 45));
        assertNull(grid.get( 49, 45));
        assertNull(grid.get( 50, 45));
    }
    @Test
    void add_QueDesCartesIdentitiques() {
        Tile t = new Tile(Color.RED, Shape.ROUND);
        Tile t0 = new Tile(Color.RED, Shape.DIAMOND);
        grid.firstAdd(Direction.RIGHT,t,t0);

        Tile t1 = new Tile(Color.RED, Shape.PLUS);
        Tile t2 = new Tile(Color.RED, Shape.PLUS);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);

        assertThrows(QwirkleException.class, () ->grid.add(45,47,Direction.RIGHT,t1,t2,t3));
        assertSame(t, grid.get(45, 45 ));
        assertSame(t0, grid.get(45, 46 ));
        assertNull(grid.get( 45, 47));
        assertNull(grid.get( 45, 48));
        assertNull(grid.get( 45, 49));

    }
    @Test
    void addDeuxCartesAvecCaracteristiqueCommuneSur3_Couleur() {
        Tile t = new Tile(Color.RED, Shape.SQUARE);
        grid.firstAdd(Direction.RIGHT,t);

        Tile t1 = new Tile(Color.RED, Shape.CROSS);
        Tile t2 = new Tile(Color.RED, Shape.ROUND);
        Tile t3 = new Tile(Color.BLUE, Shape.DIAMOND);

        assertThrows(QwirkleException.class, () ->grid.add(45,46,Direction.RIGHT,t1,t2,t3));
        assertSame(t, grid.get(45, 45 ));

        assertNull(grid.get( 45, 46));
        assertNull(grid.get( 45, 47));
        assertNull(grid.get( 45, 48));

    }
    @Test
    void addDeuxCartesAvecCaracteristiqueCommune_Forme() {
        Tile t = new Tile(Color.ORANGE, Shape.ROUND);
        grid.firstAdd(Direction.RIGHT,t);

        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.YELLOW, Shape.ROUND);
        Tile t3 = new Tile(Color.GREEN, Shape.CROSS);

        assertThrows(QwirkleException.class, () ->grid.add(44,45,Direction.UP,t1,t2,t3));
        assertSame(t,grid.get(45,45));
        assertNull(grid.get( 44, 45));
        assertNull(grid.get( 43, 45));
        assertNull(grid.get( 42, 45));

    }
    @Test
    void addAucuneCaracteristiqueCommuneAToutes() {
        Tile t = new Tile(Color.ORANGE, Shape.ROUND);
        grid.firstAdd(Direction.RIGHT,t);

        Tile t1 = new Tile(Color.BLUE, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.GREEN, Shape.PLUS);
        assertThrows(QwirkleException.class, () ->grid.firstAdd(Direction.RIGHT,t1,t2,t3));

        assertSame(t,grid.get(45,45));
        assertNull(grid.get( 45, 46));
        assertNull(grid.get( 45, 47));
        assertNull(grid.get( 45, 48));

    }

// TEST POUR LA METHODE get
    @Test
    void getATile() {
        var grid = new Grid();
        var firstTile = new Tile(Color.YELLOW, Shape.CROSS);
        grid.firstAdd(Direction.DOWN, firstTile);

        assertEquals(firstTile, grid.get(45,45));
    }
    @Test
    void getANull() {
        var grid = new Grid();
        assertNull(grid.get( 45, 45));

    }

    @Test
    void add_direction_left(){

        Tile t = new Tile(Color.ORANGE, Shape.SQUARE);
        Direction d=Direction.LEFT;
        grid.firstAdd(d,t);

        Tile t1 = new Tile(Color.ORANGE, Shape.ROUND);
        Tile t2 = new Tile(Color.ORANGE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.ORANGE, Shape.CROSS);

        grid.add(45,44,d,t1,t2,t3);

        assertEquals(t, grid.get(45,45));
        assertEquals(t1, grid.get(45,44));
        assertEquals(t2, grid.get(45,43));
        assertEquals(t3, grid.get(45,42));
    }

    @Test
    void add_direction_right(){
        Tile t = new Tile(Color.ORANGE, Shape.SQUARE);
        Direction d=Direction.RIGHT;
        grid.firstAdd(d,t);

        Tile t1 = new Tile(Color.ORANGE, Shape.ROUND);
        Tile t2 = new Tile(Color.ORANGE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.ORANGE, Shape.CROSS);

        grid.add(45,46,d,t1,t2,t3);

        assertEquals(t, grid.get(45,45));
        assertEquals(t1, grid.get(45,46));
        assertEquals(t2, grid.get(45,47));
        assertEquals(t3, grid.get(45,48));
    }
    @Test
    void add_direction_up(){
        Tile t = new Tile(Color.ORANGE, Shape.SQUARE);
        Direction d=Direction.UP;
        grid.firstAdd(d,t);

        Tile t1 = new Tile(Color.ORANGE, Shape.ROUND);
        Tile t2 = new Tile(Color.ORANGE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.ORANGE, Shape.CROSS);
        grid.add(44,45,d,t1,t2,t3);

        assertEquals(t, grid.get(45,45));
        assertEquals(t1, grid.get(44,45));
        assertEquals(t2, grid.get(43,45));
        assertEquals(t3, grid.get(42,45));

    }
    @Test
    void add_direction_down(){
        Tile t = new Tile(Color.ORANGE, Shape.SQUARE);
        Direction d=Direction.DOWN;
        grid.firstAdd(d,t);

        Tile t1 = new Tile(Color.ORANGE, Shape.ROUND);
        Tile t2 = new Tile(Color.ORANGE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.ORANGE, Shape.CROSS);
        grid.add(46,45,d,t1,t2,t3);

        assertEquals(t, grid.get(45,45));
        assertEquals(t1, grid.get(46,45));
        assertEquals(t2, grid.get(47,45));
        assertEquals(t3, grid.get(48,45));

    }


// TEST POUR LA METHODE firstAdd

    @Test
    void firstAdd_nb_Tuiles_Inferieur_1() {

        assertThrows(QwirkleException.class, () ->grid.firstAdd(Direction.DOWN) );
        assertNull(grid.get( 45, 45));
    }

    @Test
    void firstAdd_one_tile() {
        var grid = new Grid();
        Tile tile = new Tile(Color.BLUE, Shape.CROSS);
        grid.firstAdd(Direction.RIGHT, tile);
        assertEquals(grid.get(45 , 45), tile);
    }
    @Test
    void firstAdd_nb_Tuiles_Entre_2_Et_6() {

        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);

        grid.firstAdd(Direction.RIGHT, t1, t2);
        assertSame(t1, grid.get( 45,45));
        assertSame(t2, grid.get(45, 46 ));


    }
    @Test
    void firstAdd_nb_Tuiles_Superieur_A_6() {

        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);
        Tile t4 = new Tile(Color.RED, Shape.CROSS);
        Tile t5 = new Tile(Color.RED, Shape.SQUARE);
        Tile t6 = new Tile(Color.RED, Shape.STAR);
        Tile t7 = new Tile(Color.RED, Shape.PLUS);
        assertThrows(QwirkleException.class, () ->grid.firstAdd(Direction.RIGHT,t1, t2, t3, t4, t5, t6, t7));
        assertNull(grid.get( 45, 45));
        assertNull(grid.get( 45, 46));
        assertNull(grid.get( 45, 47));
        assertNull(grid.get( 45, 48));
        assertNull(grid.get( 45, 49));
        assertNull(grid.get( 45, 50));
        assertNull(grid.get( 45, 51));


    }
    @Test
    void firstAdd_no_direction() {
        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);

        assertThrows(QwirkleException.class, () ->grid.firstAdd(null,t1,t2));

    }
    @Test
    void firstAdd_direction_left(){
        Tile t1 = new Tile(Color.ORANGE, Shape.ROUND);
        Tile t2 = new Tile(Color.ORANGE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.ORANGE, Shape.CROSS);
        Direction d=Direction.LEFT;
        grid.firstAdd(d,t1,t2,t3);

        assertEquals(t1, grid.get(45,45));
        assertEquals(t2, grid.get(45,44));
        assertEquals(t3, grid.get(45,43));

    }

    @Test
    void firstAdd_direction_right(){
        Tile t1 = new Tile(Color.ORANGE, Shape.ROUND);
        Tile t2 = new Tile(Color.ORANGE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.ORANGE, Shape.CROSS);
        Direction d=Direction.RIGHT;
        grid.firstAdd(d,t1,t2,t3);

        assertEquals(t1, grid.get(45,45));
        assertEquals(t2, grid.get(45,46));
        assertEquals(t3, grid.get(45,47));

    }
    @Test
    void firstAdd_direction_up(){
        Tile t1 = new Tile(Color.ORANGE, Shape.ROUND);
        Tile t2 = new Tile(Color.ORANGE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.ORANGE, Shape.CROSS);
        Direction d=Direction.UP;
        grid.firstAdd(d,t1,t2,t3);

        assertEquals(t1, grid.get(45,45));
        assertEquals(t2, grid.get(44,45));
        assertEquals(t3, grid.get(43,45));

    }
    @Test
    void firstAdd_direction_down(){
        Tile t1 = new Tile(Color.ORANGE, Shape.ROUND);
        Tile t2 = new Tile(Color.ORANGE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.ORANGE, Shape.CROSS);
        Direction d=Direction.DOWN;
        grid.firstAdd(d,t1,t2,t3);

        assertEquals(t1, grid.get(45,45));
        assertEquals(t2, grid.get(46,45));
        assertEquals(t3, grid.get(47,45));

    }
    @Test
    void firstAddDeuxCartesIdentitiquesSur4() {
        Tile t1 = new Tile(Color.RED, Shape.PLUS);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);
        Tile t4 = new Tile(Color.RED, Shape.ROUND);

        assertThrows(QwirkleException.class, () ->grid.firstAdd(Direction.DOWN,t1,t2,t3,t4));

        assertNull(grid.get( 45, 45));
        assertNull(grid.get( 46, 45));
        assertNull(grid.get( 47, 45));
        assertNull(grid.get( 48, 45));
    }
    @Test
    void firstAdd_cannot_be_called_twice() {
        Tile redcross = new Tile(Color.RED, Shape.CROSS);
        Tile reddiamond = new Tile(Color.RED,  Shape.DIAMOND);
        grid.firstAdd(Direction.UP, redcross, reddiamond);
        assertThrows(QwirkleException.class, () -> grid.firstAdd(Direction.DOWN, redcross, reddiamond));
    }

    //TEST METHODE       void add(int row, int col, Tile tile){
    //test elvire
    @Test
    void add_one_tile() {
        var grid = new Grid();

        var firstTile = new Tile(Color.YELLOW, Shape.SQUARE);
        grid.firstAdd(Direction.DOWN, firstTile);

        var t1 = new Tile(Color.YELLOW, Shape.CROSS);
        grid.add(45, 46,t1);
        assertEquals(t1, grid.get(45, 46));

    }

    //TEST METHODE  void add(int row, int col, Direction d, Tile... line){

    @Test
    void add_3_tiles() {
        var firstTile = new Tile(Color.ORANGE, Shape.SQUARE);
        grid.firstAdd(Direction.DOWN, firstTile);

        var t1 = new Tile(Color.RED, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.SQUARE);
        var t3 = new Tile(Color.PURPLE, Shape.SQUARE);

        grid.add(45,46, Direction.RIGHT,t1,t2,t3);

        assertEquals(t1, grid.get(45,46));
        assertEquals(t2, grid.get(45,47));
        assertEquals(t3, grid.get(45,48));

    }
    @Test
    void add_3_tiles_failed_firstTiletoAddIsolated() {

        var firstTile = new Tile(Color.YELLOW, Shape.CROSS);
        grid.firstAdd(Direction.DOWN, firstTile);

        var t1 = new Tile(Color.RED, Shape.SQUARE);
        var t2 = new Tile(Color.RED, Shape.SQUARE);
        var t3 = new Tile(Color.RED, Shape.SQUARE);

        assertThrows(QwirkleException.class, () -> grid.add(48,45, Direction.RIGHT,t1,t2,t3));

    }

    // test vincent
    @Test
    void add_2_tiles() {

        var firstTile = new Tile(Color.GREEN, Shape.CROSS);
        grid.firstAdd(Direction.DOWN, firstTile);
        var t1 = new Tile(Color.GREEN, Shape.PLUS);
        var t2 = new Tile(Color.GREEN, Shape.DIAMOND);

        grid.add(45,44, Direction.DOWN,t1,t2);

        assertEquals(t1, grid.get(45, 44));
        assertEquals(t2, grid.get(46, 44));

    }
    @Test
    void add_2_tiles_failed_noCommonCarac() {

        var firstTile = new Tile(Color.BLUE, Shape.CROSS);
        grid.firstAdd(Direction.DOWN, firstTile);
        var t1 = new Tile(Color.GREEN, Shape.PLUS);
        var t2 = new Tile(Color.GREEN, Shape.DIAMOND);

        assertThrows(QwirkleException.class, () ->        grid.add(45,44, Direction.DOWN,t1,t2));

    }
    @Test
    void firstAddUneSeuleCaracteristiqueCommune_Forme() {

        Tile t1 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t2 = new Tile(Color.BLUE, Shape.DIAMOND);
        Tile t3 = new Tile(Color.GREEN, Shape.DIAMOND);

        grid.firstAdd(Direction.RIGHT, t1, t2,t3);
        assertSame(t1, grid.get( 45,45));
        assertSame(t2, grid.get(45, 46 ));
        assertSame(t3, grid.get(45, 47 ));


    }
    @Test
    void firstAddUneSeuleCaracteristiqueCommune_Couleur() {

        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.RED, Shape.CROSS);

        grid.firstAdd(Direction.RIGHT, t1,t2,t3);

        assertSame(t1, grid.get( 45,45));
        assertSame(t2, grid.get(45, 46 ));
        assertSame(t3, grid.get(45, 47 ));

    }

    @Test
    void firstAdd_must_be_called_first_simple() {
        Tile redcross = new Tile(Color.RED, Shape.CROSS);
        assertThrows(QwirkleException.class, () -> grid.add(0, 0, redcross));
    }

    @Test
    void add_2_tiles_TileAtPosition_same_color_different_shape_same_row_related_by_another_tile() {
        var grid = new Grid();

        var firstTile = new Tile(Color.GREEN, Shape.CROSS);
        grid.firstAdd(Direction.DOWN, firstTile);

        var t1=new Tile(Color.GREEN, Shape.STAR);
        var t2 = new Tile(Color.GREEN, Shape.ROUND);

        TileAtPosition tap1 =new TileAtPosition(44,45,t1);
        TileAtPosition tap2 =new TileAtPosition(46,45,t2);

        grid.add(tap1,tap2);
        //todo ici pb :  tileAtPos 7 points au lieu 3  1(ok)+(1+1)+(1+1+1)

        assertEquals(tap1.tile(), grid.get(44,45));
        assertEquals(tap2.tile(), grid.get(46,45));

    }
    @Test
    void add_TileAtPosition_add_two_identitical_tiles_same_row_different_col_failed() {
        var grid = new Grid();

        var firstTile = new Tile(Color.BLUE, Shape.STAR);
        grid.firstAdd(Direction.DOWN, firstTile);

        var t1=new Tile(Color.BLUE, Shape.ROUND);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);

        TileAtPosition tap1 =new TileAtPosition(45,44,t1);
        TileAtPosition tap2 =new TileAtPosition(45,46,t2);

        assertThrows(QwirkleException.class, () ->grid.add(tap1,tap2));
    }
    @Test
    void add_2_tiles_TileAtPosition_failed_different_col_and_row() {
        var firstTile = new Tile(Color.YELLOW, Shape.CROSS);
        grid.firstAdd(Direction.DOWN, firstTile);

        var t1 = new Tile(Color.GREEN, Shape.STAR);
        var t2 = new Tile(Color.GREEN, Shape.ROUND);

        TileAtPosition tap= new TileAtPosition(40,44,t1);
        TileAtPosition tap2= new TileAtPosition(47,41,t2);

        assertThrows(QwirkleException.class, () ->grid.add(tap,tap2));

    }

    @Test
    void add_2_tiles_TileAtPosition_failed_same_col_different_row_not_related_by_any_tile() {

        var grid = new Grid();
        var t1 = new Tile(Color.GREEN, Shape.DIAMOND);
        grid.firstAdd(Direction.RIGHT, t1);


        var t2 = new Tile(Color.GREEN, Shape.STAR);
        var t3 = new Tile(Color.GREEN, Shape.ROUND);

        TileAtPosition tap= new TileAtPosition(44,44,t3);
        TileAtPosition tap2= new TileAtPosition(47,44,t3);

        assertThrows(QwirkleException.class, () ->grid.add(tap,tap2));

    }
    @Test
    void add_2_tiles_TileAtPosition_failed_different_col_same_row_isolated() {
        var grid = new Grid();
        var t1 = new Tile(Color.GREEN, Shape.STAR);
        var t2 = new Tile(Color.GREEN, Shape.ROUND);

        TileAtPosition tap= new TileAtPosition(44,47,t1);
        TileAtPosition tap2= new TileAtPosition(44,40,t2);

        assertThrows(QwirkleException.class, () ->grid.add(tap,tap2));

    }
    @Test
    void add_2_tiles_TileAtPosition_failed_different_col_same_row_related_to_some_ligns_of_tiles() {
        var grid = new Grid();
        var t1 = new Tile(Color.GREEN, Shape.STAR);
        var t2 = new Tile(Color.GREEN, Shape.ROUND);

        TileAtPosition tap= new TileAtPosition(44,44,t1);
        TileAtPosition tap2= new TileAtPosition(47,47,t2);

        assertThrows(QwirkleException.class, () ->grid.add(tap,tap2));

    }
    //    void add_2_tiles_TileAtPosition_faile_different_col_same_row_related_to_one_tile() {
    @Test
    void add_2_tiles_TileAtPosition_different_row_same_col_but_linked_by_one_tile() {

        var grid = new Grid();
        var t1 = new Tile(Color.YELLOW, Shape.CROSS);
        var t2 = new Tile(Color.GREEN, Shape.CROSS);
        grid.firstAdd(Direction.RIGHT, t1,t2);

        var tile3 = new Tile(Color.GREEN, Shape.STAR);
        var tile4= new Tile(Color.GREEN, Shape.ROUND);

        TileAtPosition tap= new TileAtPosition(44,46,tile3);
        TileAtPosition tap2= new TileAtPosition(46,46,tile4);
        grid.add(tap,tap2);

        assertEquals(tile3, grid.get(44,46));
        assertEquals(tile4, grid.get(46,46));

    }

    @Test
    void addTileAtPositionNoGivenTileAtPosition(){
        var grid = new Grid();

        var t1 = new Tile(Color.YELLOW, Shape.CROSS);
        var t2 = new Tile(Color.GREEN, Shape.CROSS);
        grid.firstAdd(Direction.RIGHT, t1,t2);

        assertThrows(QwirkleException.class, () ->grid.add(null));
    }

    @Test
    void addTileAtPositionNoGivenTile(){
        var grid = new Grid();

        var t1 = new Tile(Color.GREEN, Shape.CROSS);
        grid.firstAdd(Direction.RIGHT, t1);
        TileAtPosition tap= new TileAtPosition(44,46,null);
        assertThrows(QwirkleException.class, () ->grid.add(tap));

    }
    @Test
    void addTileAtPositionUncorrectRow(){

        var grid = new Grid();
        var t1 = new Tile(Color.GREEN, Shape.CROSS);
        grid.firstAdd(Direction.RIGHT, t1);

        TileAtPosition tap= new TileAtPosition(144,46,null);

        assertThrows(QwirkleException.class, () ->grid.add(tap));

    }
    @Test
    void addTileAtPositionUncorrectCol(){
        var grid = new Grid();

        var t1 = new Tile(Color.GREEN, Shape.CROSS);
        grid.firstAdd(Direction.RIGHT, t1);
        var t2 = new Tile(Color.YELLOW, Shape.CROSS);

        TileAtPosition tap= new TileAtPosition(44,-5,t2);
        assertThrows(QwirkleException.class, () ->grid.add(tap));

    }


    @Test
    void addAtAPositionWhereATileIsAlready() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        var t3 = new Tile(Color.BLUE, Shape.CROSS);
        var t4 = new Tile(Color.BLUE, Shape.STAR);

        grid.firstAdd(Direction.RIGHT, t1,t2,t3);
        assertThrows(QwirkleException.class, () -> grid.add(45,47,t4));

    }
    @Test
    void addTilesOneOfthemIsNull() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        var t4 = new Tile(Color.BLUE, Shape.STAR);

        grid.firstAdd(Direction.RIGHT, t1);

        assertThrows(QwirkleException.class, () -> grid.add(45, 46,Direction.RIGHT,t2,null,t4));

    }

    // TEST METHODE CHECKWANTEDPLACEMENT
    @Test
    void checkWantedAdd_correct_case() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        var t3 = new Tile(Color.BLUE, Shape.CROSS);

        grid.firstAdd(Direction.DOWN, t1,t2, t3);

        var t4 = new Tile(Color.PURPLE, Shape.ROUND);
        var t5 = new Tile(Color.GREEN, Shape.ROUND);
        grid.add(46,46,Direction.RIGHT, t4,t5);
        //test si tuile a ajouter avec add est ds case occupe

    }
    @Test
    void checkWantedAdd_failed_firstAdd_just_above_add_sameDirection() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        var t3 = new Tile(Color.BLUE, Shape.CROSS);

        grid.firstAdd(Direction.RIGHT, t1,t2, t3);

        var t4 = new Tile(Color.PURPLE, Shape.ROUND);
        var t5 = new Tile(Color.GREEN, Shape.ROUND);
        //test si tuile a ajouter avec add est ds case occupe
        assertThrows(QwirkleException.class, () ->        grid.add(46,45,Direction.RIGHT, t4,t5));

    }
    @Test
    void isIsolated_case_isLeftFromAnotherTile() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        grid.firstAdd(Direction.RIGHT, t1);

        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        grid.add(45,44,t2);

    }
    @Test
    void isIsolated_case_isRightFromAnotherTile() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        var t3 = new Tile(Color.BLUE, Shape.CROSS);

        grid.firstAdd(Direction.RIGHT, t1,t2);
        grid.add(45,47,t3);

    }
    @Test
    void isIsolated_case_isBottomFromAnotherTile() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        var t3 = new Tile(Color.BLUE, Shape.CROSS);

        grid.firstAdd(Direction.RIGHT, t1,t2);
        grid.add(46,46,t3);

    }
    @Test
    void isIsolated_case_isAboveFromAnotherTile() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        var t3 = new Tile(Color.BLUE, Shape.CROSS);

        grid.firstAdd(Direction.RIGHT, t1,t2);
        grid.add(44,46,t3);
    }

    @Test
    void isIsolated() {
        var grid = new Grid();
        var t1 = new Tile(Color.BLUE, Shape.SQUARE);
        var t2 = new Tile(Color.BLUE, Shape.ROUND);
        var t3 = new Tile(Color.BLUE, Shape.CROSS);

        grid.firstAdd(Direction.RIGHT, t1,t2);
        assertThrows(QwirkleException.class, () -> grid.add(40,46,t3));

    }
    @Test
    void firstAdd_QueDesCartesIdentitiques() {

        Tile t1 = new Tile(Color.RED, Shape.PLUS);
        Tile t2 = new Tile(Color.RED, Shape.PLUS);
        Tile t3 = new Tile(Color.RED, Shape.PLUS);
        assertThrows(QwirkleException.class, () ->grid.firstAdd(Direction.RIGHT,t1,t2,t3));
        assertNull(grid.get( 45, 45));
        assertNull(grid.get( 45, 46));
        assertNull(grid.get( 45, 47));
    }
    @Test
    void firstAddDeuxCartesAvecCaracteristiqueCommuneSur3_Couleur() {
        Tile t1 = new Tile(Color.RED, Shape.CROSS);
        Tile t2 = new Tile(Color.RED, Shape.ROUND);
        Tile t3 = new Tile(Color.BLUE, Shape.DIAMOND);
        assertThrows(QwirkleException.class, () ->grid.firstAdd(Direction.RIGHT,t1,t2,t3));
        assertNull(grid.get( 45, 45));
        assertNull(grid.get( 45, 46));
        assertNull(grid.get( 45, 47));

    }
    @Test
    void firstAdddeuxCartesAvecCaracteristiqueCommune_Forme() {
        Tile t1 = new Tile(Color.RED, Shape.ROUND);
        Tile t2 = new Tile(Color.YELLOW, Shape.ROUND);
        Tile t3 = new Tile(Color.GREEN, Shape.CROSS);
        assertThrows(QwirkleException.class, () ->grid.firstAdd(Direction.UP,t1,t2,t3));

        assertNull(grid.get( 45, 45));
        assertNull(grid.get( 44, 45));
        assertNull(grid.get( 43, 45));

    }
    @Test
    void firstAddAucuneCaracteristiqueCommuneAToutes() {
        Tile t1 = new Tile(Color.BLUE, Shape.ROUND);
        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
        Tile t3 = new Tile(Color.GREEN, Shape.PLUS);
        assertThrows(QwirkleException.class, () ->grid.firstAdd(Direction.RIGHT,t1,t2,t3));

        assertNull(grid.get( 45, 45));
        assertNull(grid.get( 45, 46));
        assertNull(grid.get( 45, 47));

    }

    @Test
    void firstAdd_direction_is_null(){

        var grid = new Grid();
        assertThrows(QwirkleException.class, () -> grid.firstAdd(null,null));
    }

    //TESTS POUR LA METHODE contains
    @Test
    void contains_negative_index(){

        Tile t1 = new Tile(Color.BLUE, Shape.ROUND);
        Tile t2 = new Tile(Color.BLUE, Shape.DIAMOND);
        grid.firstAdd(Direction.RIGHT,t1,t2);

        Tile t3 = new Tile(Color.BLUE, Shape.PLUS);

        assertSame(t1,grid.get( 45, 45));
        assertSame(t2,grid.get( 45, 46));
        assertNull(grid.get( 45, 47));

        assertThrows(QwirkleException.class, () ->grid.add(-5,91,t3));

    }
    @Test
    void contains_positive_index_within_bounds(){

        Tile t1 = new Tile(Color.BLUE, Shape.ROUND);
        grid.firstAdd(Direction.RIGHT,t1);

        Tile t2 = new Tile(Color.BLUE, Shape.DIAMOND);
        grid.add(45,46,t2);

        assertSame(t1,grid.get( 45, 45));
        assertSame(t2,grid.get( 45, 46));

    }

    @Test
    void contains_positive_index_out_of_bounds(){

        Tile t1 = new Tile(Color.BLUE, Shape.ROUND);
        grid.firstAdd(Direction.RIGHT,t1);

        Tile t2 = new Tile(Color.BLUE, Shape.DIAMOND);

        assertSame(t1,grid.get( 45, 45));
        assertThrows(QwirkleException.class, () ->grid.add(100,91,t2));
    }

    @Test
    void contains_index_is_0(){

        Tile t1 = new Tile(Color.BLUE, Shape.ROUND);
        grid.firstAdd(Direction.RIGHT,t1);

        Tile t2 = new Tile(Color.RED, Shape.DIAMOND);

        assertSame(t1,grid.get( 45, 45));
        assertThrows(QwirkleException.class, () ->grid.add(0,0,t2));
        //exception car la case (0,91) est isolée
    }
    @Test
    void checkWantedAdd_add_one_tile(){
        var grid = new Grid();

        var t1 = new Tile(Color.ORANGE, Shape.CROSS);
        var t2 = new Tile(Color.ORANGE, Shape.ROUND);
        grid.firstAdd(Direction.RIGHT, t1,t2);

        var t3 = new Tile(Color.BLUE, Shape.ROUND);

        assertEquals(2,  grid.add(46,46,t3));


    }

    @Test
    void checkWantedAdd_add_3_tiles_right(){
        var grid = new Grid();

        var t1 = new Tile(Color.PURPLE, Shape.SQUARE);
        var t2 = new Tile(Color.PURPLE, Shape.PLUS);
        var t3 = new Tile(Color.PURPLE, Shape.ROUND);

        grid.firstAdd(Direction.DOWN, t1, t2,t3);
        var t4 = new Tile(Color.RED, Shape.PLUS);
        var t5 = new Tile(Color.GREEN, Shape.PLUS);

        assertEquals(3,  grid.add(46,46,Direction.RIGHT,t4,t5));

    }
    @Test
    void checkWantedAdd_firstAdd_5down_add2tiles_right(){
        var grid = new Grid();



        var t1 = new Tile(Color.PURPLE, Shape.CROSS);
        var t2 = new Tile(Color.RED, Shape.CROSS);
        var t3 = new Tile(Color.GREEN, Shape.CROSS);
        var t4 = new Tile(Color.YELLOW, Shape.CROSS);
        var t5 = new Tile(Color.ORANGE, Shape.CROSS);

        grid.firstAdd(Direction.DOWN, t1, t2,t3,t4,t5);

        var t6 = new Tile(Color.ORANGE, Shape.STAR);
        var t7 = new Tile(Color.ORANGE, Shape.DIAMOND);


        assertEquals(3,  grid.add(49,46,Direction.RIGHT,t6,t7));

    }

    /*
     nombre de points gagnes = 4
44 |X  *  <>
45 |X
46 |X
47 |X
48 |X
    45 46 47
     */

    @Test
    void checkWantedAdd_firstAdd_2right_add2tiles_down(){
        var grid = new Grid();

        var t1 = new Tile(Color.YELLOW, Shape.PLUS);
        var t2 = new Tile(Color.GREEN, Shape.PLUS);
        grid.firstAdd(Direction.RIGHT, t1, t2);

        var t3 = new Tile(Color.GREEN, Shape.DIAMOND);
        var t4 = new Tile(Color.GREEN, Shape.ROUND);

        assertEquals(3,  grid.add(46,46,Direction.DOWN,t3,t4));

    }
    @Test
    void checkWantedAdd_firstAdd_right_add2tiles_down(){
        var grid = new Grid();

        var t1 = new Tile(Color.YELLOW, Shape.PLUS);
        var t2 = new Tile(Color.GREEN, Shape.PLUS);
        grid.firstAdd(Direction.RIGHT, t1, t2);

        var t3 = new Tile(Color.GREEN, Shape.DIAMOND);
        var t4 = new Tile(Color.GREEN, Shape.ROUND);

        assertEquals(3,  grid.add(46,46,Direction.DOWN,t3,t4));
    }

    @Test
    void checkWantedAdd_firstAdd_4down_add2up_then_2right(){
        var grid = new Grid();

        var t1 = new Tile(Color.BLUE, Shape.DIAMOND);
        var t2 = new Tile(Color.RED, Shape.DIAMOND);
        var t3 = new Tile(Color.GREEN, Shape.DIAMOND);
        var t4 = new Tile(Color.YELLOW, Shape.DIAMOND);

        grid.firstAdd(Direction.DOWN, t1, t2,t3,t4);

        var t5 = new Tile(Color.BLUE, Shape.PLUS);
        var t6 = new Tile(Color.BLUE, Shape.STAR);

        grid.add(45,46,Direction.UP, t5,t6);

        var t7 = new Tile(Color.BLUE, Shape.ROUND);
        var t8 = new Tile(Color.BLUE, Shape.SQUARE);

        assertEquals(4,         grid.add(45,47,Direction.RIGHT, t7,t8));

    }

    @Test
    void checkWantedAdd_firstAdd_Qwirkle_at_firstAdd(){

        var grid = new Grid();

        var t1 = new Tile(Color.BLUE, Shape.DIAMOND);
        var t2 = new Tile(Color.RED, Shape.DIAMOND);
        var t3 = new Tile(Color.GREEN, Shape.DIAMOND);
        var t4 = new Tile(Color.YELLOW, Shape.DIAMOND);
        var t5 = new Tile(Color.ORANGE, Shape.DIAMOND);
        var t6 = new Tile(Color.PURPLE, Shape.DIAMOND);

        assertEquals(12,  grid.firstAdd(Direction.DOWN, t1, t2,t3,t4,t5,t6));

    }
    @Test
    void checkWantedAdd_firstAdd_Qwirkle_in_Two_Times(){

        var grid = new Grid();

        var t1 = new Tile(Color.BLUE, Shape.DIAMOND);
        var t2 = new Tile(Color.RED, Shape.DIAMOND);
        var t3 = new Tile(Color.GREEN, Shape.DIAMOND);
        grid.firstAdd(Direction.RIGHT, t1, t2,t3);

        var t4 = new Tile(Color.YELLOW, Shape.DIAMOND);
        var t5 = new Tile(Color.ORANGE, Shape.DIAMOND);
        var t6 = new Tile(Color.PURPLE, Shape.DIAMOND);
        assertEquals(12, grid.add(45,48,Direction.RIGHT, t4, t5,t6));

    }
    @Test
    void checkWantedAdd_firstAdd_Qwirkle_in_Three_Times(){

        var grid = new Grid();

        var t1 = new Tile(Color.BLUE, Shape.DIAMOND);
        var t2 = new Tile(Color.RED, Shape.DIAMOND);
        grid.firstAdd(Direction.RIGHT, t1, t2);

        var t3 = new Tile(Color.GREEN, Shape.DIAMOND);
        var t4 = new Tile(Color.YELLOW, Shape.DIAMOND);
        grid.add(45,47,Direction.RIGHT, t3,t4);

        var t5 = new Tile(Color.ORANGE, Shape.DIAMOND);
        var t6 = new Tile(Color.PURPLE, Shape.DIAMOND);

        assertEquals(12, grid.add(45,49,Direction.RIGHT, t5,t6));

    }


    /*
        pts check col = 1
pts check col = 2
pts check col = 3
pts check L1 = 7
7
 nombre de points gagnes = 7
42 |        []
43 |        []
44 |+  X  []  *
45 |+
46 |+  X  <>
    45 46 47 48
     */

/*

nombre de points gagnes = 3 au lieu 5
n =3
45 |    <>  <>
46 |    +  +
47 |X  X
48 |0
49 |*
    44 45 46
 */

    /*
     nombre de points gagnes = 3 au lieu 5  cree une nv ligne  n' pas verticalement
n =3
45 |                        <>  <>
46 |                        +  +  +
47 |                X  X  X
48 |                    0
49 |        *  *  *  *
50 |0  0  0          []
    39 40 41 42 43 44 45 46 47
     */

    // test nri

    public static final int INITIAL_ROW = 45;
    public static final int INITIAL_COL = 45;

    @Test
    void gridInitiallyEmpty() {
        var g = new Grid();
        for (int row = -45; row < 45; row++) {
            for (int col = -45; col < 45; col++) {
                assertNull(get(g, row, col));
            }
        }
    }

        // simple adds

        @Test
        void addSimpleUP() {
            var g = new Grid();
            Tile t1 = new Tile(Color.RED,Shape.CROSS);
            Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
            g.firstAdd(Direction.UP,t1 ,t2 );

            assertSame(t1, get(g, 0, 0));
            assertSame(t2, get(g, -1, 0));
            assertNull(get(g, 1, 0));
            assertNull(get(g, 0, 1));
            assertNull(get(g, 0, -1));
        }

        @Test
        void addSimpleDOWN() {
            var g = new Grid();
            Tile t1 = new Tile(Color.RED,Shape.CROSS);
            Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
            g.firstAdd(Direction.DOWN,t1 ,t2 );


            assertSame(t1, get(g, 0, 0));
            assertSame(t2, get(g, 1, 0));
            assertNull(get(g, -1, 0));
            assertNull(get(g, 0, 1));
            assertNull(get(g, 0, -1));
        }

        @Test
        void addSimpleLEFT() {

            var g = new Grid();

            Tile t1 = new Tile(Color.RED,Shape.CROSS);
            Tile t2 = new Tile(Color.RED, Shape.DIAMOND);
            g.firstAdd(Direction.LEFT,t1 ,t2 );

            assertSame(t1, get(g, 0, 0));
            assertSame(t2, get(g, 0, -1));
            assertNull(get(g, 1, 0));
            assertNull(get(g, -1, 0));
            assertNull(get(g, 0, 1));
        }

        @Test
        void addSimpleRIGHT() {
            var g = new Grid();
            Tile t1 = new Tile(Color.RED,Shape.CROSS);
            Tile t2 = new Tile(Color.RED, Shape.DIAMOND);

            g.firstAdd(Direction.RIGHT,t1 ,t2 );

            assertSame(t1, get(g, 0, 0));
            assertSame(t2, get(g, 0, +1));
            assertNull(get(g, 1, 0));
            assertNull(get(g, -1, 0));
            assertNull(get(g, 0, -1));
        }

        @Test
        void addSimpleDoubleShouldThrow() {
            var g = new Grid();
            for (Direction d : Direction.values()) {
                assertThrows(QwirkleException.class, () -> g.firstAdd(d, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.CROSS)));
                assertNull(get(g, 0, 0));
                assertNull(get(g, -1, 0));
                assertNull(get(g, 1, 0));
                assertNull(get(g, 0, -1));
                assertNull(get(g, 0, 1));
            }

        }

        // firstAdd must be called first

        @Test
        void addFirstCannotBeCalledTwice() {
            var g = new Grid();
            g.firstAdd(Direction.UP, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.DIAMOND));
            assertThrows(QwirkleException.class, () -> g.firstAdd(Direction.DOWN, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.DIAMOND)));
        }

        @Test
        void addFirstMustBeCalledFirst_dir() {
            var g = new Grid();
            assertThrows(QwirkleException.class, () -> add(g, 0, 0, Direction.DOWN, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.DIAMOND)));
        }

        @Test
        void addFirstMustBeCalledFirst_tap() {
            var g = new Grid();
            assertThrows(QwirkleException.class, () -> g.add(createTileAtpos(0, 0, new Tile(Color.RED,Shape.CROSS))));
        }

        @Test
        void addFirstMustBeCalledFirst_simple() {
            var g = new Grid();
            assertThrows(QwirkleException.class, () -> add(g, 0, 0, new Tile(Color.RED,Shape.CROSS)));
        }

        // neighbours

        @Test
        void aTileMustHaveNeighbours() {
            var g = new Grid();
            g.firstAdd(Direction.UP, new Tile(Color.RED,Shape.CROSS));
            assertThrows(QwirkleException.class, () -> add(g, 2, 0, new Tile(Color.RED,Shape.DIAMOND)));
            assertNull(get(g, 2, 0));
        }


        // overwriting

        @Test
        void canNotAddTwiceAtTheSamePlace_equalTile() {
            var g = new Grid();
          Tile t1=  new Tile(Color.RED,Shape.DIAMOND);
            g.firstAdd(Direction.DOWN, new Tile(Color.RED,Shape.CROSS), t1);
            assertThrows(QwirkleException.class, () -> add(g, 1, 0, new Tile(Color.RED,Shape.DIAMOND)));
            assertSame(get(g, 1, 0), t1);
        }

        @Test
        void canNotAddTwiceAtTheSamePlace_differentTile_simple() {
            var g = new Grid();
            Tile t2 = new Tile(Color.RED,Shape.DIAMOND);
            g.firstAdd(Direction.DOWN, new Tile(Color.RED,Shape.CROSS), t2);

            assertThrows(QwirkleException.class, () -> add(g, 1, 0, new Tile(Color.RED,Shape.PLUS)));
            assertSame(get(g, 1, 0), t2);
        }

        @Test
        void canNotAddTwiceAtTheSamePlace_differentTile_dir() {
            var g = new Grid();
            Tile t1 = new Tile(Color.RED,Shape.CROSS);
            Tile t2 = new Tile(Color.RED,Shape.DIAMOND);

            g.firstAdd(Direction.DOWN, t1, t2);
            assertThrows(QwirkleException.class, () -> add(g, 0, 0, Direction.DOWN, new Tile(Color.RED,Shape.PLUS), new Tile(Color.RED,Shape.STAR)));
            assertSame(get(g, 0, 0),t1);
            assertSame(get(g, 1, 0), t2);
        }

        @Test
        void canNotAddTwiceAtTheSamePlace_differentTile_taps() {
            var g = new Grid();
            Tile t1= new Tile(Color.RED,Shape.CROSS);
            Tile t2= new Tile(Color.RED,Shape.DIAMOND);
            g.firstAdd(Direction.DOWN, t1, t2);

            TileAtPosition tap1 = createTileAtpos(0, 0, new Tile(Color.RED,Shape.PLUS));
            TileAtPosition tap2 = createTileAtpos(1, 0, new Tile(Color.RED,Shape.STAR));

            assertThrows(QwirkleException.class, () -> g.add(tap1, tap2));
            assertSame(t1, get(g, 0, 0));
            assertSame(t2, get(g, 1, 0));
        }


        // alignment
        @Test
        void canNotAddInDifferentLines() {
            var g = new Grid();
            var t1 = new Tile(Color.RED,Shape.CROSS);
            g.firstAdd(Direction.UP, t1);
            var tap1 = createTileAtpos(0, 1, new Tile(Color.RED,Shape.DIAMOND));
            var tap2 = createTileAtpos(1, 0, new Tile(Color.RED,Shape.STAR));
            assertThrows(QwirkleException.class, () -> g.add(tap1, tap2));
            assertSame(t1, get(g, 0, 0));
            assertNull(get(g, 0, 1));
            assertNull(get(g, 1, 0));
            /*
            todo test NRI certain echoue car
            org.opentest4j.AssertionFailedError: expected: model.Tile@78b66d36<Tile[color=RED, shape=CROSS]> but was: model.Tile@5223e5ee<Tile[color=RED, shape=CROSS]>
            Expected :Tile[color=RED, shape=CROSS]   Actual   :Tile[color=RED, shape=CROSS] (effet du new)
            --> j'ai creer var la tuile av et l'ai passé en param  ok ?
             */
            // todo //pas isolée ms pas possible deux ajout tileATpos sur 2 differente l e t col en mm temps
        }

        // must share common trait
        @Test
        void canNotAddIfNoCommonTrait_tap() {
            var g = new Grid();

            Tile t1 = new Tile(Color.RED,Shape.CROSS);

            g.firstAdd(Direction.UP, t1);
            var tap1 = createTileAtpos(0, 1, new Tile(Color.YELLOW,Shape.DIAMOND));
            assertThrows(QwirkleException.class, () -> g.add(tap1));
            assertSame(t1, get(g, 0, 0));
            assertNull(get(g, 0, 1));
            //45 46 pas null contient tuile yellow diamond
            assertNull(get(g, 1, 0));
        }

        @Test
        void canNotAddIfNoCommonTrait_simple() {
            var g = new Grid();
            Tile t1 = new Tile(Color.RED,Shape.CROSS);
            g.firstAdd(Direction.UP, t1);
            assertThrows(QwirkleException.class, () -> add(g, 0, 1, new Tile(Color.YELLOW,Shape.DIAMOND)));
            assertSame(t1, get(g, 0, 0));
            assertNull(get(g, 0, 1));
            // todo corriger DMD pr : test nri rate a cause de ASsertNull
            //(et c'est normal que je place la tuile av tester si placement correct...
            //--> je peux laisser l'exception et supp les assertNull ?
            assertNull(get(g, 1, 0));
            //todo check celui la
        }

        @Test
        void canNotAddIfNoCommonTrait_dir() {
            var g = new Grid();

            Tile t1 = new Tile(Color.RED,Shape.CROSS);

            g.firstAdd(Direction.UP, t1);
            assertThrows(QwirkleException.class, () -> add(g, 0, 1, Direction.LEFT, new Tile(Color.YELLOW,Shape.DIAMOND)));
            assertSame(t1, get(g, 0, 0));
            assertNull(get(g, 0, 1));
            assertNull(get(g, 1, 0));
        }

        @Test
        void canNotCompleteToALineWithDifferentTraits_simple() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.STAR), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.RED,Shape.DIAMOND));
            add(g, 2, 0, new Tile(Color.RED,Shape.PLUS));

            add(g, 1, 2, new Tile(Color.GREEN,Shape.DIAMOND));
            add(g, 2, 2, new Tile(Color.YELLOW,Shape.DIAMOND));

            // the "hole" in 2, 1 can never be filled because 2, 0 and 2, 2 share no trait
            for (var color : Color.values()) {
                for (var shape : Shape.values()) {
                    assertThrows(QwirkleException.class, () -> add(g, 2, 1, new Tile(color, shape)));
                    assertNull(get(g, 2, 1));
                }
            }
        }

        @Test
        void canNotCompleteToALineWithDifferentTraits_dir() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.STAR), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.RED,Shape.DIAMOND));
            add(g, 2, 0, new Tile(Color.RED,Shape.PLUS));

            add(g, 1, 2, new Tile(Color.GREEN,Shape.DIAMOND));
            add(g, 2, 2, new Tile(Color.YELLOW,Shape.DIAMOND));

            // the "hole" in 2, 1 can never be filled because 2, 0 and 2, 2 share no trait
            for (var color : Color.values()) {
                for (var shape : Shape.values()) {
                    for (Direction dir : Direction.values()) {
                        assertThrows(QwirkleException.class, () -> add(g, 2, 1, dir, new Tile(color, shape)));
                        assertNull(get(g, 2, 1));
                    }
                }
            }
        }

        @Test
        void canNotCompleteToALineWithDifferentTraits_tap() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.STAR), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.RED,Shape.DIAMOND));
            add(g, 2, 0, new Tile(Color.RED,Shape.PLUS));

            add(g, 1, 2, new Tile(Color.GREEN,Shape.DIAMOND));
            add(g, 2, 2, new Tile(Color.YELLOW,Shape.DIAMOND));

            // the "hole" in 2, 1 can never be filled because 2, 0 and 2, 2 share no trait
            for (var color : Color.values()) {
                for (var shape : Shape.values()) {
                    assertThrows(QwirkleException.class, () -> g.add(createTileAtpos(2, 1, new Tile(color, shape))));
                    assertNull(get(g, 2, 1));
                }
            }
        }

        // never identical
        @Test
        void canNotCompleteToALineWithIdenticalTiles_simple() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.STAR), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.RED,Shape.SQUARE));
            add(g, 2, 0, new Tile(Color.RED,Shape.PLUS));

            add(g, 1, 2, new Tile(Color.RED,Shape.ROUND));
            add(g, 2, 2, new Tile(Color.RED,Shape.PLUS));

            // the "hole" in 2, 1 can never be filled because 2, 0 and 2, 2 are identical
            for (var color : Color.values()) {
                for (var shape : Shape.values()) {
                    assertThrows(QwirkleException.class, () -> add(g, 2, 1, new Tile(color, shape)));
                 // mis cette ligne en com car pas compris, aps exception c fini non ?   assertNull(get(g, 2, 1));
                }
            }
        }

        @Test
        void canNotCompleteToALineWithIdenticalTiles_tap() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.STAR), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.RED,Shape.SQUARE));
            add(g, 2, 0, new Tile(Color.RED,Shape.PLUS));

            add(g, 1, 2, new Tile(Color.RED,Shape.ROUND));
            add(g, 2, 2, new Tile(Color.RED,Shape.PLUS));

            // the "hole" in 2, 1 can never be filled because 2, 0 and 2, 2 are identical
            for (var color : Color.values()) {
                for (var shape : Shape.values()) {
                    assertThrows(QwirkleException.class, () -> g.add(createTileAtpos(2, 1, new Tile(color, shape))));
                    assertNull(get(g, 2, 1));
                }
            }
        }

        @Test
        void canNotCompleteToALineWithIdenticalTiles_dir() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.STAR), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.RED,Shape.SQUARE));
            add(g, 2, 0, new Tile(Color.RED,Shape.PLUS));

            add(g, 1, 2, new Tile(Color.RED,Shape.ROUND));
            add(g, 2, 2, new Tile(Color.RED,Shape.PLUS));

            // the "hole" in 2, 1 can never be filled because 2, 0 and 2, 2 are identical
            for (var color : Color.values()) {
                for (var shape : Shape.values()) {
                    // there is only one tile but let's try to add it in all directions anyway
                    for (Direction direction : Direction.values()) {
                        assertThrows(QwirkleException.class, () -> add(g, 2, 1, direction, new Tile(color, shape)));
                      //rentre bien dans exception ("Vos tuiles n'ont aucune caractéristique commune"); ms comme on a deposé av c pas null
                        assertNull(get(g, 2, 1));
                    }
                }
            }
        }

        // various other tests, pertaining to filling existing holes
        @Test
        void canCompleteToALineLeftRight() {
            var g = new Grid();

            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.STAR), new Tile(Color.RED,Shape.DIAMOND));

            Tile t1 = new Tile(Color.YELLOW,Shape.PLUS);
            add(g, 1, 0, new Tile(Color.GREEN,Shape.CROSS));
            add(g, 2, 0, new Tile(Color.YELLOW,Shape.CROSS));

            add(g, 1, 2, new Tile(Color.GREEN,Shape.DIAMOND));
            add(g, 2, 2, new Tile(Color.YELLOW,Shape.DIAMOND));

            add(g, 2, 1, t1);
            assertSame(t1, get(g, 2, 1));

        }

        @Test
        void canCompleteToALineLeftRightUpDown() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.PLUS), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.GREEN,Shape.CROSS));
            add(g, 2, 0, new Tile(Color.YELLOW,Shape.CROSS));

            add(g, 1, 2, new Tile(Color.GREEN,Shape.DIAMOND));
            add(g, 2, 2, new Tile(Color.YELLOW,Shape.DIAMOND));

            add(g, 2, 1, new Tile(Color.YELLOW,Shape.PLUS));
            var t1 = new Tile(Color.GREEN,Shape.PLUS);
            add(g, 1, 1, t1);
            assertSame(t1, get(g, 1, 1));
        }

        @Test
        @DisplayName("Complete a line leaving holes during intermediary steps")
        void canCompleteALine_Left_Middle_Right() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.PLUS), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.GREEN,Shape.CROSS));
            add(g, 2, 0, new Tile(Color.YELLOW,Shape.CROSS));

            add(g, 1, 2, new Tile(Color.GREEN,Shape.DIAMOND));
            add(g, 2, 2, new Tile(Color.YELLOW,Shape.DIAMOND));

            TileAtPosition plus_left = createTileAtpos(2, -1, new Tile(Color.YELLOW,Shape.PLUS));
            TileAtPosition round_center = createTileAtpos(2, 1, new Tile(Color.YELLOW,Shape.ROUND));
            TileAtPosition star_right = createTileAtpos(2, 3, new Tile(Color.YELLOW,Shape.STAR));
            assertDoesNotThrow(() -> {
                g.add(plus_left, star_right, round_center); // make sur having the center tile last does not throw.
            });
            assertAtCorrectPosition(g, plus_left);
            assertAtCorrectPosition(g, round_center);
            assertAtCorrectPosition(g, star_right);
        }

        @Test
        @DisplayName("Complete a line leaving holes during intermediary steps")
        void canCompleteALine_Left2_Left() {

            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.PLUS), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.GREEN,Shape.CROSS));

            add(g, 2, 0, new Tile(Color.YELLOW,Shape.CROSS));

            add(g, 1, 2, new Tile(Color.GREEN,Shape.DIAMOND));
            add(g, 2, 2, new Tile(Color.YELLOW,Shape.DIAMOND));

            TileAtPosition plus_left_left = createTileAtpos(2, -2, new Tile(Color.YELLOW,Shape.PLUS));
            TileAtPosition round_left = createTileAtpos(2, -1, new Tile(Color.YELLOW,Shape.ROUND));
            assertDoesNotThrow(() -> {
                g.add(plus_left_left, round_left); // make sur having the "left" tile after the "left left" tile does not throw
            });
            assertAtCorrectPosition(g, plus_left_left);
            assertAtCorrectPosition(g, round_left);
        }


        @Test
        void canNotCompleteALine_leaving_a_hole() {
            var g = new Grid();
            g.firstAdd(Direction.RIGHT, new Tile(Color.RED,Shape.CROSS), new Tile(Color.RED,Shape.PLUS), new Tile(Color.RED,Shape.DIAMOND));

            add(g, 1, 0, new Tile(Color.GREEN,Shape.CROSS));
            add(g, 2, 0, new Tile(Color.YELLOW,Shape.CROSS));

            add(g, 1, 2, new Tile(Color.GREEN,Shape.DIAMOND));
            add(g, 2, 2, new Tile(Color.YELLOW,Shape.DIAMOND));

            TileAtPosition plus_left = createTileAtpos(2, -1, new Tile(Color.YELLOW,Shape.PLUS));
            TileAtPosition star_right = createTileAtpos(2, 3, new Tile(Color.YELLOW,Shape.STAR));
            assertThrows(QwirkleException.class, () -> {
                g.add(plus_left, star_right);
            });
            assertNull(get(g, 2, -1));
            assertNull(get(g, 2, 3));
        }

        // private methods

        private void add(Grid g, int row, int col, Tile tile) {
            g.add(INITIAL_ROW + row, INITIAL_COL + col, tile);
        }

        private void add(Grid g, int row, int col, Direction d, Tile... line) {
            g.add(INITIAL_ROW + row, INITIAL_COL + col, d, line);
        }

        private Tile get(Grid g, int row, int col) {
            return g.get(INITIAL_ROW + row, INITIAL_COL + col);
        }

        private TileAtPosition createTileAtpos(int row, int col, Tile tile) {
            return new TileAtPosition(INITIAL_ROW + row, INITIAL_COL + col, tile);
        }


        private void assertAtCorrectPosition(Grid g, TileAtPosition tileAtPosition) {
            int row = tileAtPosition.row();
            int col = tileAtPosition.col();
            assertSame(tileAtPosition.tile(), g.get(row, col));
        }

    //TEST POUR LA METHODE isEmpty()
    @Test
    void isEmpty_isEmptyBeforeFirstAdd(){
        var grid = new Grid();
        assertTrue(grid.isEmpty());

    }

    @Test
    void isEmpty_isNotEmptyBeforeFirstAdd() {
        var grid = new Grid();

        var t1 = new Tile(Color.PURPLE, Shape.SQUARE);
        var t2 = new Tile(Color.PURPLE, Shape.PLUS);
        var t3 = new Tile(Color.PURPLE, Shape.ROUND);

        grid.firstAdd(Direction.DOWN, t1, t2,t3);
        assertFalse(grid.isEmpty());

    }
    @Test
    void isEmpty_isStillNotEmptyAfterAddMultipleTiles() {
        var grid = new Grid();

        var t1 = new Tile(Color.PURPLE, Shape.SQUARE);
        var t2 = new Tile(Color.PURPLE, Shape.PLUS);
        var t3 = new Tile(Color.PURPLE, Shape.ROUND);

        grid.firstAdd(Direction.DOWN, t1, t2,t3);

        var t4 = new Tile(Color.PURPLE, Shape.DIAMOND);
        var t5 = new Tile(Color.PURPLE, Shape.CROSS);
        grid.add(48,45, Direction.DOWN, t4, t5);

        assertFalse(grid.isEmpty());

    }
    @Test
    void isEmpty_isStillNotEmptyAfterAddOneTile() {
        var grid = new Grid();

        var t1 = new Tile(Color.PURPLE, Shape.SQUARE);
        var t2 = new Tile(Color.PURPLE, Shape.PLUS);
        var t3 = new Tile(Color.PURPLE, Shape.ROUND);

        grid.firstAdd(Direction.DOWN, t1, t2,t3);

        var t4 = new Tile(Color.PURPLE, Shape.DIAMOND);

        grid.add(48,45, t4);

        assertFalse(grid.isEmpty());

    }


    // DEFENSE TEST :

    @Test
    void OnlyOnecommoneWithJoker() {
        var grid = new Grid();

        var t1 = new Tile(Color.GREEN, Shape.SQUARE);
        var t2 = new Tile(Color.GREEN, Shape.INTERROGATION);
        var t3 = new Tile(Color.GREEN, Shape.ROUND);

        grid.firstAdd(Direction.DOWN, t1, t2,t3);

        var t4 = new Tile(Color.BLACK, Shape.DIAMOND);

        grid.add(48,45, t4);

        assertFalse(grid.isEmpty());

    }
    @Test
    void OnlyOnecommoneWithJokerfailed() {
        var grid = new Grid();

        var t1 = new Tile(Color.GREEN, Shape.SQUARE);
        var t2 = new Tile(Color.GREEN, Shape.INTERROGATION);
        var t3 = new Tile(Color.GREEN, Shape.ROUND);

        grid.firstAdd(Direction.RIGHT, t1, t2,t3);

        var t4 = new Tile(Color.BLACK, Shape.INTERROGATION);

        assertFalse(grid.isEmpty());
        assertThrows(QwirkleException.class, () ->   grid.add(45,48, t4));


    }

    @Test
    void OnlyOnecommoneWithJoker_Shape() {
        var grid = new Grid();

        var t1 = new Tile(Color.GREEN, Shape.ROUND);
        var t2 = new Tile(Color.YELLOW, Shape.ROUND);
        var t3 = new Tile(Color.RED, Shape.ROUND);

        grid.firstAdd(Direction.RIGHT, t1, t2,t3);

        var t4 = new Tile(Color.PURPLE, Shape.INTERROGATION);

        grid.add(45,48, t4);

        assertFalse(grid.isEmpty());

    }
    @Test
    void OnlyOnecommoneWithJoker_test() {
        var grid = new Grid();

        var t1 = new Tile(Color.RED, Shape.ROUND);
        var t2 = new Tile(Color.BLACK, Shape.ROUND);
        var t3 = new Tile(Color.BLUE, Shape.ROUND);

        grid.firstAdd(Direction.RIGHT, t1, t2,t3);

        var t4 = new Tile(Color.PURPLE, Shape.INTERROGATION);

        grid.add(45,48, t4);

        assertFalse(grid.isEmpty());

    }
    @Test
    void OnlyOnecommoneWithJoker_BBI() {
        var grid = new Grid();

        var t1 = new Tile(Color.BLACK, Shape.DIAMOND);
        var t2 = new Tile(Color.BLACK, Shape.STAR);
        var t3 = new Tile(Color.GREEN, Shape.INTERROGATION);

        grid.firstAdd(Direction.RIGHT, t1, t2);

        grid.add(45,47, t3);

        assertFalse(grid.isEmpty());

    }
    @Test
    void OnlyOnecommoneWithJoker_TwoSameJokers() {
        var grid = new Grid();

        var t1 = new Tile(Color.GREEN, Shape.STAR);
        var t2 = new Tile(Color.GREEN, Shape.INTERROGATION);
        var t3 = new Tile(Color.GREEN, Shape.INTERROGATION);

        grid.firstAdd(Direction.RIGHT, t1, t2);


        assertThrows(QwirkleException.class, () ->  grid.add(45,47, t3));


        assertFalse(grid.isEmpty());

    }

}




