package com.contactapp.model;

/**
 * Model class representing a contact person.
 */
public class Person {
    
    private int idperson;
    private String firstname;
    private String lastname;
    private String nickname;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String birthDate;

    // Getters
    public int getIdperson() { return idperson; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getNickname() { return nickname; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmailAddress() { return emailAddress; }
    public String getAddress() { return address; }
    public String getBirthDate() { return birthDate; }

    // Setters
    public void setIdperson(int idperson) { this.idperson = idperson; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setAddress(String address) { this.address = address; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    @Override
    public String toString() {
        return "Person{" +
                "idperson=" + idperson +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}