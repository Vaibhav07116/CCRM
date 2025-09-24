package edu.ccrm.exception;

/**
 * A custom checked exception for handling errors related to the student
 * enrollment process. Using a custom exception makes the code more readable
 * and allows for specific catch blocks.
 */
public class EnrollmentException extends Exception {

    /**
     * Constructor that takes a custom error message.
     * @param message The detailed error message.
     */
    public EnrollmentException(String message) {
        // Pass the message up to the parent Exception class constructor.
        super(message);
    }
}
