package org.example.in;

import org.example.controller.MainController;
import org.example.dao.impl.ExtraDAOImpl;
import org.example.dao.impl.TrainingDAOImpl;
import org.example.dao.impl.SecurityDAOImpl;
import org.example.dao.impl.TrainingTypeDAOImpl;
import org.example.dbconfig.ConnectionManager;
import org.example.handler.MainHandler;
import org.example.handler.SecurityHandler;
import org.example.model.User;
import org.example.service.ExtraService;
import org.example.service.TrainingService;
import org.example.service.SecurityService;
import org.example.service.TypeService;
import org.example.service.impl.ExtraServiceImpl;
import org.example.service.impl.TrainingServiceImpl;
import org.example.service.impl.SecurityServiceImpl;
import org.example.service.impl.TypeServiceImpl;

import java.text.ParseException;
import java.util.Scanner;
/**
 * Created by fanat1kq on 12/04/2024.
 * console of application
 */

public class AppConsole {
    static Scanner scanner = new Scanner(System.in);
    static SecurityService securityService = new SecurityServiceImpl(new SecurityDAOImpl(new ConnectionManager()));
    static TrainingService trainingService = new TrainingServiceImpl(new TrainingDAOImpl(new ConnectionManager()));
    static TypeService typeService = new TypeServiceImpl(new TrainingTypeDAOImpl(new ConnectionManager()));
    static ExtraService extraService = new ExtraServiceImpl(new ExtraDAOImpl(new ConnectionManager()));

    private static final MainController mainController = new MainController(trainingService, securityService, typeService, extraService);
//    private static UserStage userStage;

    /**
     * main menu of App
     */
    public static void startApp() throws ParseException {
//        userStage = UserStage.SECURITY;
        while (true) {
            final String startMenu = """
                    Внимание
                    нажмите цифру желаемого действия
                    1.Войти
                    2.Создать пользователя
                    3.Выйти
                    """;
            System.out.println(startMenu);
            int input = Integer.parseInt(scanner.nextLine().toLowerCase());
            switch (input) {
                case 1 -> SecurityHandler.handleAuthorization(mainController);
                case 2 -> SecurityHandler.handleRegistration(mainController);
                case 3 -> SecurityHandler.exit();
            }
        }
    }

    /**
     * menu working with data of training
     *
     * @param user data of user
     * @throws ParseException exception
     */
    public static void AppLoop(User user) throws ParseException {
        while (true) {
            final String loopMenu = """
                    1. Просмотреть тренировки
                    2. Добавить тренировку
                    3. Удалить тренировку
                    4. Отредактировать тренировку
                    5. Посмотреть статистику по колориям
                    6. Добавить тип тренировки
                    7. Обратно в меню
                    8. Выход
                    """;
            System.out.println(loopMenu);
            int input = Integer.parseInt(scanner.nextLine().toLowerCase());
            switch (input) {
                case 1 -> MainHandler.handlerGetTraining(user, mainController);
                case 2 -> MainHandler.handlerGetAllType(user, mainController);
                case 3 -> MainHandler.handlerDeleteTraining(mainController);
                case 4 -> MainHandler.handlerUpdateTraining(user, mainController);
                case 5 -> MainHandler.handlerGetStatic(mainController);
                case 6 -> MainHandler.handlerAddType(mainController);
                case 7 -> startApp();
                case 8 -> MainHandler.handlerExit();
            }
            }
    }

}

