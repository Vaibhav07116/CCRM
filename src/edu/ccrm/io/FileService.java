package edu.ccrm.io;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles all file import and export operations using Java NIO.2 and Streams.
 */
public class FileService {

    private final DataStore dataStore = DataStore.getInstance();

    /**
     * Loads initial data from CSV files in the test-data directory.
     * Uses try-with-resources to ensure files are closed properly.
     */
    public void loadInitialData() {
        System.out.println("Loading initial data from test-data directory...");
        try {
            // NOTE: We need sample instructor data to properly link courses.
            // Ensure you have an instructors.csv file.
            loadInstructors("test-data/instructors.csv");
            loadStudents("test-data/students.csv");
            loadCourses("test-data/courses.csv");
            System.out.println("Initial data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading initial data: " + e.getMessage());
            // In a real app, you might want to exit or handle this more gracefully.
        }
    }

    private void loadInstructors(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) return;

        try (Stream<String> lines = Files.lines(path).skip(1)) { // Skip header line
            List<Instructor> instructors = lines
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length >= 3)
                    .map(parts -> {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        String email = parts[2].trim();
                        String department = parts.length > 3 ? parts[3].trim() : "General";
                        return new Instructor(id, name, email, department);
                    })
                    .collect(Collectors.toList());
            dataStore.getInstructors().addAll(instructors);
        }
    }

    private void loadStudents(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) return;

        try (Stream<String> lines = Files.lines(path).skip(1)) { // Skip header line
            List<Student> students = lines
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length >= 3)
                    .map(parts -> {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        String email = parts[2].trim();
                        String regNo = "REG" + id; // Generate a simple registration number
                        // Create a default student who enrolled a year ago.
                        return new Student(id, name, email, regNo, LocalDate.now().minusYears(1));
                    })
                    .collect(Collectors.toList());
            dataStore.getStudents().addAll(students);
        }
    }

    private void loadCourses(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) return;

        try (Stream<String> lines = Files.lines(path).skip(1)) {
            List<Course> courses = lines
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length >= 5)
                    .map(parts -> new Course.CourseBuilder(parts[0].trim(), parts[1].trim())
                            .credits(Integer.parseInt(parts[2].trim()))
                            .instructorId(Integer.parseInt(parts[3].trim()))
                            .semester(Semester.valueOf(parts[4].trim().toUpperCase()))
                            .build())
                    .collect(Collectors.toList());
            dataStore.getCourses().addAll(courses);
        }
    }

    /**
     * Exports all current data to CSV files in a dedicated 'exports' directory.
     * Demonstrates writing to files and creating directories with NIO.2.
     */
    public void exportData() {
        System.out.println("Exporting current data...");
        Path exportDir = Paths.get("exports");
        try {
            // This line is crucial - it creates the directory if it doesn't exist.
            Files.createDirectories(exportDir);

            exportStudents(exportDir.resolve("students_export.csv"));
            exportCourses(exportDir.resolve("courses_export.csv"));
            exportEnrollments(exportDir.resolve("enrollments_export.csv"));

            System.out.println("Data successfully exported to the 'exports' directory.");
        } catch (IOException e) {
            System.err.println("Error exporting data: " + e.getMessage());
        }
    }

    private void exportStudents(Path path) throws IOException {
        String header = "ID,FullName,Email,RegistrationNumber,Status,EnrollmentDate\n";
        List<String> lines = dataStore.getStudents().stream()
                .map(s -> String.join(",",
                        String.valueOf(s.getId()),
                        s.getFullName(),
                        s.getEmail(),
                        s.getRegistrationNumber(),
                        s.getStatus().toString(),
                        s.getEnrollmentDate().toString()))
                .collect(Collectors.toList());

        // These options ensure a new file is created or an existing one is overwritten.
        Files.write(path, Collections.singleton(header), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(path, lines, StandardOpenOption.APPEND);
    }

    private void exportCourses(Path path) throws IOException {
        String header = "CourseCode,Title,Credits,InstructorID,Semester\n";
        List<String> lines = dataStore.getCourses().stream()
                .map(c -> String.join(",",
                        c.getCourseCode(),
                        c.getTitle(),
                        String.valueOf(c.getCredits()),
                        String.valueOf(c.getInstructorId()),
                        c.getSemester().toString()))
                .collect(Collectors.toList());
        Files.write(path, Collections.singleton(header), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(path, lines, StandardOpenOption.APPEND);
    }

    private void exportEnrollments(Path path) throws IOException {
        String header = "StudentID,CourseCode,Grade\n";
        List<String> lines = dataStore.getStudents().stream()
                // This is a great example of flatMap to turn a List<Student> into a Stream<Enrollment>
                .flatMap(student -> student.getEnrollments().stream())
                .map(e -> String.join(",",
                        String.valueOf(e.getStudentId()),
                        e.getCourseCode(),
                        e.getGrade() != null ? e.getGrade().toString() : "N/A"))
                .collect(Collectors.toList());
        Files.write(path, Collections.singleton(header), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(path, lines, StandardOpenOption.APPEND);
    }
}