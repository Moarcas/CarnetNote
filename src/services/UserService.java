package services;

import java.sql.SQLException;
import java.util.List;

import exceptions.EmailAlreadyUser;
import exceptions.GrupaNotFoundException;
import exceptions.MaterieNotFoundException;
import exceptions.UserNotFoundException;
import model.dao.UserDAO;
import model.entity.User;

public abstract class UserService {
    protected UserDAO userDAO;

    protected UserService() {
        userDAO = UserDAO.getInstance();
    }

    public User getUserById(int id) throws UserNotFoundException, MaterieNotFoundException, GrupaNotFoundException {
        return userDAO.getUserById(id);
    }

    public List<User> getAllUsers(String userType) throws SQLException, UserNotFoundException, MaterieNotFoundException, GrupaNotFoundException {
        return userDAO.getAllUsers(userType);
    }

    public void updateUser(int id, User user) throws SQLException, UserNotFoundException {
        userDAO.updateUser(id, user);
    }

    public void deleteUser(int id) throws MaterieNotFoundException, GrupaNotFoundException, UserNotFoundException {
        userDAO.deleteUser(id);
    }

    public User registerUser(User user) throws EmailAlreadyUser {
        String emailUser= user.getEmail();

        if (userDAO.isEmailAlreadyUsed(emailUser)) {
            throw new EmailAlreadyUser("Email already used");
        }
        userDAO.addUser(user);
        return user;
    }

    public User loginUser(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        return user;
    }
}
