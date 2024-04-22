package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dao.SecurityDAO;
import org.example.exception.AuthorizeException;
import org.example.exception.RegisterException;
import org.example.model.User;
import org.example.service.SecurityService;
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final SecurityDAO securityDAO;

    @Override
    public User login(User user) {
        User userByName = securityDAO.findByLogin(user.getLogin());
        if (userByName==null) {
            throw new AuthorizeException("Такого пользователя не существует");
        }
        if (!userByName.getPassword().equals(user.getPassword())) {
            throw new AuthorizeException("Не правильный пароль");
        }

        return userByName;
    }

    @Override
    public User createUser(User user) {
        User userByName = securityDAO.findByLogin(user.getLogin());
        if (userByName!=null) {
            throw new RegisterException("Такой пользователь уже существует");
        }
    return securityDAO.save(user);
    }

}
