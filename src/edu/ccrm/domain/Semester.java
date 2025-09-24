package edu.ccrm.domain;

/**
 * An Enum to represent the fixed set of academic semesters.
 * Using an enum prevents errors from using incorrect string values
 * (e.g., "spring" vs "SPRING").
 */
public enum Semester {
    FALL,
    INTERIM,
    SUMMER,
    WINTER
}

