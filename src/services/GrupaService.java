package services;

import exceptions.GrupaNotFoundException;
import model.dao.GrupaDAO;
import model.entity.Grupa;

public class GrupaService {
    private static GrupaService instance = null;
    private GrupaDAO grupaDAO;
    
    public GrupaService() {
        grupaDAO = GrupaDAO.getInstance();
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
}
