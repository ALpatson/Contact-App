package com.contactapp.controller;

import com.contactapp.Main;
import com.contactapp.model.Person;
import com.contactapp.db.PersonDAO;
import com.contactapp.util.PersonContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

/**
 * Controller for the main window.
 * Handles displaying contacts in a table and managing user interactions.
 */
public class MainController {

    @FXML
    private TableView<Person> contactsTable;

    @FXML
    private TableColumn<Person, Integer> idColumn;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private TableColumn<Person, String> nickNameColumn;

    @FXML
    private TableColumn<Person, String> phoneColumn;

    @FXML
    private TableColumn<Person, String> emailColumn;

    @FXML
    private TableColumn<Person, String> addressColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button refreshButton;
    
    private PersonDAO dao = new PersonDAO();

    /**
     * Initialize the controller and set up the table columns.
     * This method is automatically called after the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        // Set up ONLY the visible table columns
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Load data from database
        loadContactsFromDatabase();
    }

    /**
     * Load contacts from the database and display in table.
     */
    private void loadContactsFromDatabase() {
        try {
            List<Person> contacts = dao.getAllPersons();
            ObservableList<Person> observableContacts = FXCollections.observableArrayList(contacts);
            contactsTable.setItems(observableContacts);
        } catch (Exception e) {
            System.err.println("Error loading contacts: " + e.getMessage());
            loadSampleData();  // Fall back to sample data if database fails
        }
    }

    /**
     * Load sample data into the table for testing purposes.
     * Used as fallback if database is unavailable.
     */
    private void loadSampleData() {
        ObservableList<Person> contacts = FXCollections.observableArrayList();

        // Sample data - this will be replaced with database data
        Person person1 = new Person();
        person1.setIdperson(1);
        person1.setFirstname("John");
        person1.setLastname("Doe");
        person1.setNickname("JD");
        person1.setPhoneNumber("123-456-7890");
        person1.setEmailAddress("john@example.com");
        person1.setAddress("123 Main St");

        Person person2 = new Person();
        person2.setIdperson(2);
        person2.setFirstname("Jane");
        person2.setLastname("Smith");
        person2.setNickname("JS");
        person2.setPhoneNumber("098-765-4321");
        person2.setEmailAddress("jane@example.com");
        person2.setAddress("456 Oak Ave");

        contacts.add(person1);
        contacts.add(person2);

        contactsTable.setItems(contacts);
    }
    
    /**
     * Handle the View Details button click.
     * Shows all contact information.
     */
    @FXML
    private void handleViewDetails() {
        Person selected = contactsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a contact to view details.");
            return;
        }

        String details = "Contact Details:\n\n" +
                "ID: " + selected.getIdperson() + "\n" +
                "First Name: " + selected.getFirstname() + "\n" +
                "Last Name: " + selected.getLastname() + "\n" +
                "Nickname: " + selected.getNickname() + "\n" +
                "Phone: " + selected.getPhoneNumber() + "\n" +
                "Email: " + selected.getEmailAddress() + "\n" +
                "Address: " + selected.getAddress() + "\n" +
                "Birth Date: " + (selected.getBirthDate() != null ? selected.getBirthDate() : "N/A");

        showAlert("Contact Details", details);
    }

    /**
     * Handle the Add button click.
     * Opens the add person form.
     */
    @FXML
    private void handleAddContact() {
        try {
            Main.showView("AddPersonForm");
        } catch (Exception e) {
            showAlert("Error", "Failed to open add contact form: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handle the Edit button click.
     * Opens the edit form with selected contact's data.
     */
    @FXML
    private void handleEditContact() {
        Person selected = contactsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a contact to edit.");
            return;
        }
        
        try {
            // Load the update form
            PersonContext.setSelectedPerson(selected);
            Main.showView("UpdatePersonForm");
            
        } catch (Exception e) {
            showAlert("Error", "Failed to open edit contact form: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handle the Delete button click.
     * Deletes the selected contact from the database.
     */
    @FXML
    private void handleDeleteContact() {
        Person selected = contactsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a contact to delete.");
            return;
        }
        
        try {
            // Delete from database
            dao.deletePerson(selected.getIdperson());
            
            // Show success message
            showAlert("Success", "Contact deleted: " + selected.getFirstname() + " " + selected.getLastname());
            
            // Refresh the table
            loadContactsFromDatabase();
            
        } catch (Exception e) {
            showAlert("Error", "Failed to delete contact: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handle the Refresh button click.
     * Reloads the contact list from the database.
     */
    @FXML
    private void handleRefresh() {
        try {
            loadContactsFromDatabase();
            showAlert("Success", "Contact list refreshed!");
        } catch (Exception e) {
            showAlert("Error", "Failed to refresh contacts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Display an alert dialog to the user.
     *
     * @param title The title of the alert
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