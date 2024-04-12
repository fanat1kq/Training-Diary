package org.example.dao;

import org.example.models.Extra;
import org.example.models.Training;
import org.example.models.User;
import org.example.models.enumerates.Role;

import java.time.LocalDate;
import java.util.*;
/**
 * Created by fanat1kq on 12/04/2024.
 * implements users, indications
 * using List, HashMap//
 */
public class TrainingDAO {
     static int ID =1;
     public static HashMap<Training, User> trainingMap =new HashMap<>();
     public static List<String> trainingType = new ArrayList<>();
     Scanner scanner = new Scanner(System.in);

    public TrainingDAO() {
    }
    /**
     * get data of training
     * @return HashMap
     * @param user data of user
     * @param role role of user
     */
    public HashMap<Training, User> getTraining(User user, Role role) {
        if(role.equals(Role.ADMIN)){
            System.out.println(trainingMap);
            return trainingMap;}
        else if (trainingMap.containsValue(user)){
            for (Map.Entry<Training, User> entry : trainingMap.entrySet()) {
                Training key = entry.getKey();
                System.out.println(key);
            }
            }
        return null;
        }

    /**
     * add data of training in HashMap
     * @param user data of user
     * @return HashMap
     */
    public  HashMap<Training, User> addTraining(User user)  {
        System.out.println("Введите название типа тренировки из списка");
        System.out.println(trainingType);
        String name = scanner.nextLine();
        if (trainingType.contains(name)){
            System.out.println("Введите длительность в минутах");
            int time = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите количество сженных калорий");
            int calorie = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите дату");
            System.out.println("пример 10/04/1995");
            String[] input = scanner.nextLine().split("/");
            int day = Integer.parseInt(input[0]);
            int month = Integer.parseInt(input[1]);
            int year = Integer.parseInt(input[2]);
            System.out.println("Добавьте дополнительную информацию");
            String extraName = scanner.nextLine();
            System.out.println("Введите значение");
            int extraValue = Integer.parseInt(scanner.nextLine());
            Extra extra= new Extra();
            extra.setName(extraName);
            extra.setValue(extraValue);
            Training training2 = new Training(ID,name, time, calorie, LocalDate.of(year, month, day),extra);
            trainingMap.put(training2,user);
            ID++;
            return trainingMap;
            }
        else {System.out.println("такого нет");
        }
        return null;
    }
    /**
     * get static of using calories meantime training
     * @return int
     */
    public  int getStatistic()  {
        int calorie = 0;
        for (Map.Entry<Training, User> entry : trainingMap.entrySet()) {
            LocalDate date = entry.getKey().date;
            LocalDate after =LocalDate.now().minusMonths(3);
            if(date.isAfter(after)){
                calorie = entry.getKey().calorie;
                calorie++;}
        }
        System.out.println("Всего потрачено калорий за последние три месяца "+calorie);
        return calorie;
    }
    /**
     * add type of Training
     */
    public  void addType()  {
        System.out.println("Введите новый тип тренировки");
        String name = scanner.nextLine().toLowerCase();
        trainingType.add(name);
        System.out.println(trainingType);
    }
    /**
     * default data of type training
     */
    public void defalt() {
        trainingType.add("кардио");
        trainingType.add("подтягивания");
    }
    /**
     * get static of using calories meantime training
     * @return boolean
     */
    public boolean deleteTraining() {
        System.out.println(trainingMap);
        System.out.println("Введите id тренировки для удаления");
        int id = Integer.parseInt(scanner.nextLine());
        for (Training key : trainingMap.keySet()){
            if (key.id==id){
                trainingMap.remove(key);
                return true;
            }
        }
    return false;}
}
