package com.contactapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Main application entry point for the Contact App.
 * Loads the main window and initializes the JavaFX application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the MainWindow.fxml file from the view package
            FXMLLoader loader = new FXMLLoader();
            URL fxmlLocation = Main.class.getResource("/com/contactapp/view/MainWindow.fxml");
            
            if (fxmlLocation == null) {
                throw new RuntimeException("Cannot find MainWindow.fxml at /com/contactapp/view/MainWindow.fxml");
            }
            
            loader.setLocation(fxmlLocation);
            BorderPane root = loader.load();
            
            // Create the scene with the loaded FXML
            Scene scene = new Scene(root, 1000, 600);
            
            // Load the CSS stylesheet from the view package
            URL cssLocation = Main.class.getResource("/com/contactapp/view/styles.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            }
            
            // Set up the primary stage (window)
            primaryStage.setTitle("Contact App");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error starting application: " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}