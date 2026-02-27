package com.contactapp.model;

/**
 * Represents a contact person in the application.
 * Stores all contact information and provides getters/setters for data access.
 */
public class Person {
    
    // Contact information fields
    private int idperson;
    private String firstname;
    private String lastname;
    private String nickname;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String birthDate;
    
    /**
     * Constructor to create a Person with all contact details.
     * 
     * @param idperson Unique contact ID
     * @param lastname Contact's last name
     * @param firstname Contact's first name
     * @param nickname Contact's nickname
     * @param phoneNumber Contact's phone number
     * @param address Contact's physical address
     * @param emailAddress Contact's email address
     * @param birthDate Contact's date of birth
     */
    public Person(int idperson, String lastname, String firstname,
            String nickname, String phoneNumber,
            String address, String emailAddress,
            String birthDate) {
        this.idperson = idperson;
        this.lastname = lastname;
        this.firstname = firstname;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
    }
    
    /**
     * Empty constructor for creating a Person without initial values.
     */
    public Person() {
    }
    
    // ===== GETTERS =====
    
    public int getIdperson() { 
        return idperson; 
    }
    
    public String getFirstname() { 
        return firstname; 
    }
    
    public String getLastname() { 
        return lastname; 
    }
    
    public String getNickname() { 
        return nickname; 
    }
    
    public String getPhoneNumber() { 
        return phoneNumber; 
    }
    
    public String getEmailAddress() { 
        return emailAddress; 
    }
    
    public String getAddress() { 
        return address; 
    }
    
    public String getBirthDate() { 
        return birthDate; 
    }
    
    // ===== SETTERS =====
    
    public void setIdperson(int idperson) { 
        this.idperson = idperson; 
    }
    
    public void setFirstname(String firstname) { 
        this.firstname = firstname; 
    }
    
    public void setLastname(String lastname) { 
        this.lastname = lastname; 
    }
    
    public void setNickname(String nickname) { 
        this.nickname = nickname; 
    }
    
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    
    public void setEmailAddress(String emailAddress) { 
        this.emailAddress = emailAddress; 
    }
    
    public void setAddress(String address) { 
        this.address = address; 
    }
    
    public void setBirthDate(String birthDate) { 
        this.birthDate = birthDate; 
    }
    
    /**
     * Return a string representation of the contact.
     * Useful for debugging and logging.
     */
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