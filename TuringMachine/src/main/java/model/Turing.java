package model;

import model.dpcommand.*;
import model.dpoo.Observer;
import model.validator.Validator;
import java.util.Random;
/**
 * Represents the Facade in The Facade DP
 */
public class Turing  {
    // creer la command au niveau de la facade ->   // creer un invoker attr
    // Turing est client, dmd a Invoker d'executer commande, INVOKER execute commande
    private final Invoker invoker;// Turing (la Facade ) est invoker . Receiver = Partie
    private final Problems problems;
    Play play ;
    /**
     * Constructs a new Turing Machine game instance
     * @throws TuringGameException If an error occurs during the initialization of the game
     */
    public Turing() throws TuringGameException {
        this.problems = new Problems() ;
        this.invoker = new Invoker();
        play = null;
    }
    /**
     * Starts a new game with the specified problem id.
     * @param problemId The index of the problem to start the game with
     * @throws TuringGameException If the index is invalid
     */
    public void startGame(int problemId) throws TuringGameException {
        Problem problem;
        if(problemId == -1){
            Random random = new Random();
            int chanceId = random.nextInt(problems.nbProblems()) + 1;  // nb entre 1 et 15 inclus
            problem = problems.getProblem(chanceId);
        } else {
            problem = problems.getProblem(problemId);
        }
        play = new Play(problem);
    }
    /**
     * Gets a string representation of the list of problems
     * @return A string representation the list of problems
     */
    public String problemsList() {
        return problems.toString();
    }
    /**
     * Gets the number of problems in the game
     * @return The number of problems in the game
     */
    public int nbProblems (){
        return problems.nbProblems();
    }
    /**
     * Gets the data of the current problem in the game
     * @return The data of the current problem
     */
    public String problemData() {
        return this.play.problemData();
    }
    /**
     * Gets the current score in the game
     * @return The current score
     */
    public int getScore() { return play.getScore();}
    /**
     * Gets the number of rounds played in the current game
     * @return The number of rounds played
     */
    public int getNbRound() {return play.getNbRound();}
    /**
     * Makes a new round
     * @param userCode The entered user code
     * @throws TuringGameException If the code is invalid
     */
    public void enterCode (int userCode) throws TuringGameException {
        checkCode(userCode);
        var command = new EnterCodeCommand(play, userCode);
        invoker.execute(command);
    } // UTILISATION DP COMMAND : chq action de l'utilisateur est une commande
    // UTILITE DP COMMAND :  déplacer l'action dans un objet, et on peut faire undo redo via history
    // une commande concrete da tt ce qu'il faut pr executer une commande
    /**
     * Tests a validator of the game
     * @param index The index of the validator to test
     * @throws TuringGameException If an error occurs during the validation process
     */
    public void validatorResults(int index) throws TuringGameException {
        var command = new TestValidatorCommand(play,index);
        invoker.execute(command);
    }
    /**
     * Compares user code to the secret
     * @throws TuringGameException If guessing the code is not allowed
     */
    public void guessCode() throws TuringGameException {
        var command = new GuessCodeCommand(play);
        invoker.execute(command);
    }
    /**
     * Moves to the next round in the game
     * @throws TuringGameException If moving to the next round is not allowed
     */
    public void nextRound() throws TuringGameException {
        var command = new NextRoundCommand(play);
        invoker.execute(command);
    }
    /**
     * Registers an observer for the play
     * @param obs The observer to be registered
     */
    public  void  register (Observer obs) {
       play.registerObserver(obs);
    }
    /**
     * Undoes the last command
     *  @throws TuringGameException If undoing is not allowed
     */
    public void undo() throws TuringGameException {
        invoker.undo();
    }

    /**
     * Redoes the last undone command
     * @throws TuringGameException If redoing is not allowed
     */
    public void redo() throws TuringGameException {
        invoker.redo();
    }
    private void checkCode(int userCode) throws TuringGameException {
        String code = (""+userCode);
        if( code.length()!= 3 ){
            throw new TuringGameException(" User code must have exactly three figures !");
        }
        for (int i = 0; i < code.length() ; i++) {
            int figure = Integer.parseInt(""+code.charAt(i));
            if( figure< 1 || figure > 5 ){
                throw new TuringGameException("User code must have figures only between 1 and 5");
            }
        }
    }
    /**
     * Displays a string representation of the problem
     * @param i The index of the problem to display
     * @return A string representation of the problem
     * @throws TuringGameException If invalid index
     */
    public String showProblem (int i) throws TuringGameException {
       return problems.displayProblem(i);
    }
    /**
     * Gets the validator id at the specified index
     * @param index The index of the validator to get
     * @return The id of the validator
     * @throws TuringGameException If invalid index
     */
    public int getValidatorId(int index) throws TuringGameException {
        return play.getValidatorId(index);
    }
    /**
     * Gets the validator at the specified index in the game
     * @param i The index of the validator
     * @return The validator at the specified index
     * @throws TuringGameException If invalid index
     */
    public Validator getValidator(int i) throws TuringGameException {
        return play.getValidator(i);
    }

    /**
     * Gets the number of validators of the played problem
     * @return The number of validators of the played problem
     */
    public int getValidatorsNb(){
        return play.getValidatorsNb();
    }
}
