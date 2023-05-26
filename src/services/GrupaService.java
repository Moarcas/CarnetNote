package services;

import java.util.List;

import exceptions.DatabaseException;
import exceptions.GrupaNotFoundException;
import model.dao.GrupaDAO;
import model.dao.ProfesorMaterieDAO;
import model.entity.Grupa;

public class GrupaService {
    private static GrupaService instance = null;
    private GrupaDAO grupaDAO;
    private ProfesorMaterieDAO profesorMaterieDAO;
    
    public GrupaService() {
        grupaDAO = GrupaDAO.getInstance();
        profesorMaterieDAO = ProfesorMaterieDAO.getInstance();
    }

    public static synchronized GrupaService getInstance() {
        if (instance == null) {
            instance = new GrupaService();
        }
        return instance;
    }

    public Grupa getGrupaById(int id) throws GrupaNotFoundException {
        return grupaDAO.getGrupaById(id);
    } 

    public Grupa getGrupaByName(String numeGrupa) throws GrupaNotFoundException {
        return grupaDAO.getGrupaByName(numeGrupa);
    }
    
    public List<Grupa> getClassesByTeacher(int idProfesor) throws DatabaseException {
        return profesorMaterieDAO.getClassesByTeacher(idProfesor);
    }

    public List<Grupa> getGrupeByTeacherAndCourse(int idProfesor, int idMaterie) throws DatabaseException {
        return profesorMaterieDAO.getClassesByTeacherAndCourse(idProfesor, idMaterie);
    }

    public List<Grupa> getAllClasses() throws DatabaseException {
        return grupaDAO.getAllClasses();
    }

}
