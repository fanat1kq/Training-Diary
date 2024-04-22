package org.example.service;

import org.example.model.Type;

import java.util.List;

public interface TypeService {
    int getTypeId(String type);

    List<Type> getAllType();
}
