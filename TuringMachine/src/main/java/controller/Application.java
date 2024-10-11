package controller;

import model.Turing;
import model.TuringGameException;
import vue.Vue;
import java.util.Scanner;
/**
 * Represent the controller of the console view in the MVC DP
 */
public class Application {
    private static Scanner clavier;
    private final Turing  turing;
    private final Vue  vue;
    public Application() throws TuringGameException {
        this.vue =  new Vue();
        this.turing = new Turing();
        //ici  application = controller : instancie la vue : pour pr afficher resultat d'update
    }

    public static void main(String[] args) throws TuringGameException {
        var app = new Application();
        app.vue.displayWelcome();
        app.askIfNeedHelp("Do you want to display the commands" +
                " of the game ? If yes, enter 'yes' ");

        String askForProblem = "Would you like to choose a problem? If yes, enter 'yes'";
        app.askProblemIndex(askForProblem);
        app.turing.register(app.vue);

        boolean hasGuessed  = false;
        while (!hasGuessed){
            hasGuessed = app.playCommand();
        }
    }
    private void askProblemIndex( String askForProblem){
        int problemIndex;
        boolean wrongProblemId = true;
        while (wrongProblemId){
            try {
                problemIndex = problemToPlay(askForProblem);
                turing.startGame(problemIndex);
                vue.displayTheProblem("The problem for party is: "
                        + turing.problemData());
                wrongProblemId = false;
            }
            catch (TuringGameException t){
                vue.displayError(t.getMessage());
                vue.displayMessage("Please enter a valid index for the problem");
            }
        }
    }
    private boolean playCommand (){
        boolean hasGuessed = false;
        boolean wrongCommand = true;
        while (wrongCommand){
            try {
                int round = turing.getNbRound();
                vue.displayRound(round);
                int score = turing.getScore();
                vue.displayScore(score);
                hasGuessed = start();
                wrongCommand = false;
                 // score aps start() -> score aps de la commande
                if(hasGuessed){
                    vue.displayFinalScore(round,score );
                }
            }
            catch (TuringGameException t){
                vue.displayError(t.getMessage());
            }
        }
        return hasGuessed;
    }

    /**
     * Executes one command of the game
     * @return if the play is over or not
     * @throws TuringGameException when the command execution failed
     */
    public boolean start () throws TuringGameException {
        String play = checkCommand("Entrez la commande de la manipulation que vous souhaitez faire");
        String[] cSplit = play.split("\\s+");

        switch(cSplit[0]){
            case  "enter" -> turing.enterCode(Integer.parseInt(cSplit[1]));
            case  "test" ->  turing.validatorResults(Integer.parseInt(cSplit[1] ));
            case "exit" -> System.exit(0);
            case "next" -> turing.nextRound();
            case  "guess" -> {
                turing.guessCode();
                return true;
            }
            case  "undo" -> turing.undo();
            default ->  turing.redo();
        }
        return false;
    }
    private boolean wantToChooseProblem(String askForProblem) {
        String answer = this.lireString(askForProblem);
        return  answer.equals("yes");
    }
    private int problemToPlay(String askForProblem){
        int problemIndex;
        if ( !wantToChooseProblem(askForProblem) ) {
            problemIndex = -1;
        }
        else {
            String wantProblemList = "Would you like to see the list of problems";
            askIfNeedProblems(wantProblemList);
            problemIndex = lireEntier("Give the index of the problem you want to play/solve");
        }
        return problemIndex;
    }
    private  void askIfNeedHelp(String message){
        if(askMessage(message) ){
            vue.displayHelp();
        }
    }
    private  void askIfNeedProblems(String message){
        if(askMessage(message) ){
            vue.displayProblems(turing.problemsList());
        }
    }

    private boolean askMessage(String message){
        String answer = lireString(message);
        return answer.equals("yes");
    }
    private  String checkCommand (String message){
        String command;
        do {
            command =  lireString(message);
        }
        while(      !command.matches("enter\\s+\\d+\\s*")
                &&  !command.matches("test\\s+\\d+\\s*")
                &&  !command.matches("guess\\s*")
                &&  !command.matches("undo\\s*")
                &&  !command.matches("redo\\s*")
                &&  !command.matches("exit\\s*")
                &&  !command.matches("next\\s*")
        );
        return command;
    }
    private int lireEntier(String message){

        vue.displayMessage(message);
        while (!clavier.hasNextInt()){
            clavier.next();
            vue.displayMessage("L'entrée saisie n’est pas un entier");
            vue.displayMessage(message);
        }
        return clavier.nextInt();
    }
    private String lireString(String message){
        clavier = new Scanner(System.in);  // pour eviter chaine vide quand on réappelle lireString -> empeche utilisateur entree qqch
        vue.displayMessage(message);

        while (!clavier.hasNextLine()){
            clavier.next();
            vue.displayMessage("L'entrée saisie n’est pas une chaine de caractère.");
            vue.displayMessage(message);
        }
        return clavier.nextLine();
    }
}
