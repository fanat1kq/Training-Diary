package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aop.Audit;
import org.example.exception.NotValidParameterException;
import org.example.in.dto.AddExtraRequest;
import org.example.model.Extra;
import org.example.repository.ExtraInformationRepository;
import org.example.service.ExtraInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtraInformationInformationServiceImpl implements ExtraInformationService {
    private final ExtraInformationRepository extraInformationRepository;
    @Transactional
    @Audit
    @Override
    public Extra addExtra(AddExtraRequest request) {
        if (request.name == null) {
            throw new NotValidParameterException("Name of extra information can not be empty");
        } else if (request.value < 0) {
            throw new NotValidParameterException("Value of extra information can not be empty");
        }
        Extra extra = Extra.builder().name(request.getName()).value(request.getValue()).build();
        return extraInformationRepository.save(extra);
    }
    @Transactional(readOnly = true)
    @Audit
    @Override
    public List<Extra> getExtra() {
        return extraInformationRepository.findAll();
    }

}
