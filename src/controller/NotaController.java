package controller;

import java.util.List;

import exceptions.DatabaseException;
import model.entity.Nota;
import services.NotaService;

public class NotaController {
    private NotaService notaService = NotaService.getInstance();
    private static NotaController instance = null;

    public NotaController() {}

    public static synchronized NotaController getInstance() {
        if (instance == null) {
            instance = new NotaController();
        }
        return instance;
    }

    public void addNota(int idStudent, int idMaterie, float nota, String data) throws DatabaseException {
        Nota notaObj = new Nota(idStudent, idMaterie, nota, data);
        notaService.addNota(notaObj);
    }

    public List<Nota> getNoteByStudent(int idStudent, int idMaterie) throws DatabaseException {
        return notaService.getNoteByStudent(idStudent, idMaterie);
    }
}
