package org.example.liquibase;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.dbconfig.Config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * class for start Liquibase  which migration of DB
 * method startLiquibase(), do migrations
 */
public class Liquibase {
    private static String URL = Config.getUrl();
    private static String USERNAME = Config.getUsername();
    private static String PASSWORD = Config.getPassword();
    private static String SCHEMA = Config.getSchema();
    private static String FILE = Config.getChangeLogFile();
    public void start() {
        try {
            Connection connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setDefaultSchemaName(SCHEMA);
            liquibase.Liquibase liquibase = new liquibase.Liquibase(FILE, new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println("Миграции успешно выполнены!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}