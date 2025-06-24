package org.ST.mfusi.app.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class WelcomeViewController {

    @FXML
    private TextField nameField;

    @FXML
    private void handleStartButton() {
        String profileName = nameField.getText();
        if (profileName == null || profileName.isBlank()) {
            showAlert("Name Required", "Please enter your name to continue.");
            return;
        }

        try {
            // Load the main ToDoView FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ToDoView.fxml"));
            Parent root = loader.load();


            ToDoViewController toDoViewController = loader.getController();

            toDoViewController.initializeData(profileName);


            Stage mainStage = new Stage();
            mainStage.setTitle("To-Do List for " + profileName);
            mainStage.setScene(new Scene(root, 800, 600));
            mainStage.show();

            // Close the current welcome window
            Stage welcomeStage = (Stage) nameField.getScene().getWindow();
            welcomeStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load the main application window.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}