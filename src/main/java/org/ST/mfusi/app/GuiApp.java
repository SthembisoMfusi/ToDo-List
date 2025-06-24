package org.ST.mfusi.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the WELCOME view first
        Parent root = FXMLLoader.load(getClass().getResource("/views/WelcomeView.fxml"));

        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}