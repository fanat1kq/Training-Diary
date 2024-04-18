package org.example.dao;

import org.example.model.Training;
import org.example.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TrainingDAO {
    List<Map.Entry<Integer, Training>> getTraining(User user);
    Training addTraining(Training training);
    int getStatistic();
    List<String> addType(String type);
    void defaultType();
    void deleteTraining(int id);

    Training findByDate(LocalDate date, String type);

    void updateTraining(User newUser, Training newTraining);
    Optional<Training> findById(int id);
    List<Training> findAllByPlayerId(int userId);

    List<Training> findAll();
}
