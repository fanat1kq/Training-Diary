package org.example.dao;

import org.example.model.Type;

public interface TrainingTypeDAO extends MainDAO<Integer, Type> {
    int getTypeId(String type);
}
