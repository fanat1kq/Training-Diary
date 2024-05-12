package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aop.Audit;
import org.example.exception.AuthorizeException;
import org.example.exception.RegisterException;
import org.example.in.dto.JwtResponse;
import org.example.in.security.JwtTokenProvider;
import org.example.model.Users;
import org.example.repository.UserRepository;
import org.example.service.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;
    @Transactional
    @Audit
    @Override
    public JwtResponse authorization(Users users) {
        String accessToken = tokenProvider.createAccessToken(users.login);
        String refreshToken = tokenProvider.createRefreshToken(users.login);
        try {
            tokenProvider.authentication(accessToken);
        } catch (AccessDeniedException e) {
            throw new AuthorizeException("Access denied!.");
        }
        return new JwtResponse(users.login, accessToken, refreshToken);
    }
    @Transactional
    @Audit
    @Override
    public Users register(Users users) {
        Users usersByName = userRepository.findByLogin(users.getLogin());
        if (usersByName !=null) {
            throw new RegisterException("Такой пользователь уже существует");
        }
    return userRepository.save(users);
    }


}
