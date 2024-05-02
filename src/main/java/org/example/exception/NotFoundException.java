package org.example.exception;

/**
 * The Type training already exists exception.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Instantiates a new Type training already exists exception.
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(message);
    }
}
