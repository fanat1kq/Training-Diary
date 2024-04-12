package org.example.exception;

/**
 * The type Type training already exists exception.
 */
public class TypeTrainingNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Type training already exists exception.
     *
     * @param message the message
     */
    public TypeTrainingNotFoundException(String message) {
        super(message);
    }
}
