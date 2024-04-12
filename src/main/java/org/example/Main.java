package org.example;

import org.example.dao.TrainingDAO;
import org.example.in.AppConsole;

import java.text.ParseException;

/**
 * Created by fanat1kq on 12/04/2024.
 */

public class Main {
    public static void main(String[] args) throws ParseException {
            TrainingDAO trainingDAO = new TrainingDAO();
            trainingDAO.defalt();
            AppConsole appConsole = new AppConsole();
            appConsole.startApp();
    }
}