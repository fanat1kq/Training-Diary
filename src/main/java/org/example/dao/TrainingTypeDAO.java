package org.example.dao;

import org.example.model.Type;

public interface TrainingTypeDAO extends MainDAO<Type> {
    int getTypeId(String type);

}
