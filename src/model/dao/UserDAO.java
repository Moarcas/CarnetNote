package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.DatabaseException;
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

    public void addUser(User user) {
        try {
            // Adauga user in tabela users
            addUserToUsersTable(user);
        } catch (SQLException e) {
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
                profesorDAO.addTeacher((Profesor) user);   
                logger.info("Profesorul " + user.getNume() + " " + user.getPrenume() + " a fost adaugat cu succes in baza de date.");    
           
            } catch (SQLException e) {
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
                    user = profesorDAO.getTeacherById(id);
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
            logger.log(Level.SEVERE, "Utilizatorul cu ID-ul " + id + " nu a fost gasit in baza de date");
            throw new UserNotFoundException("Utilizatorul cu ID-ul " + id + " nu a fost gasit");
        }

        return user;
    }
    
    public User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM USERS WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
        
            if (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String passwordHash = rs.getString("passwordHash");
                String rol = rs.getString("rol");

                if (rol.equals("student")) {
                    StudentDAO studentDAO = StudentDAO.getInstance();
                    user = studentDAO.getStudentById(id);
                } else if (rol.equals("profesor")) {
                    ProfesorDAO profesorDAO = ProfesorDAO.getInstance();
                    user = profesorDAO.getTeacherById(id);
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
            logger.log(Level.SEVERE, "Nu s-a putut obtine utilizatorul cu email-ul " + email, e);
            throw new DatabaseException("Nu s-a putut obtine utilizatorul cu email-ul " + email, e);
        } 
        return user;
    } 


    public List<User> getAllUsers(String userType) throws SQLException, UserNotFoundException, MaterieNotFoundException, GrupaNotFoundException {
        List<User> users = new ArrayList<>();

        switch (userType) {
            case "student":
                StudentDAO studentDAO = StudentDAO.getInstance();
                users = studentDAO.getAllStudents();
                break;
            case "profesor":
                ProfesorDAO profesorDAO = ProfesorDAO.getInstance();
                users = profesorDAO.getAllTeachers();
                break;
            case "administrator":
                AdministratorDAO administratorDAO = AdministratorDAO.getInstance();
                // users = administratorDAO.getAllAdmins();
                break;
            default:
                throw new IllegalStateException("Unexpected value " + userType);
        }

        return users;
    }

    public void updateUser(int id, User user) throws SQLException, UserNotFoundException {
        if (user instanceof Student) {
            try {
                StudentDAO studentDAO = StudentDAO.getInstance();
                studentDAO.updateStudent(id, (Student) user);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut actualiza studentul " + user.getNume() + " " + user.getPrenume() + " in baza de date.", e);        
                throw e;
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut actualiza studentul " + user.getNume() + " " + user.getPrenume() + " in baza de date: " + e.getMessage(), e);
                throw e;
            }
        } else if (user instanceof Profesor) {
            try {
                ProfesorDAO profesorDAO = ProfesorDAO.getInstance();
                profesorDAO.updateTeacher(id, (Profesor) user);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut actualiza studentul " + user.getNume() + " " + user.getPrenume() + " in baza de date.", e);        
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut actualiza studentul " + user.getNume() + " " + user.getPrenume() + " in baza de date: " + e.getMessage(), e);
                throw e;
            }
        // } else if (user instanceof Administrator) {
        //     try {
        //         AdministratorDAO administratorDAO = AdministratorDAO.getInstance();
        //         // administratorDAO.updateAdministrator((Administrator) user);     
        //     } catch (SQLException e) {
        //         e.printStackTrace();
        //         logger.log(Level.SEVERE, "Nu s-a putut actualiza studentul " + user.getNume() + " " + user.getPrenume() + " in baza de date.", e);        
        //     }
        }

        String sql = "UPDATE users SET nume = ?, prenume = ?, email = ?, passwordHash = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getNume());
        stmt.setString(2, user.getPrenume());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getPasswordHash());
        stmt.setInt(5, id);

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0) {
            throw new UserNotFoundException("Utilizatorul cu id-ul " + id + " nu a fost gasit in baza de date");
        }

        logger.info("Informatiile utilizatorului " + user.getNume() + " " + user.getPrenume() + " au fost actualizate cu succes.");
    } 

    public void deleteUser(int id) throws MaterieNotFoundException, GrupaNotFoundException, UserNotFoundException {
        UserDAO userDAO = UserDAO.getInstance();
        User user = userDAO.getUserById(id);  
        
        if (user instanceof Student) {
            try {
                StudentDAO studentDAO = StudentDAO.getInstance();
                studentDAO.deteleStudent(id);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut sterge studentul " + user.getNume() + " " + user.getPrenume() + " din baza de date.", e);        
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut sterge studentul " + user.getNume() + " " + user.getPrenume() + " din baza de date: " + e.getMessage(), e);
            }
        } else if (user instanceof Profesor) {
            try {
                ProfesorDAO profesorDAO = ProfesorDAO.getInstance();
                profesorDAO.deteleTeacher(id);
            } catch (SQLException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut sterge profesorul " + user.getNume() + " " + user.getPrenume() + " din baza de date.", e);        
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Nu s-a putut sterge profesorul " + user.getNume() + " " + user.getPrenume() + " din baza de date: " + e.getMessage(), e);
                throw e;
            }
        } else if (user instanceof Administrator) {
            // try {
            //     StudentDAO studentDAO = StudentDAO.getInstance();
            //     studentDAO.deteleStudent(id);
            // } catch (SQLException e) {
            //     e.printStackTrace();
            //     logger.log(Level.SEVERE, "Nu s-a putut sterge studentul " + user.getNume() + " " + user.getPrenume() + " din baza de date.", e);        
            // } catch (UserNotFoundException e) {
            //     e.printStackTrace();
            //     logger.log(Level.SEVERE, "Nu s-a putut sterge studentul " + user.getNume() + " " + user.getPrenume() + " din baza de date: " + e.getMessage(), e);
            //     throw e;
            // }
        }

        // sterge din tabela users
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut sterge user-ul cu id-ul " + id, e);
        }
        logger.info("User-ul cu id-ul " + id + " a fost sters cu succes din baza de date");
    }

    public boolean isEmailAlreadyUsed(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Nu s-a putut verifica daca adresa de email este deja folosita", e);
        }
        return false;
    }

}