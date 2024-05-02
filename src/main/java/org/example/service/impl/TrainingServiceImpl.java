package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dao.TrainingDAO;
import org.example.exception.AlreadyExistException;
import org.example.model.Training;
import org.example.model.User;
import org.example.model.enumerates.Role;
import org.example.service.TrainingService;

import java.util.List;
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService {
    private final TrainingDAO trainingDAO;

    @Override
    public List<Training> getTraining(int userId, Role role) {
        if (role.equals(Role.ADMIN)) {
            return trainingDAO.findAll();
        }
        return trainingDAO.findAllByUserId(userId);
    }

    @Override
    public Training addTraining(Training training) {
        Training trainingByDate = trainingDAO.findByDate(training.getDate(), training.getTypeId());
        if (trainingByDate!=null){
            throw new AlreadyExistException("Тренировка в этот день уже записана");
        }
        return trainingDAO.save(training);
    }

    @Override
    public int getStatistic() {
        return trainingDAO.getStatistic();
    }

    @Override
    public void deleteTraining(int id) {
        trainingDAO.deleteTraining(id);
    }

    @Override
    public Training updateTraining(User newUser, Training newTraining) {
        return trainingDAO.updateTraining(newUser,newTraining);
    }



}
