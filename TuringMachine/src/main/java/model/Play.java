package model;

import model.validator.Validator;
import model.dpoo.Observable;
import model.dpoo.Observer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  Represents a Play of the Turing Machine game
 */
public class Play implements Observable {
    // play est receiver et observé
    Set<Observer> observers;
    private final Problem problem;
    List<Round> rounds;
    Round curRound;
    private int score;
    private boolean isOver;

    /**
     *Constructs a play
     * @param problem
     * @throws TuringGameException
     */
    public Play(Problem problem) throws TuringGameException {
        if(problem == null){
            throw new TuringGameException("No provided problem");
        }
        this.problem = problem;
        observers = new HashSet<>();
        rounds = new ArrayList<>();
        score = 0;
        curRound = null;
        isOver = false;
    }
    // ds mm manche : les result sont stocke | entre deux manche tt est remis à 0

    /**
     * Compare the userCode to the secret
     * @throws TuringGameException not in a current round
     */
    public void guessCode() throws TuringGameException {
        if(curRound == null ){
            throw new TuringGameException("You have to enter a code before guessing it");
        }
        if( this.problem.getCode() == curRound.getUserCode()){
           notifyObservers(PropertyName.HAS_WON_THE_GAME);

        }else {
           notifyObservers(PropertyName.HAS_LOST_THE_GAME);
        }
        isOver = true;
    }

    /**
     * Create A new Round
     * @param userCode the code for the new round
     * @throws TuringGameException if still in a round
     */
    public void enterCode ( int userCode ) throws TuringGameException {
       if(curRound != null ){
           throw new TuringGameException("You cannot enter a new code within the same round!");
       }
        curRound = new Round(userCode);
        rounds.add(curRound);
        // pas besoin de notifier un enter code classique mais un redo oui
        notifyObservers(PropertyName.REDO_ENTER_A_CODE, userCode);
    }
    public void unEnterCode() throws TuringGameException {
        if(curRound == null ){
            throw new TuringGameException("You did not enter a code");
        }
        rounds.remove(curRound);
        curRound = null;
        notifyObservers(PropertyName.UN_ENTER_A_CODE);
    }

    /**
     * Tests the validator at the given index
     * @param index the given index
     * @throws TuringGameException if invalid index
     */
    public void testValidator(int index ) throws TuringGameException {
        if(curRound == null ){
            throw new TuringGameException("You cannot test a validators without entering a code before!");
        }
        var validator= problem.getValidator(index);
        curRound.validatorResult(validator); // resp de round : tester validateur
        score++;
        notifyObservers(PropertyName.VALIDATOR_RESULT, curRound.getUserCode(), index, validator.ValidatorResult());
    }// resp de Play de notifier un chg d'état  | validator : resp de dire si a ete visite ou pas

    /**
     * Registers a new observer
     * @param obs The observer to register
     */
    @Override
    public void registerObserver(Observer obs) {observers.add(obs);}
    /**
     * Removes a new observer
     * @param obs The observer to remove
     */
    @Override
    public void removeObserver(Observer obs) { observers.remove(obs); }
    /**
     * Notifies all the observers about a change
     */
    @Override
    public void notifyObservers(PropertyName propertyChange, Object... args) {
        // model donne signal "j'ai change" à vue + donne tt les info du chgt
        switch (propertyChange){
            case HAS_WON_THE_GAME ->
                    observers.forEach(o -> o.update(propertyChange,"Congratulation! You won!") );
            case HAS_LOST_THE_GAME ->
                    observers.forEach(o -> o.update(propertyChange, "Game over! Your code does not match the secret code") );
            case VALIDATOR_RESULT ->
                    observers.forEach(o -> o.update(propertyChange,  args[0], args[1], args[2]) );
            case UNDO_A_VALIDATOR_TEST, REDO_ENTER_A_CODE->  observers.forEach(o -> o.update(propertyChange, args[0]) );
            case  NEXT_ROUND,UN_ENTER_A_CODE,UNDO, REDO ->
                    observers.forEach(o -> o.update(propertyChange ) );

        }
    }     // Object ... -> car le nb de param dep du type de notification :

    /**
     * Goes to the next round
     * @throws TuringGameException If an error occurs during the operation
     */
    public  void nextRound() throws TuringGameException {
        for (int i = 0; i < problem.nbValidators(); i++) {
            var validator = problem.getValidator(i);
            if(validator.isTested()){
                validator.setTested();
                validator.setResult();
            }
        }
        curRound = null;
        notifyObservers(PropertyName.NEXT_ROUND);
    }
    /**
     * Get the current round
     * @return The current round
     */
    public Round getCurRound() {
        return curRound;
    }

    /**
     * Set the game at the previous round
     */
    public void previousRound(){
        curRound = rounds.get(rounds.size() -1); // still in the round
        notifyObservers(PropertyName.UNDO_A_NEXT_ROUND);
    }
    /**
     * Gets information about the current problem.
     * @return A string representation of the problem
     */
    public String problemData() {return problem.toString(); }
    /**
     * Gets the current score in the game.
     * @return The current score
     */
    public int getScore() {return score; }
    /**
     * Gets the number of rounds played in the game.
     * @return The number of rounds
     */
    public int getNbRound() {return rounds.size(); }
    /**
     * Undoes the testing of a validator in the current round.
     * @param valIndex The index of the validator to undo testing
     * @throws TuringGameException If the index is invalid
     */
    public void unTestValidator(int valIndex) throws TuringGameException {
        var validator = problem.getValidator(valIndex);
        validator.setTested();
        validator.setResult();
        score--;
        curRound.setTestedValNb(-1);
        notifyObservers(PropertyName.UNDO_A_VALIDATOR_TEST,valIndex);
    }

    /**
     * Gets the validator ID at the specified index
     * @param index The index of the validator
     * @return The index of the validator
     * @throws TuringGameException If the index is invalid
     */
    public int getValidatorId(int index) throws TuringGameException {
        return problem.getValidatorNum(index);
    }
    /**
     * Gets the validator at the specified index
     * @param i The index of the validator
     * @return The validator instance
     * @throws TuringGameException If the index is invalid
     */
    public Validator getValidator(int i) throws TuringGameException {
        return problem.getValidator(i);
    }
    /**
     * Gets the number of validators in the current problem
     * @return The number of validators
     */
    public int getValidatorsNb(){
        return problem.nbValidators();
    }
}
