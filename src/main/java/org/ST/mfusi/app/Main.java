package org.ST.mfusi.app;

import org.ST.mfusi.profile.Profile;
import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Profile userProfile;

    public static void main(String[] args) {

        System.out.println("Welcome to your To-Do List Application!");
        System.out.print("Please enter your name to create a profile: ");
        String name = scanner.nextLine();
        userProfile = new Profile(name);
        System.out.println("\nProfile for " + name + " created. Let's get started!\n");


        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> viewToDoList();
                    case 2 -> handleAddTask();
                    case 3 -> handleRemoveTask();
                    case 4 -> handleMarkTaskComplete();
                    case 5 -> handleChangeTaskPriority();
                    case 6 -> handleEditTask();
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

    private static void viewToDoList() {
        System.out.println(userProfile.getFormattedToDoList());
    }

    private static void handleAddTask() {
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

        userProfile.addTask(title, description, priority);
        System.out.println("Task added successfully!");
    }

    private static void handleRemoveTask() {
        if (isListEmpty()) return;
        viewToDoList();
        System.out.print("Enter the number of the task to remove: ");
        try {
            int taskNumber = scanner.nextInt();
            scanner.nextLine();
            if (userProfile.removeTask(taskNumber)) {
                System.out.println("Task removed successfully.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void handleMarkTaskComplete() {
        if (isListEmpty()) return;
        viewToDoList();
        System.out.print("Enter the number of the task to mark as complete: ");
        try {
            int taskNumber = scanner.nextInt();
            scanner.nextLine();
            if (userProfile.markTaskComplete(taskNumber)) {
                System.out.println("Task marked as complete.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void handleChangeTaskPriority() {
        if (isListEmpty()) return;
        viewToDoList();
        System.out.print("Enter the number of the task to change: ");
        try {
            int taskNumber = scanner.nextInt();
            scanner.nextLine();

            if (taskNumber > 0 && taskNumber <= userProfile.getTasks().size()) {
                Task task = userProfile.getTasks().get(taskNumber - 1);

                Priority newPriority = null;
                while (newPriority == null) {
                    System.out.print("Enter new priority (HIGH, MEDIUM, LOW): ");
                    try {
                        newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
                        task.setPriority(newPriority);
                        System.out.println("Priority updated successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid priority. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void handleEditTask() {
        if (isListEmpty()) return;
        viewToDoList();
        System.out.print("Enter the number of the task to edit: ");
        try {
            int taskNumber = scanner.nextInt();
            scanner.nextLine();

            if (taskNumber > 0 && taskNumber <= userProfile.getTasks().size()) {
                Task task = userProfile.getTasks().get(taskNumber - 1);

                System.out.printf("Current title: %s\n", task.getTitle());
                System.out.print("Enter new title (or press Enter to keep current): ");
                String newTitle = scanner.nextLine();
                if (!newTitle.isBlank()) {
                    task.setTitle(newTitle);
                }

                System.out.printf("Current description: %s\n", task.getDescription());
                System.out.print("Enter new description (or press Enter to keep current): ");
                String newDescription = scanner.nextLine();
                if (!newDescription.isBlank()) {
                    task.setDescription(newDescription);
                }

                System.out.println("Task updated successfully.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static boolean isListEmpty() {
        if (userProfile.getTasks().isEmpty()) {
            System.out.println("Your to-do list is empty. Please add a task first.");
            return true;
        }
        return false;
    }
}