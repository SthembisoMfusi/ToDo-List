package org.ST.mfusi.validator;

import org.ST.mfusi.task.Priority;

/**
 * A utility class for validating Task properties before creation.
 * This ensures that no invalid Task objects can be instantiated.
 */
public final class TaskValidator {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private TaskValidator() {}

    /**
     * Validates the core properties of a task.
     * Throws an exception if any validation rule is violated.
     *
     * @param title The title of the task to validate.
     * @param description The description of the task to validate.
     * @param priority The priority of the task to validate.
     * @throws IllegalArgumentException if title, description, or priority are null or blank.
     */
    public static void validate(String title, String description, Priority priority) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or empty.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be null or empty.");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Task priority cannot be null.");
        }
    }
}