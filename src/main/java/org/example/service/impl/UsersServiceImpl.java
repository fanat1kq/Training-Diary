package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.exception.NotFoundException;
import org.example.model.Users;
import org.example.repository.UsersRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    @Transactional(readOnly = true)
    @Override
    public Users getByLogin(String login) {
        Users users = usersRepository.findByLogin(login);
        if (users == null) {
        throw new NotFoundException("User with login " + login + " not found!");
        }
        return users;
    }

}
