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
 * Controller for the main contact list window.
 * Displays all contacts in a table and handles user actions (add, edit, delete, view).
 */
public class MainController {

    // Table and column components
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

    // Action buttons
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
     * Initialize the table and load contacts when the window opens.
     */
    @FXML
    public void initialize() {
        // Link table columns to Person object properties
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Load all contacts from database
        loadContactsFromDatabase();
    }

    /**
     * Fetch all contacts from the database and populate the table.
     * If database fails, show sample data as fallback.
     */
    private void loadContactsFromDatabase() {
        try {
            List<Person> contacts = dao.getAllPersons();
            ObservableList<Person> observableContacts = FXCollections.observableArrayList(contacts);
            contactsTable.setItems(observableContacts);
        } catch (Exception e) {
            System.err.println("Error loading contacts: " + e.getMessage());
            loadSampleData();  // Show sample data if database unavailable
        }
    }

    /**
     * Load test contacts for demonstration purposes.
     * Used as backup if database connection fails.
     */
    private void loadSampleData() {
        ObservableList<Person> contacts = FXCollections.observableArrayList();

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
     * Open the details view for the selected contact.
     */
    @FXML
    private void handleViewDetails() {
        Person selected = contactsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a contact to view details.");
            return;
        }

        try {
            // Pass selected contact to the details view
            PersonContext.setSelectedPerson(selected);
            Main.showView("ViewPersonDetails");
        } catch (Exception e) {
            showAlert("Error", "Failed to open contact details: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Open the form to add a new contact.
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
     * Open the edit form with the selected contact's information.
     */
    @FXML
    private void handleEditContact() {
        Person selected = contactsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a contact to edit.");
            return;
        }
        
        try {
            // Pass selected contact to the edit form
            PersonContext.setSelectedPerson(selected);
            Main.showView("UpdatePersonForm");
        } catch (Exception e) {
            showAlert("Error", "Failed to open edit form: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Delete the selected contact from the database.
     */
    @FXML
    private void handleDeleteContact() {
        Person selected = contactsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a contact to delete.");
            return;
        }
        
        try {
            // Remove contact from database
            dao.deletePerson(selected.getIdperson());
            
            // Notify user and refresh table
            showAlert("Success", "Contact deleted: " + selected.getFirstname() + " " + selected.getLastname());
            loadContactsFromDatabase();
        } catch (Exception e) {
            showAlert("Error", "Failed to delete contact: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Refresh the contact list from the database.
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
     * Display a popup message to the user.
     * 
     * @param title The title of the message box
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