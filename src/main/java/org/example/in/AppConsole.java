package org.example.in;

import org.example.controller.MainController;
import org.example.dao.impl.TrainingDAOImpl;
import org.example.dao.impl.SecurityDAOImpl;
import org.example.model.Extra;
import org.example.model.Training;
import org.example.model.Type;
import org.example.model.User;
import org.example.model.enumerates.Role;
import org.example.service.TrainingService;
import org.example.service.SecurityService;
import org.example.service.impl.TrainingServiceImpl;
import org.example.service.impl.SecurityServiceImpl;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * Created by fanat1kq on 12/04/2024.
 * console of application
 */

public class AppConsole {
    Scanner scanner= new Scanner(System.in);
    static SecurityService securityService = new SecurityServiceImpl(new SecurityDAOImpl());
    static TrainingService trainingService = new TrainingServiceImpl(new TrainingDAOImpl());
    private static final MainController mainController=new MainController(trainingService, securityService);

    /**
     * main menu of App
     */
    public void startApp() throws ParseException {
        while(true) {
            final String startMenu="""
            Внимание
            нажмите цифру желаемого действия
            1.Войти
            2.Создать пользователя
            3.Выйти
            """;
            System.out.println(startMenu);
            int input = Integer.parseInt(scanner.nextLine().toLowerCase());
            switch (input) {
                case 1 -> {
                    System.out.println("Введите логин и пароль:");
                    String[] input1 = scanner.nextLine().split(" ");
                    if (input1.length < 1) {
                        System.out.println("невверный формат");
                        continue;
                    }
                    String name = input1[0];
                    String password = input1[1];
                    User user = new User(name, password);
                    User login = mainController.login(user);
                    AppLoop(login);
                }
                case 2 -> {
                    final String userExample = """
                            Введите нового пользователя: логин, пароль и права доступа(user, admin)
                            Пример: юзер 123 ADMIN
                            ввод производить через пробелы
                            """;
                    System.out.println(userExample);
                    String[] input2 = scanner.nextLine().split(" ");
                    if (input2.length < 3) {
                        System.out.println("неверный формат");
                        continue;
                    }
                    String name1 = input2[0];
                    String password1 = input2[1];
                    Role role = Role.valueOf(input2[2].toUpperCase());
                    User user2 = new User(name1, password1, role);
                    mainController.createUser(user2);
                }
                case 3 -> {
                    scanner.close();
                    System.exit(0);
                }
            }
        }
    }
    /**
     *menu working with data of training
     * @exception ParseException
     * @param user data of user
     */
    public  void AppLoop(User user) throws ParseException {
        while (true) {
            final String loopMenu="""
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
                case 1 -> System.out.println(mainController.getTraining(user));
                case 2 -> {
                    System.out.println("Введите название типа тренировки из списка");
                    System.out.println(TrainingDAOImpl.trainingType);
                    String type = scanner.nextLine();
//                    if (!TrainingDAOImpl.trainingType.contains(type)){
//                        throw new NotFoundException("Такого типа тренировки нет");
//                    }
                    int idType = mainController.getTypeId(type);
                    System.out.println("Введите длительность в минутах");
                    int time = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите количество сженных калорий");
                    int calorie = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите дату");
                    System.out.println("Пример 10/04/1995");
                    String[] in = scanner.nextLine().split("/");
                    int day = Integer.parseInt(in[0]);
                    int month = Integer.parseInt(in[1]);
                    int year = Integer.parseInt(in[2]);
                    LocalDate date = LocalDate.of(year, month, day);
                    System.out.println("Добавьте дополнительную информацию");
                    String extraName = scanner.nextLine();
                    System.out.println("Введите значение");
                    int extraValue = Integer.parseInt(scanner.nextLine());
                    Extra extra = mainController.addExtra(Extra.builder().name(extraName).value(extraValue).build());
//                    Training training = new Training(user.getId(), type,time,calorie,date,extra);
                    Training training = Training.builder().userId(user.getId()).time(time).calorie(calorie).
                            date(date).typeId(idType).extraId(extra.id).build();
                    mainController.addTraining(training);
                }
                case 3 -> {
                    System.out.println("Введите id тренировки для удаления");
                    int id = Integer.parseInt(scanner.nextLine());
                    mainController.deleteTraining(id);
                }
                case 4 -> {
                    System.out.println();
                    System.out.println("Введите id тренировки для редактирования");
                    int id1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите название типа тренировки из списка");
                    System.out.println(TrainingDAOImpl.trainingType);
                    String type1 = scanner.nextLine();
//                    if (!TrainingDAOImpl.trainingType.contains(type1)){
//                        throw new NotFoundException("Такого типа тренировки нет");
//                    }
                    int idType1 = mainController.getTypeId(type1);
                    System.out.println("Введите длительность в минутах");
                    int time1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите количество сженных калорий");
                    int calorie1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите дату");
                    System.out.println("Пример 10/04/1995");
                    String[] in1 = scanner.nextLine().split("/");
                    int day1 = Integer.parseInt(in1[0]);
                    int month1 = Integer.parseInt(in1[1]);
                    int year1 = Integer.parseInt(in1[2]);
                    LocalDate date1 = LocalDate.of(year1, month1, day1);
                    System.out.println("Добавьте дополнительную информацию");
                    String extraName1 = scanner.nextLine();
                    System.out.println("Введите значение");
                    int extraValue1 = Integer.parseInt(scanner.nextLine());
                    Extra extra1 = mainController.addExtra(Extra.builder().name(extraName1).value(extraValue1).build());
                    Training training1 = new Training(id1, user.getId(), time1, calorie1, date1, idType1, extra1.getId());
                    mainController.updateTraining(user, training1);
                }
                case 5 -> mainController.getStatistic();
                case 6 -> {
                    System.out.println("Введите новый тип тренировки");
                    String type2 = scanner.nextLine().toLowerCase();
                    System.out.println(mainController.addType(Type.builder().typeName(type2).build()));
                }
                case 7 -> startApp();
                case 8 -> {
                    scanner.close();
                    System.exit(0);
                }
            }
        }}

//    public void defaultValues() {
//        mainController.defalt();
//    }
}


