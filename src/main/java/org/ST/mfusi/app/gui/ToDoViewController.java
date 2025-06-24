package org.ST.mfusi.app.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.ST.mfusi.app.AppController;
import org.ST.mfusi.task.Priority;
import org.ST.mfusi.task.Task;
import java.util.Optional;

public class ToDoViewController {

    @FXML private Label welcomeLabel;
    @FXML private ListView<Task> taskListView;
    @FXML private TextField titleField;
    @FXML private TextField descriptionField;
    @FXML private ComboBox<Priority> priorityComboBox;

    private AppController appController;

    // This method is called by the WelcomeViewController to pass in the user's name
    public void initializeData(String profileName) {
        this.appController = new AppController(profileName);
        welcomeLabel.setText("Tasks for " + profileName);
        updateTaskList();
    }

    @FXML
    public void initialize() {
        // Populate the priority dropdown with all possible values
        priorityComboBox.setItems(FXCollections.observableArrayList(Priority.values()));

        // Set the ListView to display tasks in a user-friendly way
        taskListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setText(null);
                } else {
                    String status = task.isCompleted() ? "[✔]" : "[ ]"; // More graphical checkmark
                    setText(String.format("%s %s: %s (Priority: %s)",
                            status, task.getTitle(), task.getDescription(), task.getPriority()));
                }
            }
        });
    }

    @FXML
    private void handleAddTaskButton() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        Priority priority = priorityComboBox.getValue();

        if (title.isBlank() || description.isBlank() || priority == null) {
            showAlert("Invalid Input", "Title, description, and priority are all required.");
            return;
        }

        appController.addTask(title, description, priority);
        titleField.clear();
        descriptionField.clear();
        priorityComboBox.setValue(null); // Reset dropdown
        updateTaskList();
    }

    @FXML
    private void handleMarkCompleteButton() {
        Task selectedTask = getSelectedTask();
        if (selectedTask != null) {
            appController.markTaskComplete(getTaskIndex(selectedTask) + 1);
            updateTaskList();
        }
    }

    @FXML
    private void handleRemoveTaskButton() {
        Task selectedTask = getSelectedTask();
        if (selectedTask != null) {
            appController.removeTask(getTaskIndex(selectedTask) + 1);
            updateTaskList();
        }
    }

    @FXML
    private void handleChangePriorityButton() {
        Task selectedTask = getSelectedTask();
        if (selectedTask == null) return;

        ChoiceDialog<Priority> dialog = new ChoiceDialog<>(selectedTask.getPriority(), Priority.values());
        dialog.setTitle("Change Priority");
        dialog.setHeaderText("Change priority for task: " + selectedTask.getTitle());
        dialog.setContentText("Choose a new priority:");

        Optional<Priority> result = dialog.showAndWait();
        result.ifPresent(newPriority -> {
            appController.changeTaskPriority(getTaskIndex(selectedTask) + 1, newPriority);
            updateTaskList();
        });
    }

    @FXML
    private void handleEditTaskButton() {
        Task selectedTask = getSelectedTask();
        if (selectedTask == null) return;

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit Task");
        dialog.setHeaderText("Editing task: " + selectedTask.getTitle());

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the layout and fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField editTitle = new TextField(selectedTask.getTitle());
        TextField editDescription = new TextField(selectedTask.getDescription());

        grid.add(new Label("Title:"), 0, 0);
        grid.add(editTitle, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(editDescription, 1, 1);

        dialog.getDialogPane().setContent(grid);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == saveButtonType) {
            appController.editTask(getTaskIndex(selectedTask) + 1, editTitle.getText(), editDescription.getText());
            updateTaskList();
        }
    }

    // --- Helper Methods ---

    private Task getSelectedTask() {
        Task selected = taskListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Task Selected", "Please select a task from the list first.");
        }
        return selected;
    }

    private int getTaskIndex(Task task) {
        return appController.getUserProfile().getTasks().indexOf(task);
    }

    private void updateTaskList() {
        if (appController != null) {
            ObservableList<Task> tasks = FXCollections.observableArrayList(appController.getUserProfile().getTasks());
            taskListView.setItems(tasks);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}