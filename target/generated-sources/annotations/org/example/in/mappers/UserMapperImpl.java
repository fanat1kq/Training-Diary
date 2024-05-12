package org.example.in.mappers;

import javax.annotation.processing.Generated;
import org.example.in.dto.UserDTO;
import org.example.model.Users;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-12T22:43:00+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Homebrew)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(Users entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setLogin( entity.getLogin() );

        return userDTO;
    }
}
