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
    private final Profile userProfile;

    public AppController(String profileName) {
        this.userProfile = new Profile(profileName);
    }

    public Profile getUserProfile() {
        return userProfile;
    }

    public String getFormattedToDoList() {
        return userProfile.getFormattedToDoList();
    }

    public String addTask(String title, String description, Priority priority) {
        if (title == null || title.isBlank() || description == null || description.isBlank() || priority == null) {
            return "Error: All fields (title, description, priority) are required.";
        }
        userProfile.addTask(title, description, priority);
        return "Task added successfully!";
    }

    public String removeTask(int taskNumber) {
        if (userProfile.removeTask(taskNumber)) {
            return "Task removed successfully.";
        }
        return "Invalid task number.";
    }

    public String markTaskComplete(int taskNumber) {
        if (userProfile.markTaskComplete(taskNumber)) {
            return "Task marked as complete.";
        }
        return "Invalid task number.";
    }

    public String changeTaskPriority(int taskNumber, Priority newPriority) {
        if (taskNumber > 0 && taskNumber <= userProfile.getTasks().size()) {
            Task task = userProfile.getTasks().get(taskNumber - 1);
            task.setPriority(newPriority);
            return "Priority updated successfully.";
        }
        return "Invalid task number.";
    }

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
}