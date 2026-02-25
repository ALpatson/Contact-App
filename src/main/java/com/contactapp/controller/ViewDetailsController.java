package com.contactapp.controller;

import com.contactapp.Main;
import com.contactapp.model.Person;
import com.contactapp.db.PersonDAO;
import com.contactapp.util.PersonContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for displaying full contact details.
 * Shows all contact information in read-only format.
 */
public class ViewDetailsController {
    
    // Display labels for contact information (read-only)
    @FXML
    private Label firstNameField;
    
    @FXML
    private Label lastNameField;
    
    @FXML
    private Label nicknameField;
    
    @FXML
    private Label phoneField;
    
    @FXML
    private Label addressField;
    
    @FXML
    private Label emailField;
    
    @FXML
    private Label birthDateField;
    
    private PersonDAO dao = new PersonDAO();
    private Person currentPerson;
    
    /**
     * Initialize the view when opened.
     * Retrieve the selected contact and display all details.
     */
    @FXML
    public void initialize() {
        // Get the contact passed from the main window
        currentPerson = PersonContext.getSelectedPerson();
        
        // Display the contact's information
        populateFields();
    }
    
    /**
     * Display all contact information in the detail view.
     */
    private void populateFields() {
        if (currentPerson != null) {
            firstNameField.setText(currentPerson.getFirstname());
            lastNameField.setText(currentPerson.getLastname());
            nicknameField.setText(currentPerson.getNickname());
            phoneField.setText(currentPerson.getPhoneNumber());
            addressField.setText(currentPerson.getAddress());
            emailField.setText(currentPerson.getEmailAddress());
            birthDateField.setText(currentPerson.getBirthDate());
        }
    }
    
    /**
     * Open the edit form to modify this contact.
     */
    @FXML
    private void handleEdit() {
        Main.showView("UpdatePersonForm");
    }
    
    /**
     * Close the details view and return to main contact list.
     */
    @FXML
    private void handleBack() {
        goBackToMainView();
    }
    
    /**
     * Return to the main contact list view.
     */
    private void goBackToMainView() {
        try {
            com.contactapp.Main.showView("MainWindow");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}