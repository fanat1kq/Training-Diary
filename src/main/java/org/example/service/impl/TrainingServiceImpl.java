package org.example.service.impl;

import org.example.dao.TrainingDAO;
import org.example.exception.AlreadyExistException;
import org.example.exception.NotValidParameterException;
import org.example.in.dto.AddTrainingRequest;
import org.example.in.dto.UpdateTrainingRequest;
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
        return trainingDAO.findAllByUserId(userId);
    }

    @Override
    public Training addTraining(AddTrainingRequest request, int userId) {
        if (request.getTime()< 0 | request.getCalorie()<0 | request.getExtraId()<0 | request.getTypeId()<0 | userId<0)
            throw new NotValidParameterException("must not be negative.");
        Training training = Training.builder().time(request.getTime()).calorie(request.getCalorie()).
                date(request.getDate()).extraId(request.getExtraId()).typeId(request.getTypeId()).
                userId(userId).build();
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
        if (id < 0) throw new NotValidParameterException("must not be negative.");
        trainingDAO.deleteTraining(id);
    }

    @Override
    public Training updateTraining(User newUser, UpdateTrainingRequest request) {
        if (request.getTime()< 0 | request.getCalorie()<0 | request.getExtraId()<0 | request.getTypeId()<0 | request.getUserId()<0)
            throw new NotValidParameterException("must not be negative.");
        Training newTraining = Training.builder().id(request.getId()).
                time(request.getTime()).calorie(request.getCalorie()).
                date(request.getDate()).extraId(request.getExtraId()).
                typeId(request.getTypeId()).userId(request.getId()).build();
        return trainingDAO.updateTraining(newUser,newTraining);
    }



}
