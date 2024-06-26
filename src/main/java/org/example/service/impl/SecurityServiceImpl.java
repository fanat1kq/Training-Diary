package org.example.service.impl;

import org.example.aop.annotations.Audit;
import org.example.dao.UserDAO;
import org.example.exception.AuthorizeException;
import org.example.exception.RegisterException;
import org.example.in.dto.JwtResponse;
import org.example.in.security.JwtTokenProvider;
import org.example.model.User;
import org.example.service.SecurityService;

import java.nio.file.AccessDeniedException;


public class SecurityServiceImpl implements SecurityService {
    private final UserDAO userDAO;
    private final JwtTokenProvider tokenProvider;

    public SecurityServiceImpl(UserDAO userDAO, JwtTokenProvider tokenProvider) {
        this.userDAO = userDAO;
        this.tokenProvider = tokenProvider;
    }
    @Audit
    @Override
    public JwtResponse authorization(User user) {
        String accessToken = tokenProvider.createAccessToken(user.login);
        String refreshToken = tokenProvider.createRefreshToken(user.login);
        try {
            tokenProvider.authentication(accessToken);
        } catch (AccessDeniedException e) {
            throw new AuthorizeException("Access denied!.");
        }
        return new JwtResponse(user.login, accessToken, refreshToken);
    }
    @Audit
    @Override
    public User register(User user) {
        User userByName = userDAO.findByLogin(user.getLogin());
        if (userByName!=null) {
            throw new RegisterException("Такой пользователь уже существует");
        }
    return userDAO.save(user);
    }


}
