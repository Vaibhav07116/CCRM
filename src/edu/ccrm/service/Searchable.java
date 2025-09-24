package edu.ccrm.service;

/**
 * A generic interface for services that can find objects by an ID.
 * This demonstrates the use of Java Generics (<T, ID>) and is a core part
 * of the polymorphic design, allowing different services to be searched in a common way.
 *
 * @param <T>  The type of the object to find (e.g., Student, Course).
 * @param <ID> The type of the ID to search by (e.g., Integer, String).
 */
public interface Searchable<T, ID> {

    /**
     * Finds an object by its unique identifier.
     *
     * @param id The ID to search for.
     * @return The found object, or null if no object is found with that ID.
     */
    T findById(ID id);
}

