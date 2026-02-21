/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contactapp.model;

import com.contactapp.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 *
 * @author cobbi
 */


/**
 * Unit tests for {@link Person} and its interaction with {@link DateUtil}.
 *
 * Coverage areas:
 * 
 *   Construction (no-arg, required-fields, full)
 *   Getters &amp; setters (including null-safety)
 *   Field validation ({@link Person#isValid()} and {@link Person#validationError()})
 *   Max-length constraints
 *   toString / equals / hashCode
 *   DateUtil format, parse and conversion helpers
 * 
 * 
 */
public class PersonTest {

    // A fresh valid person rebuilt before each test
    private Person person;

    @Before
    public void setUp() {
        person = new Person("Doe", "John", "JD");
    }

    // =========================================================================
    // Construction
    // =========================================================================

    @Test
    public void noArgConstructor_createsInvalidPerson() {
        Person p = new Person();
        assertFalse("A brand-new Person should be invalid (blank required fields).", p.isValid());
    }

    @Test
    public void requiredFieldsConstructor_setsFields() {
        assertEquals("Doe",  person.getLastname());
        assertEquals("John", person.getFirstname());
        assertEquals("JD",   person.getNickname());
    }

    @Test
    public void requiredFieldsConstructor_idDefaultsToZero() {
        assertEquals(0, person.getIdperson());
    }

    @Test
    public void fullConstructor_setsAllFields() {
        LocalDate dob = LocalDate.of(1990, 5, 21);
        Person p = new Person(42, "Smith", "Jane", "JS",
                "0612345678", "123 Main St", "jane@example.com", dob);

        assertEquals(42,           p.getIdperson());
        assertEquals("Smith",      p.getLastname());
        assertEquals("Jane",       p.getFirstname());
        assertEquals("JS",         p.getNickname());
        assertEquals("0612345678", p.getPhoneNumber());
        assertEquals("123 Main St",p.getAddress());
        assertEquals("jane@example.com", p.getEmailAddress());
        assertEquals(dob,          p.getBirthDate());
    }

    // =========================================================================
    // Null-safety in setters
    // =========================================================================

    @Test
    public void setLastname_null_storesEmptyString() {
        person.setLastname(null);
        assertEquals("", person.getLastname());
    }

    @Test
    public void setFirstname_null_storesEmptyString() {
        person.setFirstname(null);
        assertEquals("", person.getFirstname());
    }

    @Test
    public void setNickname_null_storesEmptyString() {
        person.setNickname(null);
        assertEquals("", person.getNickname());
    }

    @Test
    public void setPhoneNumber_null_storesEmptyString() {
        person.setPhoneNumber(null);
        assertEquals("", person.getPhoneNumber());
    }

    @Test
    public void setBirthDate_null_storesNull() {
        person.setBirthDate(null);
        assertNull(person.getBirthDate());
    }

    @Test
    public void setters_trim_whitespace() {
        person.setLastname("  Doe  ");
        person.setFirstname("\tJohn\t");
        person.setNickname(" JD ");
        assertEquals("Doe",  person.getLastname());
        assertEquals("John", person.getFirstname());
        assertEquals("JD",   person.getNickname());
    }

    // =========================================================================
    // Validation – required fields
    // =========================================================================

    @Test
    public void isValid_allRequiredFields_returnsTrue() {
        assertTrue(person.isValid());
    }

    @Test
    public void isValid_blankLastname_returnsFalse() {
        person.setLastname("");
        assertFalse(person.isValid());
    }

    @Test
    public void isValid_blankFirstname_returnsFalse() {
        person.setFirstname("  ");
        assertFalse(person.isValid());
    }

    @Test
    public void isValid_blankNickname_returnsFalse() {
        person.setNickname("");
        assertFalse(person.isValid());
    }

    @Test
    public void validationError_blankLastname_mentionsLastname() {
        person.setLastname("");
        String error = person.validationError();
        assertFalse("validationError() should return a non-empty message.", error.isEmpty());
        assertTrue("Error message should mention 'last name'.",
                error.toLowerCase().contains("last name"));
    }

    @Test
    public void validationError_blankFirstname_mentionsFirstname() {
        person.setFirstname("");
        String error = person.validationError();
        assertTrue(error.toLowerCase().contains("first name"));
    }

    @Test
    public void validationError_blankNickname_mentionsNickname() {
        person.setNickname("");
        String error = person.validationError();
        assertTrue(error.toLowerCase().contains("nickname"));
    }

    @Test
    public void validationError_validPerson_returnsEmptyString() {
        assertEquals("", person.validationError());
    }

    // =========================================================================
    // Validation – max-length constraints
    // =========================================================================

    @Test
    public void isValid_lastnameExceedsMaxLength_returnsFalse() {
        person.setLastname("A".repeat(Person.MAX_LASTNAME + 1));
        assertFalse(person.isValid());
    }

    @Test
    public void isValid_firstnameExceedsMaxLength_returnsFalse() {
        person.setFirstname("B".repeat(Person.MAX_FIRSTNAME + 1));
        assertFalse(person.isValid());
    }

    @Test
    public void isValid_nicknameExceedsMaxLength_returnsFalse() {
        person.setNickname("C".repeat(Person.MAX_NICKNAME + 1));
        assertFalse(person.isValid());
    }

    @Test
    public void isValid_phoneExceedsMaxLength_returnsFalse() {
        person.setPhoneNumber("9".repeat(Person.MAX_PHONE + 1));
        assertFalse(person.isValid());
    }

    @Test
    public void isValid_addressExceedsMaxLength_returnsFalse() {
        person.setAddress("X".repeat(Person.MAX_ADDRESS + 1));
        assertFalse(person.isValid());
    }

    @Test
    public void isValid_emailExceedsMaxLength_returnsFalse() {
        person.setEmailAddress("e".repeat(Person.MAX_EMAIL + 1));
        assertFalse(person.isValid());
    }

    @Test
    public void isValid_allFieldsAtMaxLength_returnsTrue() {
        person.setLastname("A".repeat(Person.MAX_LASTNAME));
        person.setFirstname("B".repeat(Person.MAX_FIRSTNAME));
        person.setNickname("C".repeat(Person.MAX_NICKNAME));
        person.setPhoneNumber("9".repeat(Person.MAX_PHONE));
        person.setAddress("X".repeat(Person.MAX_ADDRESS));
        person.setEmailAddress("e".repeat(Person.MAX_EMAIL));
        assertTrue(person.isValid());
    }

    // =========================================================================
    // Optional fields – do NOT invalidate a person
    // =========================================================================

    @Test
    public void isValid_nullBirthDate_isStillValid() {
        person.setBirthDate(null);
        assertTrue(person.isValid());
    }

    @Test
    public void isValid_emptyOptionalStringFields_isStillValid() {
        person.setPhoneNumber("");
        person.setAddress("");
        person.setEmailAddress("");
        assertTrue(person.isValid());
    }

    // =========================================================================
    // toString
    // =========================================================================

    @Test
    public void toString_includesFirstLastAndNickname() {
        String s = person.toString();
        assertTrue(s.contains("John"));
        assertTrue(s.contains("Doe"));
        assertTrue(s.contains("JD"));
    }

    // =========================================================================
    // equals & hashCode
    // =========================================================================

    @Test
    public void equals_sameId_areEqual() {
        person.setIdperson(7);
        Person other = new Person("Smith", "Jane", "JS");
        other.setIdperson(7);
        assertEquals(person, other);
    }

    @Test
    public void equals_differentId_areNotEqual() {
        person.setIdperson(1);
        Person other = new Person("Doe", "John", "JD");
        other.setIdperson(2);
        assertNotEquals(person, other);
    }

    @Test
    public void hashCode_sameId_sameHash() {
        person.setIdperson(5);
        Person other = new Person("X", "Y", "Z");
        other.setIdperson(5);
        assertEquals(person.hashCode(), other.hashCode());
    }

    // =========================================================================
    // DateUtil – format
    // =========================================================================

    @Test
    public void dateUtil_format_nullDate_returnsEmptyString() {
        assertEquals("", DateUtil.format(null));
    }

    @Test
    public void dateUtil_format_validDate_returnsDisplayString() {
        LocalDate date = LocalDate.of(1990, 12, 25);
        assertEquals("25/12/1990", DateUtil.format(date));
    }

    // =========================================================================
    // DateUtil – parse (display format)
    // =========================================================================

    @Test
    public void dateUtil_parse_null_returnsNull() {
        assertNull(DateUtil.parse(null));
    }

    @Test
    public void dateUtil_parse_blankString_returnsNull() {
        assertNull(DateUtil.parse("   "));
    }

    @Test
    public void dateUtil_parse_validDisplayString_returnsLocalDate() {
        LocalDate expected = LocalDate.of(1990, 12, 25);
        assertEquals(expected, DateUtil.parse("25/12/1990"));
    }

    @Test
    public void dateUtil_parse_invalidString_returnsNull() {
        assertNull(DateUtil.parse("not-a-date"));
    }

    @Test
    public void dateUtil_parse_wrongFormat_returnsNull() {
        // Database format should NOT be parseable by the display parser
        assertNull(DateUtil.parse("1990-12-25"));
    }

    // =========================================================================
    // DateUtil – isValidDisplayDate
    // =========================================================================

    @Test
    public void dateUtil_isValidDisplayDate_validString_returnsTrue() {
        assertTrue(DateUtil.isValidDisplayDate("01/01/2000"));
    }

    @Test
    public void dateUtil_isValidDisplayDate_invalidString_returnsFalse() {
        assertFalse(DateUtil.isValidDisplayDate("2000-01-01"));
        assertFalse(DateUtil.isValidDisplayDate("garbage"));
        assertFalse(DateUtil.isValidDisplayDate(null));
        assertFalse(DateUtil.isValidDisplayDate(""));
    }

    // =========================================================================
    // DateUtil – toDbString / fromDbString
    // =========================================================================

    @Test
    public void dateUtil_toDbString_null_returnsNull() {
        assertNull(DateUtil.toDbString(null));
    }

    @Test
    public void dateUtil_toDbString_validDate_returnsIsoString() {
        assertEquals("1990-12-25", DateUtil.toDbString(LocalDate.of(1990, 12, 25)));
    }

    @Test
    public void dateUtil_fromDbString_null_returnsNull() {
        assertNull(DateUtil.fromDbString(null));
    }

    @Test
    public void dateUtil_fromDbString_validIsoString_returnsLocalDate() {
        assertEquals(LocalDate.of(1990, 12, 25), DateUtil.fromDbString("1990-12-25"));
    }

    @Test
    public void dateUtil_fromDbString_invalidString_returnsNull() {
        assertNull(DateUtil.fromDbString("25/12/1990")); // display format, not DB format
        assertNull(DateUtil.fromDbString("garbage"));
    }

    // =========================================================================
    // DateUtil – round-trip conversions
    // =========================================================================

    @Test
    public void dateUtil_formatThenParse_roundTrip() {
        LocalDate original = LocalDate.of(2000, 6, 15);
        assertEquals(original, DateUtil.parse(DateUtil.format(original)));
    }

    @Test
    public void dateUtil_toDbStringThenFromDbString_roundTrip() {
        LocalDate original = LocalDate.of(1985, 3, 7);
        assertEquals(original, DateUtil.fromDbString(DateUtil.toDbString(original)));
    }

    @Test
    public void dateUtil_displayToDb_convertsCorrectly() {
        assertEquals("1990-12-25", DateUtil.displayToDb("25/12/1990"));
    }

    @Test
    public void dateUtil_dbToDisplay_convertsCorrectly() {
        assertEquals("25/12/1990", DateUtil.dbToDisplay("1990-12-25"));
    }

    @Test
    public void dateUtil_displayToDb_invalidInput_returnsNull() {
        assertNull(DateUtil.displayToDb("garbage"));
        assertNull(DateUtil.displayToDb(null));
    }

    @Test
    public void dateUtil_dbToDisplay_invalidInput_returnsEmptyString() {
        assertEquals("", DateUtil.dbToDisplay("garbage"));
        assertEquals("", DateUtil.dbToDisplay(null));
    }

    // =========================================================================
    // DateUtil – Person integration
    // =========================================================================

    @Test
    public void person_birthDate_integrationWithDateUtil() {
        LocalDate dob = LocalDate.of(1995, 8, 20);
        person.setBirthDate(dob);

        // Format for display
        String display = DateUtil.format(person.getBirthDate());
        assertEquals("20/08/1995", display);

        // Store as DB string
        String dbStr = DateUtil.toDbString(person.getBirthDate());
        assertEquals("1995-08-20", dbStr);

        // Restore from DB string
        person.setBirthDate(DateUtil.fromDbString(dbStr));
        assertEquals(dob, person.getBirthDate());
    }
}