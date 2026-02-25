package com.contactapp.db;

import com.contactapp.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class PersonDAOTest {

    private final PersonDAO dao = new PersonDAO();

    @BeforeEach
    public void initDb() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS person ("
                    + "idperson INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "lastname VARCHAR(45) NOT NULL, "
                    + "firstname VARCHAR(45) NOT NULL, "
                    + "nickname VARCHAR(45) NOT NULL, "
                    + "phone_number VARCHAR(15), "
                    + "address VARCHAR(200), "
                    + "email_address VARCHAR(150), "
                    + "birth_date DATE)");

            stmt.executeUpdate("DELETE FROM person");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='person'");

            stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
                    + "VALUES (1, 'Doe', 'John', 'JD', '123456', 'Test Address', 'john@test.com', '2000-01-01')");
            stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
                    + "VALUES (2, 'Smith', 'Jane', 'JS', '654321', 'Other Address', 'jane@test.com', '1995-05-15')");
        }
    }

    @Test
    public void shouldListPersons() {
        // WHEN
        List<Person> persons = dao.getAllPersons();

        // THEN
        assertThat(persons).hasSize(2);
        assertThat(persons).extracting("firstname", "lastname", "nickname")
                .containsOnly(
                        tuple("John", "Doe", "JD"),
                        tuple("Jane", "Smith", "JS")
                );
    }

    @Test
    public void shouldInsertPerson() throws Exception {
        // GIVEN
        Person p = new Person(0, "Brown", "Bob", "BB", "999999", "New Address", "bob@test.com", "1990-03-10");

        // WHEN
        dao.insertPerson(p);

        // THEN
        assertThat(p.getIdperson()).isNotEqualTo(0);

        // AND verify database state
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE firstname='Bob'")) {

            assertThat(rs.next()).isTrue();
            assertThat(rs.getInt("idperson")).isEqualTo(p.getIdperson());
            assertThat(rs.getString("lastname")).isEqualTo("Brown");
            assertThat(rs.next()).isFalse();
        }
    }

    @Test
    public void shouldUpdatePerson() throws Exception {
        // GIVEN
        Person p = dao.getAllPersons().get(0);
        p.setFirstname("Johnny");
        p.setPhoneNumber("000000");

        // WHEN
        dao.updatePerson(p);

        // THEN
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE idperson=" + p.getIdperson())) {

            assertThat(rs.next()).isTrue();
            assertThat(rs.getString("firstname")).isEqualTo("Johnny");
            assertThat(rs.getString("phone_number")).isEqualTo("000000");
        }
    }

    @Test
    public void shouldDeletePerson() throws Exception {
        // WHEN
        dao.deletePerson(1);

        // THEN
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM person WHERE idperson=1")) {

            assertThat(rs.next()).isFalse();
        }
    }
}