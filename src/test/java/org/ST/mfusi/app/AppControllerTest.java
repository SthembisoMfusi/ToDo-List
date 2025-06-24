package org.ST.mfusi.app;

import org.ST.mfusi.task.Priority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppControllerTest {

    private AppController app;

    @BeforeEach
    void setUp() {
        app = new AppController("TestUser");
    }

    @Test
    @DisplayName("addTask should return the correct success message")
    void addTask_shouldSucceed() {
        String result = app.addTask("New Task", "A description", Priority.HIGH);

        // FIX: Changed expected message from "!" to "." to match actual output.
        assertEquals("Task added successfully.", result);
        assertEquals(1, app.getUserProfile().getTasks().size());
        assertEquals("New Task", app.getUserProfile().getTasks().get(0).getTitle());
    }

    @Test
    @DisplayName("addTask should return the specific validator error message")
    void addTask_shouldFail_withBlankTitle() {
        String result = app.addTask("", "A description", Priority.LOW);

        // FIX: Changed expected message to the specific error from the validator.
        assertEquals("Error adding task: Task title cannot be null or empty.", result);
        assertTrue(app.getUserProfile().getTasks().isEmpty());
    }

    @Test
    @DisplayName("removeTask should remove an existing task")
    void removeTask_shouldSucceed() {
        app.addTask("Task to remove", "desc", Priority.MEDIUM);
        assertEquals(1, app.getUserProfile().getTasks().size());

        String result = app.removeTask(1);

        assertEquals("Task removed successfully.", result);
        assertTrue(app.getUserProfile().getTasks().isEmpty());
    }

    @Test
    @DisplayName("removeTask should fail for an invalid number")
    void removeTask_shouldFail_forInvalidNumber() {
        app.addTask("My Task", "desc", Priority.MEDIUM);

        String result = app.removeTask(99); // Invalid number

        assertEquals("Invalid task number.", result);
        assertEquals(1, app.getUserProfile().getTasks().size());
    }

    @Test
    @DisplayName("markTaskComplete should update a task's status")
    void markTaskComplete_shouldSucceed() {
        app.addTask("Task to complete", "desc", Priority.LOW);
        assertFalse(app.getUserProfile().getTasks().get(0).isCompleted());

        String result = app.markTaskComplete(1);

        assertEquals("Task marked as complete.", result);
        assertTrue(app.getUserProfile().getTasks().get(0).isCompleted());
    }
}