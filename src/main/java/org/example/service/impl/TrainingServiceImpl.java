package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aop.Audit;
import org.example.exception.AlreadyExistException;
import org.example.exception.NotValidParameterException;
import org.example.in.dto.AddTrainingRequest;
import org.example.in.dto.UpdateTrainingRequest;
import org.example.model.Training;
import org.example.model.Users;
import org.example.model.enumerates.Role;
import org.example.repository.TrainingRepository;
import org.example.service.TrainingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    @Transactional(readOnly = true)
    @Audit
    @Override
    public List<Training> getTraining(int userId, Role role) {
        if (role.equals(Role.ADMIN)) {
            return trainingRepository.findAll();
        }
        return trainingRepository.findAllByUserId(userId);
    }
    @Transactional
    @Audit
    @Override
    public Training addTraining(AddTrainingRequest request, int userId) {
        if (request.getTime()< 0 | request.getCalorie()<0 | request.getExtraId()<0 | request.getTypeId()<0 | userId<0)
            throw new NotValidParameterException("must not be negative.");
        Training training = Training.builder().time(request.getTime()).calorie(request.getCalorie()).
                date(request.getDate()).extraId(request.getExtraId()).typeId(request.getTypeId()).
                userId(userId).build();
        Training trainingByDate = trainingRepository.findAllByDate(training.getDate(), training.getTypeId());
        if (trainingByDate!=null){
            throw new AlreadyExistException("Тренировка в этот день уже записана");
        }
        return trainingRepository.save(training);
    }
    @Transactional(readOnly = true)
    @Audit
    @Override
    public int getStatistic() {
//        return trainingRepository.getStatistic();
        return 0;
    }
    @Transactional
    @Audit
    @Override
    public void deleteTraining(int id) {
        if (id < 0) throw new NotValidParameterException("must not be negative.");
        trainingRepository.deleteById(id);
    }
    @Transactional
    @Audit
    @Override
    public Training updateTraining(Users newUsers, UpdateTrainingRequest request) {
        if (request.getTime()< 0 | request.getCalorie()<0 | request.getExtraId()<0 | request.getTypeId()<0 | request.getUserId()<0)
            throw new NotValidParameterException("must not be negative.");
        Training newTraining = Training.builder().id(request.getId()).
                time(request.getTime()).calorie(request.getCalorie()).
                date(request.getDate()).extraId(request.getExtraId()).
                typeId(request.getTypeId()).userId(request.getId()).build();
        return trainingRepository.updateById(newUsers,newTraining);
    }
}
