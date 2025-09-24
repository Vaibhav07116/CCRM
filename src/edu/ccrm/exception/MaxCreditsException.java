package edu.ccrm.exception;

/**
 * A custom checked exception for handling violations of business rules,
 * specifically when a student attempts to exceed the maximum allowed credits
 * per semester.
 */
public class MaxCreditsException extends Exception {

    /**
     * Constructor that takes a custom error message.
     * @param message The detailed error message.
     */
    public MaxCreditsException(String message) {
        // Pass the message up to the parent Exception class constructor.
        super(message);
    }
}
