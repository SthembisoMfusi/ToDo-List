package org.ST.mfusi.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {



    @Test
    @DisplayName("Task should be created with title and description")
    void task_shouldBeCreated_withTitleAndDescription() {
        Task task = new Task("My Title", "My Description");

        assertEquals("My Title", task.getTitle());
        assertEquals("My Description", task.getDescription());
        assertFalse(task.isCompleted());
        assertEquals(Priority.MEDIUM, task.getPriority(), "Default priority should be MEDIUM");
    }

    @Test
    @DisplayName("Task should be created with all attributes")
    void task_shouldBeCreated_withAllAttributes() {
        Task task = new Task("My Title", "My Description", Priority.HIGH);

        assertEquals("My Title", task.getTitle());
        assertEquals("My Description", task.getDescription());
        assertEquals(Priority.HIGH, task.getPriority());
        assertFalse(task.isCompleted());
    }

    @Test
    @DisplayName("isCompleted status should be updatable")
    void setCompleted_shouldUpdateStatusCorrectly() {

        Task task = new Task("Completion Test", "A description");

        task.setCompleted(true);
        assertTrue(task.isCompleted());

        task.setCompleted(false);
        assertFalse(task.isCompleted());
    }

    @Test
    @DisplayName("Title should be gettable and settable")
    void testSetAndGetTitle() {
        Task task = new Task("Initial Title", "description");
        assertEquals("Initial Title", task.getTitle());

        task.setTitle("New Title");
        assertEquals("New Title", task.getTitle());
    }

    @Test
    @DisplayName("Description should be gettable and settable")
    void testSetAndGetDescription() {
        // Fix: Use a valid constructor
        Task task = new Task("Description Test", "Initial Description");
        assertEquals("Initial Description", task.getDescription());

        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription());
    }

    @Test
    @DisplayName("Priority should be gettable and settable")
    void testSetAndGetPriority() {

        Task task = new Task("Check priority", "description", Priority.LOW);
        assertEquals(Priority.LOW, task.getPriority());

        task.setPriority(Priority.HIGH);
        assertEquals(Priority.HIGH, task.getPriority());
    }

    @Test
    @DisplayName("equals and hashCode should work based on object properties")
    void testEqualsAndHashCode() {
        // These tests were already valid
        Task task1 = new Task("Identical Task", "Same description", Priority.HIGH);
        Task task2 = new Task("Identical Task", "Same description", Priority.HIGH);
        Task task3 = new Task("Different Task", "Another description", Priority.LOW);

        // Test equals contract
        assertEquals(task1, task1); // Reflexive
        assertEquals(task1, task2); // Symmetric
        assertNotEquals(task1, task3);
        assertNotEquals(null, task1);

        // Test equality with different completed status
        task1.setCompleted(true);
        assertNotEquals(task1, task2);
        task2.setCompleted(true);
        assertEquals(task1, task2);

        // Test hashCode contract
        assertEquals(task1.hashCode(), task2.hashCode());
        assertNotEquals(task1.hashCode(), task3.hashCode());
    }

    @Test
    @DisplayName("toString should return a string containing key task details")
    void testToString() {
        // This test was already valid
        Task task = new Task("My Test Title", "A description for the test.", Priority.MEDIUM);
        task.setCompleted(true);

        String taskString = task.toString();

        assertTrue(taskString.contains("My Test Title"));
        assertTrue(taskString.contains("A description for the test."));
        assertTrue(taskString.contains("priority=MEDIUM"));
        assertTrue(taskString.contains("completed=true"));
    }
}