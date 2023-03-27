package model.dao;

import java.security.DrbgParameters.Reseed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, idGrupa) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setInt(1, student.getId());
        stmt.setInt(2, student.getIdGrupa());
        stmt.executeUpdate();
    }

    public Student getStudentById(int id) throws SQLException {
        Student student = null;

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM STUDENTS WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        // Aici nu se mai verifica daca exista student in baza de date deoarece din moment
        // ce exista user cu aceelasi id exista si student
        int idGrupa = rs.getInt("idGrupa");

        student = new Student(idGrupa);

        return student;
    }
}
