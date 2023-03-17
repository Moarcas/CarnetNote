package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.Student;
import model.util.DatabaseConnection;

public class StudentDAO {
    private static StudentDAO instance = null;
    private Connection connection;

    private StudentDAO() {
        // Se realizeaza conexiunea la baza de date
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    // Adauga nou student in baza de date

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (id, an, grupa) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, student.getId());
            stmt.setInt(2, student.getAn());
            stmt.setInt(3, student.getGrupa());
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
