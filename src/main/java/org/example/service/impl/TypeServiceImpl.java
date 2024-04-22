package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.TrainingTypeDAO;
import org.example.model.Type;
import org.example.service.TypeService;

import java.util.List;

@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TrainingTypeDAO trainingTypeDAO;

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
