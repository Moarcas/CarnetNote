package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;

import model.entity.Materie;
import model.util.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterieDAO {
    private static MaterieDAO instance = null;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(MaterieDAO.class.getName());

    private MaterieDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized MaterieDAO getInstance() {
        if (instance == null) {
            instance = new MaterieDAO();
        }
        return instance;
    }

    public void addCourse(Materie materie) {
        try {
            String sql = "INSERT INTO subjectss (nume, descriere) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
        
            stmt.setString(1, materie.getNume());
            stmt.setString(2, materie.getDescriere());
            stmt.executeUpdate();
            logger.info("Materia " + materie.getNume() + " a fost adaugata cu succes in baza de date.");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut adauga materia " + materie.getNume() + " in baza de date.", e);   
        }
    }

    public Materie getCourseById(int id) {
        Materie materie = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM subjects WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                String nume = rs.getString("nume");
                String descriere = rs.getString("descriere");
                
                materie = new Materie();
                materie.setId(id);
                materie.setNume(nume);
                materie.setDescriere(descriere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut obtine materia cu id-ul" + id, e);
        }

        return materie;
    }

    public List<Materie> getAllCourses() {
        List<Materie> materii = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM subjects");
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                String descriere = rs.getString("descriere");
                
                Materie materie = new Materie();
                materie.setId(id);
                materie.setNume(nume);
                materie.setDescriere(descriere);

                materii.add(materie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-au putut obtine toate materiile", e);
        }
        
        return materii;
    }

    public List<Materie> getCoursesByStudent(int idStudent) {
        List<Materie> materii = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM student_subject WHERE idStudent = ?");

            stmt.setInt(1, idStudent);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idMaterie = rs.getInt("idMaterie");
                Materie materie = getCourseById(idMaterie);
                materii.add(materie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-au putut obtine toate materiile", e);
        }
        return materii;
    } 
}
