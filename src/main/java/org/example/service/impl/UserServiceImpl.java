package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.exception.NotFoundException;
import org.example.model.User;
import org.example.service.UserService;


public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getByLogin(String login) {
        User user = userDAO.findByLogin(login);
        if (user == null) {
        throw new NotFoundException("Player with login " + login + " not found!");
        }
        return user;
    }

}
