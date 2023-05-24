package controller;

import java.util.List;

import exceptions.MaterieNotFoundException;
import model.entity.Materie;
import services.MaterieService;

public class MaterieController {
    private static MaterieController instance = null;
    private MaterieService materieService;

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
    
    public Materie getCourseById(int id) throws MaterieNotFoundException {
        return materieService.getCourseById(id);
    }

    public List<Materie> getAllCourses() {
        System.out.println("MaterieController.getAllCourses()");
        List<Materie> materii = materieService.getAllCourses();
        if (materii == null) {
            System.out.println("MaterieController.getAllCourses() -> materii == null");
        } else {
            System.out.println("MaterieController.getAllCourses() -> materii != null");
        }
        return materieService.getAllCourses();
    }
}   
