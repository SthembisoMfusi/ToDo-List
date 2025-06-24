package org.ST.mfusi.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application Acceptance Tests")
public class AcceptanceTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    // --- Helper method to run the app with simulated input ---
    private void runAppWithInput(String input) {
        InputStream inStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inStream);
        Main.main(null);
    }

    // --- Test Scenarios ---

    @Test
    @DisplayName("Scenario 1.1: Successful Profile Creation and Exit")
    void successfulProfileCreation() {
        runAppWithInput("Alex\n0\n");
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Profile for Alex created"), "Should welcome the user by name.");
    }

    @Test
    @DisplayName("Scenario 3.1: View an Empty List")
    void viewAnEmptyList() {
        runAppWithInput("User\n1\n0\n");
        String consoleOutput = outContent.toString();
        // This test was already fixed and should be correct.
        assertTrue(consoleOutput.contains("All tasks are complete, or no tasks have been added yet!"), "Should inform the user that the list is empty.");
    }

    @Test
    @DisplayName("Scenario 2.1 & 3.2: Add a Task Successfully and View Populated List")
    void addATaskSuccessfully() {
        String input = "User\n" +      // Profile
                "2\n" +         // Add Task
                "New Title\n" +
                "New Desc\n" +
                "HIGH\n" +
                "1\n" +         // View List
                "0\n";          // Exit
        runAppWithInput(input);
        String consoleOutput = outContent.toString();

        assertAll("Adding and viewing a task",
                // This assertion uses the correct success message with a period.
                () -> assertTrue(consoleOutput.contains("Task added successfully.")),
                () -> assertTrue(consoleOutput.contains("1. [ ] New Title: New Desc (Priority: HIGH)"))
        );
    }

    @Test
    @DisplayName("Scenario 2.2: Attempt to Add Task with Blank Title")
    void attemptAddTaskWithBlankTitle() {
        String input = "User\n" +
                "2\n" +         // Add Task
                "\n" +          // Blank Title
                "Some Desc\n" +
                "LOW\n" +
                "1\n" +         // View List
                "0\n";
        runAppWithInput(input);
        String consoleOutput = outContent.toString();

        assertAll("Attempting to add invalid task",

                () -> assertTrue(consoleOutput.contains("🎉 All tasks are complete, or no tasks have been added yet!\n"), "List should remain empty.")
        );
    }

    @Test
    @DisplayName("Scenario 2.3: Mark a Task as Complete")
    void markATaskAsComplete() {
        String input = "User\n" +
                "2\nNew Task\nDesc\nMEDIUM\n" + // Add a task
                "4\n1\n" +                     // Mark task 1 complete
                "1\n" +                         // View list
                "0\n";
        runAppWithInput(input);
        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("1. [x] New Task: Desc (Priority: MEDIUM)"), "Task status should be [x].");
    }

    @Test
    @DisplayName("Scenario 2.4: Remove a Task")
    void removeATask() {
        String input = "User\n" +
                "2\nTask To Remove\nDesc\nLOW\n" + // Add a task
                "3\n1\n" +                        // Remove task 1
                "1\n" +                          // View list
                "0\n";
        runAppWithInput(input);
        String consoleOutput = outContent.toString();

        assertAll("Removing a task",
                () -> assertTrue(consoleOutput.contains("Task removed successfully.")),
                // This test was already fixed and should be correct.
                () -> assertTrue(consoleOutput.contains("All tasks are complete, or no tasks have been added yet!"), "List should be empty after removal.")
        );
    }

    @Test
    @DisplayName("Scenario 2.5: Edit a Task")
    void editATask() {
        String input = "User\n" +
                "2\nOld Title\nOld Desc\nHIGH\n" + // Add a task
                "6\n1\n" +                      // Edit task 1
                "Updated Title\n" +             // New title
                "Updated Desc\n" +              // New desc
                "1\n" +                         // View list
                "0\n";
        runAppWithInput(input);
        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("1. [ ] Updated Title: Updated Desc (Priority: HIGH)"), "Task details should be updated.");
    }

    @Test
    @DisplayName("Scenario 2.6: Change a Task's Priority")
    void changeATaskPriority() {
        String input = "User\n" +
                "2\nMy Task\nDesc\nHIGH\n" +   // Add a task
                "5\n1\n" +                    // Change priority of task 1
                "LOW\n" +                     // New priority
                "1\n" +                       // View list
                "0\n";
        runAppWithInput(input);
        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("(Priority: LOW)"), "Task priority should be updated to LOW.");
    }

    @Test
    @DisplayName("Scenario 2.7: Attempt Action on Non-Existent Task")
    void attemptActionOnNonExistentTask() {
        String input = "User\n" +
                "2\nMy Task\nDesc\nHIGH\n" +   // Add one task
                "4\n99\n" +                   // Try to complete task 99
                "0\n";
        runAppWithInput(input);
        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("Invalid task number."), "Should show an error for invalid task number.");
    }
}