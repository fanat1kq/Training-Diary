package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.TrainingTypeDAO;
import org.example.service.TypeService;
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TrainingTypeDAO trainingTypeDAO;

    @Override
    public int getTypeId(String type) {
        return trainingTypeDAO.getTypeId(type);
    }
}
