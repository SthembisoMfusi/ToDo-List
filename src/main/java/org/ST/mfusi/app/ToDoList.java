package org.ST.mfusi.app;

import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * defines a to-do list class which
 * sorts all of the tasks that a user has to do.
 * they range in their priorities, completed status, and description
 */
public class ToDoList {
    private List<Task> taskList;

    public ToDoList(){
        this.taskList = new ArrayList<Task>();

    }
    /**
     * adds a new task to the list
     * @param description the description of the task
     */
    public void addTask(String description) {
        Task task = new Task(description);
        taskList.add(task);
    }
    /**
     * adds a new task to the list with a title
     * @param title the title of the task
     * @param description the description of the task
     */
    public void addTask(String title, String description) {
        Task task = new Task(title, description);
        taskList.add(task);
    }
    /**
     * adds a new task to the list with a title and priority
     * @param title the title of the task
     * @param description the description of the task
     * @param priority the priority of the task
     */
    public void addTask(String title, String description, Priority priority) {
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