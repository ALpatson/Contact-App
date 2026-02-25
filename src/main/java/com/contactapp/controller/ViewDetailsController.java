/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contactapp.controller;

import com.contactapp.Main;
import com.contactapp.model.Person;
import com.contactapp.db.PersonDAO;
import com.contactapp.util.PersonContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller for viewing person details.
 */
public class ViewDetailsController {
    
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
            birthDateField.setText(currentPerson.getBirthDate());
        }
    }
    
    @FXML
    private void handleEdit() {
            Main.showView("UpdatePersonForm");     
    }
    

    /**
     * Handle cancel button click - goes back to main view without saving
     */
    @FXML
    private void handleBack() {
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
}
    