package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.Administrator;
import model.entity.Profesor;
import model.entity.Student;
import model.entity.User;
import model.util.DatabaseConnection;

public class UserDAO {
    private static UserDAO instance = null;
    private Connection connection;

    private UserDAO() {
        // Se realizeaza conexiunea la baza de date
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    // Adauga un nou utilizator in baza de date

    public void addUser(User user) {
        if (user instanceof Student) {
            // Adauga user in tabela users
            String sql = "INSERT INTO users (id, nume, prenume, email, passwordHash, rol) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, user.getId());
                stmt.setString(2, user.getNume());
                stmt.setString(3, user.getPrenume());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getPasswordHash());
                stmt.setString(6, user.getRol());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Student student = (Student) user;

            // Adauga student in tabela students
            StudentDAO studentDAO = StudentDAO.getInstance();
            studentDAO.addStudent(student);

        } else if (user instanceof Profesor) {
            // Adauga user in tabela users
            String sql = "INSERT INTO users (id, nume, prenume, email, passwordHash, rol) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, user.getId());
                stmt.setString(2, user.getNume());
                stmt.setString(3, user.getPrenume());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getPasswordHash());
                stmt.setString(6, user.getRol());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Adauga profesor in tabela teachers

        } else if (user instanceof Administrator) {
            // Adauga user in tabela users
            String sql = "INSERT INTO users (id, nume, prenume, email, passwordHash, rol) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, user.getId());
                stmt.setString(2, user.getNume());
                stmt.setString(3, user.getPrenume());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getPasswordHash());
                stmt.setString(6, user.getRol());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Adauga administrator in table administrators            
        }
    }
}
