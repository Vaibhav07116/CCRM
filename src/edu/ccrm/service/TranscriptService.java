package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.util.List;


public class TranscriptService {

    /**
     * Generates a formatted string representing a student's academic transcript.
     *
     * @param student The student for whom to generate the transcript.
     * @return A formatted string containing the student's profile and course records.
     */
    public String generateTranscript(Student student) {
        
        StringBuilder sb = new StringBuilder();

        sb.append("\n========================================\n");
        sb.append("      ACADEMIC TRANSCRIPT\n");
        sb.append("========================================\n");
        sb.append(student.getProfile()); 
        sb.append("\n----------------------------------------\n");
        sb.append("Enrolled Courses:\n");

        List<Enrollment> enrollments = student.getEnrollments();

        if (enrollments.isEmpty()) {
            sb.append("  No courses enrolled.\n");
        } else {
            for (Enrollment enrollment : enrollments) {
                // Nicely format each course and its grade.
                String gradeStr = (enrollment.getGrade() != null) ? enrollment.getGrade().name() : "Not Graded";
                sb.append(String.format("  - %-10s | Grade: %s\n", enrollment.getCourseCode(), gradeStr));
            }
        }

        double gpa = calculateGpa(student);
        sb.append("----------------------------------------\n");
        sb.append(String.format("Cumulative GPA: %.2f\n", gpa)); 
        sb.append("========================================\n");

        return sb.toString();
    }

    /**
     * Calculates the Grade Point Average (GPA) for a student based on their graded courses.
     * This method demonstrates the use of the Java Stream API for aggregation.
     *
     * @param student The student whose GPA is to be calculated.
     * @return The calculated GPA as a double.
     */
    private double calculateGpa(Student student) {
       
        return student.getEnrollments().stream()
                .filter(enrollment -> enrollment.getGrade() != null)
                .mapToDouble(enrollment -> enrollment.getGrade().getGradePoint())
                .average()
                .orElse(0.0);
    }
}