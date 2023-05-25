package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.DatabaseException;
import model.entity.Nota;
import model.util.DatabaseConnection;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NotaDAO {
    private static NotaDAO instance;
    private static Connection connection;
    private static final Logger logger = Logger.getLogger(NotaDAO.class.getName());

    private NotaDAO() {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            logger.info("Conexiunea la baza de date a fost realizata cu succes");
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Conexiunea la baza de date nu a putut fi realizata", e);
        }
    }

    public static synchronized NotaDAO getInstance() {
        if (instance == null) {
            instance = new NotaDAO();
        }
        return instance;
    }

    public void addNota(Nota nota) {
        String sql = "INSERT INTO nota (idStudent, idMaterie, nota, data) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, nota.getIdStudent());
            stmt.setInt(2, nota.getIdMaterie());
            stmt.setFloat(3, nota.getNota());
            stmt.setString(4, nota.getData());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            nota.setId(rs.getInt(1));

            logger.info("Nota a fost adaugata cu succes");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-a putut adauga nota", e);
            throw new DatabaseException("\nDatabase error\n.", e);
        }
    }

    public List<Nota> getNoteByStudent(int idStudent, int idMaterie) {
        List<Nota> note = new ArrayList<>();
        String sql = "SELECT * FROM grades WHERE idStudent = ? AND idMaterie = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idStudent);
            stmt.setInt(2, idMaterie);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota(rs.getInt("idStudent"), rs.getInt("idMaterie"), rs.getFloat("nota"), rs.getString("data"));
                nota.setId(rs.getInt("id"));
                note.add(nota);
            }

            logger.info("Notele studentului cu id-ul " + idStudent + " au fost extrase cu succes");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-au putut extrage notele studentului cu id-ul " + idStudent, e);
            throw new DatabaseException("\nDatabase error\n.", e);
        }
        return note;
    } 

    
}
