package org.ST.mfusi.profile;

import lombok.Getter;
import org.ST.mfusi.todolist.ToDoList;
import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;

import java.util.List;
import java.util.Objects;

/**
 * Manages a user's profile, including their name, email, and personal to-do list.
 */
public class Profile {

    @Getter
    private String name;
    @Getter
    private String email;
    private final ToDoList toDoList;

    public Profile(String name) {
        this.name = name;
        this.email = ""; // Default to empty string
        this.toDoList = new ToDoList();
    }

    public Profile(String name, String email) {
        this.name = name;
        this.email = email;
        this.toDoList = new ToDoList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    /**
     * Adds a new task to this profile's to-do list.
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

    @Override
    public String toString() {
        return "Profile[name='" + name + "', email='" + email + "']";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return Objects.equals(name, profile.name) && Objects.equals(email, profile.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}