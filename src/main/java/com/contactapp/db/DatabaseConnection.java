package com.contactapp.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:sqlite:" + 
        System.getProperty("db.name", "contactapp.db");
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}