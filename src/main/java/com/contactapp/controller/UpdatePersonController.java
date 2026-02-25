package com.contactapp.controller;

import com.contactapp.model.Person;
import com.contactapp.db.PersonDAO;
import com.contactapp.util.DateUtil;
import com.contactapp.util.PersonContext;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

/**
 * Controller for the Edit Contact form.
 * Handles updating existing contact information in the database.
 */
public class UpdatePersonController {
    
    // Form input fields
    @FXML
    private TextField firstNameField;
    
    @FXML
    private TextField lastNameField;
    
    @FXML
    private TextField nicknameField;
    
    @FXML
    private TextField phoneField;
    
    @FXML
    private TextField addressField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private DatePicker birthDatePicker;
    
    private PersonDAO dao = new PersonDAO();
    private Person currentPerson;
    
    /**
     * Initialize the form when opened.
     * Retrieve the selected contact and populate all fields with existing data.
     */
    @FXML
    public void initialize() {
        // Get the contact passed from the main window
        currentPerson = PersonContext.getSelectedPerson();
        
        // Fill the form with the contact's current information
        populateFields();
    }
    
    /**
     * Fill all form fields with the current contact's data.
     */
    private void populateFields() {
        if (currentPerson != null) {
            firstNameField.setText(currentPerson.getFirstname());
            lastNameField.setText(currentPerson.getLastname());
            nicknameField.setText(currentPerson.getNickname());
            phoneField.setText(currentPerson.getPhoneNumber());
            addressField.setText(currentPerson.getAddress());
            emailField.setText(currentPerson.getEmailAddress());
            birthDatePicker.setValue(DateUtil.fromDbString(currentPerson.getBirthDate()));
        }
    }
    
    /**
     * Save the updated contact information to the database.
     */
    @FXML
    private void handleSave() {
        // Verify required fields are filled
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            showAlert("Validation Error", "First name and last name are required!");
            return;
        }
        
        // Update the contact object with new values from form
        currentPerson.setFirstname(firstNameField.getText());
        currentPerson.setLastname(lastNameField.getText());
        currentPerson.setNickname(nicknameField.getText());
        currentPerson.setPhoneNumber(phoneField.getText());
        currentPerson.setAddress(addressField.getText());
        currentPerson.setEmailAddress(emailField.getText());
        currentPerson.setBirthDate(DateUtil.toDbString(birthDatePicker.getValue()));
        
        // Save changes to database
        dao.updatePerson(currentPerson);
        
        // Confirm success to user
        showAlert("Success", "Contact updated successfully!");
        
        // Return to main contact list
        goBackToMainView();
    }
    
    /**
     * Cancel editing and discard any changes.
     */
    @FXML
    private void handleCancel() {
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
    
    /**
     * Display a popup message to the user.
     * 
     * @param title The title of the alert window
     * @param message The message to display
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}