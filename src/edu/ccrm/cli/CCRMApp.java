package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.EnrollmentException;
import edu.ccrm.exception.MaxCreditsException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.FileService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.TranscriptService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CCRMApp {

    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();
    private static final TranscriptService transcriptService = new TranscriptService();
    private static final FileService fileService = new FileService();
    private static final BackupService backupService = new BackupService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        fileService.loadInitialData();
        displayWelcomeMessage();

        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> handleStudentMenu();
                case 2 -> handleCourseMenu();
                case 3 -> handleEnrollmentMenu();
                case 4 -> handleFileMenu();
                case 5 -> {
                    exit = true;
                    System.out.println("\nThank you for using CCRM. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }

    private static void displayWelcomeMessage() {
        System.out.println("======================================================");
        System.out.println(" Welcome to the Campus Course & Records Manager (CCRM)");
        System.out.println("======================================================");
    }

    private static void displayMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments & Grades");
        System.out.println("4. File Utilities");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleStudentMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add New Student"); // NEW OPTION
        System.out.println("2. List All Students");
        System.out.println("3. View Student Profile & Transcript");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = getUserChoice();

        switch (choice) {
            case 1 -> addNewStudent(); // NEW METHOD CALL
            case 2 -> listAllStudents();
            case 3 -> viewStudentProfile();
            case 4 -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid choice.");
        }
    }

    // --- NEW METHOD TO HANDLE ADDING A STUDENT ---
    private static void addNewStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Registration Number (e.g., REG1006): ");
        String regNum = scanner.nextLine();

        Student newStudent = studentService.addStudent(fullName, email, regNum);
        System.out.println("Student added successfully!");
        System.out.println(newStudent.getProfile());
    }

    // --- Other menu handlers remain the same ---
    private static void handleCourseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. List All Courses");
        System.out.println("2. Search Courses by Instructor ID");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = getUserChoice();

        switch (choice) {
            case 1 -> listAllCourses();
            case 2 -> searchCoursesByInstructor();
            case 3 -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void handleEnrollmentMenu() {
        System.out.println("\n--- Enrollment & Grade Management ---");
        System.out.println("1. Enroll Student in a Course");
        System.out.println("2. Assign Grade to Student");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = getUserChoice();

        switch (choice) {
            case 1 -> enrollStudent();
            case 2 -> assignGrade();
            case 3 -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void handleFileMenu() {
        System.out.println("\n--- File Utilities ---");
        System.out.println("1. Export All Data");
        System.out.println("2. Create Backup of Exported Data");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = getUserChoice();

        switch (choice) {
            case 1 -> fileService.exportData();
            case 2 -> backupService.createBackup();
            case 3 -> System.out.println("Returning to Main Menu...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void listAllStudents() {
        System.out.println("\n--- List of All Students ---");
        studentService.getAllStudents().forEach(student -> System.out.println(student.getProfile()));
    }

    private static void viewStudentProfile() {
        System.out.print("Enter Student ID to view profile: ");
        int studentId = getUserChoice();
        Student student = studentService.findById(studentId);
        if (student != null) {
            System.out.println(transcriptService.generateTranscript(student));
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    private static void listAllCourses() {
        System.out.println("\n--- List of All Courses ---");
        courseService.getAllCourses().forEach(System.out::println);
    }

    private static void searchCoursesByInstructor() {
        System.out.print("Enter Instructor ID to search courses: ");
        int instructorId = getUserChoice();
        System.out.println("\n--- Courses taught by Instructor ID: " + instructorId + " ---");
        List<Course> courses = courseService.findCoursesByInstructor(instructorId);
        if (courses.isEmpty()) {
            System.out.println("No courses found for this instructor.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    private static void enrollStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = getUserChoice();
        System.out.print("Enter Course Code (e.g., CS101): ");
        String courseCode = scanner.nextLine();

        try {
            enrollmentService.enrollStudent(studentId, courseCode);
            System.out.println("Enrollment successful!");
        } catch (EnrollmentException | MaxCreditsException e) {
            System.err.println("Enrollment Failed: " + e.getMessage());
        }
    }

    private static void assignGrade() {
        System.out.print("Enter Student ID: ");
        int studentId = getUserChoice();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Grade (S, A, B, C, D, F): ");
        String gradeStr = scanner.nextLine().toUpperCase();

        try {
            Grade grade = Grade.valueOf(gradeStr);
            enrollmentService.assignGrade(studentId, courseCode, grade);
            System.out.println("Grade assigned successfully!");
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Grade. Please use one of S, A, B, C, D, F.");
        } catch (EnrollmentException e) {
            System.err.println("Failed to assign grade: " + e.getMessage());
        }
    }

    private static int getUserChoice() {
        do {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine();
            }
        } while (true);
    }
}
