import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.List;
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
        
        UserDAO userDAO = UserDAO.getInstance();

        try {
            List<User> students = userDAO.getAllUsers("srtudent");

            for (User student : students) {
                System.out.println(student.toString()); 
                System.out.println();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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