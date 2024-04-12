package org.example.exception;

/**
 * The type Transaction already exists exception.
 */
public class TypeTrainingNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Transaction already exists exception.
     *
     * @param message the message
     */
    public TypeTrainingNotFoundException(String message) {
        super(message);
    }
}
