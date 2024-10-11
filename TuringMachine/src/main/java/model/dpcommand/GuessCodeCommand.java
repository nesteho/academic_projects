package model.dpcommand;

import model.Play;
import model.TuringGameException;
/**
 * The Command to guess the code
 */
public class GuessCodeCommand implements Command{
    private Play play;
    /**
     * Constructs a new GuessCodeCommand instance
     * @param p The current play
     */
    public GuessCodeCommand(Play p) {
        this.play = p;
    }
    /**
     * Executes the command to move to the next round
     * @throws TuringGameException If guessing the code is not allowed
     */
    @Override
    public void execute() throws TuringGameException {
        play.guessCode();    // appel a Receiver
    }
    @Override
    public void unExecute() {
        // nothing to do ... guess the code -> end of the game
    }
}
