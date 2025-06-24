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

    // --- NEW VALIDATION TESTS ---

    @Test
    @DisplayName("addTask should throw exception for blank title")
    void addTask_shouldThrowException_forBlankTitle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            toDoList.addTask("", "A valid description", Priority.LOW);
        });
        assertEquals("Task title cannot be null or empty.", exception.getMessage());
    }

    @Test
    @DisplayName("addTask should throw exception for null description")
    void addTask_shouldThrowException_forNullDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            toDoList.addTask("A valid title", null, Priority.LOW);
        });
        assertEquals("Task description cannot be null or empty.", exception.getMessage());
    }

    @Test
    @DisplayName("addTask should throw exception for null priority")
    void addTask_shouldThrowException_forNullPriority() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            toDoList.addTask("A valid title", "A valid description", null);
        });
        assertEquals("Task priority cannot be null.", exception.getMessage());
    }

    // --- UPDATED AND FIXED TESTS ---

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
    @DisplayName("getTaskList should return the internal list of tasks")
    void getTaskList_shouldReturnInternalList() {
        toDoList.addTask("My Task", "My Desc");

        List<Task> returnedList = toDoList.getTaskList();

        assertNotNull(returnedList);
        assertEquals(1, returnedList.size());
        assertEquals("My Task", returnedList.get(0).getTitle());
    }

    @Test
    @DisplayName("removeTask should decrease task count when task exists")
    void removeTask_whenTaskExists() {
        toDoList.addTask("Other Task", "Description");
        toDoList.addTask("Title", "To Be Removed", Priority.LOW);
        Task taskToRemove = toDoList.getTaskList().get(1);

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
        // Fix: Use a valid addTask call
        toDoList.addTask("Task 1", "Desc 1");
        toDoList.addTask("Task 2", "Desc 2");
        assertFalse(toDoList.isEmpty());

        toDoList.clearTasks();

        assertTrue(toDoList.isEmpty());
        assertEquals(0, toDoList.getTaskCount());
    }

    @Test
    @DisplayName("getTaskCount should return the correct number of tasks")
    void getTaskCount() {
        assertEquals(0, toDoList.getTaskCount());

        // Fix: Use valid addTask calls
        toDoList.addTask("Task 1", "Desc 1");
        assertEquals(1, toDoList.getTaskCount());

        toDoList.addTask("Task 2", "Desc 2");
        assertEquals(2, toDoList.getTaskCount());
    }

    @Test
    @DisplayName("toString should contain the class name and task details")
    void testToString() {
        // Fix: Use a valid addTask call
        toDoList.addTask("My Task", "My Desc");
        String result = toDoList.toString();

        assertTrue(result.contains("ToDoList"));
        assertTrue(result.contains("My Task")); // Checks for title
    }

    @Test
    @DisplayName("isEmpty should be true for new list and false after adding a task")
    void isEmpty() {
        assertTrue(toDoList.isEmpty());

        // Fix: Use a valid addTask call
        toDoList.addTask("A task", "A desc");

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

        // Test that changing a list breaks equality
        // Fix: Use a valid addTask call
        list2.addTask("Another task", "Another desc");
        assertNotEquals(list1, list2, "Lists should not be equal after one is modified");
    }
}