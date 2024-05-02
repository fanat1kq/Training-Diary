package org.example.dbconfig;

import org.example.liquibase.Liquibase;

public class LoadDB {
    public void db() {
        String dbUrl = Config.getUrl();
        String dbUser = Config.getUsername();
        String dbPassword = Config.getPassword();
        ConnectionManager connectionManager = new ConnectionManager(dbUrl, dbUser, dbPassword);
        Liquibase liquibase = new Liquibase(connectionManager);
        liquibase.start();
    }
}
