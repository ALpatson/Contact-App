package com.contactapp.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Sets up the SQLite database when the application starts.
 * Creates the contact table from an SQL script file.
 */
public class DatabaseInitializer {
    
    /**
     * Create the database tables by executing SQL from database.sql file.
     * Reads the SQL script and runs it to set up the initial schema.
     * 
     * @throws RuntimeException If the database.sql file cannot be found
     */
    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             java.io.InputStream input =
                     DatabaseInitializer.class.getResourceAsStream("/com/contactapp/view/database.sql")) {
            
            // Check if the SQL script file exists
            if (input == null) {
                throw new RuntimeException("database.sql not found in resources at /com/contactapp/view/database.sql");
            }
            
            // Read the SQL script and execute it
            String sql = new String(input.readAllBytes());
            stmt.execute(sql);
            System.out.println("Database initialized successfully.");
            
        } catch (SQLException e) {
            // Database connection or execution error
            System.err.println("SQL Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Any other error (file reading, etc)
            System.err.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
}