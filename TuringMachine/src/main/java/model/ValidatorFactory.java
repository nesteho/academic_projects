package model;

import model.validator.*;

/**
 * Represents a Factory of validators of the game
 */
public class ValidatorFactory {
    /**
     * Constructs a factory of validators
     */
    public ValidatorFactory(){
    }

    /**
     * Create an Instance of a validator
     * @param secretCode the code to find
     * @param validatorId the index of the validator to instance
     * @throws TuringGameException when wrong given validator num
     * @return an instance of the validator at the given id
     */
    protected Validator createValidator (int secretCode, int validatorId) throws TuringGameException {  // fait partie du model, veux uniquemet accessible à facade (Turing)
        Validator validator;
        validator = switch (validatorId) {
            case 1, 2, 3, 4 -> new CompareFigureValue(validatorId, secretCode);
            case 5, 6, 7 -> new CompareFigureParity(validatorId, secretCode);
            case 8, 9, 10 -> new HowOftenFigure(validatorId, secretCode);
            case 11, 12, 13 -> new CompareTwoFigures(validatorId, secretCode);
            case 14, 15 -> new ExtremumFigure(validatorId, secretCode);
            case 16 -> new MostPresentParity(validatorId, secretCode);
            case 17 -> new EvenFigures(validatorId, secretCode);
            case 18 -> new SumParity(validatorId, secretCode);
            case 19 -> new SumTwoFigures(validatorId, secretCode);
            case 20, 21 -> new RepetitionNumber(validatorId, secretCode);
            case 22 -> new FiguresOrder(validatorId, secretCode);
            default -> throw new TuringGameException("Validator of index " + validatorId +
                    " does not exist in the problem list");
        };
        return validator;
    }
}
