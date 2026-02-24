package com.contactapp.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PersonTest {
    
    @Test
    public void testPersonConstructorAndGetters() {
        Person person = new Person(
                1,
                "Doe",
                "John",
                "JD",
                "123456",
                "Test Address",
                "john@test.com",
                "2000-01-01"
        );
        
        assertEquals(1, person.getIdperson());
        assertEquals("Doe", person.getLastname());
        assertEquals("John", person.getFirstname());
        assertEquals("JD", person.getNickname());
        assertEquals("123456", person.getPhoneNumber());
        assertEquals("Test Address", person.getAddress());
        assertEquals("john@test.com", person.getEmailAddress());
        assertEquals("2000-01-01", person.getBirthDate());
    }
    
    @Test
    public void testPersonSetters() {
        Person person = new Person();
        person.setIdperson(2);
        person.setLastname("Smith");
        person.setFirstname("Jane");
        person.setNickname("JS");
        person.setPhoneNumber("098765432");
        person.setAddress("Another Address");
        person.setEmailAddress("jane@test.com");
        person.setBirthDate("1995-05-15");
        
        assertEquals(2, person.getIdperson());
        assertEquals("Smith", person.getLastname());
        assertEquals("Jane", person.getFirstname());
        assertEquals("JS", person.getNickname());
        assertEquals("098765432", person.getPhoneNumber());
        assertEquals("Another Address", person.getAddress());
        assertEquals("jane@test.com", person.getEmailAddress());
        assertEquals("1995-05-15", person.getBirthDate());
    }
    
    @Test
    public void testEmptyConstructor() {
        Person person = new Person();
        assertNotNull(person);
    }
    
    @Test
    public void testPersonToString() {
        Person person = new Person(
                1,
                "Doe",
                "John",
                "JD",
                "123456",
                "Test Address",
                "john@test.com",
                "2000-01-01"
        );
        String result = person.toString();
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("John"));
        assertTrue(result.contains("123456"));
    }
}