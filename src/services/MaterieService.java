package services;

import java.util.List;

import model.dao.MaterieDAO;
import model.entity.Materie;

public class MaterieService {
    private static MaterieService instance = null;
    private MaterieDAO materieDAO;

    public MaterieService() {
        materieDAO = MaterieDAO.getInstance();
    }

    public static synchronized MaterieService getInstance() {
        if (instance == null) {
            instance = new MaterieService();
        }
        return instance;
    }

    public void addCourse(Materie materie) {
        materieDAO.addCourse(materie);
    }    

    public Materie getCourseById(int id) {
        return materieDAO.getCourseById(id);
    }

    public List<Materie> getAllCourses() {
        return materieDAO.getAllCourses();
    }

    public List<Materie> getCoursesByStudent(int idStudent) {
        return materieDAO.getCoursesByStudent(idStudent);
    }
}
