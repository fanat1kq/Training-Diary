package org.example.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class ConnectionManager use for connection with DB
 */
public class ConnectionManager {

    private static String URL = Config.getUrl();
    private static String USERNAME = Config.getUsername();
    private static String PASSWORD = Config.getPassword();

    public ConnectionManager(String url, String username, String password) {
        this.URL = url;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}