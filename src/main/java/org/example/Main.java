package org.example;

import org.example.dbconfig.LoadDB;
import org.example.in.AppConsole;

import java.text.ParseException;

/**
 * Created by fanat1kq on 12/04/2024.
 */

public class Main {

    public static void main(String[] args) throws ParseException {
        LoadDB loadDB = new LoadDB();
        loadDB.db();
        AppConsole.startApp();
    }
}