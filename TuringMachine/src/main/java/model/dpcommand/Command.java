package model.dpcommand;

import model.TuringGameException;

/**
 * Represent a command
 */

public interface Command {
    void execute() throws TuringGameException;
    void unExecute() throws TuringGameException;
}
