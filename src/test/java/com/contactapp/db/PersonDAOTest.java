package com.contactapp.db;

import com.contactapp.model.Person;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDAOTest {

    @Test
    public void testInsertAndRetrievePerson() {

        PersonDAO dao = new PersonDAO();

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

        dao.insertPerson(p);

        List<Person> persons = dao.getAllPersons();

        assertFalse(persons.isEmpty());
    }
}