package edu.ccrm.domain;

/**
 * An abstract base class representing a person in the institution.
 * It provides common properties for Students and Instructors.
 * This demonstrates ABSTRACTION. You cannot create a 'new Person()'.
 */
public abstract class Person {
    private final int id;
    private final String fullName;
    private String email;

    public Person(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public Person(String fullName, int id) {
        this.fullName = fullName;
        this.id = id;
    }
    

    /**
     * An abstract method defines a contract that all subclasses must follow.
     * Both Student and Instructor MUST provide their own version of getProfile().
     * This is a core part of Abstraction.
     */
    public abstract String getProfile();

    // --- Standard Getters ---
    // These methods are inherited by both Student and Instructor automatically.
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
