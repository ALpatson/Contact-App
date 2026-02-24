package com.contactapp.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Initializes the SQLite database and creates tables.
 */
public class DatabaseInitializer {
    
    /**
     * Initialize the database by creating the person table.
     * Reads SQL from database.sql resource file.
     */
    public static void initializeDatabase() {  // ← Changed from initialize() to initializeDatabase()
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             java.io.InputStream input =
                     DatabaseInitializer.class.getResourceAsStream("/com/contactapp/view/database.sql")) {
            
            if (input == null) {
                throw new RuntimeException("database.sql not found in resources at /com/contactapp/view/database.sql");
            }
            
            String sql = new String(input.readAllBytes());
            stmt.execute(sql);
            System.out.println("Database initialized successfully.");
            
        } catch (SQLException e) {
            System.err.println("SQL Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }
}