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

        assertEquals("Doe", person.getLastname());
        assertEquals("John", person.getFirstname());
        assertEquals("JD", person.getNickname());
        assertEquals("123456", person.getPhoneNumber());
        assertEquals("Test Address", person.getAddress());
        assertEquals("john@test.com", person.getEmailAddress());
        assertEquals("2000-01-01", person.getBirthDate());
    }
}