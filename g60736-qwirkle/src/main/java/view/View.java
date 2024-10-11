package view;

import model.*;
import java.util.List;

public class View {
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Display the gridView of the game
     * @param grid the gridView of the game
     */
    public static void display(GridView grid) {

        int firstNotNullRow = -1 ;
        int firstNotNullCol = -1;
        int lastNotNullRow = -1;
        int lastNotNullCol = -1;

        for (int i = 0; i < Grid.SIDE; i++) {

            for (int j = 0; j < Grid.SIDE; j++) {

                if (grid.get(i, j) != null) {

                //    ||  firstNotNullCol> j   || firstNotNullCol> j   --> pr <-> intervalle en l et col
                    if (firstNotNullRow == -1 || firstNotNullRow > i){
                        firstNotNullRow = i;
                    }
                    if (firstNotNullCol == -1 || firstNotNullCol> j){
                        firstNotNullCol = j;
                    }
                    if (lastNotNullRow < i){
                        lastNotNullRow = i;
                    }
                    if (lastNotNullCol < j){
                        lastNotNullCol = j;
                    }

                }

            }
        }

        for (int k = firstNotNullRow; k <= lastNotNullRow; k++) {

                    System.out.print(k+ " |");

                    for (int l= firstNotNullCol; l <= lastNotNullCol; l++) {
                        if (grid.get(k, l) == null){
                            System.out.print("    ");
                        }
                        else {
                            System.out.print(makeTheTileRight(grid.get(k, l))+" ");

                        }
                    }
                    System.out.println();
        }
        System.out.print("   ");

        //   Après avoir afficher tuiles/cases vides, on affiche les indices des colones

        for (int m = firstNotNullCol; m <= lastNotNullCol; m++) {

                System.out.print(" "+m );
        }
        System.out.println();

    }

    /**
     * Give the correspondant-string of the given tile
     * @param tile the given tile
     * @return the correspondant-string of the given tile
     */
    private static String  makeTheTileRight (Tile tile ){

        String color="";
        String shape="";

        switch (tile.color()){
                                case RED -> color = "\033[0;31m";
                                case BLUE -> color = "\033[0;34m";
                                case GREEN -> color = "\033[0;32m";
                                case ORANGE -> color = "\033[38;2;255;121;0m";
                                case YELLOW ->color = "\033[0;33m";
                                case PURPLE -> color = "\033[0;35m";
                                case BLACK -> color = "\033[0;30m";


                            }

        switch (tile.shape()){
                                    case PLUS ->shape = "+" ;
                                    case STAR ->shape = "*" ;
                                    case CROSS ->shape = "X";
                                    case ROUND ->shape= "0";
                                    case SQUARE -> shape = "[]" ;
                                    case DIAMOND ->shape = "<>" ;
                                    case INTERROGATION -> shape = "?";
                                }
        return color+shape+ANSI_RESET+" ";
    }

    /**
     *  Display the player'hand
     * @param name the player's name
     * @param playerHand the player's hand
     */
    public static void display(String name, List<Tile> playerHand, int playerScore){
        System.out.println();

        System.out.println("C'est le tour de "+ANSI_BLUE +name + ANSI_RESET);

        System.out.println("Voici les tuiles de votre main");
        for (int i = 0; i < playerHand.size(); i++) {

            System.out.print(View.makeTheTileRight(playerHand.get(i)));

        }

        System.out.println();

        for (int i = 0; i < playerHand.size(); i++) {

            System.out.print(i+" ");

        }
        System.out.println();
        System.out.println("votre score actuel :  "+ playerScore);

    }

    /**
     * Display the commands of the game
     */
    public static void displayHelp(){

        System.out.println("Q W I R K L E\n" +
                "Qwirkle command:\n" +
                "- play 1 tile : o <row> <col> <i>\n" +
                "- play line: l <row> <col> <direction> <i1> [<i2>]\n" +
                "- play plic-ploc : m <row1> <col1> <i1> [<row2> <col2> <i2>]\n" +
                "- play first : f <i1> [<i2>]\n" +
                "- pass : p\n" +
                "- quit : q\n" +
                "- save : s\n" +
                "i : index in list of tiles\n" +
                "d : direction in l (left), r (right), u (up), d(down)");

    }
    public static void displayError(String message){

        System.out.println(ANSI_BLUE +message+ANSI_RESET );

    }
    public static void displayTheWinner(String name){

        System.out.println("Fin du jeu");
        System.out.println("Le gagnant est"+ANSI_BLUE +name+ANSI_RESET +"!" );

    }
}
