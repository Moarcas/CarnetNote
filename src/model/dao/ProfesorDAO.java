package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.DatabaseException;
import exceptions.GrupaNotFoundException;
import exceptions.MaterieNotFoundException;
import exceptions.UserNotFoundException;
import model.entity.Grupa;
import model.entity.Materie;
import model.entity.Profesor;
import model.entity.User;
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

    public void addTeacher(Profesor profesor) throws SQLException {
        String sql = "INSERT INTO teachers (id) VALUES (?)";   
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setInt(1, profesor.getId());
        stmt.executeUpdate();
    }

    public Profesor getTeacherById(int id) throws DatabaseException {
        Profesor profesor = null;

        Set<Materie> materii = new HashSet<>();
        Set <Grupa> grupe = new HashSet<>();

        MaterieDAO materieDAO = MaterieDAO.getInstance();
        GrupaDAO grupaDAO = GrupaDAO.getInstance();

        // I want from teacher_subject courses and groups where he teaches
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM teacher_subject WHERE idProfesor = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idMaterie = rs.getInt("idMaterie");
                int idGrupa = rs.getInt("idGrupa");

                Materie materie = materieDAO.getCourseById(idMaterie);
                Grupa grupa = grupaDAO.getGrupaById(idGrupa);

                materii.add(materie);
                grupe.add(grupa);
            }            
        } catch (SQLException e) {
            throw new DatabaseException("Eroare la interogarea bazei de date", e);
        } 
        
        profesor = new Profesor(materii, grupe);
        return profesor;
    }

    public List<User> getAllTeachers() throws SQLException, UserNotFoundException, MaterieNotFoundException, GrupaNotFoundException {
        List<User> teachers = new ArrayList<>();
        UserDAO userDAO = UserDAO.getInstance();

        String sql = "SELECT id FROM teachers";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int idProfesor = rs.getInt("id");
            Profesor profesor = (Profesor) userDAO.getUserById(idProfesor);
            teachers.add(profesor);
        }
        return teachers;
    }

    public void updateTeacher(int id, Profesor profesor) throws SQLException, UserNotFoundException {
        String sql = "UPDATE teachers SET id = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.setInt(2, id);

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new UserNotFoundException("Profesorul cu id-ul " + id + " nu a fost gasit in baza de date");
        }
    }

    public void deteleTeacher(int id) throws SQLException, UserNotFoundException {
        String sql = "DELETE FROM teachers WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new UserNotFoundException("Profesorul cu id-ul " + id + " nu a fost gasit in baza de date");
        }
    }
}   
