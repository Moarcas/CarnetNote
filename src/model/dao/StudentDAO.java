package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.GrupaNotFoundException;
import exceptions.MaterieNotFoundException;
import exceptions.UserNotFoundException;
import model.entity.Student;
import model.entity.User;
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

    public Student getStudentById(int id) throws SQLException  {
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

    public List<User> getAllStudents() throws SQLException, UserNotFoundException, MaterieNotFoundException, GrupaNotFoundException {
        List<User> students = new ArrayList<>();
        UserDAO userDAO = UserDAO.getInstance();

        String sql = "SELECT id FROM STUDENTS";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int idStudent = rs.getInt("id");
            Student student = (Student) userDAO.getUserById(idStudent);
            students.add(student);
        }
        return students;
    }

    public void updateStudent(int id, Student student) throws SQLException, UserNotFoundException {
        String sql = "UPDATE students SET idGrupa = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, student.getIdGrupa());
        stmt.setInt(2, id);

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new UserNotFoundException("Studentul cu id-ul " + id + " nu a fost gasit in baza de date");
        }        
    }

    public void deteleStudent(int id) throws SQLException, UserNotFoundException {
        String sql = "DELETE FROM students WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new UserNotFoundException("Nu s-a gasit studentul cu id-ul " + id);
        } 
    }
}
