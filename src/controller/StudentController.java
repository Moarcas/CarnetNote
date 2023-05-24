package controller;

import java.util.List;

import exceptions.EmailAlreadyUser;
import exceptions.GrupaNotFoundException;
import model.entity.Materie;
import model.entity.Student;
import services.GrupaService;
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

    public Student createAccount(String nume, String prenume, String email, String password, String numeGrupa) throws EmailAlreadyUser, GrupaNotFoundException {
        int idGrupa = 0;
        GrupaService grupaService = GrupaService.getInstance();
        idGrupa = grupaService.getGrupaByName(numeGrupa).getId();

        Student user = new Student(nume, prenume, email, password, idGrupa);
        return (Student) userService.registerUser(user);
    }

    public Student login(String email, String password) {
        return (Student) userService.loginUser(email, password);
    }

    public List<Materie> getMaterii(int idStudent) {
        return ((StudentService) userService).getCourses(idStudent);
    }

    public void enrollInCourse(int idStudent, int idMaterie) {
        ((StudentService) userService).addCourse(idStudent, idMaterie);
    }
}