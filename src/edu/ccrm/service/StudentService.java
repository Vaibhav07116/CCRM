package edu.ccrm.service;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Student;
import java.time.LocalDate;
import java.util.List;

/**
 * Handles all business logic related to students.
 * This service class is responsible for creating, finding, and retrieving student data.
 */
public class StudentService implements Searchable<Student, Integer> {
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    public Student findById(Integer id) {
        // Find a student by their ID. Returns null if not found.
        return dataStore.getStudents().stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAllStudents() {
        return dataStore.getStudents();
    }

    /**
     * Creates a new Student and adds them to the data store.
     * This is the full implementation of the method.
     *
     * @param fullName           The full name of the student.
     * @param email              The email of the student.
     * @param registrationNumber The student's registration number.
     * @return The newly created Student object.
     */
    public Student addStudent(String fullName, String email, String registrationNumber) {
        // 1. Get the next unique ID from the data store.
        int studentId = dataStore.getNextStudentId();

        // 2. Create a new Student object with the current date.
        Student newStudent = new Student(studentId, fullName, email, registrationNumber, LocalDate.now());

        // 3. Add the new student to our central list of students.
        dataStore.getStudents().add(newStudent);

        // 4. Return the new student so the caller can confirm success.
        return newStudent;
    }
}

