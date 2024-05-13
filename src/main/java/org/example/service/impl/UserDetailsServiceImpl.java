package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.exception.AuthorizeException;
import org.example.model.Users;
import org.example.repository.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


/**
 * The user details service implementation
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository playerDAO;


    /**
     * @param username the username identifying the user whose data is required.
     * @return user details
     * @throws UsernameNotFoundException when user with username not found
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = playerDAO.findByLogin(username);
        if (users == null) {
            throw new AuthorizeException("There is no users with this login in the database.");
        }
        return new User(
                users.getLogin(),
                users.getPassword(),
                Collections.emptyList()
        );
    }
}
