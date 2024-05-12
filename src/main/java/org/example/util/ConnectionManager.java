package org.example.util;

import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class ConnectionManager use for connection with DB
 */
@AllArgsConstructor
public class ConnectionManager {

    private static String URL = Config.getUrl();
    private static String USERNAME = Config.getUsername();
    private static String PASSWORD = Config.getPassword();
    private static String DRIVER = Config.getDriver();

    public ConnectionManager(String url, String username, String password, String driver) {
        URL = url;
        USERNAME = username;
        PASSWORD = password;
        DRIVER = driver;
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}