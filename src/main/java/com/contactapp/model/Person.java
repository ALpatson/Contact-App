/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contactapp.model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 *
 * @author cobbi
 */


public class Person {

    // -------------------------------------------------------------------------
    // Constants – max field lengths mirroring the DDL
    // -------------------------------------------------------------------------
    public static final int MAX_LASTNAME  = 45;
    public static final int MAX_FIRSTNAME = 45;
    public static final int MAX_NICKNAME  = 45;
    public static final int MAX_PHONE     = 15;
    public static final int MAX_ADDRESS   = 200;
    public static final int MAX_EMAIL     = 150;

    // -------------------------------------------------------------------------
    // JavaFX properties
    // -------------------------------------------------------------------------
    private final IntegerProperty idperson = new SimpleIntegerProperty(0);
    private final StringProperty lastname = new SimpleStringProperty("");
    private final StringProperty firstname = new SimpleStringProperty("");
    private final StringProperty nickname = new SimpleStringProperty("");
    private final StringProperty phoneNumber = new SimpleStringProperty("");
    private final StringProperty address = new SimpleStringProperty("");
    private final StringProperty emailAddress = new SimpleStringProperty("");
    private final ObjectProperty<LocalDate> birthDate = new SimpleObjectProperty<>(null);

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    public Person() {}

    /**
     * Convenience constructor for the three mandatory fields.
     *
     * @param lastname  family name, must not be blank
     * @param firstname given name, must not be blank
     * @param nickname  nickname, must not be blank
     */
    public Person(String lastname, String firstname, String nickname) {
        setLastname(lastname);
        setFirstname(firstname);
        setNickname(nickname);
    }

    /**
     * Full constructor used when loading an existing record from the database.
     *
     * @param idperson     primary key
     * @param lastname     family name
     * @param firstname    given name
     * @param nickname     nickname
     * @param phoneNumber  phone number (nullable)
     * @param address      postal address (nullable)
     * @param emailAddress e-mail address (nullable)
     * @param birthDate    date of birth (nullable)
     */
    public Person(int idperson, String lastname, String firstname, String nickname,
                  String phoneNumber, String address, String emailAddress, LocalDate birthDate) {
        setIdperson(idperson);
        setLastname(lastname);
        setFirstname(firstname);
        setNickname(nickname);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setEmailAddress(emailAddress);
        setBirthDate(birthDate);
    }

    // -------------------------------------------------------------------------
    // Validation
    // -------------------------------------------------------------------------

    /**
     * Returns {true} when all required fields are non-blank.
     */
    public boolean isValid() {
        return validationError().isEmpty();
    }

    /**
     * Returns a human-readable description of the first validation problem found,
     * or an empty string when the object is valid.
     *
     * @return error message or {""}
     */
    public String validationError() {
        if (isBlank(getLastname()))   return "Last name must not be empty.";
        if (isBlank(getFirstname()))  return "First name must not be empty.";
        if (isBlank(getNickname()))   return "Nickname must not be empty.";
        if (getLastname().length()  > MAX_LASTNAME)  return "Last name exceeds "   + MAX_LASTNAME  + " characters.";
        if (getFirstname().length() > MAX_FIRSTNAME) return "First name exceeds "  + MAX_FIRSTNAME + " characters.";
        if (getNickname().length()  > MAX_NICKNAME)  return "Nickname exceeds "    + MAX_NICKNAME  + " characters.";
        String phone = getPhoneNumber();
        String addr  = getAddress();
        String email = getEmailAddress();
        if (phone != null && phone.length() > MAX_PHONE)   return "Phone number exceeds "  + MAX_PHONE   + " characters.";
        if (addr  != null && addr.length()  > MAX_ADDRESS) return "Address exceeds "       + MAX_ADDRESS + " characters.";
        if (email != null && email.length() > MAX_EMAIL)   return "Email address exceeds " + MAX_EMAIL   + " characters.";
        return "";
    }

    // -------------------------------------------------------------------------
    // Property accessors (for JavaFX binding / PropertyValueFactory)
    // -------------------------------------------------------------------------

    public IntegerProperty idpersonProperty() { return idperson; }
    public StringProperty lastnameProperty() { return lastname; }
    public StringProperty firstnameProperty() { return firstname; }
    public StringProperty nicknameProperty() { return nickname; }
    public StringProperty phoneNumberProperty() { return phoneNumber; }
    public StringProperty addressProperty() { return address; }
    public StringProperty emailAddressProperty() { return emailAddress; }
    public ObjectProperty<LocalDate> birthDateProperty() { return birthDate; }

    // -------------------------------------------------------------------------
    // Getters & setters
    // -------------------------------------------------------------------------

    public int getIdperson() { return idperson.get(); }
    public void setIdperson(int id) { idperson.set(id); }

    public String getLastname() { return lastname.get(); }
    public void setLastname(String lName) { lastname.set(lName == null ? "" : lName.trim()); }

    public String getFirstname() { return firstname.get(); }
    public void setFirstname(String fName) { firstname.set(fName == null ? "" : fName.trim()); }

    public String getNickname() { return nickname.get(); }
    public void setNickname(String nName) { nickname.set(nName == null ? "" : nName.trim()); }

    public String getPhoneNumber() { return phoneNumber.get(); }
    public void setPhoneNumber(String phoneNum) { phoneNumber.set(phoneNum == null ? "" : phoneNum.trim()); }

    public String getAddress() { return address.get(); }
    public void setAddress(String addr) { address.set(addr == null ? "" : addr.trim()); }

    public String getEmailAddress() { return emailAddress.get(); }
    public void setEmailAddress(String email) { emailAddress.set(email == null ? "" : email.trim()); }

    public LocalDate getBirthDate() { return birthDate.get(); }
    public void setBirthDate(LocalDate v) { birthDate.set(v); }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

    /** Returns "Firstname Lastname (nickname)" for display purposes. */
    @Override
    public String toString() {
        return getFirstname() + " " + getLastname() + " (" + getNickname() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        return getIdperson() == ((Person) o).getIdperson();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getIdperson());
    }

    // -------------------------------------------------------------------------
    // Private helpers
    // -------------------------------------------------------------------------

    private static boolean isBlank(String s) { return s == null || s.isBlank(); }
}
