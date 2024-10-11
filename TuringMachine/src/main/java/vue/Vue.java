package vue;

import model.dpoo.Observer;

/**
 * Represents the console vue in the MVC DP
 */
public class Vue implements Observer {

    public static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\033[0;33m";
    private static final String ANSI_RESET = "\u001B[0m";
    /**
     * Displays the welcome message
     */
    public  void displayWelcome() {
        System.out.println("***** Welcome to Turing Machine! *****");
    }
    /**
     * Displays the list of problems
     * @param message The list of problems
     */
    public void displayProblems(String message) {
        System.out.println(" Here is the list of problems you can choose to play");
        System.out.println(message);
    }
    /**
     * Displays the given problem
     * @param problem The problem to display
     */
    public  void displayTheProblem(String problem) {
        System.out.println(problem);
    }
    /**
     * Displays the current score
     * @param score The current score to display
     */
    public  void displayScore(int score) {
        System.out.println(  ANSI_BLUE + "Score : " + score + ANSI_RESET);
    }
    /**
     * Displays the final score
     * @param rounds The number of rounds played
     * @param score The final score
     */
    public  void displayFinalScore(int rounds,int score) {
        System.out.println(  ANSI_YELLOW + " Final Score : " + rounds*score + ANSI_RESET);
    }
    /**
     * Displays the current round
     * @param round The current round to display
     */
    public  void displayRound(int round) {
        System.out.println(  ANSI_BLUE + "Round : " + round + ANSI_RESET);
    }
    /**
     * Displays the results of testing validators
     * @param args Arguments containing the results of testing validators
     */
    public  void displayValidatorsResults(Object... args) {
        var userCode = (int) args[1];
        var index = (int)args[2] ;
        var result = (boolean)args[3];
        System.out.println("The result for validator at index " + index +
                    " with the code " + userCode + " is "+result+ " }");
    }

    /**
     * Updates the view
     * @param args The arguments containing the updates
     */
    @Override
    public void update(Object... args) {
        if (args.length == 2 && args[1] instanceof String){
            System.out.println(args[1]);
        }
        if (args.length == 4 ){

           displayValidatorsResults(args);
        }
    }
    /**
     * Display the given message
     */
    public void displayMessage(String message){
        System.out.println(message);
    }

    /**
     * Displays the commands
     */
    public void displayHelp(){
        System.out.println(
                "commands:\n" +
                        " - enter a new code : enter code\n" +
                        " - test validators : test <v1>\n" +
                        " - guess the code : guess code\n" +
                        " -undo a command: undo\n"+
                        " -redo a command: redo\n"+
                        "- next round : next\n"+
                        " -give up the game : exit\n"+
                        "code : number of 3 figures, each figure is between 1 and 5\n" +
                        "<v1> is a validator index between 0 and the number of validators of the problem\n"+
                        "at most 3 validators tested by round\n"
        );
    }
    /**
     * Displays the error message
     */
    public static void displayError(String message){
        System.out.println(ANSI_RED +message+ANSI_RESET );
    }
}
