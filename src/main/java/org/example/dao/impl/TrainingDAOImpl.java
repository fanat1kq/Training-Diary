package org.example.dao.impl;

import org.example.dao.TrainingDAO;
import org.example.exception.AlreadyExistException;
import org.example.exception.NotFoundException;
import org.example.model.Training;
import org.example.model.User;
import org.example.model.enumerates.Role;
import java.time.LocalDate;
import java.util.*;
/**
 * Created by fanat1kq on 12/04/2024.
 * implements users, indications
 * using ArraList, Treemap//
 */

public class TrainingDAOImpl implements TrainingDAO {
    private static int ID = 1;
    private final Map<Integer, Training> trainings = new HashMap<>();
    public static List<String> trainingType=new ArrayList<>();

    public TrainingDAOImpl() {
    }

    /**
     * get data of training
     *
     * @param user data of user
     * @return HashMap
     */
    @Override
    public List<Map.Entry<Integer, Training>> getTraining(User user) {
        if (user.getRole().equals(Role.ADMIN)) {
            List<Map.Entry<Integer, Training>> list = new ArrayList<>(trainings.entrySet());
            Collections.sort(list, Comparator.comparing(a -> a.getValue().getDate()));
            return list;
        } else for (Map.Entry<Integer, Training> entry : trainings.entrySet()) {
            if (entry.getValue().getUserId() == user.getId()) {
                List<Map.Entry<Integer, Training>> list = new ArrayList<>(trainings.entrySet());
                Collections.sort(list, Comparator.comparing(a -> a.getValue().getDate()));
                return list;
            }
        }
        return null;
    }

    /**
     * add data of training in HashMap
     *
     * @param training data of user
     * @return HashMap
     */
    @Override
    public Training addTraining(Training training) {
        training.setId(ID);
        trainings.put(training.getId(), training);
        ID++;
        return trainings.get(training.getId());
    }


    /**
     * get static of using calories meantime training
     *
     * @return int
     */
    @Override
    public int getStatistic() {
        int calorie = 0;
        for (Map.Entry<Integer, Training> entry : trainings.entrySet()) {
            LocalDate date = entry.getValue().getDate();
            LocalDate after = LocalDate.now().minusMonths(3);
            if (date.isAfter(after)) {
                calorie = entry.getValue().getCalorie();
                calorie++;
            }
        }
        System.out.println("Всего потрачено калорий за последние три месяца " + calorie);
        return calorie;
    }

    /**
     * add type of Training
     *
     * @return
     */
    @Override
    public List<String> addType(String type) {
        if (trainingType.contains(type)) {
            throw new AlreadyExistException("Данный тип тренировки уже существует");
        }
        trainingType.add(type);
        return trainingType;
    }

    /**
     * default data of type training
     */
    @Override
    public void defaultType() {
        trainingType.add("кардио");
        trainingType.add("подтягивания");
    }

    /**
     * delete training by id
     */
    @Override
    public void deleteTraining(int id) {
        for (Integer key : trainings.keySet()) {
            if (key == id) {
                trainings.remove(key);
            }
        };
    }

    /**
     * find training by id
     *
     * @param date date of training
     * @param type type of training
     */
    @Override
    public Training findByDate(LocalDate date, String type) {
        Training training = null;
        for (Map.Entry<Integer,Training> entry : trainings.entrySet()) {
            if (entry.getValue().getDate().equals(date) &&
                    entry.getValue().getType().equals(type)) {
                training = entry.getValue();
                break;
            }
        }
        return training;
    }

    @Override
    public Training updateTraining(User newUser, Training newTraining) {
        for (Map.Entry<Integer, Training> entry : trainings.entrySet()) {
            if (entry.getValue().getId() == newTraining.getId()) {
                trainings.put(newTraining.getId(), newTraining);
                break;
            } else throw new NotFoundException("Такой тренировки нет");
        }
        return trainings.get(newTraining.getId());
    }
    @Override
    public List<Training> findAllByUserId(int userId) {
        List<Training> result = new ArrayList<>();

        for (Training training : trainings.values()) {
            if (training.getUserId()==(userId)) {
                result.add(training);
            }
        }
        result.sort(new Training());
        return result;
    }

    @Override
    public List<Training> findAll() {
        List list = new ArrayList(trainings.entrySet());
        list.sort(Comparator.comparing((Map.Entry<Integer, Training> a) -> a.getValue().getDate()));
        return list;
    }
}
