package model.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.sqlite.ExtendedCommand;
import org.sqlite.javax.SQLitePooledConnection;

import exceptions.GrupaNotFoundException;
import exceptions.MaterieNotFoundException;
import exceptions.UserNotFoundException;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Administrator;
import model.entity.Profesor;
import model.entity.Student;
import model.entity.User;
import model.util.DatabaseConnection;

public class UserDAO {
    private static UserDAO instance = null;
    private static Connection connection;
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    private UserDAO() {
        // Se realizeaza conexiunea la baza de date
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            logger.info("Conexiunea la baza de date a fost realizata cu succes.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Nu s-a putut realiza conexiunea la baza de date.", e);
        }
    }

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    // Adauga un nou utilizator in baza de date

    private static void addUserToUsersTable(User user) throws SQLException {
        String sql = "INSERT INTO users (nume, prenume, email, passwordHash, rol) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // Adauga user in tabela users
        stmt.setString(1, user.getNume());
        stmt.setString(2, user.getPrenume());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getPasswordHash());
        stmt.setString(5, user.getRol());
        stmt.executeUpdate();
    
        ResultSet rs = stmt.getGeneratedKeys();
        user.setId(rs.getInt(1));        
    }

    public static void addUser(User user) {
        try {
            // Adauga user in tabela users
            addUserToUsersTable(user);
        } catch (SQLException e) {
            System.err.println("Exceptie SQL: " + e.getMessage());
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut adauga studentul " + user.getNume() + " " + user.getPrenume() + " in table users.", e);
        }
        
        if (user instanceof Student) {
            try {
                // Adauga student in tabela students
                StudentDAO studentDAO = StudentDAO.getInstance();
                studentDAO.addStudent((Student) user);

                logger.info("Studentul " + user.getNume() + " " + user.getPrenume() + " a fost adaugat cu succes in baza de date.");    
            } catch (SQLException e) {
                System.err.println("Exceptie SQL: " + e.getMessage());
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut adauga studentul " + user.getNume() + " " + user.getPrenume() + " in baza de date.", e);
            }
        } else if (user instanceof Profesor) {
            try {
                // Adaug profesor in tabela teachers
                ProfesorDAO profesorDAO = ProfesorDAO.getInstance();
                profesorDAO.addProfesor((Profesor) user);

                logger.info("Profesorul " + user.getNume() + " " + user.getPrenume() + " a fost adaugat cu succes in baza de date.");    
            } catch (SQLException e) {
                System.err.println("Exceptie SQL: " + e.getMessage());
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut adauga profesorul " + user.getNume() + " " + user.getPrenume() + " in baza de date.", e);
            }
        } else if (user instanceof Administrator) {
            try {
                // Adaug admin in tabela admins
                AdministratorDAO administratorDAO = AdministratorDAO.getInstance();
                administratorDAO.addAdministrator((Administrator) user);

                logger.info("Administratorul " + user.getNume() + " " + user.getPrenume() + " a fost adaugat cu succes in baza de date.");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut adauga administratorul " + user.getNume() + " " + user.getPrenume() + " in baza de date.", e);
            }          
        }
    }

    public User getUserById(int id) throws UserNotFoundException, MaterieNotFoundException, GrupaNotFoundException {
        User user = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM USERS WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
        
            if (rs.next()) {
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String email = rs.getString("email");
                String passwordHash = rs.getString("passwordHash");
                String rol = rs.getString("rol");

                if (rol.equals("student")) {
                    StudentDAO studentDAO = StudentDAO.getInstance();
                    user = studentDAO.getStudentById(id);
                } else if (rol.equals("profesor")) {
                    ProfesorDAO profesorDAO = ProfesorDAO.getInstance();
                    user = profesorDAO.getProfesorById(id);
                } else if (rol.equals("administrator")) {
                    AdministratorDAO administratorDAO = AdministratorDAO.getInstance();
                    // user = administratorDAO.getAdministratorById(id);
                }

                user.setId(id);
                user.setNume(nume);
                user.setPrenume(prenume);
                user.setEmail(email);
                user.setPasswordHash(passwordHash);
                user.setRol(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut obtine utilizatorul cu id-ul " + id, e);
        }

        if (user == null) {
            throw new UserNotFoundException("Utilizatorul cu ID-ul " + id + " nu a fost gasit");
        }

        return user;
    }
}


