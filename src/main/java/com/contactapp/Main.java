package com.contactapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.URL;
import com.contactapp.db.DatabaseInitializer;

/**
 * Main entry point for the Contact App.
 * Initializes the database and launches the JavaFX application.
 */
public class Main extends Application {
    private static BorderPane root;
    private static Scene scene;

    /**
     * Initialize the application before showing the window.
     * Sets up the SQLite database and creates necessary tables.
     * @throws java.lang.Exception
     */
    @Override
    public void init() throws Exception {
        DatabaseInitializer.initializeDatabase();
    }

    /**
     * Start the JavaFX application and display the main window.
     * @param primaryStage
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the main layout from FXML
            root = loadFXML("MainLayout");
            
            // Create a scene with the layout (640x480 window size)
            scene = new Scene(root, 640, 480);
            
            // Apply CSS styling to the scene
            URL cssLocation = Main.class.getResource("/com/contactapp/view/styles.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            }
            
            // Setup the window and show it
            primaryStage.setTitle("Contact App");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            // Display the main contact list view
            Main.showView("MainWindow");
            
        } catch (IOException e) {
            System.err.println("Error starting application: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Load FXML files from the view package.
     * This method simplifies loading FXML files by automatically pointing to the correct path.
     * 
     * @param fxml The name of the FXML file (without .fxml extension)
     * @return The loaded FXML content
     * @throws IOException If the file cannot be found or loaded
     */
    private static <T> T loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
            Main.class.getResource("/com/contactapp/view/" + fxml + ".fxml")
        );
        return fxmlLoader.load();
    }

    /**
     * Application entry point.
     * Launches the JavaFX application.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Switch the center view to display different pages (MainWindow, AddPersonForm, etc).
     * 
     * @param viewName The name of the view to display
     */
    public static void showView(String viewName) {
        try {
            root.setCenter(loadFXML(viewName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}