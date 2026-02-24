package com.contactapp.db;

import com.contactapp.model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    // =========================
    //  GET ALL PERSONS
    // =========================
    public List<Person> getAllPersons() {

        List<Person> persons = new ArrayList<>();

        String sql = "SELECT * FROM person";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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

    // =========================
    //  INSERT PERSON
    // =========================
    public void insertPerson(Person person) {

        String sql = """
            INSERT INTO person (lastname, firstname, nickname, phone_number, address, email_address, birth_date)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, person.getLastname());
            stmt.setString(2, person.getFirstname());
            stmt.setString(3, person.getNickname());
            stmt.setString(4, person.getPhoneNumber());
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getEmailAddress());
            stmt.setString(7, person.getBirthDate());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    //  UPDATE PERSON
    // =========================
    public void updatePerson(Person person) {

        String sql = """
            UPDATE person
            SET lastname=?, firstname=?, nickname=?,
                phone_number=?, address=?, email_address=?, birth_date=?
            WHERE idperson=?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, person.getLastname());
            stmt.setString(2, person.getFirstname());
            stmt.setString(3, person.getNickname());
            stmt.setString(4, person.getPhoneNumber());
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getEmailAddress());
            stmt.setString(7, person.getBirthDate());
            stmt.setInt(8, person.getIdperson());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // 🔴 DELETE PERSON
    // =========================
    public void deletePerson(int id) {

        String sql = "DELETE FROM person WHERE idperson=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}