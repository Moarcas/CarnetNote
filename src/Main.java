import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import model.util.DatabaseConnection;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());    
    public static void main(String[] args) {

        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("/home/cosmin/Facultate/pao/Proiecte/CarnetOnline/logging.properties"));
        } catch (IOException e) {
            System.err.println("Nu s-a putut configura fisierul de log.");
            e.printStackTrace();
        }

        logger.info("Aplicatia a pornit.");

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        
        view.Startup.Startup.startMenu();
        
        databaseConnection.closeConnection();

        logger.info("Aplicatia s-a oprit.");
    }
}