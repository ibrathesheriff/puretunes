package org.puretunes.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection connection;

    private static final String PURETUNES_DB_PATH = "C:\\Users\\ibrathesheriff\\Documents\\dev\\ibrathesheriff\\puretunes\\puretunes\\puretunes.db";

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.sqlite.JDBC"); // Load the driver
                connection = DriverManager.getConnection(PURETUNES_DB_PATH);
                System.out.println("Connection to SQLite database established.");
            } catch (Exception e) {
                System.err.println("SQLite JDBC driver not found!");
                e.printStackTrace();
                throw new SQLException("Error loading SQLite JDBC driver", e); // Re-throw as SQLException
            }
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Connection to SQLite database closed.");
        }
    }
}
