package org.ST.mfusi.profile;

import lombok.Getter;
import org.ST.mfusi.todolist.ToDoList;
import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;

import java.util.List;
import java.util.Objects;

/**
 * Manages a user's profile, including their name, email, and personal to-do list.
 * @author Sthembiso Mfusi
 * @version 1.0
 * @since 2025-06-24
 */
public class Profile {

    /**
     * The name of the user associated with this profile.
     */
    @Getter
    private String name;
    /**
     * The email address of the user associated with this profile.
     */
    @Getter
    private String email;
    /**
     * The to-do list containing all tasks for this profile.
     */
    private final ToDoList toDoList;

    /**
     * Constructs a new Profile with a name. The email will be set to an empty string.
     * @param name The name for the new profile.
     */
    public Profile(String name) {
        this.name = name;
        this.email = ""; // Default to empty string
        this.toDoList = new ToDoList();
    }

    /**
     * Constructs a new Profile with a name and an email address.
     * @param name The name for the new profile.
     * @param email The email address for the new profile.
     */
    public Profile(String name, String email) {
        this.name = name;
        this.email = email;
        this.toDoList = new ToDoList();
    }

    /**
     * Updates the name of this profile.
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updates the email address of this profile.
     * @param email The new email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }



    /**
     * Adds a new task to this profile's to-do list.
     * @param title The title of the new task.
     * @param description The description of the new task.
     * @param priority The priority of the new task.
     */
    public void addTask(String title, String description, Priority priority) {
        this.toDoList.addTask(title, description, priority);
    }
    /**
     * Removes a task from the list, identified by its number.
     * @param taskNumber The 1-based index of the task to remove.
     * @return true if the task was found and removed, false otherwise.
     */
    public boolean removeTask(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= toDoList.getTaskCount()) {
            toDoList.getTaskList().remove(taskNumber - 1);
            return true;
        }
        return false;
    }
    /**
     * Marks a task in the list as complete, identified by its index (starting from 1).
     * @param taskNumber The 1-based index of the task to complete.
     * @return true if the task was found and marked, false otherwise.
     */
    public boolean markTaskComplete(int taskNumber) {
        if (taskNumber > 0 && taskNumber <= toDoList.getTaskCount()) {
            Task task = toDoList.getTaskList().get(taskNumber - 1); // Adjust for 0-based index
            task.setCompleted(true);
            return true;
        }
        return false;
    }

    /**
     * Provides direct access to the list of tasks.
     * @return a list of {@link Task} objects.
     */
    public List<Task> getTasks() {
        return this.toDoList.getTaskList();
    }

    /**
     * Generates a clean, formatted string of the to-do list for terminal display.
     * @return A user-friendly string representing the to-do list.
     */
    public String getFormattedToDoList() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- To-Do List for ").append(this.name).append(" ---\n");

        if (toDoList.isEmpty()) {
            sb.append("🎉 All tasks are complete, or no tasks have been added yet!\n");
        } else {
            List<Task> tasks = toDoList.getTaskList();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String statusIcon = task.isCompleted() ? "[x]" : "[ ]";
                String title = task.getTitle() != null ? task.getTitle() : "Task";

                // Format: 1. [ ] Title: Description (Priority: HIGH)
                sb.append(String.format("%d. %s %s: %s (Priority: %s)\n",
                        i + 1, // Use 1-based numbering for the user
                        statusIcon,
                        title,
                        task.getDescription(),
                        task.getPriority()
                ));
            }
        }
        sb.append("----------------------------\n");
        return sb.toString();
    }

    /**
     * Returns a string representation of the Profile, including its name and email.
     * The to-do list itself is not included in this representation.
     * @return a formatted string representing the profile.
     */
    @Override
    public String toString() {
        return "Profile[name='" + name + "', email='" + email + "']";
    }

    /**
     * Compares this Profile to another object for equality. Two profiles are considered
     * equal if they have the same name and email address. The content of their
     * to-do lists is not considered in this comparison.
     * @param o The object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return Objects.equals(name, profile.name) && Objects.equals(email, profile.email);
    }

    /**
     * Returns a hash code value for the Profile object. The hash code is generated
     * based on the name and email address.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}