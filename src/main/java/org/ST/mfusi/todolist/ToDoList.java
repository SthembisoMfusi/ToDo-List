package org.ST.mfusi.todolist;

import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;
import org.ST.mfusi.validator.TaskValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * defines a to-do list class which
 * sorts all of the tasks that a user has to do.
 * they range in their priorities, completed status, and description
 * @author Sthembiso Mfusi
 * @version 1.1
 * @since 2025-06-24
 */
public class ToDoList {
    /**
     * A list holding all the {@link Task} objects in this to-do list.
     */
    private List<Task> taskList;

    /**
     * Constructs a new, empty ToDoList.
     */
    public ToDoList(){
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new task to the list with a title and description, using a default priority of MEDIUM.
     * This method validates the inputs before creating the task.
     *
     * @param title the title of the task.
     * @param description the description of the task.
     * @throws IllegalArgumentException if the title or description are invalid.
     */
    public void addTask(String title, String description) {
        // This method now delegates to the more specific one, ensuring validation happens there.
        addTask(title, description, Priority.MEDIUM);
    }

    /**
     * Adds a new task to the list after validating its properties.
     * This is the primary method for adding tasks.
     *
     * @param title the title of the task.
     * @param description the description of the task.
     * @param priority the priority of the task.
     * @throws IllegalArgumentException if any of the provided arguments are invalid.
     */
    public void addTask(String title, String description, Priority priority) {
        // 1. Validate inputs first. This will throw an exception if data is bad.
        TaskValidator.validate(title, description, priority);

        // 2. Only if validation passes, create and add the task.
        Task task = new Task(title, description, priority);
        taskList.add(task);
    }

    /**
     * returns the list of tasks
     * @return the list of tasks
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * removes a task from the list
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * clears the list of tasks
     */
    public void clearTasks() {
        taskList.clear();
    }

    /**
     * returns the number of tasks in the list
     * @return the number of tasks in the list
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * returns a string representation of the to-do list
     * @return a string representation of the to-do list
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", ToDoList.class.getSimpleName() + "[", "]")
                .add("taskList=" + taskList)
                .toString();
    }

    /**
     * checks if the to-do list is empty
     * @return true if the to-do list is empty, false otherwise
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Overrides the equals method to compare two to-do lists
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ToDoList toDoList)) return false;
        return Objects.equals(getTaskList(), toDoList.getTaskList());
    }

    /**
     * Overrides the hashCode method to generate a hash code for the to-do list
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getTaskList());
    }
}