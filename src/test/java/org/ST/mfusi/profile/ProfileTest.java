package org.ST.mfusi.profile;

import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;
import org.ST.mfusi.todolist.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    private Profile profile;

    @BeforeEach
    void setUp() {
        profile = new Profile("Test User", "test@example.com");
    }

    @Test
    @DisplayName("Constructor with name only should create profile with empty email")
    void constructor_withNameOnly_shouldCreateProfile() {
        Profile p = new Profile("JustName");
        assertEquals("JustName", p.getName());
        assertEquals("", p.getEmail());
        assertNotNull(p.getTasks());
        assertTrue(p.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Constructor with name and email should set all properties")
    void constructor_withAllInfo_shouldCreateProfile() {
        assertEquals("Test User", profile.getName());
        assertEquals("test@example.com", profile.getEmail());
        assertNotNull(profile.getTasks());
    }

    @Test
    @DisplayName("setName should update the profile's name")
    void setName_shouldUpdateName() {
        profile.setName("New Name");
        assertEquals("New Name", profile.getName());
    }

    @Test
    @DisplayName("setEmail should update the profile's email")
    void setEmail_shouldUpdateEmail() {
        profile.setEmail("new@email.com");
        assertEquals("new@email.com", profile.getEmail());
    }

    @Test
    @DisplayName("addTask should add a task to the profile's list")
    void addTask_shouldIncreaseTaskCount() {
        assertTrue(profile.getTasks().isEmpty());
        profile.addTask("My Title", "My Description", Priority.HIGH);
        assertEquals(1, profile.getTasks().size());
        Task addedTask = profile.getTasks().get(0);
        assertEquals("My Title", addedTask.getTitle());
    }

    @Test
    @DisplayName("removeTask should succeed for a valid task number")
    void removeTask_shouldSucceedForValidNumber() {
        profile.addTask("Task 1", "Desc 1", Priority.LOW);
        profile.addTask("Task 2", "Desc 2", Priority.HIGH);
        assertEquals(2, profile.getTasks().size());

        boolean result = profile.removeTask(1);

        assertTrue(result);
        assertEquals(1, profile.getTasks().size());
        // Verify the correct task was removed
        assertEquals("Task 2", profile.getTasks().get(0).getTitle());
    }

    @Test
    @DisplayName("removeTask should fail for an invalid task number")
    void removeTask_shouldFailForInvalidNumber() {
        profile.addTask("Task 1", "Desc 1", Priority.LOW);

        boolean resultTooHigh = profile.removeTask(2);
        boolean resultZero = profile.removeTask(0);

        assertFalse(resultTooHigh);
        assertFalse(resultZero);
        assertEquals(1, profile.getTasks().size());
    }

    @Test
    @DisplayName("markTaskComplete should succeed for a valid task number")
    void markTaskComplete_shouldSucceedForValidNumber() {
        profile.addTask("Task to complete", "Desc", Priority.MEDIUM);
        assertFalse(profile.getTasks().get(0).isCompleted());

        boolean result = profile.markTaskComplete(1);

        assertTrue(result);
        assertTrue(profile.getTasks().get(0).isCompleted());
    }

    @Test
    @DisplayName("markTaskComplete should fail for an invalid task number")
    void markTaskComplete_shouldFailForInvalidNumber() {
        profile.addTask("Task to complete", "Desc", Priority.MEDIUM);

        boolean result = profile.markTaskComplete(99);

        assertFalse(result);
        assertFalse(profile.getTasks().get(0).isCompleted());
    }

    @Test
    @DisplayName("getFormattedToDoList should show empty message for new profile")
    void getFormattedToDoList_whenEmpty() {
        String output = profile.getFormattedToDoList();
        assertTrue(output.contains("All tasks are complete"));
    }

    @Test
    @DisplayName("getFormattedToDoList should correctly format tasks")
    void getFormattedToDoList_withTasks() {
        profile.addTask("Test Title", "My Test Desc", Priority.HIGH);
        profile.markTaskComplete(1);
        profile.addTask("Another Task", "Another Desc", Priority.LOW);

        String output = profile.getFormattedToDoList();

        // Check for first completed task
        assertTrue(output.contains("1. [x] Test Title: My Test Desc (Priority: HIGH)"));
        // Check for second incomplete task
        assertTrue(output.contains("2. [ ] Another Task: Another Desc (Priority: LOW)"));
    }

    @Test
    @DisplayName("toString should return a simple representation of the profile")
    void testToString() {
        String output = profile.toString();
        assertTrue(output.contains("Profile[name='Test User'"));
        assertTrue(output.contains("email='test@example.com'"));
        assertFalse(output.contains("toDoList")); // Should not include the full list
    }

    @Test
    @DisplayName("equals and hashCode should be based on name and email only")
    void testEqualsAndHashCode() {
        Profile profile1 = new Profile("Same Name", "same@email.com");
        Profile profile2 = new Profile("Same Name", "same@email.com");
        Profile profile3 = new Profile("Different Name", "same@email.com");
        Profile profile4 = new Profile("Same Name", "different@email.com");

        // Test equals contract
        assertEquals(profile1, profile2);
        assertNotEquals(profile1, profile3);
        assertNotEquals(profile1, profile4);
        assertEquals(profile1, profile1);
        assertNotEquals(null, profile1);

        // Test hashCode contract
        assertEquals(profile1.hashCode(), profile2.hashCode());
        assertNotEquals(profile1.hashCode(), profile3.hashCode());

        // IMPORTANT: Verify that task list does NOT affect equality
        profile1.addTask("A Task", "Desc", Priority.MEDIUM);
        assertEquals(profile1, profile2, "Adding a task should not affect profile equality");
        assertEquals(profile1.hashCode(), profile2.hashCode(), "Hash code should not change when a task is added");
    }
}