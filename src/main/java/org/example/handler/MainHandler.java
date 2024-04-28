package org.example.handler;

import org.example.controller.MainController;
import org.example.exception.NotFoundException;
import org.example.model.Extra;
import org.example.model.Training;
import org.example.model.Type;
import org.example.model.User;

import java.time.LocalDate;
import java.util.Scanner;


/**
 * Handler class for main user operations. Provides methods to handle tasks related to user functionalities.
 */
public class MainHandler {
    static Scanner scanner = new Scanner(System.in);


    /**
     * Displays the current meter readings for the authenticated user.
     *
     * @param user user.
     * @param mainController The main controller for accessing business logic.
     */
    public static void handlerGetTraining(User user, MainController mainController) {
        System.out.println(mainController.getTraining(user));
    }

    /**
     * Handles the submission of meter readings by the authenticated user.
     *
     * @param user user.
     * @param mainController The main controller for accessing business logic.
     */
    public static void handlerGetAllType(User user, MainController mainController) {
        Training training = inputTraining(user, mainController);
        mainController.addTraining(training);
    }

    /**
     * Displays meter readings for a specific month and year for the authenticated user.
     *
     * @param mainController The main controller for accessing business logic.
     */
    public static void handlerDeleteTraining(MainController mainController) {
        System.out.println("Введите id тренировки для удаления");
        int id = Integer.parseInt(scanner.nextLine());
        mainController.deleteTraining(id);
    }

    /**
     * Displays the meter reading history for the authenticated user.
     *
     * @param user user.
     * @param mainController The main controller for accessing business logic.
     */
    public static void handlerUpdateTraining(User user, MainController mainController) {
        System.out.println("Введите id тренировки для редактирования");
        int id = Integer.parseInt(scanner.nextLine());
        Training training = inputTraining(user, mainController);
        training.setId(id);
        mainController.updateTraining(user, training);
    }
    public static void handlerGetStatic(MainController mainController) {
        int calorie2 = mainController.getStatistic();
        System.out.println("Всего потрачено каларий " +calorie2+" за последние 3 месяца");
    }
    public static void handlerAddType(MainController mainController) {
        System.out.println("Введите новый тип тренировки");
        String type2 = scanner.nextLine().toLowerCase();
        System.out.println(mainController.addType(Type.builder().typeName(type2).build()));
    }
    public static void handlerExit() {
        scanner.close();
        System.exit(0);}
    public static Training inputTraining(User user, MainController mainController){
        System.out.println(mainController.getAllType());
        System.out.println("Введите название типа тренировки из списка");
        String type = scanner.nextLine();
        boolean find = false;
        for (Type t:mainController.getAllType()) {
            String typeName = t.getTypeName();
            if (typeName.equals(type)){
                find= true;
                break;
            }
        }
        if (!find){
            throw new NotFoundException("Такого типа тренировки нет");
        }
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
        Training training = Training.builder().userId(user.getId()).time(time).calorie(calorie).
                date(date).typeId(idType).extraId(extra.id).build();
        return training;
    }
}
