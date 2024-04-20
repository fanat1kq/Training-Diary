package org.example.dao;

import org.example.model.Training;
import org.example.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TrainingDAO {
    List<Map.Entry<Integer, Training>> getTraining(User user);
    Training addTraining(Training training);
    int getStatistic();
    List<String> addType(String type);
    void defaultType();
    void deleteTraining(int id);

    Training findByDate(LocalDate date, String type);

    Training updateTraining(User newUser, Training newTraining);
    List<Training> findAllByUserId(int userId);

    List<Training> findAll();
}
