package controller;

import model.AsciiPaint;
import vue.AsciiVue;
import java.util.Scanner;

public class Application {
    // todo ! classe Application trop longue pas optimise -> Créer les commande à part via
    // ici creer command : appeler constructeur du bon  concreteCommand au bon moment
    // la commande sera ajoutée à liste de l'invoker  et controleur (Application) n'est pas l'invoker

    private  static Scanner clavier = new Scanner(System.in);;
    private final AsciiPaint asc;
    /**
     * Constructor of a facade of the game
     */
    public Application(){
        this.asc = new AsciiPaint();
    }

    /**
     * Constructor of a facade of the game
     * @param height  the drawing's height
     * @param width  the drawing's height
     */
    public Application( int height,  int width){
         this.asc = new AsciiPaint(height,width);
     }

    public static void main(String[] args) {
        Application app; // 1 objet application est lié et a accès à un objet ASP

        AsciiVue.displayWelcome();
        askIfNeedHelp();

        String askForDim = "Would you like to create an illustration with specific dimensions?" +
                " If yes, enter 'yes'";
        if (wantSpecificDim(askForDim)) {
            int height = lireEntier("Enter the height of the illustration");
            int width = lireEntier("Enter the width of the illustration");
            AsciiVue.displayDimensions(height,width);
           app = new Application(height,width);
        }
        else {
            app = new Application();
        }

        boolean commandNotOK = true;
        while (commandNotOK){
            try {
                app.start();
                commandNotOK = false;
            }
            catch (Exception e){
                AsciiVue.displayError( e.getMessage() ) ;
            }
        }
    }

    /**
     * Loop of the Game
     */
   public void start (){
        boolean timeToStop= false;
        while (!timeToStop) {
           // controlleur donne à vue ce qu'elle doit afficher   // meth -> de la Vue sont static

           String play = checkCommand("Entrez la commande de la manipulation que vous souhaitez faire");
           String[] cSplit = play.split("\\s+");
           String c = cSplit[0];

           if (c.equals("add")) {
               String shapeInstance = cSplit[1];
               switch (shapeInstance){
                   case  "circle" :
                       asc.newCircle(convertTheXCoordinate(cSplit),
                               convertTheYCoordinate(cSplit),
                               Integer.parseInt(cSplit[4]),
                               cSplit[5].charAt(0));
                       break;
                   case "rectangle" :
                       asc.newRectangle(convertTheXCoordinate(cSplit),
                               convertTheYCoordinate(cSplit),
                               Integer.parseInt(cSplit[4]),
                               Integer.parseInt(cSplit[5]),
                               cSplit[6].charAt(0));
                       break;
                   case "line" :
                       asc.newLine(convertTheXCoordinate(cSplit),
                               convertTheYCoordinate(cSplit),
                               Integer.parseInt(cSplit[4]),
                               Integer.parseInt(cSplit[5]),cSplit[6].charAt(0));
                       break;
                   default:
                       asc.newSquare(convertTheXCoordinate(cSplit),
                               convertTheYCoordinate(cSplit),
                               Integer.parseInt(cSplit[4]),
                               cSplit[5].charAt(0));
               }
           } else {
               switch (c) {
                   case "exit":
                       System.exit(0);
                       break;
                   case "delete":
                       asc.deleteShape(Integer.parseInt(cSplit[1]));
                       break;
                   case "show":  // grace a vue : pas besoin de créer Concretecommandes help/show/list
                       AsciiVue.showTheDrawing(asc.asAscii());
                       break;
                   case "list":
                       AsciiVue.displayShapeList(asc.setListToString());
                       break;
                   case "color":
                       asc.setShapeColor(convertShapeIndex(cSplit), cSplit[2].charAt(0));
                       break;
                   case "move":
                       asc.moveShape(convertShapeIndex(cSplit),
                               convertTheXCoordinate(cSplit),
                               convertTheYCoordinate(cSplit));
                       break;
                   case "group" :
                       var indexes = new int[cSplit.length-1];
                       for(int i = 0;i < indexes.length;i++)
                       {
                           indexes[i] = Integer.parseInt(cSplit[i+1]);
                       }
                       asc.groupShapes(indexes);
                       break;
                   case "ungroup":
                       asc.unGroupShapes(convertShapeIndex(cSplit));
                       break;
                   case "undo":
                       asc.undo();
                       break;
                   default:
                       asc.redo();
               }
           }
          timeToStop = wantToStop();
       }
    }
    private static int convertShapeIndex (String[] cSplit){
        return Integer.parseInt(cSplit[1]);
    }
    private static int convertTheXCoordinate (String[] cSplit){
       return Integer.parseInt(cSplit[2]);
    }
    private static int convertTheYCoordinate (String[] cSplit){
       return Integer.parseInt(cSplit[3]);
    }
    private static boolean  wantSpecificDim(String message){
        System.out.println(message);
        String help = clavier.nextLine();
        return  help.equals("oui");
    }
    private static void askIfNeedHelp(){
        AsciiVue.displayMessage("  Do you want to display the commands of the game ? If yes, enter 'yes' ");
        String help = clavier.nextLine();
        if (help.equals("yes")){
            AsciiVue.displayHelp();
        }
    }
    private static boolean wantToStop(){
        AsciiVue.displayMessage(" Do you want to stop? If yes, enter 'yes'  ");
        String wantToStop = clavier.nextLine();
        return  wantToStop.equals("yes");
    }

    private static String checkCommand (String message){
        String command;

        do {
            System.out.println(message);
            command =  clavier.nextLine();

        } while( !command.matches("list\\s*")
                &&  !command.matches("show\\s*")
                &&  !command.matches("delete\\s+\\d+\\s*")
                &&  !command.matches("add\\s+circle\\s+\\d+\\s+\\d+\\s+\\d\\s+.\\s*")
                &&  !command.matches("color\\s+\\d+\\s+.\\s*")
                &&  !command.matches("move\\s+\\d+\\s+-?\\d+\\s+-?\\d+\\s*")
                &&  !command.matches("add\\s+rectangle\\s+\\d+\\s+\\d+\\s+\\d\\s+\\d+\\s+.\\s*")
                &&  !command.matches("add\\s+square\\s+\\d+\\s+\\d+\\s+\\d\\s+.\\s*")
                &&  !command.matches("add\\s+line\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+.\\s*")
                &&  !command.matches("ungroup\\s+\\d+\\s*")
                &&  !command.matches("group\\s+\\d+(\\s+\\d+)*\\s*")
                &&  !command.matches("undo\\s*")
                &&  !command.matches("redo\\s*")
                &&  !command.matches("exit\\s*")
        );
        return command;
    }
    private static int lireEntier(String message){
        System.out.println(message);
        while (!clavier.hasNextInt()){
            clavier.nextLine();  // supp tt la ligne lue >< clavier.next qui, qui va jeter que  1er 'mot'/'token'
                                 // (pose pb si au lieu hauteur on entre une commande add par ex)
            System.out.println("The provided input is not an non-decimal number .");
            System.out.println(message);
        }
        return clavier.nextInt();
    }
}
