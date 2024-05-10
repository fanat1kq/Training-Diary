package org.example.service;

import org.example.in.dto.AddTypeRequest;
import org.example.model.Type;

import java.util.List;

public interface TypeService {
    int getTypeId(String type);

    List<Type> getAllType();
    Type addType(AddTypeRequest request);
}
