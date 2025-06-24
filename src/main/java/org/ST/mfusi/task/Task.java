package org.ST.mfusi.task;

import java.util.Objects;

/**
 * defines a task class which a user can create
 * @author Sthembiso Mfusi
 *  @version 1.0
 *  @since 2025-06-24
 */
public class Task {
    /**
     * The title of the task. Can be null if not provided.
     */
    private String title;
    /**
     * The detailed description of the task.
     */
    private String description;
    /**
     * The completion status of the task. Defaults to false.
     */
    private boolean completed;
    /**
     * The priority level of the task.
     */
    private Priority priority;


    /**
     * Constructs a new Task with only a description.
     * The title will be null, and the priority will default to MEDIUM.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.priority = Priority.MEDIUM;
        this.title = null; // Title is optional, can be null
    }

    /**
     * Constructs a new Task with a title and a description.
     * The priority will default to MEDIUM.
     * @param title The title of the task.
     * @param description The description of the task.
     */
    public Task(String title, String description ) {
        this.description = description;
        this.title = title;
        this.completed = false;
        this.priority = Priority.MEDIUM;
    }

    /**
     * Constructs a new Task with a title, description, and priority.
     * @param title The title of the task.
     * @param description The description of the task.
     * @param priority The priority of the task.
     */
    public Task(String title, String description, Priority priority) {
        this.description = description;
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    /**
     * Gets the title of the task.
     * @return The title string.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     * @param title The new title for the task.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the task.
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     * @param description The new description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks the completion status of the task.
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion status of the task.
     * @param completed The new completion status.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets the priority of the task.
     * @return The {@link Priority} enum.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     * @param priority The new priority for the task.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns a string representation of the Task object, including all its fields.
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", completed=").append(completed);
        sb.append(", priority=").append(priority);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Compares this Task to another object for equality. Two tasks are considered
     * equal if all their fields (title, description, completion status, and priority) are equal.
     * @param o The object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task task)) return false;
        return isCompleted() == task.isCompleted() && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getDescription(), task.getDescription()) && getPriority() == task.getPriority();
    }

    /**
     * Returns a hash code value for the Task object.
     * The hash code is generated based on all fields of the task.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), isCompleted(), getPriority());
    }
}