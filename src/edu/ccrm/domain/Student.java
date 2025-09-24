package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Student in the system.
 * Inherits common properties from the abstract Person class.
 */
public class Student extends Person {

    /**
     * Enum for student status, defined inside the Student class.
     * This is an example of a nested type.
     */
    public enum Status {
        ACTIVE,
        INACTIVE,
        GRADUATED
    }

    // Private fields demonstrate Encapsulation
    private final String registrationNumber;
    private Status status;
    private final LocalDate enrollmentDate;
    private final List<Enrollment> enrollments;

    /**
     * Constructor for creating a new Student.
     * It calls the parent Person constructor using 'super'.
     */
    public Student(int id, String fullName, String email, String registrationNumber, LocalDate enrollmentDate) {
        super(id, fullName, email); // Call to the parent class constructor
        this.registrationNumber = registrationNumber;
        this.status = Status.ACTIVE; // Default to ACTIVE on creation
        this.enrollmentDate = enrollmentDate;
        this.enrollments = new ArrayList<>();
    }

    // --- Standard Getters ---

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    /**
     * Overridden method from the Person class to provide a specific profile for a Student.
     * This is a key demonstration of Polymorphism.
     */
    @Override
    public String getProfile() {
        return String.format("STUDENT | ID: %d | Name: %s | Reg No: %s | Status: %s",
                getId(), getFullName(), registrationNumber, status);
    }
}
