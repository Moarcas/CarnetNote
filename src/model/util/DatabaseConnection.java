package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/carnet_note.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Eroare la conectarea la baza de date!");
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            instance = null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Eroare la inchiderea bazei de date!");
        }
    }
}
