package org.ST.mfusi.app;

import org.ST.mfusi.profile.Profile;
import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;

/**
 * Handles all the core application logic for the To-Do list.
 * This includes managing user profiles, tasks, and their priorities.
 * It provides methods to add, remove, edit, and mark tasks as complete,
 * as well as to change task priorities.
 * <p> The AppController acts as a bridge between the user interface and the
 *  underlying data model, ensuring that all operations are performed
 *  in a consistent manner.
 *  </p>
 * <p> This class is designed to be used in a console application or
 *  a GUI application, where it can be instantiated with a user's profile name.
 * It provides methods to interact with the user's to-do list,
 *  such as adding tasks, removing tasks, marking tasks as complete,
 *  changing task priorities, and editing tasks.
 *  The AppController class encapsulates the logic for managing a user's
 *  to-do list, ensuring that all operations are performed
 *  in a consistent manner.
 *  </p>
 * @author Sthembiso Mfusi
 * @version 1.0
 * @since 2025-06-24
 */
public class AppController {
    /**
     * The user profile associated with this controller instance.
     */
    private final Profile userProfile;

    /**
     * Constructs a new AppController and creates a user profile with the given name.
     * @param profileName The name of the user for whom the profile will be created.
     */
    public AppController(String profileName) {
        this.userProfile = new Profile(profileName);
    }

    /**
     * Retrieves the user profile managed by this controller.
     * @return The {@link Profile} instance.
     */
    public Profile getUserProfile() {
        return userProfile;
    }

    /**
     * Generates a user-friendly, formatted string representing the current to-do list.
     * @return A string containing the formatted list of tasks.
     */
    public String getFormattedToDoList() {
        return userProfile.getFormattedToDoList();
    }

    /**
     * Adds a new task to the user's to-do list.
     * @param title The title of the task.
     * @param description The detailed description of the task.
     * @param priority The priority level of the task.
     * @return A status message indicating success or failure.
     */
    public String addTask(String title, String description, Priority priority) {
        if (title == null || title.isBlank() || description == null || description.isBlank() || priority == null) {
            return "Error: All fields (title, description, priority) are required.";
        }
        userProfile.addTask(title, description, priority);
        return "Task added successfully!";
    }

    /**
     * Removes a task from the to-do list based on its 1-based index.
     * @param taskNumber The number of the task to remove as displayed in the list.
     * @return A status message indicating success or failure.
     */
    public String removeTask(int taskNumber) {
        if (userProfile.removeTask(taskNumber)) {
            return "Task removed successfully.";
        }
        return "Invalid task number.";
    }

    /**
     * Marks a task as complete based on its 1-based index.
     * @param taskNumber The number of the task to mark as complete.
     * @return A status message indicating success or failure.
     */
    public String markTaskComplete(int taskNumber) {
        if (userProfile.markTaskComplete(taskNumber)) {
            return "Task marked as complete.";
        }
        return "Invalid task number.";
    }

    /**
     * Changes the priority of a specific task.
     * @param taskNumber The 1-based index of the task to modify.
     * @param newPriority The new priority to set for the task.
     * @return A status message indicating success or failure.
     */
    public String changeTaskPriority(int taskNumber, Priority newPriority) {
        if (taskNumber > 0 && taskNumber <= userProfile.getTasks().size()) {
            Task task = userProfile.getTasks().get(taskNumber - 1);
            task.setPriority(newPriority);
            return "Priority updated successfully.";
        }
        return "Invalid task number.";
    }

    /**
     * Edits the title and/or description of an existing task.
     * @param taskNumber The 1-based index of the task to edit.
     * @param newTitle The new title for the task. If blank, the title is not changed.
     * @param newDescription The new description for the task. If blank, the description is not changed.
     * @return A status message indicating success or failure.
     */
    public String editTask(int taskNumber, String newTitle, String newDescription) {
        if (taskNumber > 0 && taskNumber <= userProfile.getTasks().size()) {
            Task task = userProfile.getTasks().get(taskNumber - 1);
            if (newTitle != null && !newTitle.isBlank()) {
                task.setTitle(newTitle);
            }
            if (newDescription != null && !newDescription.isBlank()) {
                task.setDescription(newDescription);
            }
            return "Task updated successfully.";
        }
        return "Invalid task number.";
    }
    /**
     * Clears all tasks from the user's to-do list.
     * @return A status message indicating success or failure.
     */
    public String clearAllTasks() {
        userProfile.clearTasks();
        return "All tasks cleared successfully.";
    }
    /**
     * Retrieves the number of tasks in the user's to-do list.
     * @return The count of tasks.
     */
    public int getTaskCount() {
        return userProfile.getTasks().size();
    }
    /**
     * Retrieves the user profile name.
     * @return The name of the user profile.
     */
    public String getProfileName() {
        return userProfile.getName();
    }
}