package org.ST.mfusi.task;

import java.util.Objects;

/**
 * defines a task class which a user can create
 */
public class Task {
    private String title;
    private String description;
    private boolean completed;
    private Priority priority;


    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.priority = Priority.NORMAL;
        this.title = null; // Title is optional, can be null
    }

    public Task(String title, String description ) {
        this.description = description;
        this.title = title;
        this.completed = false;
        this.priority = Priority.NORMAL;
    }

    public Task(String title, String description, Priority priority) {
        this.description = description;
        this.title = title;
        this.priority = priority;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task task)) return false;
        return isCompleted() == task.isCompleted() && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getDescription(), task.getDescription()) && getPriority() == task.getPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), isCompleted(), getPriority());
    }
}