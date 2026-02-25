package com.contactapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages SQLite database connections.
 * Provides a single point of access for all database operations.
 */
public class DatabaseConnection {
    
    // Database URL - uses "contactapp.db" by default, or custom name if specified
    private static final String URL = "jdbc:sqlite:" + 
        System.getProperty("db.name", "contactapp.db");
    
    /**
     * Get a connection to the SQLite database.
     * 
     * @return A new database connection
     * @throws SQLException If connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}