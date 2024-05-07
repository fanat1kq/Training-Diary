package org.example.service.impl;

import org.example.dao.TrainingTypeDAO;
import org.example.model.Type;
import org.example.service.TypeService;

import java.util.List;


public class TypeServiceImpl implements TypeService {
    private final TrainingTypeDAO trainingTypeDAO;

    public TypeServiceImpl(TrainingTypeDAO trainingTypeDAO) {
        this.trainingTypeDAO = trainingTypeDAO;
    }

    @Override
    public int getTypeId(String type) {
        return trainingTypeDAO.getTypeId(type);
    }

    @Override
    public List<Type> getAllType() {
        return trainingTypeDAO.findAll();
    }

    @Override
    public Type addType(Type type) {
        return trainingTypeDAO.save(type);
    }
}
