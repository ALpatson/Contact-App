/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contactapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author cobbi
 */


public final class DateUtil {

    // -------------------------------------------------------------------------
    // Date patterns
    // -------------------------------------------------------------------------

    /** Pattern shown to the user: {@code dd/MM/yyyy}. */
    public static final String DISPLAY_PATTERN = "dd/MM/yyyy";

    /** Pattern stored in SQLite: {@code yyyy-MM-dd} (ISO-8601 subset). */
    public static final String DB_PATTERN = "yyyy-MM-dd";

    // -------------------------------------------------------------------------
    // Formatters (thread-safe because DateTimeFormatter is immutable)
    // -------------------------------------------------------------------------

    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern(DISPLAY_PATTERN);

    private static final DateTimeFormatter DB_FORMATTER =
            DateTimeFormatter.ofPattern(DB_PATTERN);

    // Private constructor – utility class, not meant to be instantiated.
    private DateUtil() {}

    // -------------------------------------------------------------------------
    // Display format helpers (UI ↔ LocalDate)
    // -------------------------------------------------------------------------

    /**
     * Formats a {@link LocalDate} for display in the GUI.
     *
     * @param date the date to format, may be {@code null}
     * @return formatted string (e.g. {@code "25/12/1990"}) or {@code ""} if {@code date} is {@code null}
     */
    public static String format(LocalDate date) {
        return date == null ? "" : DISPLAY_FORMATTER.format(date);
    }

    /**
     * Parses a display-format string into a {@link LocalDate}.
     *
     * @param dateString a string in {@value #DISPLAY_PATTERN} format, may be {@code null} or blank
     * @return the parsed {@link LocalDate}, or {@code null} if the input is blank or unparseable
     */
    public static LocalDate parse(String dateString) {
        if (dateString == null || dateString.isBlank()) return null;
        try {
            return LocalDate.parse(dateString.trim(), DISPLAY_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Returns {@code true} if the given string is a valid display-format date.
     *
     * @param dateString string to validate
     * @return {@code true} when parseable, {@code false} otherwise
     */
    public static boolean isValidDisplayDate(String dateString) {
        return parse(dateString) != null;
    }

    // -------------------------------------------------------------------------
    // Database format helpers (SQLite ↔ LocalDate)
    // -------------------------------------------------------------------------

    /**
     * Formats a {@link LocalDate} for storage in the SQLite database.
     *
     * @param date the date to format, may be {@code null}
     * @return formatted string (e.g. {@code "1990-12-25"}) or {@code null} if {@code date} is {@code null}
     */
    public static String toDbString(LocalDate date) {
        return date == null ? null : DB_FORMATTER.format(date);
    }

    /**
     * Parses an ISO-8601 date string read from the SQLite database.
     *
     * @param dbString a string in {@value #DB_PATTERN} format, may be {@code null} or blank
     * @return the parsed {@link LocalDate}, or {@code null} if the input is blank or unparseable
     */
    public static LocalDate fromDbString(String dbString) {
        if (dbString == null || dbString.isBlank()) return null;
        try {
            return LocalDate.parse(dbString.trim(), DB_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // -------------------------------------------------------------------------
    // Conversion between the two string formats
    // -------------------------------------------------------------------------

    /**
     * Converts a display-format date string to a database-format date string.
     *
     * Useful when persisting a value typed directly by the user.
     *
     * @param displayDate string in {@value #DISPLAY_PATTERN} format
     * @return string in {@value #DB_PATTERN} format, or {@code null} if the input is invalid
     */
    public static String displayToDb(String displayDate) {
        return toDbString(parse(displayDate));
    }

    /**
     * Converts a database-format date string to a display-format date string.
     *
     * Useful when populating form fields from a database record.
     *
     * @param dbDate string in {@value #DB_PATTERN} format
     * @return string in {@value #DISPLAY_PATTERN} format, or {@code ""} if the input is invalid
     */
    public static String dbToDisplay(String dbDate) {
        return format(fromDbString(dbDate));
    }
}