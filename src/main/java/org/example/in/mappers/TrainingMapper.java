package org.example.in.mappers;

import org.example.in.dto.TrainingDTO;
import org.example.model.Training;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface TrainingMapper {


    TrainingMapper INSTANCE = Mappers.getMapper(TrainingMapper.class);

    Training toEntity(TrainingDTO dto);

    TrainingDTO toDTO(Training entity);


    List<TrainingDTO> toDTOList(List<Training> entities);

    List<Training> toEntityList(List<TrainingDTO> dtos);
}