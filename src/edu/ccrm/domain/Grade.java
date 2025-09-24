package edu.ccrm.domain;

/**
 * An Enum to represent letter grades and their corresponding grade points.
 * This is a key requirement: an enum with a constructor and fields.
 * This makes calculating GPA straightforward and type-safe.
 */
public enum Grade {
    // Each enum constant is an object, created by calling the private constructor
    S(10.0),  // S for 'Super' or 'Outstanding'
    A(9.0),
    B(8.0),
    C(7.0),
    D(6.0),
    E(5.0),
    F(0.0);   // F for 'Fail'

    // This is a field for each enum constant
    private final double gradePoint;

    /**
     * This is a private constructor for the enum.
     * When you write S(10.0), this constructor is called.
     * @param gradePoint The numerical value of the grade.
     */
    private Grade(double gradePoint) {
        this.gradePoint = gradePoint;
    }

    /**
     * A public getter to access the grade point value.
     * This allows us to get the value (e.g., Grade.A.getGradePoint() will return 9.0).
     * @return the grade point
     */
    public double getGradePoint() {
        return gradePoint;
    }
}