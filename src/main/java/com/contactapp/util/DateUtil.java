package com.contactapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for handling date conversions between different formats.
 * Converts dates between user-friendly display format (dd/MM/yyyy) and database format (yyyy-MM-dd).
 */
public final class DateUtil {

    // Date format patterns
    /** Format shown to users in the UI: dd/MM/yyyy (e.g., "25/12/1990") */
    public static final String DISPLAY_PATTERN = "dd/MM/yyyy";

    /** Format stored in SQLite database: yyyy-MM-dd (e.g., "1990-12-25") */
    public static final String DB_PATTERN = "yyyy-MM-dd";

    // Thread-safe date formatters (created once and reused)
    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern(DISPLAY_PATTERN);

    private static final DateTimeFormatter DB_FORMATTER =
            DateTimeFormatter.ofPattern(DB_PATTERN);

    /**
     * Private constructor - this is a utility class with only static methods.
     * It should never be instantiated.
     */
    private DateUtil() {}

    // ===== DISPLAY FORMAT (for UI) =====

    /**
     * Format a date for display in the user interface.
     * 
     * @param date The date to format (can be null)
     * @return Formatted string like "25/12/1990", or empty string if date is null
     */
    public static String format(LocalDate date) {
        return date == null ? "" : DISPLAY_FORMATTER.format(date);
    }

    /**
     * Parse a date string from user input (dd/MM/yyyy format).
     * 
     * @param dateString User-entered date string (can be null or blank)
     * @return Parsed LocalDate, or null if invalid or blank
     */
    public static LocalDate parse(String dateString) {
        if (dateString == null || dateString.isBlank()) return null;
        try {
            return LocalDate.parse(dateString.trim(), DISPLAY_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;  // Invalid date format
        }
    }

    /**
     * Check if a date string is valid display format.
     * 
     * @param dateString The string to validate
     * @return true if it's a valid date, false otherwise
     */
    public static boolean isValidDisplayDate(String dateString) {
        return parse(dateString) != null;
    }

    // ===== DATABASE FORMAT (for SQLite) =====

    /**
     * Format a date for storage in the SQLite database.
     * 
     * @param date The date to format (can be null)
     * @return Formatted string like "1990-12-25", or null if date is null
     */
    public static String toDbString(LocalDate date) {
        return date == null ? null : DB_FORMATTER.format(date);
    }

    /**
     * Parse a date string from the SQLite database (yyyy-MM-dd format).
     * 
     * @param dbString Date string from database (can be null or blank)
     * @return Parsed LocalDate, or null if invalid or blank
     */
    public static LocalDate fromDbString(String dbString) {
        if (dbString == null || dbString.isBlank()) return null;
        try {
            return LocalDate.parse(dbString.trim(), DB_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;  // Invalid date format
        }
    }

    // ===== FORMAT CONVERSION =====

    /**
     * Convert a user-entered date to database format.
     * 
     * @param displayDate Date string in dd/MM/yyyy format
     * @return Date string in yyyy-MM-dd format, or null if invalid
     */
    public static String displayToDb(String displayDate) {
        return toDbString(parse(displayDate));
    }

    /**
     * Convert a database date to user-friendly format.
     * 
     * @param dbDate Date string in yyyy-MM-dd format
     * @return Date string in dd/MM/yyyy format, or empty string if invalid
     */
    public static String dbToDisplay(String dbDate) {
        return format(fromDbString(dbDate));
    }
}