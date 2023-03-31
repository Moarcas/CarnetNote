package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.util.DatabaseConnection;

public class ProfesorCursDAO {
    private static ProfesorCursDAO instance = null;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    private ProfesorCursDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized ProfesorCursDAO getInstance() {
        if (instance == null) {
            instance = new ProfesorCursDAO();
        }
        return instance;
    }

    public void addProfesorToClass(int idProfesor, int idGrupa, int idMaterie) {
        String sql = "INSERT INTO teacher_subject (idProfesor, idMaterie, idGrupa) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idProfesor);
            stmt.setInt(2, idMaterie);
            stmt.setInt(3, idGrupa);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut adauga prefesorul cu id-ul " + idProfesor + " in tabela teacher_subject", e);
        }

        logger.info("Profesorul cu id-ul " + idProfesor + " a fost adaugat cu succes la materia cu id-ul " + idMaterie + " la grupa cu id-ul " + idGrupa);
    }

    // De continuat restul CRUD-ului
}
