package org.ST.mfusi.app;

import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main entry point for the To-Do List application.
 * This class handles all user interface interactions (console input/output)
 * and delegates application logic to an AppController instance.
 * @author Sthembiso Mfusi
 * @version 1.0
 * @since 2025-06-24
 */
public class Main {

    /**
     * The main entry point of the application. Initializes the application,
     * creates the user profile, and runs the main menu loop.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to your To-Do List Application!");
        System.out.print("Please enter your name to create a profile: ");
        String name = scanner.nextLine();
        AppController app = new AppController(name);

        System.out.println("\nProfile for " + name + " created. Let's get started!\n");


        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> System.out.println(app.getFormattedToDoList());
                    case 2 -> handleAddTask(scanner, app);
                    case 3 -> handleRemoveTask(scanner, app);
                    case 4 -> handleMarkTaskComplete(scanner, app);
                    case 5 -> handleChangeTaskPriority(scanner, app);
                    case 6 -> handleEditTask(scanner, app);
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
            System.out.println();
        }


        System.out.println("Thank you for using the To-Do List App. Goodbye!");
        scanner.close();
    }

    /**
     * Displays the main menu of options to the user's console.
     */
    private static void displayMenu() {
        System.out.println("--- Main Menu ---");
        System.out.println("1. View To-Do List");
        System.out.println("2. Add New Task");
        System.out.println("3. Remove a Task");
        System.out.println("4. Mark a Task as Complete");
        System.out.println("5. Change a Task's Priority");
        System.out.println("6. Edit a Task");
        System.out.println("0. Exit");
        System.out.println("-----------------");
    }


    /**
     * Handles the logic for adding a new task to the user's to-do list.
     * Prompts the user for task details such as title, description, and priority.
     * Validates the input and delegates the task creation to the {@code AppController}.
     *
     * @param scanner the Scanner instance for reading user input
     * @param app the {@code AppController} instance that manages the application logic
     */
    private static void handleAddTask(Scanner scanner, AppController app) {
        System.out.println("\n--- Add New Task ---");
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Priority priority = null;
        while (priority == null) {
            System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
            try {
                priority = Priority.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Please try again.");
            }
        }

        // Delegate the work to the controller and print the result
        String result = app.addTask(title, description, priority);
        System.out.println(result);
    }
    /**
     * Handles the logic for removing a task from the user's to-do list.
     * Displays the current list of tasks and prompts the user for the task number to remove.
     * Validates the input and delegates the removal to the {@code AppController}.
     *
     * @param scanner the Scanner instance for reading user input
     * @param app the {@code AppController} instance that manages the application logic
     */
    private static void handleRemoveTask(Scanner scanner, AppController app) {
        if (isListEmpty(app)) return;
        System.out.println(app.getFormattedToDoList());

        System.out.print("Enter the number of the task to remove: ");
        try {
            int taskNumber = scanner.nextInt();
            scanner.nextLine();
            String result = app.removeTask(taskNumber);
            System.out.println(result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    /**
     * Handles the logic for marking a task as complete in the user's to-do list.
     * Displays the current list of tasks and prompts the user for the task number to mark as complete.
     * Validates the input and delegates the completion marking to the {@code AppController}.
     *
     * @param scanner the Scanner instance for reading user input
     * @param app the {@code AppController} instance that manages the application logic
     */
    private static void handleMarkTaskComplete(Scanner scanner, AppController app) {
        if (isListEmpty(app)) return;
        System.out.println(app.getFormattedToDoList());

        System.out.print("Enter the number of the task to mark as complete: ");
        try {
            int taskNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String result = app.markTaskComplete(taskNumber);
            System.out.println(result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    /**
     * Handles the logic for changing the priority of a {@code task} in the user's to-do list.
     * Displays the current list of tasks and prompts the user for the task number and new priority.
     * Validates the input and delegates the priority change to the {@code AppController}.
     *
     * @param scanner the Scanner instance for reading user input
     * @param app the {@code AppController} instance that manages the application logic
     */
    private static void handleChangeTaskPriority(Scanner scanner, AppController app) {
        if (isListEmpty(app)) return;
        System.out.println(app.getFormattedToDoList());

        try {
            System.out.print("Enter the number of the task to change: ");
            int taskNumber = scanner.nextInt();
            scanner.nextLine();

            // Check if task number is valid before asking for more input
            if (taskNumber <= 0 || taskNumber > app.getUserProfile().getTasks().size()) {
                System.out.println("Invalid task number.");
                return;
            }

            Priority newPriority = null;
            while (newPriority == null) {
                System.out.print("Enter new priority (HIGH, MEDIUM, LOW): ");
                try {
                    newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid priority. Please try again.");
                }
            }

            String result = app.changeTaskPriority(taskNumber, newPriority);
            System.out.println(result);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    /**
     * Handles the user interaction for editing an existing task.
     * Prompts the user to select a task and enter a new title and description.
     * Delegates the actual modification to the AppController.
     *
     * @param scanner The Scanner instance to read user input.
     * @param app     The AppController instance managing application logic.
     */
    private static void handleEditTask(Scanner scanner, AppController app) {
        if (isListEmpty(app)) return;
        System.out.println(app.getFormattedToDoList());

        try {
            System.out.print("Enter the number of the task to edit: ");
            int taskNumber = scanner.nextInt();
            scanner.nextLine();

            // Check if task number is valid before asking for more input
            if (taskNumber <= 0 || taskNumber > app.getUserProfile().getTasks().size()) {
                System.out.println("Invalid task number.");
                return;
            }

            Task task = app.getUserProfile().getTasks().get(taskNumber - 1);

            System.out.printf("Current title: %s\n", task.getTitle());
            System.out.print("Enter new title (or press Enter to keep current): ");
            String newTitle = scanner.nextLine();

            System.out.printf("Current description: %s\n", task.getDescription());
            System.out.print("Enter new description (or press Enter to keep current): ");
            String newDescription = scanner.nextLine();

            String result = app.editTask(taskNumber, newTitle, newDescription);
            System.out.println(result);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    /**
     * Checks if the user's to-do list is empty and prints a message if it is.
     * This is a helper method to avoid repetitive code in the handler methods.
     *
     * @param app The AppController instance.
     * @return {@code true} if the list is empty, {@code false} otherwise.
     */
    private static boolean isListEmpty(AppController app) {
        if (app.getUserProfile().getTasks().isEmpty()) {
            System.out.println("\nYour to-do list is empty. Please add a task first.");
            return true;
        }
        return false;
    }
}