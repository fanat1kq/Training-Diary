package org.example.in;

import org.example.dao.TrainingDAO;
import org.example.dao.UserDAO;
import org.example.models.Extra;
import org.example.models.User;
import org.example.models.enumerates.Role;
import java.text.ParseException;
import java.util.Scanner;
/**
 * Created by fanat1kq on 12/04/2024.
 * console of application
 */

public class AppConsole {
    static int ID =1;
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
                    System.out.println("Введите пользователя и пароль:");
                    String[] input1 = scanner.nextLine().split(" ");
                    if (input1.length<1){
                        System.out.println("невверный формат");
                    }
                    String name = input1[0];
                    String password = input1[1];
                    userDAO=new UserDAO();
                    userDAO.login(name, password);
                    break;
                case 2:
                    System.out.println("Введите нового пользователя, пароль и права доступа(USER, ADMIN) и :");
                    System.out.println("Пример: юзер 123 ADMIN");
                    System.out.println("ввод производить через пробелы");
                    String[] input2 = scanner.nextLine().split(" ");
                    if (input2.length<3 ){
                        System.out.println("невверный формат");
                        continue;
                    }
                    String name1=input2[0];
                    String password1=input2[1];
                    String role=input2[2];
                    userDAO=new UserDAO();
                    userDAO.createUser(name1,password1,role);
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
                    System.out.println("Введите длительность в минутах");
                    int time = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите количество сженных калорий");
                    int calorie = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите дату");
                    System.out.println("пример 10/04/1995");
                    String[] in = scanner.nextLine().split("/");
                    int day = Integer.parseInt(in[0]);
                    int month = Integer.parseInt(in[1]);
                    int year = Integer.parseInt(in[2]);
                    System.out.println("Добавьте дополнительную информацию");
                    String extraName = scanner.nextLine();
                    System.out.println("Введите значение");
                    int extraValue = Integer.parseInt(scanner.nextLine());
                    Extra extra= new Extra();
                    extra.setName(extraName);
                    extra.setValue(extraValue);
                    trainingDAO.addTraining(user,ID,time, calorie,day,month,year,extra);
                    ID++;
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


