import model.*;
import model.exception.QwirkleException;
import view.View;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int nbPlayers = lireEntier("Entrez le nombre de joueurs svp, entre 2 et 4",2,4);
        Game game =askForGame(nbPlayers);
        askIfNeedHelp();

        int round = 0;
        boolean timeTostop =false;

        while ( !timeTostop && !game.isOver()  ) {  //&& pas q  q = arreter partie sans l'enregistrer av
            System.out.println();
            round++;
            System.out.println("Manche " + round);

            for (int i = 0; i < nbPlayers; i++) {
                boolean wrongAdd = true;

                while (wrongAdd){

                   try {
                     timeTostop =  playAnAdd(game);
                     wrongAdd = false;
                   }
                   catch (QwirkleException q){
                       View.displayError(q.getMessage());
                       //ceci renvoie le message exact de l'exception qui a été déclenchée dans playAnAdd
                   }
               }

                if (timeTostop){
                    break;
                }

                //tant que ajout pas fait (car pas correct )--> on redemande au même joueur de re-rentrer une commande

            }
        }

    }

    private static boolean playAnAdd(Game game) throws IOException {

        View.display(game.getCurrentPlayerName(),game.getCurrentPlayerHand(),game.getCurrentPlayerScore());

        String play = checkPlay("Entrez la commande de l'ajout que vous souhaitez faire");
        String[] aSplit = play.split("\\s+");

        char p = aSplit[0].charAt(0);
        int row ;
        int col ;
        Direction dir;
        int[] indexes;

        if (p =='f'){

            dir = checkDirection(aSplit[1].charAt(0));
            indexes = new int[aSplit.length-2];

            for (int i = 2; i < aSplit.length ; i++) {
                indexes[i-2] = Integer.parseInt(aSplit[i]);
            }

            game.first( dir, indexes );

        }
        else if (p =='l') {

            row =  Integer.parseInt(aSplit[1]);
            col =  Integer.parseInt(aSplit[2]);
            dir = checkDirection(aSplit[3].charAt(0));
            indexes = new int[aSplit.length-4];
            for (int i = 4; i < aSplit.length ; i++) {
                indexes[i-4] = Integer.parseInt(aSplit[i]);
            }

            game.play(row,col,dir,indexes);

        } else if (p =='o') {

            row =  Integer.parseInt(aSplit[1]);
            col =  Integer.parseInt(aSplit[2]);
            int index =  Integer.parseInt(aSplit[3]);

            game.play(row,col, index);
        }
        else if (p=='m') {

            indexes = new int[aSplit.length-1];

            for (int i = 1; i < aSplit.length ; i++) {
                indexes[i-1] = Integer.parseInt(aSplit[i]);
            }

            game.play(indexes);

        }
        else if (p == 'q') {

            return  true;
        }
        else if (p=='s') {
            // sauver partie avant de quitter (comme ça on pourra reprendre plus tard la partie avec  meth serializeDataIn


            String  fileName =lireString("Entrez le nom sous lequel vous voulez enregistrer le fichier et l'extension");
            File f = new File("C:/"+fileName);
            f.createNewFile();
            Game.write(game, Bag.getInstance(),f.getName());
            return true;
        }
        else  {
            game.pass();

        }

        View.display(game.getGrid());
        return false;
    }
    private static Game  askForGame(int nbPlayers) throws IOException, ClassNotFoundException {

        Game game;

        System.out.println("Voulez vous reprendre votre partie ? -oui");
        Scanner clavier = new Scanner(System.in);
        String reprendre = clavier.nextLine();

        if (reprendre.equals("oui")){

            String  fileName =lireString("Entrez le nom du fichier que vous voulez ouvrir et terminez par l'extension .txt");
            game=  Game.getFromFile(fileName);
            //charger partie en cours av de reprendre partie
        }

        else{

            game = new Game(asksPlayersNames(nbPlayers));
        }

        return game;

    }
    private static void askIfNeedHelp(){

        Scanner clavier = new Scanner(System.in);
        System.out.println(" Voulez-vous afficher les commandes du jeu ?, si oui : entrez 'oui' ");
        String help = clavier.nextLine();

        if (help.equals("oui")){
            View.displayHelp();
        }

    }
    private static String checkPlay(String message){
        Scanner clavier =new Scanner(System.in);
        String play ;
        do {
            System.out.println(message);
            play = clavier.nextLine();

        } while (  !play.matches("p\\s*")
                && !play.matches("q\\s*")
                &&  !play.matches("s\\s*")
                && !play.matches("[f]+\\s+[udlr]{1}(\\s+[0-5])+\\s*")
                && !play.matches("[o]+\\s+\\d+\\s+\\d+\\s+[0-5]{1}\\s*")
                //                       l 44 44 u 2 3
                && !play.matches("[l]+\\s+\\d+\\s+\\d+\\s+[udlr]{1}(\\s+[0-5]{1})+\\s*")
                && !play.matches("[m](\\s+\\d+\\s+\\d+\\s+[0-5]{1})+\\s*")
        );

        return play;
    }

    private static List<String> asksPlayersNames(int nbPlayers){
        List<String> names = new ArrayList<>(nbPlayers);
        Scanner clavier =new Scanner(System.in);
        for (int i = 0; i < nbPlayers; i++) {

            System.out.println("Entrez le nom du joueur "+i);
            String name= clavier.nextLine();
            names.add(name);

        }
        return names;
    }

    private static Direction checkDirection(char d){
        Direction dir;

        switch (d){

            case 'l' -> dir = Direction.LEFT;
            case 'r' -> dir = Direction.RIGHT;
            case 'u' -> dir = Direction.UP;
            default ->  dir = Direction.DOWN;

        }
        System.out.println();
        return dir;

    }
    private static int lireEntier(String message, int min, int max){

        int val=lireEntier(message);
        while (val>max || val<min) {

            val=lireEntier(message);
        }

        return val;
    }
    private static int lireEntier(String message){

        Scanner clavier= new Scanner(System.in);
        System.out.println(message);

        while (!clavier.hasNextInt()){
            clavier.next();
            System.out.println("L'entrée saisie n’est pas un entier.");
            System.out.println(message);
        }
        return clavier.nextInt();
    }
    private static String lireString(String message){

        Scanner clavier= new Scanner(System.in);
        System.out.println(message);

        while (!clavier.hasNextLine()){
            clavier.next();
            System.out.println("L'entrée saisie n’est pas une chaine de caractère.");
            System.out.println(message);
        }
        return clavier.nextLine();
    }

}
