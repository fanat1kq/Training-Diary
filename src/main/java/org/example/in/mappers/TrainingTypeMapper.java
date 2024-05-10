//package org.example.in.mappers;
//
//
//import org.example.in.dto.TrainingDTO;
//import org.example.in.dto.TypeDTO;
//import org.example.model.Training;
//import org.example.model.Type;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper
//public interface TrainingTypeMapper {
//
//
//    TrainingTypeMapper INSTANCE = Mappers.getMapper(TrainingTypeMapper.class);
//
//    Type toEntity(TrainingDTO dto);
//
//    TypeDTO toDTO(Training entity);
//
//
//    List<TypeDTO> toDTOList(List<Type> entities);
//
//    List<Type> toEntityList(List<TypeDTO> dtos);
//}