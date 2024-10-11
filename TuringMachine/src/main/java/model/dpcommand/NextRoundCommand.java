package model.dpcommand;

import model.Play;
import model.TuringGameException;
/**
 * The Command to move to the next Round
 */
public class NextRoundCommand implements Command {
    private Play play;
    /**
     * Constructs a new NextRoundCommand instance
     * @param play The current play
     * @throws TuringGameException If an error occurs during the command initialization
     */
    public NextRoundCommand(Play play) throws TuringGameException {
        if( play.getCurRound() == null){
            throw new TuringGameException("You must be in a round to move to the next one!");
        }
        if( play.getCurRound().getTestedValNb() == 0){
            throw new TuringGameException("You must test a validtor before do next round!");
        }
        this.play = play;
    }
    /**
     * Executes the command to move to the next round
     * @throws TuringGameException If an error occurs during the execution of the command
     */
    @Override
    public void execute() throws TuringGameException {
        play.nextRound();
    }
    /**
     * Undoes the execution of the command by moving to the previous round
     */
    @Override
    public void unExecute() {
        play.previousRound();
    }
}
