package com.example.midtermexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class mysqlconnect {
    private static final String URL = "jdbc:mysql://localhost:3306/diagnosis_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection ConnectDb() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            JOptionPane.showMessageDialog(null, "Connection Established");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return conn;
    }

    public static void insertDiagnosis(String patient_id, String symptoms, String diagnosis, String medicines, boolean ward_required) {
        Connection conn = ConnectDb();
        if (conn != null) {
            String query = "INSERT INTO diagnosis (patient_id, symptoms, diagnosis, medicines, ward_required) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, patient_id);
                pstmt.setString(2, symptoms);
                pstmt.setString(3, diagnosis);
                pstmt.setString(4, medicines);
                pstmt.setBoolean(5, ward_required);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public static void main(String[] args) {

        ConnectDb();

        insertDiagnosis("P001", "Fever, Cough", "Flu", "Paracetamol", true);
    }
}
