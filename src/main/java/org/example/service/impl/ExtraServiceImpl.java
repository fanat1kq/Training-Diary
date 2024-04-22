package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dao.ExtraDAO;
import org.example.dao.TrainingDAO;
import org.example.model.Extra;
import org.example.service.ExtraService;
@AllArgsConstructor
public class ExtraServiceImpl implements ExtraService {
    private final ExtraDAO extraDAO;
    @Override
    public Extra addExtra(Extra extra) {
        return extraDAO.save(extra);
    }

}
