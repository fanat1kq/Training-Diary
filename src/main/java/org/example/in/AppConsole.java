package org.example.in;

import org.example.dao.TrainingDAO;
import org.example.dao.UserDAO;
import org.example.models.User;
import org.example.models.enumerates.Role;

import java.text.ParseException;
import java.util.Scanner;
/**
 * Created by fanat1kq on 12/04/2024.
 * console of application
 */

public class AppConsole {
    Scanner scanner= new Scanner(System.in);
    final TrainingDAO trainingDAO = new TrainingDAO();
    UserDAO userDAO= new UserDAO();
    /**
     * main menu of App
     */
    public void startApp() throws ParseException {
        while(true) {
            System.out.println("Внимание");
            System.out.println("нажмите цифру желаемого действия");
            System.out.println("1.Войти");
            System.out.println("2.Создать пользователя");
            System.out.println("3.Выйти");
            int input = Integer.parseInt(scanner.nextLine().toLowerCase());
            switch (input) {
                case 1:
                    userDAO.login();
                    break;
                case 2:
                    userDAO.createUser();
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
            }
        }
    }
    /**
     *menu working with data of training
     * @exception ParseException
     * @param user data of user
     * @param role role of user
     */
    public  void AppLoop(User user, Role role) throws ParseException {
        while (true) {
            System.out.println("1. Просмотреть тренировки");
            System.out.println("2. Добавить тренировку");
            System.out.println("3. Удалить тренировку");
            System.out.println("4. Посмотреть статистику по колориям");
            System.out.println("5. Добавить тип тренировки");
            System.out.println("6. Обратно в меню");
            System.out.println("7. Выход");
            int input = Integer.parseInt(scanner.nextLine().toLowerCase());
            switch (input) {
                case 1:
                    trainingDAO.getTraining(user,role);
                    break;
                case 2:
                    trainingDAO.addTraining(user);
                    break;
                case 3:
                    trainingDAO.deleteTraining();
                    break;
                case 4:
                    trainingDAO.getStatistic();
                    break;
                case 5:
                    trainingDAO.addType();
                    break;
                case 6:
                    startApp();
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
            }
        }}
}


