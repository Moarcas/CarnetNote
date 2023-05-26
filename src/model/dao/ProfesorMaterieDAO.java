package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.DatabaseException;
import exceptions.MaterieNotFoundException;
import exceptions.ProfesorAlreadyEnrolled;
import exceptions.UserNotFoundException;
import model.entity.Grupa;
import model.entity.Materie;
import model.entity.Profesor;
import model.util.DatabaseConnection;

public class ProfesorMaterieDAO {
    private static ProfesorMaterieDAO instance = null;
    private Connection connection;
    private static final Logger logger = Logger.getLogger(ProfesorMaterieDAO.class.getName());

    private ProfesorMaterieDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized ProfesorMaterieDAO getInstance() {
        if (instance == null) {
            instance = new ProfesorMaterieDAO();
        }
        return instance;
    }

    public void addTeacherToClass(int idProfesor, int idGrupa, int idMaterie) throws ProfesorAlreadyEnrolled, DatabaseException {
        String sql = "INSERT INTO teacher_subject (idProfesor, idMaterie, idGrupa) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idProfesor);
            stmt.setInt(2, idMaterie);
            stmt.setInt(3, idGrupa);
            stmt.executeUpdate();
            logger.info("Profesorul cu id-ul " + idProfesor + " a fost adaugat cu succes la materia cu id-ul " + idMaterie + " la grupa cu id-ul " + idGrupa);
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                // Cheie primară duplicată
                logger.log(Level.WARNING, "Profesorul cu id-ul " + idProfesor + " este deja înscris la materia cu id-ul " + idMaterie + " la grupa cu id-ul " + idGrupa, e);
                throw new ProfesorAlreadyEnrolled("\nProfesorul este deja inregistrat la aceasta materie\n");
            } else {
                // Alte excepții SQL
                logger.log(Level.SEVERE, "Nu s-a putut adauga profesorul cu id-ul " + idProfesor + " la materia cu id-ul " + idMaterie + " la grupa cu id-ul " + idGrupa, e);
                throw new DatabaseException("\nDatabase error\n.", e);
            }
        }
    }

    public void deleteTeacherFromClass(int idProfesor, int idGrupa, int idMaterie) {
        String sql = "DELETE FROM teacher_subject WHERE idProfesor = ? AND idGrupa = ? AND idMaterie = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idProfesor);
            stmt.setInt(2, idGrupa);
            stmt.setInt(3, idMaterie);
            
            int affectdRows = stmt.executeUpdate();
            if (affectdRows == 0) {
                logger.warning("Profesorul cu id-ul " + idProfesor + " de la materia cu id-ul " + idMaterie + " de la grupa cu id-ul " + idGrupa + " nu a fost gasit in tabelul techer_subject");
            }
            else {
                logger.info("Profesorul cu id-ul " + idProfesor + " de la materia cu id-ul " + idMaterie + " de la grupa cu id-ul " + idGrupa + " a fost sters cu succes din tabelul teacher_subject");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut sterge profesorul cu id-ul " + idProfesor + " din tabela techer_subject", e);
        } 
    }

    public List<Profesor> getTeachersBySubject(int idMaterie) throws SQLException, UserNotFoundException {
        List<Profesor> profesori = new ArrayList<>();
    
        String sql = "SELECT u.nume, u.prenume, u.email, u.passwordHash FROM users u JOIN teacher_subject ts ON ts.idProfesor = u.id WHERE ts.idMaterie = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMaterie);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {   
                    String nume = rs.getString("nume");
                    String prenume = rs.getString("prenume");
                    String email = rs.getString("email");
                    String passwordHash = rs.getString("passwordHash");
    
                    Profesor profesor = new Profesor(nume, prenume, email, passwordHash);

                    profesori.add(profesor);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-au putut obtine profesorii din tabela teacher_subject", e);
            throw e;
        }

        if (profesori.isEmpty()) {
            throw new UserNotFoundException("Nu exista profesori pentru materia data.");
        }

        return profesori;
    }

    public List<Materie> getSubjectsByTeacher(int idProfesor) throws SQLException, MaterieNotFoundException {
        List<Materie> materii = new ArrayList<>();
    
        String sql = "SELECT DISTINCT s.id, s.nume, s.descriere FROM subjects s JOIN teacher_subject ts ON s.id = ts.idMaterie WHERE ts.idProfesor = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProfesor);
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nume = rs.getString("nume");
                    String descriere = rs.getString("descriere");
    
                    Materie materie = new Materie(nume, descriere);
                    materie.setId(id);
    
                    materii.add(materie);
                }
            }
    
            if (materii.isEmpty()) {
                throw new MaterieNotFoundException("Nu s-au gasit materii asociate cu profesorul dat");
            }
    
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-au putut obtine materiile din tabela teacher_subject", e);
            throw e;
        }
        return materii;
    }
    
    public List<Grupa> getClassesByTeacher(int idProfesor) throws DatabaseException {
        List<Grupa> grupe = new ArrayList<>();

        String sql = "SELECT DISTINCT c.id, c.nume, c.an FROM classes c JOIN teacher_subject ts ON c.id = ts.idGrupa WHERE ts.idProfesor = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProfesor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nume = rs.getString("nume");
                    int an = rs.getInt("an");

                    Grupa grupa = new Grupa(nume, an);
                    grupa.setId(id);

                    grupe.add(grupa);
                }
            } 
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-au putut obtine grupele din tabela teacher_subject", e);
            throw new DatabaseException("Nu s-au putut obtine grupele din tabela teacher_subject", e);
        }

        return grupe;
    }

    public List<Grupa> getClassesByTeacherAndCourse(int idProfesor, int idMaterie) throws DatabaseException {
        List<Grupa> grupe = new ArrayList<>();

        String sql = "SELECT DISTINCT c.id, c.nume, c.an FROM classes c JOIN teacher_subject ts ON c.id = ts.idGrupa WHERE ts.idProfesor = ? AND ts.idMaterie = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProfesor);
            stmt.setInt(2, idMaterie);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nume = rs.getString("nume");
                    int an = rs.getInt("an");

                    Grupa grupa = new Grupa(nume, an);
                    grupa.setId(id);

                    grupe.add(grupa);
                }
            } 
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Nu s-au putut obtine grupele din tabela teacher_subject", e);
            throw new DatabaseException("Database error", e);
        }

        return grupe;
    }

}
