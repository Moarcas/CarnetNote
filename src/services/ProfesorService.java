package services;

import java.sql.SQLException;
import java.util.List;

import exceptions.GrupaNotFoundException;
import exceptions.MaterieNotFoundException;
import exceptions.UserNotFoundException;
import model.dao.ProfesorMaterieDAO;
import model.entity.Grupa;
import model.entity.Materie;
import model.entity.Profesor;

public class ProfesorService extends UserService {
    private static ProfesorService instance = null;
    private ProfesorMaterieDAO profesorMaterieDAO ;

    public ProfesorService() {
        profesorMaterieDAO = ProfesorMaterieDAO.getInstance();
    }
    
    public static synchronized ProfesorService getInstance() {
        if (instance == null) {
            instance = new ProfesorService();
        }
        return instance;
    }

    public void addTeacherToClass(int idProfesor, int idGrupa, int idMaterie) {
        profesorMaterieDAO.addTeacherToClass(idProfesor, idGrupa, idMaterie);
    }

    public void deleteTeacherFromClass(int idProfesor, int idGrupa, int idMaterie) {
        profesorMaterieDAO.deleteTeacherFromClass(idProfesor, idGrupa, idMaterie);
    }

    public List<Profesor> getTeachersBySubject(int idMaterie) throws SQLException, UserNotFoundException {
        return profesorMaterieDAO.getTeachersBySubject(idMaterie);
    }

    public List<Materie> getSubjectsByTeacher(int idProfesor) throws SQLException, MaterieNotFoundException {
        return profesorMaterieDAO.getSubjectsByTeacher(idProfesor);
    }

    public List<Grupa> getClassesByTeacher(int idProfesor) throws SQLException, GrupaNotFoundException {
        return profesorMaterieDAO.getClassesByTeacher(idProfesor);
    }
}
