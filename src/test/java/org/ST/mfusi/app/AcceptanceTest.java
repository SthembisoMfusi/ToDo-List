package org.ST.mfusi.app;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Application Acceptance Tests")
public class AcceptanceTest {

    /**
     * This class tests the full user flow of the To-Do List application.
     * It simulates a user creating a profile, adding tasks, completing them,
     * and viewing the results through console output.
     */
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

    /**
     * This test simulates a full user session: creating a profile, adding a task,
     * viewing it, completing it, viewing it again, and exiting.
     */
    @Test
    @DisplayName("A user should be able to add and complete a task")
    void fullUserFlow_AddAndCompleteTask() {

        String simulatedUserInput = "Alex\n" +
                "2\n" +
                "Finish project report\n" +
                "Complete Q3 summary\n" +
                "HIGH\n" +
                "1\n" +
                "4\n" +
                "1\n" +
                "1\n" +
                "0\n";

        // Redirect System.in to read from our simulated input string
        InputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(in);

        // Act: Run the entire application from start to finish
        Main.main(null);

        // Assert: Check the captured console output for expected results
        String consoleOutput = outContent.toString();

        // Verify that all key messages and state changes appeared in the output
        assertTrue(consoleOutput.contains("Profile for Alex created"), "Profile creation message should be shown.");
        assertTrue(consoleOutput.contains("Task added successfully!"), "Task addition confirmation should be shown.");
        assertTrue(consoleOutput.contains("1. [ ] Finish project report: Complete Q3 summary (Priority: HIGH)"), "The new, incomplete task should be in the list.");
        assertTrue(consoleOutput.contains("Task marked as complete."), "Task completion confirmation should be shown.");
        assertTrue(consoleOutput.contains("1. [x] Finish project report: Complete Q3 summary (Priority: HIGH)"), "The task should be marked as complete in the final list.");
        assertTrue(consoleOutput.contains("Thank you for using the To-Do List App"), "The exit message should be shown.");
    }
}