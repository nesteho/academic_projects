    package model;

    import model.exception.QwirkleException;
    import view.View;

    import java.io.*;
    import java.util.List;

    /**
     * Initialization of a part of the game
     */
    public class Game  implements Serializable {
        private Grid grid;
        private Player[] players;
        int currentPlayer;


        /**
         * Constructor of the game
        * @param names names of the players
        */
        public Game(List<String>names){

            grid = new Grid();
            players = new Player[names.size()];
            currentPlayer = 0;

            for (int i = 0; i <players.length ; i++) {

                players[i]=new Player(names.get(i));
            }
        }
        /**
        * Tries to play the move for the current player
        * @param d the direction of the add
        * @param is the indexes of the tiles in the current player hand
        */
         public void first(Direction d, int... is){

             Tile[] tiles = prepareTilesToAdd(is);

             players[currentPlayer].addScore(grid.firstAdd(d,tiles));

             removeRefillAndPass(is);

         }

        // add est pas mth static tu n' pas besoin d'appeler avc nom de la classe classe.meth : pour appeler ds context static

        /**
         * Plays the current player add
         * @param row the row where the player wants to place the tile
         * @param col the col where the player wants to place the tile
         * @param index the index of the tile from the current player hand to place
         */
        public void play(int row, int col, int index){

            players[currentPlayer].addScore(

                    grid.add(row,col, prepareTilesToAdd(index)[0] )
            );

            removeRefillAndPass(index);

        }

        /**
         * Plays the current player add
         * @param row the row where the player wants to place the first tile
         * @param col the col where the player wants to place the first tile
         * @param indexes the indexes of the tiles from the current player hand to place
         */
        public void play(int row, int col, Direction d, int... indexes){

            Tile[] tiles = prepareTilesToAdd(indexes);

            players[currentPlayer].addScore(grid.add(row,col,d,tiles));

           removeRefillAndPass(indexes);
        }

        /**
         * Plays the current player add
         * @param is the indexes of the row, the col and the tiles to add
         */
        public void play(int... is){

            if (is.length%3 != 0){
                throw new QwirkleException("le nombre de paramètres est invalide");

            }

            int[] tileIndexes = new int[is.length/3];  //mettre les indices des tuiles ds un tab
            int i = 0;

            for (int indexTile = 2; indexTile < (is.length); indexTile++) {

                tileIndexes[i] = is[indexTile];

                indexTile+=3;
                i++;
            }

            i=0;

            TileAtPosition[] taps = new TileAtPosition[is.length/3];

            for (int j = 0; j < (is.length); j+=3) {

                taps[i] = new TileAtPosition(is[j],is[j+1],prepareTilesToAdd(is)[i] );
                i++;

            }

            players[currentPlayer].addScore(grid.add(taps));
            removeRefillAndPass(tileIndexes);
        }

        /**
         * Getter of the current player name
         * @return the current player name
         */
        public String getCurrentPlayerName(){return players[currentPlayer].getName();
        }

        /**
         * Getter the current player hand
         * @return the current player hand
         */
        public List<Tile> getCurrentPlayerHand(){
            return players[currentPlayer].getHand();

        }

        /**
         * Passes the current player to the next player
         */
        public void pass(){

            if (currentPlayer == players.length-1){
                currentPlayer=0;
            }
            else {
                currentPlayer++;
            }

        }

        /**
         * Getter of the gridView of the game
         * @return the gridView of the game
         */
        public GridView getGrid(){

            GridView gridView = new GridView(grid);

            return  gridView;

        }
        public Grid getGridR(){
            return  grid;
        }
        /**
         * Getter of the current player's score
         * @returnthe current player's score
         * */
        public int getCurrentPlayerScore  (){
            return players[currentPlayer].getScore();
        }

        /**
         * Return the state of the game
         * @return whether the game is over or not
         * */
        public boolean isOver(){

            if (players[currentPlayer].getHand().size()==0){

                players[currentPlayer].addScore(6);

                View.displayTheWinner(andTheWinnerIs().getName());

                return true;
            }

            if (Bag.getInstance().size() == 0){
                System.out.println("La pioche est vide- le premier joueur a déposé toutes ses tuiles termine la partie" );
            }

            for (int i = 0; i < players.length; i++) {
                for (int j = 0; j < players[i].getHand().size(); j++) {
                    for (int k = 0; k < Grid.SIDE ; k++) {
                        for (int l = 0; l < Grid.SIDE; l++) {

                            try {

                                if (grid.isEmpty()){
                                    return false;
                                    //avant le premier tour , grille du jeu vide --> on a pas commenncé -> on a pas terminé

                                }
                                grid.add(k, l , players[i].getHand().get(j));
                                grid.removeATileFromACase(k,l);
                                return false;
                            }
                            catch (QwirkleException q){

                            };

                        }
                    }
                }
            }
            return true;

        }

        private Tile[] prepareTilesToAdd(int... indexes){

            Tile[] tiles = new Tile[indexes.length];

            for (int i = 0; i < indexes.length ; i++) {

                tiles[i] = players[currentPlayer].getHand().get(indexes[i]);
                //  la tuile à la position i de la main du joueur courant
                //  est mise à l'indice i d'un nv tableau de tuiles

            }
            return tiles;
        }
        private Player andTheWinnerIs(){

            Player winner =players[0];
            for (Player p:players) {
                if (p.getScore()> winner.getScore()){
                    winner= p;
                }
            }
            System.out.println();
            return winner;
        }
        private void removeRefillAndPass (int... indexes){

            players[currentPlayer].remove( prepareTilesToAdd(indexes));
            players[currentPlayer].refill();
            pass();
        }


        // ecrire/ creer le fichier
        //a appeler ds App
        public static void write( Game game, Bag bag, String fileName)throws IOException, QwirkleException{

            FileOutputStream fos =   new FileOutputStream(fileName);
            //  conversion java--> text
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(game);  //appeler une methode de la classe ObjectOutputStream sur un objet de la classe ObjectOutputStream
            oos.writeObject(bag);

            oos.close();

        }


        //charger fichier enregistré + reprendre partie
        public static Game getFromFile( String fileName) throws IOException, ClassNotFoundException,QwirkleException {

            String file= fileName;
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);
            Game game= (Game) ois.readObject();
            Bag bag = (Bag) ois.readObject();
            // stocker bag ds var instance + remplacer le bag de classe bag par celui la
            ois.close();
            return game;
        }
    }
