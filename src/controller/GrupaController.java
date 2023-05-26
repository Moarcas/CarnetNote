package controller;

import java.util.List;

import exceptions.DatabaseException;
import exceptions.GrupaNotFoundException;
import model.entity.Grupa;
import services.GrupaService;

public class GrupaController {
    private static GrupaController instance = null;
    private GrupaService grupaService = GrupaService.getInstance();
    
    public GrupaController() {}

    public static synchronized GrupaController getInstance() {
        if (instance == null) {
            instance = new GrupaController();
        }
        return instance;
    }

    public Grupa getGrupaById(int id) throws GrupaNotFoundException {
        return grupaService.getGrupaById(id);
    }

    public Grupa getGrupaByName(String numeGrupa) throws GrupaNotFoundException {
        return grupaService.getGrupaByName(numeGrupa);
    }

    public List<Grupa> getClassesByTeacher(int idProfesor) throws DatabaseException {
        return grupaService.getClassesByTeacher(idProfesor);
    }  
    
    public List<Grupa> getGrupeByTeacherAndCourse(int idProfesor, int idMaterie) throws DatabaseException {
        return grupaService.getGrupeByTeacherAndCourse(idProfesor, idMaterie);
    }

    public List<Grupa> getAllClasses() throws DatabaseException {
        return grupaService.getAllClasses();
    }
}
