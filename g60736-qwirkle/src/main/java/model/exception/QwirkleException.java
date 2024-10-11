package model.exception;

import view.View;

/**
 * Throw an QwirkleException
 */

public class QwirkleException extends RuntimeException{

    /**
     * Constructs an QwirkleException with the specified detail message.
     * @param message the specified detail message
     */

    public  QwirkleException(String message){
        super(message);
    }


}
