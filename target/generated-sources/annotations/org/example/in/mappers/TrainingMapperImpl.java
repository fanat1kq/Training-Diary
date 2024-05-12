package org.example.in.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.in.dto.TrainingDTO;
import org.example.model.Training;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-12T15:21:58+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
public class TrainingMapperImpl implements TrainingMapper {

    @Override
    public Training toEntity(TrainingDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Training.TrainingBuilder training = Training.builder();

        training.id( dto.getId() );
        training.time( dto.getTime() );
        training.calorie( dto.getCalorie() );
        training.date( dto.getDate() );
        training.userId( dto.getUserId() );
        training.typeId( dto.getTypeId() );
        training.extraId( dto.getExtraId() );

        return training.build();
    }

    @Override
    public TrainingDTO toDTO(Training entity) {
        if ( entity == null ) {
            return null;
        }

        TrainingDTO trainingDTO = new TrainingDTO();

        trainingDTO.setId( entity.getId() );
        trainingDTO.setTime( entity.getTime() );
        trainingDTO.setCalorie( entity.getCalorie() );
        trainingDTO.setDate( entity.getDate() );
        trainingDTO.setUserId( entity.getUserId() );
        trainingDTO.setTypeId( entity.getTypeId() );
        trainingDTO.setExtraId( entity.getExtraId() );

        return trainingDTO;
    }

    @Override
    public List<TrainingDTO> toDTOList(List<Training> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TrainingDTO> list = new ArrayList<TrainingDTO>( entities.size() );
        for ( Training training : entities ) {
            list.add( toDTO( training ) );
        }

        return list;
    }

    @Override
    public List<Training> toEntityList(List<TrainingDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Training> list = new ArrayList<Training>( dtos.size() );
        for ( TrainingDTO trainingDTO : dtos ) {
            list.add( toEntity( trainingDTO ) );
        }

        return list;
    }
}
