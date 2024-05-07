package org.example.service.impl;

import org.example.dao.ExtraDAO;
import org.example.model.Extra;
import org.example.service.ExtraService;

public class ExtraServiceImpl implements ExtraService {
    private final ExtraDAO extraDAO;

    public ExtraServiceImpl(ExtraDAO extraDAO) {
        this.extraDAO = extraDAO;
    }

    @Override
    public Extra addExtra(Extra extra) {
        return extraDAO.save(extra);
    }

}
