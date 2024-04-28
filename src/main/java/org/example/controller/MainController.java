package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.exception.NotValidParametrException;
import org.example.model.Extra;
import org.example.model.Training;
import org.example.model.Type;
import org.example.model.User;
import org.example.service.ExtraService;
import org.example.service.TrainingService;
import org.example.service.SecurityService;
import org.example.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RequiredArgsConstructor
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private final TrainingService trainingService;
    private final SecurityService securityService;
    private final TypeService typeService;
    private final ExtraService extraService;
    public List<Training> getTraining(User user){
        log.info("The user with login " + user.getLogin() + " showing his training history");
        return trainingService.getTraining(user.getId(),user.getRole());
    }
    public Training addTraining(Training training){
        log.info("The user with login  add his training");
        return trainingService.addTraining(training);
    }
    public int getStatistic(){
        log.info("The user see calories for 3 month");
        return trainingService.getStatistic();
    }
    public void deleteTraining(int id){
        log.info("The user delete training by id");
        trainingService.deleteTraining(id);
    }
    public User login(User user){
        log.info("The user trying to log in with login " + user.getLogin() + " and password " + user.getPassword());
        return securityService.login(user);
    }
    public User createUser(User user){
        log.info("The user trying to register with login " + user.getLogin() + " and password " + user.getPassword());

        if (user.getLogin() == null || user.getPassword() == null || user.getLogin().isEmpty() ||  user.getPassword().isEmpty()) {
            throw new NotValidParametrException("Логин и пароль не могут быть пустыми");
        }

        if (user.getPassword().length() < 2 || user.getPassword().length() > 10) {
            throw new NotValidParametrException("Длина пароля должна быть от 2 до 10 символов");
        }

        return securityService.createUser(user);
    }


    public Training updateTraining(User newUser, Training newTraining) {
        log.info("The user "+ newUser.getLogin() +" update training");
        return trainingService.updateTraining(newUser,newTraining);
    }

    public Extra addExtra(Extra extra) {
        log.info("The user add new extra information of training");
        return extraService.addExtra(extra);
    }

    public Type addType(Type type){
        log.info("The user add new type training");
        return typeService.addType(type);
    }

    public List<Type> getAllType() { return typeService.getAllType();
    }

    public int getTypeId(String type) {
        return typeService.getTypeId(type);
    }
}
