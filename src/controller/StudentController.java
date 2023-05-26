package controller;

import java.util.List;

import exceptions.DatabaseException;
import exceptions.EmailAlreadyUser;
import exceptions.GrupaNotFoundException;
import exceptions.StudentAlreadyEnrolledException;
import model.entity.Materie;
import model.entity.Student;
import services.GrupaService;
import services.StudentService;
import services.UserService;

public class StudentController {
    private UserService userService = StudentService.getInstance();
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
        try {
            return (Student) userService.loginUser(email, password);
        } catch (ClassCastException e) {
            // Au fost introduce credentialele unui profesor
            return null;
        }
    }

    public List<Materie> getMaterii(int idStudent) {
        return ((StudentService) userService).getCourses(idStudent);
    }

    public void enrollInCourse(int idStudent, int idMaterie) throws StudentAlreadyEnrolledException, DatabaseException {
        ((StudentService) userService).addCourse(idStudent, idMaterie);
    }

    public List<Student> getStudentsByMaterieAndGrupa(int idMaterie, int grupa) throws DatabaseException {
        return ((StudentService) userService).getStudentsByMaterieAndGrupa(idMaterie, grupa);
    }
}