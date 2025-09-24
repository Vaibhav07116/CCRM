package edu.ccrm.config;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Instructor;
import edu.ccrm.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implements the Singleton design pattern to provide a single, global point of access
 * to the application's in-memory data. This ensures that all parts of the application
 * share the same data store.
 */
public class DataStore {

    // 1. The single, static instance of the class. It's private so no one else can access it.
    private static DataStore instance = null;

    // Data lists to hold our domain objects.
    private final List<Student> students;
    private final List<Instructor> instructors;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;

    // AtomicIntegers are a thread-safe way to generate unique IDs.
    private final AtomicInteger studentIdCounter;
    private final AtomicInteger instructorIdCounter;

    /**
     * 2. The constructor is private. This is the key to the Singleton pattern.
     * It prevents anyone from creating a new instance using `new DataStore()`.
     */
    private DataStore() {
        students = new ArrayList<>();
        instructors = new ArrayList<>();
        courses = new ArrayList<>();
        enrollments = new ArrayList<>();
        
        // Start counters from 1
        studentIdCounter = new AtomicInteger(0);
        instructorIdCounter = new AtomicInteger(0);
    }

    /**
     * 3. The public static method that provides access to the single instance.
     * This is the only way to get a reference to the DataStore object.
     * It's "lazy-initialized" - the instance is only created the first time it's needed.
     *
     * @return The single instance of the DataStore.
     */
    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    // --- ID Generation ---
    public int getNextStudentId() {
        return studentIdCounter.incrementAndGet();
    }
    
    public int getNextInstructorId() {
        return instructorIdCounter.incrementAndGet();
    }

    // --- Data Access Methods (Getters for the lists) ---

    public List<Student> getStudents() {
        return students;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}