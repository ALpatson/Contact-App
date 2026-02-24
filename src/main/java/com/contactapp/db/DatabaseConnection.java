package com.contactapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	 // SQLite database file
    private static final String URL = "jdbc:sqlite:contactapp.db";

    /**
     * Establish connection to SQLite database
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
