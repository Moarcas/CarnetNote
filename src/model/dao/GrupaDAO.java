package model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

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

    public Grupa getGrupaById(int id) throws GrupaNotFoundException {
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
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut obtine grupa cu id-ul " + id, e);
        }

        if (grupa == null) {
            throw new GrupaNotFoundException("Grupa " + id + " nu a fost gasita");
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
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut obtine grupa cu numele " + numeGrupa, e);
        }

        if (grupa == null) {
            throw new GrupaNotFoundException("Grupa " + numeGrupa + " nu a fost gasita");
        }

        return grupa;
    }
}
