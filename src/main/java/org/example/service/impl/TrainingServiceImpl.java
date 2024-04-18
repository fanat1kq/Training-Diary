package org.example.service.impl;

import org.example.dao.TrainingDAO;
import org.example.exception.AlreadyExistException;
import org.example.model.Training;
import org.example.model.User;
import org.example.model.enumerates.Role;
import org.example.service.TrainingService;

import java.util.List;

public class TrainingServiceImpl implements TrainingService {
    private final TrainingDAO trainingDAO;

    public TrainingServiceImpl(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public List<Training> getTraining(int userId, Role role) {
        if (role.equals(Role.ADMIN)) {
            return trainingDAO.findAll();
        }
        return trainingDAO.findAllByPlayerId(userId);
    }

    @Override
    public Training addTraining(Training training) {
        Training trainingByDate = trainingDAO.findByDate(training.getDate(), training.getType());
        if (trainingByDate!=null){
            throw new AlreadyExistException("Тренировка в этот день уже записана");
        }
        return trainingDAO.addTraining(training);
    }

    @Override
    public int getStatistic() {
        return trainingDAO.getStatistic();
    }

    @Override
    public List<String> addType(String type) {

        return trainingDAO.addType(type);
    }
    @Override
    public void defaultType() {
        trainingDAO.defaultType();
    }

    @Override
    public void deleteTraining(int id) {
        trainingDAO.deleteTraining(id);
    }

    @Override
    public void updateTraining(User newUser, Training newTraining) {
        trainingDAO.updateTraining(newUser,newTraining);
    }
}