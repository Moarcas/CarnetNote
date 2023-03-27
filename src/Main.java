import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.GrayFilter;

import exceptions.GrupaNotFoundException;
import exceptions.MaterieNotFoundException;
import exceptions.UserNotFoundException;
import model.dao.GrupaDAO;
import model.dao.MaterieDAO;
import model.dao.StudentDAO;
import model.dao.UserDAO;
import model.entity.Grupa;
import model.entity.Materie;
import model.entity.Profesor;
import model.entity.Student;
import model.entity.User;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("/home/cosmin/Facultate/PAO/Proiecte/CarnetOnline/logging.properties"));
        } catch (IOException e) {
            System.err.println("Nu s-a putut configura fisierul de log.");
            e.printStackTrace();
        }

        logger.info("Aplicatia a pornit.");
        
        // MaterieDAO materieDAO = MaterieDAO.getInstance();
        // try {
        //     Materie materie = materieDAO.getMaterieById(4);
        //     System.out.println(materie.toString());
        // } catch (MaterieNotFoundException e) {
        //     System.err.println("Materia nu a fost gaista");
        //     e.printStackTrace();
        // }

        // GrupaDAO grupaDAO = GrupaDAO.getInstance();
        // Grupa grupa = new Grupa("251", 2, null); 
        // grupaDAO.addGrupa(grupa);
    
        // Grupa grupa2;
        // try {
        //     grupa2 = grupaDAO.getGrupaById(1);
        //     System.out.println(grupa2.toString());
        // } catch (GrupaNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (UserNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    
        // UserDAO userDAO = UserDAO.getInstance();
        // Student student = new Student("Maca", "Maca", "mamcamaca@gmai.com", "sdfsdfd", 1);
        // userDAO.addUser(student);

        // try {
        //     grupa2 = grupaDAO.getGrupaById(1);
        //     System.out.println(grupa2.toString());
        // } catch (GrupaNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (UserNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        
    
        try {
            UserDAO userDAO = UserDAO.getInstance();
            User profesor = userDAO.getUserById(30);
            System.out.println(profesor.toString());

        } catch (UserNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MaterieNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GrupaNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        logger.info("Aplicatia s-a oprit.");
    }
}