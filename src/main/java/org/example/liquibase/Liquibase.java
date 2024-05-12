package org.example.liquibase;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.util.Config;
import org.example.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * class for start Liquibase  which migration of DB
 * method startLiquibase(), do migrations
 */
public class Liquibase {
    private final ConnectionManager connectionManager;
    private static final String SCHEMA = Config.getSchema();
    private static final String FILE = Config.getChangeLogFile();
    private static final String SQL_CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS app";

    public Liquibase(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void start() {
        try {
            Connection connection = connectionManager.getConnection();
            createSchemaForMigration(connection);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setDefaultSchemaName(SCHEMA);
            liquibase.Liquibase liquibase = new liquibase.Liquibase(FILE, new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println("Миграции успешно выполнены!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createSchemaForMigration(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(SQL_CREATE_SCHEMA);
        statement.close();
    }

}