package org.example.exception;

/**
 * The type Authorize exception.
 */
public class NotValidParameterException extends RuntimeException {
    /**
     * Instantiates a new Authorize exception.
     *
     * @param message the message
     */
    public NotValidParameterException(String message) {
        super(message);
    }
}
