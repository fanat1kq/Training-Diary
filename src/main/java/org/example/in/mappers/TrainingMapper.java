package org.example.in.mappers;


import org.example.in.dto.TrainingDTO;
import org.example.model.Training;
import org.mapstruct.Mapper;


import java.util.List;

/**
 * Mapper for transaction entity
 */
@Mapper(componentModel = "spring")
public interface TrainingMapper {

    /**
     * Mapping transactions list entity to dto list
     *
     * @param entities the transactions entities
     * @return mapped transaction dto list
     */
    List<TrainingDTO> toDTOList(List<Training> entities);

}