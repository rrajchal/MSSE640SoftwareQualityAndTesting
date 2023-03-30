package com.triangle.springproject;

import java.sql.*;

public class TriangleDb {
    private Connection connection;

    public TriangleDb() {
        String tableName = "triangles.db";
        try {
            // Connect to the database file
            connection = DriverManager.getConnection("jdbc:sqlite:" + tableName);

            // Create the 'triangles' table if it doesn't exist
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS triangles ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "sideA DOUBLE,"
                    + "sideB DOUBLE,"
                    + "sideC DOUBLE,"
                    + "type TEXT"
                    + ")");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void saveTriangle(Triangle triangle) {
        try {
            // Prepare a SQL statement to insert the triangle data into the 'triangles' table
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO triangles (sideA, sideB, sideC, type) VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setDouble(1, triangle.getSideA());
            preparedStatement.setDouble(2, triangle.getSideB());
            preparedStatement.setDouble(3, triangle.getSideC());
            preparedStatement.setString(4, triangle.getTriangleType().toString());

            // Execute the SQL statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving triangle to database: " + e.getMessage());
        }
    }

    public void close() {
        try {
            // Close the database connection
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }
}