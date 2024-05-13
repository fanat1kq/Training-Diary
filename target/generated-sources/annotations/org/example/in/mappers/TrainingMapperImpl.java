package org.example.in.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.in.dto.TrainingDTO;
import org.example.model.Training;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-13T12:13:33+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Homebrew)"
)
@Component
public class TrainingMapperImpl implements TrainingMapper {

    @Override
    public List<TrainingDTO> toDTOList(List<Training> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TrainingDTO> list = new ArrayList<TrainingDTO>( entities.size() );
        for ( Training training : entities ) {
            list.add( trainingToTrainingDTO( training ) );
        }

        return list;
    }

    protected TrainingDTO trainingToTrainingDTO(Training training) {
        if ( training == null ) {
            return null;
        }

        TrainingDTO trainingDTO = new TrainingDTO();

        trainingDTO.setId( training.getId() );
        trainingDTO.setTime( training.getTime() );
        trainingDTO.setCalorie( training.getCalorie() );
        trainingDTO.setDate( training.getDate() );
        trainingDTO.setUserId( training.getUserId() );
        trainingDTO.setTypeId( training.getTypeId() );
        trainingDTO.setExtraId( training.getExtraId() );

        return trainingDTO;
    }
}
