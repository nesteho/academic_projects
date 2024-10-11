package model;

import model.validator.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a round in the Turing Machine game
 */
public class Round {
    private final int userCode;
    private int testedValNb;
    private List<Validator> testedValidators;

    public List<Validator> getTestedValidators() {
        return testedValidators;
    }

    /**
     * Constructs a new round with the given user code
     * @param userCurrentCode The user code for the round
     */
    public Round( int userCurrentCode) {
        userCode = userCurrentCode;
        testedValNb = 0;
        testedValidators = new ArrayList<>();
    }
    /**
     * Records the result of testing a validator in the round
     * @param validator The validator to test
     * @throws TuringGameException If the maximum number of tested validators is reached,
     */
    public void validatorResult(Validator validator) throws TuringGameException {
        if (testedValNb >= 3){
            throw new TuringGameException("Max three tested validators!");
        }
        if ( validator.isTested() ) {
            throw new TuringGameException("This validator has already been tested in the current round !");
        }
        validator.test(userCode);
        testedValidators.add(validator);
        testedValNb++;
    }
    /**
     * Gets the user code for the round
     * @return The user code
     */
    public int getUserCode() {
        return userCode;
    }
    /**
     * Gets the number of validators tested in the round
     * @return The number of tested validators
     */
    public int getTestedValNb() {
        return testedValNb;
    }
    /**
     * Sets the number of tested validators in the round
     * @param n The number to set
     * @throws TuringGameException If the resulting number of tested validators is negative or exceeds 3
     */
    public void setTestedValNb(int n) throws TuringGameException {
        if(testedValNb + n < 0){
            throw new TuringGameException("Cannot have tested a negative amount of validators");
        }
        if(testedValNb + n > 3){
            throw new TuringGameException("Cannot test more than 3 validators");
        }
        testedValNb += n;
    }
}
