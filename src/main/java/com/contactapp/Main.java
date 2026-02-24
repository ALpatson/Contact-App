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
 * Main application entry point for the Contact App.
 * Loads the main window and initializes the JavaFX application.
 */
public class Main extends Application {

    private static BorderPane root;
    private static Scene scene;

    @Override
       public void init() throws Exception {
           DatabaseInitializer.initialize();
           }
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the MainWindow.fxml file from the view package
            root = loadFXML("MainLayout");
            
            // Create the scene with the loaded FXML
            scene = new Scene(root, 640, 480);
            
            // Load the CSS stylesheet from the view package
            URL cssLocation = Main.class.getResource("/com/contactapp/view/styles.css");
            if (cssLocation != null) {
                scene.getStylesheets().add(cssLocation.toExternalForm());
            }
            
            // Set up the primary stage (window)
            primaryStage.setTitle("Contact App");
            primaryStage.setScene(scene);
            primaryStage.show();
            Main.showView("MainWindow");
            
        } catch (IOException e) {
            System.err.println("Error starting application: " + e.getMessage());
            throw e;
        }
    }
    
    private static <T> T loadFXML(String fxml) throws IOException {
		// As we will use this method a lot, and we have all our view in a specific
		// package, let's put it there to save some typing :)
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/contactapp/view/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

    public static void main(String[] args) {
        launch(args);
    }
    
    

    public static void showView(String viewName) {
        try {
            root.setCenter(loadFXML(viewName));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
}
}