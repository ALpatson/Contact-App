package com.contactapp.db;

import com.contactapp.model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for Person entity.
 * Handles all database operations: retrieve, create, update, and delete contacts.
 */
public class PersonDAO {
    
    /**
     * Retrieve all contacts from the database.
     * 
     * @return List of all Person objects, or empty list if database error
     */
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            // Loop through each row and create Person objects
            while (rs.next()) {
                Person p = new Person(
                        rs.getInt("idperson"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getString("nickname"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("email_address"),
                        rs.getString("birth_date")
                );
                persons.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }
    
    /**
     * Add a new contact to the database.
     * Automatically generates and sets the contact ID.
     * 
     * @param person The contact to save
     */
    public void insertPerson(Person person) {
        String sql = """
            INSERT INTO person (lastname, firstname, nickname, phone_number, address, email_address, birth_date)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Set all contact fields in the SQL statement
            stmt.setString(1, person.getLastname());
            stmt.setString(2, person.getFirstname());
            stmt.setString(3, person.getNickname());
            stmt.setString(4, person.getPhoneNumber());
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getEmailAddress());
            stmt.setString(7, person.getBirthDate());
            
            // Execute the insert
            stmt.executeUpdate();
            
            // Get the auto-generated ID and set it on the Person object
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    person.setIdperson(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Update an existing contact in the database.
     * 
     * @param person The contact with updated information
     */
    public void updatePerson(Person person) {
        String sql = """
            UPDATE person
            SET lastname=?, firstname=?, nickname=?,
                phone_number=?, address=?, email_address=?, birth_date=?
            WHERE idperson=?
        """;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set all contact fields with new values
            stmt.setString(1, person.getLastname());
            stmt.setString(2, person.getFirstname());
            stmt.setString(3, person.getNickname());
            stmt.setString(4, person.getPhoneNumber());
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getEmailAddress());
            stmt.setString(7, person.getBirthDate());
            stmt.setInt(8, person.getIdperson());  // WHERE clause - identify which contact to update
            
            // Execute the update
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete a contact from the database.
     * 
     * @param id The ID of the contact to delete
     */
    public void deletePerson(int id) {
        String sql = "DELETE FROM person WHERE idperson=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the contact ID to delete
            stmt.setInt(1, id);
            
            // Execute the delete
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}