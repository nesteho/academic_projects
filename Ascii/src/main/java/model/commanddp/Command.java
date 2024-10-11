package model.commanddp;

/**
 * Represent a command
 */
public interface Command {
    void execute();
    void unexecute();
}
