package model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.plaf.synth.SynthSpinnerUI;

import exceptions.GrupaNotFoundException;
import exceptions.MaterieNotFoundException;
import exceptions.UserNotFoundException;
import model.entity.Grupa;
import model.entity.Student;
import model.entity.User;
import model.util.DatabaseConnection;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GrupaDAO {
    private static GrupaDAO instance = null;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(GrupaDAO.class.getName());

    private GrupaDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }    
    
    public static synchronized GrupaDAO getInstance() {
        if (instance == null) {
            instance = new GrupaDAO();
        }
        return instance;
    }

    // Adaug grupa noua in baza de date
    public void addGrupa(Grupa grupa) {
        try {
            String sql = "INSERT INTO classes (nume, an) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, grupa.getNume());
            stmt.setInt(2, grupa.getAn());
            stmt.executeUpdate();
            logger.info("Grupa " + grupa.getNume() + " a fost adaugata cu succes in baza de date");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut adauga grupa " + grupa.getNume() + " in baza de date", e);
        }
    }

    public Grupa getGrupaById(int id) throws GrupaNotFoundException, UserNotFoundException, MaterieNotFoundException {
        Grupa grupa = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM classes where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nume = rs.getString("nume");
                int an = rs.getInt("an");

                grupa = new Grupa();
                grupa.setId(id);
                grupa.setNume(nume);
                grupa.setAn(an);

                // Obtin lista cu studentii din grupa

                UserDAO userDAO = UserDAO.getInstance();
                User student;

                stmt = connection.prepareStatement("SELECT * FROM students WHERE idGrupa = ?");
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
    
                while (rs.next()) {
                    int idStudent = rs.getInt("id");

                    student = userDAO.getUserById(idStudent);
                    grupa.getStudenti().add((Student) student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut obtine grupa cu id-ul " + id, e);
        }

        if (grupa == null) {
            throw new GrupaNotFoundException("Grupa " + id + " nu a fost gasita");
        }
        
        return grupa;
    }
}
