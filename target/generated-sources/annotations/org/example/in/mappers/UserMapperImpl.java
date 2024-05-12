package org.example.in.mappers;

import javax.annotation.processing.Generated;
import org.example.in.dto.UserDTO;
import org.example.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-12T15:21:58+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setLogin( entity.getLogin() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.login( dto.getLogin() );

        return user.build();
    }
}
