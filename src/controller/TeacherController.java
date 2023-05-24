package controller;

import exceptions.EmailAlreadyUser;
import model.entity.Profesor;
import model.entity.User;
import services.ProfesorService;
import services.UserService;

public class TeacherController {
    private UserService userService = new ProfesorService();
    private static TeacherController instance = null;

    public TeacherController() {}

    public static synchronized TeacherController getInstance() {
        if (instance == null) {
            instance = new TeacherController();
        }
        return instance;
    }

    public void createAccount(String nume, String prenume, String email, String password) throws EmailAlreadyUser {
        User user;

        user = new Profesor(nume, prenume, email, password, null, null);
        userService.registerUser(user);
    }

    public void login(String email, String password) {
        userService.loginUser(email, password);
    }

}
