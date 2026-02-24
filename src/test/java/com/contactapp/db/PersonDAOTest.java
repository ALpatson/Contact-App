package com.contactapp.db;

import com.contactapp.model.Person;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Tests for PersonDAO class.
 * Tests CRUD operations for Person objects.
 */
public class PersonDAOTest {
    
    private PersonDAO dao;
    
    /**
     * Set up test environment.
     * Initialize database and create PersonDAO instance before each test.
     */
    @Before
    public void setUp() {
        // Initialize the database and create tables BEFORE each test
        DatabaseInitializer.initializeDatabase();
        dao = new PersonDAO();
    }
    
    /**
     * Test inserting a person and retrieving all persons.
     */
    @Test
    public void testInsertAndRetrievePerson() {
        Person p = new Person(
                0,
                "Doe",
                "John",
                "JD",
                "123456",
                "Test Address",
                "john@test.com",
                "2000-01-01"
        );
        
        // Insert the person
        dao.insertPerson(p);
        
        // Retrieve all persons
        List<Person> persons = dao.getAllPersons();
        
        // Assert that the list is not empty
        assertFalse("Persons list should not be empty after insert", persons.isEmpty());
        
        // Assert that we have at least one person
        assertTrue("Should have at least one person", persons.size() > 0);
    }
    
    /**
     * Test retrieving all persons.
     */
    @Test
    public void testGetAllPersons() {
        List<Person> persons = dao.getAllPersons();
        assertNotNull("Persons list should not be null", persons);
    }
}