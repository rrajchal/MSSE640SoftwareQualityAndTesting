package com.triangle.springproject;

import java.sql.*;
import java.util.*;

import org.json.JSONArray;

public class TriangleDb {
    private Connection connection;
    String tableName = "triangles.db";
    public TriangleDb() {

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

    public JSONArray getTriangle(Triangle triangle) {
        System.out.print("Getting data from the triangles table for : ");
        if (triangle != null)
            System.out.println(triangle);
        else
            System.out.println(" all");
        String query = "";
        ResultSet rs = null;
        PreparedStatement statement = null;
        List<Map<String, Object>> data = new LinkedList<>();

        try {
            if (connection.isClosed())
                connection = DriverManager.getConnection("jdbc:sqlite:" + tableName);
            if (triangle == null) {
                query = "SELECT * from triangles";
                statement = connection.prepareStatement(query);
            }
            else {
                query = "SELECT * from triangles where sideA = ? and sideB = ? and sideC = ?";
                statement = connection.prepareStatement(query);
                statement.setDouble(1, triangle.getSideA());
                statement.setDouble(2, triangle.getSideB());
                statement.setDouble(3, triangle.getSideC());
            }
            rs = statement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = rsmd.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                data.add(row);
            }
            JSONArray jsonArray = new JSONArray(data);
            return jsonArray;
        } catch (SQLException e) {
            System.out.println("Error retrieving triangle from database: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void saveTriangle(Triangle triangle) {
        System.out.println("Saving triangle: " + triangle);
        try {
            // Prepare a SQL statement to insert the triangle data into the 'triangles' table
            if (connection.isClosed())
                connection = DriverManager.getConnection("jdbc:sqlite:" + tableName);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO triangles (sideA, sideB, sideC, type) VALUES (?, ?, ?, ?)"
            );
            statement.setDouble(1, triangle.getSideA());
            statement.setDouble(2, triangle.getSideB());
            statement.setDouble(3, triangle.getSideC());
            statement.setString(4, triangle.getTriangleType().toString());

            // Execute the SQL statement
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving triangle to database: " + e.getMessage());
        }
    }

    public void deleteTriangle(Triangle triangle) {
        System.out.println("Deleting triangle: " + triangle);
        try {
            // Prepare a SQL statement to delete the triangle from the 'triangles' table
            if (connection.isClosed())
                connection = DriverManager.getConnection("jdbc:sqlite:" + tableName);
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM triangles WHERE sideA = ? AND sideB = ? AND sideC = ? AND type = ?"
            );
            statement.setDouble(1, triangle.getSideA());
            statement.setDouble(2, triangle.getSideB());
            statement.setDouble(3, triangle.getSideC());
            statement.setString(4, triangle.getTriangleType().toString());

            // Execute the SQL statement
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving triangle to database: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Error closing database connection: " + e.getMessage());
        }
    }
}