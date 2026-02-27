package com.contactapp.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    @Test
    public void testPersonConstructorAndGetters() {
        Person person = new Person(1, "Doe", "John", "JD", "123456", "Test Address", "john@test.com", "2000-01-01");

        assertThat(person.getIdperson()).isEqualTo(1);
        assertThat(person.getLastname()).isEqualTo("Doe");
        assertThat(person.getFirstname()).isEqualTo("John");
        assertThat(person.getNickname()).isEqualTo("JD");
        assertThat(person.getPhoneNumber()).isEqualTo("123456");
        assertThat(person.getAddress()).isEqualTo("Test Address");
        assertThat(person.getEmailAddress()).isEqualTo("john@test.com");
        assertThat(person.getBirthDate()).isEqualTo("2000-01-01");
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

        assertThat(person.getIdperson()).isEqualTo(2);
        assertThat(person.getLastname()).isEqualTo("Smith");
        assertThat(person.getFirstname()).isEqualTo("Jane");
        assertThat(person.getNickname()).isEqualTo("JS");
        assertThat(person.getPhoneNumber()).isEqualTo("098765432");
        assertThat(person.getAddress()).isEqualTo("Another Address");
        assertThat(person.getEmailAddress()).isEqualTo("jane@test.com");
        assertThat(person.getBirthDate()).isEqualTo("1995-05-15");
    }

    @Test
    public void testEmptyConstructor() {
        Person person = new Person();
        assertThat(person).isNotNull();
    }

    @Test
    public void testPersonToString() {
        Person person = new Person(1, "Doe", "John", "JD", "123456", "Test Address", "john@test.com", "2000-01-01");
        String result = person.toString();

        assertThat(result).contains("Doe");
        assertThat(result).contains("John");
    }
}