package edu.ccrm.domain;

import java.time.LocalDate;

/**
 * Represents the enrollment of a single Student in a single Course.
 * This class acts as a link between the Student and Course entities,
 * and it also stores the grade and enrollment date. This is a classic
 * example of an association class in object-oriented design.
 */
public class Enrollment {

    private final int studentId;
    private final String courseCode;
    private Grade grade; // Grade is null by default for new objects
    private final LocalDate enrollmentDate;

    /**
     * Constructor for a new enrollment.
     * The grade is not included here because it's typically assigned later.
     *
     * @param studentId  The ID of the student enrolling.
     * @param courseCode The code of the course they are enrolling in.
     */
    public Enrollment(int studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.enrollmentDate = LocalDate.now(); // Sets the enrollment date to the current date
        // No need to set this.grade = null; it's the default value.
    }

    // Standard getters for all fields

    public int getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Grade getGrade() {
        return grade;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    // A setter for the grade, as this will be updated later
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        // A simple string representation of the enrollment
        return String.format("Enrollment[StudentId=%d, CourseCode=%s, Grade=%s]",
                studentId, courseCode, grade != null ? grade : "Not Graded");
    }
}
