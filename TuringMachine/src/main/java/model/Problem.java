package model;

import model.validator.Validator;

import java.util.Arrays;
/**
 * Represents a problem in the Turing Machine game
 */
public class Problem {
    private final int num;
    private final int difficulty;
    private final int luck;
    private final int code;
    private final int[] validatorsId;
    private Validator[] validators;
    /**
     * Constructs a Problem object with the specified parameters
     * @param num         The number of the problem
     * @param difficulty  The difficulty level of the problem
     * @param luck        The luck factor of the problem
     * @param code        The secret code associated with the problem
     * @param vI          The array of validator index for the problem
     * @throws TuringGameException If an error occurs during the creation of validators
     */
    public Problem(int num, int difficulty, int luck, int code, int[] vI) throws TuringGameException {
        this.num = num;
        this.difficulty = difficulty;
        this.luck = luck;
        this.code = code;
        this.validatorsId = vI;
        validators = new Validator[validatorsId.length];
        initializeValidators();
    }

    /**
     * Returns a string representation of the Problem object
     * @return A string representation of the Problem
     */
    @Override
    public String toString() {
        return "Problem{ " +
                "num = " + num +
                ", difficulty = " + difficulty +
                ", luck = " + luck +
                ", validators = " + Arrays.toString(validatorsId) +
                '}';
    }
    /**
     * Gets the secret code associated with the problem
     * @return The secret code
     */
    public int getCode() {
        return code;
    }
    /**
     * Gets the number of validators associated with the problem.
     * @return The number of validators.
     */
    public int nbValidators() {
        return validators.length;
    }
    /**
     * Initializes the array of validators for the problem
     * @throws TuringGameException If an error occurs during the creation of validators
     */
    public void  initializeValidators() throws TuringGameException {
        var factory = new ValidatorFactory();
        for (int i = 0; i < validatorsId.length; i++) {
            this.validators[i] = factory.createValidator(code, validatorsId[i]);
        }
    }
    /**
     * Checks if the given validator index is valid
     * @param valId The validator index to check
     * @throws TuringGameException If the validator index is invalid
     */
    private void checkValIndex(int valId) throws TuringGameException {
        if (valId < 0 || valId >= nbValidators() ){
            throw new TuringGameException("Problem "+num+" does not contain validator at the index "+valId);
        }
    }
    /**
     * Gets the validator at the specified index
     * @param valId The index of the validator
     * @return The Validator object at the specified index
     * @throws TuringGameException If the validator index is invalid
     */
    public Validator getValidator(int valId) throws TuringGameException {
        checkValIndex(valId);
        return validators[valId];
    }
    /**
     * Gets the validator id at the specified index
     * @param index The index of the validator index
     * @return The validator id at the specified index
     */
    public int getValidatorNum(int index) throws TuringGameException {
        checkValIndex(index);
        return validatorsId[index];
    }
}
