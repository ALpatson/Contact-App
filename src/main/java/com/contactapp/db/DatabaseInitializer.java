package com.contactapp.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

	public static void initialize() {

	    try (Connection conn = DatabaseConnection.getConnection();
	         Statement stmt = conn.createStatement();
	         java.io.InputStream input =
	                 DatabaseInitializer.class.getResourceAsStream("/database.sql")) {

	        if (input == null) {
	            throw new RuntimeException("database.sql not found in resources");
	        }

	        String sql = new String(input.readAllBytes());
	        stmt.execute(sql);

	        System.out.println("Database initialized successfully.");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}