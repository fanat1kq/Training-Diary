package org.example.service.impl;

import org.example.aop.annotations.Audit;
import org.example.dao.ExtraDAO;
import org.example.exception.NotValidParameterException;
import org.example.in.dto.AddExtraRequest;
import org.example.model.Extra;
import org.example.service.ExtraService;

import java.util.List;

public class ExtraServiceImpl implements ExtraService {
    private final ExtraDAO extraDAO;

    public ExtraServiceImpl(ExtraDAO extraDAO) {
        this.extraDAO = extraDAO;
    }
    @Audit
    @Override
    public Extra addExtra(AddExtraRequest request) {
        if (request.name == null) {
            throw new NotValidParameterException("Name of extra information can not be empty");
        } else if (request.value < 0) {
            throw new NotValidParameterException("Value of extra information can not be empty");
        }
        Extra extra = Extra.builder().name(request.getName()).value(request.getValue()).build();
        return extraDAO.save(extra);
    }
    @Audit
    @Override
    public List<Extra> getExtra() {
        return extraDAO.findAll();
    }

}
