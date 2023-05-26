package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.DatabaseException;
import exceptions.GrupaNotFoundException;
import model.entity.Grupa;
import model.util.DatabaseConnection;

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

    public void addGrupa(Grupa grupa) {
        try {
            String sql = "INSERT INTO classes (nume, an) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, grupa.getNume());
            stmt.setInt(2, grupa.getAn());
            stmt.executeUpdate();
            logger.info("Grupa " + grupa.getNume() + " a fost adaugata cu succes in baza de date");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-a putut adauga grupa " + grupa.getNume() + " in baza de date", e);
            throw new DatabaseException("Nu s-a putut adauga grupa " + grupa.getNume() + " in baza de date", e);
        }
    }

    public Grupa getGrupaById(int id) throws DatabaseException {
        Grupa grupa = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM classes where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nume = rs.getString("nume");
                int an = rs.getInt("an");

                grupa = new Grupa(nume, an);
                grupa.setId(id);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-a putut obtine grupa cu id-ul " + id, e);
            throw new DatabaseException("Nu s-a putut obtine grupa cu id-ul " + id, e);
        }

        return grupa;
    }

    public Grupa getGrupaByName(String numeGrupa) throws GrupaNotFoundException {
        Grupa grupa = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM classes where nume = ?");
            stmt.setString(1, numeGrupa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int an = rs.getInt("an");

                grupa = new Grupa(numeGrupa, an);
                grupa.setId(id);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-a putut obtine grupa cu numele " + numeGrupa, e);
            throw new DatabaseException("Nu s-a putut obtine grupa cu numele " + numeGrupa, e);
        }
        return grupa;
    }

    public List<Grupa> getAllClasses() {
        List<Grupa> grupe = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM classes");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                int an = rs.getInt("an");

                Grupa grupa = new Grupa(nume, an);
                grupa.setId(id);
                grupe.add(grupa);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-au putut obtine grupele", e);
            throw new DatabaseException("Nu s-au putut obtine grupele", e);
        }
        return grupe;
    }
}
