package edu.ccrm.domain;

/**
 * Represents an Instructor, extending the Person class.
 * This also demonstrates INHERITANCE.
 */
public class Instructor extends Person {

    private final String department;
    // Example of an instructor-specific field

    public Instructor(int id, String fullName, String email, String department) {
        // Call the parent Person constructor using 'super'
        super(id, fullName, email);
        this.department = department;
    }

    /**
     * Fulfilling the abstract contract from the Person class.
     * The Instructor's profile is different from a Student's profile.
     */
    @Override
    public String getProfile() {
        return String.format("Instructor Profile:\n  ID: %d\n  Name: %s\n  Department: %s",
                getId(), getFullName(), getDepartment());
    }
    
    /**
     * Overriding toString() provides a simple, readable representation of the object,
     * which is useful for debugging and logging.
     */
    @Override
    public String toString() {
        return String.format("Instructor[ID=%d, Name=%s, Dept=%s]",
                getId(), getFullName(), department);
    }

    // Getter for the instructor-specific field
    public String getDepartment() {
        return department;
    }
}
