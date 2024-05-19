package lk.ijse.hardwareManagment.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hardwaremanagmentsystem", "root", "1234");

    private DbConnection() throws SQLException {
    }

    public static DbConnection getInstance() throws SQLException {
        return dbConnection == null ? (dbConnection = new DbConnection()) : dbConnection;
    }

    public Connection getConnection() {
        return this.connection;
    }
}