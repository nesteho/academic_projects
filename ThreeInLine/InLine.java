package inline;


//import g60736.dev1.td1.Exercice1;
import g60736.dev1.td10.Exercice6;
import g60736.dev1.td11.Exercice1;
import g60736.dev1.td8.Lecture;

import java.util.Scanner;

//appeler meth < autre package : import signature du package.nomClasse;
public class InLine {
    //écrire javadoc av écrire code
    /**
     * Déplace une bille dans le tableau du jeu
     * elle doit se déplacer vers une case vide
     * elle ne peut sauter qu'au dessus d'une bille au maximum
     * @param is le tableau du jeu
     * @param from  est la position de départ
     * @param to est la position d’arrivée
     */
    public static void move(int[] is, int from, int to){
        if ( from>= is.length|| from<0 || is[from] ==(0)
                || to>= is.length|| to<0||is[to]!=0){

            throw new IllegalArgumentException("les paramètres ne permettent" +
                    "pas de faire le déplacement");
        }
        // attentio ordre cond est imp is[from] ==(0) ||  from>= is.length|| from<0 est faux et lance exception
        is[to]=is[from];
        is[from]=0;
        //Attention oubli d'écrire l code de ce que move fait; à sav : is[to]=is[from] et [from]=0;

        // erreur : !element.equals |  is[from] !.equals(qqch)
        //aussi null et .equals c que pr String ! Ici on gère entiers --> == != et 0 (pour dire vide)
        //is[to]!=0 veut dire : s'il y a un element a la pos "to"
    }

    /**
     * Ajoute deux billes à deux endroits libres aléatoires du tableau de jeu
     * @param is le tableau de jeu
     */
    public static void add2balls (int[] is){

        //while (is[i]!=0&& is[j]!=0){
            //apt 1er tour
        int i;
        int j;
        do {
            i=(int)(Math.random()*is.length)+0;
            //i=(int)(0+Math.random())*is.length-1;
            // indice est au hasard entre 0 et 19-1 ( car le nombre aps * n'est pas inclus--> c pas 20-1)
            // pb au niveau des parentaise . sol/ordre=  (int)[qui est arrondi] (Math.random()*nb element)+décalage


                j=(int)(Math.random()*is.length)+0;
                if (j==i){
                    j= (int)(Math.random()*is.length)+0;
                }

            }while (i==j || is[i] !=0 || is[j] !=0) ;
                //cond ds while : i et j=/ && elements a pos i et j st =0 (case vide)
                // do while et non while car faut au moin 1 tour et on sort des que c ok
        int nb1=1+(int)(Math.random()*5);
        is[i]=nb1;
        int nb2= (int)(1+Math.random())*5;
            is[j]=nb2;


       // }
    }

    /**
     * affiche le tableau de jeu
     * @param is le tableau de jeu
     */
    public static void display(int[] is){
       // Exercice1.afficherTab(is);

         /*ce que j'ai fait :  for ( int element:is) {
            System.out.print(element+" ");
        } */
        //Oubli/erreur/pasPriseEnCompte : il faut un espace vide qd il y a des 0
        //Oubli/erreur/pasPriseEnCompte : il faut respecter mise en page : les "limite" du tableau de jeu et de chq case
        //sol:
        for (int i = 0; i < 3*is.length; i++) {
            System.out.print("-");

        }
        //  3*is.length | 1 indice ( sous le  tableau)prend 3 espace -> tt est aligné
        System.out.println();
        for ( int element:is) {

            System.out.print(element+" |");
        }
        System.out.println();
        for (int i = 0; i < 3*is.length; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < is.length; i++) {

            if (i<10){
                System.out.print("0"+i);
                System.out.print(" ");
            }
            else {
                System.out.print(i+" ");

            }
        }
    }

    /**
     * retire les billes dès lors qu’il y a
     *trois billes de même couleurs consécutives.
     * @param is le tableau de jeu
     */
    public static void remove3inline(int[] is){
        for (int i = 0; i < is.length-2 ; i++) {
            int j = i+1;
            if (is[i] !=0){
            while (j <is.length&& is[i]==is[j]) {
                j++;
                }
            // position j est la pos du 1er elm != de ceux d'av
            int nb=i-j;
            if (nb>=3){
                for (int k = 0; k <j ; k++) {
                    is[k]=0;
                }
            }

            //nb case similaire est donnée par diff i et j
            }

            if (is[i] !=0 && i<= is.length-2 && is[i]==is[i+1] && is[i]==is[i+2]){
                is[i]=0;
                is[i+1]=0;
                is[i+2]=0;
            }

        }

    }
    public static String demandeMvtOuFin (String message){
        Scanner clavier= new Scanner(System.in);
        System.out.println(message);
        String result= clavier.nextLine();
        while (!result.equals("move")|| !result.equals("end")){
            System.out.println(message);
            result= clavier.nextLine();
        }
        return result;
    }

    public static int siMove (String message){
        System.out.println(message);
        int position=Lecture.lireEntier("Entrez un entier");

        return position ;


    }
    public static void main(String[] args) {
        int[] is=new int[20];
        display(is);
        add2balls(is);
        display(is);
        remove3inline(is);
        boolean continuerJeu=true;

        while (continuerJeu){
            String prochaineAction=demandeMvtOuFin("Quel mouvement voulez vous faire : 'move' ou 'end' ");
            if (prochaineAction.equals("move")){
                int from=siMove("Entrez la position de départ");
                int to=siMove("Entrez la position d'arrivée");
                move(is,from,to);
                add2balls(is);
                display(is);
            }
            else {
              continuerJeu=false;
            }
        }
    }



}


