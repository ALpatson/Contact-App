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
 * Controller for updating persons.
 */
public class UpdatePersonController {
    
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
     * Set the person to be edited
     */
    @FXML
    public void initialize() {
        currentPerson = PersonContext.getSelectedPerson();
        populateFields();
    }
    
    /**
     * Populate form fields with current person's data
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
     * Handle save button click - updates the contact in database
     */
    @FXML
    private void handleSave() {
        // Validate inputs
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            showAlert("Validation Error", "First name and last name are required!");
            return;
        }
        
        // Update person object with new values
        currentPerson.setFirstname(firstNameField.getText());
        currentPerson.setLastname(lastNameField.getText());
        currentPerson.setNickname(nicknameField.getText());
        currentPerson.setPhoneNumber(phoneField.getText());
        currentPerson.setAddress(addressField.getText());
        currentPerson.setEmailAddress(emailField.getText());
        currentPerson.setBirthDate(DateUtil.toDbString(birthDatePicker.getValue()));
        
        // Update in database
        dao.updatePerson(currentPerson);
        
        // Show success message
        showAlert("Success", "Contact updated successfully!");
        
        // Go back to main view
        goBackToMainView();
    }
    
    /**
     * Handle cancel button click - goes back to main view without saving
     */
    @FXML
    private void handleCancel() {
        goBackToMainView();
    }
    
    /**
     * Navigate back to main view
     */
    private void goBackToMainView() {
        try {
            com.contactapp.Main.showView("MainWindow");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Show alert dialog
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}