package edu.ccrm.domain;   


public class Course {

    // --- Fields ---
    private final String courseCode;    
    private final String title;        
    private final int credits;         
    private final String department;    
    private final Semester semester;    
    private int instructorId;           

    private Course(CourseBuilder builder) {
        this.courseCode = builder.courseCode;
        this.title = builder.title;
        this.credits = builder.credits;
        this.department = builder.department;
        this.semester = builder.semester;
        this.instructorId = builder.instructorId;
    }

    // --- Getters ---
    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getDepartment() { return department; }
    public Semester getSemester() { return semester; }
    public int getInstructorId() { return instructorId; }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return String.format("Course[Code=%s, Title='%s', Credits=%d, Dept=%s, Semester=%s, InstructorID=%d]",
                courseCode, title, credits, department, semester, instructorId);
    }

 
    public static class CourseBuilder {
        private final String courseCode;
        private final String title;

        private int credits = 3;
        private String department = "UNDECLARED";
        private Semester semester = Semester.FALL;
        private int instructorId = 0;

        public CourseBuilder(String courseCode, String title) {
            this.courseCode = courseCode;
            this.title = title;
        }

        public CourseBuilder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public CourseBuilder department(String department) {
            this.department = department;
            return this;
        }

        public CourseBuilder semester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public CourseBuilder instructorId(int instructorId) {
            this.instructorId = instructorId;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }
}
