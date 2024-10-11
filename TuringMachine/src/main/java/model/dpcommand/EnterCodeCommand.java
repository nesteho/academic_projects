package model.dpcommand;

import model.Play;
import model.TuringGameException;
/**
 * The Command to enter a new Code
 */
public class EnterCodeCommand implements Command{
    private Play play;
    private int userCode;
    /**
     * Constructs a new EnterCodeCommand instance
     * @param play The curent play
     * @param userCode The user code to be entered
     */
    public EnterCodeCommand(Play play, int userCode) {
        this.play = play;
        this.userCode = userCode;
    }
    /**
     * Executes the command to enter a new code
     * @throws TuringGameException If an error occurs during the execution of the command
     */
    @Override
    public void execute() throws TuringGameException {
        play.enterCode(userCode);
    }
    /**
     * UnExecutes the entering a code command
     * @throws TuringGameException If an error occurs during the undo operation
     */
    @Override
    public void unExecute() throws TuringGameException {
        play.unEnterCode();
    }
}
