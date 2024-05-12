package org.example.service.impl;

import org.example.aop.annotations.Audit;
import org.example.dao.TrainingTypeDAO;
import org.example.exception.NotValidParameterException;
import org.example.in.dto.AddTypeRequest;
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
    @Audit
    @Override
    public List<Type> getAllType() {
        return trainingTypeDAO.findAll();
    }
    @Audit
    @Override
    public Type addType(AddTypeRequest request) {
        if (request.type == null) throw new NotValidParameterException("Type can not be empty");
        Type type = Type.builder().typeName(request.type).build();
        return trainingTypeDAO.save(type);
    }
}
