package controller;

import java.util.List;

import model.entity.Materie;
import services.MaterieService;

public class MaterieController {
    private static MaterieController instance = null;
    private MaterieService materieService = MaterieService.getInstance();

    public MaterieController() {}

    public static synchronized MaterieController getInstance() {
        if (instance == null) {
            instance = new MaterieController();
        }
        return instance;
    }

    public void addCourse(String nume, String descriere) {
        Materie materie = new Materie(nume, descriere);
        materieService.addCourse(materie);
    }
    
    public Materie getCourseById(int id)  {
        return materieService.getCourseById(id);
    }

    public List<Materie> getAllCourses() {
        return materieService.getAllCourses();
    }

    public List<Materie> getCoursesByStudent(int idStudent) {
        return materieService.getCoursesByStudent(idStudent);
    }
}   
