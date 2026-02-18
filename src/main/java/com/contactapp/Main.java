package com.contactapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main application entry point for the Contact App.
 * Loads the main window and initializes the JavaFX application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the MainWindow.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        BorderPane root = loader.load();

        // Create the scene with the loaded FXML
        Scene scene = new Scene(root, 1000, 600);
        
        // Load the CSS stylesheet
        String css = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        // Set up the primary stage (window)
        primaryStage.setTitle("Contact App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}