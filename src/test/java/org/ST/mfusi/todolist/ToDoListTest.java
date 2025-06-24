package org.ST.mfusi.todolist;

import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    private ToDoList toDoList;

    @BeforeEach
    @DisplayName("Set up a new, empty ToDoList before each test")
    void setUp() {
        toDoList = new ToDoList();
    }

    @Test
    @DisplayName("addTask with description should add a new task with default values")
    void addTask_withDescription() {
        toDoList.addTask("Test Description");

        assertEquals(1, toDoList.getTaskCount());
        Task addedTask = toDoList.getTaskList().get(0);

        assertEquals("Test Description", addedTask.getDescription());
        assertNull(addedTask.getTitle());
        assertEquals(Priority.MEDIUM, addedTask.getPriority());
        assertFalse(addedTask.isCompleted());
    }

    @Test
    @DisplayName("addTask with title and description should add a new task")
    void addTask_withTitleAndDescription() {
        toDoList.addTask("Test Title", "Test Description");

        assertEquals(1, toDoList.getTaskCount());
        Task addedTask = toDoList.getTaskList().get(0);

        assertEquals("Test Title", addedTask.getTitle());
        assertEquals("Test Description", addedTask.getDescription());
        assertEquals(Priority.MEDIUM, addedTask.getPriority());
    }

    @Test
    @DisplayName("addTask with all parameters should add a new task correctly")
    void addTask_withAllParameters() {
        toDoList.addTask("Test Title", "Test Description", Priority.HIGH);

        assertEquals(1, toDoList.getTaskCount());
        Task addedTask = toDoList.getTaskList().get(0);

        assertEquals("Test Title", addedTask.getTitle());
        assertEquals("Test Description", addedTask.getDescription());
        assertEquals(Priority.HIGH, addedTask.getPriority());
    }

    @Test
    @DisplayName("getTaskList should return the list of tasks")
    void getTaskList() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");

        toDoList.addTask("Task 1");
        // We can't directly add the task object, so we'll create an equivalent one
        // and check if the list contains an object that .equals() it.
        toDoList.getTaskList().set(0, task1); // Replace the created one with our instance for easier testing

        List<Task> returnedList = toDoList.getTaskList();

        assertNotNull(returnedList);
        assertEquals(1, returnedList.size());
        assertTrue(returnedList.contains(task1));
    }

    @Test
    @DisplayName("removeTask should decrease task count when task exists")
    void removeTask_whenTaskExists() {
        Task taskToRemove = new Task("Title", "To Be Removed", Priority.LOW);
        toDoList.addTask("Other Task", "Description");
        toDoList.getTaskList().add(taskToRemove); // Add the specific task instance

        assertEquals(2, toDoList.getTaskCount());

        toDoList.removeTask(taskToRemove);

        assertEquals(1, toDoList.getTaskCount());
        assertFalse(toDoList.getTaskList().contains(taskToRemove));
    }

    @Test
    @DisplayName("removeTask should do nothing if task does not exist")
    void removeTask_whenTaskDoesNotExist() {
        toDoList.addTask("Existing Task", "Description");
        Task nonExistentTask = new Task("Non-Existent", "This task is not in the list");

        assertEquals(1, toDoList.getTaskCount());

        toDoList.removeTask(nonExistentTask);

        assertEquals(1, toDoList.getTaskCount());
    }


    @Test
    @DisplayName("clearTasks should remove all tasks from the list")
    void clearTasks() {
        toDoList.addTask("Task 1");
        toDoList.addTask("Task 2");
        assertFalse(toDoList.isEmpty());

        toDoList.clearTasks();

        assertTrue(toDoList.isEmpty());
        assertEquals(0, toDoList.getTaskCount());
    }

    @Test
    @DisplayName("getTaskCount should return the correct number of tasks")
    void getTaskCount() {
        assertEquals(0, toDoList.getTaskCount());

        toDoList.addTask("Task 1");
        assertEquals(1, toDoList.getTaskCount());

        toDoList.addTask("Task 2");
        assertEquals(2, toDoList.getTaskCount());
    }

    @Test
    @DisplayName("toString should contain the class name")
    void testToString() {
        toDoList.addTask("My Task");
        String result = toDoList.toString();

        assertTrue(result.contains("ToDoList"));
        assertTrue(result.contains("My Task"));
    }

    @Test
    @DisplayName("isEmpty should be true for new list and false after adding a task")
    void isEmpty() {
        assertTrue(toDoList.isEmpty());

        toDoList.addTask("A task");

        assertFalse(toDoList.isEmpty());
    }

    @Test
    @DisplayName("equals and hashCode should be based on task list content")
    void testEqualsAndHashCode() {
        ToDoList list1 = new ToDoList();
        list1.addTask("Shared Task", "Description 1", Priority.HIGH);

        ToDoList list2 = new ToDoList();
        list2.addTask("Shared Task", "Description 1", Priority.HIGH);

        ToDoList list3 = new ToDoList();
        list3.addTask("Different Task", "Description 2", Priority.LOW);

        // Test equals
        assertEquals(list1, list2, "Lists with identical tasks should be equal");
        assertNotEquals(list1, list3, "Lists with different tasks should not be equal");
        assertEquals(list1, list1, "A list should be equal to itself");
        assertNotEquals(null, list1, "A list should not be equal to null");

        // Test hashCode
        assertEquals(list1.hashCode(), list2.hashCode(), "Hash codes should be same for equal lists");
        assertNotEquals(list1.hashCode(), list3.hashCode(), "Hash codes should be different for non-equal lists");

        // Test that changing a list breaks equality
        list2.addTask("Another task");
        assertNotEquals(list1, list2, "Lists should not be equal after one is modified");
    }
}