package com.contactapp.db;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseConnectionTest {

    @Test
    public void testConnectionIsNotNull() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            assertThat(conn).isNotNull();
        }
    }
}