package org.example;

import org.example.in.AppConsole;
import org.example.liquibase.Liquibase;

import java.text.ParseException;

/**
 * Created by fanat1kq on 12/04/2024.
 */

public class Main {
    public static void main(String[] args) throws ParseException {
        Liquibase liquibase=new Liquibase();
        liquibase.start();
        AppConsole appConsole = new AppConsole();
        appConsole.startApp();
    }
}