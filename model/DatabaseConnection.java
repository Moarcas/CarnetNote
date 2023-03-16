import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection()  {
        if (connection == null) {
            try {
                // încarcarea driverului JDBC
                Class.forName("org.sqlite.JDBC");

                // stabilirea conexiunii cu baza de date
                connection = DriverManager.getConnection("jdbc:sqlite:DataBase/carnet_note.db");

                System.out.println("Conexiunea la baza de date s-a efecutat cu succes!");

            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Eroare la conexiunea la baze de date");
            }
        }
        return connection;
    }
}
