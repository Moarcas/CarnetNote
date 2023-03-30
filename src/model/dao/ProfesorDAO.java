package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import exceptions.GrupaNotFoundException;
import exceptions.MaterieNotFoundException;
import exceptions.UserNotFoundException;
import model.entity.Grupa;
import model.entity.Materie;
import model.entity.Profesor;
import model.util.DatabaseConnection;

public class ProfesorDAO {
    private static ProfesorDAO instance = null;
    private Connection connection;

    private ProfesorDAO() {
        // Se realizeaza conexiunea la baza de date
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized ProfesorDAO getInstance() {
        if (instance == null) {
            instance = new ProfesorDAO();
        }
        return instance;
    }

    // Adauga nou profesor in baza de date
    public void addProfesor(Profesor profesor) throws SQLException {
        String sql = "INSERT INTO teachers (id) VALUES (?)";   
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setInt(1, profesor.getId());
        stmt.executeUpdate();
    }

    public Profesor getProfesorById(int id) throws SQLException, MaterieNotFoundException, UserNotFoundException, GrupaNotFoundException {
        Profesor profesor = null;

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM teachers WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            profesor = new Profesor();
            
            // Iau din tabelul techer_subject id-urile materiilor predate
            MaterieDAO materieDAO = MaterieDAO.getInstance();
            stmt = connection.prepareStatement("SELECT DISTINCT idMaterie FROM teacher_subject WHERE idProfesor = ?");
            
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idMaterie = rs.getInt("idMaterie");

                // Iau din subjects materia cu id-ul specificat si il bag in lista
                Materie materie = materieDAO.getMaterieById(idMaterie);
                profesor.getMateriiPredate().add(materie);
            }

            // Iau din tabeul tacher_subject grupele la care preda
            GrupaDAO grupaDAO = GrupaDAO.getInstance();
            stmt = connection.prepareStatement("SELECT DISTINCT idGrupa FROM teacher_subject WHERE idProfesor = ?");

            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idGrupa = rs.getInt("idGrupa");

                // Iau din classes grupa cu id-ul specificat si o bag in lista
                Grupa grupa = grupaDAO.getGrupaById(idGrupa);
                profesor.getGrupe().add(grupa);
            }
        }
    
        if (profesor == null) {
            throw new UserNotFoundException("Profesorul cu id-ul " + id + " nu a fost gasit");
        }

        return profesor;
    }

    public void updateProfesor(int id, Profesor profesor) throws SQLException, UserNotFoundException {
        String sql = "UPDATE teachers SET id = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, id);

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new UserNotFoundException("Profesorul cu id-ul " + id + " nu a fost gasit in baza de date");
        }
    }

    public void deteleProfesor(int id) throws SQLException, UserNotFoundException {
        String sql = "DELETE FROM teachers where id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new UserNotFoundException("Profesorul cu id-ul " + id + " nu a fost gasit in baza de date");
        }
    }
}   
