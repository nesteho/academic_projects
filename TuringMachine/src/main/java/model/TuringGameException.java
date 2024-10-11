package model;

/**
 * Represents an Exception thrown during the game
 */
public class TuringGameException extends Exception{
    /**
     * Constructs an TuringGameException instance
     * @param errorMessage the error message
     */
    public TuringGameException(String errorMessage) {
        super(errorMessage);
    }
}
