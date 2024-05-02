package org.example.dao;

import org.example.model.Training;
import org.example.model.User;

import java.time.LocalDate;
import java.util.List;

public interface TrainingDAO extends MainDAO<Training> {
    int getStatistic();
    void deleteTraining(int id);

    Training findByDate(LocalDate date, int type);

    Training updateTraining(User newUser, Training newTraining);
    List<Training> findAllByUserId(int userId);

    List<Training> findAll();


}
