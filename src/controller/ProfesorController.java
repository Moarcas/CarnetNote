package controller;

import exceptions.DatabaseException;
import exceptions.EmailAlreadyUser;
import exceptions.ProfesorAlreadyEnrolled;
import model.entity.Profesor;
import services.ProfesorService;
import services.UserService;

public class ProfesorController {
    private UserService userService = new ProfesorService();
    private static ProfesorController instance = null;

    public ProfesorController() {}

    public static synchronized ProfesorController getInstance() {
        if (instance == null) {
            instance = new ProfesorController();
        }
        return instance;
    }

    public Profesor createAccount(String nume, String prenume, String email, String password) throws EmailAlreadyUser {
        Profesor user = new Profesor(nume, prenume, email, password); 
        return (Profesor) userService.registerUser(user);
    }

    public Profesor login(String email, String password) {
        try {
            return (Profesor) userService.loginUser(email, password);
        } catch (ClassCastException e) {
            // Au fost introduce credentialele unui student
            return null;
        }
    }

    public void enrollInCourseAndClass(int idProfesor, int idMaterie, int idGrupa) throws ProfesorAlreadyEnrolled, DatabaseException {
        ((ProfesorService) userService).addTeacherToClass(idProfesor, idGrupa, idMaterie);
    }
}
