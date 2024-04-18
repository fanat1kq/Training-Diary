package org.example.service;

import org.example.model.Training;
import org.example.model.User;
import org.example.model.enumerates.Role;

import java.util.List;

public interface TrainingService {
    List<Training> getTraining(int userId, Role role);
    Training addTraining(Training training);
    int getStatistic();
    List<String> addType(String type);
    void defaultType();
    void deleteTraining(int id);

    void updateTraining(User newUser,Training newTraining);
}