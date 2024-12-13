/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HUMAIRA
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordDAO {
    public List<Record> getAllRecords() throws SQLException {
        List<Record> records = new ArrayList<>();
        String query = "SELECT * FROM records";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                records.add(new Record(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                ));
            }
        }
        return records;
    }

    public void insertRecord(Record record) throws SQLException {
        String query = "INSERT INTO records (name, email, address, phone) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, record.getName());
            preparedStatement.setString(2, record.getEmail());
            preparedStatement.setString(3, record.getAddress());
            preparedStatement.setString(4, record.getPhone());
            preparedStatement.executeUpdate();
        }
    }

    public void updateRecord(Record record) throws SQLException {
        String query = "UPDATE records SET name=?, email=?, address=?, phone=? WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, record.getName());
            preparedStatement.setString(2, record.getEmail());
            preparedStatement.setString(3, record.getAddress());
            preparedStatement.setString(4, record.getPhone());
            preparedStatement.setInt(5, record.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteRecord(int id) throws SQLException {
        String query = "DELETE FROM records WHERE id=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}