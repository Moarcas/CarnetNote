package controller;

import exceptions.EmailAlreadyUser;
import model.entity.Student;
import model.entity.User;
import services.StudentService;
import services.UserService;

public class StudentController {
    private UserService userService = new StudentService();
    private static StudentController instance = null;
    
    public StudentController() {}

    public static synchronized StudentController getInstance() {
        if (instance == null) {
            instance = new StudentController();
        }
        return instance;
    } 

    public void createAccount(String nume, String prenume, String email, String password, String grupa) throws EmailAlreadyUser {
        User user;

        user = new Student(nume, prenume, email, password, Integer.parseInt(grupa));
        userService.registerUser(user);
    }

    public void login(String email, String password) {
        userService.loginUser(email, password);
    }
}