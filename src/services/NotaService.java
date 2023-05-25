package services;

import java.util.List;

import exceptions.DatabaseException;
import model.dao.NotaDAO;
import model.entity.Nota;

public class NotaService {
    private static NotaService instance = null;
    private NotaDAO notaDAO;

    public NotaService() {
        notaDAO = NotaDAO.getInstance();
    }

    public static synchronized NotaService getInstance() {
        if (instance == null) {
            instance = new NotaService();
        }
        return instance;
    }

    public void addNota(Nota nota) throws DatabaseException {
        notaDAO.addNota(nota);
    }

    public List<Nota> getNoteByStudent(int idStudent, int idMaterie) throws DatabaseException {
        return notaDAO.getNoteByStudent(idStudent, idMaterie);
    }
}
