package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.exception.NotFoundException;
import org.example.model.Users;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    @Override
    public Users getByLogin(String login) {
        Users users = userRepository.findByLogin(login);
        if (users == null) {
        throw new NotFoundException("User with login " + login + " not found!");
        }
        return users;
    }

}
