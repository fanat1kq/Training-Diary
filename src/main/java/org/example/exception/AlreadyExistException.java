package org.example.exception;

/**
 * The type Type training already exists exception.
 */
public class AlreadyExistException extends RuntimeException {
    /**
     * Instantiates a new Type training already exists exception.
     *
     * @param message the message
     */
    public AlreadyExistException(String message) {
        super(message);
    }
}
