package edu.ccrm.service;

import edu.ccrm.config.DataStore;
import edu.ccrm.domain.Course;
import java.util.List;
import java.util.stream.Collectors;

public class CourseService implements Searchable<Course, String> {
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    public Course findById(String courseCode) {
        // This now returns a Course or null, NOT Optional<Course>
        return dataStore.getCourses().stream()
                .filter(course -> course.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElse(null);
    }

    public List<Course> getAllCourses() {
        return dataStore.getCourses();
    }

    public List<Course> findCoursesByInstructor(int instructorId) {
        return dataStore.getCourses().stream()
                .filter(course -> course.getInstructorId() == instructorId)
                .collect(Collectors.toList());
    }
}


