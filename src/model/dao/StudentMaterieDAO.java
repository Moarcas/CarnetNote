package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.MaterieNotFoundException;
import model.entity.Materie;
import model.entity.Student;
import model.util.DatabaseConnection;

public class StudentMaterieDAO {
    private static StudentMaterieDAO instance = null;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(StudentMaterieDAO.class.getName());

    private StudentMaterieDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized StudentMaterieDAO getInstance() {
        if (instance == null) {
            instance = new StudentMaterieDAO();
        }
        return instance;
    }

    public void addStudentToCourse(int idStudent, int idMaterie) {
        String sql = "INSERT INTO student_subject (idStudent, idMaterie) VALUES (?, ?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idStudent);
            stmt.setInt(2, idMaterie);
            stmt.executeUpdate();
            logger.info("Studentul cu id-ul " + idStudent + " a fost adaugat cu succes la materia cu id-ul " + idMaterie);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut adauga studentul cu id-ul " + idStudent + " in tabela student_subject", e);
        }
    }

    public void deleteStudentFromCourse(int idStudent, int idMaterie) {
        String sql = "DELETE FROM student_subject WHERE idStudent = ? AND idMaterie = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idStudent);
            stmt.setInt(2, idMaterie);
            
            stmt.executeUpdate();
            logger.info("Studentul cu id-ul " + idStudent + " a fost sters cu succes de la materia cu id-ul " + idMaterie);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut sterge studentul cu id-ul " + idStudent + " din tabela student_subject", e);
        }
    }

    public List<Materie> getCorusesByStudentId(int id) {
        List<Materie> materii = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM student_subject WHERE idStudent = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idMaterie = rs.getInt("idMaterie");
                Materie materie = MaterieDAO.getInstance().getCourseById(idMaterie);
                materii.add(materie);
            }
        } catch (SQLException | MaterieNotFoundException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-au putut obtine materiile studentului cu id-ul" + id, e);
        } 
        return materii;
    }

    public List<Student> getStudentsByCoruseId(int id) {
        List<Student> students = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM student_subject WHERE idMaterie = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idStudent = rs.getInt("idStudent");
                Student student = StudentDAO.getInstance().getStudentById(idStudent);
                students.add(student);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-au putut obtine studentii materiei cu id-ul" + id, e);
        } 
        return students;
    }
}
