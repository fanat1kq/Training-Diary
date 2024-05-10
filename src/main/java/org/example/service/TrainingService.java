package org.example.service;

import org.example.in.dto.AddTrainingRequest;
import org.example.in.dto.UpdateTrainingRequest;
import org.example.model.Training;
import org.example.model.User;
import org.example.model.enumerates.Role;

import java.util.List;

public interface TrainingService {
    List<Training> getTraining(int userId, Role role);
    Training addTraining(AddTrainingRequest request, int userId);
    int getStatistic();

    void deleteTraining(int id);

    Training updateTraining(User newUser, UpdateTrainingRequest request);




}
