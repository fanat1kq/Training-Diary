package org.example.in.mappers;

import org.example.in.dto.UserDTO;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User entity);

    User toEntity(UserDTO dto);
}
