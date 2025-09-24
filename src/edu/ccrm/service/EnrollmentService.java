package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.EnrollmentException;
import edu.ccrm.exception.MaxCreditsException;
import java.util.Optional;

public class EnrollmentService {

    private static final int MAX_CREDITS_PER_SEMESTER = 18;

    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();

    public void enrollStudent(int studentId, String courseCode) throws EnrollmentException, MaxCreditsException {
        // Now this receives a real Student object, not a box!
        Student student = studentService.findById(studentId);
        if (student == null) {
            throw new EnrollmentException("Student with ID " + studentId + " not found.");
        }

        Course courseToEnroll = courseService.findById(courseCode);
        if (courseToEnroll == null) {
            throw new EnrollmentException("Course with code " + courseCode + " not found.");
        }

        // This line will now work perfectly!
        boolean isAlreadyEnrolled = student.getEnrollments().stream()
                .anyMatch(e -> e.getCourseCode().equalsIgnoreCase(courseCode));
        if (isAlreadyEnrolled) {
            throw new EnrollmentException("Student is already enrolled in course " + courseCode);
        }

        int currentCredits = 0;
        for (Enrollment enrollment : student.getEnrollments()) {
            Course enrolledCourse = courseService.findById(enrollment.getCourseCode());
            if (enrolledCourse != null && enrolledCourse.getSemester() == courseToEnroll.getSemester()) {
                currentCredits += enrolledCourse.getCredits();
            }
        }

        if (currentCredits + courseToEnroll.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditsException("Enrollment failed: Exceeds max credit limit of "
                    + MAX_CREDITS_PER_SEMESTER + " for the semester.");
        }

        Enrollment newEnrollment = new Enrollment(studentId, courseCode);
        student.getEnrollments().add(newEnrollment);
    }

    public void assignGrade(int studentId, String courseCode, Grade grade) throws EnrollmentException {
        Student student = studentService.findById(studentId);
        if (student == null) {
            throw new EnrollmentException("Student with ID " + studentId + " not found.");
        }

        Optional<Enrollment> enrollmentOpt = student.getEnrollments().stream()
                .filter(e -> e.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst();

        if (enrollmentOpt.isPresent()) {
            enrollmentOpt.get().setGrade(grade);
        } else {
            throw new EnrollmentException("Cannot assign grade: Student is not enrolled in course " + courseCode);
        }
    }
}
