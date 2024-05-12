package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aop.Audit;
import org.example.exception.NotValidParameterException;
import org.example.in.dto.AddTypeRequest;
import org.example.model.Type;
import org.example.repository.TypeTrainingRepository;
import org.example.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeTrainingRepository typeTrainingRepository;
    @Transactional(readOnly = true)
    @Override
    public int getTypeId(String type) {
        return typeTrainingRepository.findByTypeName(type);
    }
    @Transactional(readOnly = true)
    @Audit
    @Override
    public List<Type> getAllType() {
        return typeTrainingRepository.findAll();
    }
    @Transactional
    @Audit
    @Override
    public Type addType(AddTypeRequest request) {
        if (request.type == null) throw new NotValidParameterException("Type can not be empty");
        Type type = Type.builder().typeName(request.type).build();
        return typeTrainingRepository.save(type);
    }
}
