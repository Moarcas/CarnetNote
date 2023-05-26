package services;

import java.util.List;

import exceptions.DatabaseException;
import exceptions.StudentAlreadyEnrolledException;
import model.dao.StudentMaterieDAO;
import model.entity.Materie;
import model.entity.Student;

public class StudentService extends UserService {
    private static StudentService instance = null;
    
    public StudentService() {}

    public static synchronized StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public List<Materie> getCourses(int idStudent) {
        StudentMaterieDAO studentMaterieDAO = StudentMaterieDAO.getInstance();
        List<Materie> materii = studentMaterieDAO.getCorusesByStudentId(idStudent);
        return materii;
    }

    public void addCourse(int idStudent, int idMaterie) throws StudentAlreadyEnrolledException, DatabaseException {
        StudentMaterieDAO studentMaterieDAO = StudentMaterieDAO.getInstance();
        studentMaterieDAO.addStudentToCourse(idStudent, idMaterie);
    }

    public List<Student> getStudentsByMaterieAndGrupa(int idMaterie, int grupa) throws DatabaseException {
        StudentMaterieDAO studentMaterieDAO = StudentMaterieDAO.getInstance();
        List<Student> students = studentMaterieDAO.getStudentsByCourseAndGrupa(idMaterie, grupa);
        return students;
    }
}
