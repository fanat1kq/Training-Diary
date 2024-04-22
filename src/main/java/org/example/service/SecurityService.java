package org.example.service;

import org.example.model.User;

public interface SecurityService {
    User login(User user);
    User createUser(User user);

}
