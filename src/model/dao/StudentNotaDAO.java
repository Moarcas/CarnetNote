package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.util.DatabaseConnection;

public class StudentNotaDAO {
    private static StudentNotaDAO instance = null;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(StudentNotaDAO.class.getName());

    private StudentNotaDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized StudentNotaDAO getInstance() {
        if (instance == null) {
            instance = new StudentNotaDAO();
        }
        return instance;
    }

    public void addGradeToStudent(int idStudent, int idMaterie, double nota, String data) {
        String sql = "INSERT INTO grades (idStudent, idMaterie, nota, date) VALUES (?, ?, ?, ?)";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idStudent);
            stmt.setInt(2, idMaterie);
            stmt.setDouble(3, nota);
            stmt.setString(4, data);
            stmt.executeUpdate();
            logger.info("Nota pentru studentul cu id-ul " + idStudent + " a fost adaugata cu succes la materia cu id-ul " + idMaterie + " si data de " + data);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut adauga nota pentru studentul cu id-ul " + idStudent + " in tabela grades", e);
        }
    }
}
