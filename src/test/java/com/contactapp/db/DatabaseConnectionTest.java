package com.contactapp.db;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {

    @Test
 public   void testConnectionIsNotNull() throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        assertNotNull(conn);
        conn.close();
    }
}