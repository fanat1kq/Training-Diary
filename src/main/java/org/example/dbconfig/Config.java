package org.example.dbconfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class Config is configuration of DB
 * Config use for get URL, name of user and password for connection with DB
 */
public class Config {

    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @return get URL DB
     */
    public static String getUrl() {
        return properties.getProperty("db.url");
    }


    /**
     *
     * @return get name of user
     */
    public static String getUsername() {
        return properties.getProperty("db.username");
    }
    /**
     *
     * @return get password of user
     */
    public static String getPassword() {
        return properties.getProperty("db.password");
    }
    public static String getSchema() {
        return properties.getProperty("liquibase.schemaName");
    }
    public static String getChangeLogFile() {
        return properties.getProperty("liquibase.changeLogFile");
    }
    public static String getDriver() {
        return properties.getProperty("db.driver");
    }


}