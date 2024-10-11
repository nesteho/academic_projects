package model.dpcommand;

import model.Play;
import model.TuringGameException;
/**
 * The Command to test a validator
 */
public class TestValidatorCommand implements Command{
    private int validatorIndex;
    private Play play;
    /**
     * Constructs a new TestValidatorCommand instance
     * @param p The current play
     * @param valIndex The index of the validator to test
     */
    public TestValidatorCommand(Play p, int valIndex) {
        this.validatorIndex = valIndex;
        play = p;
    }

    /**
     * Executes the command to test a validator
     * @throws TuringGameException If an error occurs during the execution of the command
     */
    @Override
    public void execute() throws TuringGameException {
        play.testValidator(validatorIndex);
    }
    /**
     * Undoes the execution of the command by untesting to validator
     * @throws TuringGameException If an error occurs during the execution of the command
     */
    @Override
    public void unExecute() throws TuringGameException {
        play.unTestValidator(validatorIndex);
    }

}
