package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.Administrator;
import model.util.DatabaseConnection;

public class AdministratorDAO {
    private static AdministratorDAO instance = null;
    private Connection connection;
    
    private AdministratorDAO() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public static synchronized AdministratorDAO getInstance() {
        if (instance == null) {
            instance = new AdministratorDAO();
        }
        return instance;
    }

    public void addAdministrator(Administrator administrator) throws SQLException {
        String sql = "INSERT INTO admins (id) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setInt(1, administrator.getId());
    }
}
